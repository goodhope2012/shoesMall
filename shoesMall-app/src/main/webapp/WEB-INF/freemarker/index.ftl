<#ftl encoding="utf-8"/>
<html>
	<head>
		<title>Welcome!</title>
		<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/js/lof-slide/lof-slide.css" />
		<script language="javascript" type="text/javascript" src="${absoluteContextPath}/js/jquery.easing.js"></script>
		<script language="javascript" type="text/javascript" src="${absoluteContextPath}/js/lof-slide/lof-slide.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${absoluteContextPath}/js/slidesjs/slidesjs.css" />
		<script language="javascript" type="text/javascript" src="${absoluteContextPath}/js/slidesjs/slides.min.jquery.js"></script>
		
		<script type="text/javascript">
			$(document).ready( function(){	
				var buttons =	{ previous	: $('#lofslidecontent45 .lof-previous'),
									next	: $('#lofslidecontent45 .lof-next')
								};
				$obj = $('#lofslidecontent45').lofJSidernews({ 
							interval 		: 4000,
							direction		: 'opacity',	
						 	easing			: 'easeOutBounce',
							duration		: 1200,
							auto		 	: true,
							mainWidth		: 600,
							buttons			: buttons 
						});	
			});
			
			$(function(){
				var startSlide = 1;
				$('#slides_2').slides({
					effect: 'fade, fade',
					preload: true,
					preloadImage: '${absoluteContextPath}/js/slidesjs/loading.gif',
					generatePagination: true,
					prependPagination: true,
					play: 6000,
					pause: 3000,
					hoverPause: true,
					// Get the starting slide
					start: startSlide
				});
				$('#slides_3').slides({
					effect: 'fade, fade',
					preload: true,
					preloadImage: '${absoluteContextPath}/js/slidesjs/loading.gif',
					generatePagination: false,
					prependPagination: false,
					play: 2000,
					pause: 1500,
					hoverPause: true,
					start: startSlide
				});
			});
		</script>
	</head>
	<body>
		<div class="main_banner">
			<div class="main_banner_sub">
				<div id="summaryInfo" class="lof-slidecontent summaryInfo" style="width:320px; height:340px;">
					<br>
					<br>
					<br>
					<p class='orange'>加Share-X分享按钮</p>
					<p class='orange'>稳步提升您的app流量！</p>
					<br>
	                <h3>
	                	稳步提升网站流量和搜索引擎排名的SMO工具！
						<i> — 最大的移动社会化分享按钮及代码提供商：</i>
					</h3>
					<h2>提供分享到新浪微博、腾讯微博、twitter、facebook等SDK...</h2>
	                <p>
	                	通过访客不断的分享行为，来提升移动app的优质外链、增加社会化流量、带来更多的用户！
	                	<a class="readmore" href="#">Read more </a>
	                </p>
				</div>
				<div id="lofslidecontent45" class="lof-slidecontent" style="float:right; width:600px; height:340px;">
					<div class="preload"><div></div></div>
				  	<div class="lof-main-outer" style="width:600px; height:340px;">
				    	<div onclick="return false" href="" class="lof-previous">Previous</div>
					  	<ul class="lof-main-wapper"> 
					  		<li> 
				        		<img src="${absoluteContextPath}/images/thumbl_980x340_002.png" title="Newsflash 2" >           
				                <div class="lof-main-item-desc"> 
					                <h3><a target="_parent" title="Newsflash 1" href="#Category-1">/ Newsflash 1 /</a> <i> — Monday, February 15, 2010 12:42</i></h3> 
									<h2>Content of Newsflash 1</h2> 
					                <p>The one thing about a Web site, it always changes! Joomla! makes it easy to add Articles, content,...
					                	<a class="readmore" href="#">Read more </a> 
					                </p> 
					             </div> 
					        </li> 
					       <li> 
					       	  	 <img src="${absoluteContextPath}/images/thumbl_980x340_003.png" title="Newsflash 1" >           
					          	 <div class="lof-main-item-desc"> 
					                <h3><a target="_parent" title="Newsflash 2" href="#Category-2">/ Newsflash 2 /</a> 	<i> — Monday, February 15, 2010 12:42</i></h3> 
					                <h2>Content of Newsflash 2</h2> 
					                <p>Joomla! makes it easy to launch a Web site of any kind. Whether you want a brochure site or you are...
					                <a class="readmore" href="#">Read more </a> 
					                </p> 
					             </div> 
					        </li> 
					        <li> 
					       	  	<img src="${absoluteContextPath}/images/thumbl_980x340_006.png" title="Newsflash 5" >            
					          	<div class="lof-main-item-desc"> 
					                <h3><a target="_parent" title="Newsflash 4" href="#Category-4">/ Newsflash 4 /</a>	<i> — Monday, February 15, 2010 12:42</i></h3> 
					                <h2>Content of Newsflash 4</h2> 
					                <p>Joomla! 1.5 - 'Experience the Freedom'!. It has never been easier to create your own dynamic Web...
					                <a class="readmore" href="#">Read more </a> 
					                </p> 
					             </div> 
					        </li> 
					        <li> 
					       	  	<img src="${absoluteContextPath}/images/thumbl_980x340_008.png" title="Newsflash 5" >            
					          	<div class="lof-main-item-desc"> 
					                <h3><a target="_parent" title="Newsflash 4" href="#Category-4">/ Newsflash 4 /</a>	<i> — Monday, February 15, 2010 12:42</i></h3> 
					                <h2>Content of Newsflash 4</h2> 
					                <p>Joomla! 1.5 - 'Experience the Freedom'!. It has never been easier to create your own dynamic Web...
					                <a class="readmore" href="#">Read more </a> 
					                </p> 
					             </div> 
					        </li> 
					      </ul>  	
				      	<div onclick="return false" href="" class="lof-next">Next</div>
				  	</div>
				 </div> 
				 <div style="clear:both;"></div>
			</div>
			<div id="midInfoTop"></div>
		</div>
		<div id="midInfoBottom">
			<br>
			 Try it out.
			<br>
		</div>
		<div class="main_pc">
			 <div class="index_pc">
			    <div class="index_pc01">
			    	<ul class="slide_title"><b>What's New</b></ul>
				    <dl>
					    <dt><img src="${absoluteContextPath}/images/index_img01.png" /></dt>
					    <dd>Share-X beta版本发布 2011-11-15 »</dd>
					    <dd>Share-X 添加twitter分享功能! »</dd>
					    <dd style=" text-align:right;"><a href="#"><img src="${absoluteContextPath}/images/index_more.png" /></a></dd>
				    </dl>
			    </div>
				<div class="index_pc01">
					<div id="slides_2">
						<div style="clear:both"></div>
						<div class="slides_container">	
							<div class="slide">
							    <dl>
								    <dt></dt>
								    <dd><b>Get the Button</b></dd>
								    <dd>Established in November, 2006, Shangyu Seeway Gloves Co., Ltd. is located in Shangyu City,</dd>
								    <dd style=" text-align:right;"><a href="#" class="links">see more »</a></dd>
							    </dl>
							</div>
							<div class="slide">
								<h1>Fifth Slide</h1>
								<p>ecat cupidatat non proident.</p>
								<p><a href="#7" class="links">Check out the seventh slide &rsaquo;</a></p>
							</div>
							<div class="slide">
								<h1>Sixth Slide</h1>
								<p>ugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
								<p><a href="#1" class="links">Check out the first slide &rsaquo;</a></p>
							</div>
							<div class="slide">
								<h1>Seventh Slide</h1>
								<p>upidatat non proident.</p>
								<p><a href="#3" class="links">Check out the third slide &rsaquo;</a></p>
							</div>
						</div>
					</div>
			    </div>
			    <div class="index_pc01 index_pc02">
					<div id="slides_3">
						<ul class="slide_title"><b>COCOS博客</b></ul>
						<div style="clear:both"></div>
						<div class="slides_container">	
							<div class="slide">
								<h1>Trending Now</h1>
								<p>lamco labatat non proident.</p>
								<p><a href="#6" class="links">Check out the sixth slide &rsaquo;</a></p>
							</div>
							<div class="slide">
								<h1>Fifth Slide</h1>
								<p>ecat cupidatat non proident.</p>
								<p><a href="#7" class="links">Check out the seventh slide &rsaquo;</a></p>
							</div>
							<div class="slide">
								<h1>Sixth Slide</h1>
								<p>ugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident.</p>
								<p><a href="#1" class="links">Check out the first slide &rsaquo;</a></p>
							</div>
							<div class="slide">
								<h1>Seventh Slide</h1>
								<p>upidatat non proident.</p>
								<p><a href="#3" class="links">Check out the third slide &rsaquo;</a></p>
							</div>
						</div>
					</div>
			    </div>
			  </div>
			  <div style="clear:both;"></div>
		 </div> 
	</body>
</html>