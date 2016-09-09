$(function(){
    findAllUser();
    $("#logout").click(function(){
        $.ajax({
            url:"/rest/user/logout",
            type:"POST",
            success:function(){
                window.location.href="/login.html";
            }
        });
    });
});
// ----------------------独立方法------------------------------
function findAllUser(){
    $.ajax({
        type:"GET",
        url:"/rest/user",
        dataType:"json",
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/login.html";
            }
        },
        success:function(data){
            var userList=data.data;
            var userListHtml="<thead><tr><th>用户编号</th><th>用户账户</th><th>真实姓名</th><th>性别</th><th>用户角色</th><th>创建时间</th>操作</tr></thead>";
            $.each(userList,function(i,n){
                userListHtml=userListHtml+"<tbody><tr><td>"
                    +n.yhId+"</td><td>"
                    +n.yhzh+"</td><td>"
                    +n.yhxm+"</td><td>"
                    +n.yhxb+"</td><td>"
                    +" " +"</td><td>"
                    +" "+"</td><td><a class='btn' href='user-editor.html?yhId="
                    +n.yhId+"'><i class='fa fa-edit'></i> 编辑</a><a class='btn del-user' data-id='"
                    +n.yhId+"'><i class='fa fa-trash-o'></i> 删除</a></td></tr>"
            })
            userListHtml=userListHtml+"</tbody>"
            $(".table").html(userListHtml);
            $(".del-user").click(function(){
                delUser($(this).attr("data-id"));
            });
        }
    });

    function delUser(yhId){
        $.ajax({
            type:"POST",
            url:"/rest/user",
            data:{yhId:yhId ,_method: 'DELETE'},
            error:function(XMLHttpRequest, textStatus, errorThrown){
                if(XMLHttpRequest.responseText=="loginError"){
                    window.location.href="/login.html";
                }
            },
            success:function(){
                findAllUser();
            }
        });
    }
}