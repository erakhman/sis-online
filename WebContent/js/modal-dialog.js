var numbersOfModal = 0;

function initializeDialog(numbers){
	if(!numbers){
		numbersOfModal = 1;
		numbers=1;
	}else{
		numbersOfModal = numbers;
	}
	for(i=1;i<=numbers;i++){
		$('<div id="dialog' + i + '" title="" ></div>').appendTo("body");
		$("#dialog"+i)
		.dialog({
			autoOpen: false,
			//title: 'Basic Dialog',
			modal: true,
			stack: true,
			/*zIndex: 1000,
			width: 600,
			height: 310,*/
			beforeClose: function(event, ui) {
				if(event.keyCode == 27) {
					if(typeof closeOnEscape != 'undefined'){
						//alert(jQuery.isFunction(closeOnEscape));
	                    if(jQuery.isFunction(closeOnEscape)){
	                    	closeOnEscape();
	                    }
					}
                }
			},
			close: function(event, ui) {
				if(event.keyCode == 27) {
					$("#dialog"+i).html("");
					if(typeof closeOnEscape != 'undefined'){
						//alert("true");
						closeOnEscape = null;
					}
				}
			},
			open: function(event, ui) {

			}
		});
	}
}

function openConfirmationDialog(message,number,callback){
	if(!number){
		for(i=1; i<=numbersOfModal; i++){
			if($("#dialog"+i).dialog("isOpen")==false){
				number = i;
				$("#dialogId").val("#dialog"+i);
				break;
			}
		}
	}
	if(numbersOfModal==0){
		alert("No dialog initialized..");
	}
	else if(number > numbersOfModal){
		alert("There are only " + numbersOfModal + " dialogs initialized..");
	}
	else{
		lastOpenedId = "#dialog" + number;
		$("#dialogId").val(lastOpenedId);
		var browserName=navigator.appName; 
		if (browserName=="Microsoft Internet Explorer"){
			$(lastOpenedId).dialog("option", "width", "300px");  
			$(lastOpenedId).dialog("option", "height","160px");
		}else{
			$(lastOpenedId).dialog("option", "width", "300");  
			$(lastOpenedId).dialog("option", "height","160");
		}
		$(lastOpenedId).dialog("option", "draggable",false);
		$(lastOpenedId).dialog("option", "bgiframe",true);
		$(lastOpenedId).dialog("option", "resizable",false);
		$(lastOpenedId).dialog("option", "zIndex", 99999);
		$(lastOpenedId).dialog("option", "title", "Confirmation");
		$(lastOpenedId).dialog("option", "buttons",{
			"Yes": function() {
				$( this ).dialog( "close" );
				eval(callback);
			},
			"No": function() {
				$( this ).dialog( "close" );
			}
		});
		$(lastOpenedId).dialog("option", "open", function() {
			$(this).parents('.ui-dialog-buttonpane button:eq(0)').blur();
			$(this).parents('.ui-dialog-buttonpane button:eq(1)').blur();
			//$(this).parents('.ui-dialog-buttonpane button:eq('+focusNum+')').focus();
		});
		$(lastOpenedId).html('<p>'+message+'</p>').dialog('open');
	}
}


function openDialog(width, height, zIndex, title, data, number){
	if(!number){
		for(i=1; i<=numbersOfModal; i++){
			if($("#dialog"+i).dialog("isOpen")==false){
				number = i;
				$("#dialogId").val("#dialog"+i);
				break;
			}
		}
	}
	if(numbersOfModal==0){
		alert("No dialog initialized..");
	}
	else if(number > numbersOfModal){
		alert("There are only " + numbersOfModal + " dialogs initialized..");
	}
	else{
		lastOpenedId = "#dialog" + number;
		$("#dialogId").val(lastOpenedId);
		var browserName=navigator.appName; 
		var browserVer=parseInt(navigator.appVersion); 
		if (browserName=="Microsoft Internet Explorer"){
			$(lastOpenedId).dialog("option", "width", width+"px");  
			$(lastOpenedId).dialog("option", "height",height+"px");
			$(lastOpenedId).dialog("option", "draggable",false);
			$(lastOpenedId).dialog("option", "bgiframe",true);
			$(lastOpenedId).dialog("option", "resizable",false);
			$(lastOpenedId).dialog("option", "buttons", {});
		}else {
			$(lastOpenedId).dialog("option", "width", "auto");  
			$(lastOpenedId).dialog("option", "height","auto"); 
			$(lastOpenedId).dialog("option", "buttons", {});
		}
		$(lastOpenedId).dialog("option", "zIndex", zIndex);
		$(lastOpenedId).dialog("option", "title", title);
		$(lastOpenedId).html(data).dialog('open');
	}
	
}

function closeDialog(number){
	if(!number){
		for(i=numbersOfModal; i>=1; i--){
			if($("#dialog"+i).dialog("isOpen")){
				$("#dialog"+i).dialog('close');
				// to be safe, "dispose" the previous content
				$("#dialog"+i).html("");
				break;
			}
		}
	}else{
		if(numbersOfModal==0){
			alert("No dialog initialized..");
		}
		else if(number > numbersOfModal){
			alert("There are only " + numbersOfModal + " dialogs initialized..");
		}
		else{
			$("#dialog"+number).dialog('close');
			// to be safe, "dispose" the previous content
			$("#dialog"+number).html("");
		}
	}
}

function fillDialogContent(data, number){
	if(!number){
		for(i=numbersOfModal; i>=1; i--){
			if($("#dialog"+i).dialog("isOpen")){
				number = i;
				break;
			}
		}
	}
	if(numbersOfModal==0){
		alert("No dialog initialized..");
	}
	else if(number > numbersOfModal){
		alert("There are only " + numbersOfModal + " dialogs initialized..");
	}
	else{
		$("#dialog"+number).html(data);
	}
}

function isDialogOpen(number){
	if(!number)
		number = 1;
	if(numbersOfModal==0){
		alert("No dialog initialized..");
	}
	else if(number > numbersOfModal){
		alert("There are only " + numbersOfModal + " dialogs initialized..");
	}
	else{
		return $("#dialog"+number).dialog("isOpen");
	}
}

function initAJAXLoader(base_url){
	$('<div id="loadingmsg" style="position: absolute; z-index: 5000; left: 40%; top: 40%; width: 200; height: 80; border-width: 1; border-style: ridge; background-color: #eeeeee"><div align="center"><br><br><font face="Arial" size="3"><b>Loading.....</b></font><br><img src="' + base_url + '/images/loading.gif"><br></div></div>')
	.appendTo("body");
	$('<div id="loadingOverlay" style="position: absolute; z-index: 4999; left: 0; top: 0; width: 100%; height: 100%; border-width: 1; border-style: ridge; background: #666666 url('+base_url+'/images/ui-bg_diagonals-thick_20_666666_40x40.png) 50% 50% repeat; opacity: .50;filter:Alpha(Opacity=50);"></div>')
	.appendTo("body");
	
	if (document.all) {
		document.all.loadingmsg.style.visibility='hidden';
		document.all.loadingOverlay.style.display='none';
	}
	if (document.layers) {
		document.loadingmsg.visibility='hidden';
		document.loadingOverlay.style.display='none';
	}
	if (document.getElementById) {
		document.getElementById('loadingmsg').style.visibility='hidden';
		document.getElementById('loadingOverlay').style.display='none';
	}
}

function startAJAXLoader(){
	$("#loadingOverlay").css('display', 'block');
	$("#loadingmsg").css('visibility', 'visible');
}

function stopAJAXLoader(){
	$("#loadingOverlay").css('display', 'none');
	$("#loadingmsg").css('visibility', 'hidden');
}

function displayError(data, number){	
	if(!number){
		for(i=numbersOfModal; i>=1; i--){
			if($("#dialog"+i).dialog("isOpen")){
				number = i;
				break;
			}
		}
	}
	if(!number || number==1){
		$("#errorFragmentList").removeClass();
		$("#errorFragmentList").addClass('errorFragmentList');
		$("#errorFragmentList").html(data);
	}
	else{
		$("#errorFragmentList"+number).removeClass();
		$("#errorFragmentList"+number).addClass('errorFragmentList');
		$("#errorFragmentList"+number).html(data);
	}
}

function getNumbersOfOpenedDialog(){
	var x = 0;
	for(i=numbersOfModal; i>=1; i--){
		if($("#dialog"+i).dialog("isOpen")){
			x++;
		}
	}
	return x;
}

/*
 * Returns last opened id. This method only valid if it is invoked within script 
 * in the currently opening dialog.
 * If it is called in the already opened dialog which might in turn opened another new dialog on top,
 * the last opened id will point the the latest dialog opened.
 *   
 * One thing to be considered: dialog close doesn't update last opened id
 * 
 */
function getLastOpenedId() {
	return lastOpenedId;
}
