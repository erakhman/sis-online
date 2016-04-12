<%@include file="/WEB-INF/pages/layout/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/pages/layout/layout.jsp" pageTitle="ICD">
<s:layout-component name="content">
<table cellpadding="0" cellspacing="0" width="100%">   
	<tr>
	  	<td class="toolBar">
	  		<form method="post" id="searchForm">
		    	<table cellspacing="0" cellpadding="0" border="0">
					<tr>
		               	<td width="100%"/>	
		                <td nowrap="nowrap">
		                 	&nbsp;<input type="text" style="width:150px" placeholder="Nama Parameter" name="model.name"/>
		                </td>
		                <td class="toolBarImageButton" onclick="showList(1)">
		                	<img src="<%=request.getContextPath()%>/images/search24.gif" align="absMiddle"/>
		                </td>
		           	</tr>
		  		</table>
	  		</form>
		</td>
	</tr>
	<tr>
    	<td height="100%" valign="top">
			<table cellspacing="0" cellpadding="0" id="tableList" width="100%">
	        	<thead>
	            	<tr> 
	                    <td class="listHeader">Description</td>
	                    <td class="listHeader">Data Type</td>
	                    <td class="listHeader">Value</td>  
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
	$('#tabParameter').addClass('tabActive');
});

function showDetail(id){
	var param = id == 0 ? '' : 'model.pkAppParameter='+id;
	var sURL = '<s:url beanclass="${actionBean.beanClass}"/>?showDetail=';
	$.get(sURL,param,function(data){
		openDialog(440, 170, 1, 'Rubah Parameter', data, 1);
	});	
}

function populateTable(data){
	var tBody = '';
	$.each(data,function(i){
		var dataType = '';
		if (this.dataType == 1){
			dataType = 'Integer';
		} else if (this.dataType == 2){
			dataType = 'String';
		}
		tBody += 
   			'<tr>'+
   				'<td class="listItem" onclick="showDetail('+this.pkAppParameter+')">'+this.description+'</td>'+
   				'<td class="listItem" onclick="showDetail('+this.pkAppParameter+')">'+dataType+'</td>'+
   				'<td class="listItem" onclick="showDetail('+this.pkAppParameter+')">'+this.value+'</td>'+
   			'</tr>';
	});
	return tBody;
}
</script>
</s:layout-component>
</s:layout-render>