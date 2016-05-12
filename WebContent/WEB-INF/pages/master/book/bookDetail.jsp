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
						<input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}" />
					</c:when>
					<c:otherwise>
						<input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}" />
					</c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td class="caption">ISBN<b class="mandatory">*</b></td>
						<td><s:text name="model.bookIsbn" /></td>
					</tr>
					<tr>
						<td class="caption">Kode<b class="mandatory">*</b></td>
						<td><s:text name="model.bookCode" /></td>
					</tr>
					<tr>
						<td class="caption">Judul<b class="mandatory">*</b></td>
						<td><s:text name="model.bookTitle" /></td>
					</tr>
					<tr>
						<td class="caption">Kategori<b class="mandatory">*</b></td>
						<td>
							<s:select name="model.bookCategory">
								<s:options-collection collection="${actionBean.bookCategoryList}" value="pkBookCategory" label="categoryDescription" />
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption">Pengarang<b class="mandatory">*</b></td>
						<td><s:text name="model.bookAuthor" /></td>
					</tr>
					<tr>
						<td class="caption">Harga<b class="mandatory">*</b></td>
						<td><s:text name="model.price" /></td>
					</tr>
					<tr>
						<td class="caption">Tanggal Terima</td>
						<td><s:text name="model.receivedDate" class="datepicker"/></td>
					</tr>
					<tr>
						<td class="caption">Penerbit<b class="mandatory">*</b></td>	
						<td>
							<s:select name="model.bookPublisher">
								<s:options-collection collection="${actionBean.bookPublisherList}" value="pkBookPublisher" label="publisherName" />
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption">Lokasi<b class="mandatory">*</b></td>
						<td>
							<s:select name="model.bookLocation">
								<s:options-collection collection="${actionBean.bookLocationList}" value="id" label="locationDescription" />
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption">Status<b class="mandatory">*</b></td>
						<td>
							<s:select name="model.bookStatus">
								<s:option value="<%= SystemConstant.BookStatus.AVAILABLE %>"><%= SystemConstant.BookStatus().get(SystemConstant.BookStatus.AVAILABLE) %></s:option>
								<s:option value="<%= SystemConstant.BookStatus.BORROWED %>"><%= SystemConstant.BookStatus().get(SystemConstant.BookStatus.BORROWED) %></s:option>
								<s:option value="<%= SystemConstant.BookStatus.LOST %>"><%= SystemConstant.BookStatus().get(SystemConstant.BookStatus.LOST) %></s:option>
							</s:select>
						</td>
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
