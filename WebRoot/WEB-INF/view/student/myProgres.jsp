<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/common.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>welcome</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="<%=path%>/css/pintuer.css">
<link rel="stylesheet" href="<%=path%>/css/admin.css">
<link rel="stylesheet" href="<%=path%>/css/custom.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
<script src="<%=path%>/js/WdatePicker.js"></script>
<style type="text/css" id="thisStyle">
</style>
</head>
<body>
	<div class="gpProgresDivSS">
		<div id="gpProgresDivS" class="gpProgressS">
			<div class="gpProgresText">
				<div class="gpPChildrenNoColor"></div>
				<div class=gpPChildrenNoColor>选导</div>
				<div class="gpPChildrenNoColor">选题</div>
				<div class="gpPChildrenNoColor">开题报告</div>
				<div class="gpPChildrenNoColor">开题答辩</div>
				<div class="gpPChildrenNoColor">毕设/论文</div>
				<div class="gpPChildrenNoColor">毕业答辩</div>
				<div class="gpPChildrenNoColor"></div>
			</div>
			<div id="gpProgress" class="gpProgress">
				<div id="p0" class="gpPFristChildren"
					style="background-color: 6caf00;"></div>
				<div id="p1" class=gpPChildrenNoColor>
					<input name="" type="button" onmouseover="display();"
						onmouseout="hide();" style="cursor:default">
				</div>
				<div id="p2" class=gpPChildrenNoColor>
					<input name="" type="button" onmouseover="display();"
						onmouseout="hide();" style="cursor:default">
				</div>
				<div id="p3" class=gpPChildrenNoColor>
					<input name="" type="button" onmouseover="display();"
						onmouseout="hide();" style="cursor:default">
				</div>
				<div id="p4" class="gpPChildrenNoColor">
					<input name="" type="button" onmouseover="display();"
						onmouseout="hide();" style="cursor:default">
				</div>
				<div id="p5" class="gpPChildrenNoColor">
					<input name="" type="button" onmouseover="display();"
						onmouseout="hide();" style="cursor:default">
				</div>
				<div id="p6" class="gpPChildrenNoColor">
					<input name="" type="button" onmouseover="display();"
						onmouseout="hide();" style="cursor:default">
				</div>
				<div id="p7" class="gpPChildrenNoColor"></div>
			</div>
			<div id="gpProgresDivS" class="gpProgressS">
				<div class="gpProgresText">
					<div class="gpPChildrenNoColor"></div>
					<div class=gpPChildrenNoColor>
						<div class="displayNone" id="tipsDiv1">
							<div class="element"></div>
							<div class="angle-wrapper" id="DivContent1"></div>
						</div>
					</div>
					<div class="gpPChildrenNoColor">
						<div class="displayNone" id="tipsDiv2">
							<div class="element"></div>
							<div class="angle-wrapper" id="DivContent2"></div>
						</div>
					</div>
					<div class="gpPChildrenNoColor">
						<div class="displayNone" id="tipsDiv3">
							<div class="element"></div>
							<div class="angle-wrapper" id="DivContent3"></div>
						</div>
					</div>
					<div class="gpPChildrenNoColor">
						<div class="displayNone" id="tipsDiv4">
							<div class="element"></div>
							<div class="angle-wrapper" id="DivContent4"></div>
						</div>
					</div>
					<div class="gpPChildrenNoColor">
						<div class="displayNone" id="tipsDiv5">
							<div class="element"></div>
							<div class="angle-wrapper" id="DivContent5"></div>
						</div>
					</div>
					<div class="gpPChildrenNoColor">
						<div class="displayNone" id="tipsDiv6">
							<div class="element"></div>
							<div class="angle-wrapper" id="DivContent6"></div>
						</div>
					</div>
					<div class="gpPChildrenNoColor"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="progressSS" class="gpProgresDivSS">
		<div class="gpProgresDivS_1">
			<a class="button bg-main" href="JavaScript:void(0)"
				onclick="openDialog();"> <span></span> 添加新进度</a>
		</div>
	</div>


<!----------------------------------------------------------------- 隐藏 ---------------------------------------------------------------------------------->
	<div id="light" class="white_content">
			<form id="form_addProgress" method="post" action="<%=path%>/stu/Student_addProgress.action">
				<div>
					<table>
						<tr>
							<td height="50" align="center" valign="middle">进度条名称：</td>
							<td height="50" align="center" valign="middle">
							<input type="text" class="inputW50" name="progress.progressName" id="progress.progressName" data-validate="required:请填写名称">
							</td>
						</tr>
						<tr>
							<td height="50" align="center" valign="middle">所属阶段：</td>
							<td height="50" align="center" valign="middle">
							<select class="inputW50" name="progress.phase" id="progress.phase">
									<option>开题报告</option>
									<option>论文</option>
									<option>毕业设计</option>
							</select>
							</td>
						</tr>
						<tr>
							<td height="50" align="center" valign="middle">开始时间：</td>
							<td height="50" align="center" valign="middle">
							<label>
							<input class="input2W50" type="text" onClick="WdatePicker();"id="progress.startTime" name="progress.startTime"> 
							</label>
							</td>
						</tr>
						<tr>
							<td height="50" align="center" valign="middle">预计完成时间：</td>
							<td height="50" align="center" valign="middle">
							<label>
							<input id="progress.estimatedTime" name="progress.estimatedTime" class="input2W50" type="text" onClick="WdatePicker();">
							</label>
							</td>
						</tr>
					</table>
					<div class="form-group">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
						<a class="button bg-main" onclick="addProgress();" style="cursor: pointer;"> <span> </span> 添加 </a>
							<a class="button bg-red" href="javascript:void(0)" onclick="closeDialog();"> <span> </span> 关闭 </a>
						</div>
						<div class="field"></div>
					</div>
				</div>
			</form>
		</div>
	<div id="updateNode" class="white_content">
	  <form id="upNode" method="post" action="">
	        <div>
					<table>
						<tr>
							<td height="50" align="center" valign="middle">进度名称：</td>
							<td height="50" align="center" valign="middle">
							<input type="text" class="inputW50" name="progress.progressName" id="up.node.progressName" data-validate="required:请填写名称">
							</td>
						</tr>
						<tr>
							<td height="50" align="center" valign="middle">日期：</td>
							<td height="50" align="center" valign="middle">
							<input type="text" class="inputW50" id="node.nodeDate" name="node.nodeDate">
							</td>
						</tr>
						<tr>
							<td height="50" align="center" valign="top">内容：</td>
							<td height="50" align="center" valign="middle"><textarea name="node.content" cols="" rows="2" class="textarea" id="node.content"></textarea></td>
						</tr>
					</table>
					<div class="form-group">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
						<a class="button bg-main" onclick="AOUNode();" style="cursor: pointer;"> <span></span> 添加 </a>
							<a class="button bg-red" href="javascript:void(0)" onclick="closeUpdateNode();"> <span> </span> 关闭 </a>
						</div>
						<div class="field"></div>
					</div>
				</div>
	  </form>
	</div>	
	<div id="fade" class="black_overlay"></div>
	<div id="modeDiv" style="display:none" mce_style="display:none">
		<div class="wrapperS">
				<div class="progressbar2">
					<div class="progressbar2_topChildren">
						进度名称：<span id="span1"></span>
					</div>
					<div class="progressbar2_topChildren">
						所属阶段：<span id="span1"></span>
					</div>
					<div class="progressbar2_topChildren">
						开始时间：<span id="span1"></span>
					</div>
					<div class="progressbar2_topChildren">
						预计完成时间：<span id="span1"></span>
					</div>
					<div class="progressbar2_topChildren">
						实际完成时间：<span id="span1"></span>
					</div>
				</div>
			<div class="progressbar3">
			<!--进度条容器-->
				<div id="progressbar" class="progressbar">
					<div style="width: 100%">
						<div id="fill" class="fill"></div>
					</div>
				</div>
				<div name="progressbarMask" class="progressbarMask"id="progressbarMask" ></div>
				<div id="bottom" class="bottom">
					<div id="bottom0" class="scale" style="visibility:hidden;">
						<label><div class="element2_1" ></div> </label>
					</div>
					<div id="bottom1" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom2" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom3" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom4" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom5" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom6" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom7" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom8" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom9" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom10" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom11" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom12" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom13" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom14" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom15" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom16" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom17" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom18" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom19" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom20" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom21" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom22" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom23" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom24" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom25" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom26" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom27" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom28" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom29" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom30" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom31" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom32" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom33" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom34" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom35" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom36" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom37" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom38" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom39" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom40" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom41" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom42" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom43" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom44" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom45" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom46" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom47" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom48" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom49" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom50" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom51" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom52" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom53" class="scale">
						<label><div id="13" class="element2_1"onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom54" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom55" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom56" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom57" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom58" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom59" class="scale">
						<label>
						<div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom60" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom61" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom62" class="scale">
						<label><div class="element2_1" onmouseover="listNode();"
								onclick="openUpdateNode();"></div> </label>
					</div>
					<div id="bottom63" class="scale" style="visibility:hidden;">
						<label><div class="element2_1"  ></div> </label>
					</div>
				</div>
			</div>
			<div style="float: left;margin-top: 40px;">
			<div>
				<a class="button border-main" href="/GraduationProject/stu/Student_finishProgres.action" onclick="allotApply();"> <span
					class="icon-edit"></span> 完成</a>
			</div>
			<div style="margin-top: 5px;">
				<a class="button border-red" href="/GraduationProject/stu/Student_deleteProgres.action" onclick="allotApply();"> <span
					class="icon-edit"></span> 删除</a>
					</div>
			</div>
		</div>	
	</div>
    <div id="nodeList" class="nodeList" onmouseleave="noneNode();" >
       <div class="element1"></div>
      <div class="nodeList1"  style="padding-top: 15px;">
      <table>
      <tr><td height="40px">日期：</td><td id="listNodeDate"></td></tr>
      <tr><td height="40px" align="center" valign="top">内容：</td><td ><textarea name="node.content" cols="" rows="2" class="list_textarea" id="listNodeContent"></textarea></td></tr>
      </table>
      </div>
    </div>
</body>
<script type="text/javascript">
/**
 *总进度显示
 */
 console.log("fuck");
	var myProgres = new Array(9);
	var proChildren = new Array();
	<c:forEach items="${studentList}" var="item" varStatus="status" >
	myProgres[0] = "";
	myProgres[1] = "${item.tutName}";
	myProgres[2] = "${item.subject}";
	</c:forEach>

	<c:forEach items="${gpFileList}" var="item2" varStatus="status" >
	var proChi;
	proChi = "${item2.reviewState}";
	proChildren.push(proChi);
	</c:forEach>
	myProgres[3] = proChildren[0];
	myProgres[5] = proChildren[1];
	myProgres[6] = proChildren[2];

	proChildren = [];
	<c:forEach items="${gpResultsList}" var="item3" varStatus="status" >
	var proChi;
	proChi = "${item3.results}";
	proChildren.push(proChi);
	</c:forEach>
	myProgres[4] = proChildren[0];
	myProgres[7] = proChildren[1];
	myProgres[8] = proChildren[2];
	
	 //获取progressList内容
	var progressList = new Array();
	var nodeList = new Array();
	<c:forEach items="${progressList}" var="pro" varStatus="status" >
	var proress=[];
	proress[0] = "${pro.progressName}";
	proress[1] = "${pro.phase}";
	proress[2] = "${pro.startTime}";
	proress[3] = "${pro.estimatedTime}";
	proress[4] = "${pro.actualTime}";
	proress[5] = "${pro.progressId}";
	proress[6] = "${pro.stuNo}";
	proress[7] = "${pro.nodeNumS}";
	progressList.push(proress);
	</c:forEach>

	//<c:forEach items="${nodeList}" var="pro1" varStatus="status" >
	<s:iterator id="item" value="nodeList" status="i">
	var proress=[];
	proress[0] = "${nodeId}";
	proress[1] = "${progressId}";
	proress[2] = "${stuNo}";
	proress[3] = "${nodeDate}";
	proress[4] = "${content}";
	proress[5] = "${nodePositionNum}";
	console.log("proress:",proress);
	nodeList.push(proress);
	</s:iterator>
	//</c:forEach>
	
	porgressTop();
	progressBottom();
</script>
</html>
