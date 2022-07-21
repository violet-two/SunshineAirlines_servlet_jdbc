package edu.wtbu.helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Helper {
    private static String url = "jdbc:mysql://localhost:3306/session1?serverTimezone=GMT%2B8&useOldAlasMetadataBehavior=true";
    private static String user = "root";
    private static String password = "123456";
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<HashMap<String,Object>> executuQuery(String sql,Object[] param){
        List<HashMap<String, Object>> list = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url,user,password);
            pstmt = conn.prepareStatement(sql);
            if(param!=null){
                for (int i = 0;i< param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()){
                HashMap<String,Object> map = new HashMap<>();
                for (int i = 0;i<rsmd.getColumnCount();i++){
                    map.put(rsmd.getColumnName(i+1),rs.getObject(i+1));
                }
                list.add(map);
            }
            closeALl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int executuUpdata(String sql,Object[] param){
        int result = 0;
        try {
            conn = DriverManager.getConnection(url,user,password);
            pstmt = conn.prepareStatement(sql);
            if(param!=null){
                for (int i = 0;i< param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            result = pstmt.executeUpdate();
            closeALl();
        }catch (Exception e){

        }
        return result;
    }

    public static void closeALl() throws Exception {
        if (conn != null) {
            conn.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
}

