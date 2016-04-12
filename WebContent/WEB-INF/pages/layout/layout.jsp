<%@page import="com.beesinergi.util.SystemParameter"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<%@page import="java.util.Date"%>
<%@page import="com.beesinergi.util.SystemConstant"%>
<%@page import="com.beesinergi.util.DateTimeFunction"%>
<s:layout-definition>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="app.name"/>">
    <meta name="author" content="Edy Rakhman">
    <title>${title}</title>
    <link rel="stylesheet" href="${contextPath}/css/style.css" >
    <link rel="stylesheet" href="${contextPath}/css/jquery-ui-1.8.6.custom.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/print.css" type="text/css" media="print">
    <link rel="stylesheet" href="${contextPath}/css/jquery.timepicker.css" type="text/css" media="print">
    <script type="text/javascript" src="${contextPath}/js/jquery-1.4.3.js"></script>
    <script type="text/javascript" src="${contextPath}/js/modal-dialog.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.6.custom.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/clock.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery.PrintArea.js"></script>
    <script type="text/javascript" src="${contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery.alphanumeric.js"></script>
    <script type="text/javascript" src="${contextPath}/js/moment.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery.timepicker.js"></script>
    <script>	
	$(document).ready(function(){
		$("tableList tbody tr:odd").css('background-color', 'white');
		$("tableList tbody tr:even").css('background-color', 'whitesmoke');
		startclock('<%=DateTimeFunction.date2String(new Date(), "MMM dd, yyyy kk:mm:ss")%>');
		$('.tabActive').removeClass('tabActive');
		$('.numeric').numeric();
		$('#searchForm').submit(function(){
			showList(1);
			return false; 
		});
	});
	
	function convertNumericToMoney(){
		$('.numeric').each(function(){
			$(this).val(formatMoney($(this).val()));
		});
	}
	
	function convertMoneyToNumeric(){
		$('.numeric').each(function(){
			if ($(this).val() != ''){
				$(this).val(removeFormatMoney($(this).val()));
			}
		});
	}
	
	function convertNumericHtmlToMoney(){
		$('.numericHtml').each(function(){
			alert($(this).html());
			if ($(this).html() != ''){
				$(this).html(formatMoney($(this).html()));
			}
		});
	}
	
	function convertMoneyToNumericHtml(){
		$('.numericHtml').each(function(){
			if ($(this).html() != ''){
				$(this).html(removeFormatMoney($(this).html()));
			}
		});
	}
	
	function datePicker(){
		$('.datepicker').datepicker({ 
			dateFormat: '<%= SystemConstant.DATE_PICKER_FORMAT %>',
			changeYear: true, 
			showOn:'button',
			buttonImage: '${contextPath}/images/calendaricon.gif', 
			buttonImageOnly: true,
			changeMonth:true
		}); 
		$('.datepicker').attr('readonly','readonly');
	}
	
	function dateTimePicker(){
		$('.datetimepicker').datetimepicker({
			dateFormat: '<%= SystemConstant.DATE_PICKER_FORMAT %>',
			changeYear: true, 
			showOn:'button',
			buttonImage: '${contextPath}/images/calendaricon.gif', 
			buttonImageOnly: true,
			changeMonth:true
	    });
		$('.datetimepicker').attr('readonly','readonly');
	}
	
	function showList(page){
		var param = $('#searchForm').serialize()+'&paging.page='+page;
		var url = '<s:url beanclass="${actionBean.beanClass}"/>?doGetList=';
		var tBody = '';
		$.getJSON(url,param,function(data){
			if(typeof populateTable == 'function'){
				tBody = populateTable(data.maintenanceList);
				if (tBody == ''){
					var colLength = $('#tableList thead th').length;
					tBody = '<tr><td class="listItemWithoutPointer" colspan="'+colLength+'"><fmt:message key="table.noData"/></td></tr>';
				}
				$('#tableList tbody').html(tBody);
				generatePaging(data);
				$('.listItem').click(function(){
					showDetail($(this).parent().children().first().html());
				});
			}
		});
		return false;
	}
	
	function showDetail(id){
		var param = 'maintenanceId='+id;
		var sURL = '<s:url beanclass="${actionBean.beanClass}"/>?showDetail=';
		$.get(sURL,param,function(data){
			openDialog(300, 400, 1, '${title}', data, 1);
			if (id == ''){
				$('#chkIsActive').attr('checked','checked');
				$('#chkIsActive').next().html('Active');
			}
			activeInactive();
			$('.numeric').numeric();
			convertNumericToMoney();
			$('.numeric').keyup(function(){
				formatMoneyKeyUp($(this));
			});
			datePicker();
			dateTimePicker();
		});	
	}
	
	function activeInactive(){
		if ($('#chkIsActive').is(':checked')){
			$('#chkIsActive').next().html('<fmt:message key="label.active"/>');
		} else{
			$('#chkIsActive').next().html('<fmt:message key="label.inactive"/>');
		}
	}
	
	function check(el){
		var checkedCount = $('.chk:checked').length;
		var rowCount = $('#tableList tbody tr').length;	
		if ($(el).is(':checked') && checkedCount == rowCount){
			$('#chkAll').attr('checked',true);
		} else{
			$('#chkAll').attr('checked',false);
		}
	}
	
	function checkAll(el){
		if ($(el).is(':checked')){
			$('.chk').attr('checked',true);
		} else{
			$('.chk').attr('checked',false);
		}
	}
	
	function formatDateTime(date){
		var m = moment(date);
		return m.format('DD MMM YYYY HH:mm');  
	}
	
	function getCurrentDate(){
		var m = moment(new Date());
		return m.format('DD-MM-YYYY HH:mm');
	}
	
	function formatDate(date){
		var m = moment(date);
		return m.format('<%= SystemConstant.JQUERY_DATE_FORMAT %>');
	}
	</script>
</head>

<body onload="initAJAXLoader('${contextPath}');initializeDialog(4)">
	<table cellspacing="0"  cellpadding="0" width="100%" height="55" border="0" >
		<tr>
			<td valign="top" >  
				<%-- <img width="230px" src="${contextPath}/images/additional/sales_logo2.png" style="margin:5px"> --%>
			</td> 
			<td width="50%" align="right"  valign="top">
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<td><div id="clockbox"></div></td>
						<td valign="top" class="menulink"  height="20"
							onclick="javascript:window.location='${actionBean.lastUrl}?locale=en'">
							English
						</td>
						<td>|</td>
						<td valign="top" class="menulink"  height="20"
							onclick="javascript:window.location='${actionBean.lastUrl}?locale=in'">
							Indonesia
						</td>
						<td>|</td>
						<td valign="top" class="menulink"  height="20"
							onclick="javascript:window.location='${contextPath}/action/changePassword'">
							<fmt:message key="app.changePassword"/>
						</td>
						<td>|</td>
						<td valign="top" class="menulink" height="20" 
							onclick="javascript:window.location='${contextPath}/action/login?doLogout='">
							(${actionBean.userInfo.fullName})&nbsp;<fmt:message key="app.logout"/>
						</td>
					</tr>
				</table> 
			</td>
		</tr>
		<%@include file="/WEB-INF/pages/layout/navigation.jsp"%>
	</table>
	<s:layout-component name="content" />
</body>
</html>
</s:layout-definition>
