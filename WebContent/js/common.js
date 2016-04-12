function formatMoney(strParam){
	if (strParam == null){
		return 0;
	} else{
		var str = new String(strParam);
		var isValueMinus = false;
		if (str.indexOf('-') != -1){
			isValueMinus = true;
			str = str.replace('-', '');
		}
		var formatedStr = str;
		var count = 1;
		for (var i=str.length; i>0; i--){
			if (count%3==0 && count != str.length){
				var position = parseInt(str.length) - parseInt(count); 
				var output = [formatedStr.slice(0, position), ',', formatedStr.slice(position)].join('');
				formatedStr = output;
			}
			count++;
		} 
		return formatedStr = isValueMinus ? '-'+formatedStr : formatedStr;
	}
}

function formatMoneyKeyUp(el){
	var str = removeFormatMoney($(el).val());
	var formated = formatMoney(str);
	if (!isNaN(str)){
		$(el).val(formated);
	}
	
}

function removeFormatMoney(str){
	var field = str;
	var result = field[0] == '-' ? "" : 0;
    for(var f in field){
        if(!isNaN(field[f]) || field[f] == "-") result += field[f];
    }
    return parseInt(result);
}

Date.prototype.format = function(format){
	var o = {
		"M+" : this.getMonth()+1, 
		"d+" : this.getDate(),    
	    "h+" : this.getHours(),   //hour
	    "m+" : this.getMinutes(), //minute
	    "s+" : this.getSeconds(), //second
	    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	    "S" : this.getMilliseconds() //millisecond
	}

	if(/(y+)/.test(format)) 
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
  return format;
}