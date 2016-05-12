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
	            		<td class="listHeader">Kode Kelas</td> 
	            		<td class="listHeader">Nama Kelas</td> 
	            		<td class="listHeader">Wali Kelas</td>
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
	$('#'+'<%=SystemConstant.MenuCode.KELAS%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkKelas+'</td>'+
   				'<td class="listItem">'+this.kodeKelas+'</td>'+
   				'<td class="listItem">'+this.namaKelas+'</td>'+
   				'<td class="listItemWithoutPointer" align="center"><a href="#" style="color: red;" onclick="showClassHistory('+this.pkKelas+')"><strong>Lihat</strong></a></td>'+
   			'</tr>';
	});
	return tBody;
}

function showClassHistory(id){
	var param = 'model.fkClass='+id;
	var sURL = '${contextPath}/ClassHistory.action';
	$.get(sURL,param,function(data){
		openDialog(300, 300, 1, 'Wali Kelas', data, 1);
	});	
}
</script>
</s:layout-component>
</s:layout-render>