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
							<td class="caption">Rak<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.shelfId">
									<s:options-collection collection="${actionBean.bookShelfList}" value="id" label="shelfName"/>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption">Kode<b class="mandatory">*</b></td>
							<td>
								<s:text name="model.code"/>
							</td>
						</tr>
						<tr>
							<td class="caption">Deskripsi<b class="mandatory">*</b></td>
							<td>
								<s:text name="model.locationDescription"/>
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
