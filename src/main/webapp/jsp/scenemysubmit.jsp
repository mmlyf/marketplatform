<!-- 我的提交数据展示页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title></title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script> 
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body"  style="height:800px">
	<div class="layui-fluid" >
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				
				<div class="layui-tab-content" >
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show" >
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="demoTable">
									列表记录数：
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="groupnum">
										</span>
									</div>
									<div class="layui-inline" id="sendop"
									style="float: right;">
									<button type="button" class="layui-btn" id="stop">群发暂停</button>
									<button type="button" class="layui-btn layui-hide" id="start">群发续发</button>
									<button type="button" class="layui-btn tzqf" id="end">群发停止</button>
								</div>
								</div>
								<div style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>群组列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="test" lay-filter="test"></table>
									
									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									<%
										String permission = (String)session.getAttribute("permision");
									if(permission.indexOf("1")!=-1||permission.indexOf("25")!=-1){
									%>
										<a class="layui-btn layui-btn-xs" id="confirmsend" lay-event="send">发送</a>
									<%}
										
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
															table.render({
																		elem : '#test',
																		url : '../scenejob/selectscenejob',
																		cellMinWidth : 80,
																		width:window.innerWidth*0.93,
																		height:550,
																		toolbar:'toolbarDemo',
																		title : '群组列表',
																		loading:true,
																		cols : [[ 
																				{
																					type : 'checkbox',
																					fixed : 'left',
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
																					field : 'label',
																					title : '标签',
																					align : 'center',
																					width : 120
																				},
																				{
																					field : 'label_opera',
																					title : '操作',
																					align : 'center'
																				},
																				{
																					field : 'data_count',
																					title : '数量',
																					sort : true,
																					align : 'center'
																				},
																				{
																					field : 'isTimework',
																					title : '定时任务',
																					align : 'center',
																					width : 100
																				},
																				{
																					field : 'timework',
																					title : '任务时间',
																					align : 'center',
																					width : 120
																				},
																				{
																					field : 'isdelblack',
																					title : '去黑名单',
																					align : 'center',
																					width : 100
																				},
																				{
																					field : 'isdeldays',
																					title : '重复营销',
																					align : 'center',
																					width : 100
																				},
																				{
																					field : 'deldays',
																					title : '天数',
																					align : 'center'
																				},
																				{
																					field : 'misid',
																					title : '消息',
																					align : 'center',
																					width : 120
																				},
																				{
																					field : 'last_opera',
																					title : '最后操作',
																					align : 'center',
																					width : 120
																				},
																				{
																					field : 'review_man',
																					title : '审核',
																					align : 'center'
																				},
																				{
																					field : 'addman',
																					title : '创建人'
																				},
																				{
																					field : 'state',
																					title : '状态',
																					align : 'center',
																					width : 100
																				},
																				{
																					field : 'addtime',
																					title : '创建时间',
																					sort : true,
																					width : 120,
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
																		where:{
																			keyword:encodeURI('<%=session.getAttribute("realname") %>'),
																			keytype:'add_man'
																		},
																		id : 'tasklist',
																		done:function(data){
																			$('#groupnum').text(data.count+"条");
																		}
																	});

															table.on('toolbar(test)',function(obj){
																
															});

															//监听行工具事件
															table.on('tool(test)',function(obj) {
																				var data = obj.data;
																				//console.log(obj)
																				if(obj.event === 'send'){
																					if(data.state=='审核通过'){
																						layer.confirm("你确定发送当前的数据:<br>id:"
																								+ data.id
																								+ "<br>标签内容:"
																								+ data.label
																								+ "<br>标签操作:"
																								+ data.label_opera
																								+ "<br>用户数:"
																								+ data.data_count
																								+ "<br>创建人:"
																								+ data.addman
																								+ "<br>创建时间:"
																								+ data.addtime
																								+ "<br>最后操作者:"
																								+ data.last_opera
																								+ "<br>去除黑名单:"
																								+ data.isdelblack
																								+"<br>去除重复营销:"
																								+ data.isdeldays
																								+ "<br>定时任务:"
																								+ data.istimework
																								+ "<br>任务时间:"
																								+ data.worktime
																								+"<br>消息标题："
																								+ data.misid
																								+"<br>消息内容："
																								+ data.miscontent
																								+ "<br>审核人:"
																								+ data.review_man
																								+ "<br>状态:"
																								+ data.state,function(index){
																									$.ajax({
																										url:'../scenejob/sendscenejob',
																										type:'post',
																										dataType:'json',
																										data:{
																											jobid:data.id,
																											worker:'<%=session.getAttribute("realname") %>'
																										},
																										success:function(result){
																											if(result.code==0){
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
																							url:'../scenejob/deletescenejob',
																							type:'post',
																							dataType:'json',
																							data:{
																								jobid:data.id
																							},
																							success:function(result){
																								if(result.code==0){
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
															
														});
										$("#stop").click(function(){
											$.ajax({
												url:'../scenejob/stopsendjob',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==0){
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
												url:'../scenejob/startsendjob',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==0){
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
												url:'../scenejob/endsendjob',
												type:'post',
												dataType:'json',
												success:function(result){
													if(result.code==0){
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
				</div>
			</div>
		</div>
	</div>
	
	<script>

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