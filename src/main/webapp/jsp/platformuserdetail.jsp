<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body style="width:600px">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!--平台用户的添加-->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post" id="insertsuser"  enctype="multipart/form-data">
								
								<div class="layui-form-item">
									<label class="layui-form-label">登录名</label>
									<div class="layui-input-block">
										<input type="text" name="username" id="username" required lay-verify="required"
											placeholder="请输入号码" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">密码</label>
									<div class="layui-input-block">
										<input type="text" name="password" id="password" required lay-verify="required"
											placeholder="请输入号码" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">姓名</label>
									<div class="layui-input-block">
										<input type="text" name="realname" id="realname" required lay-verify="required"
											placeholder="请输入号码" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">权限</label>
									<div class="layui-input-block" id="permission">
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="updatauser">保存</button>
										<button type="button" id="canclesave" class="layui-btn layui-btn-primary">取消</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	layui.use([ 'form', 'laypage', 'layer', 'upload' ,'laydate'],
			function() {
				 var str=window.location.href;
		   		 var dataid = decodeURI(str.split('?')[1]);
				var form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload;
				var laydate = layui.laydate;
				
				/*****************用于填充标签数据*******************/
				 $.ajax({
					url:'../login/selectpermission',
					type:'post',
					dataType:'json',
					success:function(result){
							var data = eval(result.data);
							var htmlstr="";
							$.each(data,function(index,item){
								var json = eval(item);
								$('#permission').append("<input type='checkbox' name='permission' title='"+json.per_name+"' value='"+json.per_id+"'>")
							});
							form.render('checkbox');
						}
					});
				
				$.ajax({
					url:'../login/selectuserbyid',
					type:'post',
					dataType:'json',
					data:{
						id:dataid
					},
					success:function(result){
						$('#username').attr('value',result.loginname);
						$('#password').attr('value',result.password);
						$('#realname').attr('value',result.realname);
						var permissions = result.permission.split(",")?result.permission.split(","):result.permission;
						var obj=document.getElementsByName('permission');
						console.log(permissions);
						for(var i = 0;i<permissions.length;i++){
							for(var j = 0;j<obj.length;j++){
								if(permissions[i]==obj[j].value){
									console.log(obj[j].value);
									obj[j].checked = true;
									break;
								}
							}
						}
						form.render('checkbox');
					}
				});
				 $("#updatauser").click(function(){
					 var permission = "";
					 $("input:checkbox[name='permission']:checked").each(function(i){
							if(i==0){
								permission = $(this).val();
							}else{
								permission += ","+$(this).val();
							}
			            });
					 var username = $('#username').val();
					 var password = $('#password').val();
					 var realname = $('#realname').val();
					 
					 console.log("permission的值是:"+permission);	
			    		$.ajax({
			    			url:'../login/updatesuser',
			    			type:'POST',
			    			dataType:'json',
			    			data:{
			    				id:dataid,
			    				permission:permission,
			    				username:username,
			    				password:password,
			    				realname:realname
			    			},
			    			success:function(result){
			    				if(result.code==0){
			    					layer.msg("更新成功",{end:function(){
			        					var index = parent.layer.getFrameIndex(window.name);
			        					parent.layer.close(index);
			    					}});
			    				}else{
			    					layer.msg("更新失败");
			    				}
			    			}
			    		});
			    });
			    $("#canclesave").click(function(){
			    		var index = parent.layer.getFrameIndex(window.name); 
			    		parent.layer.close(index);
			    });
	});
</script>
</body>
</html>