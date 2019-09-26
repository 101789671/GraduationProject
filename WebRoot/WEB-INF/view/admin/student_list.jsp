<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common.jsp"%>
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
<body onload="javascript : document.listform.reset()">
	<form method="post"
		action="<%=path %>/system/Student_findStuAll.action" id="listform"
		name="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">添加学生 </strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="<%=path%>/system/Student_toaddStu.action"> 添加学生</a>
					</li>
					<li>
					<input type="submit" name="button" value="删除" onclick="batchDelStu();"  class="button border-red"/>
				</li>
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
					<td width="130px" style="text-align:left; padding-left:20px;">
						<input type="checkbox" name="ToR" id="checkall"
						onclick="allCol();">全选 <input type="checkbox" name="ToR"
						id="fanxuan" onclick="contraryCol();">反选</td>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>班级</th>
					<th>所属学院</th>
					<th>导师</th>
					<th>题目</th>
					<th>操作</th>
				</tr>

				<s:iterator id="item" value="studentList" status="i">
					<tr>
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="SubCheck" value="${stuNo}"
							onclick="SingleCol();" />${i.index+1}</td>
						<td>${stuNo}</td>
						<td>${stuName}</td>
						<td>${sex}</td>
						<td>${className}</td>
						<td>${instituteName}</td>
						<td>${tutName}</td>
						<td>${subject}</td>
						<td><div class="button-group">
								<a class="button border-main"
									href="<%=path%>/system/Student_toUpdateStu.action?student.stuNo=${stuNo}">
									<span class="icon-edit"></span> 修改</a> 
									<a class="button border-red"
									href="<%=path%>/system/Student_delStudent.action?student.stuNo=${stuNo}"
									onclick="javascript:return onedel();"> <spanclass="icon-trash-o">
									</span> 删除
								</a>
							</div></td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</form>
	<script type="text/javascript">
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}

//搜索
function changesearch(){
	
 $("#listform").submit();
}
//全选
$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

	</script>
</body>
</html>
