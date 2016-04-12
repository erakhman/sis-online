<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<tr>
	<td valign="top" height="10%">
	<table cellspacing="0" cellpadding="0">
		<tr>
			<td class="toolBarImageButton"
				onmouseover="this.className='toolBarImageHover'"
				onmouseout="this.className='toolBarImageButton'"
				onclick="save()" id="btnSave">
				<img src="${contextPath}/images/save24.gif" align="absMiddle"/>&nbsp;<fmt:message key="btn.save"/>
			</td>
		</tr>
	</table>
	</td>
</tr>
<script>
function save(){
	$('.disabled').removeAttr('disabled');
	var url = $('#detailForm').attr('action');
	$('.numeric').each(function(){
		if ($(this).val() != ''){
			$(this).val(removeFormatMoney($(this).val()));
		}
	});
	var xhr = $.post(url,$('#detailForm').serialize(),function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			showList(1);
			closeDialog();
		} else{
			convertNumericToMoney();
			$('#errorMessage').html(data);
			$('.disabled').attr('disabled','disabled');
		}
	});
} 
</script>