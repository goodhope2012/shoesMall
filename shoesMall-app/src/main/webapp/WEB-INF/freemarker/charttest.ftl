<html>
<head>
    <title>user</title>
    <script type="text/javascript" src="${absoluteContextPath}/js/jquery/highcharts.js"></script>
    <script type="text/javascript">
		var chart;
		$(document).ready(function() {
		   chart = new Highcharts.Chart({
		      chart: {
		         renderTo: 'container',
		         plotBackgroundColor: null,
		         plotBorderWidth: null,
		         plotShadow: false
		      },
		      title: {
		         text: 'Browser market shares at a specific website, 2010'
		      },
		      tooltip: {
		         formatter: function() {
		            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
		         }
		      },
		      plotOptions: {
		         pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		               enabled: true,
		               color: Highcharts.theme.textColor || '#000000',
		               connectorColor: Highcharts.theme.textColor || '#000000',
		               formatter: function() {
		                  return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
		               }
		            }
		         }
		      },
		       series: [{
		         type: 'pie',
		         name: 'Browser share',
		         data: [
		            ['Firefox',   45.0],
		            ['IE',       26.8],
		            {
		               name: 'Chrome',    
		               y: 12.8,
		               sliced: true,
		               selected: true
		            },
		            ['Safari',    8.5],
		            ['Opera',     6.2],
		            ['Others',   0.7]
		         ]
		      }]
		   });
		});
    </script>
</head>
<body>
    <div id="container"></div>
</body>
</html>