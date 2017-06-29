 <!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'maquina.label', default: 'Máquina')}" />
        <g:set var="entitiesName" value="${message(code: 'maquina.label', default: 'Máquinas')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script language="javascript">
			$(document).ready(function(e){
			
				$("#filter_planta").select2().change(function(){
					if ($("#search_word").val() == ""){
						$("#search_clean").val("1");
					}
					$( "#search_form" ).submit();
				});
				
				$("#filter_linea").select2().change(function(){
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
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#DA291C',
                    confirmButtonText: 'Si, eliminar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
                     location.href = "/maquina/remove/"+id;
                });
            }
			
			function showImage(nombre, archivo){
				swal({
				  title: nombre,
				  html:
					'<img src="${request.contextPath}/assets/mes-icons/'+archivo+'" border="0px" style="width:500px; height:300px;"/>',
				  showCloseButton: true,
				  confirmButtonText:
					'<i class="fa fa-thumbs-up"></i> Aceptar'
				})
			}
			
        </script>
    </head>
    <body>
		<g:render template="/layouts/menu" />
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li class="active"><g:message code="default.list.label" args="[entitiesName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#list-maquina" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label_a" args="[entityName]" /></g:link></li>
            </ul>
            <div class="col-xs-8" style="white-space: nowrap; float:right; padding: 0px; margin-right:3px;">
				<g:form action="index" method="POST" name="search_form">
					<div class="input-group filter-panel" style="text-align:right;width:40%;display:inline-block;top:-17px">
						<g:select optionKey="id" optionValue="nombre" name="filter_planta" from="${plantas}" noSelection="['ALL':'Todas las plantas']" value="${session.getAttribute('search_filter_planta')}"/>
						<g:select optionKey="id" optionValue="nombre" name="filter_linea" from="${lineas}" noSelection="['ALL':'Todas las líneas']" value="${session.getAttribute('search_filter_linea')}"/>
					</div>
					<div style="width:60%;display:inline-block">
						<div class="input-group" style="display:table;">
							<div class="input-group-btn search-panel">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									<span id="search_concept">${session.getAttribute('maquina_search_rows')} Registros</span> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
								  <li><a href="#10">10 Registros</a></li>
								  <li><a href="#20">20 Registros</a></li>
								  <li><a href="#50">50 Registros</a></li>
								  <li><a href="#100">100 Registros</a></li>
								</ul>
							</div>
							<input type="hidden" name="search_rows" id="search_rows" value="${session.getAttribute('maquina_search_rows')}">
							<input type="hidden" name="search_clean" id="search_clean" value="0">
							<input type="text" class="form-control" name="search_word" id="search_word" placeholder="Buscar..." value="${session.getAttribute('maquina_search_word')}">
							<span class="input-group-btn">
								<button id="btn-search" class="btn btn-primary" type="button"><li class="fa fa-search fa-lg"></li></button>
							</span>
						</div>
					</div>
				</g:form>
			</div>
        </div>
        <div id="list-maquina" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<!--
            <f:table collection="${maquinaList}" />
			-->
            <table >
                <thead>
                    <tr>
						<g:sortableColumn property="archivo_Foto" title="${message(code: 'maquina.archivo_Foto.label', default: 'Photo')}" />
                        <g:sortableColumn property="nombre" title="Nombre" />
                        <g:sortableColumn property="operations" title="Operaciones" style="width:100px" />
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${maquinaList}" var="maquina" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td style="width:100px;">
								<g:if test="${maquina.archivo_Foto}">
								<center>
									<a href="javascript:showImage('${maquina.nombre}','${maquina.archivo_Foto}');"><img src="${request.contextPath}/assets/mes-icons/${maquina.archivo_Foto}" border="0px" width="100px"/></a>
								</center>
								</g:if>
							</td>
                            <td><g:link method="GET" resource="${maquina}">${maquina.nombre}</g:link></br><span style="color:#515151;font-size:12px">${maquina.linea.planta}-${maquina.linea}</span></td>
                            <td>
                                <center>
									<a class="operations_icon red" onclick="jconfirm('${message(code: 'default.delete.ask.message', default: 'Are you sure?')}','${message(code: 'default.delete.info_personal.message', default: 'You are deleting the record.', args:[maquina.linea.toString()+"-"+maquina.nombre])}','${maquina.id}');" title="¡Eliminar!"><li class="fa fa-trash"></li></a>
                                </center>
                            </td>
                        </tr>
                    </g:each>
                    <g:if test="${maquinaCount == 0}">
						<tr>
							<td colspan="3"><g:message code="default.table.empty.label"/></td>
						</tr>
                    </g:if>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${maquinaCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
