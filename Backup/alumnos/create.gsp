<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alumnos.label', default: 'Alumno')}" />
        <g:set var="entitiesName" value="${message(code: 'alumnos.label', default: 'Alumnos')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <style>
            #nombre {width:25%}
            #status {width:150px}
            #sexo {width:150px}
        </style>
        <script language="javascript">
			$( document ).ready(function() {
				$("#status").select2();
				$("#sexo").select2();
                $("#unidad_id").select2();
				$("#carrera_id").select2();
			});
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#create-alumnos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-alumnos" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.alumnos}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.alumnos}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
					<!--
                    <f:all bean="alumnos"/>
                    -->                
                    <f:with bean="alumnos">
                        <f:field property="matricula"/>
                        <f:field property="ap_paterno"/>
                        <f:field property="ap_materno"/>
                        <f:field property="nombre"/>
                        <f:field property="status"/>
                        <f:field property="planest"/>
                        <f:field property="generacion"/>
                        <f:field property="semestre"/>
                        <f:field property="fecha_nac"/>
                        <f:field property="curp"/>
                        <f:field property="sexo">
							<g:select name="sexo" from="${['M', 'F']}" valueMessagePrefix="alumnos.sexo" />
                        </f:field>
                        <f:field property="estado"/>
                        <f:field property="municipio"/>
                        <f:field property="cp"/>
						<f:field property="unidad_id">
                            <g:select name="unidad_id" from="${unidades}" optionKey="id" optionValue="nombre"/>
                        </f:field>
                        <f:field property="carrera_id">
                            <g:select name="carrera_id" from="${carreras}" optionKey="id" optionValue="nombre"/>
                        </f:field>
                    </f:with>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
