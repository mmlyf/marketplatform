<!-- 订单数据的展示 -->
<%@page import="com.mtpt.aliservice.impl.TBReviewService"%>
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
<body class="layui-layout-body" style="height: 900px">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<fieldset class="layui-elem-field">
								<legend>订单列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="hfczlist" lay-filter="hfczlist"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									<a class="layui-btn layui-btn-xs" lay-event="review">审核</a>
									<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									</script>

									<script>
										layui.use('table', function() {
											var table = layui.table;
											table.render({
												elem : '#hfczlist',
												url : '../hfczreview/selhfczreview',
												cellMinWidth : 80,
												width : window.innerWidth * 0.94,
												height : 550,
												toolbar : 'toolbarDemo',
												title : '群组列表',
												loading : true,
												cols : [ [ {
													type : 'checkbox',
													fixed : 'left'
												}, {
													field : 'id',
													title : 'id',
													sort : true,
													unresize : true,
													fixed : 'left',
													align : 'center'
												}, {
													field : 'czdn',
													title : '号码',
													align : 'center',
													width : 130
												}, {
													field : 'czamount',
													title : '金额',
													align : 'center',
													width : 80
												}, {
													field : 'czreviewer',
													title : '审核人员',
													width : 100,
													align : 'center'
												}, {
													field : 'addman',
													title : '创建者',
													width : 100,
													align : 'center'
												}, {
													field : 'reviewstate',
													title : '审核状态',
													width : 200,
													align : 'center'
												},{
													field : 'czaddtime',
													title : '充值时间',
													width : 200,
													sort : true,
													align : 'center'
												},{
													fixed : 'right',
													title : '操作',
													toolbar : '#barDemo',
													width : 180,
													align : 'center'
												} ] ],
												page : true,
												limit : 10,
												where:{
													reviewname:encodeURI('<%=session.getAttribute("username")%>')	
												},
												id : 'hfczlist',
												done : function(data) {
													$('#listnum').text(data.count + "条");
												}
											});

											table.on('toolbar(hfczlist)', function(obj) {

											});

											//监听行工具事件
											table.on('tool(hfczlist)', function(obj) {
												var data = obj.data;
												//console.log(obj)
												if (obj.event === 'detail') {
													var str = "{<br>id:" + data.id + "<br>号码:" + data.czdn + "<br>编号:" + data.seno + "<br>充值时间:" + data.czaddtime + "<br>充值金额:"
															+ data.czamount + "<br>审核者:" + data.czreviewer +"<br>创建者:" + data.addman + "<br>充值原因:" + data.czreason + "<br>状态:" + data.reviewstate + "}";
													layer.alert('当前数据是：' + str)
												}else if(obj.event === 'review' ){
													if(data.reviewstate=='审核通过'){
														layer.msg('当前已审核，请不要重复审核');
													}else if(data.reviewstate=='审核未通过'){
														layer.msg('当前审核未通过，请联系提交人员及时修改，并重新提交');
													}else if(data.reviewstate=='未审核'){
													layer.open({
														type:2,
														title:'审批',
														maxmin:true,
														area:['800px','800px'],
													    content: encodeURI('hfczeditreview.jsp?'+data.id
													        		+","+data.czdn
													        		+","+data.seno
													        		+","+data.czaddtime
													        		+","+data.czamount
													        		+","+data.czreviewer
													        		+","+data.addman
													        		+","+data.czreason
													        		+","+data.reviewstate),
													     end:function(){
													        	//执行重载
																table.reload('hfczlist',{
																	where : 
																	{
																		reviewname:'<%=session.getAttribute("username")%>'
																	}
																});
													        }
														});
													}else{
														layer.msg('当前状态不符合审核标准！');
													}
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