<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkCoaType"/>
				<%-- <c:choose>
					<c:when test="${actionBean.model.pkPendaftaran != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose> --%>
					<table>
						<tr>
							<td class="caption"><fmt:message key="label.coaTypeName"/><b class="mandatory">*</b></td>
							<td><s:text name="model.coaTypeName"/></td>
						</tr>
						<tr>
						<td class="caption"><fmt:message key="label.coaTypeNormalBalance"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.naturalBalance">
								<s:option value="1">DEBET</s:option>
								<s:option value="2">CREDIT</s:option>
							</s:select>
						</td>
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