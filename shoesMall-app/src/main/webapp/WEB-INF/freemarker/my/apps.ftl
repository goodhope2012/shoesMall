<#assign jmesa=JspTaglibs["/WEB-INF/jmesa.tld"]>
<html>
	<head>
		<title>我的应用</title>
		<link href="${absoluteContextPath}/js/jmesa/jmesa.css" rel="stylesheet" type="text/css" />
		<script src="${absoluteContextPath}/js/jmesa/jmesa.js"></script>
		<script src="${absoluteContextPath}/js/jmesa/jquery.jmesa.js"></script>
		<script type="text/javascript">
			function onInvokeAction(id) {
				$.jmesa.setExportToLimit(id, '');
				$.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
			}
	     </script>
		 <!--[if IE 6]> 
			<script type="text/javascript" src="${absoluteContextPath}/js/DD_belatedPNG_0.0.8a-min.js" ></script> 
			<script type="text/javascript"> 
				DD_belatedPNG.fix('.controls li.ico_1,.controls li.ico_2,.controls li.ico_3'); 
			</script>
		 <![endif]-->
	</head>
	<body>
		<div class="main_banner">
			<br>
			<br>
			<div id="midInfoTop"></div>
		</div>
		<div id="midInfoBottom">
				<div id="wrap">
				  <div class="grid_12">
				  <div class="box-header">我的产品
				          <p class="num_user">总用户数:<span>523652</span></p>
				          <ul class="controls">
				            <li class="ico_1"><a href="${absoluteContextPath}/my/apps/new">添加应用</a></li>
				            <li class="ico_2"><a href="#">SDK下载</a></li>
				            <li class="ico_3"><a href="#">开发文档</a></li>
				          </ul>
				    </div>
				    <div class="box">
				      <div id="chart_div" style="position: relative; ">
				      </div>
				      <div id="dashtabs-pages" class="box-content no-padding ui-tabs-panel ui-widget-content ui-corner-bottom">
						<form name="appsForm" action="${absoluteContextPath}/my/apps" method="post">
						     <@jmesa.tableModel
						        id="tag" 
						        items=appList
						        var="app"
						        >
						        <@jmesa.htmlTable width="100%"  caption="应用列表">               
						            <@jmesa.htmlRow>     
						                <@jmesa.htmlColumn property="platform" title="平台">
						                	<img src="${absoluteContextPath}/images/${app.platform?if_exists}.png" width="25" height="27" />
						                </@jmesa.htmlColumn>
						                <@jmesa.htmlColumn property="name" title="应用名字">
						                	<a href="${absoluteContextPath}/my/apps/${app.shareKey?if_exists}">${app.name?if_exists}</a>
						                </@jmesa.htmlColumn>
						                <@jmesa.htmlColumn property="shareKey" title="Appkey"/>
						                <@jmesa.htmlColumn property="description" title="备注"/>
						                <@jmesa.htmlColumn property="category" title="类型"/>
						                <@jmesa.htmlColumn title="查看报表">
				                				<a href="${absoluteContextPath}/my/apps/${app.shareKey?if_exists}" class="tooltip" title="Edit Page">
							                		<img src="${absoluteContextPath}/images/chart_bar.png" width="16" height="16" />
							                		统计
							                	</a> 
							                	<a href="${absoluteContextPath}/my/apps/${app.shareKey?if_exists}" class="tooltip" original-title="Delete Page">
							                		<img src="${absoluteContextPath}/images/hammer_screwdriver.png" width="16" height="16" />
							                		开发工具
							                	</a>
						                </@jmesa.htmlColumn>
						            </@jmesa.htmlRow>
						        </@jmesa.htmlTable> 
						     </@jmesa.tableModel>
						</form>
				      </div>
				    </div>
				  </div>
				</div>
				<div style="clear:both;"></div>
		</div>
	</body>
<html>