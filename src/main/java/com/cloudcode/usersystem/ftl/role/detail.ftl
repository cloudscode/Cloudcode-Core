<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv">
<div class="container" id="layout">
<form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/roles/createRole" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
	    <div class="col-sm-10">
	      <input type="text" name="name" class="form-control" id="name" placeholder="名称">
	    </div>
   
    </div>
   	<div class="form-group">
	     	<label for="inputPassword3" class="col-sm-2 control-label">状态</label>
		    <div class="col-sm-10">   
		     	<label class="radio-inline">
				    <input type="radio" name="status" id="status-ac" value="0" checked>
					 正常
				  </label>
				<label class="radio-inline">
				    <input type="radio" name="status" id="status-noac" value="1">
				   		 冻结
				  </label>
			</div> 
	</div> 
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">类型</label>
	    <div class="col-sm-10">   
	     	<label class="radio-inline">
			    <input type="radio" name="type" id="type-Bussiness" value="Bussiness" checked>
				业务角色
			  </label>
			<label class="radio-inline">
			    <input type="radio" name="type" id="type-Manager" value="Admin">
			   		管理角色
			  </label>
			  <label class="radio-inline">
			    <input type="radio" name="type" id="type-System" value="System">
			   		系统内置角色
			  </label>
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
var hm = $("body").wHumanMsg();
$(function () {
   if('${entityAction}' =='update'){
	   $('#updateButton').click( function() {
				     if($('#selectTextVal').val() ==''){
				     	$('#selectTextVal').val('root');
				     }
	      		   $.ajax({
				        url: '${request.getContextPath()}/roles/'+$("#oid").val()+'/updateRole',
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
			        url: '${request.getContextPath()}/roles/createRole',
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
		<#if role?exists>  
		$('#name').val('${role.name!''}');
		$('#description').val('${role.description!''}');
		$('input:radio[name="type"]').filter('[value=${role.type!''}]').attr('checked', true);
		$('input:radio[name="status"]').filter('[value=${role.status!''}]').attr('checked', true);
		$('#oid').val('${role.id!''}');
		</#if>
	}
});
</script>
</div>
</body>
</html>