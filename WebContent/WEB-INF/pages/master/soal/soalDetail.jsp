<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkSoal"/>
				<c:choose>
					<c:when test="${actionBean.model.pkSoal != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
					<table>
						<tr>
							<td class="caption">Nama Pelajaran<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.fkPelajaran">
									<s:options-collection collection="${actionBean.pelajaranList}" value="pkPelajaran" label="namaPelajaran"/>
								</s:select>
							</td>
						</tr>
						<tr>
							<td class="caption">Soal Pelajaran<b class="mandatory">*</b></td>
							<td><s:text name="model.soalPelajaran"/></td>
						</tr>
						<tr>
							<td class="caption">Pilihan A<b class="mandatory">*</b></td>
							<td><s:text name="model.pilihanA"/></td>
						</tr>
						<tr>
							<td class="caption">Pilihan B<b class="mandatory">*</b></td>
							<td><s:text name="model.pilihanB"/></td>
						</tr>
						<tr>
							<td class="caption">Pilihan C<b class="mandatory">*</b></td>
							<td><s:text name="model.pilihanC"/></td>
						</tr>
						<tr>
							<td class="caption">Pilihan D<b class="mandatory">*</b></td>
							<td><s:text name="model.pilihanD"/></td>
						</tr>
						<tr>
							<td class="caption">Pilihan E<b class="mandatory">*</b></td>
							<td><s:text name="model.pilihanE"/></td>
						</tr>
						<tr>
							<td class="caption">Level<b class="mandatory">*</b></td>
							<td><s:text name="model.level"/></td>
						</tr>
						<tr>
							<td class="caption">Jawaban<b class="mandatory">*</b></td>
							<td><s:text name="model.jawaban"/></td>
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