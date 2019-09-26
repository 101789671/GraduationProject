function getForm(){
	var calssNo = document.getElementsByName("classForm.classNo");
	var calssName = document.getElementsByName("classForm.classtuName");
	var List_classNo = new Array();
	var List_className = new Array();
	
	for(var i =0;i<calssNo.length-1;i++){
	List_classNo[i]=calssNo[i].value;
	console.log("List_classNo["+i+"]:",List_classNo[i]);}
	
	for(var i =0;i<calssName.length-1;i++){
	List_className[i]=calssName[i].value;
	console.log("List_className["+i+"]:",List_className[i]);}
	
	var lists = new Array();
	var list = new Array();
	for(var i=0;i<calssNo.length-1;i++){
	  var list = new Array();
	  list[0]=List_classNo[i];
	  list[1]=List_className[i];
	  lists[i]=list;
	}
	
	console.log("lists.length:",lists.length);
	console.log("lists:",lists);
	for(var i=0;i<calssNo.length-1;i++){
	  console.log("lists["+i+"]:",lists[i]);
	}
	
	var json = JSON.stringify(lists);
	console.log("json:",json);
	var data= [{name:"jon",age:"12"},{name:"jic",age:"18"},{name:"petter",age:"14"}]
	$.ajax({
                type: "post",//put delete get post
                url:'<%=path%>/test/add_addclass.action',
                dataType:'json',
                async:true,//默认异步
                data:{ds:JSON.stringify(data)},
                success: function(data,textStatus){
                    alert(data);
                    alert("操作成功!");
                },
                error: function(xhr,status,errMsg){
                    alert("操作失败!");
                }
                });

}