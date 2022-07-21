package edu.wtbu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "getTicketStatistics", value = "/getTicketStatistics")
public class GetTicketStatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String startDate = request.getParameter("startDate")+"-01 00:00:00";
        String endDate = request.getParameter("endDate");
        int year = Integer.parseInt(endDate.split("-")[0]);
        int month = Integer.parseInt(endDate.split("-")[1]);
        if(month<12){
            month++;
            endDate = year+"-"+month+"-01 00:00:00";
        }else if (month>=12){
            year++;
            endDate = year+"-01-01 00:00:00";
        }
        System.out.println(startDate+"  "+endDate);
        Result result = ScheduleService.getTicketStatistics(startDate,endDate);
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
