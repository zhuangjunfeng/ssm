$(function(){
    $.ajax({
        url:"/rest/dict",
        type:"GET",
        dataType:"json",
        data:{type:"newsType"},
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
            $("#e_newsType").html(typeHtml);
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

    $("#editor-news").click(function(){
        updateNews();
    });
    findNewsById();
});

function findNewsById(){
    $.ajax({
        type:"GET",
        dataType:"json",
        url:"/rest/news/findNewsById",
        data:{NewsId:GetRequest().newsId,_method:"GET"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/cms/login.html";
            }
        },
        success:function(data){
            var news=data.data;
            $("#e_newsTitle").val(news.newsTitle);
            $("#e_newsType").val(news.newsType);
            $("#e_newsAuthor").val(news.newsAuthor);
            var newsContent=news.newsContent;
            editor.addListener("ready", function () {
                editor.setContent(newsContent,true);
            });
        }
    });
}

function updateNews(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/rest/news",
        data:{
            NewsId:GetRequest().newsId,
            NewsTitle:$("#e_newsTitle").val(),
            NewsAuthor:$("#e_newsAuthor").val(),
            NewsType:$("#e_newsType").val(),
            NewsContent:editor.getContent(),
            _method:"PUT"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/cms/login.html";
            }
        },
        success:function(data){
            window.location.href="news.html";
        }
    });
}

function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

