package shop.model.repository;


import shop.model.bean.Order;
import shop.model.bean.User;

import java.sql.*;

public class OrderDao {
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public boolean save(User e, Integer total_price) {   //  метод для корзины
        boolean flag = false;
        System.out.println("<<<сохранить заказ");
        System.out.println("user    "+e);
        System.out.println("total_price   "+total_price+">>>>>");
        try {
            String sql = "INSERT INTO user_order (user_id, total_price) VALUES"
                    + "('"+e.getId()+"', '"+total_price+"')";
            connection = DBConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
            System.out.println("сохранен заказ");
        }
        catch( SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

        public boolean update(User u, Integer total_price) {
        boolean flag = false;
            System.out.println("<<<<<заменить заказ");
            System.out.println("user    "+u);
            System.out.println("total_price   "+total_price+">>>>>>>>");
        try {
          String sql = "UPDATE user_order SET total_price =  total_price+'"+total_price+
                   "' where user_id="+u.getId();
            connection = DBConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
            System.out.println("заменен заказ");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Order get(Long id) {
        Order order = null;
        System.out.println("<<<<<<поиск заказа по id user  " +id);
        try {
            order = new Order();
            String sql = "SELECT * FROM user_order where user_id="+id;  // where user_id="+id
            connection = DBConnectionUtil.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setTotalPrice(resultSet.getFloat("total_price"));
                System.out.println("В гет order номер заказа по id юзера"+order.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println(order);
        return order;
    }
}
