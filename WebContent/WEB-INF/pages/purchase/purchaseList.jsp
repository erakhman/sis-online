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
		               	<td nowrap="nowrap"> 	
		              		<b><fmt:message key="label.purchaseNo"/></b>
		               		<s:text name="model.purchaseNo"/>&nbsp;
		              	</td>
		               	<td nowrap="nowrap"> 	
		              		<b><fmt:message key="label.poOutNo"/></b>
		               		<s:text name="model.poOutNo"/>
		              	</td>
		                <td class="toolBarImageButton" onclick="showList(1)">
		                	<img src="<%=request.getContextPath()%>/images/search24.gif" align="absMiddle"/>
		                </td>
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
	            		<td class="listHeader"><fmt:message key="label.purchaseDate"/></td> 
	            		<td class="listHeader"><fmt:message key="label.purchaseNo"/></td>
	            		<td class="listHeader"><fmt:message key="label.poOutNo"/></td>
	            		<td class="listHeader"><fmt:message key="label.vendorName"/></td>
	                    <td class="listHeader"><fmt:message key="label.paidDate"/></td>
	                    <td class="listHeader"><fmt:message key="label.dueDate"/></td>
	                    <td class="listHeader"><fmt:message key="label.outstanding"/></td>
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
	$('#'+'<%=SystemConstant.MenuCode.PURCHASE%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkPurchase+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.purchaseDate)+'</td>'+
   				'<td class="listItem">'+this.purchaseNo+'</td>'+
   				'<td class="listItem">'+this.poOutNo+'</td>'+
   				'<td class="listItem">'+this.vendorName+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.paidDate)+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.dueDate)+'</td>'+
   				'<td class="listItem">'+formatMoney(this.outstanding)+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>