$(function(){
    /**
     * @decription：页面加载时查询字典数据，成功后执行单条查询方法
     * @author：zhuangjf
     */
    $.ajax({
        url:"/rest/dict",
        type:"GET",
        dataType:"json",
        data:{type:"sexType"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/login.html";
            }
        },
        success:function(data){
            var typeList=data.data;
            var typeHtml="";
            $.each(typeList,function(i,n){
                typeHtml+="<option>"+n.dictName+"</option>";
            });
            $("#e_yhxb").html(typeHtml);
        }
    });
    /**
     * @decription：调用更新新闻方法
     */
    $("#editor-user").click(function(){
        updateUser();
    });
    findUserById();
});
/*****************独立方法*********************/
/**
 * @decription：新闻单条查询
 * @author：zhuangjf
 */
function findUserById(){
    $.ajax({
        type:"GET",
        dataType:"json",
        url:"/rest/user/findUser",
        data:{yhId:GetRequest().yhId,_method:"GET"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/login.html";
            }
        },
        success:function(data){
            var user=data.data;
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
function updateUser(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/rest/user",
        data:{
            yhId:GetRequest().yhId,
            yhzh:$("#e_yhzh").val(),
            yhxb:$("#e_yhxb").val(),
            password:$("#e_password").val(),
            _method:"PUT"},
        error:function(XMLHttpRequest, textStatus, errorThrown){
            if(XMLHttpRequest.responseText=="loginError"){
                window.location.href="/login.html";
            }
        },
        success:function(data){
            window.location.href="user.html";
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
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}



