<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkLibraryMember"/>
				<c:choose>
					<c:when test="${actionBean.model.pkLibraryMember != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
							<td class="caption">Kode Member<b class="mandatory">*</b></td>
							<td><s:text name="model.memberCode"/></td>
						</tr>
						<tr>
							<td class="caption">Nama<b class="mandatory">*</b></td>
							<td><s:text name="model.memberName"/></td>
						</tr>
						<tr>
							<td class="caption">Type<b class="mandatory">*</b></td>
							<td><s:text name="model.memberType"/></td>
						</tr>
						<tr>
							<td class="caption">No Identitas<b class="mandatory">*</b></td>
							<td><s:text name="model.memberIdentityNo"/></td>
						</tr>
						<tr>
							<td class="caption">Status<b class="mandatory">*</b></td>
							<td><s:text name="model.memberStatus"/></td>
						</tr>
						<tr>
							<td class="caption">Start Date<b class="mandatory">*</b></td>
							<td><s:text name="model.startDate"/></td>
						</tr>
						<tr>
							<td class="caption">End Date<b class="mandatory">*</b></td>
							<td><s:text name="model.endDate"/></td>
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
