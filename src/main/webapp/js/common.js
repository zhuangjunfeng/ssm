$(function(){
        findTypeList();
});
function findTypeList(){
    var programs=decodeURI(window.location.href);
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/rest/comweb/dict",
        data:{type:programs,_method:"GET"},
        success:function(data){
            var typeList=data.data;
            var typeHtml="";
            var location="";
            $.each(typeList,function(i,n){
                typeHtml= "<li class='current'><a href='javascript:;'>"+ n.dictName+"</a></li>";
                location+="<li class='bc_icon'>当前位置</li><li><a href='javacript:;'>"
                    + n.dictName+"</a></li>"
            });
            $.ajax({
                type:"GET",
                dataType:"json",
                url:"/rest/comweb/dict/findDict",
                data:{type:programs},
                success: function (data) {
                    var typeList=data.data;
                    $.each(typeList,function(i,n) {
                        if (i == 0) {
                            findNewsByType(n.dictName, location);
                            typeHtml += "<li class='current_detail'><a  class='news-type' data-type='"
                                + n.dictName + "'>"
                                + n.dictName + "</a></li>";
                        }
                        else {
                        typeHtml += "<li><a  class='news-type' data-type='"
                            + n.dictName + "'>"
                            + n.dictName + "</a></li>";
                    }
                    });
                    $("#show-program-list").html(typeHtml);
                    $(".news-type").click(function(){
                        $(this).parent().siblings().removeClass("current_detail");
                        $(this).parent().addClass("current_detail");
                        findNewsByType($(this).attr("data-type"), location);
                    })
                }
            });
        }
    })
}

function findNewsById(newsId){
    var NewsId=newsId
    $.ajax({
        type:"GET",
        dataType:"json",
        url:"/rest/comweb/news/findNewsById",
        data:{NewsId:NewsId,_method:"GET"},
        success:function(data){
            var news=data.data;
           var newsListHtml=" <article><h1>"
                +news.newsTitle+"</h1><span>作者："
                +news.newsAuthor+"</span><span>发布时间："
                +news.editorTime+"</span><div class='art_txt'>"
                +news.newsContent+"</div></article>";
           var location="<li class='bc_icon'>当前位置</li><li><a href='javacript:;'>"
                +news.newsProgram+"</a></li><li><a href='javascript:;'>"
                +news.newsType+"</a></li><li>"
                +news.newsTitle+ "</li>"
            $("#show-news").html(newsListHtml);
            $("#show-location").html(location);
        }
    });
}
function findNewsByType(newsType, Location){
    $.ajax({
        type:"POST",
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url:"/rest/comweb/news/findNewsByType",
        data:{NewsType:newsType,_method:"GET"},
        success: function (data) {
            var newsList=data.data;
            var newsListHtml="";
            var location="";

            if(newsList!=""){
                if(newsList.length!=1){
            $.each(newsList,function(i,n){
                newsListHtml=newsListHtml+"<li><a style='cursor:pointer' class='news-detail' data-id='"
                               + n.newsId+"'>"
                               + n.newsTitle+"</a><span>"
                               + n.editorTime+"</span></li>";
                 location="<li class='bc_icon'>当前位置</li><li><a href='javascript:;'>"
                    + n.newsProgram+"</a> </li><li> <a href='javascript:;'>"
                    +newsType+"</a> </li>";
            });
                }
                else{
                    $.each(newsList,function(i,n){
                        newsListHtml+= n.newsContent;
                        location="<li class='bc_icon'>当前位置</li><li><a href='javascript:;'>"
                            + n.newsProgram+"</a> </li><li> <a href='javascript:;'>"
                            +newsType+"</a> </li>";
                    });
                }
            }
            else{
                location+=Location+"<li> <a href='javascript:;'>"
                    +newsType+"</a> </li>";
            }
            $("#show-news").html(newsListHtml);
            $("#show-location").html(location);
            $(".news-detail").click(function(){
                findNewsById($(this).attr("data-id"));
            })
        }
    });
}


