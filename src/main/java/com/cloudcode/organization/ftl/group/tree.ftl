<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv" xtype="hh_content"><form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/groups/createGroup" method="post">
<div class="container" id="layout" style="width: 100%;">

 <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div id="menuContent" >
        <ul id="treeDemo" class="ztree" ></ul> 
      </div>
   
       <input type="hidden" value="" id="oid" name="id" >
	 </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
       <button type="button" id="selector" class="btn btn-default">save</button>
     </div>
  </div>


</div></form>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<script type="text/javascript">
var width = 720;
	var height = 450
var hm = $("body").wHumanMsg();
$(function () {
	$('#selector').on('click',function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),
		v = "",id="";
		if(nodes.length >1){
			ajaxframework.createDialog("操作提示！","请选择一条数据！",{});
			return;
		}
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name;// + ",";
			id += nodes[i].id;// + ",";
		}
		//if (v.length > 0 ) v111 = v.substring(0, v.length-1);		
		var params = $.ajaxframework.getIframeParams();
		var formData ={};
		formData.id = id;
		formData.name=v;
		console.log(params);
		params.callback(formData);
		Dialog.closethis();	
	
	});
});
function zTreeOnDblClick(event, treeId, treeNode) {
     $('#selectText').val(treeNode.name); 
     $('#selectTextVal').val(treeNode.tId);
      //$("#treeDemo").css('display','none');
};
	var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			},
			async: {
		        enable: true,
		        async : true, 
		      	dataType: 'JSON',
		        //返回的JSON数据的名字
		        dataName: 'treeNodes',
		        autoParam:["id=id"],
		        url:'${request.getContextPath()}/groups/queryDataTreeByPid?'
		       }
		};		
		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "",id="";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				id += nodes[i].id+ ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);			
			$('#selectText').val(v); 
    		$('#selectTextVal').val(id);
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
</script>
</div>
</body>
</html>