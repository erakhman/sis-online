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
		              		<b><fmt:message key="label.deliveryNo"/></b>
		               		<s:text name="model.deliveryNo"/>
		              	</td>
		              	<td nowrap="nowrap"> 	
		              		<b><fmt:message key="label.poNo"/></b>
		               		<s:text name="model.poNo"/>
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
	            		<td class="listHeader"><fmt:message key="label.date"/></td>
	            		<td class="listHeader"><fmt:message key="label.deliveryNo"/></td>  
	                    <td class="listHeader"><fmt:message key="label.poNo"/></td>
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
	$('#'+'<%=SystemConstant.MenuCode.DELIVERY%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkDelivery+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.deliveryDate)+'</td>'+
   				'<td class="listItem">'+this.deliveryNo+'</td>'+
   				'<td class="listItem">'+this.poNo+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>