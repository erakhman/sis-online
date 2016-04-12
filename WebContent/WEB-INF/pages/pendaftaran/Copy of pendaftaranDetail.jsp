<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPendaftaran"/>
				<s:hidden name="model.siswa.pkSiswa"/>
				<input type="hidden" id="pkPelajaran"/>
				<%-- <c:choose>
					<c:when test="${actionBean.model.pkPendaftaran != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose> --%>
				<table>
					<tr>
						<td class="caption"><fmt:message key="label.namaSiswa"/><b class="mandatory">*</b></td>
						<td><s:text name="model.siswa.namaSiswa"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.namaPelajaran"/><b class="mandatory">*</b></td>
						<td><input type="text" id="namaPelajaran"/></td>
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
				<div style="margin-bottom:-11px">&nbsp;</div>
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
	populatePelajaranList();
});

function populatePelajaranList(){
	var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetPelajaranList=';
	$.getJSON(url,function(data){
		var pelajaranArr = [];
		$.each(data.pelajaranList,function(i){
			pelajaranArr.push(this.namaPelajaran);
		});
		$('#namaPelajaran').autocomplete({
			source: pelajaranArr,
			change: function(e) {
				populatePelajaranDetail();
				$('#nilai').focus();
			},
			select: function() {
				populatePelajaranDetail();
				$('#nilai').focus();
			}
		});
	});
}

function populatePelajaranDetail(){
	if ($('#namaPelajaran').val() != ''){
		var param = '&namaPelajaran='+$('#namaPelajaran').val();
		var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetPelajaranDetail=';
		$.getJSON(url,param,function(data){
			$('#pkPelajaran').val(data.pelajaran.pkPelajaran);
			$('#namaPelajaran').val(data.pelajaran.namaPelajaran);
		});
	} 
}

function populateNilaiList(){
	if (validateBeforeSaveNilai()){
		var row = 
			'<tr>'+
				'<td class="hidden">'+$('#pkPelajaran').val()+'</td>'+
				'<td class="listItem">'+$('#namaPelajaran').val()+'</td>'+
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
		$('#pkPelajaran').val($(this).parent().children().eq(0).html());
		$('#namaPelajaran').val($(this).parent().children().eq(1).html());
		$('#nilai').val($(this).parent().children().eq(2).html());
		$('#btnAddNilai').hide();
		$('#btnEditNilai').show();
		$('#btnCancelNilai').show();
		selectedRowNilai = $(this).parent();
		$('#namaPelajaran').focus();
	});
}

function save(){
	$('#nilaiList tbody').find('tr').each(function(i){
		$('#detailForm').append('<input type="hidden" name="model.pendaftaranDetailList['+i+'].fkPelajaran" value="'+$(this).children().eq(0).html()+'"/>');
		$('#detailForm').append('<input type="hidden" name="model.pendaftaranDetailList['+i+'].nilai" value="'+$(this).children().eq(2).html()+'"/>');
	});
	var url = $('#detailForm').attr('action');
	var xhr = $.post(url,$('#detailForm').serialize(),function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			showList(1);
			closeDialog();
		} else{
			convertNumericToMoney();
			$('#errorMessage').html(data);
		}
	});
}
</script>