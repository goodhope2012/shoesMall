<html>
	<head>
		<title>数据分析</title>
		<script type="text/javascript" src="${absoluteContextPath}/js/highcharts/highcharts.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/highcharts/modules/exporting.js"></script>
		<script type="text/javascript">
			var chart;
			$(document).ready(function() {
				var options = {
					chart: { renderTo: 'container' },
					title: { text: 'Daily visits at www.highcharts.com' },
					subtitle: { text: 'Source: Google Analytics' },
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
					yAxis: [{ // left y axis
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
											maincontentText: Highcharts.dateFormat('%A, %b %e, %Y', this.x) +':<br/> '+ 
												this.y +' visits',
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
						name: 'All visits',
						lineWidth: 4,
						marker: {
							radius: 4
						}
					}, {
						name: 'New visitors'
					}]
				};
				// Load data asynchronously using jQuery. On success, add the data
				jQuery.get('analytics.tsv', null, function(tsv, state, xhr) {
					var lines = [],
						listen = false,
						date,
						// set up the two data series
						allVisits = [],
						newVisitors = [];
					// inconsistency
					if (typeof tsv !== 'string') {
						tsv = xhr.responseText;
					}
					// split the data return into lines and parse them
					tsv = tsv.split(/\n/g);
					jQuery.each(tsv, function(i, line) {
						// listen for data lines between the Graph and Table headers
						if (tsv[i - 3] == '# Graph') {
							listen = true;
						} else if (line == '' || line.charAt(0) == '#') {
							listen = false;
						}
						// all data lines start with a double quote
						if (listen) {
							line = line.split(/\t/);
							date = Date.parse(line[0] +' UTC');
							allVisits.push([
								date, 
								parseInt(line[1].replace(',', ''), 10)
							]);
							newVisitors.push([
								date, 
								parseInt(line[2].replace(',', ''), 10)
							]);
						}
					});
					options.series[0].data = allVisits;
					options.series[1].data = newVisitors;
					chart = new Highcharts.Chart(options);
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
			<b class="article_title">应用数据统计分析</b>
			<ul class="about">	
				<li>		
					<h3>实时统计:</h3>		
					帮助您掌握应用的总体发展状况。包括当天的启动次数、启动用户、新用户等信息。
					<p><img border="0" src="${absoluteContextPath}/images/statistic_image001.jpg"></p>
				</li>	
				<li>		
					<h3>时段分析:</h3>		
					掌握用户的使用时段的规律，及时发现问题。
					<p><img border="0" src="${absoluteContextPath}/images/statistic_image002.jpg"></p>
				</li>	
				<li>		
					<h3>趋势分析:</h3>		
					通过观察新增用户、启动用户等的变化趋势，评估一段时间内的应用表现。
					<p><img border="0" src="${absoluteContextPath}/images/statistic_image003.jpg"></p>
				</li>	
				<li>		
					<h3>版本分析:</h3>		
					掌握用户的版本分布情况，比较不同版本的用户活跃程度。
					<p><img border="0" src="${absoluteContextPath}/images/statistic_image004.jpg"></p>
				</li>
			</ul>
			<div style="clear:both;"></div>
			<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
		</div>
	</body>
</html>
