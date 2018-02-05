<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
<link type="text/css" rel="stylesheet"
	href="jquery-ui/jquery-ui.min.css" media="screen" />
<link type="text/css" rel="stylesheet"
	href="js/jquery.ui.plupload/css/jquery.ui.plupload.css" media="screen" />
<style>
body {
	background: #fff;
}

#uploader {
	max-width: 650px;
	margin: 50px;
}

#load {
	margin-left: 838px;
	margin-top: 60px;
}
</style>
<style type="text/css">
#mask {
	background-color: #ccc;
	opacity: 0.7;
	filter: alpha(opacity = 70);
	position: absolute;
	left: 0;
	top: 0;
	z-index: 1000;
}

#load {
	left: 0px;
	top: 200px;
	position: fixed;
	z-index: 1001;
}

.content {
	margin-bottom: 20px;
	margin-left: 90px;
}
}
</style>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="jquery-ui/jquery-ui.min.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="js/plupload.full.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="js/jquery.ui.plupload/jquery.ui.plupload.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/i18n/zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/layer.js" charset="UTF-8"></script>
<script type="text/javascript">
	$(function() {
		$("#uploader").plupload({
			// General settings
			runtimes : 'html5,flash,silverlight,html4',
			url : 'upload',//这里是action的name
			max_file_size : '2mb',
			max_file_count : 20,
			chunk_size : '1mb',
			// Specify what files to browse for
			filters : [ {
				title : "上传文件类型",
				extensions : "csv,txt"
			} ],
			// Views to activate
			views : {
				list : true,
				thumbs : true, // Show thumbs
				active : 'thumbs'
			},
			// Flash settings
			flash_swf_url : 'js/Moxie.swf',
			// Silverlight settings
			silverlight_xap_url : 'js/Moxie.xap'
		});

	});
	function HideLoad() {
		document.getElementById('load').style.display = 'none';
	}
	function Polling() {
		document.getElementById('load').style.display = 'block';
		var sWidth = document.body.scrollWidth
				|| document.documentElement.scrollWidth;
		var sHeight = document.body.scrollHeight
				|| document.documentElement.scrollHeight;

		//获取页面的可视区域高度和宽度
		var wHeight = document.documentElement.clientHeight
				|| document.body.clientHeight;

		//创建遮罩层
		var oMask = document.createElement("div");
		oMask.id = "mask";
		oMask.style.height = sHeight + 288 + "px";
		oMask.style.width = sWidth + "px";
		document.body.appendChild(oMask);

		var oLoad = document.createElement("div");
		oLoad.id = "load";
		oLoad.innerHTML = "<div class='content'><p>正在计算.....</p></div>";
		document.body.appendChild(oLoad);

	}
	function select() {
		var val = $("#select").val();
		document.getElementById("SN").value = val;
	}
</script>
</head>
<body onload="HideLoad()">
	<div id="main" style="width: 100%; height: 100%;">
		<div id="left" style="margin-left: 622px;margin-top: 170px">
			<div id="uploader" style="margin-left: 20px;">
				<p>Your browser doesn't have Flash, Silverlight or HTML5
					support.</p>
				s	System.out.println(classpath);
		System.out.println(path);

			</div>
		</div>

		<div style="margin-left: 1203px;margin-top: 70px">
			<form action="evaluate" onsubmit="Polling()" method="get">
				<input id="evaluate" type="submit" value="开始评估"> <input
					id="SN" type="hidden" name="CommunityMethod">
			</form>
		</div>

		<div style="margin-left: 643px;margin-top: -38px;">
			<span style="color: #428bca"><font size="5">选择算法</font></span> <select
				id="select" style="width: 100px; height: 36px;" onchange="select()">
				<option value="cpm" selected="selected">cpm</option>
				<option value="louvain">louvain</option>
				<option value="BMLPA">BMLPA</option>
			</select>
		</div>
	</div>
	<div id="mask"></div>
	<div id="load">
		<img id="img" alt="" src="images/load.gif">
	</div>

</body>
</html>