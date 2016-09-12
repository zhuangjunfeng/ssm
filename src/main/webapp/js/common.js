$(function(){
        findAllNews();
});

function findAllNews(){
    var name=decodeURI(window.location.href);;
    $.ajax({
        type:"GET",
        url:"/rest/comweb/news",
        dataType:"json",
        success:function(data){
            var news=[];
            var newsList=data.data;
            $.each(newsList,function(i,n){
                if(name.indexOf(n.newsProgram)!=-1){
                     news.unshift(n);
                }
            });
            var newsListHtml="";
            if(news.length==1){
                $.each(news,function(i,n){
                var news_content=n.newsContent;
                newsListHtml=newsListHtml+"<span> <b>"
                    +n.newsTitle+"</b></span><span>作者："
                    +n.newsAuthor+"</span><span>发布时间："
                    +n.editorTime+"</span>";
                $("#news-show").html(newsListHtml);
                $("#news-content").html(news_content);
                });
            }
            else{
                newsListHtml="<ul>";
                $.each(news,function(i,n){
                    newsListHtml=newsListHtml+"<li><a class='news-detail' data-id='"+ n.newsId+"'>"+ n.newsTitle+"</a></li>";
                });
                newsListHtml=newsListHtml+"</ul>";
                $("#news-show").html(newsListHtml);
                $("#news-content").html("");
                $(".news-detail").click(function(){
                    findNewsById($(this).attr("data-id"));
                })
            }
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
            var news_content=news.newsContent;
           var newsListHtml="<span> <b>"
                +news.newsTitle+"</b></span><span>作者："
                +news.newsAuthor+"</span><span>发布时间："
                +news.editorTime+"</span>";
            $("#news-show").html(newsListHtml);
            $("#news-content").html(news_content);
        }
    });
}


