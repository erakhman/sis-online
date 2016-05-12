<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">	
	<tr>
		<td valign="top">
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%">
	            	<thead>
	            		<tr> 
	                    	<td class="listHeader">Tahun Ajaran</td> 
	                   		<td class="listHeader">Nama Kelas</td>
	                 	</tr>
	             	</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty actionBean.classHistoryList}">
								<c:forEach items="${actionBean.classHistoryList}" var="history">
									<tr>
										<td class="listItem">${history.tahunAjaran}</td>
										<td class="listItem">${history.staffName}</td>
									</tr>	
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td class="listItemWithoutPointer" colspan="2">Tidak ada data.</td>
								</tr>
							</c:otherwise>
						</c:choose>
						
					</tbody>
				</table>		
			</s:form>
		</td>
	</tr>
</table>
<script>
$(function(){

});
</script>