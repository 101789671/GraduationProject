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
<link rel="stylesheet" href="<%=path%>/css/admin.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>

</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>我的导师</strong>
		</div>
		<div class="body-content">
			<script LANGUAGE="JavaScript">
function submitForm(){
    var form = document.getElementById("myform");
    form.submit();
}
</script>

			<form method="post" class="form-x" id="myform"
				action="<%=path%>/zcpo/zcpolicy_addZcpo.action">

				
<s:iterator id="item" value="tutorList" status="i">
				<div class="form-group">
					<div class="label">
						<label>导师姓名：</label>
					</div>
					<div class="field">
					<input type="text" class="input w50" value="${tutName}"
							name="zcpilicyForm.illName"  disabled="true " />
						<div class="tips"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label>性别：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${sex}"
							name="zcpilicyForm.illName" disabled="true "/>
						<div class="tips"></div>
					</div>
				</div>
<div class="form-group">
					<div class="label">
						<label>职称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${position}"
							name="zcpilicyForm.rate" disabled="true " />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>所属学院：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${instituteName}"
							name="zcpilicyForm.maxLine" disabled="true " />
						<div class="tips"></div>
					</div>
				</div>
</s:iterator>

				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<a class="button border-red"
									href="<%=path %>/stu/Student_delApply.action?apID=${apId}" onclick="Sonedel();">
									<span class="icon-edit"></span> 申请更改</a> 
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
	<script type="text/javascript">
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}
	</script>
</html>
