<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkAppParameter"/>
				<s:hidden name="model.name"/>
				<input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/>
				<table>	
					<tr>
						<td class="caption" width="20%">Description<b class="mandatory">*</b></td>
						<td><s:text name="model.description" style="width:250px"/></td>
					</tr>
					<tr>
						<td class="caption" width="20%">Value<b class="mandatory">*</b></td>
						<td><s:text name="model.value" style="width:250px"/></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>	