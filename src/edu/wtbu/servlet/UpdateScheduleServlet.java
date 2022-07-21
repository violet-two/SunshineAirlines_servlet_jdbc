package edu.wtbu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updateSchedule", value = "/updateSchedule")
public class UpdateScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int scheduleId = 0;
        try{
            scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        }catch (Exception e){
            }
        String status = request.getParameter("status");
        Result result = ScheduleService.updateScheduleStatusById(scheduleId,status);
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
