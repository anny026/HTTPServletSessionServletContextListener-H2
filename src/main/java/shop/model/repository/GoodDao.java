package shop.model.repository;

import shop.model.bean.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodDao {

    Connection connection = null;
     ResultSet resultSet = null;
     Statement statement = null;
    PreparedStatement preparedStatement = null;

    public  Map<Integer,Good> get() {
        Good good = null;
        int i=0;
        try {
            data = new HashMap<Integer,Good>();
            String sql = "SELECT * FROM goods";
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                good = new Good();
                good.setId(resultSet.getLong("id"));
                good.setTitle(resultSet.getString("title"));
                good.setPrice(resultSet.getFloat("price"));
                data.put(i++,good);
                System.out.println("метод гет!!!!");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static Map<Integer,Good> data;
    public Map<Integer,Good> getData()   {
        return data;
    }
    public void setData(Map<Integer,Good> data)   {        GoodDao.data = data;    }
    public Good getProductbyID(int id){
        return data.get(id);
    }
    public Good getProductbyKey (Integer key){
        return data.get(key);
    }
    public int getPricebyKey(Integer key){
        return (int) data.get(key).getPrice();
    }
    public Long getIdbyKey(Integer key) {
        return data.get(key).getId();
    }

    public Good get(Long id) {
        Good good = null;
        try {
            good = new Good();
            String sql = "SELECT * FROM goods where id="+id;
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                good.setId(resultSet.getLong("id"));
                good.setTitle(resultSet.getString("title"));
                good.setPrice(resultSet.getFloat("price"));

            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return good;
    }
}
