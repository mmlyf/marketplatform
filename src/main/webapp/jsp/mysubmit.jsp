<!-- 我的提交的页面 -->
<%@page import="com.mtpt.alibean.TBReview"%>
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
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
	
</head>
<body class="layui-layout-body" style="height:800px">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<ul class="layui-tab-title">
					<li class="layui-this">文件导入</li>
					<li>自动筛选</li>
				</ul>
				
				<div class="layui-tab-content" >
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show" >
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="demoTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="demoReload"
											autocomplete="off" placeholder="请输入群组名">
									</div>
									<button class="layui-btn" data-type="reload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="groupnum"> </span>
									</div>
									<div class="layui-inline" id="sendop"
									style="float: right;">
									<button type="button" class="layui-btn" id="stop">群发暂停</button>
									<button type="button" class="layui-btn layui-hide" id="start">群发续发</button>
									<button type="button" class="layui-btn tzqf" id="end">群发停止</button>
								</div>
								</div>
								
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>我的提交</legend>
								<div class="layui-field-box" id="tablefile">
									<table class="layui-hide" id="test" lay-filter="test"></table>
									<script type="text/html" id="toolbarDemo"></script>
									<script type="text/html" id="barDemo">
									<%
										String permission = (String)session.getAttribute("permision");
									if(permission.indexOf("1")!=-1||permission.indexOf("25")!=-1){
									%>
										<a class="layui-btn layui-btn-xs" id="confirmsend" lay-event="send">发送</a>
									<%}
										if(permission.indexOf("1")!=-1||permission.indexOf("26")!=-1){
									%>
										<a class="layui-btn layui-btn-xs" id="confirmsend" lay-event="edit">修改</a>	
									<%
										}
										if(permission.indexOf("1")!=-1||permission.indexOf("27")!=-1){
									%>
  										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
									<%
										}
									%>
									</script>
									<script>
										layui.use('table',function() {
															var table = layui.table;

															table
																	.render({
																		elem : '#test',
																		url : '../smsupdate/getfiledata',
																		cellMinWidth : 80,
																		width:window.innerWidth*0.94,
																		height:550,
																		toolbar:'toolbarDemo',
																		title : '群组列表',
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
																					align : 'center'
																				},
																				{
																					field : 'groupname',
																					title : '群组名',
																					align : 'center'
																				},
																				{
																					field : 'filename',
																					title : '文件名',
																					align : 'center'
																				},
																				{
																					field : 'filenum',
																					title : '用户数',
																					sort : true,
																					align : 'center'
																				},
																				{
																					field : 'addman',
																					title : '创建人'
																				},
																				{
																					field : 'addtime',
																					title : '创建时间',
																					sort : true,
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'lastwork',
																					title : '最后操作者',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'endtime',
																					title : '结束时间',
																					width : 100
																				},
																				{
																					field : 'istimework',
																					title : '定时任务',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'worktime',
																					title : '任务时间',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'isdelblack',
																					title : '黑名单',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'isdeldays',
																					title : '重复运营',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'deldays',
																					title : '重复天数',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'msgtitle',
																					title : '消息',
																					width : 80,
																					align : 'center'
																				},
																				{
																					field : 'reviewman',
																					title : '审核人',
																					width : 80,
																					align : 'center'
																				},
																				{
																					field : 'state',
																					title : '状态',
																					align : 'center',
																					fixed:'right',
																					width:100
																				},
																				{
																					fixed : 'right',
																					title : '操作',
																					toolbar : '#barDemo',
																					width : 160,
																					align : 'center'
																				} ]] ,
																		page : true,
																		limit:10,
																		where:{
																			keyword:'<%=session.getAttribute("realname") %>',
																			keytype:'addman'
																		},
																		id : 'tasklist',
																		done:function(res, curr, count){
																			$('#groupnum').text(count+"条");
																			
																		}
																	});
															//监听行工具事件
															table.on('tool(test)',function(obj) {
																				var data = obj.data;
																				//console.log(obj)
																				if (obj.event === 'edit') {
																					if(data.state=='审核通过'){
																						layer.msg('当前已审核通过，不需要修改！\n请点击发送吧！！');
																					}else if(data.state=='未审核'||data.state=='审核未通过'){
																							layer.open({
																								type:2,
																								title:'审批',
																								area:['800px' , '700px'],
																							    content: encodeURI('editsubmitfile.jsp?'+data.groupname+','+data.id+','+data.filename),
																							     end:function(){
																							        	//执行重载
																										table.reload('tasklist',{where : {}});
																							        }
																							});
																						
																					}else{
																						layer.msg("当前状态不可修改");
																					}
																				}else if(obj.event === 'send'){
																					if(data.state=='审核通过'){
																						layer.confirm("你确定发送当前的数据:<br>id:"
																								+ data.id
																								+ "<br>群组名称:"
																								+ data.groupname
																								+ "<br>文件名称:"
																								+ data.filename
																								+ "<br>用户数:"
																								+ data.filenum
																								+ "<br>创建人:"
																								+ data.addman
																								+ "<br>创建时间:"
																								+ data.addtime
																								+ "<br>最后操作者:"
																								+ data.lastwork
																								+ "<br>结束时间:"
																								+ data.endtime
																								+ "<br>定时任务:"
																								+ data.istimework
																								+ "<br>任务时间:"
																								+ data.worktime
																								+"<br>消息标题："
																								+ data.msgtitle
																								+ "<br>审核人:"
																								+ data.reviewman
																								+ "<br>状态:"
																								+ data.state,function(index){
																									$.ajax({
																										url:'../requestsend/sendfile',
																										type:'post',
																										dataType:'json',
																										data:{
																											taskid:data.id,
																											worker:'<%=session.getAttribute("realname") %>'
																										},
																										success:function(result){
																											if(result.code==1){
																												layer.msg('发送任务提交成功');
																												table.reload('tasklist',{where : {}});
																											}else{
																												layer.msg('发送任务提交失败');
																											}
																										}
																									});
																								});
																					}else{
																						layer.msg('当前状态未被授权发送，请确认状态！\n或者需要审核通过之后才可发送');
																					}
																				}else if(obj.event === 'del' ){
																					if(data.state == '未审核'||data.state == '审核未通过'){
																						layer.confirm('确定删除ID为'+ data.id,function(index) {obj.del();
																						$.ajax({
																							url:'../smsupdate/deltask',
																							type:'post',
																							dataType:'json',
																							data:{
																								taskid:data.id
																							},
																							success:function(result){
																								if(result.code==1){
																									layer.msg('删除成功');
																									table.reload('tasklist',{where : {}});
																								}else{
																									layer.msg('删除失败');
																								}
																							}
																						});
																						});
																					}else{
																						layer.msg("当前状态不能删除");
																					}
																				}
																			});

															var $ = layui.$, active = {
																reload : function() {
																	var id = $('#demoReload').val();
																	//执行重载
																	table.reload('tasklist',{
																		where : {
																			keyid : id,
																			keyidtype : 'groupname',
																			keyword:'<%=session.getAttribute("realname") %>',
																			keytype:'addman'
																		}
																	});
																}
															};
															$('.demoTable .layui-btn').on('click',function() {
																var type = $(this).data(	'type');
																active[type] ? active[type].call(this): '';
															});
															
														});
										$("#stop").click(function(){
											$.ajax({
												url:'../requestsend/stopsendfile',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==1){
														layer.msg("群发暂停成功");
														$("#start").removeClass("layui-hide");
														$("#stop	").addClass("layui-hide");
														layui.use('table',function(){
															var table = layui.table;
															table.reload('tasklist',{where : {}});
														});
													}else{
														layer.msg('当前暂无群发任务');
													}
												}
											});
										});
										$("#start").click(function(){
											$.ajax({
												url:'../requestsend/startsendfile',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==1){
														layer.msg("群发续发成功！");
														$("#start").addClass("layui-hide");
														$("#stop	").removeClass("layui-hide");
														layui.use('table',function(){
															var table = layui.table;
															table.reload('tasklist',{where : {}});
														});
													}
												}
											});
										});
										$("#end").click(function(){
											$.ajax({
												url:'../requestsend/endsendfile',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==1){
														layer.msg('群发结束成功！');
														layui.use('table',function(){
															var table = layui.table;
															table.reload('tasklist',{where : {}});
														});
													}else{
														layer.msg('当前暂无群发任务');
													}
												}
											});
										});
									</script>
								</div>
							</fieldset>
						</div>
					</div>
					
					
					
					<!-- 维度筛选表格展示 -->
				<div class="layui-tab-item">
					<div class="admin-main fadeInUp animated" >
							<blockquote class="layui-elem-quote">
								<div class="modelTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="modelReload"
											autocomplete="off" placeholder="请输入id">
									</div>
									<button class="layui-btn" data-type="reload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="modelnum">  
											
										</span>
									</div>
									<div class="layui-inline" id="sendmodel"
									style="float: right;">
									<button type="button" class="layui-btn" id="stopmodel">群发暂停</button>
									<button type="button" class="layui-btn layui-hide" id="startmodel">群发续发</button>
									<button type="button" class="layui-btn tzqf" id="endmodel">群发停止</button>
								</div>
								</div>
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>模型审核列</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="model" lay-filter="model"></table>
									<script type="text/html" id=toolbarModel></script>
									<script type="text/html" id="barModel">
									<%
										if(permission.indexOf("25")!=-1||permission.indexOf("1")!=-1){
									%>
 									 	<a class="layui-btn layui-btn-xs" id="confirmsend" lay-event="sendmodel">发送</a>
									<%
										}
									//if(permission.indexOf("39")!=-1||permission.indexOf("1")!=-1){
										//""<a class="layui-btn layui-btn-xs" id="confirmsend" lay-event="exportdata">导出</a>
									%>
										
									<%
										//}
									if(permission.indexOf("27")!=-1||permission.indexOf("1")!=-1){
									%>
										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
																		elem : '#model',
																		url : '../smsupdate/getmodeldata',
																		title : '模型列表',
																		cellMinWidth : 80,
																		width:window.innerWidth*0.94,
																		height:550,
																		toolbar:toolbarModel,
																		cols : [[ 
																				{
																					type : 'checkbox',
																					fixed : 'left'
																				},
																				{
																					field : 'id',
																					title : 'ID',
																					sort : true,
																					fixed : 'left',
																					width : 80,
																					unresize : true,
																					align : 'center'
																				},
																				{
																					field : 'city',
																					title : '城市',
																					align : 'center'
																				},
																				{
																					field : 'source_type',
																					title : '网络类型',
																					unresize : true,
																					align : 'center'
																				},
																				{
																					field : 'product',
																					title : '产品',
																					align : 'center'
																				},
																				{
																					field : 'dangw',
																					title : '档位',
																					sort:true,
																					align : 'center'
																				},
																				{
																					field : 'ifrh',
																					title : '融合',
																					align : 'center'
																				},
																				{
																					field : 'ifdx',
																					title : '低消',
																					align : 'center'
																				},
																				{
																					field : 'istimework',
																					title : '定时任务',
																					align : 'center'
																				},
																				{
																					field : 'worktime',
																					title : '任务时间',
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'isdelblack',
																					title : '去黑名单',
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'isdeldays',
																					title : '去重复',
																					align : 'center'
																				},
																				{
																					field : 'deldays',
																					title : '重复天数',
																					align : 'center'
																				},
																				{
																					field : 'count',
																					title : '数量',
																					sort : true,
																					align : 'center'
																				},
																				
																				{
																					field : 'rduser',
																					title : '创建人',
																					width : 100,
																					align : 'center'
																		
																				},
																				{
																					field : 'sectime',
																					title : '入库时间',
																					width : 120,
																					sort : true,
																					align : 'center'
																				},
																				{
																					field : 'createtime',
																					title : '创建时间',
																					width : 120,
																					sort : true,
																					align : 'center'
																				},
																				{
																					field : 'msgtitle',
																					title : '消息',
																					width : 100,
																					align : 'center'
																				},
																				{
																					field : 'reuser',
																					title : '审核人',
																					align : 'center'
																				},
																				{
																					field : 'state',
																					title : '状态',
																					align : 'center',
																					fixed:'right',
																					width:100
																				},
																				{
																					field : "",
																					title : '操作',
																					toolbar : '#barModel',
																					width : 200,
																					fixed:'right',
																					sort : true,
																					align : 'center'
																				} ]] ,
																		page : true,
																		limit:10,
																		where:{
																			keyword:'<%=session.getAttribute("realname") %>',
																			keytype:'rd_user'
																		},
																		id : 'modellist',
																		done:function(data){
																			$('#modelnum').text(data.count+"条");
																		}
																	});
															//监听行工具事件
															table
																	.on(
																			'tool(model)',
																			function(
																					obj) {
																				var data = obj.data;
																				//console.log(obj)
																				if (obj.event === 'sendmodel') {
																					if(data.state=='审核通过'){
																						layer.confirm('确定需要发送当前模型维度下的数据嘛：\n'
																								+ data.city
																								+ "<br>网络类型:"
																								+ data.source_type
																								+ "<br>产品:"
																								+ data.product
																								+ "<br>档位:"
																								+ data.dangw
																								+ "<br>融合"
																								+ data.ifrh
																								+ "<br>低消："
																								+ data.ifdx
																								+ "<br>定时任务:"
																								+ data.istimework
																								+ "<br>定时时间:"
																								+ data.worktime
																								+ "<br>去黑名单:"
																								+ data.isdelblack
																								+ "<br>去重复:"
																								+ data.isdeldays
																								+ "<br>重复天数:"
																								+ data.deldays
																								+"<br>数量："
																								+data.count
																								+"<br>状态："
																								+data.state
																								+"<br>创建人"
																								+data.rduser
																								+"<br>入库时间："
																								+data.sectime
																								+"<br>创建时间"
																								+data.createtime
																								+"<br>消息:"
																								+data.msgtitle
																								+"<br>审核人："
																								+data.rduser,function(index){
																									layer.msg('执行发送');
																									 $.ajax({
																										url:'../requestsend/sendmodel',
																										dataType:'json',
																										type:'post',
																										data:{
																											id:data.id
																										},
																										success:function(result){
																											if(result.code==1){
																												layer.msg("发送任务提交成功");
																												table.reload('tasklist',{where : {}});
																											}else{
																												layer.msg("发送任务提交失败");
																											}
																										}
																									}); 
																								});
																						
																					}else{
																						layer.msg('当前状态不允许发送。请查看状态！\n确认后才可点击发送。');
																					}
																				} else if (obj.event === 'del') {
																					 if(data.state=='审核未通过'||data.state=='未审核'){
																						 layer.confirm('确定删除ID为'+ data.id,function(index) {obj.del();
																							$.ajax({
																								url:'../smsupdate/delmodel',
																								type:'post',
																								dataType:'json',
																								data:{
																									taskid:data.id
																								},
																								success:function(result){
																									if(result.code==1){
																										layer.msg('删除成功');
																										table.reload('tasklist',{where : {}});
																									}else{
																										layer.msg('删除失败');
																									}
																								}
																							});
																						});
																					}else{
																						layer.msg('当前状态不允许修改');
																					}
																				}else if(obj.event === 'exportdata'){
																					/* window.location.href="http://localhost:8085/HSDT_Market_Platform/smsupdate/exportdata?re_id="+data.id; */
																					window.location.href="http://221.192.138.29:8089/HSDT_Market_Platform/smsupdate/exportdata?re_id="+data.id;
																				}
																			});
															var $ = layui.$, active = {
																reload : function() {
																	var id = $(
																			'#modelReload')
																			.val();
																	//执行重载
																	table.reload('modellist',{where : {
																		keyid:id,
																		keyidtype:'id',
																		keyword:'<%=session.getAttribute("realname") %>',
																		keytype:'rd_user'
																	}});
																}
															};
									   $('.modelTable .layui-btn').on('click',function() {
										   var type = $(this).data('type');
										   active[type] ? active[type].call(this): '';
										   });
									   });
										
										$("#stopmodel").click(function(){
											$.ajax({
												url:'../requestsend/stopsendfile',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==1){
														layer.msg("群发暂停成功");
														$("#stopmodel").addClass("layui-hide");
														$("#startmodel").removeClass("layui-hide");
														layui.use('table',function(){
															var table = layui.table;
															table.reload('tasklist',{where : {}});														
														});		
													}else{
														layer.msg('当前暂无群发任务');
													}
												}
											});
										});
										$("#startmodel").click(function(){
											$.ajax({
												url:'../requestsend/startsendfile',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==1){
														layer.msg("群发续发成功！");
														$("#stopmodel").addClass("layui-hide");
														$("#startmodel").removeClass("layui-hide");
														layui.use('table',function(){
															var table = layui.table;
															table.reload('tasklist',{where : {}});														
														});	
													}else{
														layer.msg("当前暂无群发");
													}
												}
											});
										});
										$("#endmodel").click(function(){
											$.ajax({
												url:'../requestsend/endsendfile',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==1){
														layer.msg('群发结束成功！');
														layui.use('table',function(){
															var table = layui.table;
															table.reload('tasklist',{where : {}});														
														});	
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