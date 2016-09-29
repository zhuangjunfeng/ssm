$(function(){
    findNews();
});
function findNews(){
    $.ajax({
        type:"GET",
        url:"/rest/comweb/news",
        dataType:"json",
        success:function(data){
            var listSize=5;
            var newsList=data.data;
            var newsListHtml="";
            $.each(newsList,function(i,n){
                if(i<=listSize-1){
                newsListHtml=newsListHtml+"<li><i></i><span class='news-t'>"
                    + n.newsTitle+"</span><span class='news-d'>"
                    + n.editorTime+"</li>";
                }
                $("#show-news").html(newsListHtml);
            });
        }
    });
}

