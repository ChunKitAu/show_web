
<!-- 画廊-->
$(function () {
    initGallery();
});


function initGallery() {
    $.ajax({
        url:"Gallery/isSelect",
        type:"GET",
        success:function (result) {
            console.log(result);

            var gallerys = result.data;
            $(".carousel-inner").empty();

            //动态生成画廊图片
            $.each(gallerys,function (index,item) {
                var div = $("<div></div>").addClass("item");
                if(index == 1) div.addClass("active");

                var img = $("<img></img>").attr("src",item.img).addClass("img-responsive center-block").attr("alt",item.id);
                div.append(img).appendTo(".carousel-inner");
            })
        },
        error:function () {
            alert("error!");
        },
    })
}