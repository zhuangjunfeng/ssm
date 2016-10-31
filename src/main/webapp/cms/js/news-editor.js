var userName;
$(function () {
    //查询登录用户信息
    findLoginUser();
    findDictType(GetRequest().newProgram);
    //查询新闻栏目和新闻类型
    $.ajax({
        url: "/rest/dict/findDictProgram",
        type: "GET",
        dataType: "json",
        data: {type: "newsType"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var programList = data.data;
            var programHtml = "";
            $.each(programList, function (i, n) {
                programHtml += "<option>" + n.dictName + "</option>";
            });
            $("#e_newsProgram").html(programHtml);

        }
    });
    //更新新闻方法
    $("#editor-news").click(function () {
        updateNews(userName);
    });
    //根据ID单条查询新闻
    findNewsById();
});
//-----------------------独立方法-----------------------------
/**
 * 根据新闻ID查询新闻
 */
function findNewsById() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/rest/news/findNewsById",
        data: {NewsId: GetRequest().newsId, _method: "GET"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var news = data.data;
            $("#e_newsTitle").val(news.newsTitle);
            $("#e_newsProgram").val(news.newsProgram);
            $("#e_newsAuthor").val(news.newsAuthor);
            $("#newsContent").val(news.newsContent);
            $("#e_newsType").val(news.newsType);
            $("#e_newsProgram").change(function () {
                findDictType($("#e_newsProgram").val());
            });
        }
    });
}
/**
 * 更新新闻
 */
function updateNews(newsAuthor) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/rest/news",
        data: {
            NewsId: GetRequest().newsId,
            NewsTitle: $("#e_newsTitle").val(),
            NewsAuthor: newsAuthor,
            NewsType: $("#e_newsType").val(),
            NewsProgram: $("#e_newsProgram").val(),
            NewsContent: editor.getContent(),
            _method: "PUT"
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            window.location.href = "news.html";
        }
    });
}
/**
 * 获取URL?后数据
 * @returns {Object}
 */
function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
/**
 * 查询新闻类型
 * @param newsProgram 新闻栏目
 */
function findDictType(newsProgram) {
    $.ajax({
        url: "/rest/dict/findDictType",
        type: "GET",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        data: {type: encodeURI(newsProgram), _method: "GET"},
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
            $("#e_newsType").html(typeHtml);
        }
    });
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
            userName=userMessage.yhxm;
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
