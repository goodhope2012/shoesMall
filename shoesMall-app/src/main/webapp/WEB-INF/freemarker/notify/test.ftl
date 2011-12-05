<html xmln>
<head>
    <title>user-form</title>
    <script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${absoluteContextPath}/js/jquery/jquery.ba-serializeobject.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#form').bind('submit', function(e) {
                e.preventDefault();
                //$(this).ajaxSubmit({
                    //success:function(data){
                        //$('#form').submitResult(data);//submitResult is your js function
                    //}
                //});
                 var elemUserinfo = $('#form');  
			     var userinfo = elemUserinfo.serializeObject();
			     var jsonuserinfo =JSON.stringify(userinfo);  
			     //var jsonuserinfo = "["+jsonuserinfo+","+jsonuserinfo+"]";  
			     alert(jsonuserinfo);
				 jQuery.ajax( {  
				        type : 'POST',  
				        contentType : 'application/json',  
				        url : '${absoluteContextPath}/init',  
				        //url : '${absoluteContextPath}/notify/batch',  
				        data : jsonuserinfo,  
				        dataType : 'json',  
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
    <form action="${absoluteContextPath}/notify" method="post" id="form">
        <fieldset>
            <input type="hidden" id="shareKey" name="shareKey" value="81f7e437faa5a7fce15d1ddcb9eaeaea377667b8">
            <legend>TEST</legend>
            <p>
                <label for="name">action</label><br/>
                <input type="text" id="action" name="action" value="">
            </p>
            <p>
                <label for="result">result</label><br/>
                <input type="text" id="result" name="result" value="">
            </p>
            <p>
                <input type="submit" value="保存">
            </p>
        </fieldset>
    </form>
</body>
</html>