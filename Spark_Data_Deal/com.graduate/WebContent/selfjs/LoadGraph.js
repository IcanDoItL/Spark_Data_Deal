var JsPath = "js/echarts.js";
var JqueryPath = "js/jquery-1.8.3.min.js";
document.write('<script type="text/javascript" src="' + JsPath + '"></script>');
document.write('<script type="text/javascript" src="' + JqueryPath
		+ '"></script>');
function MainFirst() {
	// body...
	var first = document.getElementById('first');
	var second = document.getElementById('second');
	var third = document.getElementById('third');
	DrawChar(first);
	DrawChar(second);
	DrawChar(third);
}
function DrawChar(divId) {
	var select = "";
	var text = "";
	var subtext = "";
	if (divId.id == 'first') {
		text = "Precision";
		subtext = "正确率";
		select = "precision";
	} else if (divId.id == 'second') {
		text = "Recall";
		subtext = "召回率";
		select = "recall";

	} else if (divId.id == 'third') {
		text = "F_measure";
		subtext = "F均值";
		select = "f_measure";

	}
	var chartData = echarts.init(divId);
	var option = {
		title : {
			text : text,
			subtext : subtext
		},
		tooltip : {
			trigger : 'axis'// item axis
		},
		legend : {
			data : [ 'ShowReport' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : {
			type : 'category',
			data : [ '1000','2000','5000','10000'],
			axisLabel : {
				interval : 0
			}
		},
		yAxis : {},
		series : [ {
			name : 'result',
			type : 'line',
			data : []
		}, {
			name : 'result',
			type : 'line',
			data : []
		} ]
	};
	$.ajax({
		type : "GET",
		async : false,// 非异步
		url : 'json/result1.json',
		dataType : 'json',
		success : function(res) {
			if (res) {
				if (select == 'precision') {
					option.series[0].data = res.precision;
				} else if (select == 'recall') {
					option.series[0].data = res.recall;
				} else if (select == 'f_measure') {
					option.series[0].data = res.f_measure;
				}
				chartData.setOption(option);
			}
		}
	});
}