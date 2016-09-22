<!DOCTYPE html>
<html lang="en">
<head>
 	<title>部门信息</title>
    <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true">
<div id="dialogDiv">
	<div class="container" id="layout">
			<div class="col-lg-9 col-sm-9" id="article">
			<section id="button">
			        <button id="add" class="ui-button-success">添加</button>
			        <button id="edit" class="ui-button-primary">编辑</button>
			        <button id="view" class="ui-button-warning">查看</button>
			        <button id="delete" class="ui-button-danger">删除</button>
			        <button id="refresh" class="ui-button-info">刷新</button>
			</section>
			</div>
	</div>
	<div class="row">
	    <div class="col-lg-12 col-sm-12">
	        <table id="jqGrid01" style="width:100%;"></table>
	        <div id="jqGridPager01"></div>
	    </div>
	</div>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<!--jqGrid-->
<script src="${request.getContextPath()}/static/jquery/ui/bootstrap/third-party/jqGrid/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/static/jquery/ui/bootstrap/third-party/jqGrid/jqGrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/static/jquery/ui/bootstrap/third-party/jqGrid/jqGrid/js/grid.treegrid.js" type="text/javascript"></script>
<!--end jqGrid-->
<#include "classpath:com/cloudcode/framework/common/ftl/require.ftl"/>
<script type="text/javascript">
var grid = null;
$(function(){
    if ($.fn.jqGrid){
        grid = $("#jqGrid01").jqGrid({
            treeGrid: true,
            treeGridModel: 'adjacency',
            ExpandColumn: 'name',
            url:"${request.getContextPath()}/depts/queryTree",
            datatype: "json",
            height: 250,
            rowNum: 10,
            prmNames : {PageRange:{page:"page",rows:"rows"}  },
            rowList: [10,20,30],
            colNames:['Id', '名称','编码','简称'],
            colModel:[
                {name:'id',index:'id', width:60, hidden:true},
                {name:'name',index:'name', width:100},
                {name:'code',index:'node', width:90},
                {name:'shortName',index:'shortName', width:90}
            ],
            autowidth: true,
            height: "auto",            
            pager: "#jqGridPager01",
            viewrecords: true,
            caption: "部门信息",
            hidegrid:false,
            multiselect: true,
            altRows: true
        });     
    }
});
requirejs(['jquery','Dialog','Request','jqueryui','main','text','select','date','radio','checkbox','textarea','password','ckeditor','button','validation','Request','combobox'], function( $, Dialog,Request ) {
	
	var    options={};
	options.title='集团选择';
   
	options.width=1000;
	options.height=800;
	grid.trigger("reloadGrid");
	options.params={
		callback : function(data) {
		   grid.trigger("reloadGrid");
		}
	};
	$( "#add" ).click(function(){
		options.url='${request.getContextPath()}/depts/collection/toDetail';
		Dialog.open(options);
	});
	$( "#edit" ).click(function(){
		var id; 
		id = grid.jqGrid('getGridParam','selrow');
		if(null != id && id.toString() != null && id.toString() != ""){
			    options.url='${request.getContextPath()}/depts/'+id+'/toDetail';
				Dialog.open(options);
		}else{
			    Dialog.error("请选择一条要编辑的数据！");	
		}
	}); 
});  
</script>
	<div id="divInDialog" style="display:none">
	</div>
</div>
</body>
</html>