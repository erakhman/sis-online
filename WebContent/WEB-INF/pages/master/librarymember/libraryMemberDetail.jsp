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
						<%-- <c:if test="${actionBean.model != null}">
							<tr>
								<td class="caption">Kode Member<b class="mandatory">*</b></td>
								<td><s:text name="model.memberCode" readonly="true"/></td>
							</tr>
						</c:if> --%>
						<tr>
							<td class="caption">Type<b class="mandatory">*</b></td>
							<td>
								<s:radio onclick="showStudentList()" value="<%= SystemConstant.MemberType.STUDENT %>" name="model.memberType"/><%= SystemConstant.MemberType().get(SystemConstant.MemberType.STUDENT) %>
								<s:radio onclick="showTeacherList()" value="<%= SystemConstant.MemberType.TEACHER %>" name="model.memberType"/><%= SystemConstant.MemberType().get(SystemConstant.MemberType.TEACHER) %>
								<s:radio onclick="showStaffList()" value="<%= SystemConstant.MemberType.ADMIN %>" name="model.memberType"/><%= SystemConstant.MemberType().get(SystemConstant.MemberType.ADMIN) %>
							</td>
						</tr>
						<tr>
							<td class="caption">Nama<b class="mandatory">*</b></td>
							<td><s:text name="model.memberName" readonly="true"/></td>
						</tr>
						<tr>
							<td class="caption">No Identitas<b class="mandatory">*</b></td>
							<td><s:text name="model.memberIdentityNo" readonly="true"/></td>
						</tr>
						<tr>
							<td class="caption">Status<b class="mandatory">*</b></td>
							<td>
								<s:radio value="<%= SystemConstant.MemberStatus.ACTIVE %>" name="model.memberStatus"/><%= SystemConstant.MemberStatus().get(SystemConstant.MemberStatus.ACTIVE) %>
								<s:radio value="<%= SystemConstant.MemberStatus.SUSPEND %>" name="model.memberStatus"/><%= SystemConstant.MemberStatus().get(SystemConstant.MemberStatus.SUSPEND) %>
								<s:radio value="<%= SystemConstant.MemberStatus.NON_ACTIVE %>" name="model.memberStatus"/><%= SystemConstant.MemberStatus().get(SystemConstant.MemberStatus.NON_ACTIVE) %>
							</td>
						</tr>
						<tr>
							<td class="caption">Start Date<b class="mandatory">*</b></td>
							<td><s:text name="model.startDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
						</tr>
						<tr>
							<td class="caption">End Date<b class="mandatory">*</b></td>
							<td><s:text name="model.endDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
						</tr>
					</table>		
			</s:form>
		</td>
	</tr>
</table>
<script>
$(function(){
	$('input[name="model.startDate"]').val(getCurrentDate());
	$('input[name="model.memberStatus"][value="<%= SystemConstant.MemberStatus.ACTIVE %>"]').attr('checked',true);
});

function onclickStudentList(el){
	var tr = $(el).parent().children();
	$('input[name="model.memberName"]').val($(tr).eq(2).html());
	$('input[name="model.memberIdentityNo"]').val($(tr).eq(1).html());
}

function onclickTeacherList(el){
	var tr = $(el).parent().children();
	$('input[name="model.memberName"]').val($(tr).eq(2).html());
	$('input[name="model.memberIdentityNo"]').val($(tr).eq(1).html());
}

function onclickStaffList(el){
	var tr = $(el).parent().children();
	$('input[name="model.memberName"]').val($(tr).eq(2).html());
	$('input[name="model.memberIdentityNo"]').val($(tr).eq(1).html());
}
</script>
