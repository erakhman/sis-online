<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkBookPublisher"/>
				<c:choose>
					<c:when test="${actionBean.model.pkBookPublisher != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
							<td class="caption">Kode<b class="mandatory">*</b></td>
							<td><s:text name="model.publisherCode"/></td>
						</tr>
						<tr>
							<td class="caption">Nama<b class="mandatory">*</b></td>
							<td><s:text name="model.publisherName"/></td>
						</tr>
						<tr>
							<td class="caption">Alamat<b class="mandatory">*</b></td>
							<td><s:textarea name="model.publisherAddress" rows="5"></s:textarea></td>
						</tr>
						<tr>
							<td class="caption">Telp<b class="mandatory">*</b></td>
							<td><s:text name="model.publisherPhoneNo"/></td>
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
