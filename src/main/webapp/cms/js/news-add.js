$(function(){
    $.ajax({
        url:"/rest/dict/findDictProgram",
        type:"GET",
        dataType:"json",
        data:{type:"newsType"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/cms/login.html";
            }
        },
        success:function(data){
            var programList=data.data;
            var programHtml="";
            $.each(programList,function(i,n){
                programHtml+="<option>"+n.dictName+"</option>";
            });
            $("#newsProgram").html(programHtml);
            var NewsProgram=$("#newsProgram").val();
            findDictType(NewsProgram);
            $("#newsProgram").change(function(){
                findDictType($("#newsProgram").val());
            });
        }
    });

    $("#logout").click(function(){
        $.ajax({
            url:"/rest/user/logout",
            type:"POST",
            success:function(){
                window.location.href="/cms/login.html";
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
                NewsProgram:$("#newsProgram").val(),
                NewsContent:editor.getContent()
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(XMLHttpRequest.responseText=="loginError"){
                    window.location.href="/cms/login.html";
                }
            },
            success:function(){
                window.location.href="news.html";
            }
        });
    });
});

function findDictType(newsProgram){
    $.ajax({
        url:"/rest/dict/findDictType",
        type:"GET",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType:"json",
        data:{type:encodeURI(newsProgram),_method:"GET"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/cms/login.html";
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
}