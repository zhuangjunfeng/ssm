$(function(){
    check();
    $("#logout").click(function(){
        $.ajax({
            url:"/rest/user/logout",
            type:"POST",
            success:function(){
                window.location.href="/cms/login.html";
            }
        });
    });
});
function check(){
    $.ajax({
        url:"/rest/news",
        type:"GET",
        dataType:"json",
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/cms/login.html";
            }
        },success:function(data){

        }
    })
}