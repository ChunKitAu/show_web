$(function () {
    toPage(1);
});

function toPage(pn) {
    var totalRecord, currentPage;

    //获取url参数   decodeURI  解析中文参数问题

    var parameterURL =decodeURI(location.search.substring(1, location.search.length));

    // alert(parameterURL);s
    if(parameterURL != null && parameterURL.length > 0){
        //获取文章类型
        var type = parameterURL.substring(5,parameterURL.length);
        $("#localA").append(type).attr("href","/list?type="+type);

        getList(type);
    }


}

function getList(type) {
    $.ajax({
        url: "/article/listByType",
        type: "GET",
        data:{type:type},
        success: function (result) {

            $("#newDiv").empty();
            if (result.code == 200) {

                var contents = result.data.list;
                //数据回显
                $.each(contents,function (index,item) {
                    var a = document.createElement('a');
                    a.href="/index_article?id="+item.id+"&type="+type;

                    var div = document.createElement('div');
                    div.append(item.title);

                    var span = document.createElement('span');
                    span.innerText = item.time;

                    a.appendChild(div);
                    a.appendChild(span);

                    $("#newDiv").append(a);
                })

                // //2.解析并显示分页信息
                build_page_info(result);
                // //3.解析显示分页条信息
                build_page_nav(result);

            }
        }
    })
}