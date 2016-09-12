$(function(){
    $(function(){
        $("#login").click(function(){
            var params ="yhzh="+$("#yhzh").val()
                +"&password="+$("#password").val();
            $.ajax({
                url:"/rest/user/login",
                type:"GET",
                data:params,
                dataType:"json",
                success:function(data){
                    var types = data.message;
                    if (types!= "success") {
                        alert(types);
                    }
                    else{
                        window.location.href="index.html";
                    }
                },
                error: function () {alert("用户名密码验证失败");
                }
            });
        });
    });
});