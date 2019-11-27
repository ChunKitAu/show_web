function login(){
    //表单的数据用ajax发送 避免页面跳转
    $.ajax({
        url:"/login",
        dataType:"json",
        type:"POST",
        data:$("#loginForm").serialize(),
        success:function (result) {
            if(result.code == 200){
                window.location.href="/article_list";
            }
            if(result.code == 1000){
                alert(result.data);
            }
        },
        error:function () {
            alert("异常！");
        },
    })
};