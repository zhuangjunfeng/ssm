$(function () {
    //查找所有用户
    findAllUser(0);
    //查询登录用户信息
    findLoginUser();
});
// ----------------------独立方法------------------------------
/**
 * 查询所有用户
 */
function findAllUser(page_id) {
    $.ajax({
        type: "GET",
        url: "/rest/user/findUserByPageNo",
        dataType: "json",
        data:{PageNo:page_id+1,PageSize:10,_method:"GET"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var userList = data.data.List;
            var totalcount = data.data.count;
            var userListHtml = "<thead><tr><th>用户编号</th><th>用户账户</th><th>真实姓名</th><th>性别</th><th>用户角色</th><th>创建时间</th><th>操作</th></tr></thead><tbody>";
            $.each(userList, function (i, n) {
                userListHtml = userListHtml + "<tr><td>"
                    + n.yhId + "</td><td>"
                    + n.yhzh + "</td><td>"
                    + n.yhxm + "</td><td>"
                    + n.yhxb + "</td><td>"
                    + n.yhjs + "</td><td>"
                    + " " + "</td><td><a class='btn' href='user-editor.html?yhId="
                    + n.yhId + "'><i class='fa fa-edit'></i> 编辑</a><a class='btn del-user' data-id='"
                    + n.yhId + "'><i class='fa fa-trash-o'></i> 删除</a></td></tr>"
            })
            userListHtml = userListHtml + "</tbody>"
            $(".table").html(userListHtml);
            $("#Pagination").pagination(totalcount, {
                callback : pageselectCallback,
                prev_text : "上一页",
                next_text : "下一页 ",
                items_per_page : 10,
                prev_show_always : true,
                next_show_always : true,
                current_page : page_id,
                link_to : "javascript:void(0)"
            });
            $(".del-user").click(function () {
                delUser($(this).attr("data-id"));
            });
        }
    });
    /**
     * 根据用户ID删除用户
     * @param yhId 用户ID
     */
    function delUser(yhId) {
        $.ajax({
            type: "POST",
            url: "/rest/user",
            data: {yhId: yhId, _method: 'DELETE'},
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.responseText == "loginError") {
                    window.location.href = "/cms/login.html";
                }
            },
            success: function () {
                findAllUser(0);
            }
        });
    }
}
/**
 * 查询登录用户信息
 */
function findLoginUser() {
    $.ajax({
        type: "POST",
        url: "/rest/user/findLoginUser",
        dataType: "json",
        data: {_method: "GET"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var userMessage = data.data;
            var userHtml = "";
            userHtml += "<li class='dropdown user user-menu'>"
                + "<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"
                + "<img src='dist/img/user2-160x160.jpg' class='user-image' alt='User Image'>"
                + "<span class='hidden-xs'>" + userMessage.yhxm
                + "</span> <i class='fa fa-gears'></i></a>"
                + "<ul class='dropdown-menu'> <li class='user-header'>"
                + "<img src='dist/img/user2-160x160.jpg' class='img-circle' alt='User Image'> <p>"
                + userMessage.yhzh + "<small>" + userMessage.yhId
                + "</small></p></li><li class='user-body'><div class='row'><div class='col-xs-6 text-center'><span>"
                + userMessage.yhxb + "</span></div><div class='col-xs-6 text-center'><span>"
                + userMessage.yhjs + "</span> </div></div></li>"
                + "<li class='user-footer'><div class='pull-left'>"
                + "<a href='#' class='btn btn-default btn-flat'>修改密码</a> </div>"
                + "<div class='pull-right'><a id='logout' class='btn btn-default btn-flat'>退出系统</a></div></li>"
            $(".navbar-nav").html(userHtml);
            $("#logout").click(function () {
                logout()
            });
        }
    });
}
/**
 * 登出方法
 */
function logout() {
    $.ajax({
        url: "/rest/user/logout",
        type: "POST",
        success: function () {
            window.location.href = "/cms/login.html";
        }
    });
}

//翻页调用
function pageselectCallback(page_id,jq){
    scroll(0,0);
    findAllUser(page_id);
}
