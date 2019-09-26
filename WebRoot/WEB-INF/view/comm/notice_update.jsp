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
.noticeSS{
background-color: #EBF4FA;
margin-left: 10px;
border-radius: 0.5em;
border: 1px double #999;
padding-left: 10px;
padding-top: 20px;
width: 910px;
margin-bottom: 20px;
margin-top: 10px;
}
.noticeS{
margin-top: 15px;
margin-left:30px;
margin-bottom: 15px;
font-size: 16px;;
}
.noticeS1{
margin-top: 15px;
margin-bottom: 15px;
font-size: 16px;;
}
.noticeS input{
width: 600px;
height: 35px;
border-radius: 0.2em;
border: 1px double #999;
}
.noticeS textarea{
width: 800px;
height: 500px;
border-radius: 0.2em;
border: 1px double #999;
resize:none;
}
.notice1 {
float: left;
padding-top: 6px;
}
.noticeRadio{
float: left;
}
.noticeSubmit{
padding-left: 300px;
}
</style>
</head>
<body>   
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>发布公告</strong>
		</div>
		<s:iterator id="item" value="noticeList" status="i">
		<form method="post" class="form-x" id="addstuform"
			action="<%=path%>/system/Notice_updateNotice.action?notice.noticeNo=${noticeNo}">
		  <div class="noticeSS">	
			   <div class="noticeS"><div class="notice1">标题：</div><div ><input type="text" name="notice.noticeTitle" value="${noticeTitle}"></div></div>
			   <div class="noticeS"><div class="notice1">内容：</div><div><textarea name="notice.noticeContent" id="notice.noticeContent">${noticeContent}</textarea></div></div>	
			   <div class="noticeS1"><div>发布对象：
			       <label><input type="radio" name="notice.pubObject" value="all" id="notice.pubObject"  <s:if test="%{#item.pubObject=='all'}">checked="CHECKED"</s:if>>全部</label>
			       <label><input type="radio" name="notice.pubObject" value="tutor" id="RadioGroup1_1" <s:if test="%{#item.pubObject=='tutor'}">checked="CHECKED"</s:if>> 导师</label>
			       <label><input type="radio" name="notice.pubObject" value="student" id="RadioGroup1_2" <s:if test="%{#item.pubObject=='student'}">checked="CHECKED"</s:if>>学生</label>
			   </div>
			   <div class="noticeSubmit">
			     <div class="form-group">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
							<button class="button bg-main icon-check-square-o" type="submit" onclick="listContent();">提交</button>
						</div>
					</div>
			  </div>
			  </div>
		  </div>	  
		</form>
</s:iterator>
	</div>
	</table>
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
