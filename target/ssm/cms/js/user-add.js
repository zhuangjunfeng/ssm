$(function(){
    $.ajax({
        url:"/rest/dict",
        type:"GET",
        dataType:"json",
        data:{type:"sexType"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/login.html";
            }
        },
        success:function(data){
            var typeList=data.data;
            var typeHtml="";
            $.each(typeList,function(i,n){
                typeHtml+="<option>"+n.dictName+"</option>";
            });
            $("#yhxb").html(typeHtml);
        }
    });

    $("#logout").click(function(){
        $.ajax({
            url:"/rest/user/logout",
            type:"POST",
            success:function(){
                window.location.href="/login.html";
            }
        });
    });
    $("#add-user").click(function(){
        var params = "yhxb="+ $("#yhxb").val()
            + "&password=" + $("#password").val()
            +"&yhzh="+$("#yhzh").val();
        $.ajax({
            url:"/rest/user",
            type:"POST",
            data:params,
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(XMLHttpRequest.responseText=="loginError"){
                    window.location.href="/login.html";
                }
            },
            success:function(){
                window.location.href="user.html";
            }
        });
    });
});