<!DOCTYPE html>
<html lang="en">
<head>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv">
<div class="container" id="layout">
<form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/menus/createMenu" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">菜单名称</label>
    <div class="col-sm-4">
      <input type="text" name="text" class="form-control" id="text" placeholder="菜单名称">
    </div>
     <label for="inputPassword3" class="col-sm-2 control-label">父菜单</label>
    <div class="col-sm-4">
    <div class="input-group">
      <input type="text" class="form-control" name="selectText" id="selectText">
       <input type="hidden" name="selectTextVal" id="selectTextVal">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" name="selectBtn" id="selectBtn">选择</button>
      </span>
      <div id="menuContent"  style="position:absolute;left: 10px;top: 29px;z-index: 100;background: #ebebeb;display:none"  class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree" ></ul> 
      </div>
    </div><!-- /input-group -->
       <input type="hidden" value="" id="oid" name="id" >
	</div>
    </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">动作</label>
    <div class="col-sm-10">
      <input type="text" name="action" class="form-control" id="action" placeholder="动作">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
       <button type="button" id="updateButton" class="btn btn-default">save</button>
    </div>
  </div>
</form>

</div>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<script type="text/javascript">
$('#selectText').on("focus", function(){
   showMenu(); // $("#treeDemo").css('display','block'); 
});
$('#selectBtn').on('click',function(){
  showMenu();  // $("#treeDemo").css('display','block'); 
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
$(function () {
    //####### Buttons
   // $("#layout button,.button,#sampleButton").button();
   if('${entityAction}' =='update'){
	   $('#updateButton').click( function() {
	      		 
	      		    $.ajax({
			        url: '${request.getContextPath()}/menus/'+$("#oid").val()+'/updateMenu',
			        type: 'post',
			        dataType: 'json',
			        data:$('form#myFormId').serialize(),
			        success: function(data) {
			       			var dialog = ajaxframework.createDialog("操作提示！","数据保存成功！",{ modal: true,
								    buttons: {
								        Ok: function () {
								            $(this).dialog("close");
								        }
								    }});
			                 }
			    });
		});
   }else{
	    $('#updateButton').click( function() {
			    $.ajax({
			        url: '${request.getContextPath()}/menus/createMenu',
			        type: 'post',
			        dataType: 'json',
			        data: $('form#myFormId').serialize(),
			        success: function(data) {
			        		var dialog = ajaxframework.createDialog("操作提示！","数据保存成功！",{ modal: true,
								    buttons: {
								        Ok: function () {
								            $(this).dialog("close");
								        }
								    }});
			        	
			         }
			    });
		});
    }
	$("#modal-message").dialog({
	    autoOpen: false,
	    modal: true,
	    buttons: {
	        Ok: function () {
	        	//$('#divInDialog').dialog("close");
	            $(this).dialog("close");
	           
	        }
	    }
	});
	if('${entityAction}' =='update'){
		<#if menu?exists>  
		$('#text').val('${menu.text!''}');
		$('#action').val('${menu.action!''}');
		$('#oid').val('${menu.id!''}');
		</#if>
	}
});
function zTreeOnDblClick(event, treeId, treeNode) {
    //alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
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
				id += nodes[i].id + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);			
			$('#selectText').val(v); 
    		$('#selectTextVal').val(id);
		}
		var zNodes =[
			{ id:1, pId:0, name:"父节点1 - 展开", open:true},
			{ id:11, pId:1, name:"父节点11 - 折叠"},
			{ id:111, pId:11, name:"叶子节点111"},		
			{ id:12, pId:1, name:"父节点12 - 折叠"},
			{ id:121, pId:12, name:"叶子节点121"},
			{ id:122, pId:12, name:"叶子节点122"}
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
</script>
</div>
</body>
</html>