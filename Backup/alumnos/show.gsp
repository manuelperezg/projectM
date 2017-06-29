<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alumnos.label', default: 'Alumno')}" />
        <g:set var="entitiesName" value="${message(code: 'alumnos.label', default: 'Alumnos')}" />
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
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#show-alumnos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-alumnos" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--
            <f:display bean="alumnos" />
            -->
            
            <ol class="property-list alumnos">
                <li class="fieldcontain">
                    <span id="matricula-label" class="property-label"><g:message code="alumnos.matricula.label" /></span>
                    <div class="property-value" aria-labelledby="matricula-label">${alumnos.matricula}</div>
                </li>
                <li class="fieldcontain">
                    <span id="ap_paterno-label" class="property-label"><g:message code="alumnos.ap_paterno.label" /></span>
                    <div class="property-value" aria-labelledby="ap_paterno-label">${alumnos.ap_paterno}</div>
                </li>
                <li class="fieldcontain">
                    <span id="ap_materno-label" class="property-label"><g:message code="alumnos.ap_materno.label" /></span>
                    <div class="property-value" aria-labelledby="ap_materno-label">${alumnos.ap_materno}</div>
                </li>
				<li class="fieldcontain">
                    <span id="nombre-label" class="property-label">Nombre</span>
                    <div class="property-value" aria-labelledby="nombre-label">${alumnos.nombre}</div>
                </li>
                <li class="fieldcontain">
                    <span id="status-label" class="property-label">Status</span>
                    <div class="property-value" aria-labelledby="status-label">${alumnos.status}</div>
                </li>
                <li class="fieldcontain">
                    <span id="planest-label" class="property-label"><g:message code="alumnos.planest.label" /></span>
                    <div class="property-value" aria-labelledby="planest-label">${alumnos.planest}</div>
                </li>
                <li class="fieldcontain">
                    <span id="generacion-label" class="property-label"><g:message code="alumnos.generacion.label" /></span>
                    <div class="property-value" aria-labelledby="generacion-label">${alumnos.generacion}</div>
                </li>
                <li class="fieldcontain">
                    <span id="semestre-label" class="property-label">Semestre</span>
                    <div class="property-value" aria-labelledby="semestre-label">${alumnos.semestre}</div>
                </li>
                <li class="fieldcontain">
                    <span id="curp-label" class="property-label">CURP</span>
                    <div class="property-value" aria-labelledby="curp-label">${alumnos.curp}</div>
                </li>
                <li class="fieldcontain">
                    <span id="sexo-label" class="property-label">Sexo</span>
                    <div class="property-value" aria-labelledby="sexo-label">${alumnos.sexo}</div>
                </li>
                <li class="fieldcontain">
                    <span id="estado-label" class="property-label">Estado</span>
                    <div class="property-value" aria-labelledby="estado-label">${alumnos.estado}</div>
                </li>
                <li class="fieldcontain">
                    <span id="municipio-label" class="property-label">Municipio</span>
                    <div class="property-value" aria-labelledby="municipio-label">${alumnos.municipio}</div>
                </li>
                <li class="fieldcontain">
                    <span id="cp-label" class="property-label"><g:message code="alumnos.cp.label" /></span>
                    <div class="property-value" aria-labelledby="cp-label">${alumnos.cp}</div>
                </li>
                <li class="fieldcontain">
                    <span id="unidad_id-label" class="property-label"><g:message code="alumnos.unidad_id.label" /></span>
                    <div class="property-value" aria-labelledby="unidad_id-label">${alumnos.getUnidad() ?alumnos.getUnidad().nombre :raw('<b><font color="red">No identificada</font></b>')}</div>
                </li>
                <li class="fieldcontain">
                    <span id="carrera_id-label" class="property-label"><g:message code="alumnos.carrera_id.label" /></span>
                    <div class="property-value" aria-labelledby="carrera_id-label">${alumnos.getCarrera() ?alumnos.getCarrera().nombre :raw('<b><font color="red">No identificada</font></b>')}
                    </div>
                </li>
			</ol>
			
            <g:form resource="${this.alumnos}" method="DELETE" name="delete_form">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.alumnos}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="button" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
