<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录注册页</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/fontAwesome/font-awesome.min.css">
	<link rel="stylesheet" href="../css/base.css">
	<link rel="stylesheet" href="../css/login.css">

	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="login-header">
	<div class="wrap-1200">
		<a href="#"><img src="../images/logo.png" alt=""></a>
	</div>
</div>

<!-- login-content -->
<div class="login-content">
	<div class="wrap-1200">
		<div class="login-field">
		    <!-- 导航 -->
			<ul class="nav nav-pills nav-justified" id="loginNav" role="tablist">
               <li role="presentation" class="active">
                  <a href="#login_signIn" aria-controls="login_signIn" role="tab" data-toggle="tab">社区会员登录</a>
               </li>
               <li role="presentation">
                  <a href="#login_signUp" aria-controls="login_signUp" role="tab" data-toggle="tab">用户注册</a>
               </li>
           </ul>
           <!-- 内容部分 -->
           <div class="tab-content">
           	<!--登录-->
           	<div role="tabpanel" class="tab-pane active" id="login_signIn">
               <form id="login_form">
               	 <div class="input_div">
               	 	<i class="fa fa-user"></i>
               	 	<input type="text" value="" name="username" placeholder="请输入用户名">
               	 </div>
               	 <div class="input_div">
               	 	<i class="fa fa-lock"></i>
               	 	<input type="password" value="" name="password" placeholder="请输入密码">
               	 </div>
               	 <div class="input_div"  style="width:200px;">
               	 	<i class="fa fa-lock"></i>
               	 	<input type="text" value="" placeholder="请输入验证码" style="width:165px;">
               	 	<img src="../images/yanzheng.png" alt="">
               	 </div>
               	 <div class="input_line">
               	 	<label>
               	 	   <input type="checkbox"> 记住登录
    			    </label>
    				<a href="javascript:">忘记密码?</a>
               	 </div>
                 <input type="button" class="login-btn" onclick="login()" value="登录">
               </form>
           	</div>
           	<!--注册-->
           	<div role="tabpanel" class="tab-pane" id="login_signUp">
               <form id="register_form">
               	 <div class="input_div">
               	 	<i class="fa fa-user"></i>
               	 	<input type="text" value="" name="username" placeholder="请输入账号">
               	 </div>
               	 <div class="input_div">
               	 	<i class="fa fa-lock"></i>
               	 	<input type="password" value="" name="password" placeholder="请输入密码">
               	 </div>
               	 <div class="input_div">
               	 	<i class="fa fa-lock"></i>
               	 	<input type="password" value="" name="pwdcheck" placeholder="确认密码">
               	 </div>
				   <!-- 身份 -->
				   <p class="radio-wrapper">
					   <!-- <label class="radio-inline">身份</label> -->
					   <label class="radio-inline">
						   <input type="radio" name="type" value="1" checked>会员
					   </label>
					   <label class="radio-inline">
						   <input type="radio" name="type"  value="3"> 讲师
					   </label>
				   </p>

				   <!-- /身份 -->
               	 <div class="input_div"  style="width:200px;">
               	 	<i class="fa fa-lock"></i>
               	 	<input type="text" value="" placeholder="请输入验证码" style="width:165px;">
               	 	<img src="../images/yanzheng.png" alt="">
               	 </div>
                 <input type="button" class="login-btn" onclick="register()" value="注册">
               </form>
           	</div>
           </div>

		</div>
	</div>
</div>
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
    function register() {
        $.ajax({
            async:false,
            type:'POST',
            data:$('#register_form').serialize(),
            url:'${pageContext.request.contextPath}/register.html',
            success:function(data){
                if(typeof(data)!="object") data=JSON.parse(data);
                if(data&&data.success){
                    //转到登录后的页面
                } else {
                    alert(data.message);
                }
            }
        });
    }

    function login() {
		console.log($('#login_form').serialize());
		console.log($('input[name="username"]').val());
        $.ajax({
            async:false,
            type:'POST',
            data:$('#login_form').serialize(),
            url:'${pageContext.request.contextPath}/login.html',
            success:function(data){
                if(typeof(data)!="object") data=JSON.parse(data);
                if(data&&data.success){
                    alert("登录成功")
                } else {
                    alert(data.message);
                }
            }
        });
    }
</script>
</html>