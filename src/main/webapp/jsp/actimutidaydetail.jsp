<!-- 多日包数据统计页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多日包数据统计页面</title>
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
												<td>申请按钮总数</td>
												<td>去看看按钮总数</td>
												<td>去看看ice浏览总数</td>
											</tr>
										</thead>
										<tbody id="value">

										</tbody>

									</table>
									<table class="layui-hide" id="mutilist" lay-filter="mutilist"></table>
									<script type="text/javascript">
										
										$.ajax({
											url:'../mutidaytotal/selecttotal',
											type:'post',
											dataType:'json',
											success:function(data){
												var str = "";
												var tbody=window.document.getElementById("value");
												str += "<tr>" +  
						                        "<td>" + data.pv + "</td>" +  
						                        "<td>" + data.bc + "</td>" +  
						                        "<td>" + data.six_orderc + "</td>" +  
						                        "<td>" + data.nine_orderc + "</td>" +  
						                        "</tr>";
						                        tbody.innerHTML=str;	
											}											
										});
										layui.use('table', function() {
											var table = layui.table;
											var index = layer.load(1);
											table.render({
												elem : '#mutilist',
												url : '../mutidaytotal/selectall',
												cellMinWidth : 80,
												height : 420,
												title : '多日包页面数据统计',
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
													field : 'bc',
													title : '申请按钮数',
													align : 'center'
												}, {
													field : 'six_orderc',
													title : '6.6元订购数',
													align : 'center'
												}, {
													field : 'nine_orderc',
													title : '9.9元订购数',
													align : 'center'
												}, {
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