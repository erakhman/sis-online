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
	            		<td class="listHeader">Kode Member</td> 
	            		<td class="listHeader">Nama</td> 
	            		<td class="listHeader">Type</td> 
	            		<td class="listHeader">No Identitas</td> 
	            		<td class="listHeader">Status</td> 
	            		<td class="listHeader">Start date</td> 
	            		<td class="listHeader">End date</td> 
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
	$('#'+'<%=SystemConstant.MenuCode.LIBRARY_MEMBER%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkLibraryMember+'</td>'+
   				'<td class="listItem">'+this.memberCode+'</td>'+
   				'<td class="listItem">'+this.memberName+'</td>'+
   				'<td class="listItem">'+this.memberTypeDescr+'</td>'+
   				'<td class="listItem">'+this.memberIdentityNo+'</td>'+
   				'<td class="listItem">'+this.memberStatusDescr+'</td>'+
   				'<td class="listItem">'+formatDate(this.startDate)+'</td>'+
   				'<td class="listItem">'+formatDate(this.endDate)+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>