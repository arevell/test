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
<title>Logout success</title>
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
<div id="fEuP4" style="width:100%;height:100%;" class="z-page">	
    <div id="fEuP1" style="height:100vh;" class="z-borderlayout">
    <div id="fEuP2"><div id="fEuP2-real" class="z-center z-center-noborder" style="left: 0px; top: 0px; width: 100%; height: 100%px;">
    <div id="fEuP2-cave" class="z-center-body" style="width: 100%; height: 100%; overflow: hidden;"><div id="fEuP3" style="width:100%;height:100%;" class="z-window-embedded">
    <div class="z-window-embedded-tl"><div class="z-window-embedded-tr"></div></div>
    <div class="z-window-embedded-cl"><div class="z-window-embedded-cr"><div class="z-window-embedded-cm">
    <div id="fEuP3-cave" class="z-window-embedded-cnt" style="height: 100%;"> 
	 <table id="fEuP7" style="width:100%;height:100%;" class="log_out z-hbox" cellpadding="0" cellspacing="0" border="0"><tbody><tr valign="middle"><td id="fEuP7-frame" style="width: 100%; height: 100%;" align="center"><table id="fEuP7-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
	 <tbody><tr valign="middle"><td id="fEuP8-chdex" style="height:100%"><table id="fEuP8" style="width:100%;">
						<tbody><tr align="center">
							<td>
								<img id="fEuPa" class="z-image" src="<c:url value="/img/content_hub_2_img.png" />" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<div id="fEuPd" class="z-groupbox-3d"><div id="fEuPd-cave" class="z-groupbox-3d-cnt z-groupbox-3d-notitle"><table id="fEuPe" style="background-color:#fff;width:100%;">
										<tbody><tr>
											<td align="center"><span id="fEuPg" class="z-label">You have logged out successfully. Thank you for using the Content Hub.</span></td>
										</tr>
										<tr>
											<td align="center">										
												<span id="fEuPj" class="z-button">
												<table id="fEuPj-box" style="width:100%;height:100%" cellpadding="0" cellspacing="0" border="0" onclick="forwardOperation()">
												<tbody>
												<tr>
													<td class="z-button-tl"><button type="button" id="fEuPj-btn" class="z-button"></button></td>
													<td class="z-button-tm"></td>
													<td class="z-button-tr"></td>
												</tr>
												<tr>
													<td class="z-button-cl"></td>
													<td class="z-button-cm">Return to login screen</td>
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
											</td>
										</tr>
									</tbody></table></div></div>							
							</td>
						</tr>
					</tbody></table></td></tr></tbody>
					</table></td></tr></tbody></table></div></div></div></div><div class="z-window-embedded-bl">
					<div class="z-window-embedded-br"></div></div></div></div></div></div></div>
</div>

<noscript>
&lt;div class="noscript"&gt;&lt;p&gt;Sorry, JavaScript must be enabled.&lt;br/&gt;Change your browser options, then &lt;a href=""&gt;try again&lt;/a&gt;.&lt;/p&gt;&lt;/div&gt;
</noscript>



</body>
</html>