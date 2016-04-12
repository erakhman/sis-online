<%@page import="com.beesinergi.model.AppUser"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<tr>
	<td valign="bottom" colspan="3">
		<table  width="100%" cellpadding="0"  cellspacing="0" border="0" height="18px;" >
			<tr>
				<td width="100%"  style="BORDER-BOTTOM: #d7141d 1px solid;text-align:right;padding-right:5px;">&nbsp;</td>
				<c:forEach items="${actionBean.userInfo.menuList}" var="menu">
					<td class="tabNonActive" nowrap="nowrap" id="${menu.menuCode}">
						<s:link beanclass="com.beesinergi.action.${menu.pageName}"> <fmt:message key="${menu.subMenu}"/></s:link>
					</td>	
				</c:forEach>	
			</tr>
		</table>
	</td>
</tr>