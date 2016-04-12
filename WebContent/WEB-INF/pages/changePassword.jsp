<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<s:form beanclass="${actionBean.beanClass}" method="POST">
	<table border="0" cellpadding="2" cellspacing="0">
	    <tr>
	        <td></td>
	        <td><s:errors/><s:messages/></td>
	    </tr>
	    <tr>
	        <td align="right">Username:</td>
	        <td><input type="text" name="user.userName" readonly="true" value="${actionBean.userInfo.userName}"/></td>
	    </tr>
	    <tr>
	        <td align="right">Password Lama:</td>
	        <td><input type="password" name="user.password"/></td>
	    </tr>
	    <tr>
	        <td align="right">Password Baru:</td>
	        <td><s:password name="newPassword"/></td>
	    </tr>
	    <tr>
	        <td align="right">Konfirmasi Password Baru:</td>
	        <td><s:password name="confirmPassword"/></td>
	    </tr>
	    <tr>
	        <td></td>
	        <td><s:submit name="doChangePassword" value="SUBMIT" class="button"/>&nbsp;</td>
	    </tr>
	</table>
</s:form>
</s:layout-component>
</s:layout-render>
