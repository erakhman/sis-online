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
		                    <img src="<%=request.getContextPath()%>/images/add24.png" align="absMiddle"/>&nbsp;<fmt:message key="btn.add"/>
		           		</td>
		               	<td width="100%"/>	
		                <td nowrap="nowrap"> 	
		               		<select name="model.reservationType">
		               			<option value="<%= SystemConstant.ReservationType.ROOM %>"><fmt:message key="label.room"/></option>
		               			<option value="<%= SystemConstant.ReservationType.CAR %>"><fmt:message key="label.car"/></option>
		               		</select>
		              	</td>
		                <td class="toolBarImageButton" onclick="showList(1)">
		                	<img src="<%=request.getContextPath()%>/images/search24.gif" align="absMiddle"/>
		                </td>
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
	            		<td class="hidden"><fmt:message key="label.pkReservation"/></td>  
	                    <td class="listHeader"><fmt:message key="label.name"/></td>  
	                    <td class="listHeader"><fmt:message key="label.dateFrom"/></td>
	                    <td class="listHeader"><fmt:message key="label.dateTo"/></td>  
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
	$('#'+'<%=SystemConstant.MenuCode.RESERVATION%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkReservation+'</td>'+
   				'<td class="listItem">'+this.lookupName+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.dateFrom)+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.dateTo)+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>