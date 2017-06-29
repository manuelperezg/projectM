<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'punto.label', default: 'Punto')}" />
        <g:set var="entitiesName" value="${message(code: 'punto.label', default: 'Puntos')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script language="javascript">
			$(document).ready(function(e){

				$("#filter_verificacion").select2().change(function(){
					if ($("#search_word").val() == ""){
						$("#search_clean").val("1");
					}
					$( "#search_form" ).submit();
				});

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
			  <li class="active"><g:message code="default.list.label" args="[entitiesName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
		<a href="#list-punto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
            <div class="col-xs-8" style="white-space: nowrap; float:right; padding: 0px; margin-right:3px;">
				<g:form action="index" method="POST" name="search_form">
					<div class="input-group filter-panel" style="text-align:right;width:40%;display:inline-block;top:-17px">
						<g:select optionKey="id" optionValue="nombre" name="filter_verificacion" from="${verificaciones}" noSelection="['ALL':'Todas las verificaciones']" value="${session.getAttribute('search_filter_verificacion')}"/>
					</div>
					<div style="width:60%;display:inline-block">
						<div class="input-group" style="display:table;">
							<div class="input-group-btn search-panel">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									<span id="search_concept">${session.getAttribute('punto_search_rows')} Registros</span> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
								  <li><a href="#10">10 Registros</a></li>
								  <li><a href="#20">20 Registros</a></li>
								  <li><a href="#50">50 Registros</a></li>
								  <li><a href="#100">100 Registros</a></li>
								</ul>
							</div>
							<input type="hidden" name="search_rows" id="search_rows" value="${session.getAttribute('punto_search_rows')}">
							<input type="hidden" name="search_clean" id="search_clean" value="0">
							<input type="text" class="form-control" name="search_word" id="search_word" placeholder="Palabra a buscar..." value="${session.getAttribute('punto_search_word')}">
							<span class="input-group-btn">
								<button id="btn-search" class="btn btn-primary" type="button"><li class="fa fa-search fa-lg"></li></button>
							</span>
						</div>
					</div>
				</g:form>
			</div>
        </div>
        <div id="list-punto" class="content scaffold-list" role="main" style="overflow: auto;">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<table>
                <thead>
                    <tr>
						<g:sortableColumn property="punto" title="${message(code: 'punto.punto.label', default: 'Points')}"  style="width:30%" />
                        <g:sortableColumn property="foto_malo" title="${message(code: 'punto.foto_malo.label', default: 'Bad Photo')}" style="width:100px;" />
                        <g:sortableColumn property="foto_bueno" title="${message(code: 'punto.foto_bueno.label', default: 'Good Photo')}" style="width:100px;" />
                        <g:sortableColumn property="actividad" title="${message(code: 'punto.actividad.label', default: 'Activity')}"  style="width:30%" />
                        <g:sortableColumn property="operations" title="Operaciones" style="text-align:center;width:100px" />
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${puntoList}" var="item" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${item.punto}.&nbsp;<g:link method="GET" resource="${item}">${item.nombre}</g:link></td>
                            <td>
								<g:if test="${item.foto_malo}">
								<center>
									<a href="javascript:showImage('Malo','${item.foto_malo}');"><img src="${request.contextPath}/assets/mes-icons/${item.foto_malo}" border="0px" width="100px"/></a>
								</center>
								</g:if>
							</td>
							<td>
								<g:if test="${item.foto_bueno}">
								<center>
									<a href="javascript:showImage('Bueno','${item.foto_bueno}');"><img src="${request.contextPath}/assets/mes-icons/${item.foto_bueno}" border="0px" width="100px"/></a>
								</center>
								</g:if>
							</td>
                            <td>${item.actividad}</td>
                            <td>
                                <center>
                                <a class="operations_icon red" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[item.nombre])}','${item.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
                                </center>
                            </td>
                        </tr>
                    </g:each>
                    <g:if test="${puntoCount == 0}">
						<tr>
							<td colspan="5"><g:message code="default.table.empty.label"/></td>
						</tr>
                    </g:if>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${puntoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
