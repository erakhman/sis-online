<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkRole"/>
				<c:choose>
					<c:when test="${actionBean.model.pkRole != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td class="caption" width="20%"><fmt:message key="label.status"/></td>
						<td>						
							<s:checkbox value="Y" name="model.isActive" onclick="activeInactive()" id="chkIsActive"/><span>Active</span>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.roleCode"/><b class="mandatory">*</b></td>
						<td><s:text name="model.roleCode"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.roleName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.roleName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.menu"/><b class="mandatory">*</b></td>
						<td>
							<c:forEach items="${actionBean.menuList}" var="menu">
								<c:set var="isFound" value="false"/>
								<c:forEach items="${actionBean.model.menuList}" var="selectedMenu">
									<c:if test="${menu.pkMenu == selectedMenu.fkMenu }">
										<input type="checkbox" value="${menu.pkMenu}" name="model.fkMenuList" checked="checked"/>
										<fmt:message key="${menu.subMenu}"/><br/>
										<c:set var="isFound" value="true"/>
									</c:if>
								</c:forEach>
								<c:if test="${isFound == false}">
									<input type="checkbox" value="${menu.pkMenu}" name="model.fkMenuList"/>
									<fmt:message key="${menu.subMenu}"/><br/>
								</c:if>
							</c:forEach>
						</td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>