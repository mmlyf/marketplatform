<!-- 我的提交中文件导入任务修改 -->
<%@page import="com.mtpt.service.impl.TBProdLxService"%>
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
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show" style="height: 1000px;" >
						<div class="admin-main fadeInUp animated ng-scope">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>文件导入任务修改</legend>
							</fieldset>
							<form class="layui-form layui-form-pane" method="post" id="upform" action="" enctype="multipart/form-data">
							<input type="hidden" id="id" name="id"/>
								<div class="layui-form-item">
									<label class="layui-form-label">用户群名称</label>
									<div class="layui-input-block">
										<input type="text" name="groupname" id="groupname" required lay-verify="required"
											placeholder="请输入名称" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">文件名称</label>
									<div class="layui-input-block" style="position: relative;">
										<label class="layui-form-label" id="filename" style="width:500px"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label>
									<div class="layui-input-block">
										<input type="checkbox" name="istimework"
											lay-filter="switchTest" lay-skin="switch" lay-text="ON|OFF">
									</div>
								</div>
								
								<div class="layui-form-item layui-hide" id="timechoice">
									<label class="layui-form-label">时间选择</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="test1"
											name="worktime" placeholder="yyyy-MM-dd hh:mm:ss"
											autocomplete="off" />
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">短信语</label>
									<div class="layui-input-block">
										<select name="migId" id="smsm" lay-filter="smsm" required lay-verify="required">
										</select>
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="checkbox" name="isdelblack" title="去黑名单"/>
									</div> 
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">重复运营</label>
									<div class="layui-input-inline">
										<input type="checkbox" name="isdeldays"
											lay-filter="switchdel" lay-skin="switch" lay-text="ON|OFF">
									</div>
									<div class="layui-input-inline layui-hide" id="deldays">
										<input type="text" class="layui-input"  name="deldays" autocomplete="false"/>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-block">
										<select name="reviewman" lay-verify="required">
											<option value=""></option>
											<option value="裴秋婷">裴秋婷</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item layui-hide">
									<label class="layui-form-label"></label>
									<div class="layui-input-block">
										<input type="hidden" value="<%=session.getAttribute("realname") %>"
											name="addman">
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" id="upsub">提交修改</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
										<button type="button" id="cancle" class="layui-btn">取消</button>
									</div>
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
	    $("#groupname").attr("value",data[0]);
	    $("#id").attr("value",data[1]);
	    $("#filename").text(data[2]);
	    $("#upsub").click(function(){
	    		$.ajax({
	    			url:'../smsupdate/upsmsfile',
	    			type:'POST',
	    			dataType:'json',
	    			data:$("#upform").serialize(),
	    			success:function(result){
	    				if(result.code==1){
	    					layer.msg('修改成功',{end:function(){
		    					var index = parent.layer.getFrameIndex(window.name);
		    					parent.layer.close(index);
	    						
	    					}});
	    				}else{
	    					layer.msg('修改失败');
	    				}
	    			}
	    		});
	    });
	    
	    $('#cancle').click(function(){
	    	var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
	    });
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
					var deptOption = $("<option></option>");
					smsm.append(deptOption);
					$.each(jsonvalue, function(indexv, itemv) {
						for (var i = 0; i < smsm.length; i++) {
							if (smsm[i].text == itemv.msgtitle
									|| smsm[i].value == itemv.msgid) {
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