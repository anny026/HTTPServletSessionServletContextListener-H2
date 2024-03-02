package shop.model.repository;

import shop.model.bean.Good;
import shop.model.bean.Order;
import shop.model.bean.OrderGood;
import shop.model.bean.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class OrderGoodDao {

    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public boolean save(Good e, User user, Order order, Integer key) {   //  метод для корзины
        boolean flag = false;
        System.out.println("<<<<user    "+user+"  заказал товар "+e+ "kye   "+key);
        System.out.println(" заказ в save ordergoodDao  "+order);
        try {
            System.out.println(e);
            String sql = "INSERT INTO order_goods (order_id, good_id) VALUES"
                    + "('"+order.getId()+"', '"+e.getId()+"')";
            connection = DBConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
        }
        catch( SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    public Map<Integer, OrderGood> get(Order order) {  //выборка заказанных товаров
        Map<Integer,OrderGood> data = null;
        OrderGood orderGood = null;
        int i=0;
        try {
            data = new HashMap<Integer,OrderGood>();
            String sql = "SELECT * FROM order_goods WHERE order_id="+order.getId();
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("метод гет OrderGoodDao начало!!!!");
                orderGood = new OrderGood();
                orderGood.setId(resultSet.getLong("id"));
                orderGood.setOrderId(resultSet.getLong("order_id"));
                orderGood.setGoodId(resultSet.getLong("good_id"));
                data.put(i++,orderGood);
                System.out.println("метод гет OrderGoodDao  конец!!!!");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
