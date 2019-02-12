<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title></title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<fieldset class="layui-elem-field">
								<legend>号码详情</legend>
								<form class="layui-form layui-form-pane" method="post">
								<div class="layui-form-item">
									<label class="layui-form-label">网别</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="nettype"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">ChargeType</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="chargetype"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label" style="width: 140px">MainComboId</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="mainconboid"
											style="width: 230px"></label>
									</div>
								</div>

								<input type="hidden" name="id" id="id" />
								<div class="layui-form-item">
									<label class="layui-form-label">套餐名称</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="productname"
											style="width: 300px"></label>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">套餐内容</label>
									<div class="layui-input-inline">
										<textarea id="productdesc" class="layui-textarea"
											style="width: 600px" readonly="readonly"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">开启时间</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="opendate"
											style="width: 200px"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">城市</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="city"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<center><button type="button" id="cancle" class="layui-btn layui-btn-primary">关闭</button></center>
								</div>
							</form>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../layer/layer.js"></script>
	<script type="text/javascript">
		var str = window.location.href;
		var dn = decodeURI(str.split('?')[1]);
			var ind = parent.layer.load(2);
			$.ajax({
				url : '../buscontro/selectdninfo',
				type : 'post',
				dataType : 'json',
				data : {
					phone : dn
				},
				success : function(data) {
					parent.layer.close(ind);
					$('#nettype').html(data.NetworkType);
					$('#chargetype').html(data.ChargeType);
					$('#mainconboid').html(data.MainComboId);
					$('#productname').html(data.ProductName);
					$('#productdesc').val(data.ProductDesc);
					$('#opendate').html(data.OpenDate);
					$('#city').html(data.CityCode);
				}
			});
		$("#cancle").click(function() {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
	</script>
</body>
</html>