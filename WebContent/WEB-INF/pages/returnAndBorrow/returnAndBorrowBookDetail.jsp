<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.id"/>
					<table>
						<tr>
							<td class="caption"><fmt:message key="label.bookId"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.bookId"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.borrowedBy"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.borrowedBy"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.startDate"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.startDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.endDate"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.endDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.returnDate"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.returnDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.createdDate"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.createdDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.changedDate"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.changedDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.createdBy"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.createdBy"/>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.changedBy"/><b class="mandatory">*</b></td>
							<td>
								<s:text name="model.changedBy"/>
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
