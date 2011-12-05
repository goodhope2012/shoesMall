(function($) {
    $.search = function(options) {
        var defaults = {
            type : 'post',
            searchId : '#search_l',
            url : '',
            param: [],
            pageNo:1,
            order:$('#order_l').val() != undefined?$('#order_l').val():'',
            orderBy:$('#orderBy_l').val()!=undefined?$('#orderBy_l').val():'',
            lightTableId:'',
            result:function(data){}
        };
        if (options != undefined && options.orderBy != undefined ) {
            if (defaults.order == 'asc'){
                options.order = 'desc';
            } else {
                options.order = 'asc';
            }
        }
        options = $.extend(true, {}, defaults,options);
        if (options.type == 'post') {
            submitSearch(options);
        } else if (options.type== 'ajax') {
            options.type = 'post';
            if (options.lightTableId == '') {
                alert('ajax type,lightTableId can not be null');
                return false;
            }
            ajaxSearch(options);
        }
    };

    $.initSearch = function(search,order,orderBy,options) {
        var defaults = {
            searchId : '#search_l'
        };
        options = $.extend(true, {}, defaults, options);
        if (search != '' && search != undefined) {
            var searchObjects = JSON.parse(search);
            for (var i = 0; i < searchObjects.length; i++) {
                var name = 'search__' + searchObjects[i].propertyName ;
                if (searchObjects[i].condition != undefined && searchObjects[i].condition != ''){
                    name = name +  '__'+searchObjects[i].condition ;
                }
                $(options.searchId).find('[name="' + name + '"]').val(searchObjects[i].value);
            }
        }
        if (order != '' && orderBy != ''){
            var orderL = jQuery('<input type="hidden" id="order_l">');
            var orderByL = jQuery('<input type="hidden" id="orderBy_l">');
            jQuery(orderL).val(order);
            jQuery(orderByL).val(orderBy);
            jQuery(orderL).appendTo('body');
            jQuery(orderByL).appendTo('body');
        }
    };

    function ajaxSearch(options){
        var defaults = {
            success:function(json){
                var success = JSON.parse(json);
                if (success.success != undefined) {
                    var data = success.success;
                    $('#' + data.lightTableId).html(data.data);
                    $.initSearch(data.search, data.order, data.orderBy);
                } else {
                    showError(data.error,'slow',15000);
                }
                options.result();
            }
        };
        options = $.extend(true, {}, jQuery.ajaxSettings, options,defaults);
        var json = searchJson(options.searchId);
        if (json != '') {
            options.param.push('search='+json);
        }
        if (options.order != '' && options.orderBy!= ''){
            options.param.push('order='+options.order);
            options.param.push('orderBy='+options.orderBy);
        }
        options.param.push('pageNo='+options.pageNo);
        options.param.push('lightTableId=table_l_'+options.lightTableId);
        options.data = options.param.join("&");
        $.ajax(options);
    }

    function submitSearch(options){
        var createForm = function(){
            //create form
            var form = jQuery('<form  action="'+options.url+'" method="post"></form>');
            if (options.param.length > 0) {
                for (var i in options.param){
                    for (var property in options.param[i]){
                        jQuery('<input type=\'hidden\' name=\'' + property + '\' value=\'' + options.param[i][property] + '\' />').appendTo(form);
                    }
                }
            }
            jQuery(form).appendTo('body');
            return form;
        };
        var json = searchJson(options.searchId);
        if (json != '') {
            options.param.push({'search':json});
        }
        if (options.order != '' && options.orderBy!= ''){
            options.param.push({order:options.order});
            options.param.push({orderBy:options.orderBy});
        }
        options.param.push({'pageNo':options.pageNo});
        var form = createForm();
        form.submit();
    }

    function searchJson(id){
        if ($(id) != undefined) {
            var objects=[];
            $(id).find('[name^="search"]').each(function(){
                var name = $(this).attr('name');
                var split = name.split('__');
                var key = split[1];
                if (split.length >= 2) {
                    var condition = split[2];
                }
                if ($(this).val() != '' && $(this).val() != null && $(this).val() != undefined) {
                    objects.push(new SearchObject(key, condition, $(this).val()));
                }
            });
            if (objects.length > 0) {
                return JSON.stringify(objects);
            }
        }
        return '';
    }

    $.fn.submitResult = function(data,options){
        var defaults = {
            css:'',   //todo
            opacity:'slow',
            delay:15000
        };
        options = $.extend(true, {}, defaults, options);
        var result = JSON.parse(data);
        if (result.error != undefined) {
            if (Object.prototype.toString.apply(result.error) === '[object Array]') {
                $(this).find('.form_error_l').remove();
                for (var i = 0 ;i < result.error.length;i++) {
                    for (var property in result.error[i]){
                        var span = jQuery('<span class="form_error_l">' + result.error[i][property]+'</span> ');
                        jQuery(span).insertAfter($(this).find('[name="'+property+'"]'));
                    }
                }
            } else {
                showError(result.error, options.opacity, options.delay);
            }
        } else {
            showSuccess(result.success, options.opacity, options.delay);
        }
    };

    function showError(error,opacity,delay) {
        $('#error_message').html(error);
        $('#error_message').fadeIn('slow',null);
        setTimeout(function(){$('#error_message').html('');$('#errorMessage').fadeOut(opacity,null);}, delay);
    }

    function showSuccess(success,opacity,delay){
        $('#success_message').html(success);
        $('#success_message').fadeIn('slow',null);
        setTimeout(function(){$('#success_message').html('');$('#successMessage').fadeOut(opacity,null);}, delay);
    }

    $.fn.highChart = function(options,highOptions){
        var defaults = {
            title:'',
            subTitle:''
        };
        options = $.extend(true, {}, defaults, options);
        var tmpHighOptions = {};
        tmpHighOptions.chart = {renderTo:$(this).attr('id')};
        tmpHighOptions.title = {text:options.title};
        tmpHighOptions.subTitle = {text:options.subTitle};
        tmpHighOptions.toolTip = {formatter:options.toolTip};
        tmpHighOptions.plotOptions = options.plotOptions;
        tmpHighOptions.credits = {
            href:'www.chinanetcenter.com',
            text:'fuckgfw'
        },

        highOptions = $.extend(true, {}, tmpHighOptions, highOptions);
        new Highcharts.Chart(highOptions);
    };

    $.fn.pieChart = function(data,options,highOptions) {
        var defaults = {
            unit:'',
            toolTip:function(){
                return '<b>'+ this.point.name +'</b>: '+ this.y + ' ' + defaults.unit;
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.y + ' ' + defaults.unit ;
                        }
                    }
                }
            }
        };
        options = $.extend(true, {}, defaults, options);

        var tmpHighOptions = {};
        tmpHighOptions.chart = {
            defaultSeriesType: 'pie'
        },
        tmpHighOptions.series = [{
            name:data.name,
            data:data.data
        }];
        $(this).highChart(options,tmpHighOptions);
    };

    $.fn.lineChart = function(data, options, highOptions){
        dChart(this,'line', data, options, highOptions);
    };

    $.fn.barChart = function(data,options,highOptions){
        dChart(this,'bar', data, options, highOptions);
    };

    $.fn.columnChart = function(data,options,highOptions){
        dChart(this,'column', data, options, highOptions);
    };

    function dChart(id,type,data,options,highOptions){
        var defaults = {
            unit:'',
            tooltip: {
                formatter: function() {
                    return ''+this.series.name +': '+ this.y + ' ' + defaults.unit;
                }
            }
        };
        options = $.extend(true, {}, defaults, options);
        var tmpHighOptions = {};
        tmpHighOptions.chart = {
            defaultSeriesType: type
        };
        tmpHighOptions.xAxis = {"categories":data.categories};
        tmpHighOptions.series = data.data;
        $.extend(true, tmpHighOptions, highOptions);
        $(id).highChart(options, tmpHighOptions);
    }
})(jQuery);


function Page(pageNo) {
    this.pageNo = pageNo;
}
/**
 * default submit search
 */
Page.prototype.toPage = function(){} ;

function SearchObject(key,condition,value) {
    this.propertyName = key;
    this.condition = condition;
    this.value = value;
}

SearchObject.prototype.EQ = "eq";
SearchObject.prototype.LT = "lt";
SearchObject.prototype.GT = "gt";
SearchObject.prototype.LIKE = "like";
