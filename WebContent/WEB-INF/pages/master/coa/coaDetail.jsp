<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkCoa"/>
				<c:choose>
					<c:when test="${actionBean.model.pkCoa != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
							<td class="caption">Account No<b class="mandatory">*</b></td>
							<td><s:text name="model.acctNo"/></td>
						</tr>
						<tr>
							<td class="caption">Account Name<b class="mandatory">*</b></td>
							<td><s:text name="model.acctName"/></td>
						</tr>
						<tr>
							<td class="caption">Description<b class="mandatory">*</b></td>
							<td><s:text name="model.descr"/></td>
						</tr>
						<tr>
							<td class="caption">Coa Type<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.fkCoaType">
									<s:options-collection collection="${actionBean.coaTypeList}" value="pkCoaType" label="coaTypeName"/>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption">Balance<b class="mandatory">*</b></td>
							<td><s:text name="model.balance"/></td>
						</tr>
						<tr>
							<td class="caption">Natural Balance<b class="mandatory">*</b></td>
							<td><s:text name="model.naturalBalance"/></td>
						</tr>
						<tr>
							<td class="caption">Parent Coa<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.parentFkCoa">
									<s:option value="">-</s:option>
									<s:options-collection collection="${actionBean.parentCoaList}" value="pkCoa" label="acctNo"/>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption">Status<b class="mandatory">*</b></td>
							<td><s:text name="model.status"/></td>
						</tr>
					</table>		
			</s:form>
		</td>
	</tr>
</table>
<script>
$(function(){

});
</script>