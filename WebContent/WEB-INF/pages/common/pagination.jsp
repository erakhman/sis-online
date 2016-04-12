<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<tr id="pagination">
	<td style="padding-left: 3px; padding-top: 5px;">
		<input type="button" onclick="prev()" value="&lt;" class="button" id="btnPrev">
		<input type="text" size="3" id="currentPage"/> of <span id="totalPage"></span> page(s)&nbsp;&nbsp;&nbsp;&nbsp;  
		<input type="button" onclick="go()" value="GO" class= "button" id="btnGo" style="margin-left:-10px;">
		<input type="button" onclick="next()" value="&gt;" class="button" id="btnNext">
	</td>
</tr>

<script>
function generatePaging(data){
	if (data.paging.totalPage > 1){
		$('#pagination').show();
		if (data.paging.page == 1){
			$('#btnPrev').hide();
		} else{
			$('#btnPrev').show();
		}
		if (data.paging.page == data.paging.totalPage){
			$('#btnNext').hide();
		} else{
			$('#btnNext').show();
		}
		$('#currentPage').val(data.paging.page);
		$('#totalPage').html(data.paging.totalPage);
	} else{
		$('#pagination').hide();
	}
}

function go(){
	var page = $('#currentPage').val();
	showList(page);
}

function next(){
	var activePage = $('#currentPage').val();
	var page = parseInt(activePage)+1;
	showList(page);
}

function prev(){
	var activePage = $('#currentPage').val();
	var page = parseInt(activePage)-1;
	showList(page);
}
</script>