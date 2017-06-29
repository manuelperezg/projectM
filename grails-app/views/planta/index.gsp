<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'planta.label', default: 'Planta')}" />
        <g:set var="entitiesName" value="${message(code: 'planta.label', default: 'Plantas')}" />
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
                     location.href = "${request.contextPath}/planta/remove/"+id;
                });
            }

            function showImage(nombre, archivo){
				swal({
				  title: nombre,
				  html:
					"<img src=\"${request.contextPath}/assets/mes-icons/"+archivo+"\" border=\"0px\" style=\"width:500px; height:300px;\"/>",
				  showCloseButton: true,
				  confirmButtonText:
					"<i class=\"fa fa-thumbs-up\"></i> Aceptar"
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
        <a href="#list-planta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label_a" args="[entityName]" /></g:link></li>
            </ul>
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
        </div>
        <div id="list-planta" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--
            <f:table collection="${plantaList}" />
            -->
			<table >
                <thead>
                    <tr>
						<g:sortableColumn property="archivo_Foto" title="${message(code: 'planta.archivo_Foto.label', default: 'Photo')}" />
						<g:sortableColumn property="archivo_Esquema" title="${message(code: 'planta.archivo_Esquema.label', default: 'Schema')}" />
                        <g:sortableColumn property="nombre" title="${message(code: 'planta.nombre.label', default: 'Name')}" />
                        <g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${plantaList}" var="planta" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td style="width:100px;">
								<g:if test="${planta.archivo_Foto}">
								<center>
									<a href="javascript:showImage('${planta.nombre.replaceAll("'", "").trim()}','${planta.archivo_Foto}');"><img src="${request.contextPath}/assets/mes-icons/${planta.archivo_Foto}" border="0px" Style="width:100px;height:70px;"/></a>
								</center>
								</g:if>
							</td>
							<td style="width:100px;">
								<g:if test="${planta.archivo_Esquema}">
								<center>
									<a href="javascript:showImage('${planta.nombre.replaceAll("'", "").trim()}','${planta.archivo_Esquema}');"><img src="${request.contextPath}/assets/mes-icons/${planta.archivo_Esquema}" border="0px" Style="width:100px;height:70px;"/></a>
								</center>
								</g:if>
							</td>
							<td><g:link method="GET" resource="${planta}">${planta.nombre}</g:link></br><span style="color:#515151;font-size:12px">${planta.direccion}</span></td>
                            <td>
                                <center>
									<a class="operations_icon red" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[planta.nombre])}','${planta.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
                                </center>
                            </td>
                        </tr>
                    </g:each>
                    <g:if test="${plantaCount == 0}">
						<tr>
							<td colspan="4"><g:message code="default.table.empty.label"/></td>
						</tr>
                    </g:if>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${plantaCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
