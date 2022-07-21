package edu.wtbu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wtbu.dao.UserDao;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "addUser", value = "/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HashMap<String,Object> map = new HashMap<>();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phone");
        String photo = request.getParameter("photo");
        String address = request.getParameter("address");
        String roleId = request.getParameter("roleId");
        String password = email.split("@")[0];
        if(password.length()>6){
            password = password.substring(0,6);
        }
        Result result = UserService.addUser(email,password,firstName,lastName,gender,
                                        dateOfBirth,phone,photo,address,roleId);
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
