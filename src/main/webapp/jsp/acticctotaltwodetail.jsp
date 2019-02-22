<!-- 信用卡办理数据统计页面2.0 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信用卡办理2.0数据统计</title>
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
												<td>页面浏览数</td>
												<td>立即申请按钮数</td>
												<td>低消按钮数</td>
												<td>冰淇淋按钮数</td>
												<td>多日包按钮数</td>
												<td>特惠包按钮数</td>
											</tr>
										</thead>
										<tbody id="value">

										</tbody>

									</table>
									<table class="layui-hide" id="cctwolist" lay-filter="cctwolist">
									</table>
									<script>
										$.ajax({
											url:'../tbcctotalTwo/selectTotal',
											type:'post',
											dataType:'json',
											success:function(data){
												var str = "";
												var tbody=window.document.getElementById("value");
												str += "<tr>" +  
						                        "<td>" + data.pvtotal + "</td>" +  
						                        "<td>" + data.bctotal + "</td>" +  
						                        "<td>" + data.dxtotal + "</td>" +  
						                        "<td>" + data.icetotal + "</td>" +  
						                        "<td>" + data.mutitotal + "</td>" +  
						                        "<td>" + data.tehuitotal + "</td>" +
						                        "</tr>";
						                        tbody.innerHTML=str;
											}
										});
										
										layui.use('table',function() {
											var table = layui.table;
											var index = layer.load(1);
											table.render({
												elem : '#cctwolist',
												url : '../tbcctotalTwo/selectAllData',
												cellMinWidth : 80,
												height:420,
												title : '信用卡2.0数据统计',
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
														title : '浏览数',
														align : 'center'
													},
													{
														field : 'bc',
														title : '立即申请按钮数',
														align : 'center'
													},
													{
														field : 'see_dx',
														title : '低消按钮数',
														align : 'center'
													},
													{
														field : 'see_llb',
														title : '冰淇淋按钮数',
														align : 'center'
													},
													{
														field : 'see_muti',
														title : '多日包按钮数',
														align : 'center'
													},
													{
														field : 'see_tehui',
														title : '特惠包按钮数',
														align : 'center'
													},
													{
														fixed:'right',
														field:'addtime',
														title:'时间',
														sort : true,
														align : 'center'													
													}
													]] ,
													page:true,
													limit:10,
													id : 'cctwolist',
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