<!-- 黑名单的文件导入 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post"
								action=""  id="blackinsert"  enctype="multipart/form-data">
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
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="inputblack">上传</button>
										<button type="button" id="canclesave" class="layui-btn layui-btn-primary">取消</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../layer/layer.js"></script>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript">
$(function(){
    $("#canclesave").click(function(){
    		var index = parent.layer.getFrameIndex(window.name); 
    		parent.layer.close(index);
    });
  });
layui.use('upload', function(){
	  var upload = layui.upload;
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#test1' //绑定元素
	    ,url: '../blackmana/uploadfile' //上传接口
	    ,auto:false
	    ,accept:'file'
	    ,bindAction:'#inputblack'
	    ,choose:function(obj){
	    		obj.pushFile();
	    		obj.preview(function(index,file,result){
	    			$("#textfield").attr("value",file.name);
	    		});
	    	}
	  	,before:function(obj){
		  	layer.load();
	 	 }
	    ,done: function(res){
	    	layer.closeAll('loading'); //关闭loading
	    		if(res.code>0){
	    			layer.closeAll('loading'); //关闭loading
				 layer.msg("添加失败");
				
			}else{
				layer.msg("添加成功",{end:function(){
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}});
			}
	    }
	    ,error: function(){
	   	 	layer.closeAll('loading'); //关闭loading
	   	 	layer.msg("添加失败");
	    }
	  });
	});
</script>
</body>
</html>