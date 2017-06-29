<div class="fieldcontain <g:if test="${required}">required</g:if> ${hasErrors(bean: bean, field: property, 'has-error')}" >
	<label for="tmp-${(prefix ?: '') + property}"><g:message code="${label}" default="${label}" />${required? '*' : '' }</label>
	<g:if test="${value}">
		${value}&nbsp;&nbsp;&nbsp;
	</g:if>
	<label id="${property}-label">
		<input type="file" id="tmp-${(prefix ?: '') + property}" name="tmp-${(prefix ?: '') + property}" <g:if test="${required}">required=""</g:if> />
    </label>
</div>
