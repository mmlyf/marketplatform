<!-- 支付宝管理界面 -->
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
<body class="layui-layout-body" style="height:800px">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">

				<div class="layui-tab-content">
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="demoTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="demoReload"
											autocomplete="off">
									</div>
									<button class="layui-btn" data-type="reload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="num"> </span>
									</div>
								</div>
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>支付宝用户列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="alipayuser"
										lay-filter="alipayuser"></table>
									<% String permision = (String)session.getAttribute("permision"); %>
									<script type="text/html" id="toolbarDemo">
  										<div class="layui-btn-container">
										<%
											if(permision.indexOf("1")!=-1||permision.indexOf("36")!=-1){
										%>
											<a class="layui-btn layui-btn-xs" lay-event="output">导出用户</a>
										<%
											}
											if(permision.indexOf("1")!=-1||permision.indexOf("37")!=-1){
										%>
											<a class="layui-btn layui-btn-xs" lay-event="notgift">每日漏赠</a>
										<%}%>
  										</div>
									</script>
									<script type="text/html" id="barDemo">
										<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									<%
										if(permision.indexOf("1")!=-1||permision.indexOf("34")!=-1){
									%>
  										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="flowgift">流量赠送</a>
									<%}%>
									</script>
									<script>
										layui.use(['table','layer'],function() {
											var table = layui.table,layer = layui.layer;
											var index = layer.load(2);
											table.render({
												elem : '#alipayuser',
												url : '../alipayuser/selectbypage',
												cellMinWidth : 80,
												height:550,
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
														align : 'center'
													},
													{
														field : 'openId',
														title : '支付宝ID',
														width:200,
														align : 'center'
													},
													{
														fixed : 'right',
														title : '操作',
														toolbar : '#barDemo',
														width : 200,
														align : 'center'
													} ]] ,
													page : true,
													limit:10,
													id : 'userlist',
													done:function(data){
														$('#num').text(data.count+"条");
														layer.close(index);
													}
											});
											/* 表格中头部的按钮事件  */
											table.on('toolbar(alipayuser)', function(obj) {
												switch (obj.event) {
												case 'output'://导出
													/* 	window.location.href="http://localhost:8085/HSDT_Market_Platform/alipayuser/output"; */
													window.location.href = "http://221.192.138.29:8089/HSDT_Market_Platform/alipayuser/output";
													break;
												case 'notgift':
													layer.open({
														type : 2,
														title : '漏赠',
														area : [ '1000px', '700px' ],
														scrollbar : false,//禁止浏览器滚动
														content : 'alipayregift.jsp',
														end : function() {
															table.reload('userlist', {
																where : {}
															});
														}
													});
													break;
												}
											});

											//监听行工具事件
											table.on('tool(alipayuser)', function(obj) {
												var data = obj.data;
												//console.log(obj)
												if (obj.event === 'flowgift') {
													layer.open({
														type : 2,
														title : '赠送',
														area : [ '700px', '500px' ],
														scrollbar : false,
														content : 'alipayflowgift.jsp?' + data.dn
													});
												} else if (obj.event == 'detail') {
													layer.open({
														type : 2,
														title : '查看',
														area : [ '1000px', '600px' ],
														scrollbar : false,
														content : 'alipayuserdetail.jsp?' + data.dn
													});
												}
											});

											var $ = layui.$, active = {
												reload : function() {
													var dn = $('#demoReload').val();
													//执行重载
													table.reload('userlist', {
														where : {
															keyword : dn,
															keytype : 'Mobile'
														}
													});
												}
											};
											$('.demoTable .layui-btn').on('click', function() {
												var type = $(this).data('type');
												active[type] ? active[type].call(this) : '';
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