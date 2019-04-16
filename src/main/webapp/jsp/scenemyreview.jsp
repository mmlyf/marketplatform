<!-- 我的审核页面展示 -->
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
								</div>
								<div style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>群组列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="reviewtable" lay-filter="reviewtable"></table>
									
									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									
										<a class="layui-btn layui-btn-xs" id="confirmsend" lay-event="review">审核</a>
									
									</script>
									<script>
										layui.use('table',function() {
															var table = layui.table;
															table.render({
																		elem : '#reviewtable',
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
																			keytype:'review_man'
																		},
																		id : 'scenelist',
																		done:function(data){
																			$('#groupnum').text(data.count+"条");
																		}
																	});

															table.on('toolbar(reviewtable)',function(obj){
																
															});

															//监听行工具事件
															table.on('tool(reviewtable)',function(obj) {
																var data = obj.data;
																if (obj.event === 'review') {
																	if(data.state=='审核通过'){
																		layer.msg('当前已审核，请不要重复审核');
																	}else if(data.state=='审核未通过'){
																		layer.msg('当前审核未通过，请联系提交人员及时修改，并重新提交');
																	}else if(data.state=='未审核'){
																		layer.open({
																			type:2,
																			title:'审批',
																			maxmin:true,
																			area:['800px','800px'],
																			content: encodeURI('sceneeditreview.jsp?'+data.label
																					+";"+data.label_opera
																					+";"+data.data_count
																					+";"+data.isTimework
																					+";"+data.timework
																					+";"+data.isdelblack
																					+";"+data.isdeldays
																					+";"+data.deldays
																					+";"+data.id
																					+";"+data.addman
																					+";"+data.misid
																					+";"+data.miscontent
																					+";"+data.review_man),
																			end:function(){
																				//执行重载
																				table.reload('scenelist',{where : {}});
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
	</script>
</body>
</html>