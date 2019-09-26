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
<script src="<%=path%>/js/custom.js"></script>

</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>学生题目修改</strong>
		</div>
		<div class="body-content">


			<form method="post" class="form-x" id="myform"
				action="<%=path%>/tut/Tutor_updateStuSubT.action?">
				<s:iterator id="item" value="studentLsit" status="i">
				<div class="form-group">
					<div class="label">
						<label>学号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50"  id="student.stuNo" value="${stuNo}"
							name="student.stuNo" data-validate="required:请输入班级编号" disabled />
						<div class="tips"></div>
					</div>
				</div>
			<div class="form-group">
					<div class="label">
						<label>姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" id="student.stuName" value="${stuName}"
							name="student.stuName" data-validate="required:请输入班级名称"  disabled/>
						<div class="tips"></div>
					</div>
				</div>
					<div class="form-group">
					<div class="label">
						<label>性别：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" id="student.sex" value="${sex}"
							name="student.sex" data-validate="required:请输入班级名称" disabled />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>班级：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" id="student.className" value="${className}"
							name="student.className" data-validate="required:请输入班级名称" disabled />
						<div class="tips"></div>
					</div>
				</div>
					<div class="form-group">
					<div class="label">
						<label>学院：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" id="student.instituteName" value="${instituteName}"
							name="student.instituteName" data-validate="required:请输入班级名称" disabled />
						<div class="tips"></div>
					</div>
				</div>

					<div class="form-group">

						<div class="label">
							<label><p>
									<input type="checkbox" name="ToR" id="checkall"
									checked="checked"	onclick="ThaveCol();">选择已有题目：
								</p>
								<p>
									<input type="checkbox" name="ToR" id="fanxuan"
										onclick="TManualCol();">手动输入题目：
								</p>
							</label>
						</div>
						<div class="field">
							<p></p>
							<p></p>
							<input type="text" class="input w50" id="subject.input"
								value="" name="student.subject"  />
								
								<select class="input w50" name="student.subject"
								id="subject.select">
								<s:iterator id="item2" value="subjectList" status="i">
									<option>${subjectName}</option>
								</s:iterator>
							</select>

							<div class="tips"></div>
						</div>
					</div>

				</s:iterator>
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
		</div>
	</div>

</body>
<script type="text/javascript">
var c1 = document.getElementsByName("ToR");
	   var input = document.getElementById("subject.input");
	     c1[0].checked = true;
	   input.setAttribute("style", "display:none");
	   input.setAttribute("disabled","disabled");

</script>
</html>
