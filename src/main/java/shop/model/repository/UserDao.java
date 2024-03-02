package shop.model.repository;
import shop.model.bean.User;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnectionFactory;
import java.sql.*;

public class UserDao {
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public User findByLogin(String login) {
        User user = null;
        System.out.println("Ищем user по логину  " + login);
        try {
        connection = DBConnectionUtil.openConnection();
        statement = connection.createStatement();
        System.out.println("Ищем user по логину внутри try");
                if (connection != null) {
                PreparedStatement pr = connection.prepareStatement("SELECT * FROM users where username=?");
                pr.setString(1, login);
                resultSet = pr.executeQuery();//return sql result
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
            }catch(SQLException e) {
                e.printStackTrace();
            }
            return user;
}

    public Boolean save(String name, String password) {
        connection = DBConnectionUtil.openConnection();
        System.out.println("Сохранение");
                try {
                PreparedStatement pr = connection.prepareStatement("insert into users " +
                        "(username,password) values"
                        + "('"+name+"', '"+password+"')");
        //  pr.setString(1,user.getUsername());  не работает с id auto_increment
        //  pr.setString(2,user.getPassword());
                 pr.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
         return false;
    }
}
