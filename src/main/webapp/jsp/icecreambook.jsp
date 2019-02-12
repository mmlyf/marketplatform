<!-- 冰激凌预约查询 -->
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

<body class="layui-layout-body" style="height:900px">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
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
										<select name="systype" id="systype" lay-search="" lay-filter="systype">
											<option value="">网段</option>
											<option value="">全部</option>
											<option value="CBSS">CBSS</option>
											<option value="BSS">BSS</option>
										</select>
									</div>
									<div class="layui-input-inline">
										<select name="qudao" id="qudao" lay-search=""
											lay-verify="required" lay-filter="qudao">
											<option value="">渠道</option>
											<option value="">全部</option>
											<option value="1">上行</option>
											<option value="0">支付宝</option>
										</select>
									</div>
									<div class="layui-input-inline">
										<select name="ifdx" id="ifdx" lay-search="" lay-filter="ifdx">
											<option value="">是否低消</option>
											<option value="">全部</option>
											<option value="0">是</option>
											<option value="1">否</option>
										</select>
									</div>
									<div class="layui-input-inline">
										<select name="ifrh" id="ifrh" lay-search="" lay-filter="ifrh">
											<option value="">是否融合</option>
											<option value="">全部</option>
											<option value="0">是</option>
											<option value="1">否</option>
										</select>
									</div>
									<div class="layui-input-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="listnum">
										</span>
									</div>
									
									</form>
									<button class="layui-btn" data-type="allreload">搜索</button>
									<button class="layui-btn" data-type="output">导出</button>
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
								<legend>冰激凌预约列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="busimodel" lay-filter="busimodel"></table>

									<script type="text/html" id="toolbarDemo">
 									 <div class="layui-btn-container">
  										</div>
									</script>
									<script type="text/html" id="barDemo">
									
									<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
									</script>
									<script src="http://code.jquery.com/jquery-1.8.0.min.js"
										type="text/javascript"></script>
									<link rel="stylesheet" href="../layui/css/layui.css"
										media="all">
									<script type="text/javascript" src="../layui/layui.js"></script>
									</head>
									<script>
										layui.use('table',function() {
															var table = layui.table;
															table.render({
																		elem : '#busimodel',
																		url : '../buscontro/icebook',
																		cellMinWidth : 80,
																		width:window.innerWidth*0.94,
																		height:550,
																		toolbar:'toolbarDemo',
																		title : '冰激凌预约列表',
																		cols : [[ 
																				{
																					type : 'checkbox',
																					fixed : 'left'
																				},
																				{
																					field : 'id',
																					title : '编号',
																					sort : true,
																					unresize : true,
																					fixed : 'left',
																					align : 'center'
																				},
																				{
																					field : 'dn',
																					title : '号码',
																					align : 'center',
																					width:100
																				},
																				{
																					field : 'firp',
																					title : '产品',
																					align : 'center',
																					width:100
																				},
																				{
																					field : 'firdw',
																					title : '档位',
																					sort : true,
																					align : 'center',
																					width:80
																				},
																				{
																					field : 'city',
																					title : '地市',
																					width:170
																				},
																				{
																					field : 'ap',
																					title : '平均消费',
																					sort : true,
																					width : 80,
																					align : 'center'
																				},
																				{
																					field : 'systype',
																					title : '网段',
																					align : 'center'
																				},
																				{
																					field : 'inn',
																					title : '套餐',
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'ifdx',
																					title : '是否低消',
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'ifrh',
																					title : '是否融合',
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'rhlx',
																					title : '融合类型',
																					width : 120,
																					align : 'center'
																				},
																				{
																					field : 'addtime',
																					title : '时间',
																					width : 150,
																					align : 'center'
																				},
																				{
																					field : 'qudao',
																					title : '渠道',
																					width : 80
																				},
																				{
																					fixed : 'right',
																					title : '操作',
																					toolbar : '#barDemo',
																					width : 180,
																					align : 'center'
																				} ]] ,
																		page : true,
																		limit:10,
																		id : 'icelist',
																		done:function(data){
																			$('#listnum').text(data.count+"条");
																		}
																	});

															table.on('toolbar(busimodel)',function(obj){
																
															});

															//监听行工具事件
															table.on('tool(busimodel)',
																			function(
																					obj) {
																				var data = obj.data;
																				//console.log(obj)
																				if(obj.event === 'detail'){
																					var str = "{<br>id:"
																						+ data.id
																						+ "<br>号码:"
																						+ data.dn
																						+ "<br>产品名称:"
																						+ data.firp
																						+ "<br>档位:"
																						+ data.firdw
																						+ "<br>地市:"
																						+ data.city
																						+ "<br>平均:"
																						+ data.ap
																						+ "<br>网段:"
																						+ data.systype
																						+ "<br>套餐:"
																						+ data.inn
																						+ "<br>低消:"
																						+ data.ifdx
																						+ "<br>融合:"
																						+ data.ifrh
																						+ "<br>类型:"
																						+ data.rhlx
																						+ "<br>时间:"
																						+ data.addtime
																						+ "<br>渠道:"
																						+ data.qudao
																						+ "}";
																				layer.alert('当前数据是：'+ str)
																				}
																			});

															var $ = layui.$, active = {
																allreload : function() {
																	var dn = $('#phone').val();
																	 var date_star = $('#date_star').val();
																	var date_end = $('#date_end').val();
																	var systype = $('#systype').val();
																	var ifdx = $('#ifdx').val();
																	var qudao = $('#qudao').val();
																	var ifrh = $('#ifrh').val(); 
																	//执行重载
																	table.reload('icelist',{
																		where:{
																			dn : dn,
																			date_star : date_star,
																			date_end : date_end,
																			systype : systype,
																			ifdx : ifdx,
																			qudao : qudao,
																			ifrh : ifrh 
																		}
																		});
																},
															output : function(){
																var dn = $('#phone').val();
															 	var date_star = $('#date_star').val();
																var date_end = $('#date_end').val();
																var systype = $('#systype').val();
																var ifdx = $('#ifdx').val();
																var qudao = $('#qudao').val();
																var ifrh = $('#ifrh').val();
																var index = layer.load(1);
																$.ajax({
																	url:'../buscontro/outputice',
																	type:'POST',
																	dataType:'json',
																	data:{
																		dn : dn,
																		date_star : date_star,
																		date_end : date_end,
																		systype : systype,
																		ifdx : ifdx,
																		qudao : qudao,
																		ifrh : ifrh
																	},
																	success:function(res){
																		if(res.code==0){
																			layer.close(index);
																			window.location.href="http://221.192.138.29:8089/HSDT_Market_Platform/buscontro/download?filepath="+res.path;
																			//window.location.href = "http://localhost:8085/HSDT_Market_Platform/buscontro/download?filepath="+res.path;
																		}else{
																			layer.closeAll();
																			layer.msg('下载失败');
																		}
																	}
																});
															}
															};
															$('.selectorder .layui-btn').on('click',
																function() {
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