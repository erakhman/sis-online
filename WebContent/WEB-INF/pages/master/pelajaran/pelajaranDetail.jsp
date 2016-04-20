<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPelajaran"/>
				<c:choose>
					<c:when test="${actionBean.model.pkPelajaran != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
							<td class="caption" width="20%">Status</td>
							<td>						
								<s:checkbox value="Y" name="model.isActive" onclick="activeInactive()" id="chkIsActive"/><span>Active</span>
							</td>
						</tr>
						<tr>
							<td class="caption">Kode Pelajaran<b class="mandatory">*</b></td>
							<td><s:text name="model.kodePelajaran"/></td>
						</tr>
						<tr>
							<td class="caption">Nama Pelajaran<b class="mandatory">*</b></td>
							<td><s:text name="model.namaPelajaran"/></td>
						</tr>
					</table>		
			</s:form>
		</td>
	</tr>
</table>
<script>
$(function(){
	if ($('input[name="model.pkPelajaran"]').val() == ''){
		$('#chkIsActive').attr('checked','checked');
	}
	activeInactive();
});
</script>