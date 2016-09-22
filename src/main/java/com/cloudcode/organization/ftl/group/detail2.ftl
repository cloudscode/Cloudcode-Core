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
    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
	    <div class="col-sm-4">
	       <span xtype="text" config=" name : 'text',required :true"></span>
	    </div>
     <label for="inputPassword3" class="col-sm-2 control-label">父菜单</label>
   <div class="col-sm-4">
   
        <span id="node_span" xtype="selectTree"
							config="name: 'idCode' , tableName : 'ORG_GROUP' , url : '../groups/queryTreeList' , params : {isNoLeaf : true} "></span>
	</div>
    </div>
   <div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">简称</label>
	    <div class="col-sm-4">
	       <span xtype="text" config=" name : 'shortName'"></span>
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
			if(formData.idCode ==''){
			    formData.idCode='root';
			 }
			var url='${request.getContextPath()}/groups/createGroup';
			if('${entityAction}' =='update'){
			  url='${request.getContextPath()}/groups/'+$("[name='id']").val()+'/updateGroup'
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

<#--var params = $.cc.getIframeParams();
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
				        	 params.callback(data);
							Dialog.close();
				        }
			    });
		});
   }else{
	    $('#updateButton').click( function() {
			    if($('#idCode').val() ==''){
			     	$('#idCode').val('root');
			     }else{
				     
				 }
			    $.ajax({
			        url: '${request.getContextPath()}/groups/createGroup',
			        type: 'post',
			        dataType: 'json',
			        data: $('form#myFormId').serialize(),
			        success: function(data) {
			       		 $('body').wHumanMsg('theme', 'black').wHumanMsg('msg', '数据保存成功！', {fadeIn: 300, fadeOut: 300});
			       		params.callback(data);
						Dialog.close();
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
-->
</script>
</div>
</body>
</html>