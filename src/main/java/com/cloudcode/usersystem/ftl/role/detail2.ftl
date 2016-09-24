<!DOCTYPE html>
<html lang="en">
<head>
   <title>机构信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div xtype="hh_content">
<div class="container" id="layout" style="width: 100%;">
<form id="form" xtype="form">
  <span xtype="text" config=" hidden:true,name : 'id'"></span>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
	    <div class="col-sm-10">
	       <span xtype="text" config=" name : 'text',required :true"></span>
	    </div>
    </div>
    <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">状态</label>
	    <div class="col-sm-10">
	        <span xtype="radio"
							config="name: 'status' ,defaultValue : 0,  data :[{id:0,text:'正常'},{id:1,text:'冻结'}]"></span>
	    </div>
   		
  </div>
   <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">类型</label>
	    <div class="col-sm-10">
	       <span xtype="radio"
							config="name: 'type' ,defaultValue : 'Bussiness',  data :[{id:'Bussiness',text:'业务角色'},{id:'Admin',text:'管理角色'},{id:'System',text:'系统内置角色'}]"></span>
	    </div>
   		
  </div>
  <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">描述</label>
	    <div class="col-sm-10">
	       <span xtype="text" config=" name : 'description'"></span>
	    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <div xtype="toolbar">
        <span xtype="button" config="text:'保存' , onClick : page.save "></span>
     </div>
      </div>
  </div>
</form>
</div>
<#include "classpath:com/cloudcode/framework/common/ftl/require.ftl"/>
<script type="text/javascript">
var page={};
requirejs(['jquery','jquery','Request','jqueryui','main','text','select','date','radio','checkbox','textarea','password','ckeditor','button','validation','Request','combobox'], function( $, jQuery,Request ) {
	var params = $.cc.getIframeParams();
	var width = 720;
	var height = 450
	$("[xtype=form]").each(function() {
		var config = $.cc.getConfig($(this));
		if ($(this).is('form')) {
			$.cc.validation.validation($(this), config);
		}
	});
	
	page.save=function () {
		$.cc.validation.check('form', function(formData) {
			if(formData.idCode ==''){
			    formData.idCode='root';
			 }
			var url='${request.getContextPath()}/roles/createRole';
			if('${entityAction}' =='update'){
			  url='${request.getContextPath()}/roles/'+$("[name='id']").val()+'/updateRole'
			}
			Request.request(url, {
				data : formData,
				callback : function(result) {
					if (result.code =1) {
						params.callback();
						Dialog.okmsg("数据保存成功!");
						Dialog.close();
					}else{
						Dialog.errormsg("数据保存失败!");
					}
				}
			});
		});
	}
	$("[xtype]").each(function() {
		$(this).render('initRender');
	});
	
	if('${entityAction}' =='update'){
		<#if entity?exists>  
			$('#form').setValue(${entity!''});
			//$('#form').setValue({'text':'text1'});
		</#if>
	}
});
</script>
</div>
</body>
</html>