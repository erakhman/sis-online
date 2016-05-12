<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<fmt:setLocale value="en_US" />
<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>			
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.fkClass"/>
				<s:hidden name="model.pkClassHistory"/>
				<input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/>
				<div id="hiddenArea">
				</div>
				<table>
					<tr>
						<td class="caption">Tahun Ajaran<b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkTahunAjaran">
								<s:option value="">Silahkan pilih</s:option>
								<s:options-collection collection="${actionBean.tahunAjaranList}" value="pkTahunAjaran" label="tahunAjaran"/>
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption">Nama Guru<b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkStaff">
								<s:option value="">Silahkan pilih</s:option>
								<s:options-collection collection="${actionBean.teacherList}" value="pkStaff" label="name"/>
							</s:select>
						</td>	
					</tr>
				</table><br/>
				<table cellspacing="0" cellpadding="0" border="0" width="100%">
	            	<thead>
	            		<tr> 
	            			<td class="hidden"></td> 
	            			<td class="hidden"></td> 
	                    	<td class="listHeader">Tahun Ajaran</td> 
	                   		<td class="listHeader">Nama Guru</td>
	                 	</tr>
	             	</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty actionBean.classHistoryList}">
								<c:forEach items="${actionBean.classHistoryList}" var="history">
									<tr>
										<td class="hidden">${history.pkClassHistory}</td>
										<td class="hidden">${history.fkTahunAjaran}</td>
										<td class="hidden">${history.fkStaff}</td>
										<td class="listItem" onclick="editClassHistory(this)">${history.tahunAjaran}</td>
										<td class="listItem" onclick="editClassHistory(this)">${history.staffName}</td>
										<td>
											<a href="#" onclick="openConfirmationDialog('Apakah anda yakin?',2,'deleteClassHistory(${history.pkClassHistory})')">&nbsp;[X]</a>	
										</td>	
									</tr>	
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td class="listItemWithoutPointer" colspan="3">Tidak ada data.</td>
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
$(document).ready(function(){
});

function editClassHistory(el){
	var selector = $(el).parent().children();
	$('input[name="model.pkClassHistory"]').val($(selector).eq(0).html());
	$('select[name="model.fkTahunAjaran"]').val($(selector).eq(1).html());
	$('select[name="model.fkStaff"]').val($(selector).eq(2).html());
}

function deleteClassHistory(id){
	var url = '${contextPath}/ClassHistory.action?doDelete=';
	var param = '&model.pkClassHistory='+id;
	var xhr = $.post(url,param,function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			showList(1);
			closeDialog();
		} else{
			$('#errorMessage').html(data);
		}
	});
}

</script>	