<font color='green'>
	<#if msgs?has_content>
		<div class='successMsg' id="message">
			<#list msgs as msg>
					<div>${msg}</div>
			</#list>
		</div>
		<script>
			setTimeout(function(){$('#message').fadeOut('slow',null);}, 5000);
		</script>
	</#if>
</font>