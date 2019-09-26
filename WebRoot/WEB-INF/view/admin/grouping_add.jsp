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
<link rel="stylesheet" href="<%=path%>/css/custom.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>答辩分组</strong>
		</div>
		
		<div id="Divss" class="Divss">
			<div id="Divs_1" class="uploadFileDiv">
			
				<form action="<%=path%>/system/File_importGrouping.action"
					method="post" enctype="multipart/form-data">
					请选择文件：<input type="file" id="dofile" name="file" /> 
					                   <input class="daoruButton" type="submit" id="btnupload" name="btnupload" value="导入">
				</form>
			</div>
		</div>
		
		<div class="body-content">
			<form method="post" class="form-x" id="myform"
				action="<%=path%>/system/grouping_answerGrouping.action">
				<div class="form-group">

					<div class="label">
						<label>答辩类型：</label>
					</div>
					<div class="field">
						<select class="input w50" name="grouping.replyType"
							id="rouping.replyType">
							<option value="TPR">开题答辩</option>
							<option value="GRR">毕业答辩</option>
						</select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit"">
							一键分组</button>
					</div>
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

	var i = 0;
	var classLi = new Array();
	var InsTutS = new Array();
	var tutorList;

	//获取所有学院-班级
	<c:forEach items="${classlist}" var="item" varStatus="status" >
	var myclass = new Array();
	myclass[0] = "${item.instituteName}";
	myclass[1] = "${item.classtuName}";
	if (myclass.length > 0) {
		classLi[i] = myclass;
	}
	i++;
	</c:forEach>

	//获取所有学院-导师
	i = 0;
	<c:forEach items="${tutorList}" var="item1" varStatus="status" >
	var instut = new Array();
	instut[0] = "${item1.instituteName}";
	instut[1] = "${item1.tutName}";
	if (instut.length > 0) {
		InsTutS[i] = instut;
	}
	i++;
	</c:forEach>

	//获取学生所在学院、导师姓名

	<c:forEach items="${studentList}" var="item3" varStatus="status" >
	var myclass = new Array();
	studentList = "${item3.instituteName}";//获取学生所在学院
	stuTutname = "${item3.tutName}";//获取学生的导师姓名
	</c:forEach>

	//获取导师所在学院

	<c:forEach items="${tutorList2}" var="item2" varStatus="status" >
	var myclass = new Array();
	tutorList = "${item2.instituteName}";
	</c:forEach>

	//获取出重的学院 
	var instituteName = new Array();
	var Tof;
	var j = 0, k = 0;
	for (i = 0; i < classLi.length; i++) {
		Tof = "ture";

		for (j = 0; j <= i; j++) {
			if (instituteName[j] == classLi[i][0]) {
				Tof = "false";
				break;
			}
		}
		if (Tof == "ture") {
			instituteName[k] = classLi[i][0];
			k++;
		}

	}
	//"导师"添加
	if (tutorList != null) {
					var obj = document.getElementById("tutorForm.instituteName");//获取下拉框
		for (i = 0; i < instituteName.length; i++) {
			var addOption = new Option("" + instituteName[i] + "", ""
					+ instituteName[i] + "");//生成一个选项
			//判断并设置默认选中
			if (tutorList == instituteName[i]) {
				addOption.selected = "selected";
			}
			obj.options.add(addOption); //这个兼容IE与firefox
		}
		//"导师"添加默认导师名
		var obj3 = document.getElementById("studentForm.tutName");//获取班级下拉框
		for (j = 0; j < InsTutS.length; j++) {
			if (tutorList == InsTutS[j][0]) {
				var addOption = new Option("" + InsTutS[j][1] + "", ""
						+ InsTutS[j][1] + "");//生成一个选项	
									//判断并设置默认选中
			if (stuTutname == InsTutS[j][1]) {
				addOption.selected = "selected";
			}
				obj3.options.add(addOption); //这个兼容IE与firefox
			}
		}
	} else {
	//"导师"添加学院框
				var obj = document.getElementById("tutorForm.instituteName");//获取下拉框
			for (i = 0; i < instituteName.length; i++) {
			var addOption = new Option("" + instituteName[i] + "", ""
					+ instituteName[i] + "");//生成一个选项
			obj.options.add(addOption); //这个兼容IE与firefox
			}
	}

	//"班级"添加学院框
	for (i = 0; i < instituteName.length; i++) {
		var obj = document.getElementById("studentForm.instituteName");//获取下拉框

		var addOption = new Option("" + instituteName[i] + "", ""
				+ instituteName[i] + "");//生成一个选项
		//判断并设置默认选中
		if (studentList == instituteName[i]) {
			addOption.selected = "selected";
		}
		obj.options.add(addOption); //这个兼容IE与firefox

	}
	//"班级"添加默认班级
	var obj4 = document.getElementById("studentForm.className");//获取班级下拉框
	for (j = 0; j < classLi.length; j++) {
		if (studentList == classLi[j][0]) {
			var addOption = new Option("" + classLi[j][1] + "", ""
					+ classLi[j][1] + "");//生成一个选项	
			obj4.options.add(addOption); //这个兼容IE与firefox
		}
	}
</script>
</html>
