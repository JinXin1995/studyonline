<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>讲师主页</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/fontAwesome/font-awesome.min.css">
	<link rel="stylesheet" href="../css/base.css">
<!-- 	<link rel="stylesheet" href="../css/index.css"> -->
	<link rel="stylesheet" href="../css/t-index.css">
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/t-index.js"></script>
</head>
<body>
<!-- header-nav -->
<div class="header-nav">
	<div class="wrap-1200">
		<div class="welcome-title">欢迎来到study-online</div>
		<ul>
			<c:if test="${sessionScope.user == null}">
				<li><a href="${pageContext.request.contextPath}/login.html">登录注册</a></li>
			</c:if>
			<c:if test="${sessionScope.user != null}">
				<li class="udown-tip">
					<a href="javascript:void(0)">我的</a>
					<div class="udown-message">
						<!-- top -->
						<div class="udown-top">
							<div class="left">
								<a href="${pageContext.request.contextPath}/home.html"><img src="../upload/${sessionScope.user.dpPath}" alt=""></a>
							</div>
							<div class="right">
								<div class="head">
									<p>${sessionScope.user.nickname}</p>
								</div>
								<p>${sessionScope.user.intro}</p>
							</div>
						</div>
						<!-- bottom -->
						<div class="udown-bottom">
							<a href="${pageContext.request.contextPath}/user/set.html" class="per-set"><i class="fa fa-gear"></i>个人设置</a>
							<a href="${pageContext.request.contextPath}/logout.html" class="login-out"><i class="fa fa-power-off"></i>退出系统</a>
						</div>
					</div>
				</li>
			</c:if>
		</ul>
	</div>
</div>
<!-- /header-nav -->

<!-- header-content -->
<div class="header-content">
	<div class="wrap-1200">
		<div class="logo-field">
			<a href="#"><img src="../images/logo.png" alt=""></a>
		</div>
		<form action="">
			<div class="search-field">
				<input type="text" placeholder="搜索课程资源" value="" class="search-input">
				<a href="#"><i class="fa fa-search"></i></a>
			</div>
		</form>
	</div>
</div>
<!-- /header-content -->

<!-- main-botnav -->
<div class="main-botnav">
	<div class="wrap-1200">
		<ul>
			<li class="a-home"><a href="#">首页</a></li>
			<li><a href="${pageContext.request.contextPath}/teacher/addCourse.html">创建课程</a></li>
		</ul>
	</div>
</div>
<!-- / main-botnav -->
<!-- banner -->
<div class="tbanner">
	<div class="wrap-1200">
		
	</div>
</div>
<!-- /banner -->

<!-- tclass -->
<div class="tclass">
	<div class="wrap-1200">
		<div class="tclass-content">		
			<!-- ul列表 -->
			<ul class="nav nav-pills" role="tablist">
				<li role="presentation" class="active">
					<a href="#myCreated" role="tab" data-toggle="tab">已发布课程</a>
				</li>
			</ul>
			<!--内容部分  -->
			<div class="tab-content">
				<!--已发布课程-->
				<div role="tabpanel" class="tab-pane active" id="myCreated">
					<c:forEach var="course" items="${courses}">
						<div class="tclass-line">
							<div class="left"><a href="#"><img src="../upload/${course.coverPic}" alt=""></a></div>
							<div class="right">
								<button class="btn-click"><i class="fa fa-location-arrow"></i></button>
								<div class="hide-field">
									<a href="${pageContext.request.contextPath}/teacher/addCourse.html?courseId=${course.id}" class="link-1"><i class="fa fa-edit"></i>编辑课程</a>
									<a href="javascript:deleteCourse(${course.id})" class="link-1"><i class="fa fa-trash-o"></i>删除课程</a>
									<a href="${pageContext.request.contextPath}/teacher/addChapter.html?courseId=${course.id}" class="link-1"><i class="fa  fa-plus-circle "></i>章节管理</a>
								</div>
							</div>
							<div class="middle">
								<h4>${course.name}</h4>
								<div>${course.introduction}</div>
								<p>
									<c:if test="${course.status == 0}"><span>未审核</span></c:if>
									<c:if test="${course.status == 1}"><span>通过审核</span></c:if>
									<c:if test="${course.status == 2}"><span>未通过审核</span></c:if>
								</p>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
			<!--/内容部分  -->
		</div>
	</div>
</div>
<!-- /tclass -->
</body>
<script>
	function deleteCourse(id){
		$.ajax({
			async:false,
			type:'POST',
			data:'courseId=' + id,
			url:'${pageContext.request.contextPath}/teacher/deleteCourse.html',
			success:function(data){
				if(typeof(data)!="object") data=JSON.parse(data);
				if(data&&data.success){
					location.reload();
				}else
					alert(data.message);
			}
		});
	}
</script>
</html>