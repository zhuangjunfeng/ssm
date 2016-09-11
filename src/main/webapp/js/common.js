$(function(){
        findAllNews();
});

function findAllNews(){
    var name=GetRequest().program;
    $.ajax({
        type:"GET",
        url:"/rest/comweb/news",
        dataType:"json",
        success:function(data){
            var news=[];
            var newsList=data.data;
            $.each(newsList,function(i,n){
                if(n.newsProgram==name){
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
                    newsListHtml=newsListHtml+"<li><a>"+ n.newsTitle+"</a></li>";
                });
                newsListHtml=newsListHtml+"</ul>";
                $("#news-show").html(newsListHtml);
                $("#news-content").html("");
            }
        }
    });
}

function GetRequest() {
    var url = decodeURI(location.search);
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
