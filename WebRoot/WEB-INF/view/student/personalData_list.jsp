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
.personalDataS{
width: 400px;
height: 500px;
background-color: #EBF4FA;
border: 1px double #999;
margin-left: 10px;
margin-top:15px;
margin-bottom:15px;
border-radius: 0.3em;
}
.personalDatas{
height: 40px;
font-size: 16px;
border-bottom: 1px double #ffffff;
padding-top: 8px;
}
.personalData1{
width:100px;
text-align: right;
float: left;
}
.personalData2{
margin-left: 110px;
}
</style>
</head>
<body>   
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>个人资料</strong>
		</div>
		<s:iterator id="item" value="studentList" status="i">
		<div class="personalDataS">
		  <div class="personalDatas"> <div class="personalData1">学号:</div> <div class="personalData2">${stuNo}</div></div>
		  <div class="personalDatas"> <div class="personalData1">姓名:</div> <div class="personalData2">${stuName}</div></div>
		  <div class="personalDatas"> <div class="personalData1">性别:</div> <div class="personalData2">${sex}</div></div>
		  <div class="personalDatas"> <div class="personalData1">班级名:</div> <div class="personalData2">${className}</div></div>
		  <div class="personalDatas"> <div class="personalData1">专业名:</div> <div class="personalData2">${majorName}</div></div>
		  <div class="personalDatas"> <div class="personalData1">所属学院:</div> <div class="personalData2">${instituteName}</div></div>
		</div>
		</s:iterator>
</body>
<script type="text/javascript">

	
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}

function listContent (){
var content = document.getElementById("notice.noticeContent");
console.log("value:",content.value);
}
      
</script>
</html>
