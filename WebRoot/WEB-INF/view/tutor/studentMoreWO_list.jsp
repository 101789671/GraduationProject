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
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder">作品/更多</strong>
		</div>
				<form action="<%=path %>/download/download_downloadFile.action">
			<div class="padding border-bottom">
				<ul class="search" style="padding-left:10px;">
				<li><a class="button border-red" href="<%=path%>/tut/Tutor_toStudentGPDatumSurvey.action?gpFile.fileCatNum=WO"> <span>
							</span><a>返回</a></li>
				<li>
							<button class="button border-main" type="submit"><span
							class="icon-edit"></span>批量下载</button>
				<li>
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
					<input type="checkbox" name="ToR" id="checkall" onclick="TallCol();">全选
					<input type="checkbox" name="ToR" id="fanxuan"
					onclick="TcontraryCol();">反选</td>
				<th>类别</th>
				<th>学号</th>
				<th>姓名</th>
				<th>文件名</th>
				<th>上传时间</th>
				<th>操作</th>
			</tr>

			<s:iterator id="item" value="gpFileList" status="i">
				<tr>
					<td style="text-align:left; padding-left:20px;"><input
						type="checkbox" name="SubCheck"
						onclick="TSingleCol();" />${i.index+1}
						<input
						type="checkbox" name="gpFile.fileNo" value="${fileNo}"
						 style="display:none" disabled /></td>
					<td>${fileCategory}</td>
					<td>${uploaderId}</td>
					<td>${uploaderName}</td>
					<td>${fileName}</td>
					<td>${uploadTime}</td>
					<td><div class="button-group">
							<a class="button border-main"
								href="<%=path %>/system/File_onlineReading.action?gpFile.fileNo=${fileNo}"
								><span class="icon-edit"></span>
							在线查看</a> 
						<a class="button border-red"
								href="<%=path %>/download/download_downloadFile.action?gpFile.fileNo=${fileNo}"
							><span class="icon-edit"></span>
							下载</a> 
						</div>
					</td>
				</tr>
			</s:iterator>
		</table>
			</form>
	</div>

	<script type="text/javascript">
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}

//搜索
	</script>
</body>
</html>
