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
		<!-- .menu-box -->
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li class="active"><g:message code="default.report.label" args="['Listado de tipos de mantenimiento']" /></li>
			</ul>	
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#create-materias" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="upload-data" class="content scaffold-create" role="main">
            <iframe frameborder="0" width="100%" height="450px" src="http://unilever-mes.com/jasperserver/flow.html?_flowId=viewReportFlow&j_username=jasperadmin&j_password=jasperadmin&standAlone=true&decorate=no&_flowId=viewReportFlow&ParentFolderUri=%2Freports&reportUnit=%2Freports%2Flistado_mantenimientos"></iframe>
        </div>
    </body>
</html>
