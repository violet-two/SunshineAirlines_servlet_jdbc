package edu.wtbu.dao;

import edu.wtbu.helper.Helper;

import java.util.HashMap;
import java.util.List;

public class CityDao {
    public static List<HashMap<String, Object>> getCityName(){
        String sql = "select * from city";
        List<HashMap<String,Object>> list = Helper.executuQuery(sql,null);
        return list;
    }
}
