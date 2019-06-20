<!-- 优惠券订单数据展示页面 -->
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
								<legend>优惠券订单数据展示页面</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="couponorder" lay-filter="couponorder"></table>
									<script>
										layui.use('table',function() {
															var table = layui.table;

															table.render({
																		elem : '#couponorder',
																		url : '../thirdouttotal/selectcoupon',
																		cellMinWidth : 80,
																		width:760,
																		height:420,
																		title : '优惠券订单数据展示页面',
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
																					field : 'out_trade_on',
																					title : '订单编号'
																				},
																				{
																					field : 'subject',
																					title : '主题'
																				},
																				{
																					field : 'total_amount',
																					title : '金额'
																				},
																				{
																					field : 'payid',
																					title : '支付渠道'
																				},
																				{
																					field : 'address',
																					title : '地址'
																				},
																				{
																					field : 'body',
																					title : '订单详情'
																				},
																				{
																					field : 'status',
																					title : '支付状态'
																				},
																				{
																					field : 'addtime',
																					title : '订单时间'
																				},
																				{
																					field : 'mobile',
																					title : '手机号'
																				},
																				{
																					field : 'type',
																					title : '订单类型'
																				},
																				{
																					field : 'time',
																					title : '时间'
																				},
																				{
																					field : 'color',
																					title : '颜色'
																				}
																				]] ,
																		limit:10,
																		page:true,
																		id : 'couponorder'
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