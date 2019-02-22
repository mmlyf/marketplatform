<!-- 消息管理界面 -->
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
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body" style="height: 750px">
	<div class="layui-fluid">
		<div class="layui-row">
			<div class="layui-tab layui-tab-card" lay-filter="activity">
				<ul class="layui-tab-title">
					<li class="layui-this">活动</li>
					<li>历史活动</li>
				</ul>
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
									<table class="layui-hide" id="acti" lay-filter="acti"></table>
									<script type="text/html" id="toolbarDemo">
  										<div class="layui-btn-container">
   											 <button class="layui-btn layui-btn-sm" lay-event="addActi">添加</button>
  										</div>
									</script>
									<script type="text/html" id="barDemo">
  										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="detail">详情</a>
										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="change">修改</a>
									</script>
									<script>
										layui.use('table', function() {
											var table = layui.table;
											var width = window.innerWidth;
											var height = window.innerHeight;
											table.render({
												elem : '#acti',
												url : '../reqact/selunendact',
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
													field : 'actiid',
													title : 'ID',
													sort : true,
													unresize : true,
													fixed : 'left',
													width : 80
												}, {
													field : 'actname',
													title : '活动名称'
												}, {
													field : 'starttime',
													title : '开始时间',
													width : 200,
													sort : true
												}, {
													field : 'endtime',
													title : '结束时间',
													width : 200,
													sort : true
												}, {
													field : 'addtime',
													title : '添加时间',
													width : 200,
													sort : true
												}, {
													fixed : 'right',
													title : '操作',
													toolbar : '#barDemo',
													width : 200,
													align : 'center'
												} ] ],
												page : true,
												limit : 10,
												id : 'actlist',
												done : function(data) {
													$('#num').text(data.count + "条");
												}
											});
											table.on('toolbar(acti)', function(obj) {
												switch (obj.event) {
												case 'addActi':
													layer.open({
														type : 2,
														title : '新增',
														area : [ '800px', '600px' ],
														scrollbar : false,//禁止浏览器滚动
														content : 'addActivity.jsp',
														end : function() {
															table.reload('actlist', {
																where : {}
															});
														}
													});
													break;
												}
											});
											//监听行工具事件
											table.on('tool(acti)', function(obj) {
												var data = obj.data;
												if (obj.event === 'detail') {
													console.log(data.detail);
													layer.open({
														type : 2,
														title : '详情',
														area : [ '850px', '700px' ],
														maxmin : true,
														scrollbar : false,//禁止浏览器滚动
														content : data.actpage,
														end : function() {
															//执行重载
															table.reload('actlist', {
																where : {}
															});
														}
													});
												}else if(obj.event === 'change'){
													console.log(data.detail);
													layer.open({
														type : 2,
														title : '修改',
														area : [ '850px', '700px' ],
														maxmin : true,
														scrollbar : false,//禁止浏览器滚动
														content : encodeURI('actiUpdate.jsp?'+data.actiid+"?"+data.actname),
														end : function() {
															//执行重载
															table.reload('actlist', {
																where : {}
															});
															table.reload('hisactlist', {
																where : {}
															});
														}
													});
												}
											});
											var $ = layui.$, active = {
												reload : function() {
													var title = $('#actiReload').val();
													//执行重载
													table.reload('actlist', {
														where : {
															keyword : title
														}
													});
												}
											};
											$('.actiTable .layui-btn').on('click', function() {
												var type = $(this).data('type');
												active[type] ? active[type].call(this) : '';
											});
										});
									</script>
								</div>

							</fieldset>
						</div>
					</div>

					<!-- 历史活动数据（已经结束的活动数据） -->
					<div class="layui-tab-item">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="hisTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="hisactiReload"
											autocomplete="off">
									</div>
									<button class="layui-btn" data-type="hisreload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="hisnum"> </span>
									</div>
								</div>
								<div style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>历史活动列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="historyacti" lay-filter="historyacti"></table>
									<script type="text/html" id="hisbarDemo">
  										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="hisdetail">详情</a>
										<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="hischange">修改</a>
									</script>
									<script>
										layui.use('table', function() {
											var table = layui.table;
											var width = window.innerWidth;
											var height = window.innerHeight;
											table.render({
												elem : '#historyacti',
												url : '../reqact/selendact',
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
													field : 'actiid',
													title : 'ID',
													sort : true,
													unresize : true,
													fixed : 'left',
													width : 80
												}, {
													field : 'actname',
													title : '活动名称'
												}, {
													field : 'starttime',
													title : '开始时间',
													width : 200,
													sort : true
												}, {
													field : 'endtime',
													title : '结束时间',
													width : 200,
													sort : true
												}, {
													field : 'addtime',
													title : '添加时间',
													width : 200,
													sort : true
												}, {
													fixed : 'right',
													title : '操作',
													toolbar : '#hisbarDemo',
													width : 200,
													align : 'center'
												} ] ],
												page : true,
												limit : 10,
												id : 'hisactlist',
												done : function(data) {
													$('#hisnum').text(data.count + "条");
												}
											});
											table.on('toolbar(historyacti)', function(obj) {
												
											});
											//监听行工具事件
											table.on('tool(historyacti)', function(res) {
												var data = res.data;
												console.log(data);
												if (res.event === 'hisdetail') {
													console.log(data.detail);
													layer.open({
														type : 2,
														title : '详情',
														area : [ '850px', '700px' ],
														maxmin : true,
														scrollbar : false,//禁止浏览器滚动
														content : data.actpage,
														end : function() {
															//执行重载
															table.reload('hisactlist', {
																where : {}
															});
														}
													});
												}else if(res.event === 'hischange'){
													console.log(data.detail);
													layer.open({
														type : 2,
														title : '修改',
														area : [ '850px', '700px' ],
														maxmin : true,
														scrollbar : false,//禁止浏览器滚动
														content : 'actiUpdate.jsp?'+data.actiid,
														end : function() {
															//执行重载
															table.reload('hisactlist', {
																where : {}
															});
															table.reload('actlist', {
																where : {}
															});
														}
													});
												}
											});
											var $ = layui.$, active = {
												hisreload : function() {
													var title = $('#hisactiReload').val();
													//执行重载
													table.reload('hisactlist', {
														where : {
															keyword : title,

														}
													});
												}
											};
											$('.hisTable .layui-btn').on('click', function() {
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