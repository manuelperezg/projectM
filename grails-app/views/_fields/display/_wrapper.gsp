<div class="fieldcontain">
    <label for="${property}"><g:message code="${label}" default="${label}" /></label>
    <g:if test="${value != null}">
		${value}
	</g:if>
	<g:else>
		<g:message code="default.unassigned.label" default="Value not found" />
	</g:else>
</div>
