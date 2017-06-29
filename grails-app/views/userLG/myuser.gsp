<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLG.label', default: 'Usuario')}" />
        <g:set var="entitiesName" value="${message(code: 'userLG.label', default: 'Usuarios')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <style>
            #nombre {width:50%}
            #email {width:30%}
        </style>
        <script language="javascript">
            function PreviewImage(id_upload, id_preview) {
				var oFReader = new FileReader();
				oFReader.readAsDataURL(document.getElementById(id_upload).files[0]);

				oFReader.onload = function (oFREvent) {
					document.getElementById(id_preview).src = oFREvent.target.result;
				};
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
			  <li class="active"><g:message code="default.myuser.label" args="[entityName]" /></li>
			</ul>	
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
				<li><a href="javascript:go('save')"		class="save"><g:message code="default.button.save.label" default="Edit" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="show-userLG" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
			<g:hasErrors bean="${this.userLG}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.userLG}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
			<g:uploadForm resource="${this.userLG}" action="updateMyUser" method="POST">
				<f:with bean="userLG">
					<ol class="property-list userLG">
						
						<g:hiddenField name="username" value="${userLG.username}" />
						<g:hiddenField name="nombre" value="${userLG.nombre}" />
						<g:hiddenField name="email" value="${userLG.email}" />
						<g:hiddenField name="telefono_Movil" value="${userLG.telefono_Movil}" />
						<g:hiddenField name="role" value="${userLG.getRol()}" />
						<g:hiddenField name="enabled" value="${userLG.enabled}" />
						<g:hiddenField name="accountExpired" value="${userLG.accountExpired}" />
						<g:hiddenField name="accountLocked" value="${userLG.accountLocked}" />
						<g:hiddenField name="passwordExpired" value="${userLG.passwordExpired}" />
						
						<f:field property="archivo_Foto" wrapper="photo"/>
						
						<li class="fieldcontain">
							<span id="username-label" class="property-label"><g:message code="userLG.username.label" default="Usuario" /></span>
							<div class="property-value" aria-labelledby="username-label"><f:display property="username"/></div>
						</li>

						<f:field property="password" label="Contraseña"/>
						<g:hiddenField name="password_Plain" value="${userLG.password_Plain}" />
						
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
							<span id="fecha_Registro-label" class="property-label"><g:message code="tipoMantenimiento.dateCreated.label" default="Fecha de registro" /></span>
							<div class="property-value" aria-labelledby="fecha_Registro-label"><f:display property="dateCreated"/></div>
						</li>
						<li class="fieldcontain">
							<span id="fecha_Cambio-label" class="property-label"><g:message code="tipoMantenimiento.lastUpdated.label" default="Última actualización" /></span>
							<div class="property-value" aria-labelledby="fecha_Cambio-label"><f:display property="lastUpdated"/></div>
						</li>
						<li class="fieldcontain">
							<span id="id_Autor-label" class="property-label"><g:message code="userLG.id_Autor.label" default="Autor" /></span>
							<div class="property-value" aria-labelledby="id_Autor-label"><f:display property="id_Autor"/></div>
						</li>
						-->
						
					</ol>
				</f:with>
				<g:submitButton name="footer-save" style="visibility:hidden;position:absolute;top:0px;width:0px;"/>
                <fieldset class="buttons">
					<g:link class="save" url="javascript:go('save')"><g:message code="default.button.save.label" default="Save" /></g:link>
                    <g:link name="footer-close" class="close" uri="/"><g:message code="default.button.close.label" default="Close" /></g:link>
				</fieldset>
			</g:uploadForm>
        </div>
    </body>
</html>
