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
            var programList=data.data;
            var programHtml="";
            $.each(programList,function(i,n){
                programHtml+="<option>"+n.dictName+"</option>";
            });
            $("#newsProgram").html(programHtml);
            var NewsProgram=$("#newsProgram").val();
            findDictType(NewsProgram);
            $("#newsProgram").change(function(){
                findDictType($("#newsProgram").val());
            });
        }
    });
    $("#add-news").click(function(){
        $.ajax({
            url:"/rest/news",
            type:"POST",
            data:{
                NewsTitle:$("#newsTitle").val(),
                NewsAuthor:$("#newsAuthor").val(),
                NewsType:$("#newsType").val(),
                NewsProgram:$("#newsProgram").val(),
                NewsContent:editor.getContent()
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(XMLHttpRequest.responseText=="loginError"){
                    window.location.href="/cms/login.html";
                }
            },
            success:function(){
                window.location.href="news.html";
            }
        });
    });
    findLoginUser();
});

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

function findLoginUser(){
    $.ajax({
        type:"POST",
        url:"/rest/user/findLoginUser",
        dataType:"json",
        data:{_method:"GET"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/cms/login.html";
            }
        },
        success:function(data){
            var userMessage=data.data;
            var userHtml="";
            userHtml+= "<li class='dropdown user user-menu'>"
                +"<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"
                +"<img src='dist/img/user2-160x160.jpg' class='user-image' alt='User Image'>"
                +"<span class='hidden-xs'>"+userMessage.yhxm
                +"</span> <i class='fa fa-gears'></i></a>"
                +"<ul class='dropdown-menu'> <li class='user-header'>"
                +"<img src='dist/img/user2-160x160.jpg' class='img-circle' alt='User Image'> <p>"
                +userMessage.yhzh +"<small>"+userMessage.yhId
                +"</small></p></li><li class='user-body'><div class='row'><div class='col-xs-6 text-center'><span>"
                +userMessage.yhxb+"</span></div><div class='col-xs-6 text-center'><span>"
                +userMessage.yhjs+"</span> </div></div></li>"
                +"<li class='user-footer'><div class='pull-left'>"
                +"<a href='#' class='btn btn-default btn-flat'>修改密码</a> </div>"
                +"<div class='pull-right'><a id='logout' class='btn btn-default btn-flat'>退出系统</a></div></li>"
            $(".navbar-nav").html(userHtml);
            $("#logout").click(function(){
                logout()
            });
        }
    });
}

function logout(){
    $.ajax({
        url:"/rest/user/logout",
        type:"POST",
        success:function(){
            window.location.href="/cms/login.html";
        }
    });
}
