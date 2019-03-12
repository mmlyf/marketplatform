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
					<!-- 文件导入 -->
					<div class="layui-tab-item layui-show" style="height: 600px;">
						<div class="admin-main fadeInUp animated ng-scope">
							
							<form class="layui-form layui-form-pane" method="post" id="msgedit" action="../alipay/insert"  enctype="multipart/form-data">
								<div class="layui-form-item">
									<label class="layui-form-label">手机号码</label>
									<div class="layui-input-block">
										<input type="text" name="pyDn" id="pyDn" placeholder="输入充值手机号" autocomplete="off" class="layui-input" required lay-verify="required">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">充值金额</label>
									<div class="layui-input-block">
										<input type="text" class="layui-input" name="pyTotalmoey" id="pyTotalmoey" required lay-verify="required" placeholder="输入需要充值的金额">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">充值原因</label>
									<div class="layui-input-block">
										<input type="text" class="layui-input" name="reason" id="reason" required lay-verify="required" placeholder="输入充值的原因">
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button" lay-submit
											lay-filter="formDemo" id="submit">提交</button>
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
   $("#submit").click(function(){
	   var pyDn = $('#pyDn').val();
	   var pyTotalmoey = $('#pyTotalmoey').val();
	   if(pyDn==''){
		   layer.msg("号码不能为空");
		   return false;
	   }
	   if(pyTotalmoey==''){
		   layer.msg("金额不能为空");
		   return false;
	   }
	   layer.confirm("确认为"+pyDn+"充值！",function(){
		   $.ajax({
   			url:'../alipay/insert',
   			type:'POST',
   			dataType:'json',
   			data:$('#msgedit').serialize(),
   			success:function(result){
   				if(result.code==0){
   					/* var seno = result.seno;
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
   				}else if(result.code==2){
   					layer.msg("当前金额过大已提交审核，审核结果可在我的提交中进行查看！");
   				}else{
   					layer.msg("提交失败。请重试！");
   				}
   			}
   		});
	   });
    		
    });
  });
</script>
</body>
</html>