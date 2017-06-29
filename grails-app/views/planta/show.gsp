<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'planta.label', default: 'Planta')}" />
        <g:set var="entitiesName" value="${message(code: 'planta.label', default: 'Plantas')}" />
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
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li><g:link action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
			  <li class="active"><g:message code="default.show.label" args="[entityName]" /></li>
			</ul>	
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-planta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><a href="javascript:go('edit')"		class="edit"><g:message code="default.button.edit.label" default="Edit" /></a></li>
                <li><a href="javascript:go('delete')"	class="delete"><g:message code="default.button.delete.label" default="Delete" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="show-planta" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--
            <f:display bean="planta" />
            --}%
            <f:with bean="planta">
				<ol class="property-list userLG">
					<li class="fieldcontain">
                        <span id="archivo_Foto-label" class="property-label"><g:message code="planta.archivo_Foto.label" default="Foto de la Planta" /></span>
                        <div class="property-value" aria-labelledby="archivo_Foto-label">
							<g:if test="${planta.archivo_Foto}">
								<img src="${request.contextPath}/assets/mes-icons/<f:display property='archivo_Foto'/>" style="width:300px;height:200px;"/>
							</g:if>
							<g:else>
								<img src="${request.contextPath}/assets/mes-icons/default.png" style="width:300px;height:200px;"/>
							</g:else>
						</div>
                    </li>
                    
                    <li class="fieldcontain">
                        <span id="nombre-label" class="property-label"><g:message code="planta.nombre.label" default="Name" /></span>
                        <div class="property-value" aria-labelledby="nombre-label"><f:display property="nombre"/></div>
                    </li>
                    
                    <li class="fieldcontain">
                        <span id="direccion-label" class="property-label"><g:message code="planta.direccion.label" default="Address" /></span>
                        <div class="property-value" aria-labelledby="direccion-label"><f:display property="direccion"/></div>
                    </li>
                    
                    <li class="fieldcontain">
                        <span id="archivo_Esquema-label" class="property-label"><g:message code="planta.archivo_Esquema.label" default="Esquema de las líneas" /></span>
                        <div class="property-value" aria-labelledby="archivo_Esquema-label">
							<g:if test="${planta.archivo_Esquema}">
								<img src="${request.contextPath}/assets/mes-icons/<f:display property='archivo_Esquema'/>" style="width:300px;height:200px;"/>
							</g:if>
							<g:else>
								<img src="${request.contextPath}/assets/mes-icons/default.png" style="width:300px;height:200px;"/>
							</g:else>
						</div>
                    </li>
                    <!--
					<li class="fieldcontain">
                        <span id="fecha_Registro-label" class="property-label"><g:message code="planta.dateCreated.label" default="Fecha de registro" /></span>
                        <div class="property-value" aria-labelledby="fecha_Registro-label"><f:display property="dateCreated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="fecha_Cambio-label" class="property-label"><g:message code="planta.lastUpdated.label" default="Última actualización" /></span>
                        <div class="property-value" aria-labelledby="fecha_Cambio-label"><f:display property="lastUpdated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="id_Autor-label" class="property-label"><g:message code="planta.id_Autor.label" default="Autor" /></span>
                        <div class="property-value" aria-labelledby="id_Autor-label"><f:display property="id_Autor"/></div>
                    </li>
                    -->
					
                 </ol>
            </f:with>
            
            <g:form resource="${this.planta}" method="DELETE" name="delete_form">
				<fieldset class="buttons">
					<g:link name="footer-edit" class="edit" action="edit" resource="${this.planta}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link name="footer-delete" class="delete" url="javascript:void(0)" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
                    <g:link name="footer-close" class="close" action="index"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
