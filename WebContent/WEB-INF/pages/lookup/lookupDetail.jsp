<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.model.Lookup"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkLookup"/>
				<c:choose>
					<c:when test="${actionBean.model.pkLookup != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td class="caption"><fmt:message key="label.fkLookupGroup"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.lookupType">
		               			<s:option value="<%= SystemConstant.LookupGroup.JADWAL_UJIAN %>"><fmt:message key="<%= SystemConstant.LookupGroupDescr().get(SystemConstant.LookupGroup.JADWAL_UJIAN) %>"/></s:option>
		               		</s:select>
						</td>
					</tr>
					
					<tr>
						<td class="caption"><fmt:message key="label.lookupName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.lookupName"/></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>
<script>
$(document).ready(function(){
	
});
</script>	