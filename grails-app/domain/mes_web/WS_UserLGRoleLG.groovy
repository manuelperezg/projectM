package mes_web

import grails.rest.*
import grails.gorm.DetachedCriteria
import groovy.transform.ToString

@ToString(cache=true, includeNames=true, includePackage=false)
@Resource(uri='/WS_UserLGRoleLG', formats=['json', 'xml'])
class WS_UserLGRoleLG implements Serializable {

	private static final long serialVersionUID = 1

	UserLG userLG
	RoleLG roleLG

	WS_UserLGRoleLG(UserLG u, RoleLG r) {
		this()
		userLG = u
		roleLG = r
	}

	static mapping = {
		table 'USERLG_ROLELG'
        version false
		id composite: ['userLG', 'roleLG']
	}
}
