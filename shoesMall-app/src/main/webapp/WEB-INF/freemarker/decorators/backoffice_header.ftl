<div id="header">
	<div id="logo"><h1><a href="${absoluteContextPath}/backoffice">管理中心</a></h1></div>
	<ul id="user">
		<li class="first">欢迎您!  ${userName?if_exists}</li>
		<li><a href="${absoluteContextPath}" title="Home">网站首页</a></li>
	</ul>
	<!-- end user -->
	<div id="header-inner">
		<div id="home">
			<a href="${absoluteContextPath}/backoffice" title="Home"></a>
		</div>
		<!-- quick -->
		<ul id="quick">
			<li>
				<a href="${absoluteContextPath}/cas/logout"><span class="icon"><img src="${absoluteContextPath}/images/cog.png" alt="Settings"></span><span>退出系统</span></a>
			</li>
		</ul>
		<!-- end quick -->
	</div>
</div>