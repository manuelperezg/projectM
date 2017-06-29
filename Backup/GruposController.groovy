package saiuv

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import jxl.DateCell
import jxl.LabelCell
import jxl.NumberCell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

@Transactional(readOnly = true)
class GruposController {

    private final static int ROW_START = 1
    private final static int COLUMN_SEMESTRE = 0
    private final static int COLUMN_NOMBRE = 1
    private final static int COLUMN_MATERIA_ID = 2
    private final static int COLUMN_DOCENTE_ID = 3
    private final static int COLUMN_HORAS = 4
    private final static int COLUMN_MODALIDAD = 5
    private final static int COLUMN_CUPO = 6

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("grupos_search_offset")){
			params.offset = session.getAttribute("grupos_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("grupos_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("grupos_search_rows")){
			params.max = session.getAttribute("grupos_search_rows").toInteger();
        } else {
			params.max = 10;
        }
        params.search_rows = params.max;
        //Recuperando la palabra de busqueda
        if (params.search_clean == "1") {
			params.search_word = "";
			params.offset = 0;
		} else if (params.search_word){
			//Enviada la palabra a buscar
			if (params.search_word != session.getAttribute("grupos_search_word")) params.offset = 0;
        } else if (session.getAttribute("grupos_search_word")){
			params.search_word = session.getAttribute("grupos_search_word");
        }
        
        def itemsList = Grupos.createCriteria().list (params) {
            if ( params.search_word ) {
				or {
					ilike("nombre", "%${params.search_word}%")
					ilike("semestre", "%${params.search_word}%")
					ilike("modalidad", "%${params.search_word}%")
				}
            }
        }
        
        session.setAttribute("grupos_search_rows", params.search_rows);
        session.setAttribute("grupos_search_word", params.search_word);
        session.setAttribute("grupos_search_offset", params.offset);
        
        respond itemsList, model:[gruposCount: itemsList.totalCount]
    }

    def alumnos(Grupos grupos) {
        // Materias
        def materias = TomaMaterias.findAllWhere(grupo_id: grupos.id)
        def alumnos_materias = materias ?Alumnos.findAllByIdInList(materias*.alumno_id) :[]

        // Tutorias
        def tutorias = TomaTutorias.findAllWhere(grupo_id: grupos.id)
        def alumnos_tutorias = tutorias ?Alumnos.findAllByIdInList(tutorias*.alumno_id) :[]

        respond grupos, model:[alumnos: alumnos_materias + alumnos_tutorias , alumnosAll:Alumnos.list()]
    }

    def show(Grupos grupos) {
		// Materias
        def materias = TomaMaterias.findAllWhere(grupo_id: grupos.id)
        def alumnos_materias = materias ?Alumnos.findAllByIdInList(materias*.alumno_id) :[]

        // Tutorias
        def tutorias = TomaTutorias.findAllWhere(grupo_id: grupos.id)
        def alumnos_tutorias = tutorias ?Alumnos.findAllByIdInList(tutorias*.alumno_id) :[]

        respond grupos, model:[alumnos: alumnos_materias + alumnos_tutorias , alumnosAll:Alumnos.list()]
        //respond grupos
    }

    def create() {
        respond new Grupos(params), model:[materias:Materias.list(), docentes:Docentes.list()]
    }

    @Transactional
    def save(Grupos grupos) {
        if (grupos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grupos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grupos.errors, view:'create'
            return
        }

        int id = 0;
        if (Grupos.createCriteria().get {projections {max "id"}} as Integer != null){
           id = Grupos.createCriteria().get {projections {max "id"}} as int
        }
        id++

        grupos.id = id

        grupos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grupos.label', default: 'Grupo'), grupos.nombre])
                redirect grupos
            }
            '*' { respond grupos, [status: CREATED] }
        }
    }

    def edit(Grupos grupos) {
        respond grupos, model:[materias:Materias.list(), docentes:Docentes.list()]
    }

    @Transactional
    def update(Grupos grupos) {
        if (grupos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grupos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grupos.errors, view:'edit'
            return
        }

        grupos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grupos.label', default: 'Grupo'), grupos.nombre])
                redirect grupos
            }
            '*'{ respond grupos, [status: OK] }
        }
    }

    @Transactional
    def delete(Grupos grupos) {

        if (grupos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        grupos.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupos.label', default: 'Grupo'), grupos.nombre])
                redirect action: "index"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Transactional
    def deleteAlumno(Grupos grupo) {

        if (grupo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def materia = TomaMaterias.findWhere(alumno_id: params.int('alumno'), grupo_id: grupo.id);
        if(materia){
            materia.delete flush:true
        }

        def tutoria = TomaTutorias.findWhere(alumno_id: params.int('alumno'), grupo_id: grupo.id);
        if(tutoria){
            tutoria.delete flush:true
        }

        grupo.inscritos = grupo.inscritos - 1
        grupo.save flush:true

        redirect action: 'show', id: grupo.id
    }

    @Transactional
    def addAlumno() {

        Grupos grupo = Grupos.findById(params.grupo_id)
        Alumnos alumno = Alumnos.findById(params.alumno_id)

        if (grupo == null || alumno == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        // Tutoria
        if ( grupo.getMateria().clave.startsWith("TUT") ) {
            def materia = new TomaTutorias()
            
            int id = 0;
			if (TomaTutorias.createCriteria().get {projections {max "id"}} as Integer != null){
			   id = TomaTutorias.createCriteria().get {projections {max "id"}} as int
			}
			id++
			materia.id = id

            materia.alumno_id = alumno.id
            materia.grupo_id = grupo.id
            materia.estado = params.estado
            materia.semestre = alumno.semestre
            materia.save flush:true
        }
        // Materia
        else {
            def materia = new TomaMaterias()
			
			int id = 0;
			if (TomaMaterias.createCriteria().get {projections {max "id"}} as Integer != null){
			   id = TomaMaterias.createCriteria().get {projections {max "id"}} as int
			}
			id++
			materia.id = id
			
            materia.alumno_id = alumno.id
            materia.grupo_id = grupo.id
            materia.estado = params.estado
            materia.semestre = alumno.semestre
            materia.save flush:true
        }

        grupo.inscritos = grupo.inscritos + 1
        grupo.save flush:true

        redirect action: 'show', id: grupo.id
    }

    def upload(){

    }
    
    def report(){
        String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
    }
	
	def inscritos(){
		String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
	}
	
	def listas(){
		String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
	}
	
	@Transactional
    def process() {
        //Recuperando el archivo
        def file = request.getFile('file')

        if (file.getInputStream() == null){
            flash.message = message(code: 'default.empty.file.message')
            redirect action:"upload", method:"GET"
        }

        //Borrando datos anteriores
        Grupos.findAll().each { it.delete(flush:true, failOnError:true) }
        //Abriendo el archivo de excel
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);

        // skip first row (row 0) by starting from 1
        int id = 0;
        for (int row = ROW_START; row < sheet.getRows(); row++) {
            if (id == 0){
                if (Alumnos.createCriteria().get {projections {max "id"}} as Integer != null){
                   id = Alumnos.createCriteria().get {projections {max "id"}} as int
                }
            }
            id++

            String semestre = sheet.getCell(COLUMN_SEMESTRE, row).getContents()
            String nombre = sheet.getCell(COLUMN_NOMBRE, row).getContents()
            String materia_id = sheet.getCell(COLUMN_MATERIA_ID, row).getContents()
            //Buscar el id de la materia
            Materias materia = Materias.find("FROM Materias WHERE upper(nombre)='"+materia_id.toUpperCase()+"'");
            if (materia){
                materia_id = materia.id
            }
            String docente_id = sheet.getCell(COLUMN_DOCENTE_ID, row).getContents()
            //Buscar el id del docente
            Docentes docente = Docentes.find("FROM Docentes WHERE upper(nombre)='"+docente_id.toUpperCase()+"'");
            if (docente){
                docente_id = docente.id
            }
            String horas = sheet.getCell(COLUMN_HORAS, row).getContents()
            String modalidad = sheet.getCell(COLUMN_MODALIDAD, row).getContents()
            switch (modalidad.toUpperCase()) {
                case "PRES":
                    modalidad = "Presencial";
                    break;
                case "VIR":
                    modalidad = "Virtual"
                    break;
                case "HIB":
                    modalidad = "Híbrida"
                    break;
            }
            String cupo = sheet.getCell(COLUMN_CUPO, row).getContents()

            //LabelCell firstName = sheet.getCell(COLUMN_FIRST_NAME, row)
            //DateCell dateOfBirth = sheet.getCell(COLUMN_DATE_OF_BIRTH, row)
            //NumberCell numberOfChildren = sheet.getCell(COLUMN_NUMBER_OF_CHILDREN, row)

            Grupos grupo = new Grupos()
            grupo.id = id
            grupo.semestre = semestre
            grupo.nombre = nombre
            grupo.materia_id = (materia_id?.isInteger()?materia_id as int:0)
            grupo.docente_id = (docente_id?.isInteger()?docente_id as int:0)
            grupo.horas = (horas?.isInteger()?horas as int:0)
            grupo.cupo = (cupo?.isInteger()?cupo as int:0)
            grupo.inscritos = 0
            grupo.modalidad = modalidad

            grupo.save flush:true
        }
        redirect action: "index"
    }

	@Transactional
    def deleteall(){
        //Borrando datos anteriores
        Grupos.findAll().each { it.delete(flush:true, failOnError:true) }
        redirect action: "index"
    }

	def backup(){
        xls_file('grupos')
    }
    
    private void xls_file(String file_name){
        response.setContentType('application/vnd.ms-excel')
        response.setHeader('Content-Disposition', 'Attachment;Filename="'+file_name+'.xls"')
        WritableWorkbook workbook = Workbook.createWorkbook(response.outputStream)
        WritableSheet sheet1 = workbook.createSheet("Datos", 0)
        def grupos_list = Grupos.list(sort: "id", order: "asc").toArray()
        sheet1.addCell(new Label(0,0, "ID"))
        sheet1.addCell(new Label(1,0, "Semestre"))
        sheet1.addCell(new Label(2,0, "Grupo"))
        sheet1.addCell(new Label(3,0, "Unidad curricular"))
        sheet1.addCell(new Label(4,0, "Docente"))
        sheet1.addCell(new Label(5,0, "Modalidad"))
        sheet1.addCell(new Label(6,0, "Horas"))
        sheet1.addCell(new Label(7,0, "Cupo"))
        sheet1.addCell(new Label(8,0, "Inscritos"))
        int row = 0;
        for(item in grupos_list) {
            row++;
            sheet1.addCell(new Label(0,row, item.id.toString()))
            sheet1.addCell(new Label(1,row, item.semestre))
            sheet1.addCell(new Label(2,row, item.nombre))
            sheet1.addCell(new Label(3,row, (item.getMateria())?item.getMateria().nombre:"No identificada" ))
            sheet1.addCell(new Label(4,row, (item.getDocente())?item.getDocente().nombre:"No identificado" ))
            sheet1.addCell(new Label(5,row, item.modalidad))
            sheet1.addCell(new Label(6,row, item.horas.toString()))
            sheet1.addCell(new Label(7,row, item.cupo.toString()))
            sheet1.addCell(new Label(8,row, item.inscritos.toString()))
            //WritableSheet sheet2 = workbook.createSheet("Courses", 1)
            //sheet2.addCell(new Label(0,0, "Course Name"))
            //sheet2.addCell(new Label(1,0, "Number of units"))
            //sheet2.addCell(new Label(0,1, "Algebra"))
            //sheet2.addCell(new Label(1,1, "3"))
            //sheet2.addCell(new Label(0,2, "English Grammar"))
            //sheet2.addCell(new Label(1,2, "5"))
        }
        workbook.write();
        workbook.close();
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupos.label', default: 'Grupo'), params.nombre])
                redirect action: "index"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
