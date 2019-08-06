<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
<link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">
<!-- 全局js -->
<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx!}/assets/js/plugins/echarts/echarts-all.js"></script>
</head>
<body class="gray-bg">

    <div id="main" style="width:80%;height:80%;left:center;top:middle;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.getJSON('${ctx!}/admin/wechatLog/datelistStat', function (data) {
            if(data.code == 0)
            {
            	<!-- 一般角色 -->
            	<@shiro.hasRole name="normal">
                var xdata = new Array();
                var ydata = new Array();
                for(var i = data.data.length-1;i>=0;i--){
                    xdata.push(data.data[i].date);
                    ydata.push(data.data[i].count);
                }
                // 填入数据
                myChart.setOption({
                    title: {
                        text: '最近7日加粉数量统计'
                    },
                    tooltip: {show: true,trigger: 'item'},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: true},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    legend: {
                        data:['数量']
                    },
                    xAxis: {
                        data: xdata
                    },
                    yAxis: {
                    },
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: ydata
                    }]
                });
				</@shiro.hasRole>
				<!-- 管理员角色 -->
				<@shiro.hasRole name="administrator">
				var xdata = new Array();
                var ydata = new Array();
                for(var i = data.data.length-1;i>=0;i--){
                    xdata.push(data.data[i].username);
                    ydata.push(data.data[i].count);
                }
                // 填入数据
                myChart.setOption({
                    title: {
                        text: '各个用户的加粉数量统计'
                    },
                    tooltip: {show: true,trigger: 'item'},
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: true},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    legend: {
                        data:['数量']
                    },
                    xAxis: {
                        data: xdata
                    },
                    yAxis: {
                    },
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: ydata
                    }]
                });
				</@shiro.hasRole>
            }

        });
    </script>
</body>
</html>