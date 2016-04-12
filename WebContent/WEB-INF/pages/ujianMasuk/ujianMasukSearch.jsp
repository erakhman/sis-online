<%@page import="com.beesinergi.util.SystemParameter"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<s:form method="post" id="searchForm" beanclass="${actionBean.beanClass}">
	  			<input type="hidden" value="<%= SystemParameter.TAHUN_AJARAN_BERJALAN %>" name="ujianMasuk.tahunAjaran"/>
	  			<input type="hidden" value="9" name="ujianMasuk.fkPendaftaran"/>
		    	<table cellspacing="0" cellpadding="0" border="0">
		    		<tr>
						<td nowrap="nowrap"> 
		           			&nbsp;<b><fmt:message key="label.namaPelajaran"/></b>	
		               		<s:select name="fkPelajaran" onchange="showDetail(this)">
		               			<s:option value=""><fmt:message key="label.silahkanPilih"/></s:option>
								<s:options-collection collection="${actionBean.pelajaranList}" label="namaPelajaran" value="pkPelajaran"/>
							</s:select>
		              	</td>
						<td width="100%"/>
					</tr>
		  		</table>
	  		</s:form>
		</td>
	</tr>
	<tr>
    	<td height="100%" valign="top" id="ujianMasukDetail"></td>
	</tr>
</table>
<script>
$(document).ready(function(){
	$('#'+'<%=SystemConstant.MenuCode.UJIAN_MASUK%>').addClass('tabActive');
});

function showDetail(el){
	if ($(el).val() != ''){
		var param = $('#searchForm').serialize();
		var sURL = '<s:url beanclass="${actionBean.beanClass}"/>?showDetail=';
		$.get(sURL,param,function(data){
			$('#ujianMasukDetail').html(data);
		});
	} else{
		$('#ujianMasukDetail').html('');
	}
}
</script>
</s:layout-component>
</s:layout-render>