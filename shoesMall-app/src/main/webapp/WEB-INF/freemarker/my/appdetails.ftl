<html>
	<head>
		<title>数据分析</title>
		<script type="text/javascript" src="${absoluteContextPath}/js/highcharts/highcharts.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/highcharts/modules/exporting.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form2json.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.ba-serializeobject.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.min.js"></script>
		<link type="text/css" href="${absoluteContextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
		<script type="text/javascript">
			$(document).ready(function() {
				$('#appDetailTabs').tabs();
			});
			var chart;
			$(document).ready(function() {
				var options = {
					chart: { renderTo: '' },
					title: { text: '用户访问报表' },
					subtitle: { text: '各sns平台访问情况' },
					xAxis: {
						type: 'datetime',
						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: -3 
						}
					},
					yAxis: [{
						title: {
							text: null
						},
						labels: {
							align: 'left',
							x: 3,
							y: 16,
							formatter: function() {
								return Highcharts.numberFormat(this.value, 0);
							}
						},
						showFirstLabel: false
					}, { // right y axis
						linkedTo: 0,
						gridLineWidth: 0,
						opposite: true,
						title: {
							text: null
						},
						labels: {
							align: 'right',
							x: -3,
							y: 16,
							formatter: function() {
								return Highcharts.numberFormat(this.value, 0);
							}
						},
						showFirstLabel: false
					}],
					legend: {
						align: 'left',
						verticalAlign: 'top',
						y: 20,
						floating: true,
						borderWidth: 0
					},
					tooltip: {
						shared: true,
						crosshairs: true
					},
					plotOptions: {
						series: {
							cursor: 'pointer',
							point: {
								events: {
									click: function() {
										hs.htmlExpand(null, {
											pageOrigin: {
												x: this.pageX, 
												y: this.pageY
											},
											headingText: this.series.name,
											maincontentText: Highcharts.dateFormat('%A, %b %e, %Y', this.x) +':<br/> '+ this.y +' visits',
											width: 200
										});
									}
								}
							},
							marker: {
								lineWidth: 1
							}
						}
					},
					series: [{
						name: '所有访客',
						lineWidth: 2,
						marker: {
							radius: 2
						},
						data: ${allVisits}
					}, {
						name: '新访客',
						data: ${allVisits}
					}]
				};
				options.chart.renderTo='container';
				chart = new Highcharts.Chart(options);
			});
			
			$(document).ready(function() {
				$('#submitAppForm').click(function() {
					 jQuery.ajax( {  
					        type : 'POST',  
					        contentType : 'application/json;charset=utf-8',  
					        url : '${absoluteContextPath}/my/apps/${app.shareKey}/modify/info',  
					        data : JSON.stringify($("#addAppForm").form2json()),  
					        success : function(jsonResponse) {  
					        	alert(JSON.stringify(jsonResponse));
					        },  
					        error : function(resp) {  
					        	alert("error!");
					        }  
					}); 
				});
				$('#submitSnsForm').click(function() {
					 jsonAppSnsListList = [];
					 $('form[name=appSnsForm]').each(function(){
						 jsonAppSnsListList.push($(this).form2json());
					 });
					 jQuery.ajax( {  
					        type : 'POST',  
					        contentType : 'application/json;charset=utf-8',  
					        url : '${absoluteContextPath}/my/apps/${app.shareKey}/modify/sns',  
					        data : JSON.stringify(jsonAppSnsListList),  
					        success : function(jsonResponse) {  
					        	alert(JSON.stringify(jsonResponse));
					        },  
					        error : function(resp) {  
					        	alert("error!");
					        }  
					}); 
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
			<b class="article_title">产品详情:<#if app??>${app.name?if_exists}</#if></b>
			<div id="appDetailTabs">
				<ul>
					<li><a href="#tabs-1">编辑应用</a></li>
					<li><a href="#tabs-2">SNS分享</a></li>
					<li><a href="#tabs-3">统计分析</a></li>
				</ul>
				<div id="tabs-1">
					<div id="wrap">
					  <div id="contentApp">
					    <div class="content_1">
					      <div class="appInfo">
							<form id="addAppForm" name="addAppForm">
						        <p class="guide"></p>
						        <p class="p1"> <span class="name left">应用名称：</span><span class="input">
						          <input id="name" name="name" value="<#if app??>${app.name?if_exists}</#if>" class="input_1" />
						          </span> </p>
						        <p class="p1"> <span class="name left">应用主页：</span><span class="input">
						          <input id="url" name="url" value="<#if app??>${app.url?if_exists}</#if>" class="input_1" />
						          </span> </p>
						        <p class="p1"> <span class="name left">平台类型：</span><span class="input">
						          <input type="radio" name="platform" id="radio" value="android" />
						          Android
						          <input type="radio" name="platform" id="radio" value="ios" />
						          iOS</span>
						        </p>
						        <p class="p1"> <span class="name left">应用类型：</span><span class="input">
						          <select class="input_1" name="category" id="category">
						            <option>请选择类型</option>
						            <option>教育</option>
						            <option>娱乐</option>
						          </select>
						          </span> 
						        </p>
						        <p class="p1"><span class="name left">备注：</span>
						        	<span class="input">
						          		<textarea class="input_2" id="description" name="description" cols="45" rows="5"><#if app??>${app.description?if_exists}</#if></textarea>
						        	</span>
						        </p>
						        <p class="p1"><span class="name left">Appkey：</span>
						        	<span class="input">
						          		${app.shareKey}
						        	</span>
						        </p>
						        <div class="btn_1"><a id="submitAppForm" class="btn_tj" href="javascript:void(0);"></a></div>
						        <div class="clear"></div>
							</form>
					      </div>
					    </div>
					  </div>
					</div>
				</div>
				<div id="tabs-2">
					<div id="wrap">
					  <div id="contentApp">
					    <div class="content_1">
					      <div class="appInfo">
						        <p class="guide"></p>
								<#list snsList as sns>
									<form name="appSnsForm">
											<#assign blank=true />
											${sns.name}：<br>
											<#list app.appSnsList as appSns>
												<#if sns.id==appSns.sns.id>
														<input type="hidden" name="snsId" value="${sns.id?c}" />
														<input type="hidden" name="id" value="${appSns.id?c}" />
														<p class="p1">
															<span class="name left">${sns.briefName} ID: </span>
															<span class="input"><input type="text" name="snsAccount" value="${appSns.snsAccount?if_exists}"/></span>
														</p>
														<p class="p1">
															<span class="name left">${sns.briefName} Key: </span>
															<span class="input"><input type="text" name="snsKey" value="${appSns.snsKey?if_exists}"/></span>
														</p>
														<p class="p1">
															<span class="name left">${sns.briefName} Secret: </span>
															<span class="input"><input type="text" name="snsSecret" value="${appSns.snsSecret?if_exists}"/></span>
														</p>
														<p class="p1">
															<span class="name left">${sns.briefName} Templet: </span>
															<span class="input"><input type="text" name="templet" value="${appSns.templet?if_exists}"/></span>
														</p>
													<#assign blank=false />
													<#break>
												</#if>
											</#list>
											<#if blank>
												<input type="hidden" name="snsId" value="${sns.id?c}" />
												<p class="p1">
													<span class="name left">${sns.briefName} ID: </span>
													<span class="input"><input name="snsAccount" type="text" /></span>
												</p>
												<p class="p1">
													<span class="name left">${sns.briefName} Key: </span>
													<span class="input"><input name="snsKey" type="text" /></span>
												</p>
												<p class="p1">
													<span class="name left">${sns.briefName} Secret: </span>
													<span class="input"><input name="snsSecret" type="text" /></span>
												</p>
												<p class="p1">
													<span class="name left">${sns.briefName} Templet: </span>
													<span class="input"><input name="templet" type="text" /></span>
												</p>
											</#if>
									</form>
								</#list>
						        <div class="btn_1"><a id="submitSnsForm" class="btn_tj" href="javascript:void(0);"></a></div>
						        <div class="clear"></div>
					      </div>
					    </div>
					  </div>
					</div>
				</div>
				<div id="tabs-3">
					<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
				</div>
			</div>
		</div>
	</body>
</html>
