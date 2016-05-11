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
							<td class="caption">Tahun Ajaran<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.tahunAjaranId">
									<s:options-collection collection="${actionBean.tahunAjaranList}" value="pkTahunAjaran" label="tahunAjaran"/>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption">Biaya Tahunan<b class="mandatory">*</b></td>
							<td><s:text name="model.annualFee"/></td>
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
