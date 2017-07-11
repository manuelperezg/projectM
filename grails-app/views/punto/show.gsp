<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'punto.label', default: 'Punto')}" />
        <g:set var="entitiesName" value="${message(code: 'punto.label', default: 'Puntos')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script language="javascript">
            function jconfirm(pregunta,mensaje){
                swal({
                    title: pregunta,
                    text: mensaje,
                    type: "question",
                    showCancelButton: true,
                    confirmButtonColor: "#DA291C",
                    confirmButtonText: "Si, eliminar!",
                    cancelButtonText: "No",
                    confirmButtonClass: "btn btn-success",
                    cancelButtonClass: "btn btn-danger",
                    buttonsStyling: true
                }).then(function() {
                     $( "#delete_form" ).submit();
                });
            }

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
			  <li><g:link action="index" controller="verificacion"><g:message code="default.list.label" args="['Verificaciones']" /></g:link></li>
			  <li><g:link action="show" controller="verificacion" resource="${punto.verificacion}" >${punto.verificacion}</g:link></li>
			  <li class="active"><g:message code="default.show.label" args="[entityName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-punto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><a href="javascript:go('edit')"		class="edit"><g:message code="default.button.edit.label" default="Edit" /></a></li>
                <li><a href="javascript:go('delete')"	class="delete"><g:message code="default.button.delete.label" default="Delete" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="show-punto" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--
            <f:display bean="punto" />
            --}%

            <f:with bean="punto">
				<ol class="property-list punto">

					<li class="fieldcontain">
                        <span id="verificacion-label" class="property-label"><g:message code="punto.verificacion.label" default="Verificación" /></span>
                        <div class="property-value" aria-labelledby="verificacion-label"><f:display property="verificacion"/></div>
                    </li>

					<li class="fieldcontain">
                        <span id="punto-label" class="property-label"><g:message code="punto.punto.label" default="Punto" /></span>
                        <div class="property-value" aria-labelledby="punto-label"><f:display property="punto"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="conjunto-label" class="property-label"><g:message code="punto.conjunto.label" default="Conjunto" /></span>
                        <div class="property-value" aria-labelledby="conjunto-label"><f:display property="conjunto"/></div>
                    </li>

					<li class="fieldcontain">
                        <span id="nombre-label" class="property-label"><g:message code="punto.nombre.label" default="Nombre" /></span>
                        <div class="property-value" aria-labelledby="nombre-label"><f:display property="nombre"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="foto_bueno-label" class="property-label"><g:message code="punto.foto_bueno.label" default="Bueno" /></span>
                        <div class="property-value" aria-labelledby="foto_bueno-label">
							<g:if test="${punto.foto_bueno}">
								<img src="${request.contextPath}/assets/mes-icons/<f:display property='foto_bueno'/>" style="width:300px;height:200px;"/>
							</g:if>
							<g:else>
								<img src="${request.contextPath}/assets/mes-icons/default.png" style="width:300px;height:200px;"/>
							</g:else>
						</div>
                    </li>

					<g:if test="${punto.requiere_malo == true}">
					<li class="fieldcontain">
                        <span id="foto_malo-label" class="property-label"><g:message code="punto.foto_malo.label" default="Malo" /></span>
                        <div class="property-value" aria-labelledby="foto_malo-label">
							<g:if test="${punto.foto_malo}">
								<img src="${request.contextPath}/assets/mes-icons/<f:display property='foto_malo'/>" style="width:300px;height:200px;"/>
							</g:if>
							<g:else>
								<img src="${request.contextPath}/assets/mes-icons/default.png" style="width:300px;height:200px;"/>
							</g:else>
						</div>
                    </li>
                    </g:if>

                    <li class="fieldcontain">
                        <span id="actividad-label" class="property-label"><g:message code="punto.actividad.label" default="Actividades a seguir" /></span>
                        <div class="property-value" aria-labelledby="actividad-label"><f:display property="actividad"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="responsable-label" class="property-label"><g:message code="punto.responsable.label" default="Responsable" /></span>
                        <div class="property-value" aria-labelledby="responsable-label"><f:display property="responsable"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="frecuencia-label" class="property-label"><g:message code="punto.frecuencia.label" default="Frecuencia" /></span>
                        <div class="property-value" aria-labelledby="frecuencia-label"><f:display property="frecuencia"/></div>
                    </li>

                    <g:if test="${punto.frecuencia == 'Una vez por semana'}">
                    <li class="fieldcontain">
                        <span id="dia_semana-label" class="property-label"><g:message code="punto.dia_semana.label" default="Día de la semana" /></span>
                        <div class="property-value" aria-labelledby="dia_semana-label"><f:display property="dia_semana"/></div>
                    </li>
                    </g:if>

                    <g:if test="${punto.frecuencia == 'Una vez por día'}">
                    <li class="fieldcontain">
                        <span id="horario-label" class="property-label"><g:message code="punto.horario.label" default="Horario" /></span>
                        <div class="property-value" aria-labelledby="horario-label"><f:display property="horario"/></div>
                    </li>
                    </g:if>

                    <g:if test="${punto.frecuencia == 'Una vez por trimestre'||punto.frecuencia == 'Una vez por año'}">
                    <li class="fieldcontain">
                        <span id="mes-label" class="property-label"><g:message code="punto.mes.label" default="Mes" /></span>
                        <div class="property-value" aria-labelledby="mes-label"><f:display property="mes"/></div>
                    </li>
                    </g:if>

                    <li class="fieldcontain">
                        <span id="estado-label" class="property-label"><g:message code="punto.estado.label" default="Estado de la máquina" /></span>
                        <div class="property-value" aria-labelledby="estado-label"><f:display property="estado"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="tiempo-label" class="property-label"><g:message code="punto.tiempo.label" default="Tiempo (min)" /></span>
                        <div class="property-value" aria-labelledby="tiempo-label"><f:display property="tiempo"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="descripcion-label" class="property-label"><g:message code="punto.descripcion.label" default="¿Cómo hacerlo?" /></span>
                        <div class="property-value" aria-labelledby="descripcion-label"><f:display property="descripcion"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="dispositivos-label" class="property-label"><g:message code="punto.dispositivos.label" default="Dispositivos de seguridad" /></span>
                        <div class="property-value" aria-labelledby="dispositivos-label"><f:display property="dispositivos"/></div>
                    </li>

                    <li class="fieldcontain">
                        <span id="requiere_materiales-label" class="property-label"><g:message code="punto.requiere_materiales.label" default="Requiere materiales" /></span>
                        <div class="property-value" aria-labelledby="requiere_materiales-label"><f:display property="requiere_materiales"/></div>
                    </li>

                    <g:if test="${punto.requiere_materiales == true}">
                    <li class="fieldcontain">
                        <span id="materiales-label" class="property-label"><g:message code="punto.materiales.label" default="Materiales" /></span>
                        <div class="property-value" aria-labelledby="materiales-label"><f:display property="materiales"/></div>
                    </li>
                    </g:if>

                    <li class="fieldcontain">
                        <span id="requiere_subsistema-label" class="property-label"><g:message code="punto.requiere_subsistema.label" default="Requiere subsistema" /></span>
                        <div class="property-value" aria-labelledby="requiere_subsistema-label"><f:display property="requiere_subsistema"/></div>
                    </li>

                    <g:if test="${punto.requiere_subsistema == true}">
                    <li class="fieldcontain">
                        <span id="subsistema-label" class="property-label"><g:message code="punto.subsistema.label" default="Subsistema" /></span>
                        <div class="property-value" aria-labelledby="subsistema-label"><f:display property="subsistema"/></div>
                    </li>
                    </g:if>

					<!--
                    <li class="fieldcontain">
                        <span id="fecha_Registro-label" class="property-label"><g:message code="verificacion.dateCreated.label" default="Fecha de registro" /></span>
                        <div class="property-value" aria-labelledby="fecha_Registro-label"><f:display property="dateCreated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="fecha_Cambio-label" class="property-label"><g:message code="punto.lastUpdated.label" default="Última actualización" /></span>
                        <div class="property-value" aria-labelledby="fecha_Cambio-label"><f:display property="lastUpdated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="id_Autor-label" class="property-label"><g:message code="punto.id_Autor.label" default="Autor" /></span>
                        <div class="property-value" aria-labelledby="id_Autor-label"><f:display property="id_Autor"/></div>
                    </li>
					-->

                </ol>
            </f:with>

            <g:form resource="${this.punto}" method="DELETE" name="delete_form">
				<fieldset class="buttons">
					<g:link name="footer-edit" class="edit" action="edit" resource="${this.punto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link name="footer-delete" class="delete" url="javascript:void(0)" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
                    <g:link name="footer-close" class="close" action="show" controller="verificacion" resource="${punto.verificacion}"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
