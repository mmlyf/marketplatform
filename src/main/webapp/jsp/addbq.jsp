<!-- 添加标签页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script type="text/javascript" src="../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post" id="bqinsert"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">标签名称</label>
									<div class="layui-input-block">
										<input type="text" name="bqValue" id="bqvalue" required lay-verify="required"
											placeholder="请输入标签" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="insertbq">保存</button>
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
	<script type="text/javascript">
$(function(){
    $("#insertbq").click(function(){
    		$.ajax({
    			url:'../scenemarket/insertbq',
    			type:'POST',
    			dataType:'json',
    			data:$('#bqinsert').serialize(),
    			success:function(result){
    				if(result.code==0){
    					parent.layer.msg("添加成功",{end:function(){
    						var index = parent.layer.getFrameIndex(window.name);
        					parent.layer.close(index);
    					}});
    					
    				}else{
    					parent.layer.msg("添加失败");
    				}
    			}
    		});
    });
    $("#canclesave").click(function(){
    		var index = parent.layer.getFrameIndex(window.name); 
    		parent.layer.close(index);
    });
  });
</script>
</body>
</html>