<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'planta.label', default: 'Planta')}" />
        <g:set var="entitiesName" value="${message(code: 'planta.label', default: 'Plantas')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <style>
            #nombre {
				width:50%;
			}
            #direccion {
				width:50%;
			}
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
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li><g:link action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
			  <li class="active"><g:message code="default.new.label_a" args="[entityName]" /></li>
			</ul>	
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#create-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
				<li><a href="javascript:go('save')"		class="save"><g:message code="default.button.save.label" default="Edit" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="create-planta" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.planta}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.planta}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm action="save">
                <fieldset class="form">
					%{--
                    <f:all bean="planta"/>
                    --}%
                    <f:with bean="planta">
						<f:field property="archivo_Foto" wrapper="icon"/>
						<f:field property="nombre"/>
						<f:field property="direccion"/>
						<f:field property="archivo_Esquema" wrapper="icon"/>
                    </f:with>
                </fieldset>
                <g:submitButton name="footer-save" style="visibility:hidden;position:absolute;top:0px;width:0px;"/>
                <fieldset class="buttons">
					<g:link class="save" url="javascript:go('save')"><g:message code="default.button.save.label" default="Save" /></g:link>
                    <g:link name="footer-close" class="close" action="index"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
