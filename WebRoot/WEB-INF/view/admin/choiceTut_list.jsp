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
				<strong class="icon-reorder">选导概况 </strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main "
						href="<%=path%>/system/Student_alreadyChoiceTut.action"> 已选</a>
					</li>
					<li><a class="button border-red"
						href="<%=path%>/system/Student_NoChoiceTut.action"> 未选</a>
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
						序号</td>
					<th>状态</th>
					<th>学号</th>
					<th>姓名</th>
					<th>班级</th>
					<th>所属学院</th>
					<th>导师</th>
					<th>操作</th>
				</tr>

				<s:iterator id="item" value="studentList" status="i">
					<tr>
						<td style="text-align:left; padding-left:20px;">${i.index+1}</td>
						<td><s:if test="%{#item.tutName!=null && #item.tutName!=''}">已选</s:if><s:else>未选</s:else></td>
						<td>${stuNo}</td>
						<td>${stuName}</td>
						<td>${className}</td>
						<td>${instituteName}</td>
						<td>${tutName}</td>

						<td><div class="button-group">
								<s:if test="%{#item.tutName!=null && #item.tutName!=''}"><a class="button border-main"
									href="<%=path%>/system/Student_toUpdateStu.action?stuNo=${stuNo}">
									<span class="icon-edit"></span> 修改</a></s:if>
						<s:elseif test="%{#item.tutName==null || #item.tutName==''}"><a class="button border-main"
									href="<%=path %>/system/Apply_randomAllot.action?studentForm.stuNo=${stuNo}" onclick="allotApply();">
									<span class="icon-edit"></span> 自动分配</a> 
									<a class="button border-red"
									href="<%=path %>/system/Apply_toAllotCT.action?stuNo=${stuNo}">
									<span class="icon-edit"></span> 人工分配</a> </s:elseif>
								
							</div>
						</td>
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
