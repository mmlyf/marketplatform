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
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				
				<div class="layui-tab-content" >
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show" >
						<div class="admin-main fadeInUp animated">
							<fieldset class="layui-elem-field">
								<legend>抽奖数据统计</legend>
								<div class="layui-field-box">
									<table class="layui-table">
										<thead>
											<tr>
											<td>页面总访问数</td>
											<td>按钮总点击数</td>
											<td>优酷总数</td>
											<td>爱奇艺总数</td>
											<td>芒果总数</td>
											<td>腾讯总数</td>
											</tr>
										</thead>
										<tbody id="value">
										
										</tbody>
										
									</table>
									<table class="layui-hide" id="equitylist" lay-filter="equitylist"></table>
									<script>
									
										$.ajax({
											url:'../equityPcTotal/selecttotal',
											type:'post',
											dataType:'json',
											success:function(data){
												 var tbody=window.document.getElementById("value");
												var str = "";
												str += "<tr>" +  
							                        "<td>" + data.pvtotal + "</td>" +  
							                        "<td>" + data.bctotal + "</td>" +  
							                        "<td>" + data.yktotal + "</td>" +  
							                        "<td>" + data.aqytotal + "</td>" +  
							                        "<td>" + data.mgtotal + "</td>" +  
							                        "<td>" + data.txtotal + "</td>" +   
							                        "</tr>";  
							                        console.log(str);
												 tbody.innerHTML=str;
											}
											
										});		
										layui.use('table',function() {
															var table = layui.table;  
															table.render({
																		elem : '#equitylist',
																		url : '../equityPcTotal/selectall',
																		cellMinWidth : 80,
																		height:420,
																		title : '数据统计列表',
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
																					field : 'pvcount',
																					title : '页面访问数',
																					align : 'center'
																				},
																				{
																					field : 'bccount',
																					title : '按钮点击数',
																					align : 'center'
																				},
																				{
																					field : 'youkucount',
																					title : '优酷',
																					align : 'center'
																				},
																				{
																					field : 'aqiycount',
																					title : '爱奇艺',
																					align : 'center'
																				},
																				{
																					field : 'mgcount',
																					title : '芒果',
																					align : 'center'
																				},
																				{
																					field : 'txcount',
																					title : '腾讯',
																					align : 'center'
																				},
																				{
																					field:'addtime',
																					title:'时间',
																					align : 'center',
																					sort : true
																				}
																				]] ,
																		limit:10,
																		page : true,
																		id : 'equitylist'
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