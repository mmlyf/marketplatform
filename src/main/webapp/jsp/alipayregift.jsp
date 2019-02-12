<!-- 流量漏赠查看界面 -->
<%@page import="com.mtpt.service.impl.TBMssageService"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="com.mtpt.aliservice.impl.TBRecordService"%>
<%@page import="com.mtpt.alibean.TBRecord"%>
<%@page import="java.util.List"%>
<%@page import="com.mtpt.aliservice.ITBRecordService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
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
							<blockquote class="layui-elem-quote">
								<div
									style=" height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>支付宝漏赠用户列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="ungiftuser" lay-filter="ungiftuser"></table>
									<script type="text/html" id="toolbarDemo">
  										<div class="layui-btn-container">
											
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									<%
										String permision = (String)session.getAttribute("permision");
										if(permision.indexOf("1")!=-1||permision.indexOf("38")!=-1){
									%>
  										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="flowgift">补赠</a>
									<%
										}
									%>
									</script>
									<script>
										layui
												.use(
														'table',
														function() {
															var table = layui.table;

															table
																	.render({
																		elem : '#ungiftuser',
																		url : '../alipayuser/selectungift',
																		cellMinWidth : 80,
																		toolbar: '#toolbarDemo',
																		title : '支付宝用户列表',
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
																					width:80,
																					align : 'center'
																				},
																				{
																					field:'uid',
																					title:'用户ID',
																					sort:true,
																					width:300,
																					align : 'center'
																				},
																				{
																					field : 'dn',
																					title : '号码',
																					align : 'center',
																					width:200
																				},
																				{
																					field : 'openId',
																					title : '支付宝ID',
																					align : 'center'
																				},
																				{
																					fixed : 'right',
																					title : '操作',
																					toolbar : '#barDemo',
																					width : 150,
																					align : 'center'
																				} ]] ,
																		page : true,
																		limit:10,
																		id : 'userlist',
																		done:function(data){
																			$('#num').text(data.count+"条");
																		}
																	});
															
															//监听行工具事件
															table.on('tool(ungiftuser)',function(obj) {
																var data = obj.data;
																//console.log(obj)
																if (obj.event === 'flowgift') {
																	layer.open({
																		type:2,
																		title:'补赠',
																		area:['700px','500px'],
																		scrollbar:false,
																		content:'alipayflowgift.jsp?'+data.dn
																	});
																}
																});

															var $ = layui.$, active = {
																reload : function() {
																	var dn = $(
																			'#demoReload')
																			.val();
																	//执行重载
																	table
																			.reload(
																					'userlist',
																					{
																						where : {
																							keyword : dn,
																							keytype:'Mobile'
																							
																						}
																					});
																}
															};
															$('.demoTable .layui-btn').on('click',function() {
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