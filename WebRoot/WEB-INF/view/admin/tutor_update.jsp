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

	<c:forEach items="${tutorList}" var="tutor">
			<form method="post" class="form-x" id="myform"
				action="<%=path%>/system/Tutor_updateTutor.action?tutNo=${tutor.tutNo}">
			<div class="form-group">
	
					<div class="label">
						<label>导师编号：</label>
					</div>
					<div class="field">
						<input readOnly="true" type="text" class="input w50" id="tutorForm.tutNo" value="${tutor.tutNo}"
							name="tutorForm.tutNo" data-validate="required:请输入编号" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>导师姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50"  id="tutorForm.tutName" value="${tutor.tutName}"
							name="tutorForm.tutName" data-validate="required:请输入姓名"  />
						<div class="tips"></div>
					</div>
				</div>
				
						<div class="form-group">
					<div class="label">
						<label>性别：</label>
					</div>
					<div class="field">
						<select class="input w50" name="tutorForm.sex" id="tutorForm.sex" >
								    <option >男</option>
								    <option>女</option></select>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>导师职称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50"  id="tutorForm.tutName" value="${tutor.position}"
							name="tutorForm.position"   />
						<div class="tips"></div>
					</div>
				</div>
		
				<div class="form-group">
					<div class="label">
						<label>所属学院：</label>
					</div>
					<div class="field">
						  <select class="input w50" name="tutorForm.instituteName" id="tutorForm.instituteName" >
								   </select>
						<div class="tips"></div>
					</div>
				</div>

			
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit" ">
							提交</button>
					</div>
				</div>
				</div>
			</form>
			</c:forEach>
		</div>
	</div>




</body>
<script type="text/javascript">

	
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}
	
var array = new Array();
	//console.info("info");
var i =0;
	var classLi = new Array();
	var tutorList;
		<c:forEach items="${classlist}" var="item" varStatus="status" >
		var myclass= new Array();
		myclass[0] = "${item.instituteName}";
		if(myclass.length>0){classLi[i] = myclass;}
		i++;
	</c:forEach> 
		<c:forEach items="${tutorList}" var="item2" varStatus="status" >
		var myclass= new Array();
		tutorList = "${item2.instituteName}";
	</c:forEach> 
   //获取学院 
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
	//添加学院框
	for(i=0;i<instituteName.length;i++){
	   var obj=document.getElementById("tutorForm.instituteName");//根据id查找对象，
         var addOption=new Option(""+instituteName[i]+"",""+instituteName[i]+"");//生成一个选项
         //判断并设置默认选中
         if(tutorList==instituteName[i]){
         addOption.selected="selected";
         }
         obj.options.add(addOption); //这个兼容IE与firefox
         }
      
</script>
</html>
