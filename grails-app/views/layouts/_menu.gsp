<script>
	$( document ).ready(function() {
		$('#btn-log').click(function() {
			$('#log_form').submit();
		});
		
		$('#dropdown-login').on('click', function(event){
			// The event won't be propagated up to the document NODE and
			// therefore delegated events won't be fired
			event.stopPropagation();
			event.preventDefault();
		});

		$( "#dropdown-login" ).mouseout(function() {
			console.log( "Handler for .mouseout() called." );
		});
		
		$("#activo_rol").select2();
		
	});
</script>

<header class="header header-two">
  <div class="header-wrapper">
	<div class="container">
	  <div class="row">
		<div class="col-xs-6 col-md-2 col-lg-3 logo-box">
		  <div class="logo">
			<a href="${createLink(uri: '/')}" style="text-decoration: none;color:#333;">
				<img src="${request.contextPath}/assets/unilever_blue.svg" class="logo-img" alt="" style="width:250px">
			</a>
		  </div>
		</div><!-- .logo-box -->

		<div class="col-xs-6 col-md-10 col-lg-9 right-box">
		  <div class="right-box-wrapper">
			<div class="header-icons">
			  <div class="btn-group cart-header">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-delay="2000" data-close-others="false">
					<li class="fa fa-users"></li>
				</a>
				<div class="dropdown-menu" id="dropdown-login">
<sec:ifLoggedIn>
					<div class="label_title" style="font-size:20px;width:100%">
						<li class="fa fa-arrow-right"></li>&nbsp;Bienvenid@
					</div>
					<strong> ${session.getAttribute("username_nombre")}</strong>
					<ul class="list-unstyled">
						<li>
							<g:link class="product-image" controller="userLG" action="myuser">
								<g:if test="${session.getAttribute("username_archivo_Foto")}">
									<img class="replace-2x" src="${request.contextPath}/assets/mes-users/${session.getAttribute("username_archivo_Foto")}" width="70" height="70" alt="">
								</g:if>
								<g:else>
									<img class="replace-2x" src="${request.contextPath}/assets/mes-users/default.png" width="70" height="70" alt="">
								</g:else>
							</g:link>
							<h4 class="product-name">${message(code: 'default.login.activerole.label', default: 'Active role:')}</h4>
							<div class="product-price">
								<b>${session.getAttribute("username_rolActivo")}</b>
								<!--
								<select name="activo_rol" id="activo_rol" style="width:100%">
									<option>Programador</option>
									<option selected="selected">Administrador</option>
									<option>Titular de la UV</option>
								</select>
								-->
							</div>
						</li>
					</ul>

					<div class="form-group"  >
						<a id="btn-myuser" href="${createLink(uri: '/userLG/myuser')}" name="btn-log" class="btn btn-default btn-block" style="font-family:arial"><i class="fa fa-user"></i> ${message(code: 'default.login.myuser.label', default: 'Mi cuenta')}</a>
					</div>

					<g:form controller="logout" action="index" autocomplete="off" name="log_form">
						<span class="form-group" style="float:center">
							<a id="btn-log" name="btn-log" class="btn btn-primary btn-block" style="font-family:arial"><i class="fa fa-sign-out"></i> ${message(code: 'default.button.logout.label', default: 'Logout')}</a>
						</span>
					</g:form>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
					<div class="label_title" style="font-size:20px">
						<li class="fa fa-arrow-right"></li>&nbsp;<g:message code="default.login.label" args="['Usuario']" />
					</div>
					<g:form controller="login" action="authenticate" autocomplete="off" name="log_form">
						<fieldset class="form" style="margin-left:0px; width:100%">

							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">${message(code: 'default.login.user.label', default: 'User')}
								<span class='required-indicator'>*</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" style="border: 1px solid #b1b1b1" class="form-control" value="" autocomplete="off" name="username" id="username" placeholder="${message(code: 'default.login.user.holder.label', default: 'Tape your username')}" />
										<!--required=""-->
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="password" class="cols-sm-2 control-label">${message(code: 'default.login.password.label', default: 'Pass')}
								<span class='required-indicator'>*</span></label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
										<input type="password" style="border: 1px solid #b1b1b1" class="form-control" value="" autocomplete="off" name="password" id="password"  placeholder="${message(code: 'default.login.password.holder.label', default: '8 or more characters')}"/>
									</div>
								</div>
							</div>

							<!--
							<div class="input-group">
								<div class="cols-sm-10">
									<label>
										<input id="login-remember" type="checkbox" name="remember" value="1"> ${message(code: 'default.login.rememberme.label', default: 'Remember me')}
									</label>
								</div>
							</div>
							-->

							<span class="form-group" style="float:center">
								<a id="btn-log" name="btn-log" class="btn btn-primary btn-block" style="font-family:arial"><i class="fa fa-sign-in"></i> ${message(code: 'default.button.login.label', default: 'Login')}</a>
							</span>
						</fieldset>
					</g:form>
</sec:ifNotLoggedIn>
				</div>
			  </div>

			</div><!-- .header-icons -->

			<div class="primary">
			  <div class="navbar navbar-default" role="navigation">
				<button type="button" class="navbar-toggle btn-navbar collapsed" data-toggle="collapse" data-target=".primary .navbar-collapse">
				  <span class="text">Menu</span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</button>

				<nav class="collapse collapsing navbar-collapse">
				  <ul class="nav navbar-nav navbar-center">

					<li class="parent item-primary item-bg">
					  <a href="${createLink(uri: '/')}">
						<i class="fa fa-home"></i>&nbsp;Inicio
					  </a>
				    </li>

					<li class="parent item-primary item-bg">
					  <a href="#">
						<i class="fa fa-info-circle"></i>&nbsp;General
					  </a>
					  <ul class="sub">
						<li class="sub-wrapper">
						  <div class="sub-list">
							<div class="box closed">
							  <h6 class="title">Conceptos básicos</h6>
							  <ul>
								<li><a href="${request.contextPath}/general/mes"><i class="fa fa-plus-square"></i>&nbsp;¿Qué es MES?</a></li>
								<!--
								<li><a href="${request.contextPath}/general/tiposMantenimiento"><i class="fa fa-plus-square"></i>&nbsp;Tipos de Mantenimientos</a></li>
								<li><a href="${request.contextPath}/general/prevencionAccidentes"><i class="fa fa-plus-square"></i>&nbsp;Prevención de accidentes<span class="item-new bg-warning">Wow</span></a></li>
								<li><a href="#"><i class="fa fa-plus-square"></i>&nbsp;Personal</a></li>
								</br>
								<li><a href="#"><i class="fa fa-graduation-cap "></i>&nbsp;Manuales<span class="item-new">New</span></a></li>
							  	-->
							  </ul>
							</div><!-- .box -->

						  </div><!-- .sub-list -->
						</li>
					  </ul><!-- .sub -->
					</li>

<sec:ifLoggedIn>
<sec:ifAnyGranted roles='ROLE_ADMINISTRADOR'>

					<li class="parent item-primary item-bg">
					  <a href="#"><i class="fa fa-cog"></i>&nbsp;Administración</a>
					  <ul class="sub">
						<li class="sub-wrapper">
						  <div class="sub-list">

							<div class="box closed">
							  <h6 class="title">Seguridad</h6>
							  <ul>
								<li><a href="${request.contextPath}/userLG/index"><i class="fa fa-address-card"></i>&nbsp;Usuarios</a></li>
								<li><a href="${request.contextPath}/roleLG/index"><i class="fa fa-plus-square"></i>&nbsp;Roles</a></li>
								<!--
								<li><a href="${request.contextPath}/userLGRoleLG/index"><i class="fa fa-plus-square"></i>&nbsp;Asignación de roles</a></li>
								-->
							  </ul>
							</div><!-- .box -->
							
							<div class="box closed">
							  <h6 class="title">Catálogos</h6>
							  <ul>
							  	<li><a href="${request.contextPath}/categoria/index"><i class="fa fa-plus-square"></i>&nbsp;Categorías de materiales</a></li>
							  	<li><a href="${request.contextPath}/tipoMantenimiento/index"><i class="fa fa-plus-square"></i>&nbsp;Tipos de mantenimiento</a></li>
							  	<li><a href="${request.contextPath}/tipoVerificacion/index"><i class="fa fa-plus-square"></i>&nbsp;Tipos de verificación</a></li>
							  </ul>
							</div><!-- .box -->
							
							<div class="box closed">
							  <h6 class="title">Estructura</h6>
							  <ul>
								<li><a href="${request.contextPath}/planta/index"><i class="fa fa-plus-square"></i>&nbsp;Plantas</a></li>

								



								<li><a href="${request.contextPath}/linea/index"><i class="fa fa-plus-square"></i>&nbsp;Líneas & Máquinas</a></li>
								<li><a href="${request.contextPath}/subsistema/index"><i class="fa fa-plus-square"></i>&nbsp;Subsistemas</a></li>
								<!--
								<li><a href="${request.contextPath}/maquina/index"><i class="fa fa-plus-square"></i>&nbsp;Máquinas</a></li>
								-->
							  	<li><a href="${request.contextPath}/material/index"><i class="fa fa-plus-square"></i>&nbsp;Materiales</a></li>
							  </ul>
							</div><!-- .box -->
							
							<div class="box closed">
							  <h6 class="title">Verificaciones</h6>
							  <ul>
								<li><a href="${request.contextPath}/verificacion/index?title=Estándar LIL"><i class="fa fa-check"></i>&nbsp;Estándar LIL</a></li>
								<li><a href="${request.contextPath}/verificacion/index?title=Machine Ledger"><i class="fa fa-check"></i>&nbsp;Machine ledger</a></li>
								<!--
								<li><a href="${request.contextPath}/calendario/index"><i class="fa fa-calendar-check-o"></i>&nbsp;Calendario preventivo</a></li>
								<li><a href="${request.contextPath}/punto/index"><i class="fa fa-plus-square"></i>&nbsp;Puntos de verificación</a></li>
								<li><a href="${request.contextPath}/estandar/index"><i class="fa fa-files-o"></i>&nbsp;Estándares de consulta</a></li>
								-->
							  </ul>
							</div><!-- .box -->
						  </div><!-- .sub-list -->

						</li>
					  </ul><!-- .sub -->
					</li>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles='ROLE_ADMINISTRADOR,ROLE_EJECUTIVO'>
					<li class="parent item-primary item-bg">
					  <a href="#"><i class="fa fa-print"></i>&nbsp;Informes<span class="item-new">New</span></a>
					  <ul class="sub">
						<li class="sub-wrapper">
						  <div class="sub-list">
							<div class="box closed">
							  <ul>
								<li><a href="${request.contextPath}/informes/indicadores"><i class="fa fa-bar-chart "></i>&nbsp;Indicadores</a></li>
							  </ul>
							</div><!-- .box -->


							<div class="box closed">
							  <h6 class="title">Seguridad</h6>
							  <ul>
								<li><a href="${request.contextPath}/userLG/report"><i class="fa fa-file-text"></i>&nbsp;Listado de usuarios</a></li>
								</ul>
							</div><!-- .box -->

							<div class="box closed">
							  <h6 class="title">Catálogos</h6>
							  <ul>
								<li><a href="${request.contextPath}/planta/report"><i class="fa fa-file-text"></i>&nbsp;Listado de plantas</a></li>
								<li><a href="${request.contextPath}/linea/report"><i class="fa fa-file-text"></i>&nbsp;Listado de líneas</a></li>
								<li><a href="${request.contextPath}/maquina/report"><i class="fa fa-file-text"></i>&nbsp;Listado de máquinas</a></li>
								<li><a href="${request.contextPath}/subsistema/report"><i class="fa fa-file-text"></i>&nbsp;Listado de subsistemas</a></li>
								<li><a href="${request.contextPath}/tipoMantenimiento/report"><i class="fa fa-file-text"></i>&nbsp;Listado de tipos de mttos.</a></li>
								<li><a href="${request.contextPath}/material/report"><i class="fa fa-file-text"></i>&nbsp;Listado de materiales</a></li>
							  </ul>
							</div><!-- .box -->

							<div class="box closed">
							  <h6 class="title">Mantenimientos</h6>
							  <ul>
								<li><a href="#"><i class="fa fa-file-text"></i>&nbsp;Estándar LIL</a></li>
								<li><a href="#"><i class="fa fa-file-text"></i>&nbsp;Machine Ledger</a></li>
							  </ul>
							</div><!-- .box -->
						  </div><!-- .sub-list -->

						</li>

</sec:ifAnyGranted>
</sec:ifLoggedIn>

				  </ul>
				</nav>
			  </div>
			</div><!-- .primary -->
		  </div>
		</div>
	  </div><!--.row -->
	</div>
  </div><!-- .header-wrapper -->
</header><!-- .header -->
