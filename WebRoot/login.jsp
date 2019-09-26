<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
    <link rel="stylesheet" href="<%=path %>/css/admin.css">
    <script src="<%=path %>/js/jquery.js"></script>
    <script src="<%=path %>/js/pintuer.js"></script>  
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="<%=path%>/system/Access_login.action" method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top">
                  <h1><img src="images/logo.png" width="258" height="44"></h1>
                </div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="user.userid" placeholder="登录账号" data-validate="required:请填写账号" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name="user.pwd" placeholder="登录密码" data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" name="yzcode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                           <img src="<%=path %>/servlet/KaptchaServlet" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">  
                                                   
                        </div>
                    </div>
                   <div class="form-group">
                        <div class="zdyconter">
                          <p>
                            <label>
                               <input type="radio" name="user.role" value="admin" id="userType_0">
                              管理员</label>
                           
                            <label>
                              <input name="user.role" type="radio" id="userType_1" value="student" checked="CHECKED">
                              学生</label>
                           
                            <label>
                              <input type="radio" name="user.role" value="tutor" id="userType_2">
                              导师</label>
                           
                          </p>

                           
                        </div>
                    </div>
                    
                </div>
                <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
            </div>
            </form>          
        </div>
    </div>
</div>

</body>
<script type="text/javascript">

	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}
</script>

<script type="text/javascript">  
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(e){  var ev = e || window.event;
//获取event对象  
var obj = ev.target || ev.srcElement;
//获取事件源  
var t = obj.type || obj.getAttribute('type');
//获取事件源类型  
//获取作为判断条件的事件类型  
var vReadOnly = obj.getAttribute('readonly');  
var vEnabled = obj.getAttribute('enabled');  
//处理null值情况  
vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
vEnabled = (vEnabled == null) ? true : vEnabled;  
//当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
 //并且readonly属性为true或enabled属性为false的，则退格键失效  
 var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")  && (vReadOnly==true || vEnabled!=true))?true:false;  
 //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
 var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  ?true:false;  
 //判断  
 if(flag2){  return false;  }  if(flag1){  return false;  }  }  
 //禁止后退键 作用于Firefox、Opera  
 document.onkeypress=banBackSpace;  
 //禁止后退键 作用于IE、Chrome  
 document.onkeydown=banBackSpace;  
 </script>
</html>