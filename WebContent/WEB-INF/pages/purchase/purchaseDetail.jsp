<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPurchase"/>
				<input type="hidden" id="pkProduct"/>
				<div id="hiddenArea"></div>
				<c:choose>
					<c:when test="${actionBean.model.pkPurchase != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table style="margin-right:10px">	
					<tr>
						<td class="caption"><fmt:message key="label.date"/></td>
						<td><s:text name="model.purchaseDate" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.purchaseNo"/><b class="mandatory">*</b></td>
						<td><s:text name="model.purchaseNo"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.poOutNo"/></td>
						<td>
							<c:choose>
								<c:when test="${actionBean.model.pkPurchase == null}">
									<s:select name="model.fkPoOut" onchange="populatePoOutDetailList()">
										<s:options-collection collection="${actionBean.poOutList}" value="pkPoOut" label="poOutNo"/>
									</s:select>
								</c:when>
								<c:otherwise>
									<s:text name="model.poOutNo" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.vendor"/></td>
						<td>
							<c:choose>
								<c:when test="${actionBean.model.pkPurchase == null}">
									<s:select name="model.fkVendor" disabled="true">
										<s:options-collection collection="${actionBean.vendorList}" value="pkVendor" label="vendorName"/>
									</s:select>
								</c:when>
								<c:otherwise>
									<s:text name="model.vendorName" disabled="disabled"/>
								</c:otherwise>
							</c:choose>	
							
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.paidDate"/><b class="mandatory">*</b></td>
						<td><s:text name="model.paidDate" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.dueDate"/><b class="mandatory">*</b></td>
						<td><s:text name="model.dueDate" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.payment"/><b class="mandatory">*</b></td>
						<td><s:text name="model.payment" class="numeric"/></td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.outstanding"/><b class="mandatory">*</b></td>
						<td><s:text name="model.outstanding" class="numeric"/></td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" border="0" id="poOutDetailList" style="margin-bottom:20px;margin-top:3px;width:100%">
	            	<thead>
	            		<tr>
	            			<td class="listHeader"><fmt:message key="label.category"/></td> 
	                    	<td class="listHeader"><fmt:message key="label.productName"/></td>
	                    	<td class="listHeader"><fmt:message key="label.basicPrice"/></td>  
	                   		<td class="listHeader"><fmt:message key="label.qty"/></td>
	                     	<td class="hidden"></td>
	                 	</tr>
	             	</thead>
					<tbody></tbody>
				</table>
			</s:form> 
		</td>
	</tr>
</table>
<script>
var selectedRowProduct;

$(document).ready(function(){
	if ($('input[name="model.pkPurchase"]').val() != ''){
		populatePurchaseDetailList();
	} else{
		populatePoOutDetailList();
		$('input[name="model.purchaseDate"]').val(getCurrentDate());
	}
});

setTimeout(function(){
	$('input[name="model.purchaseNo"]').focus();
},0);

function populatePurchaseDetailList(){
	populateDetailList('&model.pkPurchase='+$('input[name="model.pkPurchase"]').val());
}

function populatePoOutDetailList(){
	populateDetailList('&model.fkPoOut='+$('select[name="model.fkPoOut"]').val());
}

function populateDetailList(param){
	var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetPoOutDetail=';
	$.getJSON(url,param,function(data){
		$('#poOutDetailList tbody').html('');
		$.each(data.poOutDetailList,function(i){
			var price = this.price == null ? 0 : this.price;
			var row = 
				'<tr>'+
					'<td class="listItem">'+this.categoryName+'</td>'+
					'<td class="listItem">'+this.productName+'</td>'+
					'<td class="listItem"><input type="text" name="model.purchaseDetailList['+i+'].price" value="'+price+'" style="width:40px" class="numeric"></td>'+
					'<td class="listItem"><input type="text" name="model.purchaseDetailList['+i+'].qty" value="'+this.qty+'" style="width:40px" class="numeric"></td>'+
					'<td class="hidden"><input type="hidden" value="'+this.fkProduct+'" name="model.purchaseDetailList['+i+'].fkProduct"></td>'+
					'<td><a href="#" onclick="deleteProduct(this)">&nbsp;[X]</a></td>'+
				'</tr>';
			$('#poOutDetailList tbody').append(row);
		});
	});
}

function deleteProduct(el){
	$(el).parent().parent().remove();
}
</script>	