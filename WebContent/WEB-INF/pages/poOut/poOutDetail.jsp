<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkPoOut"/>
				<s:hidden name="model.status"/>
				<input type="hidden" id="pkProduct"/>
				<div id="hiddenArea"></div>
				<c:choose>
					<c:when test="${actionBean.model.pkPoOut != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table style="margin-right:10px">	
					<%-- <tr>
						<td class="caption"><fmt:message key="label.date"/></td>
						<td><s:text name="model.poOutDate" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/></td>
					</tr> --%>
					<tr>
						<td class="caption"><fmt:message key="label.poNo"/></td>
						<td>
							<c:choose>
								<c:when test="${actionBean.model.pkPoOut == null}">
									<s:select name="model.fkPo" id="fkPo" onchange="populatePoDetailList()">
										<s:options-collection collection="${actionBean.poList}" value="pkPo" label="poNo"/>
									</s:select>
								</c:when>
								<c:otherwise>
									<s:text name="model.poNo" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.vendor"/></td>
						<td>
							<c:choose>
								<c:when test="${actionBean.model.pkPoOut == null}">
									<s:select name="model.fkVendor">
									<s:options-collection collection="${actionBean.vendorList}" value="pkVendor" label="vendorName"/>
								</s:select>
								</c:when>
								<c:otherwise>
									<s:text name="model.vendorName" disabled="disabled"/>
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
	                   		<td class="listHeader"><fmt:message key="label.reqQty"/></td>
	                   		<td class="listHeader"><fmt:message key="label.stock"/></td>
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
	//var currentDate = formatDateTime(new Date());
	//$('input[name="model.poOutDate"]').val(currentDate);
	if ($('input[name="model.pkPoOut"]').val() != ''){
		populatePoOutDetailList();
	} else{
		populatePoDetailList();
	}
});

setTimeout(function(){
	$('input[name="model.poOutNo"]').focus();
},0);

function populatePoOutDetailList(){
	populateDetailList('&model.pkPoOut='+$('input[name="model.pkPoOut"]').val());
}

function populatePoDetailList(){
	populateDetailList('&model.fkPo='+$('#fkPo').val());
}

function deleteProduct(el){
	$(el).parent().parent().remove();
}

function populateDetailList(param){
	var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetPoDetail=';
	$.getJSON(url,param,function(data){
		$('#poDetailList tbody').html('');
		$.each(data.poDetailList,function(i){
			var reqQty = $('input[name="model.pkPoOut"]').val() == '' ? this.qty : this.reqQty;
			var row = 
				'<tr>'+
					'<td class="listItem">'+this.categoryName+'</td>'+
					'<td class="listItem">'+this.productName+'</td>'+
					'<td class="listItem">'+reqQty+'</td>'+
					'<td class="hidden">'+
						'<input type="hidden" name="model.poOutDetailList['+i+'].fkProduct" value="'+this.fkProduct+'"/>'+
						'<input type="hidden" name="model.poOutDetailList['+i+'].reqQty" value="'+reqQty+'"/>'+
						'<input type="hidden" name="model.poOutDetailList['+i+'].productName" value="'+this.productName+'"/>'+
					'</td>'+
					'<td class="listItem">'+this.productStock+'</td>'+
					'<td class="listItem"><input type="text" name="model.poOutDetailList['+i+'].qty" value="'+this.qty+'" style="width:40px" class="numeric"></td>'+
					'<td><a href="#" onclick="deleteProduct(this)">&nbsp;[X]</a></td>'+
				'</tr>';
			$('#poDetailList tbody').append(row);
		});
		$('.numeric').numeric();
		convertNumericToMoney();
	});
}
</script>	