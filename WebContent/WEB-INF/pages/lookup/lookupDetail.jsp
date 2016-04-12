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
						<td class="caption" width="20%"><fmt:message key="label.status"/></td>
						<td>						
							<s:checkbox value="Y" name="model.isActive" onclick="activeInactive()" id="chkIsActive"/><span>Active</span>
						</td>
					</tr>	
					<tr>
						<td class="caption"><fmt:message key="label.lookupType"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.lookupType">
		               			<s:option value="<%= SystemConstant.LookupType.CATEGORY %>"><fmt:message key="<%= SystemConstant.LookupTypeDescr().get(SystemConstant.LookupType.CATEGORY) %>"/></s:option>
		               			<s:option value="<%= SystemConstant.LookupType.UNIT %>"><fmt:message key="<%= SystemConstant.LookupTypeDescr().get(SystemConstant.LookupType.UNIT) %>"/></s:option>
		               			<s:option value="<%= SystemConstant.LookupType.CAR %>"><fmt:message key="<%= SystemConstant.LookupTypeDescr().get(SystemConstant.LookupType.CAR) %>"/></s:option>
		               			<s:option value="<%= SystemConstant.LookupType.DEPARTMENT %>"><fmt:message key="<%= SystemConstant.LookupTypeDescr().get(SystemConstant.LookupType.DEPARTMENT) %>"/></s:option>
		               			<s:option value="<%= SystemConstant.LookupType.ROOM %>"><fmt:message key="<%= SystemConstant.LookupTypeDescr().get(SystemConstant.LookupType.ROOM) %>"/></s:option>
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
$(document).ready(function(){$('input[name="model.lookupName"]').val();
	if ($('input[name="model.pkLookup"]').val() == ''){
		$('#chkIsActive').attr('checked','checked');
	} 
	activeInactive();
});

function activeInactive(){
	if ($('#chkIsActive').is(':checked')){
		$('#chkIsActive').next().html('Active');
	} else{
		$('#chkIsActive').next().html('Inactive');
	}
}
</script>	