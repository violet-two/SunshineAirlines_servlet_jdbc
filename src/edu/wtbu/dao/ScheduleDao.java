package edu.wtbu.dao;

import edu.wtbu.helper.Helper;

import java.util.HashMap;
import java.util.List;

public class ScheduleDao {
    public static List<HashMap<String, Object>> getScheduleByTime(String fromCity, String toCity, String startDate, String endDate) {
        String sql = "select\n" +
                "ScheduleId,\n" +
                "Date,\n" +
                "Time,\n" +
                "EconomyPrice,\n" +
                "FlightNumber,\n" +
                "Gate,\n" +
                "Status,\n" +
                "route.DepartureAirportIATA as DepartureAirportIATA,\n" +
                "DepartCity.CityName as DepartCityName,\n" +
                "route.ArrivalAirportIATA as ArrivalAirportIATA,\n" +
                "ArriveCity.CityName as ArriveCityName,\n" +
                "Name\n" +
                "from `schedule`\n" +
                "LEFT JOIN aircraft on `schedule`.ScheduleId = aircraft.AircraftId\n" +
                "LEFT JOIN route on `schedule`.RouteId = route.RouteId\n" +
                "LEFT JOIN airport as DepartureAirport on route.DepartureAirportIATA = DepartureAirport.IATACode\n" +
                "LEFT JOIN city as DepartCity on DepartureAirport.CityCode = DepartCity.CityCode\n" +
                "LEFT JOIN airport as ArrivalAirport on route.ArrivalAirportIATA = ArrivalAirport.IATACode\n" +
                "LEFT JOIN city as ArriveCity on ArrivalAirport.CityCode = ArriveCity.CityCode\n" +
                "where DepartCity.CityName = ? and ArriveCity.CityName = ? \n" +
                "and Date BETWEEN ? and ?\n";
        List<HashMap<String,Object>> list = Helper.executuQuery(sql,new Object[] {fromCity,toCity,startDate,endDate});
        return list;
    }

    public static int updateScheduleStatusById(int scheduleId, String status) {
        String sql = "UPDATE `schedule` set STATUS = ? where ScheduleId = ?";
        int rs = Helper.executuUpdata(sql,new Object[] {status,scheduleId});
        return rs;
    }

    public static List<HashMap<String, Object>> getTicketStatistics(String startDate, String endDate) {
        String sql = "select\n" +
                "Year(date) as Year,\n" +
                "MONTH(date) as Month,\n" +
                "COUNT(DISTINCT `schedule`.ScheduleId) as FlightsAmount,\n" +
                "COUNT(flightreservation.ReservationId) as TicketsAmount,\n" +
                "SUM(Payment) as TicketsRevenue\n" +
                "FROM\n" +
                "`schedule`\n" +
                "LEFT JOIN flightreservation ON flightreservation.ScheduleId = `schedule`.ScheduleId\n" +
                "where Date BETWEEN ? AND ? and status = 'Confirmed'\n" +
                "GROUP BY year(date),month(date)\n" +
                "ORDER BY year(date),month(date)\n";
        List<HashMap<String,Object>> list = Helper.executuQuery(sql,new Object[] {startDate,endDate});
        return  list;
    }
}
