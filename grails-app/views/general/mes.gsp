<!doctype html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="-1">
        <meta name="layout" content="main"/>
        <title>Unilever MES</title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
        <style>
            .label_title {
                float:none;
                color:#D0006F !important;
            }
            .title-box {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <!-- .menu-box -->
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li class="active"><g:message code="default.mes.label" /></li>
			</ul>
		  </div>
		</div>
        <div id="show-mes" class="content scaffold-show" role="main">
			<div class="row">
				<div class="col-sm-6 col-md-6 bottom-padding">
					<div class="video-box html5">
						<video class="video-js vjs-default-skin" controls preload="auto" autoplay loop="true" poster="" data-setup="{}">
							<source src="${request.contextPath}/assets/MES.m4v" type="video/mp4">
						</video>
					</div>
				</div>
				<div class="col-sm-6 col-md-6">
					<div class="title-box">
						<h2 class="title" style="color:#D0006F;">
						   <i class="fa fa-lightbulb-o"></i>&nbsp;Manufacturing Execution System (MES)
						</h2>
					</div>
					<p class="lead">Es una plataforma modular basada la administración en Web y lo operativo en Móvil, integra los mantenimientos autónomo y profesional para gestionar las actividades de las listas de verificación del personal y dar seguimiento al resultado de sus actividades.</p>
					<div class="title-box">
						<h2 class="title" style="color:#D0006F;">
						   <i class="fa fa-lightbulb-o"></i>&nbsp;¿A quien va dirigido?
						</h2>
					</div>
					<p class="lead">MES esta destinada a los supervisores, operadores y mecánicos que intervienen en los procesos de producción en la planta con la finalidad de facilitar el registro de sus actividades y generar con ello un conjunto de indicadores e informes que apoyen en la toma de decisiones a los niveles superiores.</p>
					<div class="title-box">
						<h2 class="title" style="color:#D0006F;">
						   <i class="fa fa-lightbulb-o"></i>&nbsp;Perspectivas a futuro...
						</h2>
					</div>
					<p class="lead">Dada la arquitectura modular de MES, se planea integrar nuevos desarrollos orientados a la seguridad industrial, calidad y recursos humanos.</p>
				</div>
			</div>
			<br><br>
		</div>
    </body>
</html>
