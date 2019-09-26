<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html> 
    <head> 
    
        <meta charset="utf-8">
<link rel="stylesheet" href="<%=path%>/css/pintuer.css">
<link rel="stylesheet" href="<%=path%>/css/admin.css">
<link rel="stylesheet" href="<%=path%>/css/custom.css">
<script src="<%=path%>/js/jquery.js"></script>
<script src="<%=path%>/js/pintuer.js"></script>
<script src="<%=path%>/js/custom.js"></script>
<script src="<%=path%>/js/WdatePicker.js"></script>
        <title>点击文字弹出一个DIV层窗口代码</title> 
        <style> 
        .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
           background-color:  #999;
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 38%;
	width: 400px;
	height: 450px;
	padding: 20px;
	border: 5px double #87AADE;
	background-color: white;
	text-align: center;
	border-radius: 0.4em;
	z-index: 1002;
	overflow: auto;
        } 
.inputW50{
width: 250px;
height: 40px;
border-radius: 0.3em;
border: 1px double #999;
}
.inputW50:focus {
   border-style:solid;  
    border-color: #03a9f4;  
    box-shadow: 0 0 15px #E4E4E4; 
   
}
.input2W50{
width: 250px;
height: 40px;
border-radius: 0.3em;
border: 1px double #999;
background:#fff url(images/datePicker.gif) no-repeat right;
}
.input2W50:focus {
   border-style:solid;  
    border-color: #03a9f4;  
    box-shadow: 0 0 15px ##E4E4E4; 
   
}
        </style> 
    </head> 
    <body> 
        <p>示例弹出层：<a href = "JavaScript:void(0)" onclick = "openDialog()">请点这里</a></p> 
        
        <div id="light" class="white_content">
           <form action="">
	<div>
	<table>
	<tr><td height="50" align="center" valign="middle">进度条名称：</td><td height="50" align="center" valign="middle"><input type="text" class="inputW50"></td></tr>
	<tr><td height="50" align="center" valign="middle">开始时间：</td><td height="50" align="center" valign="middle"><label><input class="input2W50" type="text" onClick="WdatePicker();" id="progress.startTime" name="progress.startTime"></label></td></tr>
	<tr><td height="50" align="center" valign="middle">预计完成时间：</td><td height="50" align="center" valign="middle"><label><input id="progress.estimatedTime" name="progress.estimatedTime" class="input2W50" type="text" onClick="WdatePicker();"></label></td></tr>
	</table>
	<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main " type="submit"">
							添加</button>
							<a class="button bg-red"
									href="javascript:void(0)"
									onclick="closeDialog();"> <span >
									</span> 关闭 
								</a>
					</div>
					<div class="field">
						
					</div>
				</div>
				
	
	</div>
	</form>
        </div> 
        <div id="fade" class="black_overlay"></div> 
    </body> 
    <script type="text/javascript">
        $(function(){
        })
        function openDialog(){
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block'
        }
        function closeDialog(){
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none'
        }
    </script>
</html>
