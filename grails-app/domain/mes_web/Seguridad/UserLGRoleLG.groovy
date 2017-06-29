package mes_web

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserLGRoleLG implements Serializable {

	private static final long serialVersionUID = 1

	UserLG userLG
	RoleLG roleLG

	UserLGRoleLG(UserLG u, RoleLG r) {
		this()
		userLG = u
		roleLG = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof UserLGRoleLG)) {
			return false
		}

		other.userLG?.id == userLG?.id && other.roleLG?.id == roleLG?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (userLG) builder.append(userLG.id)
		if (roleLG) builder.append(roleLG.id)
		builder.toHashCode()
	}

	static UserLGRoleLG get(long userLGId, long roleLGId) {
		criteriaFor(userLGId, roleLGId).get()
	}

	static boolean exists(long userLGId, long roleLGId) {
		criteriaFor(userLGId, roleLGId).count()
	}

	private static DetachedCriteria criteriaFor(long userLGId, long roleLGId) {
		UserLGRoleLG.where {
			userLG == UserLG.load(userLGId) &&
			roleLG == RoleLG.load(roleLGId)
		}
	}

	static UserLGRoleLG create(UserLG userLG, RoleLG roleLG, boolean flush = false) {
		def instance = new UserLGRoleLG(userLG: userLG, roleLG: roleLG)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(UserLG u, RoleLG r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserLGRoleLG.where { userLG == u && roleLG == r }.deleteAll()

		if (flush) { UserLGRoleLG.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(UserLG u, boolean flush = false) {
		if (u == null) return

		UserLGRoleLG.where { userLG == u }.deleteAll()

		if (flush) { UserLGRoleLG.withSession { it.flush() } }
	}

	static void removeAll(RoleLG r, boolean flush = false) {
		if (r == null) return

		UserLGRoleLG.where { roleLG == r }.deleteAll()

		if (flush) { UserLGRoleLG.withSession { it.flush() } }
	}

	static constraints = {
		roleLG validator: { RoleLG r, UserLGRoleLG ur ->
			if (ur.userLG == null || ur.userLG.id == null) return
			boolean existing = false
			UserLGRoleLG.withNewSession {
				existing = UserLGRoleLG.exists(ur.userLG.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		table 'USERLG_ROLELG'
        version false
		id composite: ['userLG', 'roleLG']
	}
}
