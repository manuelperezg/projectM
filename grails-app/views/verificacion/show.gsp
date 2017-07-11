<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'verificacion.label', default: session.getAttribute('title'))}" />
        <g:set var="entitiesName" value="${message(code: 'verificacion.label', default: session.getAttribute('title'))}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script language="javascript">
			function changeTab(opcion){
				url = "${request.contextPath}/verificacion/changeTab?option="+opcion;
				$("#cmd_space")[0].src = url;
			}

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
                     location.href = "${request.contextPath}/punto/remove/"+id;
                });
            }

            function jremove_estandar(pregunta,mensaje,id){
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
                     location.href = "${request.contextPath}/estandar/remove/"+id;
                });
            }

            function go(id_tag){
				$('[name="footer-'+id_tag+'"]')[0].click();
			}

			function showImage(nombre, archivo){
				swal({
				  title: nombre,
				  html:
					'<img src="${request.contextPath}/assets/mes-icons/'+archivo+'" border="0px" width="500px"/>',
				  showCloseButton: true,
				  confirmButtonText:
					'<i class="fa fa-thumbs-up"></i> Aceptar'
				})
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
			  <li><g:link action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
			  <li class="active">${verificacion.nombre}</li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-verificacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><a href="javascript:go('edit')"		class="edit"><g:message code="default.button.edit.label" default="Edit" /></a></li>
                <li><a href="javascript:go('delete')"	class="delete"><g:message code="default.button.delete.label" default="Delete" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="Close" /></a></li>
                <li style="float:right;margin:0px;"><a href="javascript:go('create-estandar')"	class="create"><g:message code="default.new.label" args="['Documento']" default="New document"/></a></li>
                <li style="float:right;margin:0px;"><a href="javascript:go('create-punto')"	class="create"><g:message code="default.new.label" args="['Punto']" default="New point"/></a></li>
            </ul>
        </div>
        <div id="show-verificacion" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--
            <f:display bean="verificacion" />
            --}%

            <f:with bean="verificacion">
				<ol class="property-list verificacion">
                    <li class="fieldcontain">
                        <span id="nombre-label" class="property-label"><strong><g:message code="verificacion.nombre.label" default="Nombre" /></strong></span>
                        <div class="property-value" aria-labelledby="nombre-label"><strong><f:display property="nombre"/></strong></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="maquina-label" class="property-label"><g:message code="verificacion.maquina.label" default="Machine" /></span>
                        <div class="property-value" aria-labelledby="maquina-label"><f:display property="maquina"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="mantenimiento-label" class="property-label"><g:message code="verificacion.mantenimiento.label" default="Mtto Type" /></span>
                        <div class="property-value" aria-labelledby="mantenimiento-label"><f:display property="mantenimiento"/></div>
                    </li>
                    <li class="fieldcontain">
                        <span id="tipo-label" class="property-label"><g:message code="verificacion.tipo.label" default="Mtto Type" /></span>
                        <div class="property-value" aria-labelledby="tipo-label"><f:display property="tipo"/></div>
                    </li>
                </ol>
            </f:with>
            <div class="tabs" style="width:98%;margin:auto auto;">
				<ul class="nav nav-tabs">
					<li <g:if test="${session.getAttribute('verificacion_tab') == 1}">class="active"</g:if> >
						<a href="#list-frecuencia" data-toggle="tab" onclick="javascript:changeTab(1)"><i class="fa fa-calendar-check-o "></i> <g:message code="verificacion.tab.calendario.label" default="Inspection schedule" /></a>
					</li>
					<li <g:if test="${session.getAttribute('verificacion_tab') == 2}">class="active"</g:if> >
						<a href="#list-punto-materiales" data-toggle="tab" onclick="javascript:changeTab(2)"><i class="fa fa-search-plus"></i> <g:message code="verificacion.tab.estandar_materiales.label" default="Standard with materials" /></a>
					</li>
					<li <g:if test="${session.getAttribute('verificacion_tab') == 3}">class="active"</g:if> >
						<a href="#list-punto" data-toggle="tab" onclick="javascript:changeTab(3)"><i class="fa fa-search"></i> <g:message code="verificacion.tab.estandar_normal.label" default="Standard of maintenance" /></a>
					</li>
					<li <g:if test="${session.getAttribute('verificacion_tab') == 4}">class="active"</g:if> >
						<a href="#list-estandares" data-toggle="tab" onclick="javascript:changeTab(4)"><i class="fa fa-graduation-cap"></i> <g:message code="verificacion.tab.estandar_consulta.label" default="Standard of consultation" /></a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane <g:if test="${session.getAttribute('verificacion_tab') == 1}">active</g:if> fade in content scaffold-list" id="list-frecuencia" role="main" style="overflow: auto;">
						<table style="width:100%">
							<thead>
								<tr>
									<g:sortableColumn property="foto_bueno" title="${message(code: 'punto.foto.calendario.label', default: 'Image')}" style="width:100px;" />
									<g:sortableColumn property="conjunto" title="${message(code: 'punto.conjunto.label', default: 'Object')}" style="width:100px;" />
									<g:sortableColumn property="punto" title="${message(code: 'punto.punto.label', default: 'Points')}"  style="width:30px;" />
									<g:sortableColumn property="nombre" title="${message(code: 'punto.ubicacion.label', default: 'Location')}"  style="width:30%" />
									<g:sortableColumn property="dia_semana" title="${message(code: 'punto.frecuencia.label', default: 'Frecuency')}"  style="width:100px;" />
									<th style="width:60%" class="sortable"><a href="#">Período</a></th>
									<g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
								</tr>
							</thead>
							<tbody>
								<g:each in="${puntos}" var="item" status="i">
									<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
										<td>
											<g:if test="${item.foto_bueno}">
											<center>
												<a href="javascript:showImage('Bueno','${item.foto_bueno}');"><img src="${request.contextPath}/assets/mes-icons/${item.foto_bueno}" border="0px" width="100px"/></a>
											</center>
											</g:if>
										</td>
										<td>${item.conjunto}</td>
										<td>${item.punto}</td>
										<td>
										<g:link method="GET" resource="${item}">${item.nombre}</g:link>
										</td>
										<td>${item.frecuencia.replaceAll("Una vez por", "X").trim()}</br>
										<g:if test="${item.frecuencia == 'Una vez por día'}">${item.horario}</g:if>
										<g:if test="${item.frecuencia == 'Una vez por semana'}">${item.dia_semana}</g:if>
										<g:if test="${item.frecuencia == 'Una vez por bimestre'||item.frecuencia == 'Una vez por trimestre'||item.frecuencia == 'Una vez por año'}">${item.mes}</g:if>
										</td>
										<td>
										<g:if test="${item.frecuencia == 'Una vez por turno'}">
											<g:each in="${1..4}" var="r" ><g:each in="${1..3}" var="c" ><div class="calendar_item ${(item.turno=='Matutino'&&c==1?'programed':(item.turno=='Vespertino'&&c==2?'programed':(item.turno=='Nocturno'&&c==3?'programed':'empty')))}">T${c}</div></g:each></g:each>
										</g:if>
										<g:if test="${item.frecuencia == 'Una vez por día'}">
											<g:each in="${1..12}" var="c" ><div class="calendar_item programed" style="<g:if test='${c == 2}'>color:#DA291C;font-weight:bold;</g:if>">D${new Date().plus(c-2).format('dd')}</div></g:each>
										</g:if>
										<g:if test="${item.frecuencia == 'Una vez por semana'}">
											<g:each in="${1..12}" var="c" ><div class="calendar_item programed" style="<g:if test='${c == 2}'>color:#DA291C;font-weight:bold;</g:if>">S${new Date().plus((7*(c-2))).toCalendar().get(Calendar.WEEK_OF_YEAR)-1}</div></g:each>
										</g:if>
										<g:if test="${item.frecuencia == 'Una vez por mes'}">
											<g:each in="${1..12}" var="c" ><div class="calendar_item programed" style="<g:if test='${c == new Date().toCalendar().get(Calendar.MONTH)+1}'>color:#DA291C;font-weight:bold;</g:if>">M${c}</div></g:each>
										</g:if>
										<g:set var="mes_numero" value="${item.mes == 'Enero'?1:item.mes == 'Febrero'?2:item.mes == 'Marzo'?3:item.mes == 'Abril'?4:item.mes == 'Mayo'?5:item.mes == 'Junio'?6:item.mes == 'Julio'?7:item.mes == 'Agosto'?8:item.mes == 'Septiembre'?9:item.mes == 'Octubre'?10:item.mes == 'Noviembre'?11:item.mes == 'Diciembre'?12:0}" />
										<g:if test="${item.frecuencia == 'Una vez por bimestre'}">
											<g:each in="${1..12}" var="c" ><div class="calendar_item ${(c == mes_numero || c == mes_numero+2 || c == mes_numero+4 || c == mes_numero+6 || c == mes_numero+8 || c == mes_numero+10 ?'programed':'empty')}" style="<g:if test='${c == (new Date().toCalendar().get(Calendar.MONTH)+1)}'>color:#DA291C;font-weight:bold;</g:if>">M${c}</div></g:each>
										</g:if>
										<g:if test="${item.frecuencia == 'Una vez por trimestre'}">
											<g:each in="${1..12}" var="c" ><div class="calendar_item ${(c == mes_numero || c == mes_numero+3 || c == mes_numero+6 || c == mes_numero+9?'programed':'empty')}" style="<g:if test='${c == (new Date().toCalendar().get(Calendar.MONTH)+1)}'>color:#DA291C;font-weight:bold;</g:if>">M${c}</div></g:each>
										</g:if>
										<g:if test="${item.frecuencia == 'Una vez por año'}">
											<g:each in="${1..12}" var="c" ><div class="calendar_item ${(c == mes_numero?'programed':'empty')}" style="<g:if test='${c == (new Date().toCalendar().get(Calendar.MONTH)+1)}'>color:#DA291C;font-weight:bold;</g:if>">M${c}</div></g:each>
										</g:if>
										</td>
										<td>
											<center>
												<a class="operations_icon red" onclick="jremove_punto('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[item.nombre])}','${item.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
											</center>
										</td>
									</tr>
								</g:each>
								<g:if test="${puntoCount == 0}">
									<tr>
										<td colspan="7"><g:message code="default.table.empty.label"/></td>
									</tr>
								</g:if>
							</tbody>
						</table>

						<div class="pagination" style="width:100%;-moz-border-radius: 0em 0em 0em 0em; -webkit-border-radius: 0em 0em 0em 0em; border-radius: 0em 0em 0em 0em;">
							<g:paginate total="${puntoCount ?: 0}" />
						</div>
					</div>

					<div class="tab-pane <g:if test="${session.getAttribute('verificacion_tab') == 2}">active</g:if> fade in content scaffold-list" id="list-punto-materiales" role="main" style="overflow: auto;">
						<table style="width:100%">
							<thead>
								<tr>
									<g:sortableColumn property="punto" title="${message(code: 'punto.punto.label', default: 'Points')}"  style="width:30px;" />
									<g:sortableColumn property="actividad" title="${message(code: 'punto.actividad.label', default: 'Activity')}"  style="width:30%" />
									<g:sortableColumn property="foto_bueno" title="${message(code: 'punto.foto.calendario.label', default: 'Image')}" style="width:100px;" />
									<g:sortableColumn property="responsable" title="${message(code: 'punto.responsable.label', default: 'Responsable')}"  style="width:10%" />
									<g:sortableColumn property="dia_semana" title="${message(code: 'punto.frecuencia.label', default: 'Frecuency')}"  style="width:100px;" />
									<g:sortableColumn property="estado" title="Estado"  style="width:100px;" />
									<g:sortableColumn property="tiempo" title="${message(code: 'punto.tiempo.label', default: 'Time')}"  style="width:100px;" />
									<g:sortableColumn property="descripcion" title="${message(code: 'punto.descripcion.label', default: 'Indications')}"  style="width:60%" />
									<g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
								</tr>
							</thead>
							<tbody>
								<g:each in="${puntos}" var="item" status="i">
									<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
										<td>${item.punto}</td>
										<td>
										<g:link method="GET" resource="${item}">${item.actividad}</g:link>
										</td>
										<td>
											<g:if test="${item.foto_bueno}">
											<center>
												<a href="javascript:showImage('Bueno','${item.foto_bueno}');"><img src="${request.contextPath}/assets/mes-icons/${item.foto_bueno}" border="0px" width="100px"/></a>
											</center>
											</g:if>
										</td>
										<td>${item.responsable}</td>
										<td>${item.frecuencia.replaceAll("Una vez por", "X").trim()}</br>
										<g:if test="${item.frecuencia == 'Una vez por turno'}">${item.turno}</g:if>
										<g:if test="${item.frecuencia == 'Una vez por día'}">${item.horario}</g:if>
										<g:if test="${item.frecuencia == 'Una vez por semana'}">${item.dia_semana}</g:if>
										<g:if test="${item.frecuencia == 'Una vez por trimestre'||item.frecuencia == 'Una vez por año'}">${item.mes}</g:if>
										</td>
										<td>${item.estado}</td>
										<td>${item.tiempo}</td>
										<td>${item.descripcion}</td>
										<td>
											<center>
												<a class="operations_icon red" onclick="jremove_punto('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[item.nombre])}','${item.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
											</center>
										</td>
									</tr>
									<g:if test="${item.materiales}">
									<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
										<td colspan="2" style="font-size:12px;text-align:right;vertical-align:middle;">Listado de materiales requeridos</td>
										<td colspan="8" style="text-align:left;">
									<g:each in="${item.materiales}" var="itemMaterial" status="c">
											<div style="width:110px;height:40px;text-align:center;display:inline-block;">
												<span style="font-size:8px;line-height: normal">${itemMaterial.categoria}: ${itemMaterial.nombre}</span></br>
												<img src="${request.contextPath}/assets/mes-icons/${itemMaterial.archivo_Foto}" style="width:30px;height:30px;">
											</div>
									</g:each>
										</td>
									</tr>
									</g:if>
								</g:each>
								<g:if test="${puntoCount == 0}">
									<tr>
										<td colspan="10"><g:message code="default.table.empty.label"/></td>
									</tr>
								</g:if>
							</tbody>
						</table>

						<div class="pagination" style="width:100%;-moz-border-radius: 0em 0em 0em 0em; -webkit-border-radius: 0em 0em 0em 0em; border-radius: 0em 0em 0em 0em;">
							<g:paginate total="${puntoCount ?: 0}" />
						</div>
					</div>

					<div class="tab-pane <g:if test="${session.getAttribute('verificacion_tab') == 3}">active</g:if> fade in content scaffold-list" id="list-punto" role="main" style="overflow: auto;">
						<table style="width:100%">
							<thead>
								<tr>
									<g:sortableColumn property="punto" title="${message(code: 'punto.punto_nombre.label', default: 'Points')}"  style="width:25%" />
									<g:sortableColumn property="foto_bueno" title="${message(code: 'punto.foto_bueno.label', default: 'Good Photo')}" style="width:100px;" />
									<g:sortableColumn property="foto_malo" title="${message(code: 'punto.foto_malo.label', default: 'Bad Photo')}" style="width:100px;" />
                                    <g:sortableColumn property="actividad" title="${message(code: 'punto.actividad.label', default: 'Activity')}"  style="width:25%" />
									<g:sortableColumn property="responsable" title="${message(code: 'punto.responsable.label', default: 'Responsable')}"  style="width:10%" />
									<g:sortableColumn property="frecuencia" title="${message(code: 'punto.frecuencia.label', default: 'Frecuency')}"  style="width:10%" />
									<g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
								</tr>
							</thead>
							<tbody>
								<g:each in="${puntos}" var="item" status="i">
									<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
										<td>${item.punto}.&nbsp;<g:link method="GET" resource="${item}">${item.nombre}</g:link></td>
										<td>
											<g:if test="${item.foto_bueno}">
											<center>
												<a href="javascript:showImage('Bueno','${item.foto_bueno}');"><img src="${request.contextPath}/assets/mes-icons/${item.foto_bueno}" border="0px" width="100px"/></a>
											</center>
											</g:if>
										</td>
                                        <td>
                                            <g:if test="${item.foto_malo}">
                                            <center>
                                                <a href="javascript:showImage('Malo','${item.foto_malo}');"><img src="${request.contextPath}/assets/mes-icons/${item.foto_malo}" border="0px" width="100px"/></a>
                                            </center>
                                            </g:if>
                                        </td>
										<td>${item.actividad}</td>
										<td>${item.responsable}</td>
										<td>${item.frecuencia}</td>
										<td>
											<center>
												<a class="operations_icon red" onclick="jremove_punto('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[item.nombre])}','${item.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
											</center>
										</td>
									</tr>
								</g:each>
								<g:if test="${puntoCount == 0}">
									<tr>
										<td colspan="7"><g:message code="default.table.empty.label"/></td>
									</tr>
								</g:if>
							</tbody>
						</table>

						<div class="pagination" style="width:100%;-moz-border-radius: 0em 0em 0em 0em; -webkit-border-radius: 0em 0em 0em 0em; border-radius: 0em 0em 0em 0em;">
							<g:paginate total="${puntoCount ?: 0}" />
						</div>
					</div>
					<div id="list-estandares" class="tab-pane <g:if test="${session.getAttribute('verificacion_tab') == 4}">active</g:if> fade in content scaffold-list" role="main" style="overflow: auto;">
						<table style="width:100%">
							<thead>
								<tr>
									<g:sortableColumn property="nombre" title="Estándar" />
									<g:sortableColumn property="tipo_archivo" title="Tipo de archivo" />
									<g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
								</tr>
							</thead>

							<tbody>
								<g:each in="${estandares}" var="item" status="i">
									<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
										<td><g:link method="GET" resource="${item}">${item.nombre}</g:link></td>
										<td>${item.tipo_archivo}</td>
										<td>
											<center>
												<g:if test="${item.archivo}">
													<a class="operations_icon blue" href="${request.contextPath}/assets/mes-files/${item.archivo}" target="_blank" title="Descargar archivo"><li class="fa fa-download"></li></a>
												</g:if>
												<g:else>
													 <a class="operations_icon gray" href="#" title="¡Sin archivo!"><li class="fa fa-download"></li></a>
												</g:else>
												<a class="operations_icon red" onclick="jremove_estandar('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[item.nombre])}','${item.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
											</center>
										</td>
									</tr>
								</g:each>
								<g:if test="${estandarCount == 0}">
									<tr>
										<td colspan="3"><g:message code="default.table.empty.label"/></td>
									</tr>
								</g:if>
							</tbody>
						</table>

						<div class="pagination"  style="width:100%;-moz-border-radius: 0em 0em 0em 0em; -webkit-border-radius: 0em 0em 0em 0em; border-radius: 0em 0em 0em 0em;">
							<g:paginate total="${estandarCount ?: 0}" />
						</div>
					</div>
				</div>
			</div>

            <iframe id="cmd_space" name="cmd_space" src="" style="visibility:hidden;width:0px;height:0px;position:absolute">
            </iframe>

            <g:form resource="${this.verificacion}" method="DELETE" name="delete_form">
				<fieldset class="buttons">
					<g:link name="footer-edit" class="edit" action="edit" resource="${this.verificacion}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link name="footer-delete" class="delete" url="javascript:void(0)" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info.message', default: 'You are deleting the record.')}');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
                    <g:link name="footer-close" class="close" action="index"><g:message code="default.button.close.label" default="Close" /></g:link>
                    <g:link name="footer-create-estandar" class="create" action="create" controller="estandar" params="[verificacion: "${this.verificacion.id}"]" style="float:right"><g:message code="default.new.label" args="['Documento']" /></g:link>
                    <g:link name="footer-create-punto" class="create" action="create" controller="punto" params="[verificacion: "${this.verificacion.id}"]" style="float:right"><g:message code="default.new.label" args="['Punto']" /></g:link>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
