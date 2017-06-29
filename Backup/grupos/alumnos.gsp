<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'grupos.label', default: 'Grupo')}" />
        <g:set var="entitiesName" value="${message(code: 'grupos.label', default: 'Grupos')}" />
        <title><g:message code="default.list.label" args="[entitiesName]" /></title>
        <script language="javascript">
            $( document ).ready(function() {
                $("#alumno_id").select2();
                $("#estado").select2();
            });
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#list-grupos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
            %{--
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
             --}%
            </ul>
        </div>
        <div id="list-grupos" class="content scaffold-list" role="main">
            <h1>Grupo ${grupos.nombre}</h1>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--
            <f:table collection="${gruposList}" />
			-->
            <table>
                <tbody>
                    <g:each in="${grupos}" var="grupo" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td><strong>Semestre: </strong>${grupo.semestre}</td>
                            <td>Unidad Curricular: ${(grupo.getMateria())?grupo.getMateria().nombre:raw('<b><font color="red">No identificada</font></b>')}</td>
                            <td>Docente: ${(grupo.getDocente())?grupo.getDocente().nombre:raw('<b><font color="red">No identificado</font></b>')}</td>
                        </tr>
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td>Modalidad: ${grupo.modalidad}</td>
                            <td>Horas: ${grupo.horas}</td>
                            <td>Cupo: ${grupo.cupo} - Inscritos: ${grupo.inscritos}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <hr><br>${materias}
        <div id="list-alumnos" class="content scaffold-list" role="main">
            <h1> Alumnos</h1>

            <g:form method="post">
                &nbsp;&nbsp;&nbsp;<g:select name="alumno_id" from="${alumnosAll}" optionKey="id" optionValue="matricula"/>
                &nbsp;&nbsp;&nbsp;<g:select name="estado" from="${['Primer vez','Recursada']}"/>
                <g:hiddenField name="grupo_id" value="${grupos.id}" />
                &nbsp;&nbsp;&nbsp;<g:actionSubmit class="create" action="addAlumno" value="Agregar alumno" />
            </g:form>
            <br>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
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
    </body>
</html>
