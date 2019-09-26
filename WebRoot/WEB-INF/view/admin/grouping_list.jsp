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
			<strong class="icon-reorder">分组信息 </strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left:10px;">
				<form method="post" name="findanserGrouping" id="findanserGrouping" action="<%=path%>/system/grouping_listGrouping.action?">
					<li>查询： 
					<select class="select1" name="grouping.replyType" id="grouping.replyType">
					<option value="TPR">开题答辩</option>
					<option value="GRR">毕业答辩</option>
					</select>
					<select class="select1" name="select1" id="select1"
						onchange="selcet1to2();">
							<option>全部</option>
							<option>按班级</option>
							<option>按专业</option>
							<option>按学号</option>
					</select> <select class="select2" name="select2" id="select2"
						onchange="select2to3();">

					</select> <select class="select2" name="select3" id="select3">

					</select>
					<input type="text" name="grouping.stuNo" id="grouping.stuNo"
						class="select2" />
					</li>
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
					<th>答辩类型</th>
					<th>学号</th>
					<th>姓名</th>
					<th>班级</th>
					<th>导师</th>
					<th>专业</th>
					<th>答辩分组</th>
					<th>组内答辩顺序</th>
					<th>操作</th>
				</tr>

				<s:iterator id="item" value="groupingList" status="i">
					<tr>
						<td style="text-align:left; padding-left:20px;"><input
							type="checkbox" name="SubCheck" value="${groupNo}"
							onclick="SingleCol();" />${i.index+1}</td>
						<td>${replyTypeName}</td>
						<td>${stuNo}</td>
						<td>${stuName}</td>
						<td>${className}</td>
						<td>${tutName}</td>
						<td>${majorName}</td>
						<td>${groupingsNo}</td>
						<td>${orders}</td>
						<td><div class="button-group">
								<a class="button border-main"
									href="<%=path%>/system/grouping_toUpdateGrouping.action?grouping.groupNo=${groupNo}">
									<span class="icon-edit"></span> 修改</a> <a class="button border-red"
									href="<%=path%>/system/grouping_deleteGrouping.action?grouping.groupNo=${groupNo}&grouping.replyType=TPR"
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

		var i = 0;
		//获得学院和班级
		<c:forEach items="${classList}" var="item2" varStatus="status" >
		var myclass = new Array();
		myclass[0] = "${item2.instituteName}";
		myclass[1] = "${item2.classtuName}";
		if (myclass.length > 0) {
			InsAndClassName[i] = myclass;
		}
		i++;
		</c:forEach>

		//学院去重
		var disIns = new Array();
		//console.log("InsAndClassName:", InsAndClassName);
		for ( var i = 0; i < InsAndClassName.length; i++) {
			var Tof = true;
			F1: for ( var j = 0; j < disIns.length; j++) {
				if (InsAndClassName[i][0] == disIns[j]) {
					Tof = false;
					break F1;
				}
			}
			if (Tof) {
				disIns.push(InsAndClassName[i][0]);
			}
		}
		//console.log("disIns:", disIns);

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
		<c:forEach items="${JSgroupingList}" var="item3" varStatus="status" >
		JSgpResults[0] = "${item3.replyType}";
		JSgpResults[1] = "${item3.className}";
		JSgpResults[2] = "${item3.majorName}";
		JSgpResults[3] = "${item3.stuNo}";
		</c:forEach>
		console.log("JSgpResults:", JSgpResults);			
		var select1 = document.getElementById("select1");
		var select2 = document.getElementById("select2");
		var select3 = document.getElementById("select3");
		var input1 = document.getElementById("grouping.stuNo");
		var osel = document.getElementById("grouping.replyType"); //得到select的ID 
		var opts = osel.options;//得到数组option 
		for ( var j = 0; j < opts.length; j++) {
			if (opts[j].value == JSgpResults[0]) {
			//opts[j].setAttribute("selected", ""); 也可以用这个
			opts[j].selected=true;		
			}
		}
		var s1Options = select1.options;		
		if (JSgpResults.length > 0 && JSgpResults[0] != "") {
			if (JSgpResults[1] != "" && JSgpResults[2] == ""&& JSgpResults[3] == "") {
				AccClass();
				s1Options[1].selected=true;
			}else if (JSgpResults[1] == "" && JSgpResults[2] != ""&& JSgpResults[3] == "") {
				AccMajor();
				s1Options[2].selected=true;
			}else if (JSgpResults[1] == "" && JSgpResults[2] == ""&& JSgpResults[3] != "") {
				AccStuNo();
				s1Options[3].selected=true;
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
			if (select1Value == "按班级") {
				AccClass();
			} else if (select1Value == "按专业") {
				AccMajor();
			} else if (select1Value == "按学号") {
				AccStuNo();
			} else {
				AccAll();
			}
		}

		function select2to3() {

			var select2Value = select2.value;
			select3.options.length = 0;
			for ( var j = 0; j < InsAndClassName.length; j++) {
				if (InsAndClassName[j][0] == select2Value) {
					var addOption = new Option("" + InsAndClassName[j][1] + "","" + InsAndClassName[j][1] + "");//生成一个选项	
					select3.options.add(addOption);
				}
			}
		}


		function AccClass() {
			var select2 = document.getElementById("select2");
			var select3 = document.getElementById("select3");
			var input1 = document.getElementById("grouping.stuNo");
			select2.setAttribute("style", "display:inline");
			select2.setAttribute("name", "select2");
			select2.removeAttribute('disabled');
			select3.setAttribute("style", "display:inline");
			select3.setAttribute("name", "grouping.className")
			select3.removeAttribute('disabled');
			select2.options.length = 0;
			select3.options.length = 0;
			input1.setAttribute("style", "display:none");
			input1.setAttribute("disabled", "disabled");
			//获得查询班级所在学院
			var ins;
			for ( var j = 0; j < InsAndClassName.length; j++) {
				if (JSgpResults[1] == InsAndClassName[j][1]) {
					ins = InsAndClassName[j][0];
				}
			}
			//添加学院并设置选择
			for ( var j = 0; j < disIns.length; j++) {
				var addOption = new Option("" + disIns[j] + "", "" + disIns[j]+ "");//生成一个选项
				if (disIns[j] == ins) {
					addOption.selected = "selected";
				}
				select2.options.add(addOption);
			}
			//添加班级，并设置选中
			for ( var j = 0; j < InsAndClassName.length; j++) {
				if (InsAndClassName[j][0] == ins) {
					var addOption = new Option("" + InsAndClassName[j][1] + "","" + InsAndClassName[j][1] + "");//生成一个选项	
					if (InsAndClassName[j][1] == JSgpResults[1]) {
						addOption.selected = "selected";
					}
					select3.options.add(addOption);
				} else if (InsAndClassName[j][0] == disIns[0]) {
					var addOption = new Option("" + InsAndClassName[j][1] + "","" + InsAndClassName[j][1] + "");//生成一个选项	
					select3.options.add(addOption);
				}

			}

		}

		function AccMajor() {
			var select2 = document.getElementById("select2");
			var select3 = document.getElementById("select3");
			var input1 = document.getElementById("grouping.stuNo");
			select2.setAttribute("style", "display:inline");
			select2.setAttribute("name", "grouping.majorName")
			select2.removeAttribute('disabled');
			select3.setAttribute("style", "display:none");
			select3.setAttribute("disabled", "disabled");
			input1.setAttribute("style", "display:none");
			input1.setAttribute("disabled", "disabled");
			select2.options.length = 0;
			for ( var j = 0; j < MajorName.length; j++) {
				var addOption = new Option("" + MajorName[j] + "", ""+ MajorName[j] + "");//生成一个选项	
				if(MajorName[j]==JSgpResults[2]){
				addOption.selected = "selected";
				}
				select2.options.add(addOption);
			}
		}

		function AccStuNo() {
			var select2 = document.getElementById("select2");
			var select3 = document.getElementById("select3");
			var input1 = document.getElementById("grouping.stuNo");
			input1.setAttribute("style", "display:inline");
			input1.removeAttribute('disabled');
			if(JSgpResults[3]!=""&&JSgpResults[3]!=null){
			 input1.value=JSgpResults[3];
			}
			select2.setAttribute("style", "display:none");
			select2.setAttribute("disabled", "disabled");
			select3.setAttribute("style", "display:none");
			select3.setAttribute("disabled", "disabled");
		}
		function AccAll() {
			   
			var select2 = document.getElementById("select2");
			var select3 = document.getElementById("select3");
			var input1 = document.getElementById("grouping.stuNo");
			select2.setAttribute("style", "display:none");
			select2.setAttribute("disabled", "disabled");
			select3.setAttribute("style", "display:none");
			select3.setAttribute("disabled", "disabled");
			input1.setAttribute("style", "display:none");
			input1.setAttribute("disabled", "disabled");

		}
	</script>
</body>
</html>
