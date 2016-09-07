$(function(){
    $.ajax({
        url:"/rest/dict",
        type:"GET",
        dataType:"json",
        data:{type:"newsType"},
        success:function(data){
            var typeList=data.data;
            var typeHtml="";
            $.each(typeList,function(i,n){
                typeHtml+="<option>"+n.dictName+"</option>";
            });
            $("#newsType").html(typeHtml);
        }
    });

    $("#add-news").click(function(){
        $.ajax({
            url:"/rest/news",
            type:"POST",
            data:{
                newsTitle:$("#newsTitle").val(),
                newsAuthor:$("#newsAuthor").val(),
                newsType:$("#newsType").val(),
                newsContent:editor.getContent()},
            success:function(){
                window.location.href="news.html";
            }
        });
    });
});