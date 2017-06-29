<div class="fieldcontain <g:if test="${required}">required</g:if> ${hasErrors(bean: bean, field: property, 'has-error')}">
	<label for="${property}"><g:message code="${label}" default="${label}" />${required? '*' : '' }</label>
	<label id="${property}">
    <input type="text" name="${(prefix ?: '') + property}" value="${value}" 
        <g:if test="${required}">required=""</g:if>
    />
    <g:hasErrors bean="${bean}" field="${property}">
        <g:eachError bean="${bean}" field="${property}" as="list"><span class="help-block help-block-error m-b-none text-danger"><g:message error="${it}" /></span></g:eachError>
    </g:hasErrors>
</div>
