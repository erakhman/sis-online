<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doAdjustment"/>
				<input type="hidden" id="pkProduct"/>
				<div id="hiddenArea"></div>
				<c:choose>
					<c:when test="${actionBean.model.pkStock != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table>	
					<tr>
						<td class="caption"><fmt:message key="label.date"/></td>
						<td><s:text name="model.trxDate" class="datepicker" formatPattern="<%= SystemConstant.DATE_FORMAT %>"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
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
						<td>
							<input type="text" id="productName"/>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.startStock"/><b class="mandatory">*</b></td>
						<td>
							<input type="text" id="startStock" disabled="disabled"/>
						</td>
					</tr>   
					<tr>
						<td class="caption"><fmt:message key="label.endStock"/><b class="mandatory">*</b></td>
						<td><input type="text" id="endStock"/></td>
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
				<table cellspacing="0" cellpadding="0" border="0" id="productList" style="margin-bottom:20px;width:100%;margin-top:3px">
	            	<thead>
	            		<tr>
	            			<td class="listHeader"><fmt:message key="label.category"/></td> 
	                    	<td class="listHeader"><fmt:message key="label.productName"/></td> 
	                   		<td class="listHeader"><fmt:message key="label.startStock"/></td>
	                   		<td class="listHeader"><fmt:message key="label.endStock"/></td>
	                   		<td class="hidden"></td>
	                 	</tr>
	             	</thead>
					<tbody>
					</tbody>
				</table>
			</s:form> 
		</td>
	</tr>
</table>
<script>
var selectedRowProduct;

$(document).ready(function(){
	var currentDate = formatDate(new Date());
	$('input[name="model.trxDate"]').val(currentDate);
	populateProductName($('#productCategory'));
});

function changeEndStock(){
	var startStock = $('#startStock').val();
	var trxQty = $('#trxQty').val();
	var endStock = removeFormatMoney(startStock) + removeFormatMoney(trxQty);
	$('#endStock').val(formatMoney(endStock));
}

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
				$('#startStock').val(data.product.qty);
				$('#endStock').focus();
			} else{
				alert('Product is not found');
			}
		});
	} 
}

function populateProductList(){
	if (validateBeforeAddEditProduct()){
		var row = 
			'<tr>'+
				'<td class="listItem">'+$("#productCategory option:selected").text()+'</td>'+
				'<td class="listItem">'+$('#productName').val()+'</td>'+
				'<td class="listItem">'+$('#startStock').val()+'</td>'+
				'<td class="listItem">'+$('#endStock').val()+'</td>'+
				'<td class="hidden">'+$('#pkProduct').val()+'</td>'+
				'<td><a href="#" onclick="deleteProduct(this)">&nbsp;[X]</a></td>'+
			'</tr>';
		$('#productList tbody').append(row);	
		onclickProductList();
		clearProduct();
		$('#productName').focus();
	}
}


function onclickProductList(){
	$('#productList tbody').find('td').click(function(){
		$('#productCategory').val($(this).parent().children().eq(0).html());
		$('#productName').val($(this).parent().children().eq(1).html());
		$('#startStock').val($(this).parent().children().eq(2).html());
		$('#endStock').val($(this).parent().children().eq(3).html());
		$('#pkProduct').val($(this).parent().children().eq(4).html());
		$('#btnAddProduct').hide();
		$('#btnEditProduct').show();
		$('#btnCancelProduct').show();
		selectedRowProduct = $(this).parent();
		$('#productName').focus();
	});
}

function save(){
	$('#productList tbody').find('tr').each(function(i){
		$('#hiddenArea').append('<input type="hidden" name="model.productList['+i+'].fkProduct" value="'+$(this).children().eq(4).html()+'"/>');
		$('#hiddenArea').append('<input type="hidden" name="model.productList['+i+'].startStock" value="'+removeFormatMoney($(this).children().eq(2).html())+'"/>');
		$('#hiddenArea').append('<input type="hidden" name="model.productList['+i+'].endStock" value="'+removeFormatMoney($(this).children().eq(3).html())+'"/>');
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
	populateProductList();
}

function editProduct(el){
	$(selectedRowProduct).remove();
	populateProductList();
}

function deleteProduct(el){
	$(el).parent().parent().remove();
}

function clearProduct(){	
	$('#productCategory').val($("#productCategory option:first").val());
	$('#pkProduct,#productName,#startStock,#endStock').val('');
	$('#btnAddProduct').show();
	$('#btnEditProduct').hide();
	$('#btnCancelProduct').hide();
}
</script>	