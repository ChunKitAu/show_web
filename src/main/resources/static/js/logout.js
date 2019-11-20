$("#logout").click(function () {

    if(confirm("确定要退出吗?")){
        $.ajax({
            url:"/logout",
            type:"GET",
            success:function (result) {
                if(result.code == 200){
                    alert("退出成功！");
                    window.location.href=("/login");
                }
                if(result.code == 1000){
                    alert(result.data);
                }
            },
            error:function () {
                alert("异常!");
            }
        })
    }
})