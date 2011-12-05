<html>
	<head>
		<title>HAHA</title>
		<script type="text/javascript" src="${absoluteContextPath}/js/highcharts/highcharts.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/highcharts/modules/exporting.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form2json.js"></script>
		<script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.ba-serializeobject.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#submitSnsForm1').click(function() {
					 jQuery.ajax( {  
					        type : 'POST',  
					        contentType : 'application/json;charset=utf-8',  
					        url : '${absoluteContextPath}/sns/login/sina',  
					        data : JSON.stringify($('#addAppForm1').form2json()),  
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
		<form id="addAppForm1" method="post" enctype="multipart/form-data">
		 	<input type="text" name="imei"/>
		 	<input type="text" name="shareKey" value="81f7e437faa5a7fce15d1ddcb9eaeaea377667b8"/>
		 	<input type="text" name="action"/>
		 	<input type="text" name="additional"/>
			<a id="submitSnsForm1" name="submitSnsForm1" type="button" href='javascript:void(0);'>提交了啦</a>
		</form>
		<form id="addAppForm2" method="post" enctype="multipart/form-data">
		 	<input type="text" name="filename"/>
			<input type="file" name="file" />
			<a id="submitSnsForm2" name="submitSnsForm2" type="button" href='javascript:void(0);'>提交了啦</a>
		</form>
	</body>
</html>