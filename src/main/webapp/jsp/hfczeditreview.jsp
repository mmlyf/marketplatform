<!-- 文件导入的审核界面 -->
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
</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post"
								action="../smscreate/filein"  id="msgedit"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">充值号码</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="czdn"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">充值金额</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="czamount" style="width:500px"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">充值原因</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="czreason"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">审核状态</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="restate"></label>
									</div>
								</div>
								
								<input type="hidden"  name="id" id="id" />
								<input type="hidden" id="seno" name="seno"/>
								<div class="layui-form-item">
									<label class="layui-form-label">提交人</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="addman"></label>
									</div>
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="czreviewer"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">添加时间</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="czaddtime"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="reviewsuc">审核通过</button>
										<button type="button" id="reviewunsuc" class="layui-btn layui-btn-primary">审核不通过</button>
										<button type="button" id="cancle" class="layui-btn layui-btn-primary">取消</button>
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
    var str=window.location.href;
    var objstr = decodeURI(str.split('?')[1]);
    var data = objstr.split(",");
    $('#id').text(data[0]);
    $('#czdn').text(data[1]);
    $('#seno').text(data[2]);
    $('#czaddtime').text(data[3]);
    $('#czamount').text(data[4]);
    $('#czreviewer').text(data[5]);
    $('#addman').text(data[6]);
    $('#czreason').text(data[7]);
    $('#restate').text(data[8]);
    layer.msg(data[8]);
    $("#reviewsuc").click(function(){
    		$.ajax({
    			url:'../hfczreview/uphfczstate',
    			type:'POST',
    			dataType:'json',
    			data:{
    				'state':1,
    				'reviewid':data[0]
    			},
    			success:function(result){
    				if(result.code==0){
    					/* var seno = data[2];
       				console.log(seno);
       					$.ajax({
       						url:'http://mobile99.uninforun.com/hst/index.php/api/hfcz/yycz',
       						data:{
       							out_trade_no:seno
       						},
       						success:function(){
       							layer.msg("充值成功");
       						}
       					}) */
    					layer.msg("审核通过",{end:function(){
        					var index = parent.layer.getFrameIndex(window.name);
        					parent.layer.close(index);
    					}});
    				}else{
    					layer.msg("审核失败");
    				}
    			}
    		});
    });
    $("#reviewunsuc").click(function(){
    		$.ajax({
    			url:'../hfczreview/uphfczstate',
    			type:'POST',
    			dataType:'json',
    			data:{
    				'state':2,
    				'reviewid':data[0]
    			},
    		success:function(result){
    			if(result.code==0){
					layer.msg("审核不通过");
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}else{
					layer.msg("审核失败");
				}
    		}
    		});
    });
    $("#cancle").click(function(){
    		var index = parent.layer.getFrameIndex(window.name); 
    		parent.layer.close(index);
    });
  });
</script>
</body>
</html>