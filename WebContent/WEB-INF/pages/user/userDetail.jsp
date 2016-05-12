<%@page import="com.beesinergi.model.AppUser"%>
<%@page import="com.beesinergi.util.SystemParameter"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkUser"/>
				<s:hidden name="model.fkEmployee"/>
				<c:choose>
					<c:when test="${actionBean.model.pkUser != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td class="caption"><fmt:message key="label.status"/></td>
						<td>						
							<s:checkbox value="N" name="model.isLocked" onclick="activeInactive()" id="chkIsActive"/><span><fmt:message key="label.active"/></span>
						</td>
					</tr>
					<tr>
						<td class="caption" width="45%"><fmt:message key="label.roleName"/><b class="mandatory">*</b></td>
						<td>
							<c:forEach items="${actionBean.roleList}" var="role">
								<c:set var="isMatch" value="false"/>
	                    		<c:forEach items="${actionBean.model.userRoleList}" var="roleSelected">
	                    			<c:if test="${role.pkRole == roleSelected.fkRole}">
	                    				<input type="checkbox" name="model.fkRoleList" value="${role.pkRole}" checked="checked"/>${role.roleName}
	                    				<c:set var="isMatch" value="true"/>
	                    			</c:if>
	                    		</c:forEach> 
	                    		<c:if test="${isMatch == false}">
	                    			<input type="checkbox" name="model.fkRoleList" value="${role.pkRole}"/>${role.roleName}
	                    		</c:if><br/>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.userName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.userName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.fullName"/><b class="mandatory">*</b></td>
						<td><s:text name="model.fullName"/></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>
<script>
$(document).ready(function(){
	populateEmployeeName($('#employeeName'));
});

function populateEmployeeName(el){
	var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetEmployeeList=';
	$.getJSON(url,function(data){
		var employeeArr = [];
		$.each(data.employeeList,function(i){
			employeeArr.push(this.employeeName);
		});
		$('#employeeName').autocomplete({
			source: employeeArr,
			change: function(e) {
				populateEmployeeDetail();
			},
			select: function() {
				populateEmployeeDetail();
			}
		});
	});
}

function populateEmployeeDetail(){
	if ($('#employeeName').val() != ''){
		var param = '&employee.employeeName='+$('#employeeName').val();
		var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetEmployeeDetail=';
		$.getJSON(url,param,function(data){
			if (data.employee != null){
				$('input[name="model.fkEmployee"]').val(data.employee.pkEmployee);
			} else{
				alert('Employee is not found');
			}
		});
	} 
}
</script>	