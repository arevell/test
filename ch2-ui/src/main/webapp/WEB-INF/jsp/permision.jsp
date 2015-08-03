<%@page import="com.ttc.ch2.ui.common.config.JspCh2URIs"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<title>Permision</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zk-login.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/Ch2Main.css" />" />
	<script type="text/javascript">
	function forwardOperation(){				
		var path='${pageContext.request.contextPath}<%=com.ttc.ch2.ui.common.config.JspCh2URIs.LOGIN.getPath()%>';		
		window.location=path;
    };
	</script>
</head>
<body class="safari safari537 breeze">
<div id="iFAQ_" style="width:100%;height:100%;" class="z-page">  
	 <table id="iFAQ2" style="width:100%;height:100%;" class="access_denied z-hbox" cellpadding="0" cellspacing="0" border="0">
	       <tbody>
	       <tr valign="middle">
	       <td id="iFAQ2-frame" style="width: 100%; height: 756px;" align="center">
	         <table id="iFAQ2-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
	             <tbody>
	             <tr valign="middle">
	             <td id="iFAQ3-chdex" style="height:100%"><div id="iFAQ3" class="z-panel">
	               <div class="z-panel-tl z-panel-tl-gray"><div class="z-panel-tr z-panel-tr-gray"></div></div><div id="iFAQ3-body" class="z-panel-body"><div class="z-panel-cl"><div class="z-panel-cr"><div class="z-panel-cm z-panel-noheader"><div id="iFAQ4" style="overflow: auto;" class="z-panelchildren z-panelchildren-noheader">		        
		        	 <table id="iFAQ5" style="width:350px;" class="z-hbox" cellpadding="0" cellspacing="0" border="0"><tbody><tr valign="middle">
		        	        <td id="iFAQ5-frame" style="width:100%;height:100%" align="center">
		        	        <table id="iFAQ5-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
		        	        <tbody>
		        	        <tr valign="middle">
		        	        <td id="iFAQ6-chdex" style="height:100%">
		        	        <div id="iFAQ6" class="z-vlayout">
		        	        <div id="iFAQ7-chdex" class="z-vlayout-inner" style="">
		        	        <table id="iFAQ7">
							<tbody><tr>
								<td>								    
								  	<img id="kABPa" class="z-image" src="<c:url value="/img/cross.jpg"/>" />
								</td>
								<td><span id="iFAQd" style="font-family:arial;font-size:16px;font-weight: bold;color:red;" class="z-label">No Permision</span></td>
							</tr>
							<tr>
								<td align="center" colspan="2">
								<span id="iFAQg" class="z-label">Please use request with appropriate token</span>									
								</td>
							</tr>
							</tbody></table></div></div></td></tr></tbody></table>
							</td>
							</tr>
							</tbody>
					   </table>
						</div></div></div></div><div class="z-panel-fl z-panel-nobtm2">
							<div class="z-panel-fr"><div class="z-panel-fm"></div></div></div>
							<div class="z-panel-bl"><div class="z-panel-br"></div></div></div></div>
					</td>
					</tr>
					</tbody>
				</table>
				</td>
				</tr>
				</tbody>
		</table>
</div>

<noscript>
&lt;div class="noscript"&gt;&lt;p&gt;Sorry, JavaScript must be enabled.&lt;br/&gt;Change your browser options, then &lt;a href=""&gt;try again&lt;/a&gt;.&lt;/p&gt;&lt;/div&gt;
</noscript>
</body>
</html>