package mes_web

class Estandar {

    String  id
    String  nombre
    String  tipo_archivo
    String  archivo
    Date    dateCreated
    Date    lastUpdated
    UserLG  id_Autor
    
    static  belongsTo = [verificacion:Verificacion]
	
	def beforeUpdate = {
		lastUpdated = new Date()
    }
	
    static constraints = {
		id				(nullable:true)
		verificacion    (nullable: false)
        nombre          (nullable: false)
        tipo_archivo    (nullable: false, inList: ["Archivo de video", "Archivo PDF", "Archivo de imagen"])
        archivo         (nullable: true, widget:'file')
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
    }

    static mapping = {
        table 'ESTANDARES'
        version false
        id column: "ID", generator : 'uuid'
        id_Autor column:'id_autor'
    }

    String toString() {
        return nombre
    }
}
