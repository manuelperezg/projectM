package mes_web

import grails.rest.*
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
@Resource(uri='/WS_RoleLG', formats=['json', 'xml'])
class WS_RoleLG implements Serializable {

	private static final long serialVersionUID = 1

	String	nombre
	String	authority
	Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

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
	}

	static mapping = {
		sort nombre: "asc"
		table 'ROLELG'
		cache true
		id_Autor column:'id_autor'
	}

	String toString() {
        return nombre
    }
}
