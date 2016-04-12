<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkDriver"/>
				<c:choose>
					<c:when test="${actionBean.model.pkDriver != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
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
						<td class="caption"><fmt:message key="label.driverName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.driverName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.address"/><b class="mandatory">*</b></td>
						<td><s:text name="model.address"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.phoneNo"/><b class="mandatory">*</b></td>
						<td><s:text name="model.phoneNo"/></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>