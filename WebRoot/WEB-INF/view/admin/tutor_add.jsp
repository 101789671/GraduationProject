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
			<strong><span class="icon-pencil-square-o"></span>添加导师</strong>
		</div>
				<div id="Divss" class="Divss">
			<div id="Divs_1" class="uploadFileDiv">
			
				<form action="<%=path%>/system/File_importTutor.action"
					method="post" enctype="multipart/form-data">
					请选择文件：<input type="file" id="dofile" name="file" /> 
					                   <input class="daoruButton" type="submit" id="btnupload" name="btnupload" value="导入">
				</form>
			</div>
		</div>
		<form method="post" class="form-x" id="addTutorform"
			action="<%=path%>/system/Tutor_addTutro.action?role=tutor">
			<div class="fuckPrent">
				<div id="Divs" class="fuck">
					<div id="Divs_1" class="Divs_1">
						<table width="100%" height="100%" align="center" id="tables">
							<tr></tr>
							<tr id="trmod2">
								<td valign="middle" class="claTd"></td>
								<td valign="middle" class="claTd">教师编号： <input type="text"
									name="tutorForm.tutNo" id="tutorForm.tutNo">
								</td>
								<td valign="middle" class="claTd">姓名： <input type="text"
									name="tutorForm.tutName" id="tutorForm.tutName">
								</td>
								<td valign="middle" class="claTd">性别： 
								  <select name="tutorForm.sex" id="tutorForm.sex" class="selectS">
								    <option >男</option>
								    <option>女</option>
					            </select></td>
					            <td valign="middle" class="claTd">职称： <input type="text"
									name="tutorForm.position" id="tutorForm.position">
								</td>
								<td valign="middle" class="claTd">所属学院： <select name="tutorForm.instituteName" id="tutorForm.instituteName" class="selectS">
										<option>请选择学院</option>
								</td>
								<td valign="middle" class="claTd"><input name="addclass"
									type="button" id="claButton" value="+" style="cursor:default"
									onclick="addstuTr();"> <input name="addclass"
									type="button" id="claButton2" value="-" style="cursor:default"
									onclick="removeTr();">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="button"
							onclick="addTutorSubmit();">提交</button>
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
	
var array = new Array();
	//console.info("info");
var i =0;
	var classLi = new Array();
	
		<c:forEach items="${classlist}" var="item" varStatus="status" >
		var myclass= new Array();
		myclass[0] = "${item.instituteName}";
		if(myclass.length>0){classLi[i] = myclass;}
		i++;
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
         obj.options.add(addOption); //这个兼容IE与firefox
         }
      
</script>
</html>
