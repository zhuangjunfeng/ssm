$(function () {
    //查询新闻栏目以及对应新闻类型
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
            var typeList = data.data;
            var typeHtml = "";
            $.each(typeList, function (i, n) {
                typeHtml += "<option>" + n.dictName + "</option>";
            });
            $("#newsPrograms").html(typeHtml);
            var NewsProgram = $("#newsPrograms").val();
            findDictType(NewsProgram);
            $("#newsPrograms").change(function () {
                findDictType($("#newsPrograms").val());
            });
        }
    });
    findAllNews();
    findLoginUser();
});
// ----------------------独立方法------------------------------
/**
 * 查询所有新闻
 */
function findAllNews() {
    $.ajax({
        type: "GET",
        url: "/rest/news",
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function (data) {
            var newsList = data.data;
            var newsListHtml = "<thead><tr><th>内容编号</th><th>内容标题</th><th>内容栏目</th><th>内容类型</th><th>操作</th></tr></thead><tbody>";
            $.each(newsList, function (i, n) {
                newsListHtml = newsListHtml + "<tr><td>"
                    + n.newsId + "</td><td>"
                    + n.newsTitle + "</td><td>"
                    + n.newsProgram + "</td><td>"
                    + n.newsType + "</td><td><a class='btn' href='news-editor.html?newsId="
                    + n.newsId + "'><i class='fa fa-edit'></i> 编辑</a><a class='btn del-news'  data-id='"
                    + n.newsId + "'><i class='fa fa-trash-o'></i> 删除</a></td></tr>"
            })
            newsListHtml = newsListHtml + "</tbody>"
            $(".table").html(newsListHtml);
            $(".del-news").click(function () {
                delNews($(this).attr("data-id"));
            });
            $("#news-publish").click(function () {
                publishNews();
            })
        }
    });
}
/**
 * 删除新闻
 * @param newsId 新闻ID
 */
function delNews(newsId) {
    $.ajax({
        type: "POST",
        url: "/rest/news",
        data: {NewsId: newsId, _method: 'DELETE'},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "/cms/login.html";
            }
        },
        success: function () {
            findAllNews();
        }
    });
}
/**
 * 发布所有未发布新闻
 */
function publishNews() {
    $.ajax({
        type: "POST",
        url: "/rest/news/publishNews",
        dataType: "json",
        data: {_method: "PUT"},
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.responseText == "loginError") {
                window.location.href = "cms/login.html";
            }
        },
        success: function (data) {
            if (data.message == "success") {
                alert("发布成功");
            }
            else {
                alert("发布失败");
            }
        }
    });
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
            $("#newsType").html(typeHtml);
        }
    });
}
/**
 * 获取登录用户信息
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
