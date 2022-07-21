package edu.wtbu.service;

import edu.wtbu.dao.ScheduleDao;
import edu.wtbu.pojo.Result;

import java.util.HashMap;
import java.util.List;

public class ScheduleService {
    public static Result getSchedule(String fromCity, String toCity, String startDate, String endDate) {
        Result result = new Result("fail",null);
        List<HashMap<String,Object>> list = ScheduleDao.getScheduleByTime(fromCity,toCity,startDate,endDate);
        if(list!=null&&list.size()>0){
            result.setFlag("success");
            result.setData(list);
        }
        return result;
    }

    public static Result updateScheduleStatusById(int scheduleId, String status) {
        Result result = new Result("fail",null);
        int rs = ScheduleDao.updateScheduleStatusById(scheduleId,status);
        if(rs>0){
            result.setFlag("success");
        }
        return result;
    }

    public static Result getTicketStatistics(String startDate, String endDate) {
        Result result = new Result("fail",null);
        List<HashMap<String,Object>> list = ScheduleDao.getTicketStatistics(startDate,endDate);
        if(list!=null&&list.size()>0){
            for (HashMap hashMap:list) {
                String Month = "";
                Integer month = Integer.parseInt(hashMap.get("Month").toString());
                String year = hashMap.get("Year").toString();
                Month = year+"-"+(month<10?"0"+month:month);
                hashMap.remove("Year");
                hashMap.put("Month",Month);
            }
            result.setFlag("success");
            result.setData(list);
        }
        return result;
    }
}
