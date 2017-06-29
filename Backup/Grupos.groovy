package saiuv

class Grupos {
    //Atributos de la tabla
    int id
    String nombre
    String semestre
    int materia_id
    int docente_id
    String modalidad
    int horas
    int cupo
    int inscritos = 0

    //Validaciones
    static constraints = {
        id(nullable:false)
        nombre(nullable:false)
        semestre(nullable:false)
        materia_id(nullable:false)
        docente_id(nullable:false)
        modalidad(inList:["Presencial", "Virtual", "Híbrida"])
        horas(nullable:false)
        cupo(nullable:false)
        inscritos(nullable:false)
    }

    //Vinculación con la tabla de la BD
    static mapping = {
        table 'GRUPOS'
        version false
        //columns {
            id column: "ID", generator: "assigned"
            nombre column:'NOMBRE'
            semestre column:'SEMESTRE'
            materia_id column:'MATERIA_ID'
            docente_id column:'DOCENTE_ID'
            modalidad column:'MODALIDAD'
            horas column:'HORAS'
            cupo column:'CUPO'
            inscritos column:'INSCRITOS'
        //}
    }

    Materias getMateria() {
		Materias.findById(materia_id)
	}

    Docentes getDocente() {
		Docentes.findById(docente_id)
	}

	int isInscritoMateria(String username, int grupo_id){
	    Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'")
	    if (TomaMaterias.find("FROM TomaMaterias as tm WHERE tm.semestre='" + alumno.semestre + "' AND tm.alumno_id=" + alumno.id.toString() + " AND tm.grupo_id="+grupo_id.toString())){
			return 1;
	    }else{
			return 0;
	    }
	}
	
	int isInscritoTutoria(String username, int grupo_id){
	    Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'")
	    if (TomaTutorias.find("FROM TomaTutorias as tm WHERE tm.semestre='" + alumno.semestre + "' AND tm.alumno_id=" + alumno.id.toString() + " AND tm.grupo_id="+grupo_id.toString())){
			return 1;
	    }else{
			return 0;
	    }
	}
}
