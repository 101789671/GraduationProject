/** ----------------------------------------------------------- admin-class ------------------------------------------------------------------------- */
/**
 *添加行
 *方式一
 *此方法可以在事件源所在行后插入新的行
 */
function addclassTR1() {
	var r = document.getElementById("tablemode");//获得模板表
	//找到当前事件源所在的表格 table 对象，并将这个对象赋给t
	var otab = _p(event.srcElement, "tbody");//
	//console.log("otab:",otab);//控制台打印
	var t = _p(event.srcElement, "tr");//获得事件源所在行----event.srcElement：获取当前事件源或者对象
	var intr = r.rows[1];//获得模板行
	var newtr = document.createElement("tr"); //创建一个tr元素。
	newtr.innerHTML = intr.innerHTML;
	newtr.id="trmod2";
	otab.insertBefore(newtr,t.nextSibling);
}

/**
 *添加行
 *方式二
 *此方法只能在最后行后插入新行
 */
function addclassTR2() {
	var r = document.getElementById("trmod");//根据ID获取对象
	var r0 = r;
	//找到当前事件源所在的表格 table 对象，并将这个对象赋给t
	var t = _p(event.srcElement, "table");//event.srcElement：获取当前事件源或者对象
	//在这个表格对象中的指定位置插入一行
	var r1 = t.insertRow(t.rows.length);//insertRow() 方法用于在表格中的指定位置插入一个新行; .rows 返回数目
	r1.setAttribute("id", 'trmod2');
	//循环插入列
	for ( var i = 0, c = r0.cells; i < c.length; i++) { //cells 集合返回表格中所有单元格的一个数组
		var x = r1.insertCell(); //insertCell() 方法用于在 HTML 表的一行的指定位置插入一个空的 <td> 元素
		x.setAttribute("valign", 'middle');
		x.setAttribute("class", 'claTd');
		x.innerHTML = c[i].innerHTML; //innerHTML 设置或返回表格行的开始和结束标签之间的 HTML。
	}
//更改前面按钮的值和js方法名
	var trNuber = document.getElementById('tables').rows;	//获得行
	for ( var i = 1; i < trNuber.length - 1; i++) {
		var td = trNuber[i].cells;	//获得单元格

			var input0 = td[3].getElementsByTagName("input")[0];
			/*
			//删除+按钮
			if(td[3].getElementsByTagName("input").length > 1){
			td[3].removeChild(input0);
		}*/
	
	}
}
function _p(obj, tagName) {
	tagName = tagName.toUpperCase();//toUpperCase() 方法用于把所有字符串转换为大写。
	while (obj.tagName != tagName)
		//.tagName 返回元素的标签名.在 HTML 中，tagName 属性的返回值始终是大写的。
		obj = obj.parentElement;// parentElement 获取对象层次中的父对象
	return obj;//返回一个对象
}

/**
 *删除行
 */	
function removeTr(){
	//var event = event.srcElement;//获得事件源对象
	var parentTb =_p(event.srcElement,"tbody");//获得事件源所在的 tbody 对象
	//console.log("parentTab:",parentTb);//控制台打印
	var parentTr = _p(event.srcElement,"tr");//获得事件源所在的 tr 对象
	//console.log("parentTr:",parentTr);
	//判断要删除的是否是最后一行
	var tb_tr =parentTb.rows;//获取所有行
	if(tb_tr.length>2){
	parentTb.removeChild(parentTr);
	}
}

/**
 * 添加DIV
 * 方法一
 * 此方法可以在事件源所在区域后插入新的区域
 */
function addDIV(){
	var evDivSs = event.srcElement;
	for (var i=0;i<7;i++){
		evDivSs = evDivSs.parentElement;
	}
	var evDivs = event.srcElement;
	for (var i=0;i<6;i++){
		evDivs = evDivs.parentElement;
	}
	var Divsmode= document.getElementById("Divsmode");//获得模板Div
	var oDiv3 = document.createElement("div"); //创建一个div元素。
	//console.log("Divsmode.innerHTML:",Divsmode.innerHTML);
	oDiv3.innerHTML= Divsmode.innerHTML;
	oDiv3.id = "Divs";
	oDiv3.setAttribute("class", 'Divs');
	//console.log("oDiv3:",oDiv3);
	evDivSs.insertBefore(oDiv3,evDivs.nextSibling);
}

/**
*添加DIV
*方法二
*此方法只能在第一个区域后插入新区域
*已更改Divs位置 此方法不在适用
*/
function addDIV2() {
var oDivs = document.getElementById("myform");//获取"myform"对象
var oDiv = document.getElementById("Divs");
var newDiv = oDivs.childNodes[1];//获取"myform"对象“Divs”对象
var oDiv3 = document.createElement("div"); //创建一个div元素。
oDiv3.innerHTML = newDiv.innerHTML;
oDiv3.id = "div3";
oDiv3.setAttribute("class", 'Divs');
//console.log("newDiv:",newDiv);
//console.log("oDiv3:",oDiv3);
//将oDiv3插入在oDivs下的第一个子标签后
oDivs.insertBefore(oDiv3,newDiv.nextSibling);//.childNodes获取所选节点（标签）下的所有子节点（标签），并返回一个集合
}

/**
 * 删除DIV区域
 */
function reremoveDiv(){
	var evDivSs = event.srcElement;
	for (var i=0;i<7;i++){
		evDivSs = evDivSs.parentElement;
	}
	var evDivs = event.srcElement;
	for (var i=0;i<6;i++){
		evDivs = evDivs.parentElement;
	}
	if(evDivSs.childNodes.length>5){
	 evDivSs.removeChild(evDivs);
	}
}

function addclassSubmit(){
	var calssNo = document.getElementsByName("classForm.classNo");
	var calssName = document.getElementsByName("classForm.classtuName");
	var List_classNo = new Array();
	var List_className = new Array();
	
	for(var i =0;i<calssNo.length-1;i++){
	List_classNo[i]=calssNo[i].value;
	console.log("List_classNo.length:",List_classNo.length);}
	
	for(var i =0;i<calssName.length-1;i++){
	List_className[i]=calssName[i].value;
	console.log("List_className.length:",List_classNames.length);}
}
function addclassSubmit() {
	var calssNo = document.getElementsByName("classForm.classNo");
	var calssName = document.getElementsByName("classForm.classtuName");
	var classInsname = document.getElementsByName("classForm.instituteName");
	
	var Ero0 = "no", Ero1 = "no", Ero2 = "no";
	for ( var i = 0; i < classInsname.length - 1; i++) {
		if (classInsname[i].value == "") {
			Ero0 = "yes";
		}
	}
	for ( var i = 0; i < calssNo.length - 1; i++) {
		if (calssNo[i].value == "") {
			Ero1 = "yes";
		}
	}
	for ( var i = 0; i < calssName.length - 1; i++) {
		if (calssName[i].value == "") {
			Ero2 = "yes";
		}
	}
	if (Ero0 == "yes") {
		alert("存在未填写的 所属学院 ！");
		
	} else if (Ero1 == "yes" && Ero2 == "yes") {
		alert("存在未填写的 班级编号 和 班级名称！");	
	} else if (Ero1 == "yes") {
		alert("存在未填写的 班级编号 ！");	
	} else if (Ero2 == "yes") {
		alert("存在未填写的 班级名称 ！");
	}else {
		document.getElementById("addclassform").submit();
	}
}

//批量删除
function batchDelCal(){
	uploadData("SubCheck","Class_delclassBatch.action","确定删除记录？",true,true);
        }
/** ---------------------------------------------------------Grouping--------------------------------------------------------------------- */
//批量删除
function batchDelGrouping(){
	uploadData("SubCheck","grouping_batchDelGrouping.action","确定删除记录？",true,true);
        }
/** ---------------------------------------------------------admin-student--------------------------------------------------------------------- */
/**
 * 更改下拉框
 */
function updateOption() {
	//获取选中的学院的值
	var obj = _p(event.srcElement,"select");//获取学院 下拉框
	console.log("obj:",obj);
	var obj2 = _p(event.srcElement,"td").childNodes[3];//获取班级下拉框
	console.log("obj2:",obj2);
	var index = obj.selectedIndex; //序号，取当前选中选项的序号
	var val = obj.options[index].value;//获取值
	

	obj2.options.length = 0;//删除所有选项
	var addOption = new Option("请选择班级", "");//生成一个选项
	obj2.options.add(addOption);
	for (i = 0; i < classLi.length; i++) {
		if (val == classLi[i][0]) {
			var addOption = new Option("" + classLi[i][1] + "", ""+ classLi[i][1] + "");//生成一个选项	
			obj2.options.add(addOption); //这个兼容IE与firefox
		}
	}
}
/**
 * 更改下拉框
 * student_updae.jsp
 */
function updateOption3() {
	//获取选中的学院的值
	var obj = _p(event.srcElement, "select");//获取学院 下拉框
	//console.log("obj:",obj);
	var obj2 = _p(event.srcElement, "div").childNodes[3];//获取班级下拉框
	//console.log("obj2:",obj2);
	var index = obj.selectedIndex; //序号，取当前选中选项的序号
	var val = obj.options[index].value;//获取值

	obj2.options.length = 0;//删除所有选项
	var addOption = new Option("", "");//生成一个选项
	obj2.options.add(addOption);
	console.log("InsTutS:",InsTutS);//控制台打印
	console.log("val:",val);//控制台打印
	for (i = 0; i < InsTutS.length; i++) {
		if (val == InsTutS[i][0]) {
			var addOption = new Option("" + InsTutS[i][1] + "", ""
					+ InsTutS[i][1] + "");//生成一个选项	
			obj2.options.add(addOption); //这个兼容IE与firefox
		}
	}
}
/**
 * 更改下拉框
 * student_updae.jsp
 */
function updateOption2() {
	//获取选中的学院的值
	var obj = _p(event.srcElement, "select");//获取学院 下拉框
	//console.log("obj:",obj);
	var obj2 = _p(event.srcElement, "div").childNodes[3];//获取班级下拉框
	//console.log("obj2:",obj2);
	var index = obj.selectedIndex; //序号，取当前选中选项的序号
	var val = obj.options[index].value;//获取值

	obj2.options.length = 0;//删除所有选项
	var addOption = new Option("请选择班级", "");//生成一个选项
	obj2.options.add(addOption);
	for (i = 0; i < classLi.length; i++) {
		if (val == classLi[i][0]) {
			var addOption = new Option("" + classLi[i][1] + "", ""
					+ classLi[i][1] + "");//生成一个选项	
			obj2.options.add(addOption); //这个兼容IE与firefox
		}
	}
}

/**
 *添加行
 *studen_add.jsp专用
 */
 function addstuTr(){
	 addTr("tables");	 	 
 }

 /**
  * 批量删除
  * student_list.jsp
  */
 function batchDelStu(){
	 uploadData("SubCheck","Student_batchDelStu.action","确定删除所选记录？",true,true);
 }

 function addStuSubmit() {
		var stuNo = document.getElementsByName("studentForm.stuNo");
		var stuName = document.getElementsByName("studentForm.stuName");
		var sex = document.getElementsByName("studentForm.sex");
		var instituteName = document.getElementsByName("classForm.instituteName");
		var classtuName = document.getElementsByName("classForm.classtuName");

		var Ero0 = "no", Ero1 = "no", Ero2 = "no", Ero3 = "no", Ero4 = "no";
		
		for ( var i = 0; i < stuNo.length; i++) {
			if (stuNo[i].value == "") {
				
				Ero0 = "yes";
			}
		}
		
		for ( var i = 0; i < stuName.length; i++) {
			if (stuName[i].value == "") {
				Ero1 = "yes";
			}
		}
		
		for ( var i = 0; i < sex.length; i++) {
			if (sex[i].value == "") {
				Ero2 = "yes";
			}
		}
		
		for ( var i = 0; i < instituteName.length; i++) {
			if (instituteName[i].value == "请选择学院"||instituteName[i].value=="") {
				Ero3 = "yes";
			}
		}
		
		for ( var i = 0; i < classtuName.length; i++) {
			if (classtuName[i].value == "请选择班级"||classtuName[i].value=="") {
				Ero4 = "yes";
			}
		}


		
		if (Ero0 == "yes") {
			alert("存在未填写的 学生学号！");		
		} else if (Ero1 == "yes") {
			alert("存在未填写的 学生姓名！");	
		} else if (Ero2 == "yes") {
			alert("存在未填写的 性别 ！");	
		} else if (Ero3 == "yes") {
			alert("存在未选择的 学院 ！");
		}else if (Ero4 == "yes") {
			alert("存在未选择的 班级 ！");
		}else {
			document.getElementById("addstuform").submit();
		}
	}
 
/** ------------------------------------------------------------------admin-Tutor------------------------------------------------------------------------ */
 function batchDelTu(){
	 uploadData("SubCheck","Tutor_delTuBatch.action","确定删除所选记录？",true,true);
 }
 
 function uodataTutorSubmit() {

		var classtuName = document.getElementsByName("tutorForm.instituteName");

		Ero4 = "no";

		for ( var i = 0; i < classtuName.length; i++) {
			if (classtuName[i].value == "请选择学院"||classtuName[i].value=="") {
				Ero4 = "yes";
			}
		}
 if (Ero4 == "yes") {
			alert("请选择的 学院 ！");
		}else {
			document.getElementById("addTutorform").submit();
		}
	}
 
 function addTutorSubmit() {
		var stuNo = document.getElementsByName("tutorForm.tutNo");
		var stuName = document.getElementsByName("tutorForm.tutName");
		var sex = document.getElementsByName("tutorForm.sex");
		var instituteName = document.getElementsByName("tutorForm.position");
		var classtuName = document.getElementsByName("tutorForm.instituteName");

		var Ero0 = "no", Ero1 = "no", Ero2 = "no", Ero3 = "no", Ero4 = "no";
		
		for ( var i = 0; i < stuNo.length; i++) {
			if (stuNo[i].value == "") {
				
				Ero0 = "yes";
			}
		}
		
		for ( var i = 0; i < stuName.length; i++) {
			if (stuName[i].value == "") {
				Ero1 = "yes";
			}
		}
		
		for ( var i = 0; i < sex.length; i++) {
			if (sex[i].value == "") {
				Ero2 = "yes";
			}
		}
		
		for ( var i = 0; i < instituteName.length; i++) {
			if (instituteName[i].value=="") {
				Ero3 = "yes";
			}
		}
		
		for ( var i = 0; i < classtuName.length; i++) {
			if (classtuName[i].value == "请选择学院"||classtuName[i].value=="") {
				Ero4 = "yes";
			}
		}


		
		if (Ero0 == "yes") {
			alert("存在未填写的 教师编号！");		
		} else if (Ero1 == "yes") {
			alert("存在未填写的 姓名！");	
		} else if (Ero2 == "yes") {
			alert("存在未填写的 性别 ！");	
		} else if (Ero3 == "yes") {
			alert("存在未填写的 职称 ！");
		}else if (Ero4 == "yes") {
			alert("存在未选择的 学院 ！");
		}else {
			document.getElementById("addTutorform").submit();
		}
	}
 
 function batchAllot(){
	 uploadData("SubCheck", "Apply_batchRaAllot.action", "确定进行批量自动分配？",true,true);
 }
 
 
 /** ----------------------------------------------------------admin-Switch----------------------------------------------------------------------------------- */
 function sySwitch(){
	 var mySwitch = document.getElementsByName("sySwitch");
	 if(mySwitch[0].checked){
		 twoUploadData("Admin_updateSwitch.action", "SYS","T", true);
	 }else{
		 twoUploadData("Admin_updateSwitch.action", "SYS","F", true);
	 }
 }
 function tpSwitch(){
	 var mySwitch = document.getElementsByName("tpSwitch");
	 if(mySwitch[0].checked){
		 twoUploadData("Admin_updateSwitch.action", "TPS","T", false);
	 }else{
		 twoUploadData("Admin_updateSwitch.action", "TPS","F", false);
	 }
 }
 function paSwitch(){
	 var mySwitch = document.getElementsByName("paSwitch");
	 if(mySwitch[0].checked){
		 twoUploadData("Admin_updateSwitch.action", "PAS","T", false);
	 }else{
		 twoUploadData("Admin_updateSwitch.action", "PAS","F", false);
	 }
 }
 function woSwitch(){
	 var mySwitch = document.getElementsByName("woSwitch");
	 if(mySwitch[0].checked){
		 twoUploadData("Admin_updateSwitch.action","WOS","T", false);
	 }else{
		 twoUploadData("Admin_updateSwitch.action", "WOS","F", false);
	 }
 }
/** -----------------------------------------------------------------Admin_gpResults--------------------------------------------------------------------- */

 function batchDelGpResults(){
	 uploadData("SubCheck","../system/gpResults_batchDelGpResults.action","确定删除所选记录？",true,false);
 }
 
/** -----------------------------------------------------------------Student_Chioce---------------------------------------------------------------------- */
 function Sonedel() {
		if (!confirm("确认要撤销此申请吗？")) {
			window.event.returnValue = false;
		}
	}
 function allotApply() {
		if (!confirm("确定要进行自动分配吗？")) {
			window.event.returnValue = false;
		}
	}
 
 function SRemindChioceSub(){
	 remind("确定选择？\n\n选择该题目后，将提交申请至导师，请耐心等待。");
 }
 
 function batchDelApplyCS(){
	 uploadData("SubCheck", "Student_batchDelApply.action", "确定要撤销所选申请吗？",true, false);
	 var form = document.getElementById('listform');
	// console.log("form:",form);
	 form.submit();
 }
/** --------------------------------------------------------------- Student_myProgres ------------------------------------------------------------------- */
/**
 * 显示Div
 */
	function display() {
		var t = event.srcElement.parentElement;
		var id = t.id;
		var tipsDiv;
		if (id == "p1") {
			tipsDiv = document.getElementById("tipsDiv1");
		} else if (id == "p2") {
			tipsDiv = document.getElementById("tipsDiv2");
		} else if (id == "p3") {
			tipsDiv = document.getElementById("tipsDiv3");
		} else if (id == "p4") {
			tipsDiv = document.getElementById("tipsDiv4");
		} else if (id == "p5") {
			tipsDiv = document.getElementById("tipsDiv5");
		} else if (id == "p6") {
			tipsDiv = document.getElementById("tipsDiv6");
		}
		if (tipsDiv != null) {
			tipsDiv.setAttribute("class", "displayBlock");
		}
	}
/**
 * 隐藏Div
 */
	function hide() {
		var t = event.srcElement.parentElement;
		t.setAttribute("disabled", "disabled");
		var id = t.id;
		var tipsDiv;
		if (id == "p1") {
			tipsDiv = document.getElementById("tipsDiv1");
		} else if (id == "p2") {
			tipsDiv = document.getElementById("tipsDiv2");
		} else if (id == "p3") {
			tipsDiv = document.getElementById("tipsDiv3");
		} else if (id == "p4") {
			tipsDiv = document.getElementById("tipsDiv4");
		} else if (id == "p5") {
			tipsDiv = document.getElementById("tipsDiv5");
		} else if (id == "p6") {
			tipsDiv = document.getElementById("tipsDiv6");
		}
		if (tipsDiv != null) {
			tipsDiv.setAttribute("class", "displayNone");
		}
	}

/**
 * 验证日期
 */
	function submitProgress(){
		var startTime = document.getElementById("progress.startTime").value;
		var estimatedTime = document.getElementById("progress.estimatedTime").value;
		//日期格式转换
		startTime = new Date(startTime.replace(/-/g, "/"));
		estimatedTime = new Date(estimatedTime.replace(/-/g, "/"));
		//计算时间差 （天）
		var dateDiff = estimatedTime.getTime() - startTime.getTime();//时间差的毫秒数
	    var dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000));//计算出相差天数
	    if(dayDiff>7){
	    	if(dayDiff<=62){
	    		
	    	}else{
		    	alert("进度条总时间不可大于2个月!");
				window.event.returnValue = false;
		    }
	    }else{
	    	alert("进度条总时间应在7天以上！");
			window.event.returnValue = false;
	    }
	}
	
function porgressTop(){
	var p0 = document.getElementById("p0");
	var p1 = document.getElementById("p1");
	var p2 = document.getElementById("p2");
	var p3 = document.getElementById("p3");
	var p4 = document.getElementById("p4");
	var p5 = document.getElementById("p5");
	var p6 = document.getElementById("p6");
	var p7 = document.getElementById("p7");

	var DivContent1 = document.getElementById("DivContent1");
	var DivContent2 = document.getElementById("DivContent2");
	var DivContent3 = document.getElementById("DivContent3");
	var DivContent4 = document.getElementById("DivContent4");
	var DivContent5 = document.getElementById("DivContent5");
	var DivContent6 = document.getElementById("DivContent6");

	DivContent1.innerHTML = "选导：未完成";
	DivContent2.innerHTML = "选题：未完成";
	DivContent3.innerHTML = "开题报告：导师未同意或未完成";
	DivContent4.innerHTML = "开题答辩：未通过或未答辩";
	DivContent5.innerHTML = "论文：导师未同意或不存在</br>作品：导师未同意或不存在";
	DivContent6.innerHTML = "论文：未通过</br>作品：未通过";
	if (myProgres[1] != "" && myProgres[1] != null) {
		p0.setAttribute("class", "gpPFristChildrenLeft");
		DivContent1.innerHTML = "选导：已完成";

		if (myProgres[2] != "" && myProgres[2] != null) {
			p1.setAttribute("class", "gpPChildrenHaveColor");
			DivContent2.innerHTML = "选题：已完成";

			if (myProgres[3] != "不同意" && myProgres[3] != null) {
				p2.setAttribute("class", "gpPChildrenHaveColor");

				DivContent3.innerHTML = "开题报告：导师已同意";

				if (myProgres[4] != "不通过" && myProgres[4] != ""
						&& myProgres[4] != null) {
					p3.setAttribute("class", "gpPChildrenHaveColor");

					DivContent4.innerHTML = "开题答辩：已通过";

					var Tof1;

					if ((myProgres[5] != "不同意" && myProgres[5] != null && myProgres[5] != "")
							&& (myProgres[6] != "不同意" && myProgres[6] != "" && myProgres[6] != null)) {
						DivContent5.innerHTML = "论文：导师已同意</br>作品：导师已同意";
						Tof1 = true;

					} else if ((myProgres[5] != "不同意" && myProgres[5] != null && myProgres[5] != "")
							&& (myProgres[6] == "不同意" || myProgres[6] == "" || myProgres[6] == null)) {
						DivContent5.innerHTML = "论文：导师已同意</br>作品：导师未同意或不存在";
						Tof1 = true;

					} else if ((myProgres[5] == "不同意" || myProgres[5] == null || myProgres[5] == "")
							&& (myProgres[6] != "不同意" && myProgres[6] != "" && myProgres[6] != null)) {
						DivContent5.innerHTML = "论文：导师未同意或不存在</br>作品：导师已同意";
						Tof1 = true;

					}
					if (Tof1 == true) {
						p4.setAttribute("class", "gpPChildrenHaveColor");
						var Tof2 = false;
						if ((myProgres[7] != "不合格" && myProgres[7] != "不通过"
								&& myProgres[7] != "" && myProgres[7] != null)
								&& (myProgres[8] != "不合格"
										&& myProgres[8] != "不通过"
										&& myProgres[8] != "" && myProgres[8] != null)) {
							DivContent6.innerHTML = "论文：通过</br>作品：通过";
							Tof2 = true;
						} else if ((myProgres[7] != "不合格"
								&& myProgres[7] != "不通过" && myProgres[7] != "" && myProgres[7] != null)
								&& (myProgres[8] == "不合格"
										|| myProgres[8] == "不通过"
										|| myProgres[8] == "" || myProgres[8] == null)) {
							DivContent6.innerHTML = "论文：通过</br>作品：未通过";
						}
						if ((myProgres[7] == "不合格" || myProgres[7] == "不通过"
								|| myProgres[7] == "" || myProgres[7] == null)
								&& (myProgres[8] != "不合格"
										&& myProgres[8] != "不通过"
										&& myProgres[8] != "" && myProgres[8] != null)) {
							DivContent6.innerHTML = "论文：未通过</br>作品：通过";
						}
						if (Tof2 == true) {
							p5.setAttribute("class", "gpPChildrenHaveColor");
							p6.setAttribute("class", "gpPChildrenHaveColor");
							p7.setAttribute("class", "gpPChildrenRight");

						} else {
							p5.setAttribute("class", "gpPChildrenRight");
						}
					} else {
						p4.setAttribute("class", "gpPChildrenRight");
					}
				} else {
					p3.setAttribute("class", "gpPChildrenRight");
				}
			} else {
				p2.setAttribute("class", "gpPChildrenRight");
			}
		} else {
			p1.setAttribute("class", "gpPChildrenRight");
		}
	}
}
/**
 *子进度显示
 */
function progressBottom(){
	//获得进度条插入位置
	var progressSS = document.getElementById("progressSS");
	var progressSSchildren = progressSS.children;
	//获得进度模板
	var modeDiv1 = document.getElementById("modeDiv");
	var modeDiv = modeDiv1.children[0];
	var percentDayList = [];
	var a= progressSSchildren.length;
	for(var i=0;i<progressList.length;i++){
	   //写入上层模块的内容 (文本层)
	   //获得上层子节点集合
	   var modeDiv2 = document.createElement("div"); //创建一个tr元素。
	   modeDiv2.innerHTML = modeDiv.innerHTML;
	   modeDiv2.setAttribute("class","wrapperS");
	   var TopText = modeDiv2.children[0].children;
	   for(var j=0;j<5;j++){
	   if(progressList[i][j]!=""){
	          TopText[j].children[0].innerHTML=""+progressList[i][j]+"";
	     }else {
			  TopText[j].children[0].innerHTML="-- -- --";
		}
	   }
	   
	  //设置下层模块(进度层)
	   //计算容器长度
	   var long = 22+(progressList[i][7]*20);
	   //获得进度层子节点集合
	   var lowerChildren = modeDiv2.children[1].children;
	   
	   //获得进度条-容器层
	   var MiddleContainer = lowerChildren[0];
	   MiddleContainer.setAttribute("style","width:"+long+"px;"); 
	   //获得已用时间
	   var nowTime = new Date().getTime();
	   var startTime = progressList[i][2].replace(/-/g, "/");
	   startTime =  Date.parse(startTime);
	   var diffDays = nowTime-startTime;
	   var effluxDays = Math.floor(diffDays/ (24 * 3600 * 1000));
	   //计算时间百分百 
	   var percentDay = (effluxDays/progressList[i][7]).toFixed(3);
	   percentDay = (percentDay*100).toFixed(1);
	   //获得内嵌样式区块
	   var thisStyle = document.getElementById("thisStyle");
	   thisStyle.innerHTML=thisStyle.innerHTML+"@keyframes move"+i+" { 0%{width: 0;}100%{width:"+percentDay+"%;}}";
	   //获得进度条
	   var progressBar = MiddleContainer.children[0];
	   progressBar.setAttribute("style","width: "+percentDay+"%;animation: move"+i+" 2s;"); 
	   
	   //获得进度条-遮罩层
	   var MiddleMask = lowerChildren[1];
	   MiddleMask.setAttribute("style","width:"+long+"px;");
	   
	   //获得进度条-刻度层
	   var MiddleScale = lowerChildren[2];
	   //获得所有刻度
	   var allScale = MiddleScale.children;
	   //设置节点值
	   for(var j=1;j<=parseInt(progressList[i][7]);j++){
	       var thisScaleChildren = allScale[j].children;
	       var value;
	       if(j<10){
	        value= progressList[i][5]+0+j;
	       }else {
			value= progressList[i][5]+j;
		}
		thisScaleChildren[0].children[0].setAttribute("value",""+value+"");
	   }
	   //设置剩余节点隐藏
	   for(var j=parseInt(progressList[i][7])+1; j<63;j++){
	       allScale[j].setAttribute("style","visibility:hidden;");
	   } 
	   percentDayList.push(percentDay);
	   //添加进度条	
	  progressSS.insertBefore(modeDiv2,progressSSchildren[a]);   	
	   	a++;
	}
	
	
	/*并行*/
	//根据名称获取同名元素
	var progressbarMaskName = document.getElementsByName("progressbarMask");
	var imgs = [].slice.apply(progressbarMaskName);
    imgs.splice(progressbarMaskName.length-1,1);
	percentDayList.push(0.1);//用于阻止模板的progressbarMask线程继续运行
	progressbarMaskName.forEach(function Pro(progressbarMaskName,number) {
		var progressbar = {
			init : function() {
				//var progressbarMask = document.getElementById("progressbarMask");
				var count = -0.1;
				//通过间隔定时器实现百分比文字效果,通过计算CSS动画持续时间进行间隔设置
				var timer = setInterval(function(e) {
					count = count + 0.1;
					//progressbarMaskName.innerHTML = count.toFixed(1) + '%';
					if (count.toFixed(1) == percentDayList[number])
						clearInterval(timer);
				}, 4);
			}
		};
		progressbar.init();
	});
	
}	
/**
 *添加进度 的弹窗
 */
	function openDialog() {
		document.getElementById('light').style.display = 'block';
		document.getElementById('fade').style.display = 'block';
	}
	function closeDialog() {
		document.getElementById('light').style.display = 'none';
		document.getElementById('fade').style.display = 'none';
	}
	
/**
 *节点
 */
	function listNode() {
		var eventDiv = event.srcElement;
		var scaleValue = event.srcElement.getAttribute("value");
		var listDiv = document.getElementById("nodeList");
		var listNodeDate = document.getElementById("listNodeDate");
		var listNodeContent = document.getElementById("listNodeContent");
		console.log("eventDiv:",eventDiv);
		var Tof=false;
		for(var i=0;i<nodeList.length;i++){
		  if(scaleValue==nodeList[i][0]){
		    listNodeDate.innerHTML=nodeList[i][3];
		    listNodeContent.innerHTML=nodeList[i][4];
		    Tof=true;
		  }
		}
		if(Tof==false){
		listNodeDate.innerHTML="--";
		    listNodeContent.innerHTML="--";
		}
		var objDiv = $("#nodeList");
		var X = eventDiv.getBoundingClientRect().left;
		var Y = eventDiv.getBoundingClientRect().top;
		$(objDiv).css("display", "block");
		$(objDiv).css("left", X-157 );
		$(objDiv).css("top", Y+12);

	}

	function noneNode() {
			var listNodeDate = document.getElementById("listNodeDate");
		var listNodeContent = document.getElementById("listNodeContent");
		listNodeDate.innerHTML="";
		    listNodeContent.innerHTML="";
		var listDiv = document.getElementById("nodeList");
		listDiv.style.display = "none";
	}

	function openUpdateNode() {
		var scaleValue = event.srcElement.getAttribute("value");
		var upform = document.getElementById("upNode");
		upform.setAttribute("action","/GraduationProject/stu/Student_addOrUpdate.action?node.nodeId="+scaleValue+"");
		//截取scaleValue获得progresId和节点位置
		var progresId = scaleValue.substring(0, scaleValue.length - 2);
		var num = scaleValue.substring(scaleValue.length - 2, scaleValue.length);
		num = parseInt(num) - 1;
		//向弹窗写入进度名和日期 
		for ( var j = 0; j < progressList.length; j++) {
			if (progresId == progressList[j][5]) {
				startTime = progressList[j][2];
				//写入进度名
				var up_progressName_Input = document.getElementById("up.node.progressName");
				up_progressName_Input.value = "" + progressList[j][0] + "";
				up_progressName_Input.setAttribute("readOnly","true");
				var startTime;
				startTime = startTime.replace(/-/g, "/");
				startTime = new Date(startTime);
				//获得该节点日期
				var thisScaleDate = startTime;
				thisScaleDate.setDate(thisScaleDate.getDate() + num);
				var M = thisScaleDate.getMonth() + 1;//getMonth:0=1月 1=2月...所以加1变成当前月 以便输出
				thisScaleDate = thisScaleDate.getFullYear() + "-" + M + "-"+ thisScaleDate.getDate();//字符串类型的thisScaleDate
				//写入日期
				var up_nodeDate_input = document.getElementById("node.nodeDate");
				up_nodeDate_input.value = "" + thisScaleDate + "";
				up_nodeDate_input.setAttribute("readOnly","true");
			}
		}
		//向弹窗添加内容
		for ( var j = 0; j < nodeList.length; j++) {
			if (scaleValue == nodeList[j][0]) {
			   var up_content_input = document.getElementById("node.content");
			   up_content_input.value=""+nodeList[j][4]+"";
			}
		}
		document.getElementById('updateNode').style.display = 'block';
		document.getElementById('fade').style.display = 'block';
	}
	
	function closeUpdateNode() {
		document.getElementById('updateNode').style.display = 'none';
		document.getElementById('fade').style.display = 'none';
	}
	function AOUNode() {
	var upform = document.getElementById("upNode");
	console.log("upform:",upform);
	upform.submit();
	}

	function addProgress() {
		//获取表单参数
		var progressName = document.getElementById("progress.progressName").value;
		if (progressName != "") {
			var phase = document.getElementById("progress.phase").value;
			var startTime = document.getElementById("progress.startTime").value;
			var estimatedTime = document
					.getElementById("progress.estimatedTime").value;
			//替换字符
			startTime = startTime.replace(/-/g, "/");
			estimatedTime = estimatedTime.replace(/-/g, "/");
			//转换成Time类型
			startTime = Date.parse(startTime);
			estimatedTime = Date.parse(estimatedTime);
			//计算时间差（天）
			var timeDiffMS = estimatedTime - startTime;
			var timeDiffD = Math.floor(timeDiffMS / (24 * 3600 * 1000));
			if (timeDiffD >= 7) {
				if (timeDiffD <= 62) {
					var form = document.getElementById("form_addProgress");
					form.submit();
				} else {
					alert("进度总天数应小于或等于62天！");
				}
			} else {
				alert("进度总天数应大于或等于7天！");
			}
		} else {
			alert("请填写进度条名称！");
		}
	}	
 
/** ---------------------------------------------------------------tutor-------------------------------------------------------------------- */
/**
 * 批量删除题目
 */
 function batchDelSub(){
	 uploadData("SubCheck","Subject_batchDelSubject.action","确定删除所选记录？",true,true);
 }

/**
 * 提交更权限复选框
 */
 function subSwitch(){
	 var mySwitch = document.getElementsByName("tsSwitch");
	 if(mySwitch[0].checked){
		 pureUploadData("TsControl_updateTsControl.action", "T", false);
	 }else{
		 pureUploadData("TsControl_updateTsControl.action", "F", false);
	 }
 }
 
 
 function addSubjectTR1() {
 	var r = document.getElementById("tablemode");//获得模板表
 	//找到当前事件源所在的表格 table 对象，并将这个对象赋给t
 	var otab = _p(event.srcElement, "tbody");//
 	console.log("otab:",otab);//控制台打印
 	
 	var t = _p(event.srcElement, "tr");//获得事件源所在行----event.srcElement：获取当前事件源或者对象
 	console.log("t:",t);
 	
 	var intr = r.rows[0];//获得模板行
 	console.log("r:",r);
 	
 	var newtr = document.createElement("tr"); //创建一个tr元素。
 	console.log("newtr:",newtr);
 	
 	newtr.innerHTML = intr.innerHTML;
 	console.log("newtr:",newtr);
 	
 	newtr.id="trmod2";
 	otab.insertBefore(newtr,t.nextSibling);
 }
 
 /**
  *删除行
  */	
 function removeSubjectTr(){
 	//var event = event.srcElement;//获得事件源对象
 	var parentTb =_p(event.srcElement,"tbody");//获得事件源所在的 tbody 对象
 	//console.log("parentTab:",parentTb);//控制台打印
 	var parentTr = _p(event.srcElement,"tr");//获得事件源所在的 tr 对象
 	//console.log("parentTr:",parentTr);
 	//判断要删除的是否是最后一行
 	var tb_tr =parentTb.rows;//获取所有行
 	if(tb_tr.length>=2){
 	parentTb.removeChild(parentTr);
 	}
 }

 function TRemindRemoveStu(){
	 remind("确定要移除该学生吗？");
 }
 function ThaveCol(){
	 var c1 = document.getElementsByName("ToR");
	 var select = document.getElementById("subject.select");
	 var input = document.getElementById("subject.input");
	 if(c1[0].checked){

	 c1[1].checked = false;
	   input.setAttribute("style", "display:none");
	   input.setAttribute("disabled","disabled");
	   select.setAttribute("style", "display:inline");
	   select.removeAttribute('disabled');
	 }
	 else{
		 c1[1].checked = true; 
		  input.setAttribute("style", "display:inline");
		  input.removeAttribute('disabled');
		  select.setAttribute("style", "display:none"); 
		  select.setAttribute("disabled","disabled");
 }}
 function TManualCol(){
	   var c1 = document.getElementsByName("ToR");
	   var select = document.getElementById("subject.select");
	   var input = document.getElementById("subject.input");
	   if(c1[1].checked){
	   c1[0].checked = false;
	  input.setAttribute("style", "display:inline");
	  input.removeAttribute('disabled');
	  select.setAttribute("style", "display:none");
	  select.setAttribute("disabled","disabled");
	  }else {
		   c1[0].checked = true;
		   input.setAttribute("style", "display:none");
		   input.setAttribute("disabled","disabled");
		   select.setAttribute("style", "display:inline");
		   select.removeAttribute('disabled');
	}
 }
 /**
  *全选
  **/	
 	function TallCol() {
 		var c1 = document.getElementsByName("ToR");//getElementsByName() 方法可返回带有指定名称的对象的集合
 		var c2 = document.getElementsByName("SubCheck");
 		var c3 = document.getElementsByName("gpFile.fileNo");
 		if (c1[0].checked) {
 			for ( var i = 0; i < c2.length; i++) {
 				c2[i].checked = true;
 				c3[i].setAttribute("checked",'checked');
 				c3[i].removeAttribute('disabled');
 			}
 		} else {
 			for ( var i = 0; i < c2.length; i++) {
 				c2[i].checked = false;
 				c3[i].removeAttribute("checked","checked");
 				c3[i].setAttribute("disabled","disabled");
 			}
 		}
 		//取消“反选”的勾选
 		c1[1].checked = false;
 	}
 	
 /**
  *反选
  **/
  function TcontraryCol(){
        var c1 = document.getElementsByName("ToR");
 		var c2 = document.getElementsByName("SubCheck");
 		var c3 = document.getElementsByName("gpFile.fileNo");
 		for(var i=0;i<c2.length;i++){
 		 if(c2[i].checked){
 		  c2[i].checked = false;
		  c3[i].removeAttribute("checked","checked");
		  c3[i].setAttribute("disabled","disabled");
 		 }
 		 else{
 		 c2[i].checked = true;
		 c3[i].setAttribute("checked",'checked');
		 c3[i].removeAttribute('disabled');
 		 }
 		}
 		//取消“全选”的勾选
 		c1[0].checked = false;
  }

 function TSingleCol(){
	 var c1 = document.getElementsByName("ToR");
	 var td =_p(event.srcElement,"td");//获得事件源所在的 td 对象
	 var children = td.childNodes;//获取子节点
	 //取消“全选”“反选”的勾选
	 c1[0].checked = false;c1[1].checked = false;
	 if(children[0].checked){
		 children[2].setAttribute("checked",'checked');
		 children[2].removeAttribute('disabled');
		 
	 }else{
		 children[2].removeAttribute("checked","checked");
		 children[2].setAttribute("disabled","disabled");
	 }
	 }
/** ------------------------------------------------------------------- tutor_gpResult ----------------------------------------------------------------- */
function tutorGpResultSelect() {
	//学院去重
	var disIns = new Array();
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
			
	var select1 = document.getElementById("select1");
	var select2 = document.getElementById("select2");
	var select3 = document.getElementById("select3");
	var input1 = document.getElementById("gpResults.stuNo");

	var osel = document.getElementById("gpResults.reType"); //得到select的ID 
	var opts = osel.options;//得到数组option 
	for ( var j = 0; j < opts.length; j++) {
		if (opts[j].value == JSgpResults[0]) {
		//opts[j].setAttribute("selected", ""); 也可以用这个
		opts[j].selected=true;
			console.log("opts[j]:", opts[j]);
			
		}
	}
	var s1Options = select1.options;		
	if (JSgpResults.length > 0 && JSgpResults[0] != "") {
		if (JSgpResults[1] != "" && JSgpResults[2] == ""&& JSgpResults[3] == "") {
			AccClass();
			s1Options[1].selected=true;
			console.log("s1Options[1]:", s1Options[1]);
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
}
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
	var input1 = document.getElementById("gpResults.stuNo");
	select2.setAttribute("style", "display:inline");
	select2.setAttribute("name", "select2");
	select2.removeAttribute('disabled');
	select3.setAttribute("style", "display:inline");
	select3.setAttribute("name", "gpResults.className")
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
	var input1 = document.getElementById("gpResults.stuNo");
	select2.setAttribute("style", "display:inline");
	select2.setAttribute("name", "gpResults.majorName")
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
	var input1 = document.getElementById("gpResults.stuNo");
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
	var input1 = document.getElementById("gpResults.stuNo");
	select2.setAttribute("style", "display:none");
	select2.setAttribute("disabled", "disabled");
	select3.setAttribute("style", "display:none");
	select3.setAttribute("disabled", "disabled");
	input1.setAttribute("style", "display:none");
	input1.setAttribute("disabled", "disabled");

}
 
/** ----------------------------------------------------------------------公共--------------------------------------------------------------------------- */
/**
 * 确认删除框
 * 单条
 */
//单条删除
function onedel() {
	remind("确认要删除该记录？");
}
//弹窗提醒
function remind(reContent){
	if (!confirm(reContent)) {
		window.event.returnValue = false;
	}
}

/**
 *全选
 **/	
	function allCol() {
		var c1 = document.getElementsByName("ToR");//getElementsByName() 方法可返回带有指定名称的对象的集合
		var c2 = document.getElementsByName("SubCheck");
		if (c1[0].checked) {
			for ( var i = 0; i < c2.length; i++) {
				//方法一
				c2[i].checked = true;
				//方法二
				//var checkElement=c2[i];
				//	checkElement.checked="checked";
			}
		} else {
			for ( var i = 0; i < c2.length; i++) {
				//方法一
				c2[i].checked = false;
				//方法二
				//var checkElement=c2[i];
				//	checkElement.checked=null;
			}
		}
		//取消“反选”的勾选
		c1[1].checked = false;
	}
	
/**
 *反选
 **/
 function contraryCol(){
       var c1 = document.getElementsByName("ToR");
		var c2 = document.getElementsByName("SubCheck");
		for(var i=0;i<c2.length;i++){
		 if(c2[i].checked){
		  c2[i].checked = false;
		  
		 }
		 else{
		 c2[i].checked = true;
		 }
		}
		//取消“全选”的勾选
		c1[0].checked = false;
 }

 /**
 *单选
 **/
 function SingleCol(){
 var c1 = document.getElementsByName("ToR");
 //取消“全选”“反选”的勾选
 c1[0].checked = false;c1[1].checked = false;
 }
 
/**
 * 公告
 */
 function addNotice(){
	 var form = document.getElementById("addNoticeForm");
	 var title = document.getElementById("notice.noticeTitle").value;
	 var content = document.getElementById("notice.noticeContent").value;
	 if(title!=""){
		 if(content!=""){
			 form.submit();
		 }else{
			 alert("内容不可为空！");
		 }	 
	 }else{
		 alert("标题不可为空！");
	 }
 }
 
/**
 * 添加行
 */
 function addTr(modTableName) {
		var r = document.getElementById(""+modTableName+"");//获得模板表
		//找到当前事件源所在的表格 table 对象，并将这个对象赋给t
		var otab = _p(event.srcElement, "tbody");//
		//console.log("otab:",otab);//控制台打印
		var t = _p(event.srcElement, "tr");//获得事件源所在行----event.srcElement：获取当前事件源或者对象
		var intr = r.rows[1];//获得模板行
		var newtr = document.createElement("tr"); //创建一个tr元素。
		newtr.innerHTML = intr.innerHTML;
		newtr.id="trmod2";
		otab.insertBefore(newtr,t.nextSibling);
	}
/**
 * 数据提交
 */
 function uploadData(inputName, actionURL, hintContent, OFswitch) {
	// 判断至少写了一项
	var checkedNum = $("input[name='" + inputName + "']:checked").length;// actionURL:xxx.action
	console.log("actionURL:", actionURL);// 控制台打印
	if (checkedNum == 0) {
		alert("请至少选择一项!");
		window.event.returnValue = false;
	} else if (hintContent != null) {
		if (!confirm(hintContent)) {
			window.event.returnValue = false;
		} else {
			uploadData_children();
		}
	} else {
		uploadData_children();
	}
 function uploadData_children(){
	 var checkedList = new Array();
		$("input[name='" + inputName + "']:checked").each(function() {
			checkedList.push($(this).val());//获取选中项的值
		});
		$.ajax({
			type : "post",
			url : "" + actionURL + "",
			data : {
				"delitems" : checkedList.toString()
			},
			datatype : "html",
			async : false,
			success : function(data) {
				//$("[name='checkbox2']:checkbox").attr("checked",false);
				if (OFswitch == true) {
					location.reload();//页面刷新
				}
				console.log("1111111111111111111111:");//控制台打印
			},
			error : function(data) {
				if (OFswitch == true) {
					location.reload();
				}

			}
		});
	}
 }
 
/**
 * 单纯的数据提交
 */
 function pureUploadData(actionURL, DataContent, OFswitch){
		$.ajax({
			type : "post",
			url : "" + actionURL + "",
			data : {
				"delitems" : DataContent.toString()
			},
			datatype : "html",
			async : false,
			success : function(data) {
				//$("[name='checkbox2']:checkbox").attr("checked",false);
				if (OFswitch == true) {
					location.reload();//页面刷新
				}
			},
			error : function(data) {
				if (OFswitch == true) {
					location.reload();
				}

			}
		});
 }
 /**
  * 双数据提交
  */
 function twoUploadData(actionURL, DataContent1,DataContent2, OFswitch){
	 var DataContentList = new Array();
	 DataContentList.push(DataContent1);
	 DataContentList.push(DataContent2);
		$.ajax({
			type : "post",
			url : "" + actionURL + "",
			data : {
				"delitems" : DataContentList.toString()
			},
			datatype : "html",
			async : false,
			success : function(data) {
				//$("[name='checkbox2']:checkbox").attr("checked",false);
				if (OFswitch == true) {
					location.reload();//页面刷新
				}
			},
			error : function(data) {
				if (OFswitch == true) {
					location.reload();
				}

			}
		});
}
 
 
 /**
  *批量下载
  */
  function batchDownload(){
 	 uploadData("SubCheck", "/GraduationProject/download/download_downloadFile.action",null, false);
  }