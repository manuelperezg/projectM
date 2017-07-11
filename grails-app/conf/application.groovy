environments {
    development {
        uploadFolder = "C:/Users/1200/Documents/Grails/mes_web_v1.39/grails-app/assets/images/"
    }
    test {
        uploadFolder = "c:/temp/upload/"
    }
    production {
        uploadFolder = "/opt/bitnami/apache-tomcat/webapps/portal/assets/"
    }
}

grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.springsecurity.userLookup.userDomainClassName = 'mes_web.UserLG'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'mes_web.UserLGRoleLG'
grails.plugin.springsecurity.authority.className = 'mes_web.RoleLG'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],

	[pattern: '/general/*',      access: ['permitAll']],

	[pattern: '/userLG/myuser',         access: ['ROLE_ADMINISTRADOR', 'ROLE_CONFIGURADOR', 'ROLE_OPERADOR', 'ROLE_EJECUTIVO']],
	[pattern: '/userLG/updateMyuser',   access: ['ROLE_ADMINISTRADOR', 'ROLE_CONFIGURADOR', 'ROLE_OPERADOR', 'ROLE_EJECUTIVO']],
	[pattern: '/userLG/*',       access: ['ROLE_ADMINISTRADOR']],
    [pattern: '/roleLG/*',       access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/userLGRoleLG/*', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/horario/*',      access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/logout/*',       access: ['permitAll']],
	
	[pattern: '/planta/*',     	 access: ['ROLE_ADMINISTRADOR']],	
	[pattern: '/planta/report',     	 access: ['ROLE_ADMINISTRADOR', 'ROLE_EJECUTIVO']],
	[pattern: '/linea/*',     	 access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/linea/report',     	 access: ['ROLE_ADMINISTRADOR', 'ROLE_EJECUTIVO']],
	[pattern: '/maquina/*',      access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/maquina/report',     	 access: ['ROLE_ADMINISTRADOR', 'ROLE_EJECUTIVO']],
	[pattern: '/tipoMantenimiento/*',   access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/tipoMantenimiento/report',     	 access: ['ROLE_ADMINISTRADOR', 'ROLE_EJECUTIVO']],
	[pattern: '/calendario/*',   access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/evento/*',       access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/verificacion/*', access: ['ROLE_ADMINISTRADOR']],
    [pattern: '/tipoVerificacion/*', access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/punto/*',        access: ['ROLE_ADMINISTRADOR']],
    [pattern: '/estandar/*',     access: ['ROLE_ADMINISTRADOR']],
    [pattern: '/subsistema/*',   access: ['ROLE_ADMINISTRADOR']],
    [pattern: '/subsistema/report',      access: ['ROLE_ADMINISTRADOR', 'ROLE_EJECUTIVO']],
    [pattern: '/pieza/*',        access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/material/*',     access: ['ROLE_ADMINISTRADOR']],
	[pattern: '/material/report',     	 access: ['ROLE_ADMINISTRADOR', 'ROLE_EJECUTIVO']],
    [pattern: '/categoria/*',    access: ['ROLE_ADMINISTRADOR']],

	[pattern: '/qrcode/*',       access: ['ROLE_ADMINISTRADOR']],
	
	//Api rest vistas
	[pattern: '/WS_Categoria/*',     access: ['permitAll']],
	[pattern: '/WS_Estandar/*',      access: ['permitAll']],
	[pattern: '/WS_Linea/*',         access: ['permitAll']],
	[pattern: '/WS_Maquina/*',       access: ['permitAll']],
	[pattern: '/WS_Material/*',      access: ['permitAll']],
	[pattern: '/WS_Pieza/*',         access: ['permitAll']],
	[pattern: '/WS_Planta/*',        access: ['permitAll']],
	[pattern: '/WS_Punto/*',         access: ['permitAll']],
	[pattern: '/WS_PuntoRespuesta/*',     access: ['permitAll']],
	[pattern: '/WS_Subsistema/*',    access: ['permitAll']],
	[pattern: '/WS_TipoMantenimiento/*',  access: ['permitAll']],
	[pattern: '/WS_TipoVerificacion/*',   access: ['permitAll']],
	[pattern: '/WS_Verificacion/*',   access: ['permitAll']],
	[pattern: '/WS_UserLG/*',   access: ['permitAll']],
	[pattern: '/WS_RoleLG/*',   access: ['permitAll']],
	[pattern: '/WS_UserLGRoleLG/*',   access: ['permitAll']],

	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]
