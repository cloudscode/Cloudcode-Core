<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv" xtype="hh_content">
<div class="container" id="layout" style="width: 100%;">
<form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/groups/createGroup" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
	    <div class="col-sm-4">
	      <input type="text" name="text" class="form-control" id="text" placeholder="名称">
	    </div>
     <label for="inputPassword3" class="col-sm-2 control-label">父菜单</label>
   <div class="col-sm-4">
   
       <div class="input-group datepicker date"> 
        <input type="text" name="idCodeName" id="idCodeName" value="" required="true" class="form-control required" style="background-color:white;cursor:default;">  
        <span class="input-group-addon" id="selectBtn">
        <i class="glyphicon glyphicon-user">
        </i>
        </span>
        <input type="hidden" value="" id="idCode" name="idCode" >
        </div>
	</div>
    </div>
   <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">简称</label>
	    <div class="col-sm-4">
	      <input type="text" name="shortName" class="form-control" id="shortName" placeholder="简称">
	    </div>
   		<label for="inputEmail3" class="col-sm-2 control-label">编码</label>
	    <div class="col-sm-4">
	      <input type="text" name="code" class="form-control" id="code" placeholder="编码">
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
</form>
<div id="divInDialog2" style="display:none">
	</div>
</div>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>

<script type="text/javascript">
	var width = 1000;
	var height = 600;
	$('#selectBtn').on('click',function(){
	
	    var    options={};
	  	options.title='集团选择';
	  	options.url='${request.getContextPath()}/groups/tree';
		options.width=500;
		options.height=600;
		options.params={
				
				callback : function(data) {
				    $("#idCode").val(data.id);
					 $("#idCodeName").val(data.name);
				}
			};
			Dialog.open(options);
	});
var divInDialogs ='';
function showGroup2() {
 divInDialogs =$( "#divInDialog2" ).dialog({
			 	 modal: true,
			 	 width:800,
			 	 title:'集团选择',
				open: function(event, ui) {
					$(this).load('${request.getContextPath()}/groups/tree');
			  },	   
		    close: function (event, ui) {  
		    }  	   
		});
		divInDialogs.idCode=$("#idCode");
		divInDialogs.idCodeName=$("#idCodeName");
		
		window.divInDialogs=divInDialogs;
		
}

var hm = $("body").wHumanMsg();
$(function () {
   if('${entityAction}' =='update'){
	   $('#updateButton').click( function() {
				     if($('#idCode').val() ==''){
				     	$('#idCode').val('root');
				     }else{
				     
				     }
	      		   $.ajax({
				        url: '${request.getContextPath()}/groups/'+$("#oid").val()+'/updateGroup',
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
			    if($('#idCode').val() ==''){
			     	$('#idCode').val('root');
			     }else{
				     
				 }debugger;
			    $.ajax({
			        url: '${request.getContextPath()}/groups/createGroup',
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
		<#if entity?exists>  
		$('#text').val('${entity.text!''}');
		$('#action').val('${entity.action!''}');
		$('#idCode').val('${entity.idCode!''}');
		$('#idCodeName').val('${entity.idCodeName!''}');
		$('#shortName').val('${entity.shortName!''}');
		$('#code').val('${entity.code!''}');
		$('#description').val('${entity.description!''}');
		$('#oid').val('${entity.id!''}');
		</#if>
	}
});

</script>
</div>
</body>
</html>