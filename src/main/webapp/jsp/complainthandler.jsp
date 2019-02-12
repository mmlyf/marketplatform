<!-- 投诉处理 -->
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
									<form action="" class="layui-form" id="complain">
										<div class="layui-form-item">
											<label class="layui-form-label">手机号</label>
											<div class="layui-input-inline">
												<input type="text" name="phonenum" required
													lay-verify="required" placeholder="输入查询号码"
													autocomplete="off" class="layui-input">
											</div>
										</div>
										
										<div class="layui-form-item">
											<label class="layui-form-label">选择年份</label>
											<div class="layui-input-inline">
												<select name="year" id="year" lay-search lay-filter="year" required lay-verify="required">
													<option value="2018">2018</option>
													<option value="2019">2019</option>
												</select>
											</div>
											<label class="layui-form-label">选择月份</label>
											<div class="layui-input-inline">
												<select name="year" id="year" lay-search lay-filter="year" required lay-verify="required">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
												</select>
											</div>
										</div>
									</form>
									<button class="layui-btn" data-type="reload" id="search">搜索</button>
								</div>
								<div
									style="width: 100%; height: 1px; border-bottom: 1px solid #F7B824; margin-top: 2px;"></div>
							</blockquote>
							<fieldset class="layui-elem-field">
								<legend>投诉数据</legend>
								<table class="layui-hide" id="complaindata" lay-filter="complaindata"></table>
							</fieldset>
						</div>
						<script type="text/javascript">
							layui.use(['form', 'layer','laydate','table'],function(){
								var form = layui.form, layer = layui.layer, $ = layui.jquery,table = layui.table;
								var laydate = layui.laydate;
								
								table.render({
									elem : '#complaindata',
									url : '../smsupdate/getfiledata',
									cellMinWidth : 80,
									width:window.innerWidth*0.94,
									height:550,
									toolbar:'toolbarDemo',
									title : '投诉数据',
									cols : [[ 
											{
												type : 'checkbox',
												fixed : 'left'
											},
											{
												field : 'phone',
												title : '手机号',
												align : 'center',
												width : 100
											},
											{
												field : 'dangw',
												title : '档位',
												align : 'center'
											},
											{
												field : 'sendtime',
												title : '下行时间',
												align : 'center'
											},
											{
												field : 'sendcontent',
												title : '下行内容',
												align : 'center',
												width : 300
											},
											{
												field : 'motime',
												title : '上行时间',
												width : 120,
												align : 'center'
											},
											{
												field : 'mocontent',
												title : '上行内容',
												width : 100,
												align : 'center'
											} ]] ,
									page : true,
									limit:10,
									where:{
										keyword:'<%=session.getAttribute("realname")%>',
										keytype:'reviewman'
									},
									id : 'tasklist',
									done:function(res, curr, count){
										$('#groupnum').text(count+"条");
										
									}
								});
								
								/**************执行时间实例************************/
								laydate.render({
									elem : '#choicetime' //指定元素
									,
									position : 'abolute',
									type : 'date'
								});
							});
							$('#search').click(function(){
								var phone = $('#phonenum').val();
								var choicetime = $('#choicetime').val();
								$.ajax({
									url:'',
									type:'post',
									dataType:'json',
									data:{
										phone:phone,
										choicetime:choicetime
									},
									success:function(result){
										
									}
								});
							})
							
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>