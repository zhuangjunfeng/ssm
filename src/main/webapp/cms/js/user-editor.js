$(function () {
    /**
     * @decription：页面加载时查询字典数据，成功后执行单条查询方法
     * @author：zhuangjf
     */
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
            $("#e_yhxb").html(typeHtml);
        }
    });
    /**
     * @decription：调用更新新闻方法
     */
    $("#editor-user").click(function () {
        updateUser();
    });
    findUserById();
    findLoginUser();
});
/*****************独立方法*********************/
/**
 * @decription：新闻单条查询
 * @author：zhuangjf
 */
function findUserById() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/rest/user/findUser",
        data: {yhId: GetRequest().yhId, _method: "GET"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var user = data.data;
            $("#e_yhzh").val(user.yhzh);
            $("#e_password").val(user.password);
            $("#e_yhxb").val(user.yhxb);
        }
    });
}
/**
 * @decription：更新新闻方法
 * @author：zhuangjf
 */
function updateUser() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/rest/user",
        data: {
            yhId: GetRequest().yhId,
            yhzh: $("#e_yhzh").val(),
            yhxb: $("#e_yhxb").val(),
            password: $("#e_password").val(),
            _method: "PUT"
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            window.location.href = "user.html";
        }
    });
}
/**
 * @theRequest：获取URL中？后字符串方法
 * @author zhuangjf
 * @returns {theRequest}
 */
function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
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

