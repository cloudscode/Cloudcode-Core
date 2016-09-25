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
                        <a href="${request.getContextPath()}/menus/menuList" target="view_window">菜单管理</a>
                    </li>
                    <li class="active">
                        <a href="${request.getContextPath()}/categories/categoryList" target="view_window">字典信息</a>
                    </li>
                    <li>
                        <a href="${request.getContextPath()}/forecast/toSearch" target="view_window">筛选信息</a>
                    </li>
                    <li>
                        <a href="${request.getContextPath()}/forecastIssue/toList" target="view_window">预测信息</a>
                    </li>
                     <li>
                        <a href="${request.getContextPath()}/lottery/toList" target="view_window">基础库信息</a>
                    </li>
                     <li>
                        <a href="${request.getContextPath()}/lmenu/toIndex" target="blank">彩票系统</a>
                    </li>
                    <li>
	                    <div class="dropdown">
						  <button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Dropdown trigger
						    <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" aria-labelledby="dLabel">
						  <li>
		                        <a href="${request.getContextPath()}/orgs/orgList" target="blank">集团管理</a>
		                    </li>
						    <li>
		                        <a href="${request.getContextPath()}/orgs/orgList" target="blank">机构管理</a>
		                    </li>
		                    <li>
		                        <a href="${request.getContextPath()}/depts/deptList" target="blank">部门管理</a>
		                    </li>
		                    <li>
		                        <a href="${request.getContextPath()}/jobs/jobList" target="blank">岗位管理</a>
		                    </li>
		                    <li>
		                        <a href="${request.getContextPath()}/roles/roleList" target="blank">角色管理</a>
		                    </li>
		                    <li>
		                        <a href="${request.getContextPath()}/rolemenus/roleMenuList" target="blank">角色权限</a>
		                    </li>
						  </ul>
						</div>
                    </li>
                    <!--<li>
                        <a href="./map.html">Map</a>
                    </li>
                    <li>
                        <a href="http://github.com/addyosmani/jquery-ui-bootstrap/issues">Feedback/Issues</a>
                    </li>
                    <li>
                        <a href="http://twitter.com/addyosmani">Contact</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Switch Theme <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="./index.html">Default Bootstrap Theme (Non LESS'd)</a></li>
                            <li><a href="theme/united/index.html">United Theme via Bootswatch (LESS'd)</a></li>
                            <li><a href="theme/base/index.html">Default Bootstrap Theme (LESS'd)</a></li>
                        </ul>
                    </li>-->
                </ul>
            </nav>
        </div>
    </header>
<iframe  id="myFrameId"  src="${request.getContextPath()}/categories/categoryList" name="view_window"  scrolling="no" frameborder="0" style="width:100%;" onload="javascript:dyniframesize('myFrameId');" ></iframe>
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