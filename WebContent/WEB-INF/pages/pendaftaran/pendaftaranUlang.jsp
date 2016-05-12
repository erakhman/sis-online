<%@page import="com.beesinergi.util.SystemParameter"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doReRegister"/>
				<s:hidden name="model.pkPendaftaran"/>
				<table>
					<tr>
						<td class="caption">Nominal<b class="mandatory">*</b></td>
						<td><s:text name="model.namaSiswa"/></td>
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