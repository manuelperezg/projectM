package mes_web

import grails.rest.*
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
@Resource(uri='/WS_UserLG', formats=['json', 'xml'])
class WS_UserLG implements Serializable {
	private static final long serialVersionUID = 1
	
	transient springSecurityService
	
	String	username
	String	password
	String	password_Plain
	String	nombre
	String	email
	String	telefono_Movil
	String	archivo_Foto
	boolean	enabled = true
	boolean	accountExpired
	boolean	accountLocked
	boolean	passwordExpired
	Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor
	
	WS_UserLG(String username, String password, String nombre, String email) {
		this()
		this.username	= username
		this.password	= password
		password_Plain	= password
		this.nombre		= nombre
		this.email		= email
	}

	Set<RoleLG> getAuthorities() {
		UserLGRoleLG.findAllByUserLG(this)*.roleLG
	}

	def getRol() {
		if (UserLGRoleLG.findByUserLG(this))
			UserLGRoleLG.findByUserLG(this)*.roleLG.authority.first();
	}

	def getRolName() {
		if (UserLGRoleLG.findByUserLG(this))
			UserLGRoleLG.findByUserLG(this)*.roleLG.nombre.first();
	}

	def beforeInsert() {
		password_Plain = password
		encodePassword()
	}

	def beforeUpdate() {
		password_Plain = password
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	public void decodePassword(){
		password = password_Plain
	}

	static transients = ['springSecurityService']

	static constraints = {
		username		(blank: false, unique: true)
		password		(blank: false)
		password_Plain	(display:false, widget:'hiddenField')
		nombre			(blank: false, nullable:false)
		email			(blank: true, nullable:true)
		telefono_Movil	(blank: true, nullable:true)
		archivo_Foto	(blank: true, nullable:true)
		dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(blank: true, nullable:true, widget:'display')
	}

	static mapping = {
		sort nombre: "asc"
		table 'USERLG'
		version false
		password column: '`password`'
		
		id_Autor column:'id_autor'
	}
	
	String toString() {
        return nombre
    }
	
}
