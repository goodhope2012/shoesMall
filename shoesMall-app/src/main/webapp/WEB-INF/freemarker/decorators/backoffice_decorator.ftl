<#ftl encoding="utf-8"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title>CocosShare 后台管理 ${title} - 专业的移动开发者服务平台 | 移动应用统计 | Android统计 | iPhone统计 </title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta content="CocosShare移动平台统计分析工具,为中国移动开发者提供专业、稳定、免费的统计分析服务以及实用的开发者工具" name="description">
		<meta content="CocosShare,统计,移动互联网,android统计,iphone统计,mobile analytics" name="keywords">
	    <link rel="icon" href="${absoluteContextPath}/images/favicon.ico" mce_href="${absoluteContextPath}/images/favicon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="${absoluteContextPath}/images/favicon.ico" mce_href="${absoluteContextPath}/images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" href="${absoluteContextPath}/css/backoffice.css" type="text/css" />
		<link rel="stylesheet" href="${absoluteContextPath}/css/lanrentuku.css" type="text/css" />
	    <script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery-1.6.4.min.js"></script>
	    <script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.cookie.js"></script>
		<script language="javascript" type="text/javascript" src="${absoluteContextPath}/js/jquery.easing.js"></script>
		<script language="javascript" type="text/javascript" src="${absoluteContextPath}/js/xixi.js"></script>
	    ${head}
	</head>
	<body>
		<#include "backoffice_header.ftl"/>
		<div id="content">
			<div id="left">
				<#include "backoffice_sidemenu.ftl"/>
			</div>
			<div id="right">
				<#include "actionErrors.ftl"/>
				<#include "actionMsgs.ftl"/>
				${body}
			</div>
		</div>
		<#include "backoffice_footer.ftl"/>
	</body>
</html>