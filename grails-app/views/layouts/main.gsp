<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<title>Unilever MES</title>
	<asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
	<meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic'>

    <asset:stylesheet src="application.css"/>
	<asset:javascript src="application.js"/>

    <asset:javascript src="revolution/jquery.themepunch.tools.min.js"/>
    <asset:javascript src="revolution/jquery.themepunch.revolution.min.js"/>
    <asset:javascript src="revolution/extensions/revolution.extension.slideanims.min.js"/>
    <asset:javascript src="revolution/extensions/revolution.extension.kenburn.min.js"/>
    <asset:javascript src="revolution/extensions/revolution.extension.video.min.js"/>
    <asset:javascript src="revolution/extensions/revolution.extension.navigation.min.js"/>
    <asset:javascript src="revolution/extensions/revolution.extension.layeranimation.min.js"/>
    <asset:javascript src="revolution/extensions/revolution.extension.migration.min.js"/>

    <!--[if lt IE 9]>
    <asset:stylesheet href="ie/ie8.css"/>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	<g:layoutHead/>
</head>
<body class="fixed-header">
<div class="page-box">
<div class="page-box-content">
    <g:if test="${session.getAttribute("redirect")}">
    <script languaje="javascript">
		location.href="${request.contextPath}${session.getAttribute("redirect")}";
    </script>
    </g:if>
    <g:else>

    <g:layoutBody/>

    <div class="footer" role="contentinfo">
		<table border="0px" cellpadding="0px" cellspacing="0pc" width="100%">
			<tr>
				<td style="border-right:2px solid white;min-width:110px;width:110px;"><img src="${request.contextPath}/assets/unilever_white.png" style="width:100px"></td>
				<td style="padding-left:10px;padding-right:10px;min-width:120px;"><span style="font-size:20px;font-weight:bold;text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);">MANUFACTURING</br>EXECUTION</br>SYSTEM</span><span style="text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);color:#ffffff;font-weight:bold;">&nbsp;&nbsp;v<g:meta name="info.app.version"/></span></td>
				<td width="50%"></td>
				<td valign="bottom" style="min-width:280px">
					<span style="font-size: 20px; font-weight: bold;text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);">CONTACTO</br></span><g:copyright startYear="2017" encodeAs="raw"></g:copyright> | Unilever de México S. de R.L. de C.V.</br>
					<i class="fa fa-map-marker"></i>&nbsp;Calle 21 E #1, Civac, Jiutepec, Morelos, Méx. 62578</br>
					<i class="fa fa-phone"></i>&nbsp;01 (777) 329 1000</br>
					<i class="fa fa-envelope"></i>&nbsp;contacto@unilever-mes.com<a href="http://weykhans.com" style="display: inline;"><img src="${request.contextPath}/assets/ws.png" width="105px" style="float:right;" border="0px"></a>
				</td>
			</tr>
		</table>
    </div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>
    </g:else>
</div>
</div>
</body>
</html>
