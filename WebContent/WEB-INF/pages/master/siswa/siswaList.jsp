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
		           		<td class="toolBarSeparatorRight"></td>
		           		<td class="toolBarImageButton"
		                	onmouseover="this.className='toolBarImageHover'"
		                    onmouseout="this.className='toolBarImageButton'"
		                    onclick="showStudentClass()" nowrap>
		                    <img src="<%=request.getContextPath()%>/images/assign24.gif" align="absMiddle"/>&nbsp;Pembagian Kelas
		           		</td>
		               	<td width="100%"/>
		               	<td nowrap="nowrap">
		               		<b>Order By</b> <s:select name="model.orderBy">
		               			<s:option value="a.nama_siswa">Nama Siswa</s:option>
		               			<s:option value="a.nem">NEM</s:option>		               			
		               		</s:select>
		               		<s:select name="model.sortBy">
		               			<s:option value="ASC">ASC</s:option>
		               			<s:option value="DESC">DESC</s:option>		               			
		               		</s:select>
		                </td>
		                <td nowrap="nowrap">
		               		&nbsp;<b>Filter By</b> <s:select name="model.fkKelas">
		               			<s:option value="">Semua Kelas</s:option>
		               			<s:option value="0">Belum Ada Kelas</s:option>
		               			<s:options-collection collection="${actionBean.kelasList}" label="namaKelas" value="pkKelas"/>
		               		</s:select>
		               		<input type="text" name="model.namaSiswa" placeholder="Nama Siswa"/>
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
    		<form method="post" id="detailForm2" action="">
				<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
		        	<thead>
		            	<tr>
		            		<td class="hidden"></td>
		            		<td class="listHeader" width="5px"><input type="checkbox" id="chkAll" onclick="checkAll(this)"></td>
		            		<td class="listHeader">Nama Siswa</td> 
		            		<td class="listHeader">NEM</td> 
		            		<td class="listHeader">Kelas</td> 
		             	</tr>
		        	</thead>
					<tbody></tbody>	
	         	</table>
         	</form>
		</td>
	</tr>
    <%@include file="/WEB-INF/pages/common/pagination.jsp"%>
</table>
<script>
$(document).ready(function(){
	showList(1);
	$('#'+'<%=SystemConstant.MenuCode.SISWA%>').addClass('tabActive');
});

function showStudentClass(){
	var param = $('#detailForm2').serialize();
	var sURL = '${contextPath}/StudentClass.action';
	$.get(sURL,param,function(data){
		openDialog(300, 300, 1, 'Pembagian Kelas', data, 1);
	});	
}

function showStudentClassHistory(){
	var param = $('#detailForm2').serialize();
	var sURL = '${contextPath}/StudentClass.action?showHistory';
	$.get(sURL,param,function(data){
		openDialog(300, 300, 1, 'History Kelas', data, 1);
	});	
}

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		var className = this.className == null ? '-' : this.className; 
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkSiswa+'</td>'+
   				'<td class="listItemWithoutPointer" align="center">'+
					'<input type="checkbox" class="chk" onclick="check(this)" name="model.pkStudentList" value="'+this.pkSiswa+'">'+
				'</td>'+
   				'<td class="listItem">'+this.namaSiswa+'</td>'+
   				'<td class="listItem">'+this.nem+'</td>'+
   				'<td class="listItemWithoutPointer" align="center"><a href="#" style="color: red;" onclick="showStudentClassHistory('+this.fkKelas+','+this.pkSiswa+')"><strong>'+className+'</strong></a></td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>