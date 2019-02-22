<!-- 添加活动页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加活动</title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show"">
						<div class="admin-main fadeInUp animated ng-scope">

							<form class="layui-form layui-form-pane" method="post" action=""
								id="upactivity"   enctype="multipart/form-data">
								<input type="hidden" name="id" id="id"> 
								<div class="layui-form-item">
									<label class="layui-form-label">活动名称</label>
									<div class="layui-input-inline">
										<input type="text" name="actName" id="actName"
											lay-verify="actName" class="layui-input">
									</div>
									
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">活动时间</label>
									<div class="layui-input-inline">
										<input type="text" name="actStarttime" id="date_star"
											lay-verify="date" placeholder="开始时间" class="layui-input">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="actEndtime" id="date_end"
											lay-verify="date" placeholder="结束时间" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="updateaci">保存</button>
										<button type="button" id="canclesave"
											class="layui-btn layui-btn-primary">取消</button>
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
		//实现时间的选框的效果
		layui.use(['laydate','layer'], function() {
			var laydate = layui.laydate, layer = layui.layer;
			//执行一个laydate实例
			laydate.render({
				elem : '#date_star' //指定元素
				,
				position : 'abolute',
				type : 'datetime'
			});
			laydate.render({
				elem : '#date_end',
				position : 'ablute',
				type : 'datetime'
			});

			$("#updateaci").click(function() {
				$.ajax({
					url : '../reqact/updateActInfo',
					type : 'POST',
					dataType : 'json',
					data : $('#upactivity').serialize(),
					success : function(result) {
						if (result.code == 0) {
							layer.msg("修改成功", {
								end : function() {
									var index = parent.layer.getFrameIndex(window.name);
									parent.layer.close(index);
								}
							});
						} else {
							layer.msg("修改失败");
						}
					}
				});
			});

			$("#canclesave").click(function() {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
		});
		$(function() {
			var str = window.location.href;
			var id = decodeURI(str.split('?')[1]);
			var actname = decodeURI(str.split('?')[2]);
			$('#id').val(id);
			$('#actName').val(actname);
			console.log($('#actname').val());
		});
	</script>
</body>
</html>