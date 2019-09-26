<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
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
<body onload="javascript : document.listform.reset()">

	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder">成绩信息 </strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left:10px;">
				<form method="post" name="findanserGrouping" id="findanserGrouping"
					action="<%=path%>/tut/Tutor_listGpResults.action?">
					<li>查询： <select class="select1" name="gpResults.reType"
						id="gpResults.reType">
							<option value="TPG">开题答辩</option>
							<option value="PAG">毕业论文</option>
							<option value="WOG">毕业设计</option>
					</select> <select class="select1" name="select1" id="select1"
						onchange="selcet1to2();">
							<option>全部</option>
							<option>按班级</option>
							<option>按专业</option>
							<option>按学号</option>
					</select> <select class="select2" name="select2" id="select2"
						onchange="select2to3();">

					</select> <select class="select2" name="select3" id="select3">

					</select> <input type="text" name="gpResults.stuNo" id="gpResults.stuNo"
						class="select2" />
					</li>

					<li><input type="submit" name="button" value="查询"
						class="button border-main icon-search" /></li>
				<li><a class="button border-red"
					href="<%=path%>/tut/Tutor_listGpResults.action">
						<span class="icon-edit"></span> 更多</a></li>
					<li>搜索：</li>


					<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
						class="input"
						style="width:250px; line-height:17px;display:inline-block" /> <a
						href="javascript:void(0)" class="button border-main icon-search"
						onclick="changesearch()"> 搜索</a>
					</li>
			</ul>

		</div>

		<table class="table table-hover text-center">
			<tr>
				<th>成绩类型</th>
				<th>学号</th>
				<th>姓名</th>
				<th>班级</th>
				<th>导师</th>
				<th>专业</th>
				<th>答辩分组</th>
				<th>组内答辩顺序</th>
				<th>题目</th>
				<th>成绩</th>
				<th>评语</th>
				<th></th>
			</tr>

			<s:iterator id="item" value="gpResultsList" status="i">
				
					<td>${reTypeName}</td>
					<td>${stuNo}</td>
					<td>${stuName}</td>
					<td>${className}</td>
					<td>${tutName}</td>
					<td>${majorName}</td>
					<td>${groupingsNo}</td>
					<td>${orders}</td>
					<td>${subject}</td>
					<td>${results}</td>
					<td>${comments}</td>
					<td><input type="text" class="button border-main" disabled="disabled" style="visibility:hidden;width:0"/></td>			
				</tr>
			</s:iterator>
		</table>
	</div>
	<script type="text/javascript">
		var message = "${requestScope.message}";
		if (message != "") {
			alert(message);
		}

		var InsAndClassName = new Array();
		var MajorName = new Array();

		var i = 0;
		//获得学院和班级
		<c:forEach items="${classList}" var="item2" varStatus="status" >
		var myclass = new Array();
		myclass[0] = "${item2.instituteName}";
		myclass[1] = "${item2.classtuName}";
		if (myclass.length > 0) {
			InsAndClassName[i] = myclass;
		}
		i++;
		</c:forEach>

		//获得专业
		i = 0;
		<c:forEach items="${classList1}" var="item2" varStatus="status" >
		var myclass = null;
		myclass = "${item2.majorName}";
		if (myclass.length > 0) {
			MajorName[i] = myclass;
		}
		i++;
		</c:forEach>

		var JSgpResults = new Array();
		<c:forEach items="${JSgpResultsList}" var="item3" varStatus="status" >
		JSgpResults[0] = "${item3.reType}";
		JSgpResults[1] = "${item3.className}";
		JSgpResults[2] = "${item3.majorName}";
		JSgpResults[3] = "${item3.stuNo}";
		</c:forEach>
		tutorGpResultSelect();

		
	</script>
</body>
</html>
