<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkProduct"/>
				<c:choose>
					<c:when test="${actionBean.model.pkProduct != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
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
						<td class="caption"><fmt:message key="label.productCode"/><b class="mandatory">*</b></td>
						<td><s:text name="model.productCode"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.productName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.productName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.basicPrice"/><b class="mandatory">*</b></td>
						<td><s:text name="model.basicPrice" class="numeric"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.sellPrice"/><b class="mandatory">*</b></td>
						<td><s:text name="model.sellPrice" class="numeric"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.qty"/><b class="mandatory">*</b></td>
						<td>
							<c:choose>
								<c:when test="${actionBean.model.pkProduct == null}">
									<s:text name="model.qty" class="numeric"/>	
								</c:when>
								<c:otherwise>
									<s:text name="model.qty" readonly="readonly" class="numeric"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.merk"/><b class="mandatory">*</b></td>
						<td><s:text name="model.merk"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.madeIn"/><b class="mandatory">*</b></td>
						<td><s:text name="model.madeIn"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.category"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkCategory">
								<s:options-collection collection="${actionBean.categoryList}" label="lookupName" value="pkLookup"/>
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.unit"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkUnit">
								<s:options-collection collection="${actionBean.unitList}" label="lookupName" value="pkLookup"/>
							</s:select>
						</td>
					</tr>	
				</table>
			</s:form>
		</td>
	</tr>
</table>