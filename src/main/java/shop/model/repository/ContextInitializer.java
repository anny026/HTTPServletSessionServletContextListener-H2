package shop.model.repository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            try (Connection connection = DriverManager
                    .getConnection("jdbc:h2:~/test;DB_CLOSE_DELAY=-1", "sa", ""))
            {

                try (PreparedStatement st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "username VARCHAR(255)," +
                        "password VARCHAR(255)" +
                       ")")) {
                    System.out.println("CREATE TABLE: rows updated - " + st.executeUpdate());
                }

                try (PreparedStatement st2 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS goods (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "title VARCHAR(255)," +
                        "price INTEGER" +
                        ")")) {
                    System.out.println("CREATE TABLE: rows updated - " + st2.executeUpdate());
                }

                try (PreparedStatement st3 = connection.prepareStatement("INSERT INTO goods (title,price) values " +
                        "('"+"coffee"+"', '"+5+"')"+";"+
                        "INSERT INTO goods (title,price) values"+"('"+"lemon"+"', '"+4+"')"+";"+
                        "INSERT INTO goods (title,price) values"+"('"+"tea"+"', '"+3+"')"+";"+
                        "INSERT INTO goods (title,price) values"+"('"+"milk"+"', '"+2+"')"+";"+
                        "INSERT INTO goods (title,price) values"+"('"+"cheese"+"', '"+6+"')")) {

                    System.out.println("Queries: " + st3.executeUpdate());
                }

                try (PreparedStatement st5 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS order_goods (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "order_id INTEGER," +
                        "good_id INTEGER" +
                        ")")) {
                    System.out.println("CREATE TABLE: rows updated - " + st5.executeUpdate());
                }

                try (PreparedStatement st7 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user_order (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "user_id INTEGER," +
                        "total_price INTEGER" +
                        ")")) {
                    System.out.println("CREATE TABLE: rows updated - " + st7.executeUpdate());
                }


                //Drop or delete table
                /*
                try (PreparedStatement st10 = connection.prepareStatement("Drop TABLE users ")) {
                    System.out.println("Drop TABLE: rows updated - " + st10.executeUpdate());
                }

                try (PreparedStatement st11 = connection.prepareStatement("Delete from order_goods ")) {
                    System.out.println("Drop TABLE: rows updated - " + st11.executeUpdate());
                }

                try (PreparedStatement st12 = connection.prepareStatement("Delete from user_order ")) {
                    System.out.println("Drop TABLE: rows updated - " + st12.executeUpdate());
                }
                try (PreparedStatement st13 = connection.prepareStatement("Delete from goods ")) {
                    System.out.println("Drop TABLE: rows updated - " + st13.executeUpdate());
                }
                try (PreparedStatement st14 = connection.prepareStatement("Delete from users ")) {
                    System.out.println("Drop TABLE: rows updated - " + st14.executeUpdate());
                }
                 */

           }  // can do it in resources    .sql
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
