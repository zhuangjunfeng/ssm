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
			var typeList=data.data;
			var typeHtml="";
			$.each(typeList,function(i,n){
				typeHtml+="<option>"+n.dictName+"</option>";
			});
			$("#newsPrograms").html(typeHtml);
			var NewsProgram=$("#newsPrograms").val();
			findDictType(NewsProgram);
			$("#newsPrograms").change(function(){
				findDictType($("#newsPrograms").val());
			});
		}
	});
	findAllNews();
	$("#logout").click(function(){
		$.ajax({
			url:"/rest/user/logout",
			type:"POST",
			success:function(){
				window.location.href="/cms/login.html";
			}
		});
	});
});
// ----------------------独立方法------------------------------
function findAllNews(){
	$.ajax({
		type:"GET",
		url:"/rest/news",
		dataType:"json",
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.responseText=="loginError"){
				window.location.href="/cms/login.html";
			}
		},
		success:function(data){
			var newsList=data.data;
			var newsListHtml="<thead><tr><th>新闻编号</th><th>新闻标题</th><th>新闻栏目</th><th>新闻类型</th><th>操作</th></tr></thead><tbody>";
			$.each(newsList,function(i,n){
				newsListHtml=newsListHtml+"<tr><td>"
				+n.newsId+"</td><td>"
				+n.newsTitle+"</td><td>"
				+n.newsProgram+"</td><td>"
				+n.newsType+"</td><td><a class='btn' href='news-editor.html?newsId="
				+n.newsId+"'><i class='fa fa-edit'></i> 编辑</a><a class='btn del-news'  data-id='"
				+n.newsId+"'><i class='fa fa-trash-o'></i> 删除</a></td></tr>"
			})
			newsListHtml=newsListHtml+"</tbody>"
			$(".table").html(newsListHtml);
			$(".del-news").click(function(){
				delNews($(this).attr("data-id"));
			});
			$("#news-publish").click(function(){
				publishNews();
			})
		}
	});
}

function delNews(newsId){
	$.ajax({
		type:"POST",
		url:"/rest/news",
		data:{NewsId:newsId ,_method: 'DELETE'},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.responseText=="loginError"){
				window.location.href="/cms/login.html";
			}
		},
		success:function(){
			findAllNews();
		}
	});
}
function publishNews(){
	$.ajax({
		type:"POST",
		url:"/rest/news/publishNews",
		dataType:"json",
		data:{_method:"PUT"},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.responseText=="loginError"){
				window.location.href="cms/login.html";
			}
		},
		success:function(data){
			if(data.message=="success"){
				alert("发布成功");
			}
			else{
				alert("发布失败");
			}
		}
	});
}
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