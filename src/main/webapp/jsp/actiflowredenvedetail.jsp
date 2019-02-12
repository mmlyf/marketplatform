<!-- 流量红包的数据展示 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流量红包活动数据展示</title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<fieldset class="layui-elem-field">
								<legend>数据列表</legend>
								<div class="layui-field-box">
									<table class="layui-table" style="text-align: center">
										<thead>
											<tr>
												<td>浏览总数</td>
												<td>立即办理按钮总数</td>
												<td>确认按钮总数</td>
												<td>腾讯视频会员总数</td>
												<td>pp视频会员总数</td>
												<td>爱奇艺视频会员总数</td>
												<td>优酷视频会员总数</td>
												<td>流量订购成功总数</td>
												<td>流量订购失败总数</td>
											</tr>
										</thead>
										<tbody id="value">

										</tbody>

									</table>
									<table class="layui-hide" id="flowredlist" lay-filter="flowredlist"></table>
									<script type="text/javascript">
										$.ajax({
											url:'../flowredeve/selecttotal',
											type:'post',
											dataType:'json',
											success:function(data){
												var str = "";
												var tbody=window.document.getElementById("value");
												str += "<tr>" +  
						                        "<td>" + data.pvtotal + "</td>" +  
						                        "<td>" + data.lijibctotal + "</td>" +  
						                        "<td>" + data.comfirbctotal + "</td>" +  
						                        "<td>" + data.txcounttotal + "</td>" +  
						                        "<td>" + data.ppcounttotal + "</td>" +  
						                        "<td>" + data.aqycounttotal + "</td>" +  
						                        "<td>" + data.ykcounttotal + "</td>" +  
						                        "<td>" + data.ordersuccounttotal + "</td>" +  
						                        "<td>" + data.orderunsuccounttotal + "</td>" +  
						                        "</tr>";
						                        tbody.innerHTML=str;	
											}
										});
										
										layui.use('table', function() {
											var table = layui.table;
											var index = layer.load(1);
											table.render({
												elem : '#flowredlist',
												url : '../flowredeve/selectdata',
												cellMinWidth : 80,
												height : 420,
												title : '流量红包列表',
												cols : [ [ {
													field : 'id',
													title : 'id',
													sort : true,
													unresize : true,
													fixed : 'left'
												}, {
													field : 'pv',
													title : '浏览数',
													align : 'center'
												}, {
													field : 'liji_bc',
													title : '立即办理按钮数',
													align : 'center'
												}, {
													field : 'comfir_bc',
													title : '确认按钮数',
													align : 'center'
												}, {
													field : 'tx_count',
													title : '腾讯视频会员',
													align : 'center'
												}, {
													field : 'pp_count',
													title : 'pp视频会员',
													align : 'center'
												},{
													field : 'aqy_count',
													title : '爱奇艺视频会员',
													align : 'center'
												},{
													field : 'yk_count',
													title : '优酷视频会员',
													align : 'center'
												},{
													field : 'ordersuc_count',
													title : '流量包订购成功数',
													align : 'center'
												},{
													field : 'orderunsuc_count',
													title : '流量包订购失败数',
													align : 'center'
												},{
													fixed : 'right',
													field : 'addtime',
													title : '时间',
													sort : true,
													align : 'center'
												} ] ],
												page : true,
												limit : 10,
												id : 'cclist',
												done : function() {
													layer.close(index);
												}
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