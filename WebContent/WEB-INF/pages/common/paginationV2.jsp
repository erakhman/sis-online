<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<div class="row" style="border-top:1px solid #59B4C5;font-size:13px">
	<div class="pagination dark">
		<span id="paginationV2"></span>
		&nbsp;<input type="text" style="padding:0px;width: 50px;" id="txtPage"/>
		&nbsp;<a href="#selectedPageV2" class="page dark gradient" onclick="go(this)">GO</a>
	</div>	
	<div class="pagination dark">
		<span style="margin-right: 20px;"></span>
		<a href="#selectedPageV2" class="page dark gradient" onclick="doPrev(this)" id="btnPrev">PREV</a>
		<span id="pagination"></span>
		<a href="#selectedPageV2" class="page dark gradient" onclick="doNext(this)" id="btnNext">NEXT</a>
	</div>		
	
</div>

<script>

function generatePaging(data,containerId){
	var newContainerId = $('#'+containerId).parent();
	var tableNo = $(newContainerId).children().attr('id').split('-')[1];
	$('#btnNext,#btnPrev,#txtPage',newContainerId).attr('id',tableNo);
	var paginationId = $('#pagination',newContainerId);
	if (data.paging.page == 1){
		$(paginationId).prev().hide();
	} else{
		$(paginationId).prev().show();
	}
	if (data.paging.page == data.paging.totalPage){
		$(paginationId).next().hide();
	} else{
		$(paginationId).next().show();
	}
	$(paginationId).prev().prev().html('Show '+data.paging.page+' of '+data.paging.totalPage+' page');
	var paging = '';
	for (var i=0; i<data.paging.totalPage; i++){
		var page = i+1;
		if (data.paging.page == page){
			paging += '<a href="#selectedPageV2" class="page dark gradient active" onclick="showPagingList('+page+','+tableNo+')" id="selectedPage-'+tableNo+'">'+(i+1)+'</a>';
		} else{
			paging += '<a href="#selectedPageV2" class="page dark gradient" onclick="showPagingList('+page+','+tableNo+')">'+(i+1)+'</a>';
		}
	}
	$(paginationId).html(paging);
}

function showPagingList(page,tableNo){
	showList(page,tableNo);
}

function go(el){
	var page = $(el).prev().val();
	var tableNo = $(el).prev().attr('id');
	showList(page,tableNo);
}

function doNext(el){
	var tableNo = $(el).attr('id');
	var activePage = $('#selectedPage-'+tableNo).html();
	var page = parseInt(activePage)+1;
	showList(page,tableNo);
}

function doPrev(el){
	var tableNo = $(el).attr('id');
	var activePage = $('#selectedPage-'+tableNo).html();
	var page = parseInt(activePage)-1;
	showList(page,tableNo);
}
</script>