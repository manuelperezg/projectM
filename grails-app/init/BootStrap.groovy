package mes_web

class BootStrap {
    //dbm-generate-gorm-changelog changelog.groovy and then ran dbm-update
    def init = { servletContext ->
		boolean flag;
        try {
            flag = true;
            println RoleLG.count().toString()
        }
        catch (Exception e) {
            flag = false;
        }
		if (flag){
			if (RoleLG.count()<1){
				/******roles*******/
				//Los roles siempre deben comenzar con ROLE_
				def administrador = new RoleLG('Administrador','ROLE_ADMINISTRADOR').save(failOnError: true, flush: true)
				def configurador = new RoleLG('Programador','ROLE_CONFIGURADOR').save(failOnError: true, flush: true)
				def operador = new RoleLG('Operador','ROLE_OPERADOR').save(failOnError: true, flush: true)
				def ejecutivo = new RoleLG('Ejecutivo','ROLE_EJECUTIVO').save(failOnError: true, flush: true)
				def movil = new RoleLG('App Móvil','ROLE_MOVIL').save(failOnError: true, flush: true)
				
				/*******users********/
				def user1 = new UserLG('config', 'config', 'Juan Peréz Peréz', 'jperez@gmail.com').save(failOnError: true, flush: true)
				def user2 = new UserLG('admin', 'admin', 'Jorge Omar Ceyca Castro', 'jceyca@gmail.com').save(failOnError: true, flush: true)
				def user3 = new UserLG('user1', 'user1', 'Usuario 1 de Pruebas', 'user1@gmail.com').save(failOnError: true, flush: true)
				def user4 = new UserLG('user2', 'user2', 'Usuario 2 de Pruebas', 'user2@gmail.com').save(failOnError: true, flush: true)
				def user5 = new UserLG('movil', 'movil', 'Aplicación Móvil', 'movil@unilever-mes.com').save(failOnError: true, flush: true)
				
				/*********** asignamos los roles a los users********/
				if (user1) UserLGRoleLG.create user1, configurador, true
				if (user2) UserLGRoleLG.create user2, administrador, true
				if (user3) UserLGRoleLG.create user3, operador, true
				if (user4) UserLGRoleLG.create user4, ejecutivo, true
				if (user5) UserLGRoleLG.create user5, movil, true
				
                // Categorias basicas
                def autonomo = new TipoMantenimiento('Mantenimiento autónomo', 'Revisión de las condiciones de limpieza, inspección de funcionamiento y lubricación de la máquina').save(failOnError: true, flush: true)
                def profesional = new TipoMantenimiento('Mantenimiento profesional', 'Reparación correctivo de las máquinas o preventiva a corde al programa de mantenimientos general').save(failOnError: true, flush: true)

                def elementos_limpieza = new Categoria('Elementos de limpieza').save(failOnError: true, flush: true)
                def herramientas = new Categoria('Herramientas').save(failOnError: true, flush: true)
                def elementos_lubricacion = new Categoria('Elementos de lubricación').save(failOnError: true, flush: true)

                def lubricacion = new TipoVerificacion('Lubricación').save(failOnError: true, flush: true)
                def limpieza = new TipoVerificacion('Limpieza').save(failOnError: true, flush: true)
                def inspeccion = new TipoVerificacion('Inspección').save(failOnError: true, flush: true)
			}
		}
    }
    def destroy = {
    }
}
