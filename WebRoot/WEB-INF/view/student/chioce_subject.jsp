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
<link rel="stylesheet" href="<%=path%>/css/custom.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
</head>
<body onload="javascript : document.listform.reset()">
	<form method="post" action="<%=path %>/stu/Student_submintCS.action"
		id="listform" name="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">题目选择</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
					<li><a class="button border-main"
						href="<%=path%>/stu/Student_findCS.action"> 申请记录</a></li>
				</ul>
				<p></p>
				<ul class="search" style="padding-left:10px;">
					<div class="Ssubjtct"><li>自定义出题：</li>
					<li><c:forEach items="${tsControlList}" var="ts">
							<input type="text" placeholder="请输入题目" name="apply.apContent"
								class="input"
								style="width:250px; line-height:17px;display:inline-block" 
								<c:if test="${ts.permissions=='F'}">disabled="true "</c:if>
								/>
<button class="button bg-main icon-check-square-o" <c:if test="${ts.permissions=='F'}">  type="button"  </c:if> <c:if test="${ts.permissions=='T'}">  type="submit"   </c:if>>提交</button>
							<c:if test="${ts.permissions=='F'}"><div class="Ssub_remind">注：导师已禁止学生自出题，请选择导师提供的题目</div></c:if>
						</c:forEach>
					</li>
</div>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<td width="130px" style="text-align:left; padding-left:20px;">
						<input type="checkbox" name="ToR" id="checkall"
						onclick="allCol();">全选 <input type="checkbox" name="ToR"
						id="fanxuan" onclick="contraryCol();">反选</td>
					<th>题目</th>
					<th>操作</th>
				</tr>

				<s:iterator id="item" value="subjectList" status="i">
					<tr>
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="SubCheck" value="${subNo}"
							onclick="SingleCol();" />${i.index+1}</td>
						<td>${subjectName}</td>
						<td><div class="button-group">
								<a class="button border-main"
									href="<%=path %>/stu/Student_submintCS.action?subject.subNo=${subNo}" onclick="SRemindChioceSub();"><span
									class="icon-edit"></span> 选择</a>
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
	</script>
</body>
</html>
