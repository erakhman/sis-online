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
	            		<td class="listHeader">Nama Pelajaran</td>
	            		<td class="listHeader">Soal Pelajaran</td> 
	            		<td class="listHeader">Pilihan A</td>
	            		<td class="listHeader">Pilihan B</td>
	            		<td class="listHeader">Pilihan C</td>
	            		<td class="listHeader">Pilihan D</td>
	            		<td class="listHeader">Pilihan E</td> 
	            		<td class="listHeader">Level</td>
	            		<td class="listHeader">Jawaban</td>
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
	$('#'+'<%=SystemConstant.MenuCode.SOAL%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkSoal+'</td>'+
   				'<td class="listItem">'+this.namaPelajaran+'</td>'+
   				'<td class="listItem">'+this.soalPelajaran+'</td>'+
   				'<td class="listItem">'+this.pilihanA+'</td>'+
   				'<td class="listItem">'+this.pilihanB+'</td>'+
   				'<td class="listItem">'+this.pilihanC+'</td>'+
   				'<td class="listItem">'+this.pilihanD+'</td>'+
   				'<td class="listItem">'+this.pilihanE+'</td>'+
   				'<td class="listItem">'+this.level+'</td>'+
   				'<td class="listItem">'+this.jawaban+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>