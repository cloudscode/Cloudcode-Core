<!DOCTYPE html>
<html lang="en">
<head>
   <title>highchart实例</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div xtype="hh_content">
<div class="container" id="layout" style="width: 100%;">
<div id="container" style="min-width:800px;height:400px"></div>
</div>
<#include "classpath:com/cloudcode/framework/common/ftl/require.ftl"/>
<script type="text/javascript">
var page={};
requirejs(['jquery','jquery','highcharts'], function( $, jQuery,highcharts ) {
	var width = 720;
	var height = 450
	
	 
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'My first Highcharts chart'
            },
            xAxis: {
                categories: ['苹果', '香蕉', '橙子']   //指定x轴分组
            },
            yAxis: {
                title: {
                    text: 'something'
                }
            },
             series: [{                                //指定数据列
                name: '小明',                          //数据列名
                data: [1, 0, 4]                        //数据
            }, {
                name: '小红',
                data: [5, 7, 3]
            }]
        });
});
</script>
</div>
</body>
</html>