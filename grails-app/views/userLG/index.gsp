<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLG.label', default: 'Usuario')}" />
        <g:set var="entitiesName" value="${message(code: 'userLG.label', default: 'Usuarios')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script language="javascript">
			$(document).ready(function(e){
				$(".search-panel .dropdown-menu").find("a").click(function(e) {
					e.preventDefault();
					var param_max = $(this).attr("href").replace("#","");
					var concept = $(this).text();
					$(".search-panel span#search_concept").text(concept);
					$("#search_rows").val(param_max);
					if ($("#search_word").val() == ""){
						$("#search_clean").val("1");
					}
					$( "#search_form" ).submit();
				});
				$("#btn-search").click(function(e) {
					if ($("#search_word").val() == ""){
						$("#search_clean").val("1");
					}
					$( "#search_form" ).submit();
				});
				$("#search_word").on("keydown", function (e) {
					if (e.keyCode == 13) {
						if ($("#search_word").val() == ""){
							$("#search_clean").val("1");
						}
						$( "#search_form" ).submit();
					}
				});
				/*
				$("#search_word").donetyping(function(){
					if ($("#search_word").val() == ""){
						$("#search_clean").val("1");
					}
					$( "#search_form" ).submit();
				});
				*/
				$("#search_word").focus();
			});

            function jconfirm(pregunta,mensaje,id){
                swal({
                    title: pregunta,
                    text: mensaje,
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#DA291C',
                    confirmButtonText: 'Si, eliminar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
                     location.href = "${request.contextPath}/userLG/remove/"+id;
                });
            }

            function restart(id){
                swal({
                    title: '<g:message code="default.restart.ask.message" default="Do you want continue?"/>',
                    text: '<g:message code="default.restart.info.message" default="The password will be restart using the nick name"/>',
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#FFD100',
                    confirmButtonText: 'Si, continuar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
					location.href = "${request.contextPath}/userLG/restart/"+id;
                });
            }

            function showImage(nombre, archivo){
				swal({
				  title: nombre,
				  html:
					'<img src="${request.contextPath}/assets/mes-users/'+archivo+'" border="0px" style="width:250px; height:300px"/>',
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
			  <li class="active"><g:message code="default.list.label" args="[entitiesName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#list-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<div class="col-xs-6 col-xs-offset-2" style="float:right;padding: 0px;">
					<g:form action="index" method="POST" name="search_form">
						<div class="input-group">
							<div class="input-group-btn search-panel">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									<span id="search_concept">${session.getAttribute('userLG_search_rows')} Registros</span> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
								  <li><a href="#10">10 Registros</a></li>
								  <li><a href="#20">20 Registros</a></li>
								  <li><a href="#50">50 Registros</a></li>
								  <li><a href="#100">100 Registros</a></li>
								</ul>
							</div>
							<input type="hidden" name="search_rows" id="search_rows" value="${session.getAttribute('userLG_search_rows')}">
							<input type="hidden" name="search_clean" id="search_clean" value="0">
							<input type="text" class="form-control" name="search_word" id="search_word" placeholder="Palabra a buscar..." value="${session.getAttribute('userLG_search_word')}">
							<span class="input-group-btn">
								<button id="btn-search" class="btn btn-primary" type="button"><li class="fa fa-search fa-lg"></li></button>
							</span>
						</div>
					</g:form>
				</div>
			</ul>
        </div>
        <div id="list-userLG" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<!--
            <f:table collection="${userLGList}" properties="['username', 'accountLocked', 'accountExpired', 'passwordExpired', 'enabled']"/>
			-->
            <table >
                <thead>
                    <tr>
						<g:sortableColumn property="archivo_Foto" title="${message(code: 'userLG.archivo_Foto.label', default: 'Photo')}" />
                        <g:sortableColumn property="nombre" title="Nombre" />
                        <g:sortableColumn property="username" title="Usuario" />
                        <g:sortableColumn property="email" title="Correo electrónico" />
                        <g:sortableColumn property="telefono_Movil" title="Teléfono móvil" />
                        <g:sortableColumn property="enabled" title="Activo" />
                        <g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${userLGList}" var="user" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td style="width:100px;">
								<g:if test="${user.archivo_Foto}">
									<center>
										<a href="javascript:showImage('${user.nombre.replaceAll("'", "").trim()}','${user.archivo_Foto}');"><img src="${request.contextPath}/assets/mes-users/${user.archivo_Foto}" border="0px" style="width:100px; height:100px"/></a>
									</center>
								</g:if>
								<g:else>
									<center>
										<a href="javascript:showImage('${user.nombre.replaceAll("'", "").trim()}','default.png');"><img src="${request.contextPath}/assets/mes-users/default.png" border="0px" style="width:100px; height:100px"/></a>
									</center>
								</g:else>
							</td>
                            <td><g:link method="GET" resource="${user}">${user.nombre}</g:link></br><span style="color:#515151;font-size:12px">${user.getRolName()}</span></td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.telefono_Movil}</td>
                            <td>${(user.enabled)? '' : raw('<b><font color="red">')}${user.enabled}${(user.enabled)? '' : raw('</font></b>')}</td>
                            <td>
								<center>
									<a class="operations_icon" onclick="restart('${user.id}')" title="¡Reestablecer contraseña!"><li class="fa fa-key"></li></a>
									<g:link class="operations_icon" action="active" resource="${user}" title="${(user.enabled)? '¡Desactivar!' : '¡Activar!'}"><li class="fa fa-check-square-o"></li></g:link>
									<a class="operations_icon red" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[user.username])}','${user.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
								</center>
							</td>
                        </tr>
                    </g:each>
                    <g:if test="${userLGCount == 0}">
						<tr>
							<td colspan="7"><g:message code="default.table.empty.label" /></td>
						</tr>
                    </g:if>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${userLGCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
