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
.noticeDetailsS{
width: 940px;
border: 1px double #999;
margin-left: 200px;
margin-bottom: 30px;
margin-top: 10px;
border-radius: 0.3em;
background: -webkit-linear-gradient(top,#F1E6DE,#D7CADF,#F1E6DE);
}
.noticeDetailsTitle{
width:900px;
height: 50px;
border-bottom: 2px double #5298CB;
margin-left: 20px;
margin-top: 20px;
padding-top:10px;
text-align: center;
font-size: 22px;
}
.noticeDetailsSz_textarea{
width:900px;
height:550px;
resize:none;
margin-left: 20px;
margin-top: 15px;
font-size: 16px;
cursor: auto;
border: 0px;
background-color:transparent;

}
.noticeDetail1{
width:500px;
height: 15px;
text-align: center;
font-size: 12px;
margin-top: 10px;
margin-left: 335px;

}
.noticeDetail1_1{
margin-left: 20px;
float: left;
}
</style>
</head>
<body>   
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>公告详情</strong>
		</div>
		<s:iterator id="item" value="noticeList" status="i">
		<div class="noticeDetailsS">
		    <div class="noticeDetailsTitle">${noticeTitle}</div>
		    <div class="noticeDetail1"><div class="noticeDetail1_1">发布者：${publisherName}</div><div class="noticeDetail1_1">发布时间：${noticeTime}</div></div>
		    <div ><textarea class="noticeDetailsSz_textarea"  onfocus=this.blur()>${noticeContent}</textarea></div>
		</div>
		</s:iterator>
	</div>
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
