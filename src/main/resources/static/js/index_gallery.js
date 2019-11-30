
<!-- 画廊-->
$(function () {
    initGallery();
});


function initGallery() {
    $.ajax({
        url:"Gallery/isSelect",
        type:"GET",
        success:function (result) {

            var gallerys = result.data;
            $(".carousel-inner").empty();

            //动态生成画廊图片
            $.each(gallerys,function (index,item) {
                //小圆点
                // <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                var Li = $("<li></li>").attr("data-target","#myCarousel")
                                       .attr("data-slide-to",index-1);
                if(index-1 == 0) Li.addClass("active");
                Li.appendTo(".carousel-indicators");

                //图片
                var div = $("<div></div>").addClass("item");
                if(index == 1) div.addClass("active");
                //.addClass("img-responsive center-block")
                var img = $("<img></img>").attr("src",item.img).attr("alt",item.id);
                div.append(img).appendTo(".carousel-inner");
            })
        },
        error:function () {
            alert("error!");
        },
    })
}