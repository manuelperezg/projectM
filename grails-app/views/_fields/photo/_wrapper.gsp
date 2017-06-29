<div class="fieldcontain <g:if test="${required}">required</g:if> ${hasErrors(bean: bean, field: property, 'has-error')}" style="height:230px;">
	<label for="tmp-${(prefix ?: '') + property}"><g:message code="${label}" default="${label}" />${required? '*' : '' }</label>
	<g:if test="${value}">
		<img id="${(prefix ?: '') + property}-img" src="${request.contextPath}/assets/mes-users/${value}" style="width:200px;height:200px;" />
	</g:if>
	<g:else>
		<img id="${(prefix ?: '') + property}-img" src="${request.contextPath}/assets/mes-users/default.png" style="width:200px;height:200px;" />
	</g:else>
	</br>
	<label for="${property}-label"></label>
	<label id="${property}-label" style="text-align:left;">
		<input type="file" id="tmp-${(prefix ?: '') + property}" name="tmp-${(prefix ?: '') + property}" <g:if test="${required}">required=""</g:if> onchange="PreviewImage('tmp-${(prefix ?: '') + property}','${(prefix ?: '') + property}-img');" />
	</label>
</div>
