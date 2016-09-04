$(function(){
	findAllNews();
});
// ----------------------独立方法------------------------------
function findAllNews(){
	$.ajax({
		type:"GET",
		url:"/rest/news/findAllNews",
		dataType:"json",
		success:function(data){
			var newsList=data.data;
			var newsListHtml="<thead><tr><th>新闻编号</th><th>新闻标题</th><th>新闻栏目</th><th>新闻类型</th><th>操作</th></tr></thead>";
			$.each(newsList,function(i,n){
				newsListHtml=newsListHtml+"<tbody><tr><td>"
				+n.newsId+"</td><td>"
				+n.newsTitle+"</td><td>"
				+" "+"</td><td>"
				+n.newsType+"</td><td><a class='btn'><i class='fa fa-edit'></i> 编辑</a><a class='btn';><i class='fa fa-trash-o'></i> 删除</a></td></tr>"
			})
			newsListHtml=newsListHtml+"</tbody>"
			$(".table").html(newsListHtml);
		}
	});
}