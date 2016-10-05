<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>讲师index</title>
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
			<li><a href="#">登录注册</a></li>
			<li><a href=""><i class="fa  fa-question-circle"></i>帮助</a></li>
			<li><a href=""><i class="fa fa-bell-o"></i>消息</a></li>
			<li class="udown-tip">
			   <a href="javascript:void(0)">我的</a>
			   <div class="udown-message">
			   <!-- top -->
			   	  <div class="udown-top">
			   	  	<div class="left">
			   	  		<a href="#"><img src="images/user-img.jpg" alt=""></a>
			   	  	</div>
			   	  	<div class="right">
			   	  		<div class="head">
			   	  			<p>username</p>
			   	  			<span>等级：78</span>
			   	  		</div>
			   	  		<p>过春风十里,尽荞麦青青</p>
			   	  	</div>
			   	  </div>
			   	  <!-- bottom -->
                  <div class="udown-bottom">
                  	<a href="#" class="per-set"><i class="fa fa-gear"></i>个人设置</a>
                  	<a href="#" class="login-out"><i class="fa fa-power-off"></i>退出系统</a>
                  </div>
			   </div>
			</li>
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
			<li><a href="#">已发布课程</a></li>
			<li><a href="#">关注课程</a></li>
			<li><a href="#">创建课程</a></li>
			<li><a href="#">个人中心</a></li>
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
				<li role="presentation">
					<a href="#myConcern"  role="tab" data-toggle="tab">关注课程</a>
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
									<span>更新中···</span>
									<span>共5章11节</span>
									<span>100人在学</span>
									<span>200人评论</span>
								</p>
							</div>
						</div>
					</c:forEach>

				</div>
				<!--关注课程-->
				<div role="tabpanel" class="tab-pane" id="myConcern">
                       <div class="tclass-line">
                     	<div class="left"><a href="#"><img src="../${course.coverPic}" alt=""></a></div>
                     	<div class="right">
                     		<button class="btn-click"><i class="fa fa-location-arrow"></i></button>
                     		<div class="hide-field">
                     			<a href="#"><img src="../images/user-img.jpg" alt=""></a>
                     			<a href="#" class="add-concern"><i class="fa  fa-plus-circle "></i>关注</a>
                     			<p>lalallalla</p>
                     		</div>
                     	</div>
                     	<div class="middle">
                     		<h4>微信开发巴拉巴拉</h4>
                     		<div>微信拥有庞大的用户基础，微信公众号的相关开发也比较热门，本套课程就带领大家进入Java微信公众号开发的精彩世界，学习微信公众号开发的相关概念，编辑模式和开发模式应用，以及百度BAE的使用。信公众号开发的精彩世界，学习微信公众号开发的相关概念，编辑模式和开</div>
                     		<p>
                     			<span>更新中···</span>
                     			<span>共5章11节</span>
                     			<span>100人在学</span>
                     			<span>200人评论</span>
                     		</p>
                     	</div>
                     </div>
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