var timerID = null;
var timerRunning = false;
var ms = 0;
var months=new Array(13);

months[1]="Jan";
months[2]="Feb";
months[3]="Mar";
months[4]="Apr";
months[5]="May";
months[6]="Jun";
months[7]="Jul";
months[8]="Aug";
months[9]="Sep";
months[10]="Oct";
months[11]="Nov";
months[12]="Dec";

function stopclock(){
  if(timerRunning)
    clearTimeout(timerID); 
    timerRunning=false;
}

function startclock(nowDate) {
   xnow = new Date(nowDate);
   stopclock();
   showtime();
}

function showtime() {
   xnow.setSeconds(xnow.getSeconds()+1);
   var lmonth=(((xnow.getMonth()+1) < 10) ? "0" : "") + (xnow.getMonth()+1);
   var date=((xnow.getDate() < 10) ? "0" : "") + xnow.getDate();
   var year=xnow.getYear();
   if (year<2000) year=year+1900
   var hour=((xnow.getHours() < 10) ? "0" : "") + xnow.getHours();
   var min=((xnow.getMinutes() < 10) ? "0" : "") + xnow.getMinutes();
   var sec=((xnow.getSeconds() < 10) ? "0" : "") + xnow.getSeconds();
   document.getElementById('clockbox').innerHTML = date + "-" + lmonth + "-" + year + "           " + hour + ":" + min + ":" + sec;
   timerID = setTimeout("showtime()",900);
   timerRunning = true;
}

function fncSetTimer() {
	ms = 0;
	then = new Date();
	then.setTime(then.getTime() - ms);
}

function fncTimer() {
	setTimeout("fncTimer();", 10000);
	ynow = new Date();
	ms = ynow.getTime() - then.getTime();
	if (ms>=480000) {
		fncSetTimer();
		fncLogoff();
	}
	window.status=parseInt(ms/1000) + " seconds idle";
}