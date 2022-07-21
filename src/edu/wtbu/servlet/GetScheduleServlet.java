package edu.wtbu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "getSchedule", value = "/getSchedule")
public class GetScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String fromCity = request.getParameter("fromCity");
        String toCity = request.getParameter("toCity");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        startDate = startDate+" 00:00:00";
        endDate = endDate + " 23:59:59";
        Result result = ScheduleService.getSchedule(fromCity,toCity,startDate,endDate);
        response.getWriter().append(JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
