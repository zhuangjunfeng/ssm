$(function(){
        findTypeList();
});
function findTypeList(){
    var programs=decodeURI(window.location.href);
    $.ajax({
        type:"GET",
        dataType:"json",
        url:"/rest/comweb/dict/findDict",
        data:{type:programs},
        success: function (data) {
            var typeList=data.data;
            var typeHtml="";
            $.each(typeList,function(i,n){
                if(i==0)
                {
                findNewsByType(n.dictName);
                }
                typeHtml+="<li><a class='news-type' data-type='"+ n.dictName+"'>"+n.dictName+"</a></li>";
            });
            $("#show-program-list").html(typeHtml);
            $(".news-type").click(function(){
                findNewsByType($(this).attr("data-type"))
            })
        }
    });
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
           var newsListHtml="<span> <b>"
                +news.newsTitle+"</b></span><span>作者："
                +news.newsAuthor+"</span><span>发布时间："
                +news.editorTime+"</span><div>"
                +news.newsContent+"</div>";
            $("#show-news").html(newsListHtml);
        }
    });
}
function findNewsByType(newsType){
    $.ajax({
        type:"POST",
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url:"/rest/comweb/news/findNewsByType",
        data:{NewsType:newsType,_method:"GET"},
        success: function (data) {
            var newsList=data.data;
            var newsListHtml="";
            $.each(newsList,function(i,n){
                newsListHtml=newsListHtml+"<li><p><a style='cursor:pointer' class='news-detail' data-id='"
                               + n.newsId+"'>"
                               + n.newsTitle+"</a></p><span>"
                               + n.editorTime+"</span></li>";
            });
            $("#show-news").html(newsListHtml);
            $(".news-detail").click(function(){
                findNewsById($(this).attr("data-id"));
            })
        }
    });
}


