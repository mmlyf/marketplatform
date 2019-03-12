<!-- 首页界面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>汇视达通网络科技</title>
		<link rel="icon" href="../image/favicon.ico" type="image/x-icon"/>
		<link rel="shortcut icon" href="../image/favicon.ico">
		<link rel="icon" href="../image/favicon.ico">
		<link rel="stylesheet" href="../layui/css/layui.css" />
		<link rel="stylesheet" href="../css/index.css" />
		<script>
			//iframe高度自适应
			function setIframeHeight(iframe) {
				if (iframe) {
					var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
					if (iframeWin.document.body) {
						iframe.height = iframeWin.document.body.scrollHeight;
					}
				}
			};
		</script> 
	</head>
	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin">
			<!--头部-->
			<div class="layui-header">
				<!--头部左侧导航-->
				<ul class="layui-nav layui-layout-left" lay-filter="leftmenu">
					<li class="layui-nav-item">
						<a href="javascript:;" class="hidetab" title="隐藏左侧菜单"><i class="layui-icon layui-icon-shrink-right"></i></a>
					</li>
					<li class="layui-nav-item">
						<a href="../jsp/index.jsp" title="主页"><i class="layui-icon layui-icon-home"></i></a>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:window.location.reload();" title="刷新"><i class="layui-icon layui-icon-refresh-3"></i></a>
					</li>
					<li class="layui-nav-item layui-hide-xs">
						<input class="layui-input layui-input-search" type="text" placeholder="搜索" autocomplete="off"/>
					</li>
				</ul>
				<!--头部右侧导航-->
				<ul class="layui-nav layui-layout-right" lay-filter="rightmenu">
					<!-- <li class="layui-nav-item">
						<a href=""><i class="layui-icon layui-icon-speaker"></i>消息中心<span class="layui-badge">9</span></a>
					</li>-->
					<li class="layui-nav-item">
						<a href="javascript:;">
							<i class="layui-icon layui-icon-user"></i>
							<%=session.getAttribute("realname") %>
						</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" lay-href="../jsp/changepassword.jsp"><i class="layui-icon layui-icon-password"></i>修改密码</a></dd>
						</dl>
					</li>
					<!--  <li class="layui-nav-item">
						<a href="javascript:;">锁屏<i class="layui-icon layui-icon-password"></i></a>
					</li>-->
					<li class="layui-nav-item"><a href="../login/logout">退出</a></li>
				</ul>
			</div>
			<!--左侧-->
			<div class="layui-side layui-side-menu">
				<!--带滚动条垂直导航-->
				<div class="layui-side-scroll layui-bg-black">
					<div class="layui-logo">汇视达通运营管理系统</div>
					<ul class="layui-nav layui-nav-tree" lay-filter="navtree">
						<!--默认展开-->
						<li class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;" title="首页" lay-href="../jsp/home.jsp"><i class="layui-icon layui-icon-home"></i><cite>首页</cite></a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" title="运营模块"><i class="layui-icon layui-icon-group"></i><cite>运营模块</cite></a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" lay-href="../jsp/smsmana.jsp">任务创建</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/delimana.jsp">任务管理</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/msgmana.jsp">营销语管理</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/mysubmit.jsp">我的提交</a></dd>
								<% 
									String permission = (String)session.getAttribute("permision");
								
									if(permission.indexOf("1")!=-1||permission.indexOf("28")!=-1){
								%>
								<dd><a href="javascript:;" lay-href="../jsp/reviewpage.jsp">我的审批</a></dd>
								<% } %>
							</dl>
						</li>
						<%
							if(permission.indexOf("42")!=-1||permission.indexOf("1")!=-1){
						%>
						<li class="layui-nav-item">
							<a href="javascript:;" title="场景营销"><i class="layui-icon layui-icon-template"></i><cite>场景营销</cite></a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" lay-href="../jsp/scenedatain.jsp">数据导入</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/scenedatamana.jsp">数据管理</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/scenemarket.jsp">场景营销</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/scenejobmana.jsp">任务管理</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/scenemysubmit.jsp">我的提交</a></dd>
								<% 
									if(permission.indexOf("1")!=-1||permission.indexOf("28")!=-1){
								%>
								<dd><a href="javascript:;" lay-href="../jsp/scenemyreview.jsp">我的审批</a></dd>
								<% } %>
							</dl>
						</li>
						<%} %>
						<li class="layui-nav-item">
							<a href="javascript:;" title="数据模块"><i class="layui-icon layui-icon-chart-screen"></i><cite>数据模块</cite></a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" lay-href="../jsp/datatotal.jsp">数据统计</a></dd>
								<dd>
									<li class="layui-nav-item">
										<a href="javascript:;" title="业务模块"><i class="layui-icon"></i><cite>业务模块</cite></a>
										<dl class="layui-nav-child">
											<dd><a href="javascript:;" lay-href="../jsp/businessmodule.jsp">订购数据</a></dd>
											<dd><a href="javascript:;" lay-href="../jsp/icecreambook.jsp">畅越预约</a></dd>
											<dd><a href="javascript:;" lay-href="../jsp/customservice.jsp">客服预约</a></dd>
										</dl>
									</li>
								</dd>
								<dd><a href="javascript:;" lay-href="../jsp/activitymana.jsp">活动模块</a></dd>
								<!-- <dd><a href="javascript:;" lay-href="../jsp/complainthandler.jsp">投诉处理</a></dd> -->
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" title="用户管理"><i class="layui-icon layui-icon-user"></i><cite>用户管理</cite></a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" lay-href="../jsp/blacklistmana.jsp">黑名单管理</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/alipayusermana.jsp">会员管理</a></dd>
								<% if(permission.indexOf("1")!=-1){ %>
								<dd><a href="javascript:;" lay-href="../jsp/platformusermana.jsp">平台会员</a></dd>
								<% } %>
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" title="模板消息"><i class="layui-icon layui-icon-user"></i><cite>模板消息</cite></a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" lay-href="http://mobile99.uninforun.com/Hst/Tpl/index.php/Home/Tpl/index.html">任务创建</a></dd>
								<dd><a href="javascript:;" lay-href="http://mobile99.uninforun.com/Hst/Tpl/index.php/Home/Moment/index.html">任务管理</a></dd>
								<dd><a href="javascript:;" lay-href="http://mobile99.uninforun.com/Hst/Tpl/index.php/Home/Log/index.html">日志管理</a></dd>
								<dd><a href="javascript:;" lay-href="http://mobile99.uninforun.com/Hst/Tpl/index.php/Home/Excel/index.html">数据管理</a></dd>
							</dl>
						</li>
						
						<li class="layui-nav-item">
							<a href="javascript:;" title="充值模块"><i class="layui-icon layui-icon-user"></i><cite>充值模块</cite></a>
							<dl class="layui-nav-child">
								<% if(permission.indexOf("1")!=-1||permission.indexOf("43")!=-1){ %>
									<dd><a href="javascript:;" lay-href="../jsp/hfczpage.jsp">话费充值</a></dd>
								<% } %>
								<dd><a href="javascript:;" lay-href="../jsp/actiychfdetail.jsp">充值订单</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/hfczreviewpage.jsp">充值审核</a></dd>
								<dd><a href="javascript:;" lay-href="../jsp/hfczmysubmitpage.jsp">充值记录</a></dd>
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" title="扩展模块"><i class="layui-icon layui-icon-user"></i><cite>扩展模块</cite></a>
							<dl class="layui-nav-child">
								<dd><a href="javascript:;" lay-href="../jsp/equitydataresshow.jsp">抽奖管理</a></dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<!--正文-->
			<div class="layui-body layui-bg-gray">
				<!--选项卡-->
				<div class="layui-admin-pagetabs">
					<div class="layui-tab layui-tab-brief" lay-allowClose="true" lay-filter="pagetabs">
						<ul class="layui-tab-title layui-bg-white">
							<li class="layui-this" lay-id="index"><i class="layui-icon layui-icon-home"></i></li>
						</ul>
						<div class="layui-tab-content">
	                    	<div class="layui-tab-item layui-show">
	                    		<iframe src="../jsp/home.jsp" id="ifream" class="layui-admin-iframe" scrolling="no" frameborder="0" onLoad="setIframeHeight(this)"></iframe>
		                </div>
	                	</div>
					</div>
				</div>
				
			</div>
			<!--底部-->
			<div class="layui-footer">© 2018 南京汇视达通网络科技有限公司</div>
		</div>
		<script src="../layui/layui.js"></script>
		<script>
			layui.use(['element','layer'],function(){
				var element = layui.element
				,layer = layui.layer
				,$ = layui.jquery;
				//隐藏tab主页关闭标签
				$(".layui-tab-title li:first-child i:last-child").css("display","none");
				//tab点击监控
				element.on('tab(pagetabs)',function(data){
					//tab切换时，左侧菜单对应选中
					var url = document.getElementById('ifream').src;
					$(".layui-nav-tree li dd").each(function(i,e){
						if($(this).find("a").attr("lay-href")==url){
							$(this).attr("class","layui-this");
						}else{
							$(this).attr("class","");
						}
					})
				});
				//顶部左侧菜单监控
				element.on('nav(leftmenu)',function(elem){
					//隐藏显示侧边菜单
					if(elem[0].className=="hidetab"){//隐藏
						//侧边菜单伸缩
						$(".layui-side-menu").animate({width:$(".layui-side-menu").width()-144+"px"});
						//正文伸缩
						$(".layui-body").animate({left:$(".layui-body").position().left-144+"px"});
						//底部伸缩
						$(".layui-footer").animate({left:$(".layui-footer").position().left-144+"px"});
						$(this).attr("class","showtab");
						$(this).find("i").attr("class","layui-icon layui-icon-spread-left");
						//侧边菜单只显示图标
						$(".layui-nav-tree").find("li").each(function(em,ind){
							$(this).find("cite").css("display","none");
							$(this).find("dl").css("display","none");
						});
					}else if(elem[0].className=="showtab"){//显示
						$(".layui-side-menu").animate({width:$(".layui-side-menu").width()+144+"px"});
						$(".layui-body").animate({left:$(".layui-body").position().left+144+"px"});
						$(".layui-footer").animate({left:$(".layui-footer").position().left+144+"px"});
						$(this).attr("class","hidetab");
						$(this).find("i").attr("class","layui-icon layui-icon-shrink-right");
						$(".layui-nav-tree").find("li").each(function(em,ind){
							$(this).find("cite").css("display","");
							$(this).find("dl").css("display","");
						});
					}else{
						
					}
				});
				//顶部右侧菜单监控
				element.on('nav(rightmenu)',function(elem){
					var url = $(this).attr("lay-href");
					if(url!=undefined){
						layer.open({
							title:elem[0].innerText,
							type: 2,
							content:url,
							area: ['600px', '500px']
						});
					}
					if(elem[0].innerText=="锁屏"){
						layer.open({
							title:"已锁屏"
							,content: '<input name="pass" class="layui-input" type="text" placeholder="请输入密码,默认123123" autocomplete="off"/>'
							,btnAlign: 'c'
							,anim: 1
							,btn: ['解锁']
							,yes: function(index, layero){
								var pass = layero.find('.layui-layer-content input').val();
								if(pass=="123123"){
						    		layer.close(index);
								}else{
									layer.title("密码不正确！", index);
								}
							}
							,cancel: function(){ 
						    	return false //开启该代码可禁止点击该按钮关闭
							}
						});
					}
				})
				//左侧垂直菜单监控
				element.on('nav(navtree)',function(elem){
					if($(".layui-side-menu").width()<200){
						$(".layui-side-menu").animate({width:$(".layui-side-menu").width()+144+"px"});
						$(".layui-body").animate({left:$(".layui-body").position().left+144+"px"});
						$(".layui-footer").animate({left:$(".layui-footer").position().left+144+"px"});
						$(".layui-layout-left li:first-child").find("a").attr("class","hidetab");
						$(".layui-layout-left li:first-child").find("i").attr("class","layui-icon layui-icon-shrink-right");
						$(".layui-nav-tree").find("li").each(function(em,ind){
							$(this).find("cite").css("display","");
							$(this).find("dl").css("display","");
						});
					} else{
						if($(this).attr("lay-href")!=undefined){
							var  flag = true;
							//url
							var url = $(this).attr("lay-href");
							//判断选项卡中是否存在已打开的页面，如果有直接切换到打开页面
							$(".layui-tab-title li").each(function(i,e){
								if( true ){
									//切换选项卡
									document.getElementById('ifream').src = url;
								}
							})
							
						}
					} 
				});
			});
		</script>
	</body>
</html>