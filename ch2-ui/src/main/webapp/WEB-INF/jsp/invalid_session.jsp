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
<title>Invalid session</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/zk-login.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/Ch2Main.css" />" />
	<style>
	.z-panel-cl .z-panelchildren {
		background-color: white;	
		}	
	</style>	
	<script type="text/javascript">
	function forwardOperation(){				
		var path='${pageContext.request.contextPath}<%=com.ttc.ch2.ui.common.config.JspCh2URIs.LOGIN.getPath()%>';		
		window.location=path;
    };
	</script>
</head>
<body class="safari safari537 breeze">
<div id="wCxP4" style="width:100%;height:100%;" class="z-page">
    <div id="wCxP1" style="height:100vh;" class="z-borderlayout"><div id="wCxP2"><div id="wCxP2-real" class="z-center z-center-noborder" style="left: 0px; top: 0px; width: 100%; height: 756px;"><div id="wCxP2-cave" class="z-center-body" style="width: 100%; height: 756px; overflow: hidden;"><div id="wCxP3" style="width:100%;height:100%;" class="z-window-embedded"><div class="z-window-embedded-tl"><div class="z-window-embedded-tr"></div></div><div class="z-window-embedded-cl"><div class="z-window-embedded-cr"><div class="z-window-embedded-cm"><div id="wCxP3-cave" class="z-window-embedded-cnt" style="height: 748px;">
	 <table id="wCxP5" style="width:100%;height:100%;" class="invalid_session z-hbox" cellpadding="0" cellspacing="0" border="0"><tbody><tr valign="middle"><td id="wCxP5-frame" style="width: 100%; height: 748px;" align="center"><table id="wCxP5-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left"><tbody><tr valign="middle"><td id="wCxP6-chdex" style="height:100%"><div id="wCxP6" class="z-panel"><div class="z-panel-tl z-panel-tl-gray"><div class="z-panel-tr z-panel-tr-gray"></div></div><div id="wCxP6-body" class="z-panel-body"><div class="z-panel-cl"><div class="z-panel-cr"><div class="z-panel-cm z-panel-noheader"><div id="wCxP7" style="overflow: auto;" class="z-panelchildren z-panelchildren-noheader">		        
		        	 <table id="wCxP8" style="width:300px;height:100px;" class="z-hbox" cellpadding="0" cellspacing="0" border="0">
		        	 <tbody>
		        	 <tr valign="middle"><td id="wCxP8-frame" style="width: 100%; height: 100px;" align="center">
		        	 <table id="wCxP8-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
		        	 <tbody>
		        	 <tr valign="middle"><td id="wCxP9-chdex" style="height:100%">
		        	 <div id="wCxP9" class="z-vlayout">
		        	 <div id="wCxPa-chdex" class="z-vlayout-inner" style="">
		        	 <table id="wCxPa">
							<tbody><tr>
								<td><span id="wCxPc" style="font-family:arial;font-size:16px;font-weight: bold;" class="z-label">Invalid Session</span></td>
							</tr>
							<tr>
								<td align="center">
									<div id="wCxPf" class="z-hlayout">
									<div id="wCxPg-chdex" class="z-hlayout-inner" style="">
									<span id="wCxPg" class="z-button">
									<table id="wCxPg-box" style="width:100%;height:100%" cellpadding="0" cellspacing="0" border="0" class="" onclick="forwardOperation()">
									<tbody>
									<tr>
										<td class="z-button-tl">
										<button type="button" id="wCxPg-btn" class="z-button"></button>
										</td>
										<td class="z-button-tm"></td>
										<td class="z-button-tr"></td>
									</tr>
									<tr>
									  <td class="z-button-cl"></td>
									  <td class="z-button-cm">Login</td>
									  <td class="z-button-cr"><div></div></td>
									</tr>
									<tr>
										<td class="z-button-bl"></td>
										<td class="z-button-bm"></td>
										<td class="z-button-br"></td>
									</tr>
									</tbody>
									</table>
									</span>
									</div>
									</div>		
								</td>
							</tr>
							</tbody>
							</table>
							</div>
							</div>
							</td>
							</tr>
							</tbody>
							</table>
							</td>
							</tr>
							</tbody>
							</table>
							</div>
							</div>
							</div>
							</div>
							<div class="z-panel-fl z-panel-nobtm2">
							<div class="z-panel-fr">
							<div class="z-panel-fm"></div></div></div>
							<div class="z-panel-bl">
							<div class="z-panel-br">
							</div></div></div></div>
							</td>
							</tr>
							</tbody>
							</table>
							</td>
							</tr>
							</tbody>
							</table>
							</div>
							</div>
							</div>
							</div>
							<div class="z-window-embedded-bl">
							<div class="z-window-embedded-br">
							</div>
							</div>
							</div>
							</div>
							</div>
							</div>
							</div>
							</div>
<noscript>
&lt;div class="noscript"&gt;&lt;p&gt;Sorry, JavaScript must be enabled.&lt;br/&gt;Change your browser options, then &lt;a href=""&gt;try again&lt;/a&gt;.&lt;/p&gt;&lt;/div&gt;
</noscript>
</body>
</html>