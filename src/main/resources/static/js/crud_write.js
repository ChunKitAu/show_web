CKEDITOR.replace( 'editor1');
var id = null;

$(function () {
    //获取url参数
    var parameterURL =location.search.substring(1, location.search.length);
    // alert(parameterURL);

    //存在id则为修改文章
    if(parameterURL != null && parameterURL.length > 0){
        //获取id
        id = parameterURL.substring(3,parameterURL.length);
        $("#type").attr("disabled","disabled");
        $('#btn').append('修改');
        $('#_method').attr('value','put');
        $('#_id').attr('value',id);
        getValue(id);
    }

});

function getValue(id){
    $.ajax({
        url: "/article/" + id,
        type: "GET",
        success: function (result) {
            console.log(result);
            if (result.code == 200) {
                //数据回显
                $('#title').val(result.data.title);
                $('#type').val(result.data.type);
                $('#reblog').val(result.data.reblog);
                $('#editor1').val(result.data.content);
            }
        }
    })
};
//提交
function Submit(){
    //发布文章
    if(id == null){
        $.ajax({
            url:"/article",
            dataType:"json",
            type:"POST",
            data:{
                id:id,
                title:$("#title").val(),
                type:$("#type").val(),
                reblog:$("#reblog").val(),
                content:CKEDITOR.instances.editor1.getData(),
            },
            success:function (result) {
                if(result.code == 200){
                    alert("发布成功！");
                }
                if(result.code == 1000){
                    alert(result.data);
                }
            },
            error:function () {
                alert("异常！");
            },
        })
    }else{
        //修改文章
        $.ajax({
            url:"/article",
            dataType:"json",
            type:"PUT",
            data:{
                id:id,
                title:$("#title").val(),
                type:$("#type").val(),
                reblog:$("#reblog").val(),
                content:CKEDITOR.instances.editor1.getData(),
            },
            success:function (result) {
                if(result.code == 200){
                    alert("修改成功！");
                }
                if(result.code == 1000){
                    alert(result.data);
                }
            },
            error:function () {
                alert("异常！");
            },
        })
    }


};


//若下拉选择项目组介绍/实验室介绍/联系我们   只能存在一篇文章
$("#type").change(function () {
    var val = $('#type option:selected').val();//选中的值
    if(val == "项目组介绍" || val == "实验室介绍" || val == "联系我们"){
        $.ajax({
            url: "/article/getIdByType?type=" + val,
            type: "GET",
            success: function (result) {
                console.log(result);
                if(result.data != null){
                    if(confirm(val+" 已经存在是否修改")){
                        window.location.href = "/write?id=" + result.data;
                    }
                }
            },
            error:function () {
                alert("error");
            }
        })
    }
});
