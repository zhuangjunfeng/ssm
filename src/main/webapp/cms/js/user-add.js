$(function () {
    //查找性别类型填入复选框
    $.ajax({
        url: "/rest/dict",
        type: "GET",
        dataType: "json",
        data: {type: "sexType"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var typeList = data.data;
            var typeHtml = "";
            $.each(typeList, function (i, n) {
                typeHtml += "<option>" + n.dictName + "</option>";
            });
            $("#yhxb").html(typeHtml);
        }
    });
    //查询登录用户信息
    findLoginUser();
    //添加用户
    $("#add-user").click(function () {
        var params = "yhxb=" + $("#yhxb").val()
            + "&password=" + $("#password").val()
            + "&yhzh=" + $("#yhzh").val()
            + "&yhxm=" + $("#yhxm").val()
            +"&yhjs="+$("#yhjs").val();
        $.ajax({
            url: "/rest/user",
            type: "POST",
            data: params,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.responseText == "loginError") {
                    window.location.href = "/cms/login.html";
                }
            },
            success: function () {
                window.location.href = "user.html";
            }
        });
    });
});
//----------------------------独立方法---------------------------
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