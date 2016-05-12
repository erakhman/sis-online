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
	            		<td class="listHeader"><fmt:message key="label.namaSiswa"/></td> 
	            		<td class="listHeader">Nama Pelajaran</td>  
	            		<td class="listHeader">Jumlah Soal</td>   
	            		<td class="listHeader">Jawaban Benar</td>   
	            		<td class="listHeader">Jawaban Salah</td>
	            		<td class="listHeader">Score</td>      
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
	$('#'+'<%=SystemConstant.MenuCode.HASIL_UJIAN_MASUK%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkHasilUjianMasuk+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.pendaftaran.tahunAjaran+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.pendaftaran.namaSiswa+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.namaPelajaran+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.jumlahSoal+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.jawabanBenar+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.jawabanSalah+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.score+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>