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
<link rel="stylesheet" href="<%=path%>/css/switch.css">
<link rel="stylesheet" href="<%=path%>/css/switch.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>系统开关</strong>
		</div>
		<div class="subject_switchS">
			<div class="subject_Text2">
				系统总开关：<label><input class="mui-switch mui-switch-animbg"
					type="checkbox" name="sySwitch"
					<c:forEach items="${sySwitchList}" var="switch"><c:if test="${switch.switchState == 'T'}">checked</c:if> </c:forEach>
					id="tsSwitch" onclick="sySwitch();">
				</label>
			</div>
		</div>
		<div class="subject_switchS">
			<div class="subject_Text">
				开题报告上传开关：<label><input class="mui-switch mui-switch-animbg"
					type="checkbox" name="tpSwitch"
					<c:forEach items="${tpSwitchList}" var="switch"><c:if test="${switch.switchState == 'T'}">checked</c:if> </c:forEach>
					id="tsSwitch" onclick="tpSwitch();">
				</label>
			</div>
		</div>
		<div class="subject_switchS">
			<div class="subject_Text">
				论文上传开关：<label><input class="mui-switch mui-switch-animbg"
					type="checkbox" name="paSwitch"
					<c:forEach items="${paSwitchList}" var="switch"><c:if test="${switch.switchState == 'T'}">checked</c:if> </c:forEach>
					id="tsSwitch" onclick="paSwitch();">
				</label>
			</div>
		</div>
		<div class="subject_switchS">
			<div class="subject_Text">
				毕业设计上传开关：<label><input class="mui-switch mui-switch-animbg"
					type="checkbox" name="woSwitch"
					<c:forEach items="${woSwitchList}" var="switch"><c:if test="${switch.switchState == 'T'}">checked</c:if> </c:forEach>
					id="tsSwitch" onclick="woSwitch();">
				</label>
			</div>
		</div>
		
	</div>
</body>
<script type="text/javascript">

	
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}


</script>
</html>
