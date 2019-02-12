<!-- 登录页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>汇视达通网络科技</title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../css/login.css" />
<link rel="icon" href="../image/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../image/favicon.ico">
<link rel="icon" href="../image/favicon.ico">
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../layer/layer.js"></script>
</head>
<body class="beg-login-bg">
	<div class="beg-login-box" ng-app="hd" ng-controller="ctrl">
		<header>
			<h1>汇视达通后台登录</h1>
		</header>
		<div class="beg-login-main">
			<form class="layui-form layui-form-pane" id="loginform" method="post"
				action="">
				<div class="layui-form-item">
					<label class="beg-login-icon"> <i class="layui-icon">&#xe612;</i>
					</label> <input type="text" name="username" id="username"
						placeholder="这里输入登录名" class="layui-input" autocomplete="off">
				</div>
				<div class="layui-form-item">
					<label class="beg-login-icon"> <i class="layui-icon">&#xe642;</i>
					</label> <input type="password" name="password" id="password"
						placeholder="这里输入密码" class="layui-input" autocomplete="off">
				</div>
				<!--   <textarea name="data" hidden></textarea> -->
				<div class="layui-form-item lfb">
					<!-- <div class="beg-pull-left beg-login-remember" style="display: none;">
                    <label>记住帐号？</label>
                    <input type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
                </div> -->
					<div class="beg-pull-right" style="margin-right: 100px;">
						<button type="button" class="layui-btn btn-submit" lay-submit
							lay-filter="sub" id="submitAdd">
							<i class="layui-icon">&#xe650;</i> 登录
						</button>
					</div>
					<div class="beg-clear"></div>
				</div>
			</form>
			<script type="text/javascript">
				$('body').keydown(function() {
					if (event.keyCode == "13") {
						var username = document.getElementById("username").value;
						var password = document.getElementById("password").value;
						if (username == "" || password == "") {
							layer.msg("用户名或者密码不能为空");
						} else {
							var index = layer.load(1);
							$.ajax({
								type : 'post',
								url : "../login/byIdPaw",
								data : {
									'username' : username,
									'password' : password
								},
								dataType : 'json',
								success : function(data) {
									if (data.code == 200) {
										layer.close(index);
										layer.msg("登录成功", {
											time : 2000,
											end : function() {
												window.location.href = "../jsp/index.jsp";
											}
										});
									} else {
										layer.close(index);
										layer.msg("用户名或密码不正确，请核对后登录！");
									}
								},
								error : function() {
									layer.close(index);
									layer.msg("当前请求失败！");
								}
							});
						}
					}
				})
				$("#submitAdd").click(function() {
					var username = document.getElementById("username").value;
					var password = document.getElementById("password").value;
					if (username == "" || password == "") {
						layer.msg("用户名或者密码不能为空");
					} else {
						var index = layer.load(1);
						$.ajax({
							type : 'post',
							url : "../login/byIdPaw",
							data : {
								'username' : username,
								'password' : password
							},
							dataType : 'json',
							success : function(data) {
								if (data.code == 200) {
									layer.close(index);
									layer.msg("登录成功", {
										time : 2000,
										end : function() {
											window.location.href = "../jsp/index.jsp";
										}
									});
								} else {
									layer.close(index);
									layer.msg("用户名或密码不正确，请核对后登录！");
								}
							},
							error : function() {
								layer.close(index);
								layer.msg("当前请求失败！");
							}
						});
					}
				});
			</script>
		</div>
		<footer>
			<p>汇视达通 © http://mobile99.uninforun.com</p>
		</footer>
	</div>
</body>
</html>