package saiuv

class Alumnos {
    //Atributos de la tabla
    int id
    String matricula
    String ap_paterno
    String ap_materno
    String nombre
    String status
    String planest
    String generacion
    String semestre
    String fecha_nac
    String curp
    String sexo
    String estado
    String municipio
    String cp
    int unidad_id
    int carrera_id
    
    //Validaciones
    static constraints = {
        id(nullable:false)
        matricula(nullable:false)
        ap_paterno(nullable:true)
        ap_materno(nullable:true)
        nombre(nullable:true)
        status(inList:["Inscrito", "Baja temporal", "No pagado", "Baja definitiva", "Cambio de carrera"])
        planest(nullable:false)
        generacion(nullable:false)
        semestre(nullable:false)
        fecha_nac(nullable:true)
        curp(nullable:false)
        sexo(inList:["M", "F"])
        estado(nullable:true)
        municipio(nullable:true)
        cp(nullable:true)
        unidad_id(nullable:false)
        carrera_id(nullable:false)
    }
    
    //Vinculación con la tabla de la BD
    static mapping = {
        table 'ALUMNOS'
        version false
        //columns {
            id column: "ID", generator: "assigned"
            matricula column:'MATRICULA'
            ap_paterno column:'AP_PATERNO'
            ap_materno column:'AP_MATERNO'
            nombre column:'NOMBRE'
            status column:'STATUS'
            planest column:'PLANEST'
            generacion column:'GENERACION'
            semestre column:'SEMESTRE'
            fecha_nac column:'FECHA_NAC'
            curp column:'CURP'
            sexo column:'SEXO'
            estado column:'ESTADO'
            municipio column:'MUNICIPIO'
            cp column:'CP'
            unidad_id column:'UNIDAD_ID'
            carrera_id column:'CARRERA_ID'
        //}
    }
    
    Unidades getUnidad() {
		Unidades.findById(unidad_id)
	}

    Carreras getCarrera() {
		Carreras.findById(carrera_id)
	}
}
