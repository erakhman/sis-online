<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>
	<tr>
		<td valign="top">
			<div id="errorMessage"></div> <s:form id="detailForm" method="POST"
				beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave" />
				<s:hidden name="model.pkBook" />
				<c:choose>
					<c:when test="${actionBean.model.pkBook != null}">
						<input type="hidden" name="model.changedBy"
							value="${actionBean.userInfo.fullName}" />
					</c:when>
					<c:otherwise>
						<input type="hidden" name="model.createdBy"
							value="${actionBean.userInfo.fullName}" />
					</c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td class="caption">ISBN<b class="mandatory">*</b></td>
						<td><s:text name="model.bookIsbn" /></td>
					</tr>
					<tr>
						<td class="caption">Kode Buku<b class="mandatory">*</b></td>
						<td><s:text name="model.bookCode" /></td>
					</tr>
					<tr>
						<td class="caption">Judul Buku<b class="mandatory">*</b></td>
						<td><s:text name="model.bookTitle" /></td>
					</tr>
					<tr>
						<td class="caption">Kategori Buku<b class="mandatory">*</b></td>
						<td><s:select name="model.bookCategory">
								<s:options-collection
									collection="${actionBean.bookCategoryList}"
									value="pkBookCategory" label="categoryCode" />
							</s:select></td>
					</tr>
					<tr>
						<td class="caption">Pengarang Buku<b class="mandatory">*</b></td>
						<td><s:text name="model.bookAuthor" /></td>
					</tr>
					<tr>
						<td class="caption">Penerbit<b class="mandatory">*</b></td>
						
						<td><s:select name="model.bookPublisher">
								<s:options-collection
									collection="${actionBean.bookPublisherList}"
									value="pkBookPublisher" label="publisherCode" />
							</s:select></td>
					</tr>
					<tr>
						<td class="caption">Status<b class="mandatory">*</b></td>
						<td><s:text name="model.bookStatus" /></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>
<script>
	$(function() {

	});
</script>
