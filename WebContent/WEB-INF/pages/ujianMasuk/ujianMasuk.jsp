<%@page import="com.beesinergi.util.SystemParameter"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<s:form method="post" id="searchForm" beanclass="${actionBean.beanClass}">
	  			<s:hidden name="model.fkPendaftaran"/>
	  			<s:hidden name="model.fkPelajaran"/>
		    	<table cellspacing="0" cellpadding="0" border="0">
		    		<tr id="btnStart">
		            	<td 
		            		class="toolBarImageButton"
		                	onmouseover="this.className='toolBarImageHover'"
		                    onmouseout="this.className='toolBarImageButton'"
		                    onclick="showDetail()" nowrap>
		                    <img src="<%=request.getContextPath()%>/images/icon-green.png" align="absMiddle"/>&nbsp;Start
		           		</td>
		           		
		           	</tr>
		  		</table>
	  		</s:form>
		</td>
	</tr>
	<tr> 
    	<td height="100%" valign="top" id="ujianMasukDetail">
    		<table cellspacing="10px">
   				<tr>
    				<td>Mata Pelajaran</td>
    				<td>:</td>
    				<td>${actionBean.jadwalUjian.namaPelajaran}</td>
    			</tr>
    			<tr>
    				<td>Durasi Ujian</td>
    				<td>:</td>
    				<td><%= SystemParameter.DURASI_UJIAN %> Menit</td>
    			</tr>	
    		</table>
    	</td>
	</tr>
</table>
<script>
var sessionWaktuUjian = '${sessionScope._waktuUjian}';
//var hasilUjian = '${actionBean.hasilUjian}';

$(document).ready(function(){
	$('#'+'<%=SystemConstant.MenuCode.UJIAN_MASUK%>').addClass('tabActive');
	if (sessionWaktuUjian == '0'){
		showResult();
	} else if(sessionWaktuUjian != ''){
		showDetail();
	} 
});

function showDetail(){
	var param = $('#searchForm').serialize();
	var sURL = '<s:url beanclass="${actionBean.beanClass}"/>?showDetail=';
	$.get(sURL,param,function(data){
		$('#ujianMasukDetail').html(data);
		$('#btnStart').html('<td id="countdown">&nbsp;&nbsp;Ujian akan berakhir dalam waktu <span class="minutes"></span> Menit <span class="seconds"></span> Detik </td>');
		if (sessionWaktuUjian == ''){
			var currentDate = new Date();
			var durasiUjian = '<%=SystemParameter.DURASI_UJIAN%>';
			currentDate.setMinutes(currentDate.getMinutes()+parseInt(durasiUjian));
			sessionWaktuUjian = currentDate;
		}
		$("#countdown").countdown({
			date: sessionWaktuUjian, 
		    format: "on"
		});
	});
}

function showResult(){
	var param = $('#searchForm').serialize();
	var sURL = '<s:url beanclass="${actionBean.beanClass}"/>?showResult=';
	$.get(sURL,param,function(data){
		$('#btnStart').html('');
		$('#ujianMasukDetail').html(data);
	});
}
</script>
</s:layout-component>
</s:layout-render>