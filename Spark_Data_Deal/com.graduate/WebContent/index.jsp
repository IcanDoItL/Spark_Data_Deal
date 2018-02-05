<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>echarts-JSON请求数据</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="js/echarts.js" charset="UTF-8"></script>
<script>
function name() {
		var chart = document.getElementById('chart');
		var chartData = echarts.init(chart);

		var option={
			title : {
				text : '异步数据加载示例'
			},
			tooltip : {},
			legend : {
				data : [ '销量' ]
			},
			xAxis : {
				data : ['first']
			},
			yAxis : {},
			series : [ {
				name : '销量',
				type : 'bar',
				data : []
			} ]
		};
       $.ajax({
    	   type: "GET",
    	   async: false,
    	   url:"json/data.json",
    	   dataType:'json',
          success:function(res)
          {
        	  if(res)
        		  {
        		  option.series[0].data = res.data;
        		  chartData.setOption(option);  
        		  }
          }
       });
	}
</script>
</head>
<body onload="name()">
	<div id="chart" style="width: 600px; height: 700px;">
	<p>AAAAA</p>
	</div>
</body>
</html>
