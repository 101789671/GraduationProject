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
			<strong class="icon-reorder">组别信息 </strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left:10px;">
				<form method="post" name="findanserGrouping" id="findanserGrouping" action="<%=path%>/system/answergroup_listAnserGroup.action">
					<li>查询： 
					<select class="select1" name="answergroup.replyType" id="answergroup.replyType"style="width: 100px">
					<option value="TPR">开题答辩组</option>
					<option value="GRR">毕业答辩组</option>
					</select>
					<select class="select1" name="select1" id="select1"
						onchange="selcet1to2();">
							<option>全部</option>
							<option>按专业</option>
					</select> <select class="select2" name="select2" id="select2"></select>
					</li>
				
				<li><input type="submit" name="button" value="查询"
					 class="button border-main icon-search" /></li>
					 </form>
						<form method="post"
			action="<%=path%>/system/grouping_listGrouping.action?grouping.replyType=TPR" id="listform"
			name="listform">
					 <li>
					<input type="submit" name="button" value="批量删除" onclick="batchDelGrouping();"  class="button border-red"/>
					
					</li>
				<li>搜索：</li>


				<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
					class="input"
					style="width:250px; line-height:17px;display:inline-block" /> <a
					href="javascript:void(0)" class="button border-main icon-search"
					onclick="changesearch()"> 搜索</a>
				</li>

			</ul>

		</div>

			<table class="table table-hover text-center">
				<tr>
					<td width="130px" style="text-align:left; padding-left:20px;">
						<input type="checkbox" name="ToR" id="checkall"
						onclick="allCol();">全选 <input type="checkbox" name="ToR"
						id="fanxuan" onclick="contraryCol();">反选</td>
					<th>组别类型</th>
					<th>学院</th>
					<th>专业</th>
					<th>组别</th>
					<th>组长</th>
					<th>组员1</th>
					<th>组员2</th>
					<th>组员3</th>
					<th>答辩地点</th>
					<th>操作</th>
				</tr>

				<s:iterator id="item" value="answergroupList" status="i">
					<tr>
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="SubCheck" value="${groupId}"
							onclick="SingleCol();" />${i.index+1}</td>
						<td> <s:if test="%{#item.replyType=='GRR'}">毕业答辩组</s:if>
						<s:elseif test="%{#item.replyType=='TPR'}">开题答辩组</s:elseif>
						</td>
						<td>${instituteName}</td>
						<td>${majorName}</td>
						<td>${groupName}</td>
						<td>${chargehandName}</td>
						<td>${member1name}</td>
						<td>${member2name}</td>
						<td>${member3name}</td>
						<td>${place}</td>
						<td><div class="button-group">
								<a class="button border-main"
									href="<%=path%>/system/answergroup_toUpdateAnswergroup.action?answergroup.groupId=${groupId}">
									<span class="icon-edit"></span> 修改</a> <a class="button border-red"
									href="<%=path%>/system/answergroup_deleteAnswergrou.action?answergroup.groupId=${groupId}&answergroup.replyType=${replyType}"
									onclick="javascript:return onedel();"> <spanclass="icon-trash-o">
									</span> 删除 
								</a>
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

		var InsAndClassName = new Array();
		var MajorName = new Array();

		//获得专业
		i = 0;
		<c:forEach items="${classList1}" var="item2" varStatus="status" >
		var myclass = null;
		myclass = "${item2.majorName}";
		if (myclass.length > 0) {
			MajorName[i] = myclass;
		}
		i++;
		</c:forEach>

		var JSgpResults = new Array();
		<c:forEach items="${JSanswergroupList}" var="item3" varStatus="status" >
		JSgpResults[0] = "${item3.replyType}";
		JSgpResults[1] = "${item3.majorName}";
		</c:forEach>
				
		var select1 = document.getElementById("select1");
		var select2 = document.getElementById("select2");
		var osel = document.getElementById("answergroup.replyType"); //得到select的ID 
		var opts = osel.options;//得到数组option 
		for ( var j = 0; j < opts.length; j++) {
			if (opts[j].value == JSgpResults[0]) {
			//opts[j].setAttribute("selected", ""); 也可以用这个
			opts[j].selected=true;		
			}
		}
		var s1Options = select1.options;		
		if (JSgpResults.length > 0 && JSgpResults[0] != "") {
		if (JSgpResults[1] != "") {
				AccMajor();
				s1Options[1].selected=true;
			}else {
			    AccAll();
			    s1Options[0].selected=true;
		}
		} else {
			AccAll();
			s1Options[0].selected;
		}
			
/**------------------------------------------辅助函数 -------------------------------------*/
		function selcet1to2() {
			var select1 = document.getElementById("select1");
			var select1Value = select1.value;
			 if (select1Value == "按专业") {
				AccMajor();
			}else {
				AccAll();
			}
		}

		function AccMajor() {
			var select2 = document.getElementById("select2");
			select2.setAttribute("style", "display:inline");
			select2.setAttribute("name", "answergroup.majorName")
			select2.removeAttribute('disabled');
			select2.options.length = 0;
			for ( var j = 0; j < MajorName.length; j++) {
				var addOption = new Option("" + MajorName[j] + "", ""+ MajorName[j] + "");//生成一个选项	
				if(MajorName[j]==JSgpResults[1]){
				addOption.selected = "selected";
				}
				select2.options.add(addOption);
			}
		}

		function AccAll() {	   
			var select2 = document.getElementById("select2");
			select2.setAttribute("style", "display:none");
			select2.setAttribute("disabled", "disabled");
		}
	</script>
</body>
</html>
