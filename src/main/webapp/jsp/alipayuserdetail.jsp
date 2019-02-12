<!-- 支付宝用户查看详情界面，主要是展示流量赠送情况 -->
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
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">


				<div class="layui-tab-content">
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">

							<fieldset class="layui-elem-field">
								<div class="layui-field-box">
								<input type="hidden" id="id">
								<input type="hidden" id="openId">
									<table class="layui-hide" id="test" lay-filter="test"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
								
									</script>
									<script>
									
										layui.use('table',function() {
															var table = layui.table;
															var dn = $("#id").attr("value");
															var openid = $("#openId").attr("value");
															table
																	.render({
																		elem : '#test',
																		url : '../alipayuser/selectdetail',
																		cellMinWidth : 80,
																		height:450,
																		title : '群组列表',
																		loading:true,
																		cols : [[ 
																				{
																					type : 'checkbox',
																					fixed : 'left'
																				},
																				{
																					field : 'id',
																					title : 'ID',
																					sort : true,
																					unresize : true,
																					fixed : 'left',
																					align : 'center'
																				},
																				{
																					field : 'dn',
																					title : '号码',
																					sort : true,
																					width:120,
																					align : 'center'
																				},
																				{
																					field : 'amount',
																					title : '流量',
																					width:80
																				},
																				{
																					field : 'state',
																					title : '状态',
																					sort : true,
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'modifytime',
																					title : '赠送时间',
																					width : 170,
																					align : 'center'
																				}]] ,
																		page : true,
																		limit:10,
																		where:{
																			keyword:dn,
																			keytype:'Mobile'
																		},
																		id : 'alipaylist',
																		done:function(data){
																			$('#groupnum').text(data.count+"条");
																		}
																	});

															table.on('toolbar(test)',function(obj){
																
															});
															table.on('tool(test)',function(obj){
																
															});
															
														});
										
										$(function(){
											 var str=window.location.href;
											    var data = str.split('?')[1]; 
											    var dataarr = data.split(',');
											    $("#id").attr("value",dataarr[0]);
											    $("#openId").attr("value",dataarr[1]);
										});
									</script>
								</div>

							</fieldset>
						</div>
					</div>
</body>
</html>