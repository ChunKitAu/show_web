$(function () {
    //获取url参数
    var parameterURL =decodeURI(location.search.substring(1, location.search.length));
    var params = null;

    if(parameterURL != null && parameterURL.length > 0){
        //获取id
        params = parameterURL.substring(0,2);

        if(params=="id"){
            var id = parameterURL.substring(3,parameterURL.indexOf("&"));

            //显示位置：

            var t = parameterURL.substring(parameterURL.indexOf("&")+6,parameterURL.length);
            $("#localA").append(t).attr("href","/list?type="+t);

            initById(id);




        }else{
            var type = parameterURL.substring(5,parameterURL.length);
            $("#localA").append(type).attr("href","/list?type="+type);
            initByType(type);
        }



    }

})

function initById(id){
    $.ajax({
        url: "/article/" + id,
        type: "GET",
        success: function (result) {
            if (result.code == 200) {
                //数据回显
                $("#table_t1").append(result.data.title);

                $("#table_t2").append("时间:"+result.data.time+"  作者:"+ result.data.author+"  来源："+ result.data.reblog+"  阅读次数："+result.data.readtimes);

                $("#contentDiv").html(result.data.content);
            }
        }
    })
}

function initByType(type){
    $.ajax({
        url: "/article/listByType",
        type: "GET",
        data:{type:type},
        success: function (result) {
            if (result.code == 200) {
                //数据回显
                $("#table_t1").append(result.data.list[0].title);
                $("#table_t2").append("");
                // $("#table_t2").append("时间:"+result.data.list[0].time+"  作者:"+ result.data.list[0].author+"  来源："+ result.data.list[0].reblog+"  阅读次数："+result.data.list[0].readtimes);

                $("#contentDiv").html(result.data.list[0].content);
            }
        }
    })
}
