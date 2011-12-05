<font color='red'>
	<#if errors?has_content>
		<div class='error' id="error">
			<#list errors as error>
					<div>${error?if_exists}</div>
			</#list>
		</div>
	</#if>
</font>