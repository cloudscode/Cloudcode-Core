<!DOCTYPE html>
<html lang="en">
<head>
 	<title>角色信息</title>
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
<script src="/cccommon/static/jquery/ui/bootstrap/third-party/jqGrid/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="/cccommon/static/jquery/ui/bootstrap/third-party/jqGrid/jqGrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<!--end jqGrid-->
<#include "classpath:com/cloudcode/framework/common/ftl/require.ftl"/>
<script type="text/javascript">
var grid = null;
$(function(){
    if ($.fn.jqGrid){
        grid = $("#jqGrid01").jqGrid({
            url:"${request.getContextPath()}/roles/query",
            datatype: "json",
            height: 250,
            rowNum: 10,
            prmNames : {PageRange:{page:"page",rows:"rows"}  },
            rowList: [10,20,30],
            colNames:['Id', '名称','类型','状态'],
            colModel:[
                {name:'id',index:'id', width:60, hidden:true},
                {name:'name',index:'name', width:100},
                {name:'type',index:'type', width:90,formatter: function(cellvalue, options, rowObject){
                		if(cellvalue =='System'){
                			return '系统内置角色';
                		}else if(cellvalue =='Admin'){
                			return '管理角色';
                		}else if(cellvalue =='Bussiness'){
                			return '业务角色';
                		}
                	}},
                {name:'status',index:'status', width:90,formatter: function(cellvalue, options, rowObject){
                		if(cellvalue ==0){
                			return '正常';
                		}else{
                			return '冻结';
                		}
                	}}
            ],
            autowidth: true,
            height: "auto",            
            pager: "#jqGridPager01",
            viewrecords: true,
            caption: "角色信息",
            hidegrid:false,
            multiselect: true,
            altRows: true
        });     
    }
});
requirejs(['jquery','Dialog','Request','jqueryui','main','text','select','date','radio','checkbox','textarea','password','ckeditor','button','validation','Request','combobox'], function( $, Dialog,Request ) {
	
	var    options={};
	options.title='角色信息';
   
	options.width=1000;
	options.height=800;
	grid.trigger("reloadGrid");
	options.params={
		callback : function(data) {
		   grid.trigger("reloadGrid");
		}
	};
	$( "#add" ).click(function(){
		options.url='${request.getContextPath()}/roles/collection/toDetail';
		Dialog.open(options);
	});
	$( "#edit" ).click(function(){
		var id; 
		id = grid.jqGrid('getGridParam','selrow');
		if(null != id && id.toString() != null && id.toString() != ""){
			    options.url='${request.getContextPath()}/roles/'+id+'/toDetail';
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