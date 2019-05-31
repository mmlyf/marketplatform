<!-- 第三方低消页面数据统计-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				
				<div class="layui-tab-content" >
					<div class="layui-tab-item layui-show" >
						<div class="admin-main fadeInUp animated">
							<fieldset class="layui-elem-field">
								<legend>低消第三方数据统计</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="dxthirdpv" lay-filter="dxthirdpv"></table>
									<script>
										layui.use('table',function() {
															var table = layui.table;

															table.render({
																		elem : '#dxthirdpv',
																		url : '../thirdouttotal/selectdx',
																		cellMinWidth : 80,
																		width:760,
																		height:420,
																		title : '低消第三方数据统计',
																		cols : [[ 
																				{
																					field : 'id',
																					title : 'id',
																					sort : true,
																					unresize : true,
																					fixed : 'left',
																					width:80
																				},
																				{
																					field : 'pv',
																					title : '页面访问数'
																				},
																				{
																					field:'addtime',
																					title:'时间'
																				}
																				]] ,
																		limit:10,
																		page:true,
																		id : 'dxthirdpv'
																		});
														});
									</script>
								</div>

							</fieldset>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>