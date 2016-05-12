<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<s:form method="post" id="searchForm" beanclass="${actionBean.beanClass}">
		    	<table cellspacing="0" cellpadding="0" border="0">
					<tr>
		            	<td class="toolBarImageButton"
		                	onmouseover="this.className='toolBarImageHover'"
		                    onmouseout="this.className='toolBarImageButton'"
		                    onclick="showDetail(0)" nowrap>
		                    <img src="<%=request.getContextPath()%>/images/add24.png" align="absMiddle"/>&nbsp;<fmt:message key="btn.add"/>
		           		</td>
		               	<td width="100%"/>	
		           	</tr>
		  		</table>
	  		</s:form>
		</td>
	</tr>
	<tr>
    	<td height="100%" valign="top">
			<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
	        	<thead>
	            	<tr>
	            		<td class="hidden"></td>
	            		<td class="listHeader">Tahun Ajaran</td>
	            		<td class="listHeader">Nama Pelajaran</td>
	            		<td class="listHeader">Nama Kelas</td>
	            		<td class="listHeader">Start Date</td> 
	            		<td class="listHeader">End Date</td>
	            		<td class="listHeader">Type</td>
	             	</tr>
	        	</thead>
				<tbody></tbody>	
         	</table>
		</td>
	</tr>
    <%@include file="/WEB-INF/pages/common/pagination.jsp"%>
</table>
<script>
$(document).ready(function(){
	showList(1);
	$('#'+'<%=SystemConstant.MenuCode.JADWAL_UJIAN%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		var namaKelas = this.namaKelas == null ? '-' : this.namaKelas;
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkJadwalUjian+'</td>'+
   				'<td class="listItem">'+this.tahunAjaran+'</td>'+
   				'<td class="listItem">'+this.namaPelajaran+'</td>'+
   				'<td class="listItem">'+namaKelas+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.startDate)+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.endDate)+'</td>'+
   				'<td class="listItem">'+this.lookupName+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>