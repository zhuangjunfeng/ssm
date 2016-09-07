$(function(){
    $.ajax({
        url:"/rest/dict",
        type:"GET",
        dataType:"json",
        data:{type:"sexType"},
        success:function(data){
            var typeList=data.data;
            var typeHtml="";
            $.each(typeList,function(i,n){
                typeHtml+="<option>"+n.dictName+"</option>";
            });
            $("#yhxb").html(typeHtml);
        }
    });

    $("#add-user").click(function(){
        var params = "yhxb="+ $("#yhxb").val()
            + "&password=" + $("#password").val()
            +"&yhzh="+$("#yhzh").val();
        $.ajax({
            url:"/rest/user",
            type:"POST",
            data:params,
            success:function(){
                window.location.href="user.html";
            }
        });
    });
});