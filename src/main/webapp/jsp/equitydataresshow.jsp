<!-- 抽奖权益发放成果展示 -->
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
<body class="layui-layout-body" style="height: 750px">
	<div class="layui-fluid">
		<div class="layui-row">
			<div class="layui-tab layui-tab-card" lay-filter="activity">
				<div class="layui-tab-content">
					<!-- 正在进行的活动数据 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="actiTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="actiReload"
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
								<legend>活动列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="equityres" lay-filter="equityres"></table>
									<script type="text/html" id="toolbarDemo">
  										<div class="layui-btn-container">
   											
  										</div>
									</script>
									<script type="text/html" id="barDemo">
  										
									</script>
									<script>
										layui.use(['table','layer'], function() {
											var table = layui.table,layer = layui.layer;
											var width = window.innerWidth;
											var height = window.innerHeight;
											var index = layer.load(2);
											table.render({
												elem : '#equityres',
												url : '../equitydata/selectdataresult',
												cellMinWidth : 80,
												width : window.innerWidth * 0.94,
												height : 550,
												toolbar : '#toolbarDemo',
												loading : true,
												title : '消息列表',
												cols : [ [ {
													type : 'checkbox',
													fixed : 'left'
												}, {
													field : 'id',
													title : 'ID',
													sort : true,
													unresize : true,
													fixed : 'left',
													width : 80
												}, {
													field : 'qy_id',
													title : '权益编号'
												}, {
													field : 'dn',
													title : '手机号码',
													sort : true
												}, {
													field : 'qy_name',
													title : '权益名称',
													sort : true
												}, {
													field : 'source',
													title : '渠道',
													sort : true
												},{
													field : 'zs_time',
													title : '赠送时间',
													sort : true
												}, {
													field : 'addtime',
													title : '抽奖时间',
													sort : true
												}, {
													field : 'zs_state',
													title : '赠送状态',
													sort : true
												} ] ],
												page : true,
												limit : 10,
												id : 'equityreslist',
												done : function(data) {
													layer.close(index);
													$('#num').text(data.count + "条");
												}
											});
											table.on('toolbar(equityreslist)', function(obj) {
												
											});
											//监听行工具事件
											table.on('tool(equityreslist)', function(obj) {
												
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
					element.tabAdd('activity', {
						title : '新选项' + (Math.random() * 1000 | 0) //用于演示
						,
						content : '内容' + (Math.random() * 1000 | 0),
						id : new Date().getTime()
					//实际使用一般是规定好的id，这里以时间戳模拟下
					})
				},
				tabDelete : function(othis) {
					//删除指定Tab项
					element.tabDelete('activity', '44'); //删除：“商品管理”

					othis.addClass('layui-btn-disabled');
				},
				tabChange : function() {
					//切换到指定Tab项
					element.tabChange('activity', '22'); //切换到：用户管理
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