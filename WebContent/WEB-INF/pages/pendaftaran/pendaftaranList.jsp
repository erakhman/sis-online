<%@page import="com.beesinergi.util.SystemParameter"%>
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
		                    onclick="examPass()" nowrap>
		                    <img src="<%=request.getContextPath()%>/images/select24.gif" align="absMiddle"/>&nbsp;Lulus
		           		</td>
		               	<td width="100%"/>	
		               	<td nowrap="nowrap">
		               		<b>Order By</b> <s:select name="model.orderBy">
		               			<s:option value="a.nem">NEM</s:option>
		               			<s:option value="total_nilai_ujian">Total Nilai Ujian</s:option>
		               		</s:select>
		                </td>
		                <td nowrap="nowrap">
		               		&nbsp;<b>Status</b> <s:select name="model.status">
		               			<s:option value="<%= SystemConstant.RegistrationStatus.NEW %>"><%= SystemConstant.RegistrationStatus().get(SystemConstant.RegistrationStatus.NEW) %></s:option>
		               			<s:option value="<%= SystemConstant.RegistrationStatus.PASSED %>"><%= SystemConstant.RegistrationStatus().get(SystemConstant.RegistrationStatus.PASSED) %></s:option>
		               		</s:select>
		               		<input type="text" name="model.kodePendaftaran" placeholder="Kode Pendaftaran"/>
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
	    		<input type="hidden" name="model.status" value="<%= SystemConstant.RegistrationStatus.PASSED %>"/>
				<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
		        	<thead>
		            	<tr>
		            		<td class="hidden"></td>
		            		<td class="listHeader" width="5px"><input type="checkbox" id="chkAll" onclick="checkAll(this)"></td> 
		            		<td class="listHeader">Tahun Ajaran</td>
		            		<td class="listHeader">Kode Pendaftaran</td>
		            		<td class="listHeader"><fmt:message key="label.namaSiswa"/></td> 
		            		<td class="listHeader">Tgl Lahir</td>   
		            		<td class="listHeader">User Name</td>   
		            		<td class="listHeader">NEM</td> 
		            		<td class="listHeader">Total Nilai Ujian</td> 
		            		<!-- <td class="listHeader">Status</td>  -->
		            		<td class="listHeader">Daftar Ulang</td> 
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
	$('#'+'<%=SystemConstant.MenuCode.PENDAFTARAN%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = ''; 
	$.each(data,function(i){
		var daftarUlang = this.status == '<%= SystemConstant.RegistrationStatus.PASSED %>' ? '<a href="#" style="color: red;" onclick="showReRegister('+this.pkPendaftaran+')"><strong>Lihat</strong></a>' : '-';					
		tBody += 
   			'<tr>'+
   				'<td class="hidden">'+this.pkPendaftaran+'</td>'+
   				'<td class="listItemWithoutPointer" align="center">'+
					'<input type="checkbox" class="chk" onclick="check(this)" name="pkPendaftaranList" value="'+this.pkPendaftaran+'">'+
				'</td>'+
   				'<td class="listItem">'+this.tahunAjaran+'</td>'+
   				'<td class="listItem">'+this.kodePendaftaran+'</td>'+
   				'<td class="listItem">'+this.namaSiswa+'</td>'+
   				'<td class="listItem">'+formatDate(this.tglLahir)+'</td>'+
   				'<td class="listItem">'+this.userName+'</td>'+
   				'<td class="listItem">'+this.nem+'</td>'+
   				'<td class="listItem">'+this.totalNilaiUjian+'</td>'+
   				/* '<td class="listItem">'+this.statusDescr+'</td>'+ */
   				'<td class="listItemWithoutPointer" align="center">'+daftarUlang+'</td>'+
   			'</tr>';
	});
	return tBody;
}

function examPass(){
	var url = $('#detailForm2').attr('action')+'?doExamPass=';
	var xhr = $.post(url,$('#detailForm2').serialize(),function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			showList(1);
		} else{
			$('#errorMessage').html(data);
		}
	});
}

function showReRegister(id) {
	var param = 'model.pkPendaftaran='+id;
	var sURL = '<s:url beanclass="${actionBean.beanClass}"/>?showReRegister=';
	$.get(sURL,param,function(data){
		openDialog(300, 400, 1, 'Daftar Ulang', data, 1);
	});	
}
</script>
</s:layout-component>
</s:layout-render>