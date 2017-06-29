<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'maquina.label', default: 'Máquina')}" />
        <g:set var="entitiesName" value="${message(code: 'maquina.label', default: 'Máquinas')}" />
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

            function jremove_subsistema(pregunta,mensaje,id){
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
                     location.href = "${request.contextPath}/subsistema/remove/"+id;
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

			function print_qr(){
				$("#qr_cdx").print();
			}
        </script>
    </head>
    <body>
		<g:render template="/layouts/menu" />
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li><g:link action="index" controller="linea"><g:message code="default.list.label" args="['Líneas']" /></g:link></li>
			  <li><g:link action="show" controller="linea" resource="${maquina.linea}" >${maquina.linea.planta} - ${maquina.linea}</g:link></li>
			  <li class="active"><g:message code="default.show.label" args="[entityName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-maquina" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><a href="javascript:go('edit')"		class="edit"><g:message code="default.button.edit.label" default="Edit" /></a></li>
                <li><a href="javascript:go('delete')"	class="delete"><g:message code="default.button.delete.label" default="Delete" /></a></li>
                <li><a href="javascript:go('print')"	class="print"><g:message code="default.button.print_qr.label" default="Print QR" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
                <li style="float:right;margin:0px;"><a href="javascript:go('create')"	class="create"><g:message code="default.new.label_a" args="['Subsistema']" /></a></li>
            </ul>
        </div>
        <div id="show-maquina" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
             <div class="content">
                <div class="row">
                    <div class="col-xs-8">
                        <fieldset class="form" style="padding-bottom:0px;">

                           %{--
							<f:display bean="maquina" />
							--}%

							<f:with bean="maquina">
								<ol class="property-list maquina">

									<li class="fieldcontain">
										<span id="archivo_Foto-label" class="property-label"><g:message code="maquina.archivo_Foto.label" default="Foto de la Máquina" /></span>
										<div class="property-value" aria-labelledby="archivo_Foto-label">
											<g:if test="${maquina.archivo_Foto}">
												<img src="${request.contextPath}/assets/mes-icons/<f:display property='archivo_Foto'/>" style="width:300px;height:200px;"/>
											</g:if>
											<g:else>
												<img src="${request.contextPath}/assets/mes-icons/default.png" style="width:300px;height:200px;"/>
											</g:else>
										</div>
									</li>

									<li class="fieldcontain">
										<span id="planta-label" class="property-label"><g:message code="maquina.linea.planta.label" default="Planta" /></span>
										<div class="property-value" aria-labelledby="planta-label"><f:display property="linea.planta"/></div>
									</li>

									<li class="fieldcontain">
										<span id="linea-label" class="property-label"><g:message code="maquina.linea.label" default="Línea de producción" /></span>
										<div class="property-value" aria-labelledby="linea-label"><f:display property="linea"/></div>
									</li>

									<li class="fieldcontain">
										<span id="nombre-label" class="property-label"><g:message code="maquina.nombre.label" default="Nombre" /></span>
										<div class="property-value" aria-labelledby="nombre-label"><f:display property="nombre"/></div>
									</li>

									<li class="fieldcontain">
										<span id="descripcion-label" class="property-label"><g:message code="maquina.descripcion.label" default="Descripción" /></span>
										<div class="property-value" aria-labelledby="descripcion-label"><f:display property="descripcion"/></div>
									</li>
									<!--
									<li class="fieldcontain">
										<span id="fecha_Registro-label" class="property-label"><g:message code="maquina.dateCreated.label" default="Fecha de registro" /></span>
										<div class="property-value" aria-labelledby="fecha_Registro-label"><f:display property="dateCreated"/></div>
									</li>
									<li class="fieldcontain">
										<span id="fecha_Cambio-label" class="property-label"><g:message code="maquina.lastUpdated.label" default="Última actualización" /></span>
										<div class="property-value" aria-labelledby="fecha_Cambio-label"><f:display property="lastUpdated"/></div>
									</li>
									<li class="fieldcontain">
										<span id="id_Autor-label" class="property-label"><g:message code="maquina.id_Autor.label" default="Autor" /></span>
										<div class="property-value" aria-labelledby="id_Autor-label"><f:display property="id_Autor"/></div>
									</li>
									-->

								</ol>
							</f:with>
                        </fieldset>
                    </div>
                    <div class="col-xs-4">
                        <qrcode:image height="410" width="410" text="${maquina.qrCode}" name="qr_cdx" id="qr_cdx"/>
                    </div>

							<div class="content scaffold-list" id="list-subsistema" role="main">
								<table>
									<thead>
										<tr>
											<g:sortableColumn property="archivo_Foto" title="${message(code: 'subsistema.archivo_Foto.label', default: 'Photo')}" />
											<g:sortableColumn property="nombre" title="${message(code: 'subsistema.nombre.label', default: 'Name')}" />
											<g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
										</tr>
									</thead>

									<tbody>
										<g:each in="${subsistemas}" var="item" status="i">
											<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
												<td style="width:100px;">
													<g:if test="${item.archivo_Foto}">
													<center>
														<a href="javascript:showImage('${item.nombre.replaceAll("'", "").trim()}','${item.archivo_Foto}');"><img src="${request.contextPath}/assets/mes-icons/${item.archivo_Foto}" border="0px" Style="width:100px;height:70px;"/></a>
													</center>
													</g:if>
												</td>
												<td><g:link method="GET" resource="${item}">${item.nombre}</g:link></br><span style="color:#515151;font-size:12px">${item.descripcion}</span></td>
												<td style="width:100px;">
													<center>
														<a class="operations_icon red" onclick="jremove_subsistema('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[item.nombre])}','${item.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
													</center>
												</td>
											</tr>
										</g:each>
										<g:if test="${subsistemaCount == 0}">
											<tr>
												<td colspan="3"><g:message code="default.table.empty.label"/></td>
											</tr>
										</g:if>
									</tbody>
								</table>
								<div class="pagination" style="-moz-border-radius: 0em 0em 0em 0em; -webkit-border-radius: 0em 0em 0em 0em; border-radius: 0em 0em 0em 0em;">
									<g:paginate total="${subsistemaCount ?: 0}" />
								</div>
							</div>
                </div>
            </div>
            <g:form resource="${this.maquina}" method="DELETE" name="delete_form">
                <fieldset class="buttons">
					<g:link name="footer-edit" class="edit" action="edit" resource="${this.maquina}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link name="footer-delete" class="delete" url="javascript:void(0)" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
					<g:link name="footer-print" class="print" url="javascript:void(0)" onclick="javascript:print_qr()"><g:message code="default.button.print_qr.label" default="Print QR" /></g:link>
                    <g:link name="footer-close" class="close" action="show" controller="linea" resource="${maquina.linea}"><g:message code="default.button.close.label" default="Close" /></g:link>
                    <g:link name="footer-create" class="create" action="create" controller="subsistema" params="[maquina: "${this.maquina.id}"]" style="float:right"><g:message code="default.new.label_a" args="['Subsistema']" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
