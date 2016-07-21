<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div id="dialogDiv">
<div class="container" id="layout" style="width: 100%;">
<form role="form" class="form-horizontal" id="myFormId" action="${request.getContextPath()}/taskconfig/createMenu" method="post">
  
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
	    <div class="col-sm-4">
	      <input type="name" name="name" class="form-control" id="name" placeholder="名称" value="${entity.name!''}">
	    </div>
     <label for="inputPassword3" class="col-sm-2 control-label">是否有效</label>
	     <div class="col-sm-4">
				      <input id="valid" name="valid" type="checkbox">
	     </div>
    </div>
    
   <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">调用类型</label>
	    <div class="col-sm-10">  
	       	<select id="execType" name="execType"  class="form-control">
			  <option value="class">接口类</option>
			  <option value="sql">sql</option>
			</select>
	    </div>
    
  </div>
  
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">调用公式</label>
	    <div class="col-sm-10">
	      <input type="text" id="formula" name="formula" class="form-control" id="description" placeholder="描述" value="${entity.formula!''}">
	    </div>
  </div>
  
    <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">注册时间(当天)</label>
	    <div class="col-sm-10">
	    		 <input class="col-width4 stepper " type="text" id="hour" name="hourRegister" value="${entity.hourRegister!''}" style="width:70px;" />时
				<input class="col-width4 stepper " type="text" id="minute" name="minuteRegister" value="${entity.minuteRegister!''}" style="width:70px" />分
				<input class="col-width4 stepper " type="text" id="second"  name="secondRegister" value="${entity.secondRegister!''}" style="width:70px;" />秒
	    </div>
  </div>
  
    <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">隔多久调用</label>
	    <div class="col-sm-10">
	     		<input class="col-width4 stepper " type="text" id="day" name="day" value="${entity.day!''}" style="width:70px;"/>天
				<input class="col-width4 stepper " type="text" id="hour" name="hour" value="${entity.hour!''}" style="width:70px;" />时
				<input class="col-width4 stepper " type="text" id="minute" name="minute" value="${entity.minute!''}" style="width:70px" />分
				<input class="col-width4 stepper " type="text" id="second"  name="second" value="${entity.second!''}" style="width:70px;" />秒
	    </div>
  </div>
  
  
  
  <div class="form-staff">
    <div class="col-sm-offset-2 col-sm-10">
       <button type="button" id="updateButton" class="btn btn-default">save</button>
    </div>
  </div>
  
  
  <input type="hidden" value="" id="id" name="id" >
  
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
	    var data =$('form#myFormId').serializeObject();
   		data.valid = $('#valid').prop('checked')?1:0;
					$.ajax({
				        url: '${request.getContextPath()}/taskconfig/'+$("#id").val()+'/updateTaskConfig',
				        type: 'post',
				        dataType: 'json',
				        data:data,
				        success: function(data) {
				       		 $('body').wHumanMsg('theme', 'black').wHumanMsg('msg', '数据保存成功！', {fadeIn: 300, fadeOut: 300});
				        	 $('.ui-dialog-titlebar-close').trigger('click');
				           }
			    });
		});
   }else{
	    $('#updateButton').click( function() {
	    var data =$('form#myFormId').serializeObject();
   		data.valid = $('#valid').prop('checked')?1:0;
			    $.ajax({
			        url: '${request.getContextPath()}/taskconfig/createTaskConfig',
			        type: 'post',
			        dataType: 'json',
			        data: data,
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
		$('#name').val('${entity.name!''}');
		
		$('#id').val('${entity.id!''}');
		
		var valid = '${entity.valid}';
		if(valid=='1'){
			$('#valid').prop('checked',true);
		}
		var execType = '${entity.execType}';
		$('#execType').setValue(execType);
		</#if>
	}
});

		$(document).ready(function(){
			
		});
</script>
</div>
</body>
</html>