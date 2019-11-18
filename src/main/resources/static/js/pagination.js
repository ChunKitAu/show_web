//解析分页信息
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
        result.extend.pageInfo.pages + ",总"
        + result.extend.pageInfo.total + "记录")
    totalRecord = result.extend.pageInfo.total;
    currentPage = result.extend.pageInfo.pageNum;
}

//解析显示分页条
function build_page_nav(result) {
    //page_nav_area

    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination pagination-lg");

    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));

    if (result.extend.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页事件
        firstPageLi.click(function () {
            toPage(1);
        });
        prePageLi.click(function () {
            toPage(result.extend.pageInfo.pageNum - 1);
        });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageli = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));

    if (result.extend.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageli.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            toPage(result.extend.pageInfo.pageNum + 1);
        });
        lastPageli.click(function () {
            toPage(result.extend.pageInfo.pages);
        });
    }

    //添加首页和前一页信息
    ul.append(firstPageLi).append(prePageLi);
    //遍历给ul添加页码提示
    $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.pageInfo.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            toPage(item);
        });
        ul.append(numLi);
    });
    //添加下一页和末页信息
    ul.append(nextPageLi).append(lastPageli);

    //把ul加入到nav中
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");

}