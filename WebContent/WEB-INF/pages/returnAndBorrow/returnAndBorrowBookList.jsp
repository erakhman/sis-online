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
	            		<td class="listHeader">Book ID</td> 
	            		<td class="listHeader">Borrowed By</td> 
	            		<td class="listHeader">Start Date</td>
	            		<td class="listHeader">End Date</td>
	            		<td class="listHeader">Return Date</td>
	            		<td class="listHeader">Created Date</td>
	            		<td class="listHeader">Changed Date</td>
	            		<td class="listHeader">Created By</td>
	            		<td class="listHeader">Changed By</td> 
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
	$('#'+'<%=SystemConstant.MenuCode.RETURN_AND_BORROW_BOOK%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.id+'</td>'+
   				'<td class="listItem">'+this.bookId+'</td>'+
   				'<td class="listItem">'+this.borrowedBy+'</td>'+
   				'<td class="listItem">'+this.startDate+'</td>'+
   				'<td class="listItem">'+this.endDate+'</td>'+
   				'<td class="listItem">'+this.returnDate+'</td>'+
   				'<td class="listItem">'+this.createdDate+'</td>'+
   				'<td class="listItem">'+this.changedDate+'</td>'+
   				'<td class="listItem">'+this.createdBy+'</td>'+
   				'<td class="listItem">'+this.changedBy+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>