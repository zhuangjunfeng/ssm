$(function(){
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