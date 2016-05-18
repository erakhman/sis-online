<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<fmt:setLocale value="en_US" />

<table cellspacing="0" cellpadding="0" width="100%" height="95%">	
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>	
			<s:form id="searchTeacherForm" method="POST" beanclass="${actionBean.beanClass}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%">
					<tr>
						<td nowrap="nowrap">
				       		<b>Search By</b> <s:select name="teacher.status">
				       			<s:option value="">Semua Status</s:option>
				       			<s:option value="<%= SystemConstant.StaffStatus.EMPLOYEE %>"><%= SystemConstant.StaffStatus().get(SystemConstant.StaffStatus.EMPLOYEE) %></s:option>
				       			<s:option value="<%= SystemConstant.StaffStatus.HONORER %>"><%= SystemConstant.StaffStatus().get(SystemConstant.StaffStatus.HONORER) %></s:option>
				       		</s:select>
				       		<input type="text" name="teacher.name" placeholder="Nama Guru"/>
				        </td>
				        <td class="toolBarImageButton" onclick="searchTeacher(1)">
				        	<img src="<%=request.getContextPath()%>/images/search24.gif" align="absMiddle"/>
				        </td>	
					</tr>
				</table>
			 </s:form>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" id="teacherList">
            	<thead>
            		<tr> 
            			<td class="hidden"></td> 
                    	<td class="listHeader">NIP</td> 
                   		<td class="listHeader">Nama Guru</td>
                 	</tr>
             	</thead>
				<tbody></tbody>
			</table>
		</td>
	</tr>
	<%-- <%@include file="/WEB-INF/pages/common/pagination.jsp"%> --%>
</table>
<script>
$(document).ready(function(){
	
});

function searchTeacher(page){
	var param = $('#searchTeacherForm').serialize()+'&paging.page='+page;
	var url = '${contextPath}/Search.action?doGetTeacherList=';
	var tBody = '';
	$.getJSON(url,param,function(data){
		$.each(data.teacherList,function(i){
			var className = this.className == null ? '-' : this.className; 
			tBody += 
	   			'<tr>'+
	   				'<td class="hidden">'+this.pkStaff+'</td>'+
	   				'<td class="listItem">'+this.code+'</td>'+
	   				'<td class="listItem">'+this.name+'</td>'+
	   			'</tr>';
		});
		if (tBody == ''){
			var colLength = $('#teacherList thead td').length;
			tBody = '<tr><td class="listItemWithoutPointer" colspan="'+colLength+'"><fmt:message key="table.noData"/></td></tr>';
		}
		$('#teacherList tbody').html(tBody);
		generatePaging(data);
		$('.listItem').click(function(){
			onclickTeacherList($(this));
			closeDialog();
		});
		return false;
	});
}
</script>	