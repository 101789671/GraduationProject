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
			<strong><span class="icon-pencil-square-o"></span>班级修改</strong>
		</div>
		<div class="body-content">

	<c:forEach items="${classlist}" var="myclass">
			<form method="post" class="form-x" id="myform"
				action="<%=path%>/system/Class_updataclass.action?classNo=${myclass.classNo}">
				
				<div class="form-group">
					<div class="label">
						<label>班级编号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50"  id="myclass.classNo" value="${myclass.classNo}"
							name="myclass.classNo" data-validate="required:请输入班级编号" readOnly="true" />
						<div class="tips"></div>
					</div>
				</div>
			<div class="form-group">
					<div class="label">
						<label>班级名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" id="myclass.classtuName" value="${myclass.classtuName}"
							name="myclass.classtuName" data-validate="required:请输入班级名称"  />
						<div class="tips"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="label">
						<label>所属学院：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50"  id="myclass.instituteName" name="myclass.instituteName" value="${myclass.instituteName }" 
							data-validate="required:请输入所属学院" />
						<div class="tips"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
				</div>
			</form>
			</c:forEach>
		</div>
	</div>

</body>
</body>
</html>
