<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'maquina.label', default: 'Máquina')}" />
        <g:set var="entitiesName" value="${message(code: 'maquina.label', default: 'Máquinas')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <style>
            #nombre {width:70%}
            #linea {width:70%}
            #descripcion {width:70%}
        </style>
        <script language="javascript">
			$( document ).ready(function() {
				$("#linea").select2();
			});
			
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
			  <li><g:link action="index" controller="linea"><g:message code="default.list.label" args="['Líneas']" /></g:link></li>
			  <li><g:link action="show" controller="linea" resource="${maquina.linea}" >${maquina.linea.planta} - ${maquina.linea}</g:link></li>
			  <li class="active"><g:message code="default.edit.label" args="[entityName]" /></li>
			</ul>	
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#edit-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
				<li><a href="javascript:go('save')"		class="save"><g:message code="default.button.save.label" default="Edit" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="edit-userLG" class="content scaffold-edit" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.maquina}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.maquina}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm resource="${this.maquina}" action="upchange" method="POST">
                <g:hiddenField name="version" value="${this.maquina?.version}" />
                 <div class="content">
                    <div class="row">
                        <div class="col-xs-8">
                            <fieldset class="form">
                                %{--
								<f:all bean="maquina"/>
								--}%
								<f:with bean="maquina">
									<f:field property="archivo_Foto" wrapper="icon"/>
									<f:field property="linea.planta" wrapper="display"/>
									<f:field property="linea"/>
									<f:field property="nombre"/>
									<f:field property="descripcion"/>
									%{--
									<f:field property="dateCreated" wrapper="display"/>
									<f:field property="lastUpdated" wrapper="display"/>
									<f:field property="id_Autor" wrapper="display"/>
									--}%
								</f:with>
                            </fieldset>
                        </div>
                        <div class="col-xs-4">
                            <g:hiddenField name="qrCode" value="${maquina.qrCode}" />
                            <qrcode:image height="410" width="410" text="${maquina.qrCode}"/>
                        </div>
                    </div>
                </div>
                <g:submitButton name="footer-save" style="visibility:hidden;position:absolute;top:0px;width:0px;"/>
                <fieldset class="buttons">
					<g:link class="save" url="javascript:go('save')"><g:message code="default.button.save.label" default="Save" /></g:link>
                    <g:link name="footer-close" class="close" action="show" controller="maquina" resource="${this.maquina}"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
