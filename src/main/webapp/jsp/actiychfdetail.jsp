<!-- 预存话费的活动数据展示界面 -->
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
						<blockquote class="layui-elem-quote">
								<div class="selectorder">
								<form action="" class="layui-form" class="layui-inline">
									<div class="layui-input-inline">
										<input type="text" name="phone" id="phone" class="layui-input"
											placeholder="请输入手机号">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="orderno" id="orderno" class="layui-input"
											placeholder="订单编号">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="date_star" id="date_star"
											lay-verify="date" placeholder="开始日期" class="layui-input">
									</div>
									<div class="layui-input-inline">
										<input type="text" name="date_end" id="date_end"
											lay-verify="date" placeholder="结束日期" class="layui-input">
									</div>
									
									<div class="layui-input-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="listnum">
										</span>
									</div>
									
									</form>
									<button class="layui-btn" data-type="allreload">搜索</button>
								</div>
								<script type="text/javascript" src="../layui/layui.js"></script>
								<script type="text/javascript">
									//实现时间的选框的效果
									layui.use('laydate', function() {
										var laydate = layui.laydate;
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
									});
								</script>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>话费充值订单</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="ychf" lay-filter="ychf"></table>
									<script>
										layui.use('table',function() {
															var table = layui.table;
															var index = layer.load(1);
															table.render({
																		elem : '#ychf',
																		url : '../zfbcz/selectall',
																		cellMinWidth : 80,
																		height:400,
																		title : '话费充值订单',
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
																					field : 'orderno',
																					title : '订单编号',
																					sort : true,
																					align: 'center'
																				},
																				{
																					field : 'dn'	,																				
																					title : '充值号码',
																					align: 'center'
																				},
																				{
																					field : 'proname',
																					title : '产品名称',
																					align: 'center'
																				},
																				{
																					field:'price',
																					title:'销售额',
																					sort:true,
																					align: 'center'
																				},
																				{
																					field:'ordertime',
																					title:'订购时间',
																					sort:true,
																					align: 'center'
																				},
																				{
																					field:'zfstate',
																					title:'支付状态',
																					align: 'center'
																				},
																				{
																					field:'czstate',
																					title:'充值状态',
																					align: 'center'
																				},
																				{
																					fixed:'right',
																					field:'qudao',
																					title:'渠道',
																					align: 'center'
																				}
																				]] ,
																		page:true,
																		limit:10,
																		id : 'ychflist',
																		done:function(data){
																			layer.close(index);
																			$('#listnum').text(data.count+"条");
																		}
																});
															var $ = layui.$, active = {
																	allreload : function() {
																		var dn = $('#phone').val();
																		 var date_star = $('#date_star').val();
																		var date_end = $('#date_end').val();
																		var orderno = $('#orderno').val();
																		//执行重载
																		table.reload('ychflist',{
																			where:{
																				dn : dn ,
																				date_star : date_star,
																				date_end : date_end,
																				orderno:orderno
																				}
																			});
																	}
																};
																$('.selectorder .layui-btn').on('click',
																	function() {
																		var type = $(this).data(	'type');
																		active[type] ? active[type].call(this): '';
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