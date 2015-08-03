<%@ page language="java" contentType="text/xml; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.ttc.ch2.ui.common.config.JspCh2URIs"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="-1" />
	<title>Login</title>	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/zk-login.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/Ch2Main.css" />" />
	<style>
	.z-label, .z-radio-cnt, .z-checkbox-cnt, .z-loading {
					font-family: arial, sans-serif;
					font-size: 12px;
					font-weight: normal;
					}
	</style>
	<script type="text/javascript">
		function loginOperation(){				
			document.getElementById("loginForm").submit();	
		}
	</script>
</head>
<body class="safari safari537 breeze">
<div id="hH6Q4" style="width:100%;height:100%;" class="z-page">
    <div id="hH6Q1" style="height:100vh;" class="z-borderlayout">
    
    <div id="hH6Q2"><div id="hH6Q2-real" class="z-center z-center-noborder" style="left: 0px; top: 0px; width: 100%; height: 600pxx;">
    <div id="hH6Q2-cave" class="z-center-body" style="width: 100%; height: 756px; overflow: hidden;"><div id="hH6Q3" style="width:100%;height:100%;" class="z-window-embedded">
    
    <div class="z-window-embedded-tl"><div class="z-window-embedded-tr"></div></div><div class="z-window-embedded-cl">
    <div class="z-window-embedded-cr"><div class="z-window-embedded-cm" >
    <div id="hH6Q3-cave" class="z-window-embedded-cnt" style="height: 592px">
	 <table id="hH6Q5" style="width:100%;height:100%;" class="login_page z-hbox" cellpadding="0" cellspacing="0" border="0">
	 <tbody>
	 <tr valign="middle">
	 <td id="hH6Q5-frame" style="width: 100%; height: 592px;" align="center">
	 	<table id="hH6Q5-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
	 	<tbody>
	 	<tr valign="middle">
	 	<td id="hH6Q6-chdex" style="height:100%">
	 		<table id="hH6Q6" style="width:100%;height:100%;" class="z-hbox" cellpadding="0" cellspacing="0" border="0">
	 		<tbody>
	 		<tr valign="middle">
	 		<td id="hH6Q6-frame" style="width: 100%; height: 516px;" align="center">
	 			<table id="hH6Q6-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
	 			<tbody>
	 			<tr valign="middle">
	 			<td id="hH6Q7-chdex" style="height:100%">
	 								<div id="hH6Q7" class="z-div">
			        					<img id="hH6Q8" class="z-image" src="img/content_hub_2_img.png" />
			        					<div id="hH6Q9" style="width:400px;height:153px;" class="z-groupbox-3d">
			        						<div class="z-groupbox-3d-tl">
			        								<div class="z-groupbox-3d-tr"></div>
			        						</div>
			        						<div class="z-groupbox-3d-hl">
			        							<div class="z-groupbox-3d-hr">
			        							<div class="z-groupbox-3d-hm z-groupbox-3d-hm-readonly">
			        							<div class="z-groupbox-3d-header">
			        								<table id="hH6Qa" class="z-caption z-caption-readonly" cellpadding="0" cellspacing="0" border="0" width="100%">
			        								<tbody>
			        									<tr valign="middle">
			        									<td id="hH6Qa-cnt" align="left" class="z-caption-l">Please login below</td>
			        									<td align="right" class="z-caption-r" id="hH6Qa-cave"></td>
			        									</tr>
			        								</tbody>
			        								</table>
			        								</div></div>
			        							</div>
			        							</div>
			        							<div id="hH6Q9-cave" class="z-groupbox-3d-cnt" style="border-top-width: 0px; height: 154px;">
			        							 <form id="loginForm" name="loginForm" action="modules/auth/j_security_check" method="post">
			        							 		<input type="hidden" value="${logPageId}" name="logPageId" />
													<div id="hH6Qc" class="z-grid"><div id="hH6Qc-body" class="z-grid-body" style="width: 423px;">
													<table width="100%" cellpadding="0" cellspacing="0" border="0" style="table-layout:fixed;">
													<tbody id="hH6Qd" class="z-rows">
													<tr id="hH6Qe" class="z-row">
														<td id="hH6Qf-chdextr" class="z-row-inner">
														    <div id="hH6Qf-cell" class="z-row-cnt z-overflow-hidden">
															    <span id="hH6Qf" style="display:block;text-indent:21px;width:100%;clear:both;font-size:16px; color:#999;" class="z-label">User: </span>
															</div>
														</td>
														<td id="hH6Qg-chdextr" class="z-row-inner">
															<div id="hH6Qg-cell" class="z-row-cnt z-overflow-hidden">
																<input id="hH6Qg" class="z-textbox" value="" type="text" name="j_username" />
															</div>
														</td>
													</tr>
													<tr id="hH6Qh" class="z-row z-grid-odd">
														<td id="hH6Qi-chdextr" class="z-row-inner">
															<div id="hH6Qi-cell" class="z-row-cnt z-overflow-hidden">
																<span id="hH6Qi" style="display:block;text-indent:21px;font-size:16px; color:#999;" class="z-label">Password: </span>
															</div>
														</td>
														<td id="hH6Qj-chdextr" class="z-row-inner">
															<div id="hH6Qj-cell" class="z-row-cnt z-overflow-hidden">
															<input id="hH6Qj" class="z-textbox" value="" type="password" name="j_password" />
															</div>
														</td>
													</tr>
													<tr id="hH6Qk" class="z-row">
														<td id="hH6Ql-chdextr" colspan="2" class="z-row-inner">
														<div id="hH6Ql-cell" class="z-row-cnt z-overflow-hidden">
															<table id="hH6Ql" style="width:100%;" class="z-hbox" cellpadding="0" cellspacing="0" border="0">
															<tbody>
															<tr valign="top">
																<td id="hH6Ql-frame" style="width:100%;height:100%" align="center">
																		<table id="hH6Ql-real" cellpadding="0" cellspacing="0" border="0" style="text-align:left">
																			<tbody>
																			<tr valign="top">
																			<td id="hH6Qm-chdex" style="height:100%">
																				<span id="hH6Qm" class="z-button">
																					<table id="hH6Qm-box" style="width:100%;height:100%" cellpadding="0" cellspacing="0" border="0" onclick="loginOperation()">
																					   <tbody>
																					    <tr>
																						    <td class="z-button-tl"><button type="submit" id="hH6Qm-btn" class="z-button"></button></td>
																						    <td class="z-button-tm"></td><td class="z-button-tr"></td>
																					    </tr>
																					    <tr>
																						    <td class="z-button-cl"></td>
																						    <td class="z-button-cm" align="center">																						     
																						    	Login																						    
																						    </td>
																						    <td class="z-button-cr"><div></div></td>
																					    </tr>
																					    <tr>
																						    <td class="z-button-bl"></td><td class="z-button-bm"></td>
																						    <td class="z-button-br"></td></tr>
																					    </tbody>
																					</table>
																				</span>
																			</td>
																			</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
																</tbody>
																</table>
															</div>
															</td>
														</tr>
														<tr>	
															<td align="center" width="100%" colspan="2" >
															     <c:if test="${param.error=='1' || param.error==2}">															
																	<div id="iWEQp" style="color:red;font-weight: bold;text-align:center;" class="z-div">
																		<span id="iWEQq" class="z-label">Invalid login or password</span>
																	</div>
																</c:if>
															</td>
														</tr>
														</tbody>
														<tbody id="hH6Qc-empty" class="z-grid-empty-body" style="display: none;">
														<tr><td colspan="1">
														
														
														</td></tr>
														</tbody>
													</table>
												</div></div>
											  	</form>
											  	</div>
										 </div>							        
								         <div id="hH6Qn" class="z-separator-hor"><div class="z-separator-hor-inner">&nbsp;</div></div>
								         <div id="hH6Qo" style="width:400px;height:90px;" class="z-groupbox-3d"><div id="hH6Qo-cave" class="z-groupbox-3d-cnt z-groupbox-3d-notitle" style="height: 85px;">
									         <span id="hH6Qp" class="less_emph_title z-label">System status</span>									         
									         <div id="hH6Qq" class="z-vlayout">
										         <div id="hH6Qr-chdex" class="z-vlayout-inner" style="padding-bottom:0.3em"><span id="hH6Qr" class="z-label">Version: ${version}</span></div>
										         <div id="hH6Qs-chdex" class="z-vlayout-inner" style="padding-bottom:0.3em"><span id="hH6Qs" class="z-label">Environment: ${enviroment}</span></div>
										         <div id="hH6Qt-chdex" class="z-vlayout-inner" style="padding-bottom:0.3em"><span id="hH6Qt" class="z-label">Date: ${currentDate}</span></div>
										         <div id="hH6Qu-chdex" class="z-vlayout-inner" style=""><span id="hH6Qu" class="z-label">Deploy Date: ${deployDate}</span></div>
									         </div>
									         </div>
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
			</td>
		</tr>
		</tbody>
		</table>
		</td>
	</tr>
	</tbody>
	</table>
</div></div></div></div>
<div class="z-window-embedded-bl"><div class="z-window-embedded-br"></div></div></div></div></div></div></div></div>
</body>
</html>