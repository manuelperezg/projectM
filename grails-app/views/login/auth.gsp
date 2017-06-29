<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLG.label', default: 'Usuario')}" />
        <title>Unilever MES</title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
        <script language="javascript">
			function submit() {
				$("#authenticate_form").submit();
			}
			$( document ).ready(function() {
				$("#authenticate_form").addClass( "form-box login-form form-validator" );
			});
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#create-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        </br>

		<div class="col-xs-12 col-sm-6 col-md-6 box login">
			<div class="info-box">
				<h3 class="title"></h3>
				<p class="descriptions"></p>
			</div>
		</div>

        <div class="col-xs-12 col-sm-6 col-md-6 box login" style="float:righ">
			<g:form controller="login" action="authenticate" autocomplete="off" name="authenticate_form">
				<div class="label_title" style="font-size:20px;width:100%">
					<li class="fa fa-arrow-right"></li>&nbsp;<g:message code="default.login.label" args="[entityName]" />
				</div>
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

				<fieldset class="form" style="margin-left:0px; width:100%">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">${message(code: 'default.login.user.label', default: 'User')}
					<span class='required-indicator'>*</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
							<input type="text" style="border: 1px solid #b1b1b1" class="form-control" value="" name="username" id="username" autocomplete="off" placeholder="${message(code: 'default.login.user.holder.label', default: 'Enter your username')}" />
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
							<input type="password" style="border: 1px solid #b1b1b1" class="form-control" value="" name="password" id="password" autocomplete="off" placeholder="${message(code: 'default.login.password.holder.label', default: '8 or more characters')}"/>
						</div>
					</div>
				</div>

				<div class="form-group">
				  <input type="checkbox" name="remember_me" id="remember_me" />&nbsp;<b>${message(code: 'default.login.rememberme.label', default: 'Remember me')}</b>
				</div>

				<div class="buttons-box clearfix">
					<a href="javascript:submit();" class="btn btn-primary" style="font-family:arial"><i class="fa fa-sign-in"></i> ${message(code: 'default.button.login.label', default: 'Login')}</a>
					%{--
					<g:link class="forgot" action="forgot"><g:message code="default.login.forgot.label" default="Forgot Your Password?"/></g:link>
				 	--}%
				</div>
				</fieldset>
			</g:form>
		</div>

    </body>
</html>
