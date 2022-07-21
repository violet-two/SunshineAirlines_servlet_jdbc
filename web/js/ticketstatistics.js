$(document).ready(function (){

    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth()+1;
    month = month<10?"0"+month:month
    var maxTime = year + "-" + month;
    $(".startDate").prop("max",maxTime)
    $(".endDate").prop("max",maxTime);

    $(".endDate").change(function (){
        var startDate = $(".startDate").val();
        var endDate = $(".endDate").val()
        if(new Date(startDate + "-01 00:00:00")>new Date(endDate + "-01 00:00:00")){
            alert("开始时间必须小于结束时间")
            $(".endDate").val("");
            return;
        }
    })

    $(".stat").click(function (){
        var startDate = $(".startDate").val();
        var endDate = $(".endDate").val();
        var param = "startDate="+startDate+"&endDate="+endDate;
        $.ajax({
            url:'http://localhost:8080/SunshineAirlines/getTicketStatistics',
            type:'post',
            data:param,
            success:function (msg){
                var json = JSON.parse(msg)
                if(json.flag=="success"){
                    var html = "";
                    var ticketstatisticsMsg = json.data
                    for (var i = 0;i<ticketstatisticsMsg.length;i++){
                        html += "<tr class='tdcolor'>"+
                        "<td>"+ticketstatisticsMsg[i].Month+"</td>"+
                        "<td>"+ticketstatisticsMsg[i].FlightsAmount+"</td>"+
                        "<td>"+ticketstatisticsMsg[i].TicketsAmount+"</td>"+
                        "<td>"+ticketstatisticsMsg[i].TicketsRevenue+"</td>"+
                        "</tr>"
                    }
                    $(".formclass tbody").html(html);
                    $(".formclass tbody tr:odd").addClass("tdcolor1");
                }
            }
        })
    })
})