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
		action="" id="listform"
		name="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">公告管理 </strong>
			</div>
	
			<table class="table table-hover text-center">
				<tr>
					<td width="130px" style="text-align:left; padding-left:20px;">
						<input type="checkbox" name="ToR" id="checkall"
						onclick="allCol();">全选 <input type="checkbox" name="ToR"
						id="fanxuan" onclick="contraryCol();">反选</td>
					<th>公告标题</th>
					<th>发布人姓名</th>
					<th>发布对象</th>
					<th>公告时间</th>
					<th>操作</th>
				</tr>

				<s:iterator id="item" value="noticeList" status="i">
					<tr>
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="SubCheck" value="${stuNo}"
							onclick="SingleCol();" />${i.index+1}</td>
						<td><a href="<%=path%>/system/Notice_noticeDetails.action?notice.noticeNo=${noticeNo}">${noticeTitle}</a></td>
						<td>${publisherName}</td>
						<td><s:if test="%{#item.pubObject=='all'}">全部</s:if><s:elseif test="%{#item.pubObject=='tutor'}">导师</s:elseif>
						<s:elseif test="%{#item.pubObject=='student'}">学生</s:elseif>
						</td>
						<td>${noticeTime}</td>
						<td><div class="button-group">
						<a class="button border-main"
									href="<%=path%>/system/Notice_noticeDetails.action?notice.noticeNo=${noticeNo}">
									<span class="icon-edit"></span> 查看</a> 
								<a class="button border-main"
									href="<%=path%>/system/Notice_toUpdateNotice.action?notice.noticeNo=${noticeNo}">
									<span class="icon-edit"></span> 修改</a> 
									<a class="button border-red"
									href="<%=path%>/system/Notice_deleteNotice.action?notice.noticeNo=${noticeNo}"
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
