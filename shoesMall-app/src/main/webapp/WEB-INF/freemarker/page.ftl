<div id ="page">
    ${page.pageNo} / ${page.totalPage}&nbsp;&nbsp;
    <#if page.pageNo == 1>
        <span class="disabled"> << </span>
        <span class="disabled"> < </span>
    <#else >
        <a href="#" onclick="new Page(1).toPage()"> << </a>&nbsp;
        <a href="#" onclick="new Page(${page.pageNo -1 }).toPage()"> < </a>
    </#if>
    <#if page.pageNo-2 <=1 && (page.totalPage <= page.pageNo + 2)>
        <#list 1.. page.totalPage as index>
            <#if page.pageNo == index>
                <span class="current">${index}</span>
            <#else >
                <a href="#" onclick="new Page(${index}).toPage()">${index}</a>
            </#if>
        </#list>
    <#elseif page.pageNo - 2 <= 1 && page.totalPage &gt; page.pageNo + 2>
        <#list 1.. (page.pageNo + 2) as index>
            <#if page.pageNo == index>
                <span class="current">${index}</span>
            <#else >
                <a href="#" onclick="new Page(${index}).toPage()">${index}</a>
            </#if>
        </#list>
        <span>...</span>
        <a href="#" onclick="new Page(${page.totalPage}).toPage()">${page.totalPage}</a>
    <#elseif page.pageNo -2 &gt; 1 && page.totalPage <=page.pageNo + 2>
        <a href="#" onclick="new Page(1).toPage()">1</a>
        <span>...</span>
        <#list (page.pageNo - 2).. page.totalPage as index>
            <#if page.pageNo == index>
                <span class="current">${index}</span>
            <#else>
                <a href="#" onclick="new Page(${index}).toPage()">${index}</a>
            </#if>
        </#list>
    <#else >
        <a href="#" onclick="new Page(1).toPage()">1</a>
        <span>...</span>
        <#list (page.pageNo - 2).. (page.pageNo + 2) as index>
            <#if page.pageNo == index>
                <span class="current">${index}</span>
            <#else >
                <a href="#" onclick="new Page(${index}).toPage()">${index}</a>
            </#if>
        </#list>
        <span>...</span>
        <a href="#" onclick="new Page(${page.totalPage}).toPage()">${page.totalPage}</a>
    </#if>

    <#if page.pageNo = page.totalPage>
         <span class="disabled"> > </span>
        <span class="disabled"> >> </span>
    <#else >
        <a href="#" onclick="new Page(${page.pageNo+1}).toPage()"> > </a>
        <a href="#" onclick="new Page(${page.totalPage}).toPage()"> >></a>&nbsp;
    </#if>
    <input type="text" class="text-small" id="jump"/>
    <a href="#" onclick="new Page($('#jump').val()).toPage()">跳转</a>
</div>
<script type="text/javascript">
    Page.prototype.toPage = function (){
        $.search({
            url:'${contextPath}/${list}',
            pageNo:this.pageNo
        });
    }
</script>