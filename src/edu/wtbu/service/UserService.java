package edu.wtbu.service;

import edu.wtbu.dao.UserDao;
import edu.wtbu.pojo.Result;

import java.util.HashMap;
import java.util.List;

public class UserService {
    public static Result addUser(String email, String password, String firstName, String lastName, String gender,
                                 String dateOfBirth, String phone, String photo, String address, String roleId) {
        Result result = new Result("fail",null);
        if(findEamilIsTure(email)){
            result.setData("邮箱已存在");
            return result;
        }
        int rs = UserDao.addUser(email,password,firstName,lastName,gender,
                dateOfBirth,phone,photo,address,roleId);
        if(rs>0){
            result.setFlag("success");
        }
        return result;
    }

    private static boolean findEamilIsTure(String email) {
        List<HashMap<String,Object>> list = UserDao.findEmailIsTure(email);
        if(list!=null&&list.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
