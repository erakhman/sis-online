<%@page import="com.beesinergi.util.SystemParameter"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
	<s:hidden name="doSave"/>
	<input type="hidden" value="<%= SystemParameter.TAHUN_AJARAN_BERJALAN %>" name="ujianMasuk.tahunAjaran"/>
	<input type="hidden" value="9" name="ujianMasuk.fkPendaftaran"/>
	<table cellspacing="10px">
		<c:forEach items="${actionBean.soalList}" var="soal" varStatus="loop">
			
			<tr style="font-weight: bold">
				<td>${loop.index+1})</td>
				<td>${soal.soalPelajaran}</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="ujianMasuk.ujianMasukDetailList[${loop.index}].fkSoal" value="${soal.pkSoal}"/>
					<input type="hidden" name="ujianMasuk.ujianMasukDetailList[${loop.index}].jawabanSoal" value="${soal.jawaban}"/>
					<s:radio name="ujianMasuk.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanA}"/>${soal.pilihanA}&nbsp;
					<s:radio name="ujianMasuk.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanB}"/>${soal.pilihanB}&nbsp;
					<s:radio name="ujianMasuk.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanC}"/>${soal.pilihanC}&nbsp;
					<s:radio name="ujianMasuk.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanD}"/>${soal.pilihanD}&nbsp;
					<c:if test="${soal.pilihanE != null}">
						<s:radio name="jawabanList[${loop.index}]" value="${soal.pilihanE}"/>${soal.pilihanE}
					</c:if><p>
					<c:if test="${actionBean.isAlreadyExam == true}">
						Jawaban yang benar: ${soal.jawaban}		
					</c:if>						
				</td>
			</tr>
		</c:forEach>
		<c:if test="${actionBean.isAlreadyExam == false}">
			<tr>
				<td></td>
				<td><input type="button" value="<fmt:message key="btn.save"/>" onclick="save()"></td>
			</tr>	
		</c:if>	
	</table>
</s:form>
<script>
$(function(){
	var isAlreadyExam = '${actionBean.isAlreadyExam}';
	if (isAlreadyExam == 'true'){
		$(':radio').attr('disabled','disabled');
	}
});

function save(){
	var fkPelajaran = $('select[name="fkPelajaran"]').val();
	var url = $('#detailForm').attr('action');
	var xhr = $.post(url,$('#detailForm').serialize()+'&fkPelajaran='+fkPelajaran,function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			alert('Data sudah berhasil disimpan!');
			$('#ujianMasukDetail').html(data);
		} else{
			$('#errorMessage').html(data);
		}
	});
}
</script>