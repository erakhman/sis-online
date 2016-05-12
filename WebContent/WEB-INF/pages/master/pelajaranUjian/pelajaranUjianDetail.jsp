<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPelajaranUjian"/>
				<c:choose>
					<c:when test="${actionBean.model.pkPelajaranUjian != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
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
							<td class="caption">Nama Pelajaran<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.fkPelajaran">
									<s:options-collection collection="${actionBean.pelajaranList}" value="pkPelajaran" label="namaPelajaran"/>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption">Tahun Ajaran<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.fkTahunAjaran">
									<s:options-collection collection="${actionBean.tahunAjaranList}" value="pkTahunAjaran" label="tahunAjaran"/>
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