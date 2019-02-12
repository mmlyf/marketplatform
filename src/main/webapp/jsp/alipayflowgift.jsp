<!-- 流量赠送界面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script>
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
								action="../smscreate/filein"  id="flowgiftform"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">手机号码</label>
									<div class="layui-input-block">
										<input type="text" name="phonenum" id="phonenum" required lay-verify="required"
											autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">流量数值</label>
									<div class="layui-input-block">
										<input type="text" name="flow" id="flow" required lay-verify="required"
											placeholder="请输入流量值：10、30、50、100、200、300、500、1024、2048(M)" autocomplete="off" class="layui-input">
									</div>
								</div>


								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="flowgift">赠送</button>
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
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript">
$(function(){
    var str=window.location.href;
    var data = str.split('?')[1];
    $("#phonenum").attr("value",data);
    $("#flowgift").click(function(){
    		$.ajax({
    			url:'../alipayuser/flowgift',
    			type:'POST',
    			dataType:'json',
    			data:$('#flowgiftform').serialize(),
    			beforeSend:function(XMLHttpRequest){
    				layer.load(0);
    			},
    			success:function(result){
    				layer.closeAll('loading');
    				if(result.code==0){
    					layer.msg("赠送流量提交成功！！",
    						{
    							time:1000,
    							end:function(){
    							var index = parent.layer.getFrameIndex(window.name);
        						parent.layer.close(index);
    						}});
    				}else{
    					layer.closeAll('loading');
    					layer.msg("提交失败\n");
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