<!-- 信用卡办理页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新信用卡办理页面</title>
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
												<td>申请按钮总数</td>
												<td>去看看按钮总数</td>
												<td>去看看ice浏览总数</td>
												<td>去看看dx浏览总数</td>
												<td>去看看多日包浏览总数</td>
												<td>ice按钮总数</td>
												<td>dx按钮总数</td>
												<td>cl信用卡浏览总数</td>
												<td>np信用卡浏览总数</td>
												<td>ice浏览总数</td>
												<td>dx浏览总数</td>
												<td>ice预约总数</td>
												<td>dx成功总数</td>
												<td>dx失败总数</td>
												<td>6.6元多日包订购总数</td>
												<td>9.9元多日包订购总数</td>
												<td>短信总数</td>
												<td>多日包快点这里点击总数</td>
												<td>特惠流量快点这里点击总数</td>
											</tr>
										</thead>
										<tbody id="value">

										</tbody>

									</table>
									<table class="layui-hide" id="cclist" lay-filter="cclist">
									</table>
									<script>
										$.ajax({
											url:'../tbccTotal/selecttotal',
											type:'post',
											dataType:'json',
											success:function(data){
												var str = "";
												var tbody=window.document.getElementById("value");
												str += "<tr>" +  
						                        "<td>" + data.bpv + "</td>" +  
						                        "<td>" + data.liallbc + "</td>" +  
						                        "<td>" + data.tanseebc + "</td>" +  
						                        "<td>" + data.tanseeicepv + "</td>" +  
						                        "<td>" + data.tanseedxpv + "</td>" +  
						                        "<td>" + data.tanliicebc + "</td>" +   
						                        "<td>" + data.tanlidxbc + "</td>" + 
						                        "<td>" + data.cccpv + "</td>" + 
						                        "<td>" + data.linpccpv + "</td>" + 
						                        "<td>" + data.seeicepv + "</td>" + 
						                        "<td>" + data.seedxpv + "</td>" + 
						                        "<td>" + data.seellbpv + "</td>" + 
						                        "<td>" + data.icebookc + "</td>" + 
						                        "<td>" + data.dxordersucc + "</td>" + 
						                        "<td>" + data.dxorderunsucc + "</td>" + 
						                        "<td>" + data.sixorderc + "</td>" + 
						                        "<td>" + data.nineorderc + "</td>" + 
						                        "<td>" + data.smsendc + "</td>" + 
						                        "<td>" + data.mutibc + "</td>" + 
						                        "<td>" + data.tehuibc + "</td>" + 
						                        "</tr>";
						                        tbody.innerHTML=str;
											}
										});
										
										layui.use('table',function() {
											var table = layui.table;
											var index = layer.load(1);
											table.render({
												elem : '#cclist',
												url : '../tbccTotal/selectall',
												cellMinWidth : 80,
												height:420,
												title : '信用卡数据统计',
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
														field : 'b_pv',
														title : '浏览数',
														align : 'center'
													},
													{
														field : 'li_allbc',
														title : '申请按钮数',
														align : 'center'
													},
													{
														field : 'tan_seebc',
														title : '去看看按钮数',
														align : 'center'
													},
													{
														field : 'tan_seeicepv',
														title : '去看看ice浏览数',
														align : 'center'
													},
													{
														field : 'tan_seedxpv',
														title : '去看看dx浏览数',
														align : 'center'
													},
													{
														field : 'tanli_icebc',
														title : 'ice按钮数',
														align : 'center'
													},
													{
														field : 'tanli_dxbc',
														title : 'dx按钮数',
														align : 'center'
													},
													{
														field : 'ccc_pv',
														title : 'cl信用卡浏览数',
														align : 'center'
													},
													{
														field : 'li_npccpv',
														title : 'np信用卡浏览数',
														align : 'center'
													},
													{
														field : 'see_icepv',
														title : 'ice浏览数',
														align : 'center'
													},
													{
														field : 'see_dxpv',
														title : 'dx浏览数',
														align : 'center'
													},
													{
														field : 'see_llbpv',
														title : '多日包浏览数',
														align : 'center'
													},
													{
														field : 'ice_bookc',
														title : 'ice预约数',
														align : 'center'
													},
													{
														field : 'dx_ordersucc',
														title : 'dx成功数',
														align : 'center'
													},
													{
														field : 'dx_ordersunsucc',
														title : 'dx失败数',
														align : 'center'
													},
													{
														field : 'six_orderc',
														title : '6.6元多日包订购数',
														align : 'center'
													},
													{
														field : 'nine_orderc',
														title : '9.9元多日包订购数',
														align : 'center'
													},
													{
														field : 'sm_sendc',
														title : '短信数',
														align : 'center'
													},
													{
														field : 'muti_bc',
														title : '多日包“快点这里”点击数',
														align : 'center'
													},
													{
														field : 'tehui_bc',
														title : '特惠流量“快点这里”点击数',
														align : 'center'
													},
													{
														fixed:'right',
														field:'addtime',
														title:'时间',
														width:130,
														sort : true,
														align : 'center'													
													}
													]] ,
													page:true,
													limit:10,
													id : 'cclist',
													done:function(){
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