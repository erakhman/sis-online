<%@page import="com.beesinergi.util.SystemParameter"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPendaftaran"/>
				<s:hidden name="model.userName"/>
				<s:hidden name="model.siswa.pkSiswa"/>
				<c:choose>
					<c:when test="${actionBean.model.pkPendaftaran != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.fullName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.fullName}"/></c:otherwise>
				</c:choose>
				<fieldset>
					<legend>Rincian Siswa</legend>
					<table>
						<tr>
							<td class="caption"><fmt:message key="label.namaSiswa"/><b class="mandatory">*</b></td>
							<td><s:text name="model.namaSiswa"/></td>
						</tr>
						<tr>
							<td class="caption">Tgl Lahir<b class="mandatory">*</b></td>
							<td><s:text name="model.tglLahir" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
						</tr>
						<tr>
							<td class="caption">NEM<b class="mandatory">*</b></td>
							<td><s:text name="model.nem"/></td>
						</tr>
						<tr>
							<td class="caption">Biaya<b class="mandatory">*</b></td>
							<td><s:text name="model.biayaPendaftaran" readonly="true" class="numeric"/></td>
						</tr>
						<tr>
							<td class="caption">Status<b class="mandatory">*</b></td>
							<td>
								<s:select name="model.status">
									<s:option value="<%= SystemConstant.RegistrationStatus.NEW %>"><%= SystemConstant.RegistrationStatus().get(SystemConstant.RegistrationStatus.NEW) %></s:option>
		               				<s:option value="<%= SystemConstant.RegistrationStatus.PASSED %>"><%= SystemConstant.RegistrationStatus().get(SystemConstant.RegistrationStatus.PASSED) %></s:option>
								</s:select>
							</td>
						</tr>
					</table>
				</fieldset>
				
				<div style="margin-bottom:-11px">&nbsp;</div>
				<fieldset>
					<legend>Rincian NEM</legend>
					<table>
					<tr>
						<td class="caption"><fmt:message key="label.namaPelajaran"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="" id="namaPelajaran">
								<s:options-collection collection="${actionBean.pelajaranList}" label="namaPelajaran" value="pkPelajaran"/>
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.nilai"/><b class="mandatory">*</b></td>
						<td><input type="text" id="nilai"/></td>
					</tr>
					<tr>
						<td class="caption"></td>
						<td class="caption">
							<s:button name="" id="btnAddNilai"><fmt:message key="btn.add"/></s:button>
							<s:button name="" id="btnEditNilai" style="display:none"><fmt:message key="btn.edit"/></s:button>
							<s:button name="" id="btnCancelNilai" style="display:none"><fmt:message key="btn.cancel"/></s:button>
						</td>
					</tr>
				</table>
					<table cellspacing="0" cellpadding="0" border="0" id="nilaiList" style="margin-bottom:20px; width:100%">
	            	<thead>
	            		<tr> 
	            			<td class="hidden"></td> 
	                    	<td class="listHeader"><fmt:message key="label.namaPelajaran"/></td> 
	                   		<td class="listHeader"><fmt:message key="label.nilai"/></td>
	                 	</tr>
	             	</thead>
					<tbody>
						<c:forEach items="${actionBean.model.pendaftaranDetailList}" var="detail">
							<tr>
								<td class="hidden">${detail.fkPelajaran}</td>
								<td class="listItem">${detail.namaPelajaran}</td>
								<td class="listItem">${detail.nilai}</td>
								<td><a href="#" onclick="deleteNilai(this)">&nbsp;[X]</a></td>
							</tr>	
						</c:forEach>
					</tbody>
				</table>
				</fieldset>	
				
			</s:form>
		</td>
	</tr>
</table>
<script>
var selectedRowNilai;

$(function(){
	$('#btnAddNilai').click(function(){
		populateNilaiList();
	});
	$('#btnEditNilai').click(function(){
		$(selectedRowNilai).remove();
		populateNilaiList();
	});
	$('#btnCancelNilai').click(function(){
		cancelNilai();
	});
	onclickNilaiList();
	if ($('input[name="model.pkPendaftaran"]').val() == ''){
		$('input[name="model.biayaPendaftaran"]').val('<%= SystemParameter.REGISTRATION_FEE %>');
	}
});

function populateNilaiList(){
	if (validateBeforeSaveNilai()){
		var row = 
			'<tr>'+
				'<td class="hidden">'+$('#namaPelajaran').val()+'</td>'+
				'<td class="listItem">'+$('#namaPelajaran option:selected').text()+'</td>'+
				'<td class="listItem">'+$('#nilai').val()+'</td>'+
				'<td><a href="#" onclick="deleteNilai(this)">&nbsp;[X]</a></td>'+
			'</tr>';
		$('#nilaiList tbody').append(row);	
		onclickNilaiList();
		cancelNilai();
	}
}

function deleteNilai(el){
	$(el).parent().parent().remove();
}

function cancelNilai(){	
	$('#namaPelajaran,#nilai').val('');
	$('#btnAddNilai').show();
	$('#btnEditNilai').hide();
	$('#btnCancelNilai').hide();
}

function validateBeforeSaveNilai(){
	var isSuccess = true;
	if ($('#namaPelajaran').val() == ''){
		$('#namaPelajaran').focus();
		isSuccess = false;
	} else if ($('#nilai').val() == ''){
		$('#nilai').focus();
		isSuccess = false;
	}
	if (!isSuccess){
		alert('Silahkan lengkapi data.');
	}
	return isSuccess;
}

function onclickNilaiList(){
	$('#nilaiList tbody').find('td').click(function(){
		$('#namaPelajaran').val($(this).parent().children().eq(0).html());
		$('#nilai').val($(this).parent().children().eq(2).html());
		$('#btnAddNilai').hide();
		$('#btnEditNilai').show();
		$('#btnCancelNilai').show();
		selectedRowNilai = $(this).parent();
		$('#namaPelajaran').focus();
	});
}

function insertAdditionalValue(){
	$('#nilaiList tbody').find('tr').each(function(i){
		$('#detailForm').append('<input type="hidden" name="model.pendaftaranDetailList['+i+'].fkPelajaran" value="'+$(this).children().eq(0).html()+'"/>');
		$('#detailForm').append('<input type="hidden" name="model.pendaftaranDetailList['+i+'].nilai" value="'+$(this).children().eq(2).html()+'"/>');
	});
}
</script>