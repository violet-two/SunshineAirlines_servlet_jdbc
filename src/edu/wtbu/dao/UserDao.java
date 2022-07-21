package edu.wtbu.dao;

import edu.wtbu.helper.Helper;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.UserService;

import java.util.HashMap;
import java.util.List;

public class UserDao {

    public static int addUser(String email, String password, String firstName, String lastName, String gender, String dateOfBirth, String phone, String photo, String address, String roleId) {
        String sql = "insert into users(email,password,firstName,lastName,gender,dateOfBirth,phone,photo,address,roleId)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        int rs = Helper.executuUpdata(sql,new Object[] {email,password,firstName,lastName,gender,dateOfBirth
                                                        ,phone,photo,address,roleId});
        return rs;
    }

    public static List<HashMap<String, Object>> findEmailIsTure(String email) {
        String sql = "select * from users where email = ?";
        List<HashMap<String,Object>> list = Helper.executuQuery(sql,new Object[] {email});
        return list;
    }
}
