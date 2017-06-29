<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alumnos.label', default: 'Alumno')}" />
        <g:set var="entitiesName" value="${message(code: 'alumnos.label', default: 'Alumnos')}" />
        <title><g:message code="default.create.label" args="[entitiesName]" /></title>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#create-materias" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-alumnos" class="content scaffold-show" role="main">
            <h1><g:message code="default.report.label" args="['Listas de asistencia por grupos']" /></h1>
        </div>
        <div id="upload-data" class="content scaffold-create" role="main">
            <iframe frameborder="0" width="100%" height="450px" src="http://${session.getAttribute('servidor')}:9090/jasperserver/flow.html?_flowId=viewReportFlow&j_username=jasperadmin&j_password=jasperadmin&standAlone=true&decorate=no&_flowId=viewReportFlow&ParentFolderUri=%2Freports&reportUnit=%2Freports%2Flistas"></iframe>
        </div>
    </body>
</html>
