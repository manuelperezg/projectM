<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'grupos.label', default: 'Grupo')}" />
        <g:set var="entitiesName" value="${message(code: 'grupos.label', default: 'Grupos')}" />
        <title><g:message code="default.list.label" args="[entitiesName]" /></title>
        <script language="javascript">
			$(document).ready(function(e){
				$('.search-panel .dropdown-menu').find('a').click(function(e) {
					e.preventDefault();
					var param_max = $(this).attr("href").replace("#","");
					var concept = $(this).text();
					$('.search-panel span#search_concept').text(concept);
					$('#search_rows').val(param_max);
					if ($('#search_word').val() == ""){
						$('#search_clean').val('1');
					}
					$( "#search_form" ).submit();
				});
				$('#btn-search').click(function(e) {
					if ($('#search_word').val() == ""){
						$('#search_clean').val('1');
					}
					$( "#search_form" ).submit();
				});
				$('#search_word').on('keydown', function (e) {
					console.log( e.keyCode +":" +$('#search_word').val());
					if (e.keyCode == 13) {
						if ($('#search_word').val() == ""){
							$('#search_clean').val('1');
						}
						$( "#search_form" ).submit();
					}
				});
				/*
				$('#search_word').donetyping(function(){
					if ($('#search_word').val() == ""){
						$('#search_clean').val('1');
					}
					$( "#search_form" ).submit();
				});
				*/
				$('#search_word').focus();
			});
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#list-grupos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="import" action="upload"><g:message code="default.import.label" args="[entitiesName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-grupos" class="content scaffold-list" role="main">
            <h1>
				<div style="height:36px;float:left">
				<g:message code="default.list.label" args="[entitiesName]" />
				</div>
				<div class="col-xs-6 col-xs-offset-2" style="float:right;">
					<g:form action="index" method="POST" name="search_form">
						<div class="input-group">
							<div class="input-group-btn search-panel">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									<span id="search_concept">${params.search_rows} Registros</span> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
								  <li><a href="#10">10 Registros</a></li>
								  <li><a href="#20">20 Registros</a></li>
								  <li><a href="#50">50 Registros</a></li>
								  <li><a href="#100">100 Registros</a></li>
								</ul>
							</div>
							<input type="hidden" name="search_rows" id="search_rows" value="${params.search_rows}">
							<input type="hidden" name="search_clean" id="search_clean" value="0">
							<input type="text" class="form-control" name="search_word" id="search_word" placeholder="Palabra a buscar..." value="${params.search_word}">
							<span class="input-group-btn">
								<button id="btn-search" class="btn btn-primary" type="button"><li class="fa fa-search fa-lg"></li></button>
							</span>
						</div>
					</g:form>
				</div>
            </h1>
            <g:if test="${flash.message}">
                <div class="message" role="status" style="float:left;width:97%">${flash.message}</div>
            </g:if>
            <!--
            <f:table collection="${gruposList}" />
			-->
            <table>
                <thead>
                    <tr>
						<!--
                        <g:sortableColumn property="operations" title="Operaciones" />
                        -->
						<g:sortableColumn property="semestre" title="Semestre" />
                        <g:sortableColumn property="nombre" title="Grupo" />
                        <g:sortableColumn property="unidadcurricular" title="Unidad curricular" />
                        <g:sortableColumn property="docente" title="Docente" />
                        <g:sortableColumn property="modalidad" title="Modalidad" />
                        <g:sortableColumn property="horas" title="Horas" />
                        <g:sortableColumn property="cupo" title="Cupo" />
                        <g:sortableColumn property="inscritos" title="Inscritos" />
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${gruposList}" var="grupo" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<!--
                            <td align="center">
                                <g:link action="alumnos" resource="${grupo}"><g:img uri="${request.contextPath}/assets/skin/users.png" title="Gestionar alumnos inscritos"/></g:link>
                            </td>
                            -->
							<td>${grupo.semestre}</td>
                            <td><g:link method="GET" resource="${grupo}">${grupo.nombre}</g:link></td>
                            <td>${(grupo.getMateria())?grupo.getMateria().nombre:raw('<b><font color="red">No identificada</font></b>')}</td>
                            <td>${(grupo.getDocente())?grupo.getDocente().nombre:raw('<b><font color="red">No identificado</font></b>')}</td>
                            <td>${grupo.modalidad}</td>
                            <td>${grupo.horas}</td>
                            <td>${grupo.cupo}</td>
                            <td>${grupo.inscritos}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${gruposCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
