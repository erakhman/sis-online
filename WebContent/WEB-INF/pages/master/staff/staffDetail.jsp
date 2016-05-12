<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkStaff"/>
				<c:choose>
					<c:when test="${actionBean.model.pkStaff != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
							<td class="caption">Kode<b class="mandatory">*</b></td>
							<td><s:text name="model.code"/></td>
						</tr>
						<tr>
							<td class="caption">Nama<b class="mandatory">*</b></td>
							<td><s:text name="model.name"/></td>
						</tr>
						<tr>
							<td class="caption">Tgl Lahir<b class="mandatory">*</b></td>
							<td><s:text name="model.dob" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
						</tr>
						<tr>
							<td class="caption">Jenis Kelamin<b class="mandatory">*</b></td>
							<td>
								<s:radio value="M" name="model.sex"/>Laki-laki
								<s:radio value="F" name="model.sex"/>Perempuan
							</td>
						</tr>
						<tr>
							<td class="caption">Status Pernikahan<b class="mandatory">*</b></td>
							<td>
								<s:radio value="S" name="model.maritalStatus"/>Jomblo
								<s:radio value="M" name="model.maritalStatus"/>Nikah
								<s:radio value="D" name="model.maritalStatus"/>Janda/Duda
							</td>
						</tr>
						<tr>
							<td class="caption">Alamat<b class="mandatory">*</b></td>
							<td><s:text name="model.address"/></td>
						</tr>
						<tr>
							<td class="caption">No Telp<b class="mandatory">*</b></td>
							<td><s:text name="model.phoneNo"/></td>
						</tr>
						<tr>
							<td class="caption">Tanggal Bergabung<b class="mandatory">*</b></td>
							<td><s:text name="model.joinDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
						</tr>
						<tr>
							<td class="caption">Tipe<b class="mandatory">*</b></td>
							<td>
								<s:radio value="<%= SystemConstant.StaffType.ADMIN %>" name="model.type"/><%= SystemConstant.StaffType().get(SystemConstant.StaffType.ADMIN) %>
								<s:radio value="<%= SystemConstant.StaffType.TEACHER %>" name="model.type"/><%= SystemConstant.StaffType().get(SystemConstant.StaffType.TEACHER) %>
							</td>
						</tr>
						<tr>
							<td class="caption">Status<b class="mandatory">*</b></td>
							<td>
								<s:radio value="<%= SystemConstant.StaffStatus.EMPLOYEE %>" name="model.status"/><%= SystemConstant.StaffStatus().get(SystemConstant.StaffStatus.EMPLOYEE) %>
								<s:radio value="<%= SystemConstant.StaffStatus.HONORER %>" name="model.status"/><%= SystemConstant.StaffStatus().get(SystemConstant.StaffStatus.HONORER) %>
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
