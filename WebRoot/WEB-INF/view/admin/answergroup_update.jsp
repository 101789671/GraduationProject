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
<link rel="stylesheet" href="<%=path%>/css/admin.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>答辩组别</strong>
		</div>
		<div class="body-content">
		<s:iterator id="item" value="answergroupList" status="i">
			<form method="post" class="form-x" id="myform"
				action="<%=path%>/system/answergroup_updateAnswergroup.action?answergroup.groupId=${groupId}">
				
					<div class="form-group">
						<div class="label">
							<label>组别类型：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.replyType"
								value="<s:if test="%{#item.replyType=='GRR'}">毕业答辩组</s:if>
						<s:elseif test="%{#item.replyType=='TPR'}">开题答辩组</s:elseif>" name="answergroup.replyType"
								readOnly="true" />
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>学院：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.instituteName"
								value="${instituteName}" name="answergroup.instituteName" readOnly="true" />
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>专业：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.majorName"
								value="${majorName}" name="answergroup.majorName" readOnly="true" />
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>组别：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.groupName"
								value="${groupName}" name="answergroup.groupName" readOnly="true" />
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>组长：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.chargehandId"
								value="${chargehandId}" name="answergroup.chargehandId" placeholder="请输入组长ID" />
								<input type="text" class="input w50" id="answergroup.chargehandName"
								value="${chargehandName}" name="answergroup.chargehandName" placeholder="请输入 组长姓名"/>
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>组员1：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.member1id"
								value="${member1id}" name="answergroup.member1id" placeholder="请输入组员ID"/>
								<input type="text" class="input w50" id="answergroup.member1name"
								value="${member1name}" name="answergroup.member1name"placeholder="请输入组员姓名" />
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>组员2：</label>
						</div>
						<div class="field">
						<input type="text" class="input w50" id="answergroup.member2id"
								value="${member2id}" name="answergroup.member2id"  placeholder="请输入 组员ID"/>
								<input type="text" class="input w50" id="answergroup.member2name"
								value="${member2name}" name="answergroup.member2name" placeholder="请输入组员姓名"/>
								<div class="tips"></div>
						</div>
					</div>

					<div class="form-group">
						<div class="label">
							<label>组员3：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.member3id"
								value="${member3id}" name="answergroup.member3id"  placeholder="请输入组员ID"/>
								<input type="text" class="input w50" id="answergroup.member3name"
								value="${member3name}" name="answergroup.member3name"placeholder="请输入组员姓名" />
								<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label>答辩地点：</label>
						</div>
						<div class="field">
							<input type="text" class="input w50" id="answergroup.place"
								value="${place}" name="answergroup.place" placeholder="请输入答辩地点" />
								<div class="tips"></div>
						</div>
					</div>
				</s:iterator>



				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit"">
							提交</button>
					</div>
				</div>
		</div>
		</form>
	</div>
	</div>




</body>
<script type="text/javascript">
	var message = "${requestScope.message}";
	if (message != "") {
		alert(message);
	}

	//数值大小排序函数
	function sortNumber(a, b) {
		return a - b
	}

	var gando = new Array();
	<c:forEach items="${groupingList1}" var="item2" varStatus="status" >
	var myclass = new Array();
	myclass[0] = "${item2.groupingsNo}";
	myclass[1] = "${item2.orders}";
	gando.push(myclass);
	</c:forEach>

	var thisGrouping = new Array();
	<c:forEach items="${groupingList}" var="item2" varStatus="status" >
	var myclass = new Array();
	thisGrouping[0] = "${item2.groupingsNo}";
	thisGrouping[1] = "${item2.orders}";
	</c:forEach>
	console.log("gando:", gando);

	//组号去重
	var groupingNo = new Array();
	for ( var i = 0; i < gando.length; i++) {
		var Tof = true;
		F1: for ( var j = 0; j < groupingNo.length; j++) {
			if (gando[i][0] == groupingNo[j]) {
				Tof = false;
				break F1;
			}
		}
		if (Tof) {
			groupingNo.push(gando[i][0]);
		}
	}
	groupingNo.sort();
	
	var groupingsNo = document.getElementById("grouping.groupingsNo");
	for ( var i = 0; i < groupingNo.length; i++) {
		var addOption = new Option("" + groupingNo[i] + "", "" + groupingNo[i]+ "");//生成一个选项
		if(thisGrouping[0]==groupingNo[i]){
		addOption.selected = "selected";
		}
		groupingsNo.options.add(addOption);
	}
	groupingsNoOnchange();
	function groupingsNoOnchange() {
	    var groupingsNo1 = document.getElementById("grouping.groupingsNo");
		var groupingsNoValue = groupingsNo1.value;
		var orders = new Array();
		orders.push(0);
		for ( var i = 0; i < gando.length; i++) {
			if (groupingsNoValue == gando[i][0]) {
				orders.push(gando[i][1]);
			}
		}
		orders.sort(sortNumber);
		//获取答辩组内空余的序号
		var orders2 = new Array();
		console.log("orders:",orders);
		for(var i=0,j=1;j<orders.length;i++,j++){
		  if(orders[j]-orders[i]!=1){
		    var a= orders[j]-orders[i];
		    console.log("a:",a);
		    for(var k=0,z=i+1;k<a-1;k++){
		    if(i!=0){
		      z++;
		      }
		      console.log("z:",z);
		      orders2.push(z);
		    }
		  }
		}
		console.log("orders2:",orders2);

	//如果无空余，则添加一个为答辩组最大顺序+1的序号
		if (orders2.length == 0) {
			orders2.push(Math.max.apply(null, orders) + 1)
		}
		var ordersS = document.getElementById("grouping.orders");
		ordersS.options.length = 0;
		if(thisGrouping[0]==groupingsNoValue){
		var addOption = new Option("" + thisGrouping[1] + "  (当前)", "" + thisGrouping[1]+ "");//生成一个选项
			ordersS.options.add(addOption);
		}
		for ( var i = 0; i < orders2.length; i++) {
			var addOption = new Option("" + orders2[i] + "", "" + orders2[i]+ "");//生成一个选项
			ordersS.options.add(addOption);
		}

	}
</script>
</html>
