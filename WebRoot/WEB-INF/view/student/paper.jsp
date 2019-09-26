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

<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder">毕业论文</strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left:10px;">
				<li class="text">论文上传</li>
			</ul>
			<div id="Divss" class="Divss">
				<div id="Divs_1" class="uploadFileDiv">

					<form
						action="<%=path%>/system/File_uploadFile.action?gpFile.fileCatNum=PA"
						method="post" enctype="multipart/form-data">
						请选择文件：<input type="file" id="dofile" name="file" /> <input
							class="daoruButton" type="submit" id="btnupload" name="btnupload"
							value="上传">
					</form>
				</div>
			</div>
		</div>
	
		<table class="table table-hover text-center">

			<tr>
				<td width="130px" style="text-align:left; padding-left:20px;">
					<input type="checkbox" name="ToR" id="checkall" onclick="allCol();">全选
					<input type="checkbox" name="ToR" id="fanxuan"
					onclick="contraryCol();">反选</td>
				<th>类别</th>
				<th>文件名</th>
				<th>上传时间</th>
				<th>操作</th>
			</tr>

			<s:iterator id="item" value="gpFileList" status="i">
				<tr>
					<td style="text-align:left; padding-left:20px;"><input
						type="checkbox" name="SubCheck" value="${subNo}"
						onclick="SingleCol();" />${i.index+1}</td>
					<td>${fileCategory}</td>
					<td>${fileName}</td>
					<td>${uploadTime}</td>
					<td><div class="button-group">
							<a class="button border-main"
								href="<%=path %>/system/File_onlineReading.action?gpFile.fileNo=${fileNo}"
								><span class="icon-edit"></span>
							在线查看</a> 
						<a class="button border-main"
								href="<%=path %>/download/download_downloadFile.action?gpFile.fileNo=${fileNo}"
							><span class="icon-edit"></span>
							下载</a> 
							<a class="button border-red"
								href="<%=path %>/system/File_deleteGpFile.action?gpFile.fileNo=${fileNo}"
								onclick="onedel();"><span class="icon-edit"></span>
							删除</a>
						</div>
					</td>
				</tr>
			</s:iterator>
		</table>
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
