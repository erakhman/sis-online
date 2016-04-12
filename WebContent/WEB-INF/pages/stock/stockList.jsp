<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<form method="post" id="searchForm">
		    	<table cellspacing="0" cellpadding="0" border="0">
					<tr>
		            	<td class="toolBarImageButton"
		                	onmouseover="this.className='toolBarImageHover'"
		                    onmouseout="this.className='toolBarImageButton'"
		                    onclick="showDetail(0)" nowrap>
		                    <img src="<%=request.getContextPath()%>/images/approval_history.png" align="absMiddle"/>&nbsp;<fmt:message key="btn.adjustment"/>
		           		</td>
		               	<td width="100%"/>	
		           	</tr>
		  		</table>
	  		</form>
		</td>
	</tr>
	<tr>
    	<td height="100%" valign="top">
			<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
	        	<thead>
	            	<tr>
	            		<td class="hidden"></td>
	            		<td class="listHeader"><fmt:message key="label.productName"/></td>  
	                    <td class="listHeader"><fmt:message key="label.trxDate"/></td>  
	                    <td class="listHeader"><fmt:message key="label.trxNo"/></td>
	                    <td class="listHeader"><fmt:message key="label.trxQty"/></td>
	                    <td class="listHeader"><fmt:message key="label.startStock"/></td>
	                    <td class="listHeader"><fmt:message key="label.endStock"/></td>  
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
	$('#'+'<%=SystemConstant.MenuCode.STOCK%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkStock+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.productName+'</td>'+
   				'<td class="listItemWithoutPointer">'+formatDate(this.trxDate)+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.trxNo+'</td>'+
   				'<td class="listItemWithoutPointer">'+this.trxQty+'</td>'+
   				'<td class="listItemWithoutPointer">'+formatMoney(this.startStock)+'</td>'+
   				'<td class="listItemWithoutPointer">'+formatMoney(this.endStock)+'</td>'+
   			'</tr>'; 
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>