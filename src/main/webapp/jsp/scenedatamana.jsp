<!-- 标签数据的管理和查询 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<title></title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body" style="height:800px">
	<div class="layui-fluid" >
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				
				<div class="layui-tab-content" >
					<!-- 文件导入数据展示 -->
					<div class="layui-tab-item layui-show" >
						<div class="admin-main fadeInUp animated">
							<blockquote class="layui-elem-quote">
								<div class="demoTable">
								<form class="layui-form layui-form-pane" enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">标签选择</label>
									<div class="layui-input-block" id="bqc">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">操作选择</label>
									<div class="layui-input-block" id="ddd">
										<input type="radio" lay-filter="opera" name="opera" value="1"  title="交">
										<input type="radio" lay-filter="opera" name="opera" value="2" title="并">
										<input type="radio" lay-filter="opera" name="opera" value="3" title="无" checked>
										<span class="layui-label" id="description" style="color:red"></span>
									 </div>
  								</div>
									</form>
									<button class="layui-btn" data-type="reload">搜索</button>
									<div class="layui-inline">
										<label class="layui-form-label"
											style="color: red; font-weight: bold; font-size: 16px; text-align: left;"><span
											class="num_peo" id="num"> 
										</span>
									</div>
								</div>
								<div style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
								
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>消息列表</legend>
								<div class="layui-field-box">
									<table class="layui-hide" id="test" lay-filter="test"></table>
									<script type="text/html" id="toolbarDemo">
  										<div class="layui-btn-container">
											<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  										</div>
									</script>
									<script type="text/html" id="barDemo">
										
									</script>
									<script>
										layui.use(['form', 'laypage', 'layer', 'upload','table'],function() {
											var form = layui.form, layer = layui.layer, $ = layui.jquery, upload = layui.upload,table = layui.table;
											 var index = layer.load(2);
											/***********加载表格数据**************/
											table.render({
												elem : '#test',
												url : '../scenemarket/selectscene',
												cellMinWidth : 80,
												width:window.innerWidth*0.94,
												height:550,
												toolbar: '#toolbarDemo',
												title : '消息列表',
												cols : [[ 
													{
														type : 'checkbox',
														fixed : 'left'
													},
													{
														field : 'id',
														title : 'ID',
														sort : true,
														unresize : true,
														fixed : 'left',
														width:80
													},
													{
														field : 'dn',
														title : '号码',
														align : 'center'
													},
													{
														field : 'bq',
														title : '标签',
														align : 'center'
													},
													{
														fixed : 'right',
														title : '操作',
														toolbar : '#barDemo',
														width : 200,
														align : 'center'
													} ]] ,
												page : true,
												limit:10,
												id : 'bqdatalist',
												done:function(data){
													layer.close(index);
													$('#num').text(data.count+"条");
												}
											});
											table.on('toolbar(test)',function(obj){
												switch(obj.event){
												case 'addMsgData':
													layer.open({
														type: 2,
														title: '新增',
														area : ['800px' , '420px'], 
														scrollbar: false,//禁止浏览器滚动
														content: 'insertmsg.jsp',
														end:function(){
															table.reload('bqdatalist',{where : {}});
														}
													});
													break;
												}
											});
											//监听行工具事件
											table.on('tool(test)',function(obj) {
												var data = obj.data;
												//console.log(obj)
												if (obj.event === 'detail') {
													layer.open({
														type: 2,
														title: '编辑',
														area : ['800px' , '420px'], 
														scrollbar: false,//禁止浏览器滚动
														content: encodeURI('editmsg.jsp?'+data.id+','+data.mistitle+','+data.miscontent),
														end:function(){
															//执行重载
															table.reload('bqdatalist',{where : {}});
														}
													} );
													}
												});
											var $ = layui.$, active = {
													reload : function() {
														var bq = "";
														$("input:checkbox[name='bq']:checked").each(function(i){
															if(i==0){
																bq = $(this).val();
															}else{
																bq += ","+$(this).val();
															}
											            });
														var opera = $("input:radio[name='opera']:checked").val();
														console.log(bq+"---"+opera);
														table.reload('bqdatalist',{
															where : {
																bq : bq,
																opera: opera
															}
														});
													}
												};
											$('.demoTable .layui-btn').on('click',function() {
												var type = $(this).data('type');
												active[type] ? active[type].call(this): '';
											});
											/*****************用于填充标签*******************/
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
</body>
</html>