<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv">
<div class="container" id="layout">
<form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/jobs/createMenu" method="post">
  <div class="form-job">
    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
	    <div class="col-sm-4">
	      <input type="text" name="text" class="form-control" id="text" placeholder="名称">
	    </div>
     <label for="inputPassword3" class="col-sm-2 control-label">父菜单</label>
    <div class="col-sm-4">
    <div class="input-group">
      <input type="text" class="form-control" name="selectText" id="selectText">
       <input type="hidden" name="node" id="selectTextVal">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" name="selectBtn" id="selectBtn">选择</button>
      </span>
      <div id="menuContent"  style="position:absolute;left: 10px;top: 29px;z-index: 100;background: #ebebeb;display:none"  class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree" ></ul> 
      </div>
    </div><!-- /input-job -->
       <input type="hidden" value="" id="oid" name="id" >
	</div>
    </div>
   <div class="form-job">
    <label for="inputEmail3" class="col-sm-2 control-label">简称</label>
	    <div class="col-sm-4">
	      <input type="text" name="shortName" class="form-control" id="shortName" placeholder="简称">
	    </div>
     <label for="inputEmail3" class="col-sm-2 control-label">编码</label>
	    <div class="col-sm-4">
	      <input type="text" name="code" class="form-control" id="code" placeholder="编码">
	    </div>
  </div>
  <div class="form-job">
    <label for="inputEmail3" class="col-sm-2 control-label">描述</label>
	    <div class="col-sm-10">
	      <input type="text" name="description" class="form-control" id="description" placeholder="描述">
	    </div>
  </div>
  <div class="form-job">
    <div class="col-sm-offset-2 col-sm-10">
       <button type="button" id="updateButton" class="btn btn-default">save</button>
    </div>
  </div>
</form>

</div>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<script type="text/javascript">
$('#selectText').on("focus", function(){
   showMenu(); 
});
$('#selectBtn').on('click',function(){
  showMenu();  
});
function showMenu() {
	var cityObj = $("#selectTextVal");
	var cityOffset = $("#selectTextVal").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "selectBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
var hm = $("body").wHumanMsg();
$(function () {
   if('${entityAction}' =='update'){
	   $('#updateButton').click( function() {
				     if($('#selectTextVal').val() ==''){
				     	$('#selectTextVal').val('root');
				     }
	      		   $.ajax({
				        url: '${request.getContextPath()}/jobs/'+$("#oid").val()+'/updateMenu',
				        type: 'post',
				        dataType: 'json',
				        data:$('form#myFormId').serialize(),
				        success: function(data) {
				       		 $('body').wHumanMsg('theme', 'black').wHumanMsg('msg', '数据保存成功！', {fadeIn: 300, fadeOut: 300});
				        	 $('.ui-dialog-titlebar-close').trigger('click');
				           }
			    });
		});
   }else{
	    $('#updateButton').click( function() {
			    if($('#selectTextVal').val() ==''){
			     	$('#selectTextVal').val('root');
			     }
			    $.ajax({
			        url: '${request.getContextPath()}/jobs/createMenu',
			        type: 'post',
			        dataType: 'json',
			        data: $('form#myFormId').serialize(),
			        success: function(data) {
			       		 $('body').wHumanMsg('theme', 'black').wHumanMsg('msg', '数据保存成功！', {fadeIn: 300, fadeOut: 300});
			       		 $('.ui-dialog-titlebar-close').trigger('click');
			         }
			    });
		});
    }
	$("#modal-message").dialog({
	    autoOpen: false,
	    modal: true,
	    buttons: {
	        Ok: function () {
	            $(this).dialog("close");
	        }
	    }
	});
	if('${entityAction}' =='update'){
		<#if menu?exists>  
		$('#text').val('${menu.text!''}');
		$('#action').val('${menu.action!''}');
		$('#selectTextVal').val('${menu.node!''}');
		$('#oid').val('${menu.id!''}');
		</#if>
	}
});
function zTreeOnDblClick(event, treeId, treeNode) {
     $('#selectText').val(treeNode.name); 
     $('#selectTextVal').val(treeNode.tId);
      $("#treeDemo").css('display','none');
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
		        url:'${request.getContextPath()}/jobs/queryDataTreeByPid'
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
				v += nodes[i].name;// + ",";
				id += nodes[i].id;// + ",";
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