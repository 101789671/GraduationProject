<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--使用include命令引入公共页面 --%>
<%@include file="/comm/common.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
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
				class="icon-user"></span> 欢迎 导师：${user.userName }</a>&nbsp;&nbsp; <a
				class="button button-little bg-green" href="" target="_blank"><span
				class="icon-home"></span> 首页</a> &nbsp;&nbsp; <a
				class="button button-little bg-red"
				href="<%=path%>/system/Access_logout.action"><span
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
			<li><a href="<%=path %>/tut/Tutor_toReleaseNotice.action"
				target="right"><span class="icon-caret-right"></span>发布公告</a></li>
			<li><a href="<%=path %>/system/Notice_listNoticeAll.action"
				target="right"><span class="icon-caret-right"></span>公告管理</a></li>
		</ul>
		<h2>
			<span class="icon-group"></span>学生管理
		</h2>
		<ul>
			<li><a href="<%=path%>/tut/Tutor_findMystu.action"
				target="right"><span class="icon-caret-right"></span>我的学生</a>
			</li>
			<li><a href="<%=path%>/tut/Tutor_examineCT.action"
				target="right"><span class="icon-caret-right"></span>选导申请</a>
			</li>

		</ul>
		<h2>
			<span class="icon-list-ul "></span>题目管理
		</h2>
		<ul>
			<li><a href="<%=path%>/tut/Tutor_toAddSubject.action"
				target="right"><span class="icon-caret-right"></span>出题</a></li>
			<li><a href="<%=path%>/sub/Subject_subjectList.action"
				target="right"><span class="icon-caret-right"></span>题目管理</a></li>
			<li><a href="<%=path%>/tut/Tutor_examineCS.action"
				target="right"><span class="icon-caret-right"></span>选题审核</a></li>

		</ul>

		<h2>
			<span class="icon-search"></span>学生毕设
		</h2>
		<ul>
			<li><a href="<%=path%>/tut/Tutor_toStudentGPDatumSurvey.action?gpFile.fileCatNum=TP&seat=1"
				target="right"><span class="icon-caret-right"></span>开题报告</a></li>
			<li><a href="<%=path%>/tut/Tutor_toStudentGPDatumSurvey.action?gpFile.fileCatNum=PA&seat=1"
				target="right"><span class="icon-caret-right"></span>论文</a></li>
			<li><a href="<%=path%>/tut/Tutor_toStudentGPDatumSurvey.action?gpFile.fileCatNum=WO&seat=1"
				target="right"><span class="icon-caret-right"></span>作品</a></li>
		</ul>
		
		<h2>
			<span class="icon-check"></span>学生成绩
		</h2>
		<ul>
			<li><a href="<%=path%>/tut/Tutor_myStudentGpResults.action"
				target="right"><span class="icon-caret-right"></span>学生成绩查询</a></li>
		</ul>
		<h2>
				<span class="icon-tasks"></span>学生进度
			</h2>
			<ul >
				<li>
					<a href="<%=path %>/tut/Tutor_toStudentPregress.action" target="right"><span
						class="icon-caret-right"></span>学生进度</a>
				</li>	
			</ul>
		<h2>
			<span class="icon-sitemap"></span>答辩组信息
		</h2>
		<ul>
			<li><a href="<%=path%>/tut/Tutor_myAnserGroup.action"
				target="right"><span class="icon-caret-right"></span>答辩组信息查询</a></li>
		</ul>
		
		
		<h2>
			<span class="icon-cog"></span>基本设置
		</h2>
		<ul>
			<li><a href="<%=path%>/tut/Tutor_personalData.action"
				target="right"><span class="icon-caret-right"></span>个人资料</a>
			</li>
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
				首页</a>
		</li>
		<li><a href="##" id="a_leader_txt">欢迎界面</a>
		</li>
		<li><b>当前语言：</b><span style="color: red;">中文</php> </span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言： <a href="##">中文</a> &nbsp;&nbsp;
			<a href="##">英文</a>
		</li>
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
