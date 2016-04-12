<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" width="100%" height="95%">
	<%@include file="/WEB-INF/pages/common/button.jsp"%>		
	<tr>
		<td valign="top">
			<div id="errorMessage"></div>
			<s:form id="detailForm" method="POST" beanclass="${actionBean.beanClass}">
				<s:hidden name="doSave"/>
				<s:hidden name="model.pkReservation"/>
				<c:choose>
					<c:when test="${actionBean.model.pkReservation != null}"><input type="hidden" name="model.changedBy" value="${actionBean.userInfo.employeeName}"/></c:when>
					<c:otherwise><input type="hidden" name="model.createdBy" value="${actionBean.userInfo.employeeName}"/></c:otherwise>
				</c:choose>
				<table>
					<tr>
						<td class="caption"><fmt:message key="label.reservationType"/><b class="mandatory">*</b></td>
						<td>
							<s:radio name="model.reservationType" id="rdRoom" value="<%= SystemConstant.ReservationType.ROOM %>" onclick="showRoomInfo()"/><fmt:message key="label.room"/>
							<s:radio name="model.reservationType" id="rdCar" value="<%= SystemConstant.ReservationType.CAR %>" onclick="showCarInfo()"/><fmt:message key="label.car"/>
						</td>
					</tr>
					<tr id="roomInfo">
						<td class="caption"><fmt:message key="label.room"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkLookup" id="dpRoom">
								<s:options-collection collection="${actionBean.roomList}" label="lookupName" value="pkLookup"/>
							</s:select>
						</td>
					</tr>
					<tr class="carInfo">
						<td class="caption"><fmt:message key="label.car"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkLookup" id="dpCar">
								<s:options-collection collection="${actionBean.carList}" label="lookupName" value="pkLookup"/>
							</s:select>
						</td>
					</tr>
					<tr class="carInfo">
						<td class="caption"><fmt:message key="label.driverName"/><b class="mandatory">*</b></td>
						<td>
							<s:select name="model.fkDriver" id="dpDriver">
								<s:options-collection collection="${actionBean.driverList}" label="driverName" value="pkDriver"/>
							</s:select>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.dateFrom"/><b class="mandatory">*</b></td>
						<td>
							<s:text name="model.dateFrom" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/>
						</td>
					</tr>
					<tr>
						<td class="caption"><fmt:message key="label.dateTo"/><b class="mandatory">*</b></td>
						<td><s:text name="model.dateTo" class="datetimepicker" formatPattern="<%= SystemConstant.DATE_TIME_FORMAT %>"/></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>
<script> 
$(document).ready(function(){
	if ($('input[name="model.pkReservation"]').val() == ''){
		$('#rdRoom').attr('checked','checked');
	}
	if ($('#rdRoom').is(':checked')){
		showRoomInfo();
	} else{
		showCarInfo();
	}
});

function showRoomInfo(){
	$('#roomInfo').show();
	$('.carInfo').hide();
	$('#dpRoom').removeAttr('disabled');
	$('#dpCar,#dpDriver').attr('disabled','disabled');
}

function showCarInfo(){
	$('#roomInfo').hide();
	$('.carInfo').show();
	$('#dpCar,#dpDriver').removeAttr('disabled');
	$('#dpRoom').attr('disabled','disabled');
}
</script>