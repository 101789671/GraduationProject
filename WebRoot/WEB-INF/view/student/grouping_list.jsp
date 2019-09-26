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
			<strong class="icon-reorder">分组信息 </strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left:10px;">

				<li><a class="button border-red"
					href="<%=path%>/stu/Student_listGrouping.action">
						<span class="icon-edit"></span> 更多</a></li>
				<li>搜索：</li>
				<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
					class="input"
					style="width:250px; line-height:17px;display:inline-block" /> <a
					href="javascript:void(0)" class="button border-main icon-search"
					onclick="changesearch()"> 搜索</a></li>

			</ul>

		</div>

		<table class="table table-hover text-center">
			<tr>

				<th>答辩类型</th>
				<th>学号</th>
				<th>姓名</th>
				<th>班级</th>
				<th>导师</th>
				<th>专业</th>
				<th>答辩分组</th>
				<th>组内答辩顺序</th>
				<th>答辩地点</th>
				<th></th>
			</tr>

			<s:iterator id="item" value="groupingList" status="i">
				<tr>
					<td>${replyTypeName}</td>
					<td>${stuNo}</td>
					<td>${stuName}</td>
					<td>${className}</td>
					<td>${tutName}</td>
					<td>${majorName}</td>
					<td>${groupingsNo}</td>
					<td>${orders}</td>
					<td>${places}</td>
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

	</script>
</body>
</html>
