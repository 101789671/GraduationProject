<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--使用include命令引入公共页面 --%>
<%@include file="/comm/common.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>毕业设计管理系统</title>
<link rel="stylesheet" href="<%=path%>/css/pintuer.css">
<link rel="stylesheet" href="<%=path%>/css/admin.css">
<script src="<%=path%>/js/jquery.js"></script>
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="images/indexlogo.png" height="50" alt="" /> 毕业设计管理系统
			</h1>
		</div>
		<div class="head-l">
			<a href="" target="_blank" style="color: #FFF"><span
				class="icon-user"></span> 欢迎管理员：${user.userName }</a>&nbsp;&nbsp; <a
				class="button button-little bg-green" href="" target="_blank"><span
				class="icon-home"></span> 首页</a> &nbsp;&nbsp; <a
				class="button button-little bg-red"
				href="<%=path %>/system/Access_logout.action"><span
				class="icon-power-off"></span> 退出登录</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<h2>
			<span class="icon-list-alt"></span>公告管理
		</h2>
		<ul>
			<li><a href="<%=path %>/admin/Admin_toReleaseNotice.action"
				target="right"><span class="icon-caret-right"></span>发布公告</a></li>
			<li><a href="<%=path %>/system/Notice_listNoticeAll.action"
				target="right"><span class="icon-caret-right"></span>公告管理</a></li>
		</ul>
		
		<h2>
			<span class="icon-pencil-square-o"></span>信息录入
		</h2>
		<ul>
			<li><a href="<%=path %>/system/Student_toaddStu.action"
				target="right"><span class="icon-caret-right"></span>添加学生</a></li>
			<li><a href="<%=path %>/system/Tutor_toAddTutor.action"
				target="right"><span class="icon-caret-right"></span>添加导师</a></li>
			<li><a href="<%=path %>/system/Class_toaddclass.action"
				target="right"><span class="icon-caret-right"></span>添加班级</a></li>
		</ul>
		<h2>
			<span class="icon-search"></span>信息查询
		</h2>
		<ul>
			<li><a href="<%=path %>/system/Student_findStuAll.action"
				target="right"><span class="icon-caret-right"></span>学生信息</a> <a
				href="<%=path %>/system/Tutor_findTuAll.action" target="right"><span
					class="icon-caret-right"></span>导师信息</a> <a
				href="<%=path %>/system/Class_findclassAll.action" target="right"><span
					class="icon-caret-right"></span>班级信息</a>
		</ul>

		<h2>
			<span class="icon-group"></span>选导信息
		</h2>
		<ul>
			<li><a href="<%=path %>/system/Student_toChoiceTut.action"
				target="right"><span class="icon-caret-right"></span>选导概况</a> <a
				href="<%=path %>/system/Apply_toAllotApply.action" target="right"><span
					class="icon-caret-right"></span>分配申请</a>
		</ul>
		<h2>
			<span class="icon-file"></span>学生毕业设计
		</h2>
		<ul>
			<li><a href="<%=path%>/admin/Admin_toStudentGPDatumSurvey.action?gpFile.fileCatNum=TP"
				target="right"><span class="icon-caret-right"></span>开题报告</a></li>
			<li><a href="<%=path%>/admin/Admin_toStudentGPDatumSurvey.action?gpFile.fileCatNum=PA"
				target="right"><span class="icon-caret-right"></span>论文</a></li>
			<li><a href="<%=path%>/admin/Admin_toStudentGPDatumSurvey.action?gpFile.fileCatNum=WO"
				target="right"><span class="icon-caret-right"></span>作品</a></li>
		</ul>
		<h2>
			<span class="icon-sitemap"></span>答辩分组
		</h2>
		<ul>
			<li><a href="<%=path%>/admin/Admin_toAddGrouping.action"
				target="right"><span class="icon-caret-right"></span>答辩分组</a></li>
			<li><a href="<%=path%>/system/grouping_listGrouping.action?grouping.replyType=TPR"
				target="right"><span class="icon-caret-right"></span>分组信息</a></li>
				<li><a href="<%=path%>/system/answergroup_listAnserGroup.action"
				target="right"><span class="icon-caret-right"></span>组别信息</a></li>
		</ul>
				<h2>
			<span class="icon-check"></span>成绩管理
		</h2>
		<ul>
			<li><a href="<%=path%>/admin/Admin_toResultsAdd.action"
				target="right"><span class="icon-caret-right"></span>成绩录入</a></li>
				<li><a href="<%=path%>/admin/Admin_listGpResults.action"
				target="right"><span class="icon-caret-right"></span>成绩管理</a></li>
		</ul>
		
		

		<h2>
			<span class="icon-cogs"></span>系统设置
		</h2>
		<ul>
			<li><a href="<%=path %>/admin/Admin_toSystemSwitch.action"
				target="right"><span class="icon-caret-right"></span>系统开关</a></li>
		</ul>
		<h2>
			<span class="icon-cog"></span>个人设置
		</h2>
		<ul>
			<li><a href="<%=path%>/system/Access_toUpdatePwd.action"
				target="right"><span class="icon-caret-right"></span>密码修改</a>
			</li>
		</ul>

	</div>
	<script type="text/javascript">
	$(function() {
		$(".leftnav h2").click(function() {
			$(this).next().slideToggle(200);
			$(this).toggleClass("on");
		})
		$(".leftnav ul li a").click(function() {
			$("#a_leader_txt").text($(this).text());
			$(".leftnav ul li a").removeClass("on");
			$(this).addClass("on");
		})
	});
</script>
	<ul class="bread">
		<li><a href="{:U('Index/info')}" target="right" class="icon-home">
				首页</a></li>
		<li><a href="##" id="a_leader_txt">欢迎界面</a></li>
		<li><b>当前语言：</b><span style="color: red;">中文</php> </span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言： <a href="##">中文</a> &nbsp;&nbsp;
			<a href="##">英文</a></li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" rameborder="0" src="welcome.jsp"
			name="right" width="100%" height="100%"></iframe>
	</div>
	<div style="text-align: center;">
		<p>
			来源: <a href="http://www.mycodes.net/" target="_blank">源码之家</a>
		</p>
	</div>
</body>
</html>
