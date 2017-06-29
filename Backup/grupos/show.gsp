<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'grupos.label', default: 'Grupo')}" />
        <g:set var="entitiesName" value="${message(code: 'grupos.label', default: 'Grupos')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script language="javascript">
            function jconfirm(pregunta,mensaje){
                swal({
                    title: pregunta,
                    text: mensaje,
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#5CB85C',
                    confirmButtonText: 'Si, eliminar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
                     $( "#delete_form" ).submit();
                });
            }
            $( document ).ready(function() {
                $("#alumno_id").select2();
                $("#estado").select2();
            });
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#show-grupos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-grupos" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

        %{--
            <f:display bean="grupos" />
        --}%

        <ol class="property-list grupos">

                <li class="fieldcontain">
                    <span id="nombre-label" class="property-label">Grupo</span>
                    <div class="property-value" aria-labelledby="nombre-label">${grupos.nombre}</div>
                </li>

                <li class="fieldcontain">
                    <span id="semestre-label" class="property-label">Semestre</span>
                    <div class="property-value" aria-labelledby="semestre-label">${grupos.semestre}</div>
                </li>

                <li class="fieldcontain">
                    <span id="materia_id-label" class="property-label">Unidad curricular</span>
                    <div class="property-value" aria-labelledby="materia_id-label">${grupos.getMateria() ?grupos.getMateria().nombre :raw('<b><font color="red">No identificada</font></b>')}</div>
                </li>

                <li class="fieldcontain">
                    <span id="docente_id-label" class="property-label">Docente</span>
                    <div class="property-value" aria-labelledby="docente_id-label">${grupos.getDocente() ?grupos.getDocente().nombre :raw('<b><font color="red">No identificada</font></b>')}</div>
                </li>

                <li class="fieldcontain">
                    <span id="modalidad-label" class="property-label">Modalidad</span>
                    <div class="property-value" aria-labelledby="modalidad-label">${grupos.modalidad}</div>
                </li>

                <li class="fieldcontain">
                    <span id="horas-label" class="property-label">Horas</span>
                    <div class="property-value" aria-labelledby="horas-label">${grupos.horas}</div>
                </li>

                <li class="fieldcontain">
                    <span id="cupo-label" class="property-label">Cupo</span>
                    <div class="property-value" aria-labelledby="cupo-label">${grupos.cupo}</div>
                </li>

                <li class="fieldcontain">
                    <span id="inscritos-label" class="property-label">Alumnos inscritos</span>
                    <div class="property-value" aria-labelledby="inscritos-label">${grupos.inscritos}</div>
                </li>
        </ol>
		<div id="list-alumnos" class="content scaffold-list" role="main">
            <h1> Alumnos</h1>

            <g:form method="post">
                &nbsp;&nbsp;&nbsp;<g:select name="alumno_id" from="${alumnosAll}" optionKey="id" optionValue="matricula"/>
                &nbsp;&nbsp;&nbsp;<g:select name="estado" from="${['Primer vez','Recursada']}"/>
                <g:hiddenField name="grupo_id" value="${grupos.id}" />
                &nbsp;&nbsp;&nbsp;<g:actionSubmit class="create" action="addAlumno" value="Agregar alumno" />
            </g:form>
            <br>
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="operaciones" title="Operaciones" />
                        <g:sortableColumn property="matricula" title="Matrícula" />
                        <g:sortableColumn property="ap_paterno" title="Apellido paterno" />
                        <g:sortableColumn property="ap_materno" title="Apellido materno" />
                        <g:sortableColumn property="nombre" title="Nombre" />
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${alumnos}" var="alumno" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td align="center">
                                <g:link action="deleteAlumno" id="${grupos.id}" params="[alumno:alumno.id]"><g:img uri="${request.contextPath}/assets/skin/close.png" title="Eliminar del grupo"/></g:link>
                            </td>
                            <td><g:link method="GET" resource="${alumno}">${alumno.matricula}</g:link></td>
                            <td>${alumno.ap_paterno}</td>
                            <td>${alumno.ap_materno}</td>
                            <td>${alumno.nombre}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>

            <g:form resource="${this.grupos}" method="DELETE" name="delete_form">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.grupos}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="button" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
