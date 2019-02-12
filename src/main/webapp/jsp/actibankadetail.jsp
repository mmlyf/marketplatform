<!-- 办卡活动的页面 -->
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
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show" >
						<div class="admin-main fadeInUp animated">
							<fieldset class="layui-elem-field">
								<legend>活动列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="banka" lay-filter="banka"></table>
									<script>
										layui.use('table',function() {
															var table = layui.table;

															table.render({
																		elem : '#banka',
																		url : 'http://221.192.138.29:8089/HSDT_Activity_Port/selectbanka',
																		cellMinWidth : 80,
																		width:760,
																		height:420,
																		title : '消息列表',
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
																					field : 'bc',
																					title : '按钮点击数'
																				},
																				{
																					field:'time',
																					title:'时间'
																				}
																				]] ,
																		limit:10,
																		id : 'bankalist'
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