<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv">
<div class="container" id="layout">
<form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/users/createUser" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
	    <div class="col-sm-4">
	      <input type="text" name="name" class="form-control" id="name" placeholder="姓名">
	    </div>
	    <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
	    <div class="col-sm-4">   
	     	<label class="radio-inline">
			    <input type="radio" name="sex" id="sex-male" value="0" checked>
				    男
			  </label>
			<label class="radio-inline">
			    <input type="radio" name="sex" id="sex-female" value="1">
			   		 女
			  </label>
		</div>
    </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">手机</label>
	    <div class="col-sm-4">
	      <input type="text" name="phone" class="form-control" id="phone" placeholder="手机">
	    </div>
     <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>
	    <div class="col-sm-4">
	      <input type="text" name="email" class="form-control" id="email" placeholder="邮箱">
	    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">描述</label>
	    <div class="col-sm-10">
	      <input type="text" name="description" class="form-control" id="description" placeholder="描述">
	    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
       <button type="button" id="updateButton" class="btn btn-default">save</button>
    </div>
  </div>
   <input type="hidden" value="" id="oid" name="id" >
</form>

</div>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<script type="text/javascript">
$('#selectText').on("focus", function(){
   showUser(); 
});
$('#selectBtn').on('click',function(){
  showUser();  
});
function showUser() {
	var cityObj = $("#selectTextVal");
	var cityOffset = $("#selectTextVal").offset();
	$("#UserContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideUser() {
	$("#UserContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "selectBtn" || event.target.id == "citySel" || event.target.id == "UserContent" || $(event.target).parents("#UserContent").length>0)) {
		hideUser();
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
				        url: '${request.getContextPath()}/users/'+$("#oid").val()+'/updateUser',
				        type: 'post',
				        dataType: 'json',
				        data:$('form#myFormId').serialize(),
				        success: function(data) {
				       		 $('body').wHumanMsg('theme', 'black').wHumanMsg('msg', '数据保存成功！', {fadeIn: 300, fadeOut: 300});
				       		  debugger;
				       		   divInDialogs[0].close();
				        	 //$('.ui-dialog-titlebar-close').trigger('click');
				           }
			    });
		});
   }else{
	    $('#updateButton').click( function() {
			    if($('#selectTextVal').val() ==''){
			     	$('#selectTextVal').val('root');
			     }
			    $.ajax({
			        url: '${request.getContextPath()}/users/createUser',
			        type: 'post',
			        dataType: 'json',
			        data: $('form#myFormId').serialize(),
			        success: function(data) {
			       		 $('body').wHumanMsg('theme', 'black').wHumanMsg('msg', '数据保存成功！', {fadeIn: 300, fadeOut: 300});
			       		 debugger;
			       		 divInDialogs[0].close();
			       		 //$('.ui-dialog-titlebar-close').trigger('click');
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
		<#if user?exists>  
			$('#name').val('${user.name!''}');
			$('#phone').val('${user.phone!''}');
			$('#email').val('${user.email!''}');
			$('input:radio[name="sex"]').filter('[value=${user.sex!''}]').attr('checked', true);
			$('#oid').val('${user.id!''}');
		</#if>
	}
});
/*
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
		        url:'${request.getContextPath()}/users/queryDataTreeByPid'
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
			//$.fn.zTree.init($("#treeDemo"), setting);
		});*/
</script>
</div>
</body>
</html>