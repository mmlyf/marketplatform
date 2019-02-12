<!-- 标签数据的导入 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>场景营销数据导入</title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../css/button.css" media="all"/>
<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<ul class="layui-tab-title">
					<li class="layui-this">文件导入</li>
				</ul>
				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated ng-scope">
							<fieldset class="layui-elem-field layui-field-title"
								style="margin-top: 20px;">
								<legend>标签数据入库</legend>
							</fieldset>
							<form class="layui-form layui-form-pane" enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">导入文件</label>
									<div class="layui-input-block" style="position: relative;">
										 <!-- <input class="layui-input" type="file" name="blackfile"
											id='daos'
											onchange="document.getElementById('textfield').value=this.value"
											style="position: absolute; top: 0; left: 150px; height: 20px; opacity: 0; width: 60px;" /> -->
										<input type='text' name='textfield' id='textfield' class='txt'
											style="height: 30px;" /><button type="button" class="layui-btn" name="blackfile" id="test1"><i class="layui-icon">&#xe67c;</i>选择文件</button>

									</div>
								</div>
								<input type="hidden" id="realname" name="realname" value="<%=session.getAttribute("realname") %>"/>
								<div class="layui-form-item">
									<label class="layui-form-label">标签选择</label>
									<div class="layui-input-block" id="bqc">
										<input type="button" class="layui-btn" id="addbq" value="添加"/> 
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">操作选择</label>
									<div class="layui-input-block" id="ddd">
										<input type="radio" lay-filter="opera" name="opera" value="add"  title="添加">
										<input type="radio" lay-filter="opera" name="opera" value="update" title="更新">
										<span class="layui-label" id="description" style="color:red"></span>
									 </div>
  								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" id="inputdata" lay-submit
											lay-filter="inputdata">上传文件</button>
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
		layui.use([ 'form', 'laypage', 'layer', 'upload' ],
						function() {
							var form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload;
							/****************用于处理点击添加时间********************/
							form.on('radio(opera)',function(){
								var opera = $("input[type='radio']:checked").val();
								console.log(opera);
								var str = "";
								if(opera == 'update'){
									str = "<h2>更新操作会将之前的数据覆盖，更新成当前最新的标签数据</h2>";
								}else if(opera=='add'){
									str = "<h2>添加操作会在原来数据基础之上进行添加新的标签数据</h2>";
								}
								$('#description').html(str);
							});
							
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
											$('#bqc').append("<input type='checkbox' name='bq' title='"+json.name+"' value='"+json.id+"'>")
										});
										form.render('checkbox');
									}
								}});  
							
							$('#addbq').click(function(){
								parent.layer.open({
									type:2,
									title:'标签添加',
									area:['600px','400px'],
									content:'addbq.jsp',
									end:function(){
										window.location.reload();
									}
								});
							});
							
						
							//执行实例
							var uploadInst = upload.render({
										elem : '#test1' //绑定元素
										,
										url : '../scenemarket/sceneupload' //上传接口
										,
										auto : false,
										accept : 'file',
										method:'post',
										bindAction : '#inputdata',
										choose : function(obj) {
											obj.pushFile();
											obj.preview(function(index, file,
													result) {
												$("#textfield").attr("value",
														file.name);
											});
											 
										},
										before : function(obj) {
												parent.layer.load();
												var opera = $('#opera').val();			
												var arr = [];
												$("input:checkbox[name='bq']:checked").each(function(i){
									                arr[i] = $(this).val();
									            });
												var opera = $("input:radio[name='opera']:checked").val();
												console.log($('#realname').val());
												console.log(arr);
												console.log(opera);
												var arrstr = arr[0];
												for(i=1;i<arr.length;i++){
													arrstr+=","+arr[i]
												}
												this.data = {
													'bq':arrstr,
													'realname':$('realname').val(),
													'opera':opera
												};
										
										},
										done : function(res) {
											if (res.code > 0) {
												 parent.layer.closeAll('loading'); //关闭loading
												parent.layer.msg("添加失败");

											} else {
												 parent.layer.closeAll('loading');
												parent.layer.msg("添加成功",
																{
																	end : function() {
																		var index = parent.layer
																				.getFrameIndex(window.name);
																		parent.layer
																				.close(index);
																	}
																});
											}
										},
										error : function() {
											 parent.layer.closeAll('loading'); //关闭loading
											parent.layer.msg("添加失败");
										}
									});
						});
	</script>
</body>
</html>