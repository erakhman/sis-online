<%@page import="com.beesinergi.util.SystemConstant"%>
<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" title="${actionBean.pageTitle}">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<s:form method="post" id="searchForm" beanclass="${actionBean.beanClass}">
		    	<table cellspacing="0" cellpadding="0" border="0">
					<tr>
		            	<td class="toolBarImageButton"
		                	onmouseover="this.className='toolBarImageHover'"
		                    onmouseout="this.className='toolBarImageButton'"
		                    onclick="showDetail(0)" nowrap>
		                    <img src="<%=request.getContextPath()%>/images/add24.png" align="absMiddle"/>&nbsp;<fmt:message key="btn.add"/>
		           		</td>
		               	<td width="100%"/>	
		           	</tr>
		  		</table>
	  		</s:form>
		</td>
	</tr>
	<tr>
    	<td height="100%" valign="top">
			<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
	        	<thead>
	            	<tr>
	            		<td class="hidden"></td>
	            		<td class="listHeader" width="5"></td> 
	            		<td class="listHeader">Account No</td> 
	            		<td class="listHeader">Account Name</td> 
	             	</tr>
	        	</thead>
				<tbody></tbody>	
         	</table>
		</td>
	</tr>
    <%@include file="/WEB-INF/pages/common/pagination.jsp"%>
</table>
<script>
$(document).ready(function(){
	showList(1);
	$('#'+'<%=SystemConstant.MenuCode.COA%>').addClass('tabActive');
});

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		var plusMinusImage = this.totalChild == 0 ? 
			'<td class="listItemWithoutPointer"><img src="${contextPath}/images/tree/nolines_minus.gif"/></td>' : 
			'<td class="listItemWithoutPointer" onclick="showChild('+this.pkCoa+')"><img src="${contextPath}/images/tree/nolines_plus.gif"/></td>';
		if (this.parentFkCoa == null){
			tBody += 
				'<tr class="'+this.parentFkCoa+'">'+
	   				'<td class="hidden">'+this.pkCoa+'</td>'+
	   				plusMinusImage+ 
	   				'<td class="listItem">'+this.acctNo+'</td>'+
	   				'<td class="listItem">'+this.acctName+'</td>'+
	   			'</tr>';
		} else{
			tBody += 
				'<tr class="'+this.parentFkCoa+'" style="display:none">'+
	   				'<td class="hidden">'+this.pkCoa+'</td>'+
	   				'<td class="listItemWithoutPointer"></td>'+ 
	   				'<td class="listItem">'+this.acctNo+'</td>'+
	   				'<td class="listItem">'+this.acctName+'</td>'+
	   			'</tr>';
		}
		
	});
	return tBody;
}

function showChild(parentFkCoa){
	if ($('.'+parentFkCoa).is( ":hidden" )){
		$('.'+parentFkCoa).show();
	} else{
		$('.'+parentFkCoa).hide();
	}
}
</script>
</s:layout-component>
</s:layout-render>