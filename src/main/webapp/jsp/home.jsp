<!-- 首页界面中图表展示的界面 -->
<%@page import="java.text.NumberFormat"%>
<%@page import="com.mtpt.alibean.TBRecord"%>
<%@page import="com.mtpt.alibean.TBReview"%>
<%@page import="java.util.List"%>
<%@page import="com.mtpt.alibean.page.TBRecordPage"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mtpt.aliservice.impl.TBRecordService"%>
<%@page import="com.mtpt.aliservice.impl.TBReviewService"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.mtpt.config.SpringContextUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mtpt.bean.DataTotal"%>
<%@page import="com.mtpt.service.impl.DataTotalService"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="../layui/css/layui.css" />
<script src="http://code.jquery.com/jquery-1.8.0.min.js"
	type="text/javascript"></script>
</head>
<body class="layui-layout-body" style="height: 1000px">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">今日工作概况</div>
					<div class="layui-card-body">
						<%
							ServletContext sc = this.getServletContext();
							ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
							TBReviewService review = (TBReviewService) ac.getBean("reservice");
							TBRecordService tbrecord = (TBRecordService) ac.getBean("tbrecord");
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String datestr = sdf.format(date);
							String addman = (String) session.getAttribute("realname");
							TBRecordPage tbRecordPage = new TBRecordPage();
							tbRecordPage.setKeyid(datestr);
							tbRecordPage.setKeyidtype("addtime");
							List<TBRecord> recordlilst = tbrecord.selectTaskByAddTime(tbRecordPage);
							tbRecordPage.setKeystate("4");
							List<TBRecord> recordsuclilst = tbrecord.selectTaskByAddTime(tbRecordPage);
							tbRecordPage.setKeyid(datestr);
							tbRecordPage.setKeyidtype("add_time");
							List<TBReview> reviewlist = review.selectTaskByAddTime(tbRecordPage);
							tbRecordPage.setKeystate("4");
							List<TBReview> reviewsuclist = review.selectTaskByAddTime(tbRecordPage);
							int phonecount = 0;//任务中号码的数量
							int phonesuccount = 0;//成功下发的号码的数量
							float tasksucrate = 0;//任务下发的成功率
							float phonenumrate = 0;
							int taskallcount = 0;//所有任务的数量
							int tasksuccount = 0;//成功任务的数量
							if (reviewlist != null || recordlilst != null) {
								taskallcount = reviewlist.size() + recordlilst.size();
								if (reviewlist != null) {
									for (TBReview tbReview : reviewlist) {
										phonecount += tbReview.getCount();
									}
								}
								if (recordlilst != null) {
									for (TBRecord tbre : recordlilst) {
										phonecount += tbre.getFilenum();
									}
								}
								
								if(reviewsuclist!=null||!reviewsuclist.isEmpty()){
									tasksuccount += reviewsuclist.size();
								for (TBReview tbReview : reviewsuclist) {
									phonesuccount += tbReview.getCount();
								}
								}
								if(recordsuclilst!=null||!recordsuclilst.isEmpty()){
									System.out.print("当前的列表数据是："+recordsuclilst.size());
									tasksuccount += recordsuclilst.size();
									for (TBRecord tbre : recordsuclilst) {
										if(tbre.getFilenum()!=null){
											phonesuccount += tbre.getFilenum();
										}
									}
								}
								if (taskallcount != 0) {
									tasksucrate = (float) (tasksuccount * 1.0) / taskallcount;
								}
								if (phonecount != 0) {
									phonenumrate = (float) (phonesuccount * 1.0) / phonecount;
								}
							}
						%>
						<table class="layui-table"
							style="height: 300px; width: 100%; text-align: center" lay-even
							lay-skin="nob">
							<tr>
								<td><h2><%=datestr %></h2></td>
								<!-- 	<td colspan="2"><input type="button" value="按钮" class="layui-btn layui-btn-primary layui-btn-radius layui-btn-lg"/></td> -->
							</tr>
							<tr>
								<td>总任务数:</td>
								<td><%=taskallcount %></td>
								<td>完成任务数:</td>
								<td><%=tasksuccount %></td>
							</tr>
							<tr>
								<td>任务用户总数</td>
								<td><%=phonecount %></td>
								<td>已下发用户数</td>
								<td><%=phonesuccount %></td>
							</tr>
							<tr>
								<td>任务完成率:</td>
								<td><%=tasksucrate %></td>
								<td>下发成功率:</td>
								<td><%=phonenumrate %></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

		</div>
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">每日数据统计</div>
					<div class="layui-card-body">
						<%-- <table class="layui-table" style="text-align:center">
							<colgroup>
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
								<col width="10.6%">
							</colgroup>
							<thead>
								<tr>
									<td>下行(总数)</td>
									<td>下行(成功)</td>
									<td>上行(总数)</td>
									<td>上行(有效)</td>
									<td>低消上行(有效)</td>
									<td>冰激凌上行(有效)</td>
									<td>流量包上行(有效)</td>
									<td>冰激凌预约成功</td>
								</tr>
							</thead>
							<%
							DataTotalService ds = (DataTotalService)ac.getBean("dataTotalService");
							Calendar calendar = Calendar.getInstance();
							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH);
							int day = calendar.get(Calendar.DATE);
							calendar.set(year, month, day-1);//昨天
							date = calendar.getTime();
							String adtime = sdf.format(date);
							DataTotal yesdata = ds.selectByAddTime(adtime);
							if(yesdata==null){
							%>
							<tbody>
								<tr>
								<td colspan=8>暂无数据</td>
								</tr>
							</tbody>
							<%} else{%>
							<tbody>
								<tr>
									<td><%=yesdata.getMtall() %></td>
									<td><%=yesdata.getMtsuc() %></td>
									<td><%=yesdata.getMoall() %></td>
									<td><%=yesdata.getMoable() %></td>
									<td><%=yesdata.getMoabledx() %></td>
									<td><%=yesdata.getMoableice() %></td>
									<td><%=yesdata.getMoablellb() %></td>
									<td><%=yesdata.getIcesuc() %></td>
								</tr>
							</tbody>
							<thead>
								<tr>
									<td>下行率</td>
									<td>回复率</td>
									<td>低消回复率</td>
									<td>流量包回复率</td>
									<td colspan=2>冰激凌回复率</td>
									<td colspan=2>订购回复率</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=yesdata.getMtrate() %></td>
									<td><%=yesdata.getMorate() %></td>
									<td><%=yesdata.getModxrate() %></td>
									<td><%=yesdata.getMollbrate() %></td>
									<td colspan=2><%=yesdata.getMoicerate() %></td>
									<td colspan=2><%=yesdata.getResorderrate() %></td>
									
								</tr>
							</tbody>
							<thead>
								<tr>
									<td>订购数</td>
									<td>订购成功数</td>
									<td>低消订购成功</td>
									<td>流量包订购成功</td>
									<td>订购率</td>
									<td>订购成功率</td>
									<td>低消成功率</td>
									<td>流量包成功率</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=yesdata.getOrderall() %></td>
									<td><%=yesdata.getOrdersuc() %></td>
									<td><%=yesdata.getOrdersucdx() %></td>
									<td><%=yesdata.getOrdersucllb() %></td>
									<td><%=yesdata.getOrderrate() %></td>
									<td><%=yesdata.getOrdersucrate() %></td>
									<td><%=yesdata.getOrdersucdxrate() %></td>
									<td><%=yesdata.getOrdersucllbrate() %></td>
									<%} %>
								</tr>
							</tbody>
						</table> --%>

						<table class="layui-table" style="text-align: center">
							<thead>
								<tr>
									<td colspan=3>订购成功数</td>
									<td colspan=3>订购总数</td>
									<td colspan=3>订购成功率</td>
								</tr>
							</thead>
							<tbody>
							<%
							DataTotalService ds = (DataTotalService)ac.getBean("dataTotalService");
							Calendar calendar = Calendar.getInstance();
							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH);
							int day = calendar.get(Calendar.DATE);
							calendar.set(year, month, day-1);//昨天
							date = calendar.getTime();
							String adtime = sdf.format(date);
							DataTotal yesdata = ds.selectByAddTime(adtime);
							NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
						    nt.setMinimumFractionDigits(2);//设置百分数精确度2即保留两位小数
						    if(yesdata!=null){
						    int sucall = yesdata.getIcesuc()+yesdata.getOrdersuc();
							%>
							
							<tr>
								<td>冰淇淋</td>
								<td><%=yesdata.getIcesuc() %></td>
								<td rowspan=3><%=sucall %></td>
								<td>冰淇淋</td>
								<td><%=yesdata.getMoableice() %></td>
								<td rowspan=3><%=yesdata.getMoable() %></td>
								<td>冰淇淋</td>
								<td><%=nt.format(yesdata.getIcesuc()*1.0/yesdata.getMoableice()) %></td>
								<td rowspan=3><%=nt.format(sucall*1.0/yesdata.getMoable()) %></td>
							</tr>
							<tr>
								<td>低消</td>
								<td><%=yesdata.getOrdersucdx() %></td>
								<td>低消</td>
								<td><%=yesdata.getMoabledx() %></td>
								<td>低消</td>
								<td><%=nt.format(yesdata.getOrdersucdxrate()) %></td>
							</tr>
							<tr>
								<td>流量包</td>
								<td><%=yesdata.getOrdersucllb() %></td>
								<td>流量包</td>
								<td><%=yesdata.getMoablellb() %></td>
								<td>流量包</td>
								<td><%=nt.format(yesdata.getOrdersucllbrate()) %></td>
							</tr>
							<%}else{ %>
							<tr>
								<td colspan=9>无数据</td>
							</tr>
							<%} %>
							<tr>
								<td>其他新增业务</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							</tbody>
							<thead>
							<tr>
								<td>下行</td>
								<td>总数</td>
								<td>到达</td>
								<td>到达率</td>
								<td>上行回复数</td>
								<td>有效回复数</td>
								<td>有效回复率</td>
								<td>回复订购成功数</td>
								<td>营销成功率</td>
							</tr>
							</thead>
							<tbody>
							<% if(yesdata!=null){ %>
							<tr>
								<td>冰淇淋</td>
								<td rowspan=3><%=yesdata.getMtall() %></td>
								<td rowspan=3><%=yesdata.getMtsuc() %></td>
								<td rowspan=3><%=nt.format(yesdata.getMtrate()) %></td>
								<td rowspan=3><a><%=yesdata.getMoable() %></a></td>
								<td><%=yesdata.getMoableice() %></td>
								<td><%=nt.format(yesdata.getMoicerate()) %></td>
								<td><%=yesdata.getIcesuc() %></td>
								<td><%=(yesdata.getMtsuc()!=0?(nt.format(yesdata.getOrdersucdx()*1.0/yesdata.getMtsuc())):nt.format(0)) %></td>
							</tr>
							<tr>
								<td>低消</td>
								<td><%=yesdata.getMoabledx() %></td>
								<td><%=nt.format(yesdata.getModxrate()) %></td>
								<td><%=yesdata.getOrdersucdx() %></td>
								<td><%=(yesdata.getMtsuc()!=0?(nt.format(yesdata.getOrdersucdx()*1.0/yesdata.getMtsuc())):nt.format(0)) %></td>
							</tr>
							<tr>
								<td>流量包</td>
								<td><%=yesdata.getMoablellb() %></td>
								<td><%=nt.format(yesdata.getMollbrate()) %></td>
								<td><%=yesdata.getOrdersucllb() %></td>
								<td><%=(yesdata.getMtsuc()!=0?(nt.format(yesdata.getOrdersucllb()*1.0/yesdata.getMtsuc())):nt.format(0)) %></td>
							</tr>
							<%}else{ %>
							<tr>
								<td colspan=9>无数据</td>
							</tr>
							<%} %>
							<tr>
								<td>其他新增业务</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">运营数据走势</div>
					<div class="layui-card-body">
						<div id="chart3" style="width: 100%; height: 300px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../layui/layui.js"></script>
	<script type="text/javascript" src="../js/echarts.min.js"></script>
	<script>
			$(function(){
				// 基于准备好的dom，初始化echarts实例
		        var myChart3 = echarts.init(document.getElementById('chart3'));
				$.ajax({
					 url: '../home/getTotal',  
		             type: 'post',  
		             dataType: 'json',
		             data:{
		            	 	delay:7
		             },
		             error: errorFunction,  //错误执行方法    
		             success: succFunction //成功执行方法  
				});
				function errorFunction() {  
			        alert("error");  
			    }  
			    function succFunction(tt) { 
			    		var datename = [];
			        var mouse = [];
			        var mounuse = [];
			        var mtall = [];
			        var mtsuc = [];
			        var ordersuc = [];
			        var orderdis = [];
			        var datestr;
			        var json = eval(tt); //数组  
			        console.log(json);
			        datename = json['xaix'];
			        var mouserjson = eval(json['mouser']);
			       	var mounuserjson = eval(json['mounuser']);
			       	var mtalljson = eval(json['mtall']);
			       	var mtsucjson = eval(json['mtsuc']);
			       	var ordersucjson = eval(json['ordersuc']);
			       	var orderdisjson = eval(json['orderdis']);
			        $.each(datename, function (index, item) {
			          	mouse[index] = mouserjson[item];
			          	mounuse[index] = mounuserjson[item];
			          	mtall[index] = mtalljson[item];
			          	mtsuc[index] = mtsucjson[item];
			          	ordersuc[index] = ordersucjson[item];
			          	orderdis[index] = orderdisjson[item];
			        });  
			        console.log(mouse);
				
				var orderoption = {
					    title: {
					        text: '七天内数据走向',
					        subtext: '汇视达通'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['下行总','下行成功','订单有效','订单无效','上行有效','上行无效']
					    },
					    toolbox: {
					        show: true,
					        feature: {
					            dataZoom: {
					                yAxisIndex: 'none'
					            },
					            dataView: {readOnly: false},
					            magicType: {type: ['line', 'bar']},
					            restore: {},
					            saveAsImage: {}
					        }
					    },
					    xAxis:  {
					        type: 'category',
					        boundaryGap: false,
					        data: datename
					    },
					    yAxis: {
					        type: 'value',
					        axisLabel: {
					            formatter: '{value}'
					        }
					    },
					    series: [
					    		{
					    			name:'下行总',
					    			type:'line',
					    			data:mtall,
					    			markPoint:{
					    				data:[
					    					{type:'max',name:'最大值'},
					    					{type:'min',name:'最小值'}
					    				]
					    			}
					    		},
					    		{
					    			name:'下行成功',
					    			type:'line',
					    			data:mtsuc,
					    			markPoint:{
					    				data:[
					    					{type:'max',name:'最大值'},
					    					{type:'min',name:'最小值'}
					    				]
					    			}
					    		},
					    		{
					    			name:'上行有效',
					    			type:'line',
					    			data:mouse,
					    			markPoint:{
					    				data:[
					    					{type:'max',name:'最大值'},
					    					{type:'min',name:'最小值'}
					    				]
					    			}
					    		},
					    		{
					    			name:'上行无效',
					    			type:'line',
					    			data:mounuse,
					    			markPoint:{
					    				data:[
					    					{type:'max',name:'最大值'},
					    					{type:'min',name:'最小值'}
					    				]
					    			}
					    		},
					        {
					            name:'订单有效',
					            type:'line',
					            data:ordersuc,
					            markPoint: {
					                data: [
					                    {type: 'max', name: '最大值'},
					                    {type: 'min', name: '最小值'}
					                ]
					            }
					        },
					        {
					            name:'订单无效',
					            type:'line',
					            data:orderdis,
					            markPoint: {
					                data: [
					                    {type: 'max', name: '最大值'},
					                    {type: 'min', name: '最小值'}
					                ]
					            }
					        }
					    ]
					};
		        // 使用刚指定的配置项和数据显示图表。
		        myChart3.setOption(orderoption);
			    }
			});
		</script>
</body>
</html>