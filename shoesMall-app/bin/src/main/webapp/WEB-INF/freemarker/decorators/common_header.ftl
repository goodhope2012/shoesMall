<#ftl encoding="utf-8"/>
<script>
	$(document).ready(function(){
	    if(window.location.pathname.toLowerCase().indexOf("/sdkcenter")==0){
	    	$('.cure').removeClass('cure');
			$("#sdkcenter_tab").addClass('cure');;
		}else if(window.location.pathname.toLowerCase().indexOf("/report")==0){
	    	$('.cure').removeClass('cure');
			$("#report_tab").addClass('cure');;
		}else if(window.location.pathname.toLowerCase().indexOf("/dev")==0){
	    	$('.cure').removeClass('cure');
			$("#dev_tab").addClass('cure');;
		}else if(window.location.pathname.toLowerCase().indexOf("/help")==0){
	    	$('.cure').removeClass('cure');
			$("#help_tab").addClass('cure');;
		}else if(window.location.pathname.toLowerCase().indexOf("/my/apps")==0){
	    	$('.cure').removeClass('cure');
			$("#apps_tab").addClass('cure');;
		}else if(window.location.pathname.toLowerCase().indexOf("/my")==0){
	    	$('.cure').removeClass('cure');
			$("#my_tab").addClass('cure');;
		}
	});
</script>
<#assign sec=JspTaglibs["/WEB-INF/security.tld"]>
<div class="topWrap">	
	<div class="uInfo">
		<ul class="topMenuLeft">
			<li><a href="${absoluteContextPath}">welcome to Share-X !</a></li>
		</ul>
		<#if userName?exists>
			<ul class="topMenu">
				<li><a href="${absoluteContextPath}/cas/logout">Sign out</a></li>
				<li><a id="my_tab" href="${absoluteContextPath}/my">Profile</a></li>
				<li><a id="apps_tab" href="${absoluteContextPath}/my/apps">My Apps</a></li>
				<@sec.authorize ifAnyGranted="ROLE_SERVER,ROLE_OPERATOR,ROLE_ADMIN">
					<li><a href="${absoluteContextPath}/backoffice" target="_blank">Backoffice</a></li>
				</@sec.authorize>
				<li>Hi, ${userName?if_exists}!</li>  
			</ul>
		<#else>
			<ul class="topMenu">
				<li><a href="${absoluteContextPath}/cas/register">Register</a></li>
				<li><a href="${absoluteContextPath}/cas/login">Sign in</a></li>
			</ul>
		</#if>
	</div>
</div>
<div class="top_banner">	
	<div  class="top">
		<div class="logo">
			<a href='${absoluteContextPath}'><img src='${absoluteContextPath}/images/logo.png'></a>
		</div>
		<div class="nav">
			<ul>
				<li><a href="${absoluteContextPath}" class="cure">首页</a></li>
				<li><a id="sdkcenter_tab" href="${absoluteContextPath}/sdkcenter" >SDK介绍</a></li>
				<li><a id="report_tab" href="${absoluteContextPath}/report">统计功能</a></li>
				<li><a id="dev_tab" href="${absoluteContextPath}/dev/doc">文档中心</a></li>
				<li><a id="help_tab" href="${absoluteContextPath}/help">FAQs</a></li>
				<li><a href="http://share.cocos2d-x.org:8080/" target="_blank">博客</a></li>
			</ul>
		</div>
		<div style="clear:both;"></div>
	</div>
</div>