<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<c:if test="${actionBean.model.status == 1 || actionBean.model.status == 2 || actionBean.model.status == 3}">
		<tr>
			<td valign="top" height="10%">
			<table cellspacing="0" cellpadding="0"> 
				<tr>
					<td class="toolBarImageButton"
						onmouseover="this.className='toolBarImageHover'"
						onmouseout="this.className='toolBarImageButton'"
						onclick="approve()" id="btnApprove">
						<img src="${contextPath}/images/select24.gif" align="absMiddle"/>&nbsp;<fmt:message key="btn.approve"/>
					</td>
					<td class="toolBarImageButton"
						onmouseover="this.className='toolBarImageHover'"
						onmouseout="this.className='toolBarImageButton'"
						onclick="reject()" id="btnReject">
						<img src="${contextPath}/images/cancel24.gif" align="absMiddle"/>&nbsp;<fmt:message key="btn.reject"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>	
	</c:if>	 
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPo"/>
				<s:hidden name="model.status"/>
				<input type="hidden" id="pkProduct"/>
				<div id="hiddenArea"></div>
				<c:choose>
					<c:when test="${actionBean.model.pkPo != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table>	
					<%-- <tr>
						<td class="caption"><fmt:message key="label.date"/></td>
						<td><s:text name="model.poDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.poNo"/></td>
						<td><s:text name="model.poNo"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr> --%>
					<tr>
						<td class="caption"><fmt:message key="label.category"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="productCategory" id="productCategory" onchange="populateProductName(this)">
								<s:options-collection collection="${actionBean.categoryList}" value="pkLookup" label="lookupName"/>
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.productName"/><b class="mandatory">*</b></td>
						<td><input type="text" id="productName"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.qty"/><b class="mandatory">*</b></td>
						<td>
							<input type="text" id="productQty" class="numeric" style="width:35px"/>
							<input type="text" id="productUnit" disabled="disabled" style="width:38px"/>
							<input type="text" id="productStock" disabled="disabled" style="width:35px"/>
						</td>
					</tr>
					<tr>
						<td class="caption"></td>
						<td class="caption">
							<input type="button" onclick="addProduct()" id="btnAddProduct" value="<fmt:message key="btn.add"/>"/>
							<input type="button" onclick="editProduct()" id="btnEditProduct" style="display:none" value="<fmt:message key="btn.edit"/>"/>
							<input type="button" onclick="clearProduct()" id="btnCancelProduct" style="display:none" value="<fmt:message key="btn.clear"/>"/>
						</td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" border="0" id="poDetailList" style="margin-bottom:20px;margin-top:3px;width:100%">
	            	<thead>
	            		<tr>
	            			<td class="listHeader"><fmt:message key="label.category"/></td> 
	                    	<td class="listHeader"><fmt:message key="label.productName"/></td> 
	                   		<td class="listHeader"><fmt:message key="label.qty"/></td>
	                   		<td class="listHeader"><fmt:message key="label.unit"/></td>
	                     	<td class="hidden"></td>
	                     	<td class="hidden"></td>
	                     	<td class="listHeader"><fmt:message key="label.outstanding"/></td>
	                     	<td class="listHeader"><fmt:message key="label.stock"/></td>
	                 	</tr>
	             	</thead>
					<tbody>
						<c:forEach items="${actionBean.model.poDetailList}" var="detail">
							<tr>
								<td class="listItem">${detail.categoryName}</td>
								<td class="listItem">${detail.productName}</td>
								<td class="listItem"><fmt:formatNumber maxFractionDigits="3" value="${detail.qty}"/></td>
								<td class="listItem">${detail.productUnit}</td>
								<td class="hidden">${detail.fkProduct}</td>
								<td class="hidden">${detail.fkCategory}</td>
								<td class="listItem"><fmt:formatNumber maxFractionDigits="3" value="${detail.outstanding}"/></td>
								<td class="listItem"><fmt:formatNumber maxFractionDigits="3" value="${detail.productStock}"/></td>
								<td><a href="#" onclick="deleteProduct(this)">&nbsp;[X]</a></td>
							</tr>	
						</c:forEach>
					</tbody>
				</table>
			</s:form> 
		</td>
	</tr>
</table>
<script>
var selectedRowProduct;

$(document).ready(function(){
	//var currentDate = formatDate(new Date());
	//$('input[name="model.poDate"]').val(currentDate);
	populateProductName($('#productCategory'));
	onclickPoDetailList();
});

function populateProductName(el){
	var param =  '&product.fkCategory='+$(el).val();
	var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetProductList=';
	$.getJSON(url,param,function(data){
		var productArr = [];
		$.each(data.productList,function(i){
			productArr.push(this.productName);
		});
		$("#productName").autocomplete({
			source: productArr,
			change: function(e) {
				populateProductDetail();
				$('#productQty').focus();
			},
			select: function() {
				populateProductDetail();
				$('#productQty').focus();
			}
		});
	});
}

function populateProductDetail(){
	if ($('#productName').val() != ''){
		var param = '&product.productName='+$('#productName').val();
		var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetProductDetail=';
		$.getJSON(url,param,function(data){
			if (data.product != null){
				$('#pkProduct').val(data.product.pkProduct);
				$('#productUnit').val(data.product.unitName);
				$('#productStock').val(data.product.qty);
			} else{
				alert('Product is not found');
			}
		});
	} 
}

function populatePoDetailList(isRepeat){
	if (validateBeforeAddEditProduct()){
		var row = 
			'<tr>'+
				'<td class="listItem">'+$("#productCategory option:selected").text()+'</td>'+
				'<td class="listItem">'+$('#productName').val()+'</td>'+
				'<td class="listItem">'+$('#productQty').val()+'</td>'+
				'<td class="listItem">'+$('#productUnit').val()+'</td>'+
				'<td class="hidden">'+$('#pkProduct').val()+'</td>'+
				'<td class="hidden">'+$('#productCategory').val()+'</td>'+
				'<td class="listItem">'+$('#productQty').val()+'</td>'+
				'<td class="listItem">'+$('#productStock').val()+'</td>'+
				'<td><a href="#" onclick="deleteProduct(this)">&nbsp;[X]</a></td>'+
			'</tr>';
		$('#poDetailList tbody').append(row);	
		onclickPoDetailList();
		clearProduct();
		$('#productName').focus();
	}
}

function onclickPoDetailList(){
	$('#poDetailList tbody').find('td').click(function(){
		$('#productName').val($(this).parent().children().eq(1).html());
		$('#productQty').val($(this).parent().children().eq(2).html());
		$('#productUnit').val($(this).parent().children().eq(3).html());
		$('#pkProduct').val($(this).parent().children().eq(4).html());
		$('#productCategory').val($(this).parent().children().eq(5).html());
		$('#productStock').val($(this).parent().children().eq(7).html());
		$('#btnAddProduct').hide();
		$('#btnEditProduct').show();
		$('#btnCancelProduct').show();
		selectedRowProduct = $(this).parent();
		$('#productName').focus();
	});
}

function save(){
	$('#poDetailList tbody').find('tr').each(function(i){
		$('#hiddenArea').append('<input type="hidden" name="model.poDetailList['+i+'].qty" value="'+removeFormatMoney($(this).children().eq(2).html())+'"/>');
		$('#hiddenArea').append('<input type="hidden" name="model.poDetailList['+i+'].outstanding" value="'+removeFormatMoney($(this).children().eq(2).html())+'"/>');
		$('#hiddenArea').append('<input type="hidden" name="model.poDetailList['+i+'].fkProduct" value="'+$(this).children().eq(4).html()+'"/>');
	});
	var url = $('#detailForm').attr('action');
	var xhr = $.post(url,$('#detailForm').serialize(),function(data){
		if(xhr.getResponseHeader('X-Stripes-Success')){
			showList(1);
			closeDialog();
		} else{
			$('#errorMessage').html(data);
			$('#hiddenArea').html('');
		}
	});
} 

function approve(){
	var status = '<%= SystemConstant.PoStatus.APPROVED %>'; 
	$('input[name="model.status"]').val(status);
	save();
}

function reject(){
	var status = '<%= SystemConstant.PoStatus.REJECTED %>'; 
	$('input[name="model.status"]').val(status);
	save();
}

function validateBeforeAddEditProduct(){
	var isSuccess = true;
	if ($('#pkProduct').val() == ''){
		$('#productName').focus();
		isSuccess = false;
	} else if ($('#productQty').val() == ''){
		$('#productQty').focus();
		isSuccess = false;
	}
	if (!isSuccess){
		alert('Please complete the field');
	}
	return isSuccess;
}

function addProduct(){
	populatePoDetailList();
}

function editProduct(el){
	$(selectedRowProduct).remove();
	populatePoDetailList();
}

function deleteProduct(el){
	$(el).parent().parent().remove();
}

function clearProduct(){	
	$('#productCategory').val($("#productCategory option:first").val());
	$('#pkProduct,#productName,#productQty,#productUnit,#productStock').val('');
	$('#btnAddProduct').show();
	$('#btnEditProduct').hide();
	$('#btnCancelProduct').hide();
}
</script>	