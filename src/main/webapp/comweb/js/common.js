$(function(){
    findAllNews();
});

function findAllNews(){
    $.ajax({
        type:"GET",
        url:"/rest/comweb/news",
        dataType:"json",
        success:function(data){
            var news=new Array();
            var newsList=data.data;
            $.each(newsList,function(i,n){
                if(n.newsProgram=="产品中心"){
                     news.unshift(n);
                }
            });
            var newsListHtml="";
            if(news.length==1){
                $.each(news,function(i,n){
                var news_content=n.newsContent;
                newsListHtml=newsListHtml+"<h3> <b>"
                    +n.newsTitle+"</b></h3><span>作者："
                    +n.newsAuthor+"</span><span>发布时间："
                    +n.editorTime+"</span>";
                $("#news-show").html(newsListHtml);
                $("#news-content").html(news_content);
                });
            }
            else{
                newsListHtml="<ul>";
                $.each(news,function(i,n){
                    newsListHtml=newsListHtml+"<li><a>"+ n.newsTitle+"</a></li>";
                });
                newsListHtml=newsListHtml+"</ul>";
                $("#news-show").html(newsListHtml);
            }
        }
    });
}