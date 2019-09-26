<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="<%=path%>/css/pintuer.css">
<link rel="stylesheet" href="<%=path%>/css/custom.css">
<link rel="stylesheet" href="<%=path%>/css/admin.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
<style type="text/css">
</style>
</head>
<body>   
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>密码修改</strong>
		</div>
		<form method="post" id="updatePwdForm" action="<%=path %>/system/Access_updatePwd.action">
		<div class="updateS">
		  <div class="update1S"><div class="update1">原密码:</div><div ><input id="oldPwd" name="oldPwd" type="password" class="inputW50" autocomplete="off"></div></div>
		  <div class="update1S"><div class="update1">新密码:</div><div><input type="password" id="newPwd" class="inputW50" onmouseout="VerificationPwd();" autocomplete="off"></div></div>
		  <div class="update1S"><div class="update1">确认新密码:</div><div style="float: left;"><input type="password" id="user.pwd" name="user.pwd" class="inputW50" onmouseout="VerificationPwd();" autocomplete="off"></div><div id="remind" class="updatePwdRemind"></div></div>	 
		
		<div class="form-group" style="margin-left: 150px;">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
							<button class="button bg-main icon-check-square-o" type="button"
								onclick="thissubmit();">提交</button>
						</div>
					</div>
					</div>
		</form>
</body>
<script type="text/javascript">

	
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}

function VerificationPwd (){
 var newPwd = document.getElementById("newPwd").value;
 var userPwd = document.getElementById("user.pwd").value;
 var remind = document.getElementById("remind");
 var Tof;
 if(newPwd!=userPwd){
 remind.innerHTML="*密码不一致";
 Tof= false;
 }else{
  remind.innerHTML="";
  Tof= true;
 }
 return Tof;
}



	function thissubmit() {
		var oldPwd = document.getElementById("oldPwd").value;
		var newPwd = document.getElementById("newPwd").value;
		var userPwd = document.getElementById("user.pwd").value;
		if (oldPwd != "") {
			if (newPwd != "" || userPwd != "") {
				var Tof = VerificationPwd();
				if (Tof) {
					var updatePwdForm = document
							.getElementById("updatePwdForm");
					updatePwdForm.submit();
				} else {
					alert("密码不一致");
				}
			} else {
				alert("新密码不可为空！")
			}
		} else {
			alert("原密码不可为空！")
		}
	}
</script>
</html>
