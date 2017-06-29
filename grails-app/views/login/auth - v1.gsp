<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLG.label', default: 'Usuario')}" />
        <title><g:message code="default.login.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#create-userLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
            </ul>
        </div>
        <div id="create-userLG" class="content scaffold-create" role="main">
            <h1><g:message code="default.login.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.userLG}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.userLG}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="authenticate" autocomplete="off">
				<fieldset class="form">
					<div class='fieldcontain required'>
					  <label for='username'>${message(code: 'default.login.user.label', default: 'User')}
						<span class='required-indicator'>*</span>
					  </label><input type="text" value="" required="" name="username" id="username" />
					</div>
					<div class='fieldcontain required'>
					  <label for='password'>${message(code: 'default.login.password.label', default: 'Pass')}
						<span class='required-indicator'>*</span>
					  </label><input type="password" value="" required="" name="password" id="password" />
					</div>
					<div class='fieldcontain'>
					  <label for='password'>&nbsp;
					  </label>
					  <input type="checkbox" name="remember-me" id="remember_me" />&nbsp;<b>Recuérdame</b>
					</div>
				</fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="login" class="login" value="${message(code: 'default.button.login.label', default: 'Login')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
