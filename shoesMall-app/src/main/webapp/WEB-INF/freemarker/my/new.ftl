<html>
	<head>
		<title>添加应用</title>
		<!--[if IE 6]> 
			<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script> 
			<script type="text/javascript">
				DD_belatedPNG.fix('.appInfo .guide,.login_info p.p1,.login_info .tip_3,img');
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
		  <div id="contentApp">
		    <div class="content_1">
		      <div class="appInfo">
				<form id="addAppForm" name="addAppForm" action="${absoluteContextPath}/my/apps/add" method="post">
			        <p class="guide"></p>
			        <p class="p1"> <span class="name left">应用名称：</span>
			        	<span class="input">
			          	<input id="name" name="name" value="<#if app??>${app.name?if_exists}</#if>" class="input_1" />
			          	</span>
					</p>
			        <p class="p1"> <span class="name left">应用主页：</span><span class="input">
			          <input id="url" name="url" value="<#if app??>${app.url?if_exists}</#if>" class="input_1" />
			          </span>
					</p>
			        <p class="p1"> <span class="name left">平台类型：</span><span class="input">
			          <input type="radio" name="platform" id="radio" value="android" checked="checked"/>Android
			          <input type="radio" name="platform" id="radio" value="ios" />iOS
			          </span>
			         </p>
			        <p class="p1"> <span class="name left">应用类型：</span><span class="input">
			          <select class="input_1" name="category" id="category">
			            <option>请选择类型</option>
			            <option selected>娱乐</option>
			            <option>教育</option>
			          </select>
			          </span>
			        </p>
			        <p class="p1"><span class="name left">备注：</span>
			        	<span class="input">
			          		<textarea class="input_2" id="description" name="description" cols="45" rows="5"><#if app??>${app.description?if_exists}</#if></textarea>
			        	</span>
			        </p>
			        <div class="btn_1"><input class="btn_tj" type="submit" value=""/></div>
			        <div class="clear"></div>
				</form>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>
