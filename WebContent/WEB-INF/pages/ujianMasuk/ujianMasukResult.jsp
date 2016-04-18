<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="10px">
	<tr>
		<td>Jumlah Soal</td>
		<td>:</td>
		<td>${actionBean.hasilUjian.jumlahSoal}</td>
	</tr>
	<tr>
		<td>Jawaban Yang Benar</td>
		<td>:</td>
		<td>${actionBean.hasilUjian.jawabanBenar}</td>
	</tr>
	<tr>
		<td>Jawaban Yang Salah</td>
		<td>:</td>
		<td>${actionBean.hasilUjian.jawabanSalah}</td>
	</tr>
	<tr>
		<td>Score</td>
		<td>:</td>
		<td>${actionBean.hasilUjian.score}</td>
	</tr>		
</table>