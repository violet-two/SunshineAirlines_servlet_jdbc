package edu.wtbu.service;

import edu.wtbu.dao.CityDao;
import edu.wtbu.pojo.Result;

import java.util.HashMap;
import java.util.List;

public class CityService {
    public static Result getCityName() {
        Result result = new Result("fail", null);
        List<HashMap<String,Object>> list = CityDao.getCityName();
        if(list!=null&&list.size()>0){
            result.setFlag("success");
            result.setData(list);
        }
        return result;
    }
}
