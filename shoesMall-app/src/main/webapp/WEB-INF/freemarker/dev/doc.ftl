<html>
	<head>
		<title>SDK文档</title>
	</head>
	<body>
		<div class="main_banner">
			<br>
			<br>
			<div id="midInfoTop"></div>
		</div>
		<div id="midInfoBottom">
			<b class="article_title">一键分享SDK使用指南</b>
			<div>
				<p style="border: 1px solid #FB9D39;background: #FEF3CE;text-align:left;margin:10px;padding:0px 10px 10px 10px;"><br>
					使用过程任何的问题和建议请通过以下方式与我们讨论交流：<br>
					QQ群: 146957607<br>
					QQ: 1828547298<br>
					Email: qinghua.chen@cocos2d-x.org<br>
				</p>
			
			    <p style="text-align:left;margin:10px">
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	欢迎您使用Share-X SNS分享SDK, 该SDK目前主要为您的应用提供跨平台SNS图文分享服务。
			    	我们目前支持的SNS平台包括新浪微博，人人网，腾讯微博，twitter和facebook，
			    	当然我们也将继续扩展我们所提供的服务的类型及支持的分享的平台。
			    	为了最大可能的节省您的开发成本，我们在为您提供了一整套API的同时，也提供了一套默认的分享界面，您只需调用相关接口即可使用。<br>
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;与此同时，为了帮您更好的了解分享功能在终端的使用情况，我们也提供了对应的分享的统计服务；
			    	为了使来自您的应用的分享更具个性，我们也支持您去配置个性化的分享模板，更多更具价值，更为有趣的功能也将不断被补充进来，
			    	愿我们所有的努力在使您的应用更加有趣的同时，也能帮助您创造更多的价值！
			    </p>
			    <p style="text-align:left;margin:10px">
			    	<strong>该SDK的使用流程如下：</strong><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您想在已在Share-X注册的应用（您的应用已获得share-x_APPKEY）中使用该SDK，
					请参考“SHARE-X_APPKEY和分享平台的绑定”及其以下部分。<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您想在尚未在Share-x注册的应用中使用该SDK，请先注册您的应用，
					具体如下：登录您的帐号后，看到share-x的管理后台，点击“添加新应用”，进入新应用信息填写的页面。
					App建立成功后，自动会跳转到该App管理页面的开发指南页面。您可以在这个页面中获得该App的AppKey，最新的开发指南以及SDK文件。
					获得该Appkey后，请继续完成以下步骤：<br>
				</p>	
				<p style="text-align:left;margin:10px">
					<strong>Share-x_APPKEY和分享平台的绑定:</strong><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;获得appkey后，您还需要将您的应用和我们支持的分享平台注册时获取的appkey和appsecret绑定。
					如果您尚未在我们支持的分享平台注册您的应用，注册过程可以参考如下(相关平台变化，请灵活应对)：<br>
					新浪微博：http://open.t.sina.com.cn -&gt; 我是开发者 -&gt; 创建新应用<br>
					腾讯微博：http://open.t.qq.com/ -&gt; 创建应用<br>
					人人网：  http://dev.renren.com/ -&gt; 创建应用 
					（创建完成后，在“我的应用”页面将可以看到获得的AppId，Appkey, Secret，
					至此您还需要去设置App的Callback URL：点击“网站信息”, 
					设置“网站 URL”为：http://sns.whalecloud.com/renr/callback ，
					网站根域名为：sns.whalecloud.com保存即可，具体见人人开放平台说明）<br>
			    </p>
				<p style="text-align:left;margin:10px">
					在各平台注册您的应用后，接下来您需要完成SHARE-X_APPKEY和您在我们支持的分享平台注册您的应用时获取的appkey和appsecret绑定。
					具体操作为：开发工具-&gt;SNS分享-&gt;设置。平台的差异想必也带给您很多的不便，我们也很无奈，祝好运!
				</p>
				<h3>  实现基本的使用</h3>
			</div>
			<div>
				<ul>
					<li><strong>添加其他资源文件</strong>
					</li>
					<li><strong>配置分享平台（可选）</strong></a>
						<ul>
							 目前我们支持的分享的平台是：新浪微博，人人网和腾讯微博， 如果您的应用中只需要用到其中的某一个或某两个平台， 
							 只需要在share-x_sns_platform_strings.xml（该文件在我们提供的资源文件， values文件夹下）将不需要的平台设为”close”即可（默认三个平台都是开启的）。
							 例如您的应用中不需要用到分享到腾讯微博的功能，则将share-x_tenc设为”close”即可。
							<div>
							</div>
						</ul>
					</li>
					<li><strong>配置分享模板（可选）</strong></a>
						<ul>
							 如果您希望使用我们提供的默认分享模板功能，您首先需要去您的产品页为您的应用设置默认的分享模板，
							 目前我们支持您为不同的分享平台设置不同的分享模板，设置方法：开发工具-&gt;SNS分享-&gt; 设置。具体请参见文档！
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>