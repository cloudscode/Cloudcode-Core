<!DOCTYPE html>
<html lang="en">
<head>
   <title>集团信息</title>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
   <#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<script type="text/javascript"
	src="/cccommon/opensource/jquery/file/ajaxfileupload.js"></script>
<script type="text/javascript">
	var params = $.cc.getIframeParams();
	function save() {
		
		var saveUrl = params.saveUrl || '../systemfile/createSystemFile';
		Doing.show();
		$.cc.validation.check('form', function(formData) {
			//console.log(formData);
			$.ajaxFileUpload({
				url : saveUrl, //需要链接到服务器地址
				secureuri : false,
				data : formData,
				fileElementId : 'file', //文件选择框的id属性
				dataType : 'json', //服务器返回的格式，可以是json
				success : function(data, textStatus) { //相当于java中try语句块的用法
					//[{isSuccess:true|false,serverPath:XXXXX}]
					Doing.hide();
					if(data.code==1){
						Dialog.error("上传附件失败！"+data.msg);
					}else{
						Dialog.okmsg("上传附件成功！");
						Dialog.close();
						params.callback(data);
					}
					//$('#imgPath').val(data[0].serverPath);
					//alert($('#imgPath').val());
				},
				error : function(data, status, e) { //相当于java中catch语句块的用法
					Doing.hide();
					Dialog.errormsg("上传附件失败！");
				}
			});
		});
	}
	function init() {
		$('#filePathSpan').setValue(params.type || 'sys');
		if(params.saveUrl){
			$('[describetr=true]').hide();
		}
	}
</script>
</head>
<body>
	<div xtype="hh_content">
		<form id="form" xtype="form" enctype="multipart/form-data"
			method="post" action="">
			<span id="filePathSpan" xtype="text"
				config=" hidden:true,name : 'type'"></span>
			<table xtype="form">
				<tr>
					<td xtype="label" style="width: 80px;">附件：</td>
					<td><input name="attachment" class="validate[required
					<#--<%
					if ("pic".equals(type)) {
				%>,custom[image]<%
					}
				%>]-->"
						id="file" type="file"
						style="width: 100%; color: #555; padding: 2px 0px 2px 0px; border: 1px solid #B5B8C8;" /></td>
				</tr>
				<#--<%
					if (false
							//!"pic".equals(type)
							) {
				%>-->
				<tr describetr=true>
					<td xtype="label" style="width: 80px;">描述：</td>
					<td><span xtype="textarea" config=" name : 'name' "></span></td>
				</tr>
			<#--	<%
					}
				%>-->
			</table>
		</form>
	</div>
	<div xtype="toolbar">
		<span xtype="button" config="text:'上传' , onClick : save "></span> <span
			xtype="button" config="text:'取消' , onClick : Dialog.close "></span>
	</div>
</body>
</html>