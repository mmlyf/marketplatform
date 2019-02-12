<!-- 提交场景营销的数据并保存值数据库 -->
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
<body class="layui-layout-body" style="height: 900px">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">

				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show"">
						<div class="admin-main fadeInUp animated ng-scope">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>标签营销</legend>
							</fieldset>
							<form class="layui-form layui-form-pane" method="post" id="scenendata"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">标签选择</label><!-- 选择需要运营的标签数据 -->
									<div class="layui-input-block" id="bqc">
										
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">操作选择</label><!-- 选择对标签的数据进行什么逻辑操作 -->
									<div class="layui-input-block" id="ddd">
										<input type="radio" lay-filter="opera" name="bqOpera" id = "bqOpera" value="1"  title="交">
										<input type="radio" lay-filter="opera" name="bqOpera" id = "bqOpera" value="2" title="并">
										<input type="radio" lay-filter="opera" name="bqOpera" id = "bqOpera" value="3" title="无" checked>
										<span class="layui-label" id="description" style="color:red"></span>
									 </div>
  								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label><!-- 用于选择是否在指定时间内运行任务 -->
									<div class="layui-input-block">
										<input type="checkbox" name="isTimework" id="isTimework"
											lay-filter="switchTest" lay-skin="switch" lay-text="ON|OFF">
									</div>
								</div>
								
								<div class="layui-form-item layui-hide" id="timechoice">
									<label class="layui-form-label">时间选择</label>
									<div class="layui-input-inline">
										<input type="text" class="layui-input" id="workTime"
											name="workTime" placeholder="yyyy-MM-dd hh:mm:ss"
											autocomplete="off" />
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">短信语</label>
									<div class="layui-input-block">
										<select name="misId" id="misId" lay-search
											lay-filter="smsms" required lay-verify="required">
										</select>
									</div>
								</div>

								<div class="layui-form-item">
									<div class="layui-input-inline">
										<input type="checkbox" name="isDelblack" id="isDelblack" title="去黑名单" />
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">重复运营</label>
									<div class="layui-input-inline">
										<input type="checkbox" name="isDeldays" id="isDeldays" lay-filter="switchdel"
											lay-skin="switch" lay-text="ON|OFF">
									</div>
									<div class="layui-input-inline layui-hide" id="daystext">
										<input type="text" class="layui-input" name="deldays" id="deldays"
											autocomplete="false" />
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-block">
										<select name="reviewMan" id="reviewMan" lay-search="" lay-verify="required">
											<option value=""></option>
											<option value="裴秋婷">裴秋婷</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item layui-hide">
									<label class="layui-form-label"></label>
									<div class="layui-input-block">
										<input type="hidden"
											value="<%=session.getAttribute("realname") %>" id="addMan" name="addMan">
									</div>
								</div>

								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" id="submitdata"
											lay-filter="formDemo">立即提交</button>
										<button class="layui-btn" type="button" id="output" lay-submit
											lay-filter="formDemo">导出</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script>
	<script src="../layui/layui.js"></script>
	<script>
		/*************************监控form表单中的一些事件和动态效果************************************/
		layui.use([ 'form', 'laypage', 'layer', 'upload' ,'laydate'],
						function() {
							var form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload;
							var laydate = layui.laydate;
							
							/*****************用于填充标签数据*******************/
							 $.ajax({
								url:'../scenemarket/selectlabel',
								type:'post',
								dataType:'json',
								success:function(result){
									console.log(result.code);
									if(result.code==0){
										var data = eval(result.data);
										var htmlstr="";
										console.log(data);
										$.each(data,function(index,item){
											var json = eval(item);
											$('#bqc').append("<input type='checkbox' name='sceneBq' title='"+json.name+"' value='"+json.id+"'>")
										});
										form.render('checkbox');
									}
								}});
							/**************执行时间实例************************/
							laydate.render({
								elem : '#workTime' //指定元素
								,
								position : 'abolute',
								type : 'datetime'
							});
							/*****************用于处理导出按钮导出数据的处理*******************/
							$('#output').click(function(){
								$.ajax({
									url:'../scenejob/outputjob',
									type:'post',
									dataType:'json',
									data:$('#scenendata').serialize(),
									success:function(result){
										//window.location.href="http://localhost:8085/HSDT_Market_Platform/smsupdate/downfile?filepath="+result.filepath;
										window.location.href="http://221.192.138.29:8089/HSDT_Market_Platform/smsupdate/downfile?filepath="+result.filepath;
										layer.msg("导出成功！");
									}
								});
							});
							
							/************************用于处理点击提交数据后的时间*****************************/
							$('#submitdata').click(function(){
								var bqOpera = $("input:radio[name='bqOpera']:checked").val();
								var isTimework = $("input:checkbox[name='isTimework']:checked").val();
								var workTime = $('#workTime').val();
								var misId = $('#misId').val();
								var isDelblack = $("input:checkbox[name='isDelblack']:checked").val();
								var isDeldays = $("input:checkbox[name='isDeldays']:checked").val();
								var deldays = $('#deldays').val();
								var reviewMan = $('#reviewMan').val();
								var addMan = $('#addMan').val();
								var sceneBq = "";
								$("input:checkbox[name='sceneBq']:checked").each(function(i){
									if(i==0){
										sceneBq = $(this).val();
									}else{
										sceneBq += ","+$(this).val();
									}
								});
								console.log("misid的值是："+misId);
								console.log("deldays的只是："+deldays);
								$.ajax({
									url:'../scenejob/insertscenejob',
									type:'post',
									dataType:'json',
									data:{
										bqOpera:bqOpera,
										isTimework:isTimework,
										workTime:workTime,
										misId:misId,
										isDelblack:isDelblack,
										isDeldays:isDeldays,
										deldays:deldays,
										reviewMan:reviewMan,
										addMan:addMan,
										sceneBq:sceneBq
									},
									success:function(res){
										if(res.code==0){
											layer.msg("添加成功");
											window.location.href = "../jsp/scenemysubmit.jsp"
										}else{
											layer.msg("添加失败");
										}
									}
								})
							});										

							/*********************************请求msg的值**************************************/
							$.ajax({
								url : '../requestdata/selectmsg',
								type : 'POST',
								dataType : 'json',
								success : function(result) {
									var json = eval(result);
									var optionstr ="";
									$.each(json, function(index, item) {
										var jsonvalue = eval(item);
										$.each(jsonvalue, function(indexv, itemv) {
											optionstr += "<option value='"+itemv.msgid+"'>"
													+ itemv.msgtitle + "</option>";
										});
									});
									$("#misId").html("<option></option> "+optionstr);
									form.render('select');
								}
							});
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
							
							form.on('switch(switchdel)',function(data){
								layer.msg('避免重复运营' + (this.checked ? '开' : '关'),
										{
											offset : '6px'
										});
								if(this.checked){
									$('#daystext').removeClass('layui-hide');
								}else{
									$('#daystext').addClass('layui-hide');
								}
							});
						});

		
	</script>
</body>
</html>