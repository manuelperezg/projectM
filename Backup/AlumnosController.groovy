package saiuv

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import jxl.DateCell
import jxl.LabelCell
import jxl.NumberCell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet 
import jxl.write.WritableWorkbook
//import org.weykhans.GeneralServices

@Transactional(readOnly = true)
class AlumnosController {
	//Identificación de la sesion del usuario
    def springSecurityService;
    
    private final static int ROW_START = 1
    private final static int COLUMN_MATRICULA = 0
    private final static int COLUMN_AP_PATERNO = 1
    private final static int COLUMN_AP_MATERNO = 2
    private final static int COLUMN_NOMBRE = 3
    private final static int COLUMN_STATUS = 6
    private final static int COLUMN_PLANEST = 8
    private final static int COLUMN_GENERACION = 9
    private final static int COLUMN_SEMESTRE = 11
    private final static int COLUMN_FECHA_NAC = 12
    private final static int COLUMN_CURP = 13
    private final static int COLUMN_SEXO = 14
    private final static int COLUMN_ESTADO = 15
    private final static int COLUMN_MUNICIPIO = 16
    private final static int COLUMN_CP = 17
    private final static int COLUMN_GRUPO_ID = -1
    private final static int COLUMN_GRUPO_NOMBRE = 10
    private final static int COLUMN_UNIDAD_ID = 5
    private final static int COLUMN_UNIDAD_NOMBRE = 19
    private final static int COLUMN_CARRERA_ID = 4
    private final static int COLUMN_CARRERA_NOMBRE = 7
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		//Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("alumnos_search_offset")){
			params.offset = session.getAttribute("alumnos_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("alumnos_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("alumnos_search_rows")){
			params.max = session.getAttribute("alumnos_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("alumnos_search_word")) params.offset = 0;
        } else if (session.getAttribute("alumnos_search_word")){
			params.search_word = session.getAttribute("alumnos_search_word");
        }
        
        def itemsList = Alumnos.createCriteria().list (params) {
            if ( params.search_word ) {
				or {
					ilike("matricula", "%${params.search_word}%")
					ilike("ap_paterno", "%${params.search_word}%")
					ilike("ap_materno", "%${params.search_word}%")
					ilike("nombre", "%${params.search_word}%")
					ilike("status", "%${params.search_word}%")
					ilike("planest", "%${params.search_word}%")
					ilike("generacion", "%${params.search_word}%")
				}
            }
        }
        
        session.setAttribute("alumnos_search_rows", params.search_rows);
        session.setAttribute("alumnos_search_word", params.search_word);
        session.setAttribute("alumnos_search_offset", params.offset);
        
        respond itemsList, model:[alumnosCount: itemsList.totalCount]
    }
    
    def tutorias(Integer max) {
		def principal = springSecurityService.principal;
        String username = principal.username;
        
        if (username.indexOf("anonymous") >= 0){
			redirect controller: 'login', action: 'auth', params: params
			return
        }
        
        int userid = principal.id;
		
		Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'");
        int tutorias_tomadas = 0;
        if (TomaTutorias.find("FROM TomaTutorias WHERE semestre=" + alumno.semestre + " and alumno_id=" + alumno.id.toString())){
			//println "FROM TomaTutorias WHERE semestre='" + alumno.semestre + "' and alumno_id=" + alumno.id.toString()
			TomaTutorias.findAll("FROM TomaTutorias WHERE semestre='" + alumno.semestre + "' and alumno_id=" + alumno.id.toString()).each{
				tutorias_tomadas++;
			}
        }
        
        session.setAttribute("username", username) 
        session.setAttribute("materias_tomadas", tutorias_tomadas)
        session.setAttribute("materias_libres", (1-tutorias_tomadas))
        
        params.max = Math.min(max ?: 100, 1000)
		
		String query = "FROM Grupos as g WHERE g.materia_id IN (SELECT m.id FROM Materias as m WHERE m.clave LIKE 'TUT%')"
		//println query 		
		def listado = Grupos.findAll(query)
		
		//println listado.size()
        respond listado.asList(), model:[gruposCount: listado.size()]
    }
    
    def materias(Integer max) {
		def principal = springSecurityService.principal;
        String username = principal.username;
        
        if (username.indexOf("anonymous") >= 0){
			redirect controller: 'login', action: 'auth', params: params
			return
        }
        
        int userid = principal.id;
		
        Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'");
        int materias_tomadas = 0;
        if (TomaMaterias.find("FROM TomaMaterias WHERE semestre=" + alumno.semestre + " and alumno_id=" + alumno.id.toString())){
			TomaMaterias.findAll("FROM TomaMaterias WHERE semestre='" + alumno.semestre + "' and alumno_id=" + alumno.id.toString()).each{
				materias_tomadas++;
			}
        }
        
        session.setAttribute("username", username) 
        session.setAttribute("materias_tomadas", materias_tomadas)
        session.setAttribute("materias_libres", (8-materias_tomadas))
        
        params.max = Math.min(max ?: 100, 1000)
		String ids = "0";
		switch (alumno.carrera_id) {
			case 1898: ids = "SELECT m.id FROM Materias as m WHERE m.prog_CE != ' '"; 
					break;
			case 1899: ids = "SELECT m.id FROM Materias as m WHERE m.prog_CTE != ' '";
					break;
			case 1900: ids = "SELECT m.id FROM Materias as m WHERE m.prog_EF != ' '";
					break;
			case 1901: ids = "SELECT m.id FROM Materias as m WHERE m.prog_D != ' '";
					break;
			case 1917: ids = "SELECT m.id FROM Materias as m WHERE m.prog_EI != ' '";
					break;
			case 1918: ids = "SELECT m.id FROM Materias as m WHERE m.prog_EFR != ' '";
					break;
		}
		
		String query = "FROM Grupos as g WHERE g.materia_id IN ("+ids+")"
		//println query 		
		def listado = Grupos.findAll(query)
		
		//println listado.size()
        respond listado.asList(), model:[gruposCount: listado.size()]
        
    }
    
    @Transactional
    def confirmarTutorias() {
		def principal = springSecurityService.principal;
        String username = principal.username;
        
        if (username.indexOf("anonymous") >= 0){
			redirect controller: 'login', action: 'auth', params: params
			return
        }
        
        int userid = principal.id;
		
        String errorMessage = "";
        //Recuperando al alummno
        Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'");
        //ID de los registros TOMATUTORIAS
        int id = 0;
        if (TomaTutorias.createCriteria().get {projections {max "id"}} as Integer != null){
           id = TomaTutorias.createCriteria().get {projections {max "id"}} as int
        }
        id++
        int count = 0;
        while (true){
            if (params["grupo_"+count.toString()]){
                //Recuperando el grupo que eligio el alumno
                String grupo_id = params["grupo_"+count.toString()].toString();
                if (params["opc_"+count.toString()].toString().length() > 0 && params["opc_"+count.toString()].toString() != "null"){
					Grupos.withNewTransaction { TransactionStatus status ->
						
						Grupos grupo = Grupos.lock(Integer.parseInt(grupo_id))
						
						//println "Inscritos:" + grupo.inscritos + "cupo:" + grupo.cupo
						
						if (grupo.inscritos < grupo.cupo){
							grupo.inscritos = grupo.inscritos+1
							
							
							//println ">>"+grupo_id
							//println ">>"+params["opc_"+count.toString()].toString()+"<"
							TomaTutorias tomatutorias = new TomaTutorias()
							tomatutorias.id = id
							tomatutorias.alumno_id = alumno.id
							tomatutorias.grupo_id = Integer.parseInt(grupo_id)
							tomatutorias.estado = params["opc_"+count.toString()].toString()
							tomatutorias.semestre = alumno.semestre
							tomatutorias.save flush:true
							
							
							
							
						}else{
							if (errorMessage == ""){
								errorMessage = " - " + grupo.getMateria().nombre;
							}else{
								errorMessage += ", - " + grupo.getMateria().nombre;
							}
						}
						grupo.save()
					}
                    
                    id++
                }
            }else{
                break;
            }
            count++;
        }
        
        if (errorMessage != ""){
			errorMessage = "NO hay cupo en las tutorías:"+errorMessage
			flash.message = errorMessage
        }
        
        redirect action:"tutorias"
    }
    
    @Transactional
    def confirmarMaterias() {
		def principal = springSecurityService.principal;
        String username = principal.username;
        
        if (username.indexOf("anonymous") >= 0){
			redirect controller: 'login', action: 'auth', params: params
			return
        }
        
        int userid = principal.id;
		
        String errorMessage = "";
        //Recuperando al alummno
        Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'");
        //ID de los registros TOMAMATERIAS
        int id = 0;
        if (TomaMaterias.createCriteria().get {projections {max "id"}} as Integer != null){
           id = TomaMaterias.createCriteria().get {projections {max "id"}} as int
        }
        id++
        int count = 0;
        while (true){
            if (params["grupo_"+count.toString()]){
                //Recuperando el grupo que eligio el alumno
                String grupo_id = params["grupo_"+count.toString()].toString();
                if (params["opc_"+count.toString()].toString().length() > 0 && params["opc_"+count.toString()].toString() != "null"){
					Grupos.withNewTransaction { TransactionStatus status ->
						
						Grupos grupo = Grupos.lock(Integer.parseInt(grupo_id))
						
						//println "Inscritos:" + grupo.inscritos + "cupo:" + grupo.cupo
						
						if (grupo.inscritos < grupo.cupo){
							grupo.inscritos = grupo.inscritos+1
							
							
							//println ">>"+grupo_id
							//println ">>"+params["opc_"+count.toString()].toString()+"<"
							TomaMaterias tomamateria = new TomaMaterias()
							tomamateria.id = id
							tomamateria.alumno_id = alumno.id
							tomamateria.grupo_id = Integer.parseInt(grupo_id)
							tomamateria.estado = params["opc_"+count.toString()].toString()
							tomamateria.semestre = alumno.semestre
							tomamateria.save flush:true
							
							
						}else{
							if (errorMessage == ""){
								errorMessage = " - " + grupo.getMateria().nombre;
							}else{
								errorMessage += ", - " + grupo.getMateria().nombre;
							}
						}
						grupo.save()
					}
                    
                    id++
                }
            }else{
                break;
            }
            count++;
        }
        
        if (errorMessage != ""){
			errorMessage = "NO hay cupo en las unidades curriculares:"+errorMessage
			flash.message = errorMessage
        }
        
        redirect action:"materias"
    }
    
    def show(Alumnos alumnos) {
		respond alumnos
    }

    def create() {
		respond new Alumnos(params), model:[unidades:Unidades.list(), carreras:Carreras.list()]
    }

    @Transactional
    def save(Alumnos alumnos) {
		if (alumnos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (alumnos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond alumnos.errors, view:'create'
            return
        }
        int id = 0;
        if (Alumnos.createCriteria().get {projections {max "id"}} as Integer != null){
           id = Alumnos.createCriteria().get {projections {max "id"}} as int
        }
        id++
        alumnos.id = id
        
        alumnos.save flush:true
        
        if (alumnos.matricula.length() > 0){
            String contrasena_tmp = "UAEM_"+alumnos.matricula
			if (alumnos.curp.length() > 0){
                contrasena_tmp = alumnos.curp
            }
            
            def studentRole = RoleLG.find("FROM RoleLG as r WHERE r.authority='ROLE_STUDENT'");
            def user_tmp = new UserLG(alumnos.matricula, contrasena_tmp).save()
			UserLGRoleLG.create user_tmp, studentRole, true
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alumnos.label', default: 'Alumno'), alumnos.matricula])
                redirect alumnos
            }
            '*' { respond alumnos, [status: CREATED] }
        }
    }

    def edit(Alumnos alumnos) {
		respond alumnos, model:[unidades:Unidades.list(), carreras:Carreras.list()]
    }

    @Transactional
    def update(Alumnos alumnos) {
		if (alumnos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (alumnos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond alumnos.errors, view:'edit'
            return
        }

        alumnos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'alumnos.label', default: 'Alumno'), alumnos.matricula])
                redirect alumnos
            }
            '*'{ respond alumnos, [status: OK] }
        }
    }

    @Transactional
    def delete(Alumnos alumnos) {
		if (alumnos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        UserLG.findAll("from UserLG as u where u.username='"+alumnos.matricula+"'").each { 
			UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.userLG.id="+it.id.toString())
			it.delete(flush:true, failOnError:true);
		}
        alumnos.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'alumnos.label', default: 'Alumno'), alumnos.matricula])
                redirect action: "index"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    def upload(){
		
    }
    
    def report() {
		String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
    }
    
    def recursantes() {
		String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
    }
    
    def acuse(String username) {
		String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
		if (username){
			
		}else{
			def principal = springSecurityService.principal;
			username = principal.username;
        }
        println ">"+username+"<";
        Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'");
        String id_alumno = alumno.id;
		//println alumno.id;
        session.setAttribute("id_alumno", id_alumno)
		
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
        Alumnos.findAll().each { it.delete(flush:true, failOnError:true) }
        
        //UserLG.findAll("FROM UserLG as u, RoleLG as r, UserLGRoleLG as ur WHERE r.authority='ROLE_STUDENT' AND ur.roleLG.id=r.id AND ur.userLG.id=u.id").each { it.delete(flush:true, failOnError:true) }
        //UserLG.executeUpdate("DELETE UserLG as u WHERE u.id in (SELECT ur.userLG.id FROM RoleLG as r, UserLGRoleLG as ur WHERE r.authority='ROLE_STUDENT' AND ur.roleLG.id=r.id)")
        //Borrando datos anteriores
        Alumnos.findAll().each { it.delete(flush:true, failOnError:true) }
        UserLG.findAll("FROM UserLG as u WHERE u.id in (SELECT ur.userLG.id FROM RoleLG as r, UserLGRoleLG as ur WHERE r.authority='ROLE_STUDENT' AND ur.roleLG.id=r.id)").each {
			//Busca a los hijos para eliminarlos!!
			//UserLGRoleLG.find("FROM UserLGRoleLG as ur WHERE ur.userLG.id="+it.id.toString()).each {it.delete(flush:true, failOnError:true)}
			UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.userLG.id="+it.id.toString())
			it.delete(flush:true, failOnError:true)
		}
        
        //Abriendo el archivo de excel
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        
        def studentRole = RoleLG.find("FROM RoleLG as r WHERE r.authority='ROLE_STUDENT'");
        
        // skip first row (row 0) by starting from 1
        int id = 0;
        for (int row = ROW_START; row < sheet.getRows(); row++) {
            if (id == 0){
                if (Alumnos.createCriteria().get {projections {max "id"}} as Integer != null){
                   id = Alumnos.createCriteria().get {projections {max "id"}} as int
                }
            }
            id++
            String matricula = sheet.getCell(COLUMN_MATRICULA, row).getContents()
            String ap_paterno = sheet.getCell(COLUMN_AP_PATERNO, row).getContents()
            String ap_materno = sheet.getCell(COLUMN_AP_MATERNO, row).getContents()
            String nombre = sheet.getCell(COLUMN_NOMBRE, row).getContents()
            String status = sheet.getCell(COLUMN_STATUS, row).getContents()
            status = toTitleCase(status)
            String planest = sheet.getCell(COLUMN_PLANEST, row).getContents()
            String generacion = sheet.getCell(COLUMN_GENERACION, row).getContents()
            String semestre = sheet.getCell(COLUMN_SEMESTRE, row).getContents()
            String fecha_nac = sheet.getCell(COLUMN_FECHA_NAC, row).getContents()
            String curp = sheet.getCell(COLUMN_CURP, row).getContents()
            String sexo = sheet.getCell(COLUMN_SEXO, row).getContents()
            String estado = sheet.getCell(COLUMN_ESTADO, row).getContents()
            estado = toTitleCase(estado)
            String municipio = sheet.getCell(COLUMN_MUNICIPIO, row).getContents()
            municipio = toTitleCase(municipio)
            String cp = sheet.getCell(COLUMN_CP, row).getContents()
            String grupo_id = "-1"
            String grupo_nombre = sheet.getCell(COLUMN_GRUPO_NOMBRE, row).getContents()
            //Buscar el id del grupo usado el semestre y nombre
            
            String unidad_id = sheet.getCell(COLUMN_UNIDAD_ID, row).getContents()
            String unidad_nombre = sheet.getCell(COLUMN_UNIDAD_NOMBRE, row).getContents()
            String carrera_id = sheet.getCell(COLUMN_CARRERA_ID, row).getContents()
            String carrera_nombre = sheet.getCell(COLUMN_CARRERA_NOMBRE, row).getContents()
            
            //LabelCell firstName = sheet.getCell(COLUMN_FIRST_NAME, row)
            //DateCell dateOfBirth = sheet.getCell(COLUMN_DATE_OF_BIRTH, row)
            //NumberCell numberOfChildren = sheet.getCell(COLUMN_NUMBER_OF_CHILDREN, row)
            //println "id:"+id+", matricula:"+matricula+", ap_paterno:"+ap_paterno+", ap_materno:"+ap_materno;
            //println "nombre:"+nombre+", status:"+status+", planest:"+planest+", generacion:"+generacion;
            //println "semestre:"+semestre+", fecha_nac:"+fecha_nac+", curp:"+curp+", sexo:"+sexo;
            //println "estado:"+estado+", municipio:"+municipio+", cp:"+cp+", sexo:"+sexo;
            //println "grupo_id:"+grupo_id+", unidad_id:"+unidad_id+", carrera_id:"+carrera_id;
            //Creando la carrera
            Carreras carrera = Carreras.findById(carrera_id.toInteger());
            if (carrera){
                //Carrera registrada
            }else{
                Carreras carrera_tmp = new Carreras()
                carrera_tmp.id = carrera_id.toInteger()
                carrera_tmp.nombre = carrera_nombre
                carrera_tmp.save flush:true
            }
            //Creando la unidad
            Unidades unidad = Unidades.findById(unidad_id.toInteger());
            if (unidad){
                //Carrera registrada
            }else{
                Unidades unidad_tmp = new Unidades()
                unidad_tmp.id = unidad_id.toInteger()
                unidad_tmp.nombre = unidad_nombre
                unidad_tmp.save flush:true
            }
            //Creando el usuario relacionado al alumno
            if (matricula.length() > 0){
                String contrasena_tmp = "UAEM_"+matricula
				if (curp.length() > 0){
					contrasena_tmp = curp
				}
				
				UserLG user_tmp = new UserLG(matricula, contrasena_tmp).save(flush:true)
				
				UserLGRoleLG.create user_tmp, studentRole, true
            }
            //Creando al alumno
            Alumnos alumno = new Alumnos()
            alumno.id = id
            alumno.matricula = matricula
            alumno.ap_paterno = ap_paterno
            alumno.ap_materno = ap_materno
            alumno.nombre = nombre
            alumno.status = status
            alumno.planest = planest
            alumno.generacion = generacion
            alumno.semestre = semestre
            alumno.fecha_nac = fecha_nac
            alumno.curp = curp
            alumno.sexo = sexo
            alumno.estado = estado
            alumno.municipio = municipio
            alumno.cp = cp
            alumno.unidad_id = (unidad_id?.isInteger()?unidad_id as int:0)
            alumno.carrera_id = (carrera_id?.isInteger()?carrera_id as int:0)
                        
            alumno.save flush:true
        }
        redirect action: "index"
    }
    
    @Transactional
    def deleteall(){
		//Borrando datos anteriores
        Alumnos.findAll().each { it.delete(flush:true, failOnError:true) }
        UserLG.findAll("FROM UserLG as u WHERE u.id in (SELECT ur.userLG.id FROM RoleLG as r, UserLGRoleLG as ur WHERE r.authority='ROLE_STUDENT' AND ur.roleLG.id=r.id)").each {
			//Busca a los hijos para eliminarlos!!
			//UserLGRoleLG.find("FROM UserLGRoleLG as ur WHERE ur.userLG.id="+it.id.toString()).each {it.delete(flush:true, failOnError:true)}
			UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.userLG.id="+it.id.toString())
			it.delete(flush:true, failOnError:true)
		}
        redirect action: "index"
    }
    
    def backup(){
		xls_file('alumnos')
    }
    
    private void xls_file(String file_name){
        response.setContentType('application/vnd.ms-excel')
        response.setHeader('Content-Disposition', 'Attachment;Filename="'+file_name+'.xls"')
        WritableWorkbook workbook = Workbook.createWorkbook(response.outputStream)
        WritableSheet sheet1 = workbook.createSheet("Datos", 0)
        def alumnos_list = Alumnos.list(sort: "id", order: "asc").toArray()
        sheet1.addCell(new Label(0,0, "ID"))
        sheet1.addCell(new Label(1,0, "Matrícula"))
        sheet1.addCell(new Label(2,0, "Apellido paterno"))
        sheet1.addCell(new Label(3,0, "Apellido materno"))
        sheet1.addCell(new Label(4,0, "Nombre"))
        sheet1.addCell(new Label(5,0, "Carrera ID"))
        sheet1.addCell(new Label(6,0, "Unidad ID"))
        sheet1.addCell(new Label(7,0, "Carrera"))
        sheet1.addCell(new Label(8,0, "Planes estudio"))
        sheet1.addCell(new Label(9,0, "Generacion"))
        sheet1.addCell(new Label(10,0, "Semestre"))
        sheet1.addCell(new Label(11,0, "F. Nacimiento"))
        sheet1.addCell(new Label(12,0, "CURP"))
        sheet1.addCell(new Label(13,0, "Sexo"))
        sheet1.addCell(new Label(14,0, "Estado"))
        sheet1.addCell(new Label(15,0, "Municipio"))
        sheet1.addCell(new Label(16,0, "CP"))
        sheet1.addCell(new Label(17,0, "Unidad"))
        int row = 0;
        Alumnos.findAll("FROM Alumnos ORDER BY id").each{
			row++;
            sheet1.addCell(new Label(0,row, it.id.toString()))
            sheet1.addCell(new Label(1,row, it.matricula))
            sheet1.addCell(new Label(2,row, it.ap_paterno))
            sheet1.addCell(new Label(3,row, it.ap_materno))
            sheet1.addCell(new Label(4,row, it.nombre))
            sheet1.addCell(new Label(5,row, it.carrera_id.toString()))
            sheet1.addCell(new Label(6,row, it.unidad_id.toString()))
            sheet1.addCell(new Label(7,row, it.getCarrera().nombre))
            sheet1.addCell(new Label(8,row, it.planest))
            sheet1.addCell(new Label(9,row, it.generacion))
            sheet1.addCell(new Label(10,row, it.semestre))
            sheet1.addCell(new Label(11,row, it.fecha_nac))
            sheet1.addCell(new Label(12,row, it.curp))
            sheet1.addCell(new Label(13,row, it.sexo))
            sheet1.addCell(new Label(14,row, it.estado))
            sheet1.addCell(new Label(15,row, it.municipio))
            sheet1.addCell(new Label(16,row, it.cp))
            //sheet1.addCell(new Label(17,row, it.getUnidad().nombre))
            
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alumnos.label', default: 'Alumno'), params.matricula])
                redirect action: "index"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    private String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;
        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true; //true = Hola Mundo Nuevo
            } else if (nextTitleCase) {
                c = Character.toUpperCase(c);
                nextTitleCase = false;
            }else{
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }
    
    private void refreshSession(){
		def principal = springSecurityService.principal;
        String username = principal.username;
        
        if (username.indexOf("anonymous") >= 0){
			redirect controller: 'login', action: 'auth', params: params
			return
        }
        
        int userid = principal.id;
		new GeneralServices().updateSession(userid);
    }
}
