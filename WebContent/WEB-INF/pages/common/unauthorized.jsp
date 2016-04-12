<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" pageTitle="ICD">
<s:layout-component name="content">
<table border="0" width="100%">
	<tr>
    	<td align="center">
    		<img width="250px" src="${contextPath}/images/additional/unauthorized.jpg">
  			<h2 style="color: #d7141d;">401 - Unauthorized: Access is denied due to invalid credentials.</h2>
    		<h3>You do not permission to view this directory or page using the credentials that you supplied</h3>
    	</td>
  	</tr>
</table>
</s:layout-component>
</s:layout-render>