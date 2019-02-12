<!-- 维度筛选审核界面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-fluid">
		<div class="layui-row ">
			<div class="layui-tab layui-tab-card" lay-filter="demo">
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show" style="height: 1000px;">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post"
								action="../smscreate/filein"  id="msgedit"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">城市</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="city"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">网络类型</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="sourcetype"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">产品</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="product"></label>
									</div>
								</div>
								
								<input type="hidden"  name="id" id="id" />
								<div class="layui-form-item">
									<label class="layui-form-label">档位</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="dangw"></label>
									</div>
									<label class="layui-form-label">是否融合</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="isrh" ></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">是否低消</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="isdx"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="istimework"></label>
									</div>
									<label class="layui-form-label">任务时间</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="worktime"></label>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">去除黑名单</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="isdelblack"></label>
									</div>
									
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">是否重复运营</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="isdeldays"></label>
									</div>
									<label class="layui-form-label">重复时间</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="deldays"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">入库时间</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="sectime"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">消息标题</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="msgtitle"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">消息内容</label>
									<div class="layui-input-inline">
										<textarea id="msgcontent" class="layui-textarea" style="width:600px"  readonly="readonly"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">创建人</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="rduser"></label>
									</div>
									<label class="layui-form-label">创建人</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="reuser"></label>
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
    $('#city').text(data[1]);
    $('#sourcetype').text(data[2]);
    $('#product').text(data[3]);
    $('#dangw').text(data[4]);
    $('#isrh').text(data[5]);
    $('#isdx').text(data[6]);
    $('#istimework').text(data[7]);
    $('#worktime').text(data[8]);
    $('#isdelblack').text(data[9]);
    $('#isdeldays').text(data[10]);
    $('#deldays').text(data[11]);
    $('#reuser').text(data[14]);
    $('#sectime').text(data[15]);
    $('#msgtitle').text(data[17]);
    $('#rduser').text(data[18]);
    $('#msgcontent').text(data[19]);
    $("#reviewsuc").click(function(){
    		$.ajax({
    			url:'../smsupdate/upstatemodel',
    			type:'POST',
    			dataType:'json',
    			data:{
    				'state':1,
    				'id':data[0]
    			},
    			success:function(result){
    				if(result.code==1){
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
    			url:'../smsupdate/upstatemodel',
    			type:'POST',
    			dataType:'json',
    			data:{
    				'state':2,
    				'id':data[0]
    			},
    		success:function(result){
    			if(result.code==1){
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