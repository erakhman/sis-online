<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="<fmt:message key="app.name"/>">
<meta name="author" content="Edy Rakhman">
<link href="${contextPath}/css/style.css" rel="stylesheet">
<title>${actionBean.pageTitle}</title>
</head>
<body onload="setFocus();">
<table  cellpadding="0" cellspacing="0" width="100%" border="0" height="100%" border="0">
    <tbody>
    <tr>
        <td valign="top" width="10%" align="left" bgcolor="">
            <table cellpadding="0" cellspacing="0" width="100%" border="0" height="100%">
                <tr> 
                    <td valign="top">
                   		<%-- <img width="230px" src="${contextPath}/images/additional/sales_logo2.png" style="margin:5px">   --%>
                    </td>
                </tr>
                <tr>
                    <td valign="middle" align="center" height="100%">
                    <s:form method="POST" beanclass="${actionBean.beanClass}">
                        
                        <table border="0" width="400px">
                            <tbody>
                            	<tr>
                            		<td></td>
                            		<td><s:errors/><s:messages/></td>
                            	</tr>
                                <tr>
                                    <th><fmt:message key="label.userName"/></th>
                                    <td><s:text name="user.userName" id="userName"/></td>
                                </tr>

                                <tr>
                                    <th><fmt:message key="label.password"/></th>
                                    <td><s:password name="user.password"/></td>
                                </tr>
                                <tr>
                                	<td>&nbsp;</td>
                                    <td align="left"><s:submit name="doLogin" value="LOGIN" class="button"/>&nbsp;</td>
                                </tr>
                            </tbody>
                        </table>
                    </s:form>
                    </td>
                </tr>
            </table>
        </td>
        <td valign="top" width="90%">                     	
             <table cellpadding="0" cellspacing="0" width="100%" border="0" height="100%">
                <tr>
                    <td valign="top">
                   
                    </td>
                </tr>
                <tr>
                    <td valign="middle" align="center" height="100%">
                        <table border="0" width="600px">
                            <tbody>
                                <tr>
                                	<td>
                                	<p align="center">
                                		<font style="font-family:impact" size="6"> <fmt:message key="app.name"/> </font><br/><br/><br/>
                                		<img src="${contextPath}/images/additional/sales.png" style="width:400px">
	                                    <br/><br/><br/>     
					                    Menjadi guru itu sebuah KENIKMATAN, Jika Anda sudah tidak NIKMAT mengajar, tidak NIKMAT mendidik, tidak 
				                    	NIKMAT berlama-lama di kelas ... kami punya saran lepaskan jabatan GURU Anda, lepaskan sertifikasi Anda ...
				                    	agar fisik dan jiwa Anda tetap Sehat. #GuruBerdidekasi #AyoMendidik
				                    </p>
				                    </td> 
                                </tr>  
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>                   
        </td>
    </tr>
    <tr>
		<td>
			<p style="font-size: 10;margin-top:3px">Developed by Edy Rakhman 2015. V1.0.0 [1-Aug-2015]</p>
		</td>
    </tr>
</tbody>
</table>
<script language="javascript" type="text/javascript">
    function setFocus() {
        document.getElementById("userName").select();
        document.getElementById("userName").focus();
    }
</script>
</body>
</html>
