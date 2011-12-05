<html>
	<header>
		<title>登陆</title>
	</header>
	<body>
		<div class="main_banner">
			<br>
			<br>
			<div id="midInfoTop"></div>
		</div>
		<div id="midInfoBottom">
	        <div id="contentlogin">
	            <div class="content_1">
	              	<div class="login_info">
	              		<form action="${absoluteContextPath}/j_spring_security_check" method="post">
		                 	 <p class="title">用户登录</p>
		                     <p class="p1">
		                      <input name="j_username" type="text" class="input1" id="j_username" />
			   				 </p>
			                  <p class="p1">
			                      <input name="j_password" type="password" class="input1" id="j_password" />
			   				 </p>
		                 	 <p class="p2">
		                      <input name="_spring_security_remember_me" type="checkbox" id="_spring_security_remember_me" />保持登录状态
		                      <a href="#" class="tip_pw">忘记密码？</a>
		   				 	 </p>
		                 	 <div class="btn_1">
		                   		<input type="submit" class="btn_login" value="" />
							 </div>
			                 <span class="tip_2" style="left:265px;	top:55px;">正确</span>
			                 <span class="tip_3" style="left:268px;	top:101px;">密码错误</span>  
		                     <div class="clear"></div>
	                    </form>      
	                 </div>
	            </div>
	            <div class="content_2">
	            <p class="p5">还没有登录账号?</p>
	            <p class="p7"> <a href="${absoluteContextPath}/cas/register" class="btn_login2"></a></p>
	          </div>
	        </div>
		</div>
	</body>
</html>