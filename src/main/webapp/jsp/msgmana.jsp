<!-- 消息管理界面 -->
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
	<div class="layui-fluid" >
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				
				<div class="layui-tab-content" >
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show" >
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
											class="num_peo" id="num"> 
										</span>
									</div>
								</div>
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>消息列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="test" lay-filter="test"></table>
									<script type="text/html" id="toolbarDemo">
  										<div class="layui-btn-container">
											<% 
											String permision = (String)session.getAttribute("permision");
											if(permision.indexOf("1")!=-1||permision.indexOf("35")!=-1){
											%>
   											 <button class="layui-btn layui-btn-sm" lay-event="addMsgData">添加</button>
											<% } %>
  										</div>
									</script>
									<script type="text/html" id="barDemo">
										<%
											if(permision.indexOf("22")!=-1||permision.indexOf("1")!=-1){
										%>
											<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
										<%
											}
										%>
 									 	<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
<%
											if(permision.indexOf("24")!=-1||permision.indexOf("1")!=-1){
										%>
  										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
<% } %>
									</script>
									<script>
										layui.use('table',function() {
															var table = layui.table;

															table.render({
																		elem : '#test',
																		url : '../msgmana/select',
																		cellMinWidth : 80,
																		width:window.innerWidth*0.94,
																		height:550,
																		toolbar: '#toolbarDemo',
																		title : '消息列表',
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
																					width:80
																				},
																				{
																					field : 'mistitle',
																					title : '标题',
																					width:100
																				},
																				{
																					field : 'miscontent',
																					title : '内容'
																				},
																				{
																					fixed : 'right',
																					title : '操作',
																					toolbar : '#barDemo',
																					width : 200
																				} ]] ,
																		page : true,
																		limit:10,
																		id : 'msglist',
																		done:function(data){
																			$('#num').text(data.count+"条");
																		}
																	});
															
															table.on('toolbar(test)',function(obj){
																switch(obj.event){
																case 'addMsgData':
																	layer.open({
																		type: 2,
																        title: '新增',
																        area : ['800px' , '420px'], 
																        scrollbar: false,//禁止浏览器滚动
																        content: 'insertmsg.jsp',
																        end:function(){
																        		table.reload('msglist',{where : {}});
																        }
																	});
																	break;
																}
															});
															
															//监听行工具事件
															table
																	.on(
																			'tool(test)',
																			function(
																					obj) {
																				var data = obj.data;
																				//console.log(obj)
																				if (obj.event === 'del') {
																					layer.confirm('确定删除ID为'+ data.id,function(index) {obj.del();
																						$.ajax({
																							url:'../msgmana/delete',
																							dataType:'json',
																							data:{
																								misId:data.id
																							},
																						success:function(result){
																							if(result.code == 1){
																								layer.msg("删除成功");
																								table.reload('msglist',{where : {}});
																							}else{
																								layer.msg("删除失败");
																							}
																						}
																						});
																					});
																				} else if (obj.event === 'detail') {
																					var str = "{<br>消息ID:"
																							+ data.id
																							+ "<br>消息标题:"
																							+ data.mistitle
																							+ "<br>消息内容:"
																							+ data.miscontent
																							+ "}";
																					layer
																							.alert('当前数据是：'
																									+ str)
																				}else if(obj.event === 'edit'){
																					layer.open({
																				        type: 2,
																				        title: '编辑',
																				        area : ['800px' , '420px'], 
																				        scrollbar: false,//禁止浏览器滚动
																				        content: encodeURI('editmsg.jsp?'+data.id+','+data.mistitle+','+data.miscontent),
																				        end:function(){
																				        	//执行重载
																							table.reload('msglist',{where : {}});
																				        }
																					} );
																				}
																			});

															var $ = layui.$, active = {
																reload : function() {
																	var title = $('#demoReload').val();
																	//执行重载
																	table.reload('msglist',
																					{
																						where : {
																							keyword : encodeURI(title),
																							keytype:'mis_title'
																						}
																					});
																}
															};
															$(
																	'.demoTable .layui-btn')
																	.on(
																			'click',
																			function() {
																				var type = $(
																						this)
																						.data(
																								'type');
																				active[type] ? active[type]
																						.call(this)
																						: '';
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
	
	<script>
		layui.use('element', function() {
			var $ = layui.jquery, element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

			//触发事件
			var active = {
				tabAdd : function() {
					//新增一个Tab项
					element.tabAdd('demo', {
						title : '新选项' + (Math.random() * 1000 | 0) //用于演示
						,
						content : '内容' + (Math.random() * 1000 | 0),
						id : new Date().getTime()
					//实际使用一般是规定好的id，这里以时间戳模拟下
					})
				},
				tabDelete : function(othis) {
					//删除指定Tab项
					element.tabDelete('demo', '44'); //删除：“商品管理”

					othis.addClass('layui-btn-disabled');
				},
				tabChange : function() {
					//切换到指定Tab项
					element.tabChange('demo', '22'); //切换到：用户管理
				}
			};

			$('.site-demo-active').on('click', function() {
				var othis = $(this), type = othis.data('type');
				active[type] ? active[type].call(this, othis) : '';
			});

			//Hash地址的定位
			var layid = location.hash.replace(/^#test=/, '');
			element.tabChange('test', layid);

			element.on('tab(test)', function(elem) {
				location.hash = 'test=' + $(this).attr('lay-id');
			});

		});

		//监听指定开关

		layui.use('form', function() {
			var form = layui.form, layer = layui.layer;
			form.on('switch(switchTest)', function(data) {
				layer.msg('定时任务' + (this.checked ? '开' : '关'), {
					offset : '6px'
				});
				if (this.checked) {
					$('#timechoice').removeClass('layui-hide');
				} else {
					$('#timechoice').addClass('layui-hide');
				}
			});
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#test1' //指定元素
				,
				position : 'abolute',
				type : 'datetime'
			});
		});
	</script>
</body>
</html>