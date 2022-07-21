$(document).ready(function (){
    getCityName()

    $(".changeicon").click(function (){
        var fromCity = $(".fromCity").val()
        var toCity = $(".toCity").val()
        $(".fromCity").val(toCity)
        $(".fromCity").val(fromCity)
    })

    $(".searchBtn").click(function (){
        getSchedule();
    })

})
function getSchedule(){
    var fromCity = $(".fromCity").val()
    var toCity = $(".toCity").val()
    var startDate = $(".startDate").val();
    var endDate = $(".endDate").val();
    var param = "fromCity="+fromCity+"&toCity="+toCity+"&startDate="+startDate+"&endDate="+endDate
    $.ajax({
        url:'http://localhost:8080/SunshineAirlines/getSchedule',
        type:'post',
        data:param,
        success:function (msg){
            var json = JSON.parse(msg)
            if(json.flag=="success"){
                var scheduleMsg = json.data;
                var html = "";
                for (var i= 0 ; i<scheduleMsg.length;i++){
                    var statusNum = 0;
                    var btnStatus = "cancel";
                    if(statusNum==1){
                        btnStatus = "Confirm";
                    }
                    html = "<tr class='tdcolor'>"+
                    "<td>"+scheduleMsg[i].Date.substring(0,10)+"</td>"+
                    "<td>"+scheduleMsg[i].Time.substring(0,5)+"</td>"+
                    "<td>"+scheduleMsg[i].DepartCityName+"/"+scheduleMsg[i].DepartureAirportIATA+"</td>"+
                    "<td>"+scheduleMsg[i].ArriveCityName+"/"+scheduleMsg[i].ArrivalAirportIATA+"</td>"+
                    "<td>"+scheduleMsg[i].Name+"</td>"+
                    "<td>"+scheduleMsg[i].EconomyPrice+"</td>"+
                    "<td>"+scheduleMsg[i].FlightNumber+"</td>"+
                    "<td>"+scheduleMsg[i].Gate+"</td>"+
                    "<td>"+scheduleMsg[i].Status+"</td>"+
                    "<td><input type='button' value='"+btnStatus+"' onclick='updateSchedule("+scheduleMsg[i].ScheduleId+"+","+"+statusNum+")'/></td>"+
                    "</tr>"
                }
            }
        }
    })
}
function updateSchedule(btnStatus,statusNum){

}

function getCityName(){
    $.ajax({
        url:'http://localhost:8080/SunshineAirlines/getCityNames',
        type:'post',
        data:'',
        success:function (msg){
            var json = JSON.parse(msg);
            if(json.flag=="success"){
                var html = ""
                var cityMsg = json.data
                for (var i = 0 ;i<cityMsg.length;i++){
                    html += "<option>"+cityMsg[i].CityName+"</option>"
                }
            }
            $(".fromCity").html(html);
            $(".toCity").html(html);
        }
    })
}