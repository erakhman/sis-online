<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkVendor"/>
				<c:choose>
					<c:when test="${actionBean.model.pkVendor != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
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
						<td class="caption"><fmt:message key="label.vendorCode"/><b class="mandatory">*</b></td>
						<td><s:text name="model.vendorCode"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.vendorName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.vendorName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.address"/><b class="mandatory">*</b></td>
						<td><s:text name="model.address"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.city"/><b class="mandatory">*</b></td>
						<td><s:text name="model.city"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.province"/><b class="mandatory">*</b></td>
						<td><s:text name="model.province"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.postalCode"/><b class="mandatory">*</b></td>
						<td><s:text name="model.postalCode"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.country"/><b class="mandatory">*</b></td>
						<td><s:text name="model.country"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.phoneNo"/><b class="mandatory">*</b></td>
						<td><s:text name="model.phoneNo"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.faxNo"/><b class="mandatory">*</b></td>
						<td><s:text name="model.faxNo"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.bankName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.bankName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.accountNo"/><b class="mandatory">*</b></td>
						<td><s:text name="model.accountNo"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.accountName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.accountName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.contactPerson"/><b class="mandatory">*</b></td>
						<td><s:text name="model.contactPerson"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.email"/><b class="mandatory">*</b></td>
						<td><s:text name="model.email"/></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>