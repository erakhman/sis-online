<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
	<s:hidden name="doSave"/>
	<table cellspacing="10px">
		<c:forEach items="${actionBean.soalList}" var="soal" varStatus="loop">
			<tr style="font-weight: bold">
				<td>${loop.index+1})</td>
				<td>${soal.soalPelajaran}</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="model.ujianMasukDetailList[${loop.index}].fkSoal" value="${soal.pkSoal}"/>
					<input type="hidden" name="model.ujianMasukDetailList[${loop.index}].jawabanSoal" value="${soal.jawaban}"/>
					<s:radio name="model.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanA}"/>${soal.pilihanA}&nbsp;
					<s:radio name="model.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanB}"/>${soal.pilihanB}&nbsp;
					<s:radio name="model.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanC}"/>${soal.pilihanC}&nbsp;
					<s:radio name="model.ujianMasukDetailList[${loop.index}].jawabanSiswa" value="${soal.pilihanD}"/>${soal.pilihanD}&nbsp;
					<c:if test="${soal.pilihanE != null}">
						<s:radio name="jawabanList[${loop.index}]" value="${soal.pilihanE}"/>${soal.pilihanE}
					</c:if>				
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td><input type="button" value="<fmt:message key="btn.save"/>" onclick="save()"></td>
		</tr>	
	</table>
</s:form>
<script>
$(function(){});

function save(){
	var fkPendaftaran = $('input[name="model.fkPendaftaran"]').val();
	var fkPelajaran = $('input[name="model.fkPelajaran"]').val();
	var url = $('#detailForm').attr('action');
	var param = '&model.fkPendaftaran='+fkPendaftaran+'&model.fkPelajaran='+fkPelajaran;
	var xhr = $.post(url,$('#detailForm').serialize()+param,function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			showResult();
		} else{
			$('#errorMessage').html(data);
		}
	});
} 
</script>