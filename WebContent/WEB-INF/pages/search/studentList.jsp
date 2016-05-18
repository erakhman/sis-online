<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<fmt:setLocale value="en_US" />

<table cellspacing="0" cellpadding="0" width="100%" height="95%">	
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>	
			<s:form id="searchStudentForm" method="POST" beanclass="${actionBean.beanClass}">
				<table cellspacing="0" cellpadding="0" border="0" width="100%">
					<tr>
						<td nowrap="nowrap">
				       		<b>Search By</b> <s:select name="student.kelas.pkKelas">
				       			<s:option value="">Semua Kelas</s:option>
				       			<s:options-collection collection="${actionBean.kelasList}" label="namaKelas" value="pkKelas"/>
				       		</s:select>
				       		<input type="text" name="student.namaSiswa" placeholder="Nama Siswa"/>
				        </td>
				        <td class="toolBarImageButton" onclick="searchStudent(1)">
				        	<img src="<%=request.getContextPath()%>/images/search24.gif" align="absMiddle"/>
				        </td>	
					</tr>
				</table>
			 </s:form>
			<table cellspacing="0" cellpadding="0" border="0" width="100%" id="studentList">
            	<thead>
            		<tr> 
            			<td class="hidden"></td> 
                    	<td class="listHeader">NIS</td> 
                   		<td class="listHeader">Nama Siswa</td>
                   		<td class="listHeader">Kelas</td>
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

function searchStudent(page){
	var param = $('#searchStudentForm').serialize()+'&paging.page='+page;
	var url = '${contextPath}/Search.action?doGetStudentList=';
	var tBody = '';
	$.getJSON(url,param,function(data){
		$.each(data.studentList,function(i){
			var className = this.className == null ? '-' : this.className; 
			tBody += 
	   			'<tr>'+
	   				'<td class="hidden">'+this.pkSiswa+'</td>'+
	   				'<td class="listItem">'+this.nis+'</td>'+
	   				'<td class="listItem">'+this.namaSiswa+'</td>'+
	   				'<td class="listItem">'+this.kelas.namaKelas+'</td>'+
	   			'</tr>';
		});
		if (tBody == ''){
			var colLength = $('#studentList thead td').length;
			tBody = '<tr><td class="listItemWithoutPointer" colspan="'+colLength+'"><fmt:message key="table.noData"/></td></tr>';
		}
		$('#studentList tbody').html(tBody);
		generatePaging(data);
		$('.listItem').click(function(){
			onclickStudentList($(this));
			closeDialog();
		});
		return false;
	});
}
</script>	