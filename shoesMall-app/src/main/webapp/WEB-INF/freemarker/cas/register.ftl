<html>
	<head>
		<title>注册</title>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.min.js"></script>
		<link type="text/css" href="${absoluteContextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
		<script type="text/javascript">
			$(function(){
				$('#dialog').dialog({
					autoOpen: false,
					width: 400,
					modal: true,//遮罩
					//bgiframe: true, 
					buttons: {
						"Ok": function() { 
							$(this).dialog("close"); 
							$("#registerForm").ajaxSubmit({
								target: '#output'
							});
						}, 
						"Cancel": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});
			});
		</script>
	</head>
	<body>
		<div class="main_banner">
			<br>
			<br>
			<div id="midInfoTop"></div>
		</div>
		<div id="midInfoBottom">
			<div id="dialog" title="确认提交">
				<p>确定提交注册信息?</p>
			</div>
        	<div id="contentlogin">
		            <div class="content_1">
		              	 <div class="login_info">
			                 	<p class="title">注册账号</p>
					            <form id="registerForm" action="${absoluteContextPath}/cas/register/add" method="POST">
									<#assign st=JspTaglibs["/WEB-INF/session.token.tld"]/>
									<@st.token/>
					            	<div id="output"></div>
									<input type="hidden" name="form_uuid_token" value="${form_uuid_token?if_exists}" />
				                     <p class="p1">
					                      <input id="name" name="name" type="text" class="input1" value="用户名" />
					   				 </p>
				                     <p class="p1">
					                      <input id="email" name="email" type="text" class="input1" value="邮箱" />
					   				 </p>
					                  <p class="p1">
					                      <input id="password" name="password" type="password" class="input1" value="密码" />
					   				 </p>
					             	 <p class="p1">
					                      <input id="confirmPassword" name="confirmPassword" type="password" class="input1" value="确认密码" />
					   				 </p> 
					                 <div class="btn_1">
					                    <label>  验证码：</label>
					                    <input id="Validate_Code" name="verifycode" type="text" style="width: 48px;" maxlength="8"/>
					                    <img width="80px" width="25px" src="${absoluteContextPath}/verifycode.jpg" onclick="this.src='${absoluteContextPath}/verifycode.jpg?'+new Date().getTime()" class="verifycode"/> 
									 </div>
					                 <div class="btn_1">
										<p>请阅读<a target="_blank" href="http://.com/Category/69-1.html">《CocosShare服务条款》</a></p>
										<input type="button" id="dialog_link" class="btn_reg" value=""/>
									 </div>
					                 <span class="tip_2" style="left:265px;	top:55px;">正确</span>
					                 <span class="tip_3" style="left:268px;	top:101px;">密码错误</span>  
				                 	 <div class="clear"></div>         
							     </form>
		                 </div>
		            </div>
	            	<div class="content_2">
			            <p class="p5">已经有账号了,请直接</p>
			            <p class="p7"><a href="${absoluteContextPath}/cas/login" class="btn_reg2"></a></p>
		            </div>
            </div>
		</div>
	</body>
</html>