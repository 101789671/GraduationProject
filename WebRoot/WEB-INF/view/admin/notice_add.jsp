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
</head>
<body>   
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>发布公告</strong>
		</div>
				<div id="Divss" class="Divss">
			<div id="Divs_1" class="uploadFileDiv">
			
				<form  action="<%=path%>/system/File_importStudent.action"
					method="post" enctype="multipart/form-data">
					请选择文件：<input type="file" id="dofile" name="file" /> 
					                   <input class="daoruButton" type="submit" id="btnupload" name="btnupload" value="导入">
				</form>
			</div>
		</div>
		<form method="post" class="form-x" id="addNoticeForm"
			action="<%=path%>/admin/Admin_addNotice.action?notice.position=admin">
		  <div class="noticeSS">	
		
			   <div class="noticeS"><div class="notice1">标题：</div><div ><input type="text" name="notice.noticeTitle"></div></div>
			   <div class="noticeS"><div class="notice1">内容：</div><div><textarea name="notice.noticeContent" id="notice.noticeContent"></textarea></div></div>	
			   <div class="noticeS1"><div>发布对象：
			       <label><input type="radio" name="notice.pubObject" value="all" id="notice.pubObject"  checked="CHECKED">全部</label>
			       <label><input type="radio" name="notice.pubObject" value="tutor" id="RadioGroup1_1" > 导师</label>
			       <label><input type="radio" name="notice.pubObject" value="student" id="RadioGroup1_2" >学生</label>
			   </div>
			   <div class="noticeSubmit">
			     <div class="form-group">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
							<button class="button bg-main icon-check-square-o" type="button" onclick="addNotice();">提交</button>
						</div>
					</div>
			  </div>
			  </div>
		  </div>
		</form>

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
