<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'linea.label', default: 'Línea')}" />
        <g:set var="entitiesName" value="${message(code: 'linea.label', default: 'Líneas')}" />
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

            function jremove_punto(pregunta,mensaje,id){
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
                     location.href = "${request.contextPath}/maquina/remove/"+id;
                });
            }

            function go(id_tag){
				$('[name="footer-'+id_tag+'"]')[0].click();
			}

			function showImage(nombre, archivo){
				swal({
				  title: nombre,
				  html:
					'<img src="${request.contextPath}/assets/mes-icons/'+archivo+'" border="0px" style="width:350px; height:250px"/>',
				  showCloseButton: true,
				  confirmButtonText:
					'<i class="fa fa-thumbs-up"></i> Aceptar'
				})
			}

			function showQR(nombre, archivo){
				swal({
				  title: nombre,
				  html:
					'<img id="qr_cdx" src="${request.contextPath}/qrcode/text?text='+archivo+'&s=410" border="0px" style="width:410px; height:410px"/>',
				  showCloseButton: true,
				  showCancelButton: true,
				  cancelButtonColor: "#6BA539",
				  confirmButtonColor: "#005EB8",
				  cancelButtonText:
					'<i class="fa fa-print"></i> Imprimir',
				  confirmButtonText:
					'<i class="fa fa-thumbs-up"></i> Aceptar'
				}).then(
				function(result) {
					// handle Confirm button click
					// result is an optional parameter, needed for modals with input
				}, function(dismiss) {
					if (dismiss === 'cancel') {
						$("#qr_cdx").print();
					}
				});
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
        <a href="#show-linea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><a href="javascript:go('edit')"		class="edit"><g:message code="default.button.edit.label" default="Edit" /></a></li>
                <li><a href="javascript:go('delete')"	class="delete"><g:message code="default.button.delete.label" default="Delete" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
                <li style="float:right;margin:0px;"><a href="javascript:go('create')"	class="create"><g:message code="default.new.label_a" args="['Máquina']" /></a></li>
            </ul>
        </div>
        <div id="show-linea" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            %{--
            <f:display bean="linea" />
			--}%

            <f:with bean="linea">
				<ol class="property-list userLG">

					<li class="fieldcontain">
                        <span id="planta-label" class="property-label"><g:message code="linea.planta.label" default="Planta" /></span>
                        <div class="property-value" aria-labelledby="email-label"><f:display property="planta"/></div>
                    </li>

					<li class="fieldcontain">
                        <span id="nombre-label" class="property-label"><g:message code="linea.nombre.label" default="Nombre" /></span>
                        <div class="property-value" aria-labelledby="nombre-label"><f:display property="nombre"/></div>
                    </li>
					<!--
                    <li class="fieldcontain">
                        <span id="fecha_Registro-label" class="property-label"><g:message code="linea.dateCreated.label" default="Fecha de registro" /></span>
                        <div class="property-value" aria-labelledby="fecha_Registro-label"><f:display property="dateCreated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="fecha_Cambio-label" class="property-label"><g:message code="linea.lastUpdated.label" default="Última actualización" /></span>
                        <div class="property-value" aria-labelledby="fecha_Cambio-label"><f:display property="lastUpdated"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="id_Autor-label" class="property-label"><g:message code="linea.id_Autor.label" default="Autor" /></span>
                        <div class="property-value" aria-labelledby="id_Autor-label"><f:display property="id_Autor"/></div>
                    </li>
					-->

                </ol>
            </f:with>

			<div class="content scaffold-list" id="list-maquina" role="main">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="archivo_Foto" title="${message(code: 'maquina.archivo_Foto.label', default: 'Photo')}" style="width:100px;" />
							<g:sortableColumn property="nombre" title="Nombre" />
							<g:sortableColumn property="qrCode" title="${message(code: 'maquina.qrCode.label', default: 'QR Code')}" style="width:100px;" />
							<g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
						</tr>
					</thead>

					<tbody>
						<g:each in="${maquinas}" var="maquina" status="i">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td style="width:100px;">
									<g:if test="${maquina.archivo_Foto}">
									<center>
										<a href="javascript:showImage('${maquina.nombre.replaceAll("'", "").trim()}','${maquina.archivo_Foto}');"><img src="${request.contextPath}/assets/mes-icons/${maquina.archivo_Foto}" border="0px" width="100px"/></a>
									</center>
									</g:if>
								</td>
								<td><g:link method="GET" resource="${maquina}">${maquina.nombre}</g:link></br><span style="color:#515151;font-size:12px">${maquina.descripcion}</span></td>
								<td style="width:100px;">
									<g:if test="${maquina.qrCode}">
									<center>
										<a href="javascript:showQR('${maquina.nombre.replaceAll("'", "").trim()}','${maquina.qrCode}');"><qrcode:image height="76" width="76" text="${maquina.qrCode}" border="0px"/></a>
									</center>
									</g:if>
								</td>
								<td>
									<center>
										<a class="operations_icon red" onclick="jremove_punto('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[maquina.nombre])}','${maquina.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
									</center>
								</td>
							</tr>
						</g:each>
						<g:if test="${maquinaCount == 0}">
							<tr>
								<td colspan="4"><g:message code="default.table.empty.label"/></td>
							</tr>
						</g:if>
					</tbody>
				</table>

				<div class="pagination" style="-moz-border-radius: 0em 0em 0em 0em; -webkit-border-radius: 0em 0em 0em 0em; border-radius: 0em 0em 0em 0em;">
					<g:paginate total="${maquinaCount ?: 0}" />
				</div>
			</div>

            <g:form resource="${this.linea}" method="DELETE" name="delete_form">
                <fieldset class="buttons">
					<g:link name="footer-edit" class="edit" action="edit" resource="${this.linea}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link name="footer-delete" class="delete" url="javascript:void(0)" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
                    <g:link name="footer-close" class="close" action="index"><g:message code="default.button.close.label" default="Close" /></g:link>
                    <g:link name="footer-create" class="create" action="create" controller="maquina" params="[linea: "${this.linea.id}"]" style="float:right"><g:message code="default.new.label_a" args="['Máquina']" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
