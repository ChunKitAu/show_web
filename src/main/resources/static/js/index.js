$(function () {
    getDemoList();//加载项目案例/成果 列表
    getLearningList();//加载学术动态列表


})

function getDemoList() {
    $.ajax({
        url: "/article/listByType",
        type: "GET",
        data:{type:"项目案例/成果"},
        success: function (result) {
            if (result.code == 200) {
                //数据回显
                var contents = result.data.list;
                $("#new1").empty();
                $("#new1").append("<ul></ul>");
                $.each(contents,function (index,item) {
                    var li = document.createElement('li');


                    var a = document.createElement('a');
                    a.innerText = item.title;
                    a.href="/index_article?id="+item.id;
                    var span = document.createElement('span');
                    span.innerText = item.time.substring(1,10);

                    li.appendChild(a);
                    li.appendChild(span);
                    $("#new1 ul").append(li);
                })

            }
        }
    })
}

function getLearningList() {
    $.ajax({
        url: "/article/listByType",
        type: "GET",
        data:{type:"学术动态"},
        success: function (result) {
            $("#learning").empty();
            if (result.code == 200) {
                //数据回显
                var contents = result.data.list;

                $("#new2").empty();
                $("#new2").append("<ul></ul>");
                $.each(contents,function (index,item) {
                    var li = document.createElement('li');

                    var a = document.createElement('a');
                    a.innerText = item.title;
                    a.href="/index_article?id="+item.id;
                    var span = document.createElement('span');
                    span.innerText = item.time.substring(1,10);

                    li.appendChild(a);
                    li.appendChild(span);
                    $("#new2 ul").append(li);
                })
            }
        }
    })
}



