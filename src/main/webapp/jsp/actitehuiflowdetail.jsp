<!-- 特惠流量包数据展示 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
									<table class="layui-table" style="text-align: center;">
										<thead>
											<tr>
												<td>浏览总数</td>
												<td>立即办理按钮总数</td>
												<td>确认按钮总数</td>
												<td>多日包按钮总数</td>
												<td>3g流量订购成功总数</td>
												<td>8g流量订购成功总数</td>
												<td>12g流量订购成功总数</td>
												<td>25g流量订购成功总数</td>
												<td>40g流量订购成功总数</td>
												<td>3g流量订购失败总数</td>
												<td>8g流量订购失败总数</td>
												<td>12g流量订购失败总数</td>
												<td>25g流量订购失败总数</td>
												<td>40g流量订购失败总数</td>
												<td>流量订购成功总数</td>
												<td>流量订购失败总数</td>
											</tr>
										</thead>
										<tbody id="value">

										</tbody>

									</table>
									<table class="layui-table" id="tehuilist"
										lay-filter="tehuilist"></table>
									
									<script type="text/javascript">
									$.ajax({
										url:'../tehuiFlowTotal/selecttotal',
										type:'post',
										dataType:'json',
										success:function(data){
											var str = "";
											var tbody=window.document.getElementById("value");
											str += "<tr>" +  
					                        "<td>" + data.pvtotal + "</td>" +  
					                        "<td>" + data.lijibctotal + "</td>" +  
					                        "<td>" + data.comfirbctotal + "</td>" +  
					                        "<td>" + data.tanseebctotal + "</td>" +  
					                        "<td>" + data._3gsuccounttotal + "</td>" +  
					                        "<td>" + data._8gsuccounttotal + "</td>" +  
					                        "<td>" + data._12gsuccounttotal + "</td>" +  
					                        "<td>" + data._25gsuccounttotal + "</td>" +  
					                        "<td>" + data._40gsuccounttotal + "</td>" +  
					                        "<td>" + data._3gunsuccounttotal + "</td>" +  
					                        "<td>" + data._8gunsuccounttotal + "</td>" +  
					                        "<td>" + data._12gunsuccounttotal + "</td>" +  
					                        "<td>" + data._25gunsuccounttotal + "</td>" +  
					                        "<td>" + data._40gunsuccounttotal + "</td>" +  
					                        "<td>" + data.allsuccounttotal + "</td>" +  
					                        "<td>" + data.allunsuccounttotal + "</td>" +  
					                        "</tr>";
					                        tbody.innerHTML=str;	
										}
									});
									
									layui.use('table',function(){
										var table = layui.table;
										var index = layer.load(1);
										table.render({
											elem:'#tehuilist',
											url:'../tehuiFlowTotal/selectdata',
											cellMinWidth:80,
											height:420,
											title:'特惠流量包展示',
											cols : [ [ {
												field : 'id',
												title : 'id',
												sort : true,
												unresize : true,
												fixed : 'left',
												width : 80
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
												field : 'tan_seebc',
												title : '去看看“多日包”按钮数',
												align : 'center'
											}, {
												field : '3g_succount',
												title : '3G流量订购成功数',
												align : 'center'
											},{
												field : '8g_succount',
												title : '8G流量订购成功数',
												align : 'center'
											},{
												field : '12g_succount',
												title : '12G流量订购成功数',
												align : 'center'
											},{
												field : '25g_succount',
												title : '25G流量订购成功数',
												align : 'center'
											},{
												field : '40g_succount',
												title : '40G流量订购成功数',
												align : 'center'
											},{
												field : '3g_unsuccount',
												title : '3G流量订购失败数',
												align : 'center'
											},{
												field : '8g_unsuccount',
												title : '8G流量订购失败数',
												align : 'center'
											},{
												field : '12g_unsuccount',
												title : '12G流量订购失败数',
												align : 'center'
											},{
												field : '25g_unsuccount',
												title : '25G流量订购失败数',
												align : 'center'
											},{
												field : '40g_unsuccount',
												title : '40G流量订购失败数',
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
											id : 'tehuilist',
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