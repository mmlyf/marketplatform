<!-- 场景营销任务审核界面 -->
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
									<label class="layui-form-label">所属标签</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="label" style="width:500px"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">标签操作</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="label_opera"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">数据数量</label>
									<div class="layui-input-block">
										<label class="layui-form-label" id="data_count"></label>
									</div>
								</div>
								
								<input type="hidden"  name="id" id="id" />
								<div class="layui-form-item">
									<label class="layui-form-label">定时任务</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="istimework"></label>
									</div>
									<label class="layui-form-label">任务时间</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="worktime" style="width:300px"></label>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">去除黑名单</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="isdelblack"></label>
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
									<label class="layui-form-label">去除重复运营</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="isdeldays"></label>
									</div>
									<label class="layui-form-label">重复时间</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="deldays" ></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">提交人</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="addman"></label>
									</div>
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-inline">
										<label class="layui-form-label" id="reviewman"></label>
									</div>
								</div>
								
								<div class="layui-form-item">
									<div class="layui-input-block">
										<input class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="reviewsuc" value="审核通过"/>
										<input type="button" id="reviewunsuc" class="layui-btn layui-btn-primary" value="审核不通过" />
										<input type="button" id="cancle" class="layui-btn layui-btn-primary" value="取消"/>
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
    var data = objstr.split(";");
    $('#label').text(data[0]);
    $('#label_opera').text(data[1]);
    $('#data_count').text(data[2]);
    $('#istimework').text(data[3]);
    $('#worktime').text(data[4]);
    $('#isdelblack').text(data[5]);
    $('#isdeldays').text(data[6]);
    $('#deldays').text(data[7]);
    $('#addman').text(data[9]);
    $('#msgtitle').text(data[10]);
    $('#msgcontent').text(data[11]);
    $('#reviewman').text(data[12])
    layer.msg(data[8]);
    $('input:button').click(function(){
    		var value = $(this).attr('value');
    		var state = 0;
    		if(value=='取消'){
    			var index = parent.layer.getFrameIndex(window.name); 
        		parent.layer.close(index);
    		}else{
    			if(value == '审核通过'){
        			state = 1;
        		}else if(value=='审核不通过'){
        			state = 2;
        		}
        		$.ajax({
        			url:'../scenejob/updatescenejob',
        			type:'POST',
        			dataType:'json',
        			data:{
        				'state':state,
        				'id':data[8]
        			},
        		success:function(result){
        			if(result.code==0){
    					layer.msg("审核提交成功");
    					var index = parent.layer.getFrameIndex(window.name);
    					parent.layer.close(index);
    				}else{
    					layer.msg("审核提交失败");
    				}
        		}
        		});
    		}
    });
    
  });
  
 
</script>
</body>
</html>