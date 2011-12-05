<#import "../light.ftl" as light>
<html>
<head>
    <title>user</title>
    <script type="text/javascript" src="${contextPath}/script/jquery/highcharts.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $.ajax({
                url: '${contextPath}/user/statistics',
                success:function(data){
                    var d = JSON.parse(data);
                    $('#chart').pieChart(d,{
                        title:'user first char statistics'
                    })
                }
            })
        });
        function ajax() {
            $.search({
                type:'ajax',
                url:'${contextPath}/user/listAjax',
                lightTableId:'test'
            })
        }
    </script>
</head>
<body>
    <@light.table id="test">
        <table cellpadding="0" cellspacing="0">
            <caption><em>user table example</em></caption>
            <thead>
                <tr>
                    <td colspan="4">
                        <div id="search_l">
                            <label> username
                                <select name="search__name__like">
                                    <option value="admin">admin</option>
                                    <option value="chen">chen</option>
                                </select>
                            </label>
                            <label>email:
                                <input type="text" name="search__email__like">
                            </label>
                            <a href="#" onclick="ajax()">查询</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><a href="#" onclick="$.search({orderBy:'name'})">name</a></th>
                    <th><a href="#" onclick="$.search({orderBy:'email'})">email</a></th>
                    <th>password</th>
                    <th>status</th>
                </tr>
            </thead>
            <tbody>
                <#list page.result as user>
                <tr>
                    <td>${user.name!""}</td>
                    <td>${user.email!""}</td>
                    <td>${user.password!""}</td>
                    <td><#if user.status == 1>激活<#else>未激活</#if></td>
                </tr>
                </#list>
            <tfoot>
                <tr>
                    <td colspan="4">
                        <#include "../page.ftl">
                    </td>
                </tr>
            </tfoot>
            </tbody>
        </table>
    </@light.table>
    <div id="chart"></div>
</body>
</html>