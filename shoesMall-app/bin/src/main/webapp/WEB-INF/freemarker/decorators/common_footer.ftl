<#ftl encoding="utf-8"/>

<link rel="stylesheet" href="${absoluteContextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.css" type="text/css" />
<script type="text/javascript" src="${absoluteContextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form.js"></script>
<script>
	$(document).ready(function(){
		// Dialog			
		$('#dialog').dialog({
			modal: true,
			autoOpen: false,
			width: 400,
			buttons: {
				"Ok": function() { 
					$("#feedback").ajaxSubmit();
					$(this).dialog("close"); 
				}, 
				"Cancel": function() { 
					$(this).dialog("close"); 
				} 
			}
		});
		// Dialog Link
		$('#fdbk_tab').click(function(){
			$('#dialog').dialog('open');
			return false;
		});
	});
</script>
<a href="#" id="fdbk_tab" class="fdbk_tab_right">FEEDBACK</a>
<div id="dialog" title="Feedback">
	<div>
		<form id="feedback" action="${absoluteContextPath}/feedback" method="post">
			email:<br>
			<input type="text" name="email" /><br>
			tel:<br>
			<input type="text" name="tel" /><br>
			topic:<br>
			<input type="text" name="topic" /><br>
			feedback:<br>
			<textarea name="additional" id="content" class="mceEditor" tabindex="2" style="width:90%;padding:8px;"></textarea>
		</form>
	</div>
</div>

<div style='clear:both;'></div>
<div class="bottom">
	<div class="bottom_link">
		<dl>
	      <dt>Cocos产品</dt>
	      <dd><a href="http://cocos2d-x.org" target="_blank">cocos2d-x</a></dd>
	      <dd><a href="#">share-x</a></dd>
	      <dd><a href="#">admob-x</a></dd>
	    </dl>
		<dl>
	      <dt>开发者中心</dt>
	      <dd><a href="/">开发者沙龙</a></dd>
	      <dd><a href="/">SDK center</a></dd>
	      <dd><a href="/">document</a></dd>
	    </dl>
		<dl>
	      <dt>数据报告</dt>
	      <dd><a href="/">行业数据报告</a></dd>
	    </dl>
		<dl>
	      <dt>关于我们</dt>
	      <dd><a href="/">Cocos2d-x team</a></dd>
	      <dd><a href="/">联系我们</a></dd>
	      <dd><a href="/">招贤纳士</a></dd>
	      <dd><a href="/">合作伙伴</a></dd>
	    </dl>
		<dl>
	      <dt>用户中心</dt>
	      <dd><a href="${absoluteContextPath}/cas/login">登录</a></dd>
	      <dd><a href="${absoluteContextPath}/cas/register">注册</a></dd>
	    </dl>
		<dl class="follow">
	      <dt>关注我们</dt>
	      <dd><a href="http://share.cocos2d-x.org:8080" target="_blank">参与blog讨论</a></dd>
	      <dd><form id="form1" name="form1" method="post" action="">
			      <label>
			        <input type="text" name="textfield" id="textfield"  class="bottom_input_text" value="enter your email here"/>
			      </label>
			      <label>
			        <input type="submit" name="button" id="button" value=" " class="bottom_input_botton" />
			      </label>
			   </form>
		  </dd>
	    </dl>
		<div style='clear:both;'></div>
	</div>
	<div class="bottom_con">
		<p>
			<script type="text/javascript">
				var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
				document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fb1d34ba141bde6e683a24f7f3476063a' type='text/javascript'%3E%3C/script%3E"));
			</script>
			Copyright © 2011-2018 Share-X(share.cocos2d-x.org) all rights reserved.
		</p>
	</div>
</div>