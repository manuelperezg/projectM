package mes_web

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.rest.*

@Resource(formats=['json', 'xml'])
@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class WS_RoleLG implements Serializable {

	private static final long serialVersionUID = 1

	String	nombre
	String	authority
	Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

    static hasMany = [lineas: Linea]

	WS_RoleLG(String nombre, String authority) {
		this()
		this.nombre = nombre
		this.authority = authority
	}

	static constraints = {
		authority		(blank: false, unique: true)
		nombre			(nullable:false)
		dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        lineas			(nullable:true, display: false, widget:'hiddenField')
	}

	static mapping = {
		cache true
		id_Autor column:'id_autor'
	}

	String toString() {
        return nombre
    }
}
