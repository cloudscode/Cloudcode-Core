<!DOCTYPE html>
<html lang="en">
<head>
   <#include "classpath:com/cloudcode/framework/common/ftl/head.ftl"/>
</head>
<style>
#updateButton{
	width:80px;
	margin: 2px;
}
h1.site-title a, h1.site-title {
    line-height: 1rem;
    font-size: 4rem;
    font-family: alegreya_sans_scthin;
    font-weight: 900;
    color: #9d9d9d;
    text-decoration: none;
}
</style>
<body data-spy="scroll" data-target=".bs-docs-sidebar" data-twttr-rendered="true">
 <!-- Navbar
    ================================================== -->
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
              <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <h1 class="site-title"><a href="http://owls.besaba.com/" rel="home">OwlFocus</a></h1>
            </div>
            <nav class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                	<li class="active">
                        <a href="${request.getContextPath()}/menus/menuList" target="view_window"><i class="glyphicon glyphicon-list"></i>菜单管理</a>
                    </li>
                    <li class="active">
                        <a href="${request.getContextPath()}/categories/categoryList" target="view_window"><i class="glyphicon glyphicon-list"></i>字典信息</a>
                    </li>
                    <li>
                        <a href="${request.getContextPath()}/forecast/toSearch" target="view_window"><i class="glyphicon glyphicon-list"></i>筛选信息</a>
                    </li>
                    <li>
                        <a href="${request.getContextPath()}/forecastIssue/toList" target="view_window"><i class="glyphicon glyphicon-list"></i>预测信息</a>
                    </li>
                     <li>
                        <a href="${request.getContextPath()}/lottery/toList" target="view_window"><i class="glyphicon glyphicon-list"></i>基础库信息</a>
                    </li>
                     <li>
                        <a href="${request.getContextPath()}/lmenu/toIndex" target="blank"><i class="glyphicon glyphicon-list"></i>彩票系统</a>
                    </li>
                    
                    <li class="dropdown ">
		                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
						  <i class="glyphicon glyphicon-list"></i>
						  组织机构管理 <b class="caret"></b>
						</a>
		                <ul class="dropdown-menu">
					      <li><a href="${request.getContextPath()}/orgs/orgList" target="blank"><i class="glyphicon glyphicon-list"></i> 集团管理</a></li>
					      <li class="divider"></li>
               				<li>
		                        <a href="${request.getContextPath()}/orgs/orgList" target="blank"><i class="glyphicon glyphicon-list"></i>机构管理</a>
		                    </li> <li class="divider"></li>
		                    <li>
		                        <a href="${request.getContextPath()}/depts/deptList" target="blank"><i class="glyphicon glyphicon-list"></i>部门管理</a>
		                    </li> <li class="divider"></li>
		                    <li>
		                        <a href="${request.getContextPath()}/jobs/jobList" target="blank"><i class="glyphicon glyphicon-list"></i>岗位管理</a>
		                    </li> <li class="divider"></li>
		                    <li>
		                        <a href="${request.getContextPath()}/roles/roleList" target="blank"><i class="glyphicon glyphicon-list"></i>角色管理</a>
		                    </li> <li class="divider"></li>
		                    <li>
		                        <a href="${request.getContextPath()}/rolemenus/roleMenuList" target="blank"><i class="glyphicon glyphicon-list"></i>角色权限</a>
		                    </li> <li class="divider"></li>
               			</ul>
              </li>
                    <li class="dropdown ">
		                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
						  <i class="glyphicon glyphicon-list"></i>
						  流程管理 <b class="caret"></b>
						</a>
		                <ul class="dropdown-menu">
					      <li><a href="${request.getContextPath()}/workFlowMenus/0/design" target="blank"><i class="glyphicon glyphicon-list"></i> 流程设计器</a></li>
					      <li class="divider"></li>
               			</ul>
             	  </li>
                    <li class="dropdown ">
		                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
						  <i class="glyphicon glyphicon-list"></i>
						  图表 <b class="caret"></b>
						</a>
		                <ul class="dropdown-menu">
					      <li><a href="${request.getContextPath()}/report/echart" target="blank"><i class="glyphicon glyphicon-list"></i> echart</a></li>
					      <li class="divider"></li>
					        <li><a href="${request.getContextPath()}/report/highchart" target="blank"><i class="glyphicon glyphicon-list"></i> highchart</a></li>
					      <li class="divider"></li>
               			</ul>
             	  </li>
                
                <li><a href="./j_spring_security_logout"><i class="glyphicon glyphicon-list"></i> 退出</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <div style="margin-top: 60px;"> 
<iframe  id="myFrameId"  src="${request.getContextPath()}/categories/categoryList" name="view_window"  scrolling="no" frameborder="0" style="width:100%;" onload="javascript:dyniframesize('myFrameId');" ></iframe>
</div>
<#include "classpath:com/cloudcode/framework/common/ftl/vendor.ftl"/>
<script type="text/javascript">
  function dyniframesize(down) { 
	var pTar = null; 
	if (document.getElementById){ 
		pTar = document.getElementById(down); 
	} 
	else{ 
		eval('pTar = ' + down + ';'); 
	} 
	if (pTar && !window.opera){ 
		//begin resizing iframe 
		pTar.style.display="block" 
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){ 
			//ns6 syntax 
			pTar.height = pTar.contentDocument.body.offsetHeight +20; 
			pTar.width = pTar.contentDocument.body.scrollWidth+20; 
		} 
		else if (pTar.Document && pTar.Document.body.scrollHeight){ 
			//ie5+ syntax 
			pTar.height = pTar.Document.body.scrollHeight; 
			pTar.width = pTar.Document.body.scrollWidth; 
		} 
	} 
	setTimeout(function (){dyniframesize(down);},500);
} 
</script>

</body>
</html>