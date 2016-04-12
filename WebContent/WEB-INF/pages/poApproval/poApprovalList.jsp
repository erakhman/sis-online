<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<s:form method="post" id="searchForm" beanclass="${actionBean.beanClass}">
	  			<input type="hidden" name="model.statusList" value="<%= SystemConstant.PoStatus.NEED_APPROVAL %>"/>
	  			<input type="hidden" name="model.statusList" value="<%= SystemConstant.PoStatus.APPROVED %>"/>
	  			<input type="hidden" name="model.statusList" value="<%= SystemConstant.PoStatus.REJECTED %>"/>
		    	<table cellspacing="0" cellpadding="0" border="0">
					<tr>
		               	<td width="100%"/>	
		               	<%-- <td nowrap="nowrap"> 	
		               		<b><fmt:message key="label.status"/></b> 
		               		<s:select name="model.status">
		               			<s:option value=""><fmt:message key="label.pleaseSelect"/></s:option>
		               			<s:options-collection collection="${actionBean.statusList}" label="statusName" value="statusCode"/>
		               		</s:select>&nbsp;
		              	</td>  --%>
	              		<td nowrap="nowrap"> 	
		              		<b><fmt:message key="label.employeeName"/></b>
		               		<s:select name="model.fkEmployee">
		               			<s:option value=""><fmt:message key="label.pleaseSelect"/></s:option>
		               			<s:options-collection collection="${actionBean.employeeList}" label="employeeName" value="pkEmployee"/>
		               		</s:select>&nbsp;
		              	</td>
		              	<td nowrap="nowrap"> 	
		              		<b><fmt:message key="label.department"/></b>
		               		<s:select name="model.fkDepartment">
		               			<s:option value=""><fmt:message key="label.pleaseSelect"/></s:option>
		               			<s:options-collection collection="${actionBean.departmentList}" label="lookupName" value="pkLookup"/>
		               		</s:select>&nbsp;
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
	            		<td class="listHeader"><fmt:message key="label.poNo"/></td>
	                    <td class="listHeader"><fmt:message key="label.status"/></td>
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
	$('#'+'<%=SystemConstant.MenuCode.PO_APPROVAL%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkPo+'</td>'+
   				'<td class="listItem">'+formatDateTime(this.poDate)+'</td>'+
   				'<td class="listItem">'+this.poNo+'</td>'+
   				'<td class="listItem">'+this.statusName+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>