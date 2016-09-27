<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>创建课程step1</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/fontAwesome/font-awesome.min.css">
	<link rel="stylesheet" href="../css/base.css">
 	<link rel="stylesheet" href="../css/index.css">
	<link rel="stylesheet" href="../css/t-index.css">
	<script src="../js/jquery.min.js"></script>
	<script src="../js/jquery.form.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/t-index.js"></script>
  <script src="../js/person-set.js"></script>
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
			<!-- step1 -->
         <div class="step">
             <div class="top step-bg1"></div>
             <div class="content">
                <form id="courseForm" class="form-step1">
                   <h4>创建新的课程</h4>
                   <div class="line">
                      <span>课程名</span>
                      <input type="text" name="name" value="" placeholder="请输入课程名">
                   </div>
                   <div class="line">
                      <span>课程方向</span>
                      <select name="typeId" id="">
                          <c:forEach var="type" items="${types }">
                              <option value="${type.id }">${type.name }</option>
                          </c:forEach>
                      </select>
                   </div>
                   <div class="line" style="height:30px;">
                      <span>课程难度</span>
                       <label class="radio-inline">
                         <input type="radio" name="difficulty" value="0" checked>初级
                      </label>
                      <label class="radio-inline">
                         <input type="radio" name="difficulty" value="1">中级
                      </label>
                      <label class="radio-inline">
                         <input type="radio" name="difficulty" value="2">高级
                      </label> 
                   </div>
                   <div class="line">
                      <span>课程介绍</span>
                      <textarea name="introduction" id="" cols="30" rows="10"></textarea>
                   </div>
                   <div class="line">
                      <span>封面图片</span>
                      <input type="text" id="show_file_name" name="coverPic" style="width:300px;">
                      <button class="btn-liulan" type="button" onclick="$('#file_wrap').click()">浏览···</button>
                   </div>
                   <div class="line">
                       <input type="button" class="btn btn-sm btn-info pull-right" style="margin-right:30px;" value="发布" onclick="addCourse()">
                   </div>

                </form>

                 <form id="uploadPicForm"
                       action="${pageContext.request.contextPath}/up/pic.html"
                       role="form" method="post" enctype="multipart/form-data">
                     <input type="file" name="file" id="file_wrap" onchange="getFilename(this.value)" style="display:none;" >
                 </form>
             </div>
         </div>
         <!-- /step1 -->
		</div>
	</div>
</div>
<!-- /tclass -->


<!-- footer -->
<div class="footer">
 <div class="wrap-1200">
   <ul>
      <li><a href="#">关于我们</a></li>
      <li><a href="#">意见反馈</a></li>
      <li><a href="#">友情链接</a></li>
      <li><a href="#">联系我们</a></li>
      <li><a href="#">讲师招聘</a></li>
      <li><a href="#">网站地图</a></li>

      <li class="pull-right"><a href="#">讲师申请</a></li>
      <li class="pull-right"><a href="#">管理员登录</a></li>
   </ul>
   <p>备案号:1234567890123456789</p>
 </div>  
</div>   
<!-- /footer -->
</body>
<script>
    function uploadPic(){
        var ret=false;
        $('#uploadPicForm').ajaxSubmit({
            async:false,
            success: function (data) {
                if(typeof(data)!='object') data=JSON.parse(data);
                if(data&&data.success){
                    $('#show_file_name').val(data.data);
                    console.log(data.data,data.success,data.message);
                    ret=true;
                }
                else
                    alert(data.message);
            }
        });
        return ret;
    }

    function addCourse(){
        var tmp=uploadPic();//reportUrl
        if(tmp==false){
            return false;
        }

        $.ajax({
            async:false,
            type:'POST',
            data:$('#courseForm').serialize(),
            url:'${pageContext.request.contextPath}/teacher/addCourse.html',
            success:function(data){
                if(typeof(data)!="object") data=JSON.parse(data);
                if(data&&data.success){

                }else
                    alert("保存失败！");
            }
        });
    }
</script>
</html>