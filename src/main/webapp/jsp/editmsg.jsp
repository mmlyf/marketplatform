<!-- 编辑营销语的界面 -->
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
<script type="text/javascript" src="../layer/layer.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show" style="height: 1000px;">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post"
								action="../smscreate/filein"  id="msgedit"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">消息标题</label>
									<div class="layui-input-block">
										<input type="text" name="misTitle" id="msgtitle" required lay-verify="required"
											placeholder="请输入名称" autocomplete="off" class="layui-input">
									</div>
								</div>
								<input type="hidden"  name="misId" id="msgid" />
								<div class="layui-form-item">
									<label class="layui-form-label">消息内容</label>
									<div class="layui-input-block">
										<textarea name="misContent" id="msgcontent" placeholder="请输入内容" class="layui-textarea"></textarea>
									</div>
								</div>
								

								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="savemsg">保存</button>
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

	

	<script type="text/javascript">
$(function(){
    var str=window.location.href;
    var datastr = decodeURI(str.split('?')[1]);
    var dataarr = datastr.split(',');
    $('#msgid').val(dataarr[0]);
    $('#msgtitle').val(dataarr[1]);
    $('#msgcontent').val(dataarr[2]);
    $("#savemsg").click(function(){
    		$.ajax({
    			url:'../msgmana/updateid',
    			type:'POST',
    			dataType:'json',
    			data:$('#msgedit').serialize(),
    			success:function(result){
    				if(result.code==1){
    					layer.msg("修改成功",{end:function(){
    						var index = parent.layer.getFrameIndex(window.name);
        					parent.layer.close(index);
    					}});
    					
    				}else{
    					layer.msg("修改失败");
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