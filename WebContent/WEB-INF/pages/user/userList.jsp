<%@page import="com.beesinergi.model.AppUser"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">
	<tr>
    	<td class="toolBar">
    		<form method="post" id="searchForm" action="${actioBean.beanClass}">
	        	<table cellspacing="0" cellpadding="0" border="0">
	            	<tr>
	            		<td class="toolBarImageButton"
	                    	onmouseover="this.className='toolBarImageHover'"
	                      	onmouseout="this.className='toolBarImageButton'"
	                      	onclick="showDetail(0)" nowrap>
	                      	<img src="<%=request.getContextPath()%>/images/user24.gif" align="absMiddle"/>&nbsp;Tambah
	            		</td> 
	            		<td class="toolBarImageButton"
	                    	onmouseover="this.className='toolBarImageHover'"
	                      	onmouseout="this.className='toolBarImageButton'"
	                      	onclick="deleteUser()" nowrap>
	                      	<img src="<%=request.getContextPath()%>/images/trash.gif" align="absMiddle"/>&nbsp;Hapus
	            		</td> 
	            		<td class="toolBarImageButton"
	                    	onmouseover="this.className='toolBarImageHover'"
	                      	onmouseout="this.className='toolBarImageButton'"
	                      	onclick="resetPassword()" nowrap>
	                      	<img src="<%=request.getContextPath()%>/images/reset24.gif" align="absMiddle"/>&nbsp;Ganti Password
	            		</td> 
		               	<td width="100%"/>
		               	<td nowrap="nowrap">
	                		&nbsp;<input type="text" style="width:150px" placeholder="Username" name="model.userName"/>
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
    		<form method="post" id="tableListForm" action="${actioBean.beanClass}">
	   			<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
	          		<thead>
		          		<tr>
		          			<td class="hidden"></td>
		          			<td class="listHeader" width="5px"><input type="checkbox" id="chkAll" onclick="checkAll(this)"></td> 
		                   	<td class="listHeader"><fmt:message key="label.userName"/></td> 
		                   	<td class="listHeader"><fmt:message key="label.fullName"/></td>
		                   	<td class="listHeader"><fmt:message key="label.lastLogin"/></td>
		                   	<td class="listHeader"><fmt:message key="label.wrongPassword"/></td>
		                   	<td class="listHeader"><fmt:message key="label.status"/></td>
		                   		<td class="listHeader">Role</td>
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
	$('#'+'<%=SystemConstant.MenuCode.USER%>').addClass('tabActive');
	$('#chkAll').removeAttr('checked');
});

function validateCheck(){
	var checkedCount = $('.chk:checked').length;
	if (checkedCount == 0){
		alert('Silahkan pilih minimal satu.');
		return false;
	}
	return true;
}

function deleteUser(){
	if (validateCheck()){
		var msg = confirm('Apakah anda yakin mau menghapus user?'); 
		if(msg){
			var url = $('#tableListForm').attr('action')+'?doDelete=';
			var xhr = $.post(url,$('#tableListForm').serialize(),function(data){
				if(xhr.getResponseHeader('X-Stripes-Success')){
					showList(1);
				} else{
					alert(data);
				}
			});
		}
	}
} 

function resetPassword(){
	if (validateCheck()){
		var msg = confirm('Apakah anda yakin mau mengganti password?');
		if (msg){
			var url = $('#tableListForm').attr('action')+'?doResetPassword=';
			var xhr = $.post(url,$('#tableListForm').serialize(),function(data){
				if(xhr.getResponseHeader('X-Stripes-Success')){
					showList(1);
					$('#chkAll').removeAttr('checked');
					alert('Password sudah berhasil diganti.');
				} else{
					alert(data);
				}
			});
		}
	}	
} 

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){ 
		var isLocked = this.isLocked == 'Y' ? 'Terkunci' : 'Aktif';
		var lastLoginDate = this.lastLoginDate == null ? '-' : formatDateTime(this.lastLoginDate);
		tBody += 
  			'<tr>'+
  				'<td class="hidden">'+this.pkUser+'</td>'+
  				'<td class="listItemWithoutPointer" align="center">'+
  					'<input type="checkbox" class="chk" onclick="check(this)" name="pkUserList" value="'+this.pkUser+'">'+
  				'</td>'+
  				'<td class="listItem">'+this.userName+'</td>'+
  				'<td class="listItem">'+this.fullName+'</td>'+
  				'<td class="listItem">'+lastLoginDate+'</td>'+
  				'<td class="listItem">'+this.wrongPassword+'</td>'+
  				'<td class="listItem">'+isLocked+'</td>'+  
  				'<td class="listItem">'+this.roleName+'</td>'+
  			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>