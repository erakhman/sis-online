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
				<%-- <c:choose>
					<c:when test="${actionBean.model.id != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose> --%>
					<table>
						<tr>
							<td class="caption"><fmt:message key="label.tahunAjaran"/><b class="mandatory">*</b></td>
							<td>
								<s:select name="model.tahunAjaranId">
									<s:option value="1">2016/2017</s:option>
									<s:option value="2">2017/2018</s:option>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.penaltyType"/><b class="mandatory">*</b></td>
							<td>
								<s:select name="model.penaltyType">
									<s:option value="1">Suspend</s:option>
									<s:option value="2">Pay base on late date</s:option>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption"><fmt:message key="label.nominal"/></td>
							<td>
								<s:text name="model.nominal"/>
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
