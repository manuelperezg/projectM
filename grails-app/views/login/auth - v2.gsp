<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLG.label', default: 'Usuario')}" />
        <title><g:message code="default.login.label" args="[entityName]" /></title>
        <script language="javascript">
			function submit() {
				$('form#authenticate_form').submit();
			}
        </script>
        <style>
			.login_box {
				border-radius: 10px 10px 10px 10px;
				-moz-border-radius: 10px 10px 10px 10px;
				-webkit-border-radius: 10px 10px 10px 10px;
				border: 2px solid #DEDEDE;
				background-color: #FAFAFA;
				width:280px;
				height:410px;
				z-index: 10;
				position: absolute;
				right: 50px;
				top: 110px;
			}
			.login_title {
				margin-top: 30px;
				padding: 10px 4px;
				background-color: #0088CC;
				width:90%;
				border-radius: 0px 5px 5px 0px;
				-moz-border-radius: 0px 5px 5px 0px;
				-webkit-border-radius: 0px 5px 5px 0px;
				border: 0px solid #FFFFFF;
				color: #FFFFFF;
				box-shadow: 3px 3px 3px #DEDEDE;
			   -webkit-box-shadow: 3px 3px 3px #DEDEDE;
			   -moz-box-shadow: 3px 3px 3px #DEDEDE;
				
			}
			.login_btn, .login_btn:focus, .login_btn:active {
				text-decoration: none;
				width:90%;
				font-family: arial;
				font-size: 20px;
				background-color: #CA002D;
				border: 1px solid #FFFFFF;
				color: #FFFFFF;
				box-shadow: 3px 3px 3px #DEDEDE;
			   -webkit-box-shadow: 3px 3px 3px #DEDEDE;
			   -moz-box-shadow: 3px 3px 3px #DEDEDE;
			}
			.login_btn:hover {
				font-weight: none;
				color: #CA002D;
				background-color: #FFFFFF;
				border: 1px solid #CA002D;
			}
        </style>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#create-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation" style="background: url('${request.contextPath}/assets/sky-bg.jpg') #0088CC; width:100%; height:300px; -webkit-background-size: 100% 100%; -moz-background-size: 100% 100%; -o-background-size: 100% 100%;  background-size: 100% 100%;">
        </div>
        <div id="create-userLG" class="content scaffold-create" role="main" style="height:165px;"></div>
        
        <div class="login_box">
            <h1 class="login_title"><i class="fa fa-users fa" aria-hidden="true"></i>&nbsp;<g:message code="default.login.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.userLG}">
            <ul class="errors" role="alert" style="valign:top">
                <g:eachError bean="${this.userLG}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="authenticate" autocomplete="off" name="authenticate_form">
				<fieldset class="form" style="margin-left:0px; width:100%">
					
					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">${message(code: 'default.login.user.label', default: 'User')}
						<span class='required-indicator'>*</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" value="" name="username" id="username"  placeholder="Capture su usuario" />
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
								<input type="password" class="form-control" value="" name="password" id="password"  placeholder="8 o más caracteres"/>
							</div>
						</div>
					</div>
					
					<div class="form-group">
					  <input type="checkbox" name="remember_me" id="remember_me" />&nbsp;<b>Recuérdame</b>
					</div>
					
					<span class="form-group">  
						<center>   
							<a href="javascript:submit();" class="btn login_btn" style="font-family:arial"><i class="fa fa-sign-in"></i> ${message(code: 'default.button.login.label', default: 'Login')}</a>
						</center>
					</span>
				</fieldset>
            </g:form>
        </div>
    </body>
</html>
