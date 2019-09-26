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

<link rel="stylesheet" href="<%=path%>/css/switch.css">
<link rel="stylesheet" href="<%=path%>/css/switch.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
</head>
<body>


	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>添加题目</strong>
		</div>
		<div class="subject_switchS">
			<div class="subject_Text">
				学生自出题权限：<label><input class="mui-switch mui-switch-animbg"
					type="checkbox" name="tsSwitch"
					<c:forEach items="${tsControlList}" var="ts"><c:if test="${ts.permissions == 'T'}">checked</c:if> </c:forEach>
					id="tsSwitch" onclick="subSwitch();">
				</label>
			</div>
		</div>
		<div id="Divss" class="Divss">
			<div id="Divs_1" class="uploadFileDiv">

				<form action="<%=path%>/system/File_importSubject.action"
					method="post" enctype="multipart/form-data">
					请选择文件：<input type="file" id="dofile" name="file" /> <input
						class="daoruButton" type="submit" id="btnupload" name="btnupload"
						value="导入">
				</form>
			</div>
		</div>
		<form method="post" class="form-x" id="addclassform"
			action="<%=path %>/sub/Subject_addSubject.action">
			<div class="DivSS">


				<div id="Divs_1" class="Divs_1">
					<table width="100%" height="100%" align="center" id="tables">
						<tr id="trmod2">
							<td valign="middle" class="claTd">
								<div class="subject_fileInput">
									题目： <input type="text" name="subject.subjectName"
										id="subject.subjectName">
								</div></td>
							<td valign="middle" class="claTd"><input name="addclass"
								type="button" id="claButton" value="+" style="cursor:default"
								onclick="addSubjectTR1();"> <input name="addclass"
								type="button" id="claButton2" value="-" style="cursor:default"
								onclick="removeSubjectTr();"></td>
						</tr>
					</table>
				</div>

				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">提交</button>
					</div>
				</div>
			</div>
		</form>

	</div>
	<div id="Divsmodes" style="display:none" mce_style="display:none">
		<div id="Divsmode" class="Divs">
			<div id="Divs_1" class="Divs_1">
				<table width="100%" height="100%" align="center" id="tablemode">
					<tr id="trmod">
						<td valign="middle" class="claTd">
							<div class="subject_fileInput">
								题目： <input type="text" name="subject.subjectName"
									id="subject.subjectName"">
							</div></td>
						<td valign="middle" class="claTd"><input name="addclass"
							type="button" id="claButton" value="+" style="cursor:default"
							onclick="addSubjectTR1();"> <input name="addclass"
							type="button" id="claButton2" value="-" style="cursor:default"
							onclick="removeSubjectTr();"></td>
					</tr>
				</table>
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
</html>
