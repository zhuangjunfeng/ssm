$(function(){
    $.ajax({
        url:"/rest/dict",
        type:"GET",
        dataType:"json",
        data:{type:"newsType"},
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
            $("#newsType").html(typeHtml);
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

    $("#add-news").click(function(){
        $.ajax({
            url:"/rest/news",
            type:"POST",
            data:{
                NewsTitle:$("#newsTitle").val(),
                NewsAuthor:$("#newsAuthor").val(),
                NewsType:$("#newsType").val(),
                NewsContent:editor.getContent()
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(XMLHttpRequest.responseText=="loginError"){
                    window.location.href="/login.html";
                }
            },
            success:function(){
                window.location.href="news.html";
            }
        });
    });
});