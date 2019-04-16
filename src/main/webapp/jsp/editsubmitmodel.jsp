<!-- 我的提交中模型维度的任务修改 -->
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 数据维度筛选 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated ng-scope" style="height:1000px">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>添加筛选规则</legend>
							</fieldset>
							<form class="layui-form layui-form-pane" id="modelform" method="post" action="../smscreate/modelIn"
								enctype="multipart/form-data" >
								<input type="hidden" id="id" name="id"/>
								<div class="layui-form-item" id="city">
									<label class="layui-form-label">城市</label>
									<div class="layui-input-block">
										<select name="city">
											<option value="all" selected >全部</option>
											<option value="唐山">唐山</option>
											<option value="张家口">张家口</option>
											<option value="邯郸">邯郸</option>
											<option value="石家庄">石家庄</option>
											<option value="承德">承德</option>
											<option value="廊坊">廊坊</option>
											<option value="沧州">沧州</option>
											<option value="衡水">衡水</option>
											<option value="邢台">邢台</option>
											<option value="秦皇岛">秦皇岛</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">产品类型</label>
									<div class="layui-input-block">
										<select name="prelx" id="prolx" lay-filter="prolx">
											
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">产品</label>
									<div class="layui-input-block">
										<select name="product" id="prod" lay-filter="prod">
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">档位</label>
									<div class="layui-input-block">
										<select name="dangw" id="dangw" lay-filter="dangw">
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">是否融合</label>
									<div class="layui-input-block">
										<select name="ifrh">
											<option value="all" selected="">全部</option>
											<option value="dsj_dx_all" >是</option>
											<option value="dsj_ice_all" >否</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">是否低消</label>
									<div class="layui-input-block">
										<select name="ifdx">
											<option value="all" selected="">全部</option>
											<option value="是">是</option>
											<option value="否" >否</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">网络类型</label>
									<div class="layui-input-block">
										<select name="sourceType">
											<option value="all" selected="">全部</option>
											<option value="CBSS">CBSS</option>
											<option value="BSS" >BSS</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">短信语</label>
									<div class="layui-input-block">
										<select name="migId" id="smsm" lay-filter="smsm" required lay-verify="required">
										</select>
									</div>
								</div>
									<div class="layui-form-item" id="timechoice">
									<label class="layui-form-label">营销时间</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="test"
											name="secTime" placeholder="yyyy-MM-dd"
											autocomplete="off" />
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label>
									<div class="layui-input-block">
										<input type="checkbox" name="isitmework"
											lay-filter="switchwtime" lay-skin="switch" lay-text="ON|OFF">
									</div>
								</div>
								
								<div class="layui-form-item layui-hide" id="timeIN">
									<label class="layui-form-label">时间选择</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="test2"
											name="worktime" placeholder="yyyy-MM-dd HH:mm:ss"
											autocomplete="off" />
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="checkbox" name="isdelblack" title="去黑名单"/>
									</div> 
									
								</div>
								
								<div class="layui-form-item" >
									<label class="layui-form-label">重复运营</label>
									<div class="layui-input-inline">
										<input type="checkbox" name="isdeldays"
											lay-filter="swithdel1" lay-skin="switch" lay-text="ON|OFF" >
									</div>
									<div class="layui-input-inline layui-hide" id="deldays1">
										<input type="text" class="layui-input"  name="deldays" autocomplete = "false"/>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-block">
										<select name="reUser" lay-verify="required">
											<option value=""></option>
											<option value="裴秋婷">裴秋婷</option>
										</select>
									</div>
								</div>
								<input type="hidden" value="<%=session.getAttribute("realname")%>"
									name="rdUser">

								<div class="layui-form-item">
									<button class="layui-btn" type="button" id="upmodel">提交修改</button>
									<button class="layui-btn" type="reset" lay-filter="demo2">
										重置</button>
										<button class="layui-btn" type="button" id="cancle">取消</button>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../layui/layui.js"></script>
	<script>
	
	$(function(){
		 var str=window.location.href;
		    var objstr = decodeURI(str.split('?')[1]);
		    var data = objstr.split(",");
		    $("#id").attr("value",data[0]);
		    $("#upmodel").click(function(){
		    		$.ajax({
		    			url:'../smsupdate/upsmsmodel',
		    			type:'POST',
		    			dataType:'json',
		    			data:$("#modelform").serialize(),
		    			success:function(result){
		    				if(result.code==1){
		    					var index = parent.layer.getFrameIndex(window.name);
		    					layer.msg("修改成功");
		    					
		    					parent.layer.close(index);
		    				}
		    			}
		    		});
		    });
		    $("#cancle").click(function(){
		    	var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
		    });
		    
	});
		/************通过ajax请求数据，填充产品类型中的数据**/
	
		$.ajax({
			url : '../requestdata/selectprod',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				var json = eval(result);
				$.each(json, function(index, item) {
					var jsonvalue = eval(item);
					var prolx = $("#prolx");
					var deptOption = $("<option></option>");
					prolx.append(deptOption);
					$.each(jsonvalue, function(indexv, itemv) {
						var deptOption = $("<option value='"+itemv.lxid+"'>"
								+ itemv.lxname + "</option>");
						prolx.append(deptOption);
					});
				});
			}
		});

		/*********************************请求msg的值**************************************/
		$.ajax({
			url : '../requestdata/selectmsg',
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				var json = eval(result);
				$.each(json, function(index, item) {
					var jsonvalue = eval(item);
					var smsm = $('#smsm');
					smsm.empty();
					var deptOption = $("<option></option>");
					smsm.append(deptOption);
					$.each(jsonvalue, function(indexv, itemv) {
						for (var i = 0; i < prod.length; i++) {
							if (prod[i].text == itemv.msgtitle
									|| prod[i].value == itemv.msgid) {
								return false;
							}
						}
						var deptOption = $("<option value='"+itemv.msgid+"'>"
								+ itemv.msgtitle + "</option>");
						smsm.append(deptOption);
					});
				});
			}
		});
		$.ajax({
			url : '../requestdata/selectmsg',
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				var json = eval(result);
				$.each(json, function(index, item) {
					var jsonvalue = eval(item);
					var filesmsm = $('#filesmsm');
					filesmsm.empty();
					var deptOption = $("<option></option>");
					filesmsm.append(deptOption);
					$.each(jsonvalue, function(indexv, itemv) {
						for (var i = 0; i < prod.length; i++) {
							if (prod[i].text == itemv.msgtitle
									|| prod[i].value == itemv.msgid) {
								return false;
							}
						}
						var deptOption = $("<option value='"+itemv.msgid+"'>"
								+ itemv.msgtitle + "</option>");
						filesmsm.append(deptOption);
					});
				});
			}
		});

		

		/*************************监控form表单中的一些事件和动态效果************************************/
		layui
				.use(
						[ 'form', 'laypage', 'layer', 'upload' ],
						function() {
							var form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload;
							//监控switch开发并实现时间选框的显示和隐藏
							form.on('switch(switchTest)', function(data) {
								layer.msg('定时任务' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if (this.checked) {
									$('#timechoice').removeClass('layui-hide');
								} else {
									$('#timechoice').addClass('layui-hide');
								}
							});
							
							form.on('switch(switchwtime)',function(data){
								layer.msg('维度筛选定时任务' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#timeIN').removeClass('layui-hide');
								}else{
									$('#timeIN').addClass('layui-hide');
								}
							});
							
							form.on('switch(switchdel)',function(data){
								layer.msg('避免重复运营' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#deldays').removeClass('layui-hide');
								}else{
									$('#deldays').addClass('layui-hide');
								}
							});
							form.on('switch(swithdel1)',function(data){
								layer.msg('避免重复运营' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#deldays1').removeClass('layui-hide');
								}else{
									$('#deldays1').addClass('layui-hide');
								}
							});
							//监控下拉选框的时间
							form
									.on(
											'select(prolx)',
											function(data) {
												$
														.ajax({
															url : '../requestdata/selectprod',
															type : 'post',
															dataType : 'json',
															data : {
																prodlxid : data.value
															},
															success : function(
																	result) {
																var json = eval(result);
																$
																		.each(
																				json,
																				function(
																						index,
																						item) {
																					var jsonvalue = eval(item);
																					var prod = $("#prod");
																					prod
																							.empty();
																					prod
																					.append("<option value='all' selected>全部</option>");
																					
																					$
																							.each(
																									jsonvalue,
																									function(
																											index,
																											item) {
																										for (var i = 0; i < prod.length; i++) {
																											if (prod[i].text == item.proname
																													|| prod[i].value == item.proid) {
																												return false;
																											}
																										}
																											var deptOption = $("<option value='"+item.proid+"'>"
																													+ item.proname
																													+ "</option>");
																										
																										prod
																												.append(deptOption);
																									});
																				});
																form
																		.render('select');
															}
														});

											});

							form
									.on(
											'select(prod)',
											function(data) {
												$
														.ajax({
															url : '../requestdata/selectprod',
															type : 'post',
															dataType : 'json',
															data : {
																prodid : data.value
															},
															success : function(
																	result) {
																var json = eval(result);
																$
																		.each(
																				json,
																				function(
																						index,
																						item) {
																					var jsonvalue = eval(item);
																					var prod = $("#dangw");
																					prod
																							.empty();
																					prod
																					.append("<option value='all' selected>全部</option>");
																					$
																							.each(
																									jsonvalue,
																									function(
																											index,
																											item) {
																										for (var i = 0; i < prod.length; i++) {
																											if (prod[i].text == item.dw
																													|| prod[i].value == item.dw) {
																												return false;
																											}
																										}
																										var deptOption = $("<option value='"+item.dw+"'>"
																												+ item.dw
																												+ "</option>");
																										prod
																												.append(deptOption);
																									});
																				});
																form
																		.render('select');
															}
														});
											});
						});

		//实现时间的选框的效果
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem : '#test1' //指定元素
				,
				position : 'abolute',
				type : 'datetime'
			});
			laydate.render({
				elem:'#test',
				position:'ablute',
				type:'datetime'
			});
			laydate.render({
				elem:'#test2',
				position:'ablute',
				type:'datetime'
			});
		});
	</script>
</body>
</html>