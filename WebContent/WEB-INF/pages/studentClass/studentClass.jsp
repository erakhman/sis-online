<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkStudentList"/>
				<table>
					<tr>
						<td class="caption">Nama Kelas<b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkClassHistory">
		               			<s:options-collection collection="${actionBean.classHistoryList}" label="className" value="pkClassHistory"/>
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