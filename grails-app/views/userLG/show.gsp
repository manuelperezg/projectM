<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLG.label', default: 'Usuario')}" />
        <g:set var="entitiesName" value="${message(code: 'userLG.label', default: 'Usuarios')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script language="javascript">
            function jconfirm(pregunta,mensaje){
                swal({
                    title: pregunta,
                    text: mensaje,
                    type: "question",
                    showCancelButton: true,
                    confirmButtonColor: "#DA291C",
                    confirmButtonText: "Si, eliminar!",
                    cancelButtonText: "No",
                    confirmButtonClass: "btn btn-success",
                    cancelButtonClass: "btn btn-danger",
                    buttonsStyling: true
                }).then(function() {
                     $( "#delete_form" ).submit();
                });
            }

            function restart(id){
                swal({
                    title: '<g:message code="default.restart.ask.message" default="Do you want continue?"/>',
                    text: '<g:message code="default.restart.info.message" default="The password will be restart using the nick name"/>',
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#FFD100',
                    confirmButtonText: 'Si, continuar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
					location.href = "${request.contextPath}/userLG/restart/"+id;
                });
            }

            function go(id_tag){
				$('[name="footer-'+id_tag+'"]')[0].click();
			}
        </script>
    </head>
    <body>
		<g:render template="/layouts/menu" />
		<!-- .menu-box -->
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li><g:link action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
			  <li class="active"><g:message code="default.show.label" args="[entityName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><a href="javascript:go('edit')"		class="edit"><g:message code="default.button.edit.label" default="Edit" /></a></li>
                <li><a href="javascript:go('delete')"	class="delete"><g:message code="default.button.delete.label" default="Delete" /></a></li>
                <li><a href="javascript:go('password')"	class="password"><g:message code="default.button.restart.label" default="Restart" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="show-userLG" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

			%{--
            <f:display bean="userLG" />
			--}%

            <f:with bean="userLG">
				<ol class="property-list userLG">
					<li class="fieldcontain">
                        <span id="archivo_Foto-label" class="property-label"><g:message code="userLG.archivo_Foto.label" default="Fotografía" /></span>
                        <div class="property-value" aria-labelledby="archivo_Foto-label">
							<g:if test="${userLG.archivo_Foto}">
								<img src="${request.contextPath}/assets/mes-users/<f:display property="archivo_Foto"/>" style="width:200px;height:200px;"/>
							</g:if>
							<g:else>
								<img src="${request.contextPath}/assets/mes-users/default.png" style="width:200px;height:200px;"/>
							</g:else>
						</div>
                    </li>

                    <li class="fieldcontain">
                        <span id="username-label" class="property-label"><g:message code="userLG.username.label" default="Usuario" /></span>
                        <div class="property-value" aria-labelledby="username-label"><f:display property="username"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="password-label" class="property-label"><g:message code="userLG.password.label" default="Contraseña" /></span>
                        <div class="property-value" aria-labelledby="password-label"><f:display property="password"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="rol-label" class="property-label"><g:message code="userLG.rol.label" default="Rol" /></span>
                        <div class="property-value" aria-labelledby="rol-label">${userLG.getRol()}</div>
                    </li>

					<li class="fieldcontain">
                        <span id="nombre-label" class="property-label"><g:message code="userLG.nombre.label" default="Nombre" /></span>
                        <div class="property-value" aria-labelledby="nombre-label"><f:display property="nombre"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="email-label" class="property-label"><g:message code="userLG.email.label" default="Correo electrónico" /></span>
                        <div class="property-value" aria-labelledby="email-label"><f:display property="email"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="telefono_Movil-label" class="property-label"><g:message code="userLG.telefono_Movil.label" default="Teléfono móvil" /></span>
                        <div class="property-value" aria-labelledby="telefono_Movil-label"><f:display property="telefono_Movil"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="accountLocked-label" class="property-label"><g:message code="userLG.accountLocked.label" default="Cuenta bloqueada" /></span>
                        <div class="property-value" aria-labelledby="accountLocked-label"><f:display property="accountLocked"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="accountExpired-label" class="property-label"><g:message code="userLG.accountExpired.label" default="Expira la cuenta" /></span>
                        <div class="property-value" aria-labelledby="accountExpired-label"><f:display property="accountExpired"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="passwordExpired-label" class="property-label"><g:message code="userLG.passwordExpired.label" default="Expira la contraseña" /></span>
                        <div class="property-value" aria-labelledby="passwordExpired-label"><f:display property="passwordExpired"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="enabled-label" class="property-label"><g:message code="userLG.enabled.label" default="Activo" /></span>
                        <div class="property-value" aria-labelledby="enabled-label"><f:display property="enabled"/></div>
                    </li>

                    <!--
                    <li class="fieldcontain">
                        <span id="fecha_Registro-label" class="property-label"><g:message code="userLG.dateCreated.label" default="Fecha de registro" /></span>
                        <div class="property-value" aria-labelledby="fecha_Registro-label"><f:display property="dateCreated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="fecha_Cambio-label" class="property-label"><g:message code="userLG.lastUpdated.label" default="Última actualización" /></span>
                        <div class="property-value" aria-labelledby="fecha_Cambio-label"><f:display property="lastUpdated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="id_Autor-label" class="property-label"><g:message code="userLG.id_Autor.label" default="Autor" /></span>
                        <div class="property-value" aria-labelledby="id_Autor-label"><f:display property="id_Autor"/></div>
                    </li>
                    -->

                </ol>
            </f:with>

            <g:form resource="${this.userLG}" method="DELETE" name="delete_form">
                <fieldset class="buttons">
					<g:link name="footer-edit" class="edit" action="edit" resource="${this.userLG}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link name="footer-delete" class="delete" url="javascript:void(0)" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
                    <g:link name="footer-password" class="password" url="javascript:restart(${this.userLG.id})"><g:message code="default.button.restart.label" default="Restart" /></g:link>
                    <g:link name="footer-close" class="close" action="index"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
