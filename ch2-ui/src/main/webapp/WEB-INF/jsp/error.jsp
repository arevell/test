<%@ page import="com.ttc.ch2.ui.common.config.JspCh2URIs"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<title>Technical break</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zk-login.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/Ch2Main.css" />" />
	<style>
				.panelchild{
					background-color: white;
				}	
				
				.z-panel-hm .z-panel-header, .z-panel-header {
					font-family: arial, sans-serif;
					font-size: 12px !important;
					font-weight: bold;
					}	
				
				.z-label, .z-radio-cnt, .z-checkbox-cnt, .z-loading {
					font-family: arial, sans-serif;
					font-size: 12px;
					font-weight: normal;
					}
					
				span.z-button {
						display: -moz-inline-box;
						vertical-align: bottom;
						display: inline-block;
						margin: 1px 1px 0 0;
						}
					
				.z-button, .z-button tr td {
						font-family: arial, sans-serif;
						font-size: 12px;
						font-weight: normal;
						color: black;
						cursor: pointer;
						white-space: nowrap;
						}	
					
				.z-button .z-button-cm {
						overflow: hidden;
						vertical-align: middle;
						text-align: center;
						background-repeat: repeat-x;
						background-position: 0 0;
						white-space: nowrap;
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
<div id="yGxP_" style="width:100%;height:100%;" class="z-page">
 <div id="yGxP2" style="width:100%;height:100vh;" class="z-window-embedded">
 <div class="z-window-embedded-tl">
     <div class="z-window-embedded-tr"></div>
 </div>
  <div class="z-window-embedded-cl">
  <div class="z-window-embedded-cr">
  <div class="z-window-embedded-cm">
  <div id="yGxP2-cave" class="z-window-embedded-cnt" style="height: 748px;">
  <table id="yGxP3" style="height:100%;" class="error_panel z-hbox" cellpadding="0" cellspacing="0" border="0">
  <tbody>
  <tr valign="middle">
  <td id="yGxP3-frame" style="width: 100%; height: 748px;" align="center">
  	<table id="yGxP3-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
  	<tbody>
  	<tr valign="middle">
  		<td id="yGxP4-chdex" style="height:100%">
  			<div id="yGxP4" class="z-panel">
  				<div class="z-panel-tl"><div class="z-panel-tr"></div></div>
  			<div class="z-panel-hl">
  			<div class="z-panel-hr">
  			<div class="z-panel-hm">
  			<div id="yGxP4-cap" class="z-panel-header ">
	  			<table id="yGxP5" class="panel_title z-caption" cellpadding="0" cellspacing="0" border="0" width="100%">
	  			<tbody>
	  			<tr valign="middle">
	  				<td id="yGxP5-cnt" align="left" class="z-caption-l">Error ${CODE} [${TIME}]</td>
	  				<td align="right" class="z-caption-r" id="yGxP5-cave"></td>
	  			</tr>
	  			</tbody>
	  			</table>
  			</div>
  			</div>
  			</div>
  			</div>
  			<div id="yGxP4-body" class="z-panel-body">
	  			<div class="z-panel-cl">
	  			<div class="z-panel-cr">
	  			<div class="z-panel-cm">
	  			<table><tr><td class="panelchild">	        
			        	 <table id="yGxP7" class="z-hbox" cellpadding="0" cellspacing="0" border="0"><tbody><tr valign="top">
				        	 <td id="yGxP7-frame" style="width:100%;height:100%" align="center">
				        	 <table id="yGxP7-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
				        	 <tbody>
				        	 <tr valign="top">
				        	 <td id="yGxP8-chdex" style="height:100%">
				        		 <span id="yGxP8" style="font-family:arial;font-size:16px;font-weight: bold;" class="z-label">Technical break</span>
				        	 </td>
				        	 </tr>
				        	 </tbody>
				        	 </table>
				        	 </td>
				        	 </tr>
				        	 </tbody>
			        	 </table>				
						<table id="yGxP9">
							<tbody><tr>
								<td>
									<span id="yGxPb" class="z-label">
										Error occurred: ${EXCEPTION_MSG}
									</span>
								</td>
							</tr>
							<tr>
								<td align="center">
									<div id="yGxPe" class="z-hlayout">
									<div id="yGxPf-chdex" class="z-hlayout-inner" style="">
									<span id="yGxPf" class="z-button">
									<table id="yGxPf-box" style="width:100%;height:100%" cellpadding="0" cellspacing="0" border="0" onclick="forwardOperation()">
									<tbody>
									<tr>
									   <td class="z-button-tl"><button type="button" id="yGxPf-btn" class="z-button"></button></td>
									   <td class="z-button-tm"></td>
									   <td class="z-button-tr"></td>
									</tr>
									<tr>
									    <td class="z-button-cl"></td>
									    <td class="z-button-cm">Back to login page</td>
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
						</td></tr>
					</table>
				</div>
				</div>
				</div>
				<div class="z-panel-fl z-panel-nobtm2">
				<div class="z-panel-fr"><div class="z-panel-fm"></div></div></div>
				<div class="z-panel-bl"><div class="z-panel-br"></div></div>
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
	<div class="z-window-embedded-bl"><div class="z-window-embedded-br"></div></div></div></div>

<noscript>
&lt;div class="noscript"&gt;&lt;p&gt;Sorry, JavaScript must be enabled.&lt;br/&gt;Change your browser options, then &lt;a href=""&gt;try again&lt;/a&gt;.&lt;/p&gt;&lt;/div&gt;
</noscript>
</body>
</html>