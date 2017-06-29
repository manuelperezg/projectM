<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tipoVerificacion.label', default: 'TipoVerificacion')}" />
        <g:set var="entitiesName" value="${message(code: 'tipoVerificacion.label', default: 'TipoVerificacions')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <style>
            #nombre, #descripcion {width:50%}
        </style>
        <script language="javascript">
			$( document ).ready(function() {

			});

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
			  <li><g:link action="show" resource="${this.tipoVerificacion}"><g:message code="default.show.label" args="[entityName]" /></g:link></li>
			  <li class="active"><g:message code="default.edit.label" args="[entityName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#edit-tipoVerificacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
				<li><a href="javascript:go('save')"		class="save"><g:message code="default.button.save.label" default="Edit" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="edit-tipoVerificacion" class="content scaffold-edit" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.tipoVerificacion}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.tipoVerificacion}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.tipoVerificacion}" method="PUT">
                <g:hiddenField name="version" value="${this.tipoVerificacion?.version}" />
                <fieldset class="form">
                    %{--
                    <f:all bean="tipoVerificacion"/>
                     --}%

                    <f:with bean="tipoVerificacion">
						<f:field property="nombre"/>
                        <f:field property="descripcion"/>
                        %{--
                        <f:field property="dateCreated" wrapper="display"/>
                        <f:field property="lastUpdated" wrapper="display"/>
                        <f:field property="id_Autor" wrapper="display"/>
                         --}%
					</f:with>


                </fieldset>
                <g:submitButton name="footer-save" style="visibility:hidden;position:absolute;top:0px;width:0px;"/>
                <fieldset class="buttons">
					<g:link class="save" url="javascript:go('save')"><g:message code="default.button.save.label" default="Save" /></g:link>
                    <g:link name="footer-close" class="close" action="show" resource="${this.tipoVerificacion}"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
