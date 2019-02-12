<!-- 首页界面中图表展示的界面 -->
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mtpt.bean.DataTotal"%>
<%@page import="com.mtpt.service.impl.DataTotalService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="../layui/css/layui.css" />
		<script src="http://code.jquery.com/jquery-1.8.0.min.js" type="text/javascript"></script>  
		<script type="text/javascript" src="../layer/layer.js"></script>
	</head>
	<body class="layui-layout-body" style="height:1000px">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
		
		<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
					<label>数据统计</label>
					<select id="choicemonth" style="float:right; transform:translateY(50%);">
						<option value="7">7</option>
						<option value="14">14</option>
						<option value="30">30</option>
					</select>
					</div>
					<div class="layui-card-body">
						<div id="chart3" style="width: 100%; height: 300px;"></div>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">数据详情</div>
					<div class="layui-card-body">
						<table class="layui-hide" layer-filter="datadetail" id="datadetail"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../layui/layui.js"></script>
		<script type="text/javascript" src="../js/echarts.min.js" ></script>
		<script>
			layui.use('table',function(){
				var table = layui.table;
				table.render({
					elem:'#datadetail',
					url : '../home/getTotaldelay',
					cellMinWidth : 80,
					height:500,
					width:window.innerWidth*0.94,
					loading:true,
					cols : [[ 
							{
								type : 'checkbox',
								fixed : 'left'
							},
							{
								field : 'time',
								title : '时间',
								sort : true,
								unresize : true,
								fixed : 'left',
								width:80,
								align:'center'
							},
							{
								field : 'mtall',
								title : '下行总',
								width:80,
								align:'center'
							},
							{
								field : 'mtsuc',
								title : '下行成功',
								align:'center',
								width:100
							},
							{
								field : 'moall',
								title : '上行总',
								align:'center'
							},
							{
								field : 'moable',
								title : '上行有效',
								align:'center',
								width:100
							},
							{
								field : 'moabledx',
								title : '上行低消',
								align:'center',
								width:100
							},
							{
								field : 'moableice',
								title : '上行冰激凌',
								align:'center',
								width:120
							},
							{
								field : 'moablellb',
								title : '上行流量包',
								align:'center',
								width:120
							},
							{
								field : 'orderall',
								title : '订单总',
								align:'center'
							},
							{
								field : 'ordersuc',
								title : '订单成功',
								align:'center',
								width:100
							},
							{
								field:'ordersucdx',
								title:'低消成功',
								align:'center',
								width:100
							},
							{
								field:'ordersucllb',
								title:'流量包成功',
								align:'center',
								width:120
							},
							{
								field:'orderdissuc',
								title:'订单不成功',
								align:'center',
								width:120
							},
							{
								field:'orderdissucdx',
								title:'低消不成功',
								align:'center',
								width:120
							},
							{
								field:'orderdissucllb',
								title:'流量包不成功',
								align:'center',
								width:130
							},
							{
								field:'icebook',
								title:'冰激凌预定',
								align:'center',
								width:120
							},
							{
								field:'mtrate',
								title:'下行率',
								align:'center'
							},
							{
								field:'morate',
								title:'回复率',
								align:'center'
							},
							{
								field:'modxrate',
								title:'低消回复率',
								align:'center',
								width:120
							},
							{
								field:'moicerate',
								title:'冰激凌回复率',
								align:'center',
								width:130
							},
							{
								field:'mollbrate',
								title:'流量包回复率',
								align:'center',
								width:130
							},
							{
								field:'orderrate',
								title:'订购率',
								align:'center'
							},
							{
								field:'resorderrate',
								title:'订购回复率',
								align:'center',
								width:120
							},
							{
								field:'ordersucrate',
								title:'订购成功率',
								align:'center',
								width:120
							},
							{
								field:'ordersucdxrate',
								title:'低消订购成功率',
								align:'center',
								width:150
							},
							{
								field:'ordersucllbrate',
								title:'流量包订购成功率',
								align:'center',
								width:200
							} ]] ,
					page : true,
					limit:10,
					id : 'totallist',
					where:{
						delay:$("#choicemonth").val()
					}
				});
			});
			
			$(function(){
				// 基于准备好的dom，初始化echarts实例
		        var myChart3 = echarts.init(document.getElementById('chart3'));
		        myChart3.showLoading();
		        //自动加载折线图中的数据
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
				
				
		        //通过下拉选框选择需要查询的月份
		        $("#choicemonth").change(function(){
		        	$.ajax({
					 url: '../home/getTotal',  
		             type: 'post',  
		             dataType: 'json',
		             data:{
		            	 	delay:$("#choicemonth").val()
		             },
		             error: errorFunction,  //错误执行方法    
		             success: succFunction //成功执行方法  
				});
		        	layui.use('table',function(){
		        		var table = layui.table;
		        		table.reload('totallist',{where:{
		        			delay:$("#choicemonth").val()
		        		}});
		        	});
		        });
		      
				function errorFunction() {  
			       layer.msg("当前的数据有问题");
			    }  
				
				
				
				//一个月的数据展示成功处理的方法
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
				var moordertotal = {
				    title: {
				        text: '',
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
				myChart3.hideLoading();
		        // 使用刚指定的配置项和数据显示图表。
		        myChart3.setOption(moordertotal);
			    }
			});
		</script>
	</body>
</html>