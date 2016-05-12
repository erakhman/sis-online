<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table>
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkTahunAjaran"/>
				<c:choose>
					<c:when test="${actionBean.model.pkTahunAjaran != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
						<td class="caption" width="20%"><fmt:message key="label.status"/></td>
							<td>						
								<s:checkbox value="Y" name="model.isActive" onclick="activeInactive()" id="chkIsActive"/><span>Active</span>
							</td>
						</tr>
						<tr>
							<td class="caption">Tahun Ajaran<b class="mandatory">*</b></td>
							<td><s:text name="model.tahunAjaran"/></td>
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
