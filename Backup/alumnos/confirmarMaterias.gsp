<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alumnos.label', default: 'Grupo')}" />
        <g:set var="entitiesName" value="${message(code: 'alumnos.label', default: 'Grupos')}" />
        <title>Confirmación de UCs</title>
        <script language="javascript">
            function index(){
                location.href="/#";
            }
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#list-alumnos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-alumnos" class="content scaffold-list" role="main">
            <h1><g:message code="default.register.confirm.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form method="POST" action="confirmar" name="confirm_form">
                <table>
                        <thead>
                                <tr>
                                        <g:sortableColumn property="grupo" title="Grupo" />
                                        <g:sortableColumn property="semestre" title="Semestre" />
                                        <g:sortableColumn property="uc" title="Unidad curricular" />
                                        <g:sortableColumn property="docente" title="Docente" />
                                        <g:sortableColumn property="estado" title="Estado" />
                                </tr>
                        </thead>
                        <tbody>
                        </tbody>
                </table>
            </g:form>

        </div>
    </body>
</html>
