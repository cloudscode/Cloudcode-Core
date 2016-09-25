<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true"> 
<div xtype="hh_content">
<div class="container" id="layout" style="width: 100%;">
<form id="form" xtype="form">
  <span xtype="text" config=" hidden:true,name : 'id'"></span>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">菜单名称</label>
	    <div class="col-sm-4">
	       <span xtype="text" config=" name : 'text',required :true"></span>
	    </div>
     <label for="inputPassword3" class="col-sm-2 control-label">父菜单</label>
   <div class="col-sm-4">
   
        <span id="node_span" xtype="selectTree"
							config="name: 'code' , tableName : 'MENU_MENU' , url : '../menus/queryTreeList' , params : {isNoLeaf : true} "></span>
	</div>
    </div>
   <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">动作</label>
	    <div class="col-sm-4">
	       <span xtype="text" config=" name : 'action'"></span>
	    </div>
   		<label for="inputEmail3" class="col-sm-2 control-label">编码</label>
	    <div class="col-sm-4">
	       <span xtype="text" config=" name : 'code',required :true"></span>
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
			if(formData.code ==''){
			    formData.code='root';
			 }
			var url='${request.getContextPath()}/menus/createMenu';
			if('${entityAction}' =='update'){
			  url='${request.getContextPath()}/menus/'+$("[name='id']").val()+'/updateMenu'
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