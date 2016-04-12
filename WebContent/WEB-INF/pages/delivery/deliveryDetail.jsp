<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkDelivery"/>
				<s:hidden name="model.status"/>
				<input type="hidden" id="pkProduct"/>
				<div id="hiddenArea"></div>
				<c:choose>
					<c:when test="${actionBean.model.pkDelivery != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table style="margin-right:10px">	
					<tr>
						<td class="caption"><fmt:message key="label.date"/></td>
						<td><s:text name="model.deliveryDate" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.poNo"/></td>
						<td>
							<c:choose>
								<c:when test="${actionBean.model.pkDelivery == null}">
									<s:select name="model.fkPo" onchange="populatePoDetailList()">
										<s:options-collection collection="${actionBean.poList}" value="pkPo" label="poNo"/>
									</s:select>	
								</c:when>
								<c:otherwise>
									<s:text name="model.poNo" disabled="disabled"/>
									<s:hidden name="model.fkPo"/>
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" border="0" id="poDetailList" style="margin-bottom:20px;margin-top:3px;width:100%">
	            	<thead>
	            		<tr>
	            			<td class="listHeader"><fmt:message key="label.category"/></td> 
	                    	<td class="listHeader"><fmt:message key="label.productName"/></td> 
	                   		<td class="listHeader"><fmt:message key="label.outstanding"/></td>
	                   		<td class="listHeader"><fmt:message key="label.qty"/></td>
	                   		<td class="hidden"></td>
	                   		<td class="listHeader"><fmt:message key="label.unit"/></td>
	                   		<td class="listHeader"><fmt:message key="label.stock"/></td>
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
	var pk = $('input[name="model.pkDelivery"]').val();
	if (pk == ''){
		$('input[name="model.deliveryDate"]').val(getCurrentDate());
	}
	populatePoDetailList();
	if ('${actionBean.model.isEditable}' == '<%= SystemConstant.NO %>'){
		$('#btnSave').remove();
	}
});

setTimeout(function(){
	//$('select[name="model.fkPo"]').focus();
	//$('select[name="model.poNo"]').focus();
},0);

function populatePoDetailList(){
	var pkDelivery = $('input[name="model.pkDelivery"]').val();
	var param = '&model.pkDelivery='+pkDelivery;
	if (pkDelivery == ''){
		param = '&model.fkPo='+$('select[name="model.fkPo"]').val();
	}
	var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetPoDetail=';
	$.getJSON(url,param,function(data){
		$('#poDetailList tbody').html('');
		$.each(data.poDetailList,function(i){
			var qty = $('input[name="model.pkDelivery"]').val() == '' ? '' : this.qty;
			var row = 
				'<tr>'+
					'<td class="listItem">'+this.categoryName+'</td>'+
					'<td class="listItem">'+this.productName+'</td>'+
					'<td class="listItem">'+this.outstanding+'</td>'+
					'<td class="listItem"><input type="text" name="model.deliveryDetailList['+i+'].qty" value="'+qty+'" style="width:40px" class="numeric"></td>'+
					'<td class="hidden">'+
						'<input type="hidden" value="'+this.fkProduct+'" name="model.deliveryDetailList['+i+'].fkProduct">'+
						'<input type="hidden" value="'+this.outstanding+'" name="model.deliveryDetailList['+i+'].outstanding">'+
						'<input type="hidden" value="'+this.productStock+'" name="model.deliveryDetailList['+i+'].productStock">'+
					'</td>'+
					'<td class="listItem">'+this.productUnit+'</td>'+
					'<td class="listItem">'+this.productStock+'</td>'+
				'</tr>';
			$('#poDetailList tbody').append(row);
		});
		$('.numeric').numeric();
		convertNumericToMoney();
	});
}
</script>	