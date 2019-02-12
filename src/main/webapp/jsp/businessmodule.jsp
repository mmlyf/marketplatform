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
				<ul class="layui-tab-title">
					<li class="layui-this">订单查询</li>
					<li>号码查询</li>
				</ul>

				<div class="layui-tab-content">
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="selectorder">
									<form action="" class="layui-form" class="layui-inline">
										<div class="layui-input-inline">
											<input type="text" name="key" id="phone" class="layui-input"
												placeholder="请输入手机号">
										</div>
										<div class="layui-input-inline">
											<input type="text" name="date_star" id="date_star"
												lay-verify="date" placeholder="开始日期" class="layui-input">
										</div>
										<div class="layui-input-inline">
											<input type="text" name="date_end" id="date_end"
												lay-verify="date" placeholder="结束日期" class="layui-input">
										</div>
										<div class="layui-input-inline">
											<select name="ddlx" id="ddlx" lay-search="" lay-filter="ddlx">
												<option value="-1">订单状态</option>
												<option value="-1">全部</option>
												<option value="0">成功</option>
												<option value="">不成功</option>
											</select>

										</div>
										<div class="layui-input-inline">
											<select name="qudao" id="qudao" lay-search=""
												lay-verify="required" lay-filter="qudao">
												<option value="">渠道</option>
												<option value="">全部</option>
												<option value="1">上行</option>
												<option value="2">支付宝</option>
												<option value="3">连接</option>
												<option value="4">补订</option>
												<option value="5">退订</option>
											</select>
										</div>
										<div class="layui-input-inline">
											<select name="yewu" id="yewu" lay-search="" lay-filter="yewu">
												<option value="">业务</option>
												<option value="">全部</option>
												<option value="1">流量包</option>
												<option value="2">低消</option>
											</select>

										</div>
										<div class="layui-input-inline">
											<label class="layui-form-label"
												style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
												class="num_peo" id="listnum"> </span>
										</div>

									</form>
									<button class="layui-btn" data-type="allreload">搜索</button>
								</div>
								<script type="text/javascript" src="../layui/layui.js"></script>
								<script type="text/javascript">
									//实现时间的选框的效果
									layui.use('laydate', function() {
										var laydate = layui.laydate;
										//执行一个laydate实例
										laydate.render({
											elem : '#date_star' //指定元素
											,
											position : 'abolute',
											type : 'datetime'
										});
										laydate.render({
											elem : '#date_end',
											position : 'ablute',
											type : 'datetime'
										});
									});
								</script>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>订单列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="busimodel" lay-filter="busimodel"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									<%String permision = (String) session.getAttribute("permision");
			if (permision.indexOf("1") != -1 || permision.indexOf("29") != -1) {%>
 									 	<a class="layui-btn layui-btn-xs" lay-event="reorder">补订</a>
										<a class="layui-btn layui-btn-xs" lay-event="outorder">退订</a>
									<%}%>
									<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									</script>

									<script>
										layui.use('table', function() {
											var table = layui.table;
											table.render({
												elem : '#busimodel',
												url : '../buscontro/selectbypage',
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
													title : '编号',
													sort : true,
													unresize : true,
													fixed : 'left',
													align : 'center'
												}, {
													field : 'dn',
													title : '号码',
													align : 'center',
													width : 130,
													templet : '#dntpl'
												}, {
													field : 'productname',
													title : '产品名称',
													align : 'center',
													width : 310
												}, {
													field : 'price',
													title : '价格',
													sort : true,
													align : 'center',
													width : 80
												}, {
													field : 'ordertime',
													title : '订购时间',
													width : 170
												}, {
													field : 'state',
													title : '状态',
													sort : true,
													width : 80,
													align : 'center'
												}, {
													field : 'agw',
													title : 'AGW',
													width : 200,
													align : 'center'
												}, {
													field : 'source',
													title : '渠道',
													width : 80
												}, {
													fixed : 'right',
													title : '操作',
													toolbar : '#barDemo',
													width : 180,
													align : 'center'
												} ] ],
												page : true,
												limit : 10,
												id : 'tasklist',
												done : function(data) {
													$('#listnum').text(data.count + "条");
												}
											});

											table.on('toolbar(busimodel)', function(obj) {

											});

											//监听行工具事件
											table.on('tool(busimodel)', function(obj) {
												var data = obj.data;
												//console.log(obj)
												if (obj.event === 'reorder') {
													if (data.state == 0) {
														layer.msg("当前状态已订购，无法补订");
													} else {
														layer.confirm('确定补订当前订单' + data.dn, function(index) {
															$.ajax({
																url : '../buscontro/orderud',
																dataType : 'json',
																data : {
																	id : data.id,
																	phonenum : data.dn,
																	actioncode : 0
																},
																success : function(result) {
																	if (result.code == 0) {
																		layer.msg("补订成功，后台将会处理。请稍等一段时间");
																	} else {
																		layer.msg("补订agw保存失败。")
																	}
																}
															});
														});
													}
												} else if (obj.event === 'outorder') {
													if (data.state != 0) {
														layer.msg("当前状态未订购，无法退订");
													} else {
														layer.confirm('确定退订号码' + data.dn + "订单", function(index) {
															$.ajax({
																url : '../buscontro/orderud',
																dataType : 'json',
																data : {
																	id : data.id,
																	phonenum : data.dn,
																	actioncode : 1
																},
																success : function(result) {
																	if (result.code == 0) {
																		layer.msg("退订成功，后台将会处理。请稍等一段时间");
																	} else {
																		layer.msg("退订的agw值保存失败");
																	}
																}
															});
														});
													}
												} else if (obj.event === 'detail') {
													var str = "{<br>id:" + data.id + "<br>号码:" + data.dn + "<br>产品名称:" + data.productname + "<br>价格:" + data.price + "<br>订购时间:"
															+ data.ordertime + "<br>状态:" + data.state + "<br>AGW:" + data.agw + "<br>渠道:" + data.source + "}";
													layer.alert('当前数据是：' + str)
												}
											});

											var $ = layui.$, active = {
												allreload : function() {
													var dn = $('#phone').val();
													var date_star = $('#date_star').val();
													var date_end = $('#date_end').val();
													var ddlx = $('#ddlx').val();
													var cplx = $('#cplx').val();
													var qudao = $('#qudao').val();
													var yewu = $('#yewu').val();
													//执行重载
													table.reload('tasklist', {
														where : {
															dn : dn,
															date_star : date_star,
															date_end : date_end,
															ddlx : ddlx,
															cplx : cplx,
															qudao : qudao,
															yewu : yewu
														}
													});
												}
											};
											$('.selectorder .layui-btn').on('click', function() {
												var type = $(this).data('type');
												active[type] ? active[type].call(this) : '';
											});
										});
									</script>
									<script type="text/javascript" src="../layer/layer.js"></script>
									<script type="text/html" id="dntpl">
									 	<a class="layui-table-link" href="javascript:dnFun({{d.dn}})">{{d.dn}}</a>
									</script>
									<script type="text/javascript">
										function dnFun(dn) {
											layer.open({
												type : 2,
												shade:[0.8, '#393D49'],
												shadeClose:true,
												title : '详情',
												area : [ '800px', '700px' ],
												scrollbar : false,//禁止浏览器滚动
												content : 'busmoduledndetail.jsp?' + dn
											});
										}
									</script>
								</div>
							</fieldset>
						</div>
					</div>
					<!-- 通过号码查询数据 -->
					<div class="layui-tab-item">
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="modelTable">
									搜索：
									<div class="layui-inline">
										<input class="layui-input" name="id" id="phonenum"
											autocomplete="off" placeholder="请输入号码">
									</div>
									<button class="layui-btn" data-type="reload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="dnselectnum"> </span>
									</div>
								</div>
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>订单列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="dnselect" lay-filter="dnselect"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
 									 	<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									</script>
									<script>
										layui.use('table', function() {
											var table = layui.table;

											table.render({
												elem : '#dnselect',
												url : '../buscontro/selectdndetails',
												cellMinWidth : 80,
												width : window.innerWidth * 0.94,
												toolbar : 'toolbarDemo',
												title : '群组列表',
												cols : [ [ {
													type : 'checkbox',
													fixed : 'left'
												}, {
													field : 'id',
													title : '编号',
													sort : true,
													unresize : true,
													fixed : 'left',
													align : 'center'
												}, {
													field : 'dn',
													title : '号码',
													align : 'center',
													width : 130
												}, {
													field : 'ap',
													title : '平均',
													align : 'center',
													width : 80
												}, {
													field : 'sys',
													title : '网络类型',
													align : 'center',
													width : 120
												}, {
													field : 'inner',
													title : '套餐',
													align : 'center',
													width : 230
												}, {
													field : 'dxfirp',
													title : '产品',
													align : 'center',
													width : 160
												}, {
													field : 'dxfirdw',
													title : '档位',
													align : 'center',
													width : 80
												}, {
													field : 'ifdx',
													title : '低消',
													align : 'center',
													width : 100
												}, {
													field : 'productname',
													title : '产品名称',
													align : 'center',
													width : 310
												}, {
													field : 'price',
													title : '价格',
													sort : true,
													align : 'center',
													width : 80
												}, {
													field : 'ordertime',
													title : '订购时间',
													width : 170
												}, {
													field : 'state',
													title : '状态',
													sort : true,
													width : 80,
													align : 'center'
												}, {
													field : 'agw',
													title : 'AGW',
													width : 200,
													align : 'center'
												}, {
													field : 'source',
													title : '渠道',
													width : 80
												}, {
													field : 'city',
													title : '城市',
													width : 100,
													fixed : 'right'
												} ] ],
												page : true,
												limit : 10,
												where : {
													phonenum : ''
												},
												id : 'dnselectlist',
												done : function(data) {
													$('#dnselectnum').text(data.count + "条");
												}
											});

											table.on('toolbar(dnselect)', function(obj) {
											});
											var $ = layui.$, active = {
												reload : function() {
													var dn = $('#phonenum').val();

													//执行重载
													table.reload('dnselectlist', {
														where : {
															phonenum : dn
														}
													});
												}
											};
											$('.modelTable .layui-btn').on('click', function() {
												var type = $(this).data('type');
												layer.msg(type);
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