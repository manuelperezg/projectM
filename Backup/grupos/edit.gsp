<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'grupos.label', default: 'Grupos')}" />
        <g:set var="entitiesName" value="${message(code: 'grupos.label', default: 'Grupo')}" />
        <title><g:message code="default.edit.label" args="[entitiesName]" /></title>
        <style>
            #nombre {width:5%}
            #semestre {width:5%}
            #modalidad {width:10%}
        </style>
        <script language="javascript">
			$( document ).ready(function() {
				$("#materia_id").select2();
                $("#docente_id").select2();
				$("#modalidad").select2();
			});
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#edit-grupos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label_a" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-grupos" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.grupos}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.grupos}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.grupos}" method="PUT">
                <g:hiddenField name="version" value="${this.grupos?.version}" />
                <fieldset class="form">
                %{--
                    <f:all bean="grupos"/>
                --}%

                    <f:with bean="grupos">
                        <f:field property="nombre"/>
                        <f:field property="semestre"/>

                        <f:field property="materia_id">
                            <g:select name="materia_id" from="${materias}" optionKey="id" optionValue="nombre" value="${grupos?.materia_id}"/>
                        </f:field>

                        <f:field property="docente_id">
                            <g:select name="docente_id" from="${docentes}" optionKey="id" optionValue="nombre" value="${grupos?.docente_id}"/>
                        </f:field>

                        <f:field property="modalidad"/>
                        <f:field property="horas"/>
                        <f:field property="cupo"/>
                        <f:field property="inscritos"/>
                    </f:with>

                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
