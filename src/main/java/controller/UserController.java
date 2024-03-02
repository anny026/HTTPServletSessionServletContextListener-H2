package controller;

import shop.model.bean.Good;
import shop.model.bean.User;
import shop.model.repository.DBConnectionUtil;
import shop.model.repository.GoodDao;
import shop.model.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserController extends HttpServlet {
    private Connection connection;
    RequestDispatcher dispatcher = null; //????***
    GoodDao goodDao= new GoodDao() ;//**
    User user = new User();
    UserService userService= new UserService();

    @Override
    public void init() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test;DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();//*
        String password1= req.getParameter("password");
        String name1= req.getParameter("name");
        session.setAttribute("password", req.getParameter("password"));
        session.setAttribute("name", req.getParameter("name"));//* не из БД, а из сессии
        if (req.getParameter("agree")!=null){
            //вывести из БД товар
            Map<Integer, Good> goods = goodDao.get();//**
            session.setAttribute("list", goods);//**
            userService.registration(name1, password1);
            req.getRequestDispatcher("page2.jsp")
                    .forward(req,resp);
        } else {
            req.getRequestDispatcher("pageOops.jsp")
                    .forward(req,resp);}
    }
    // НЕ ИСПОЛЬЗОВАЛА
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
          try (PreparedStatement st = this.connection.prepareStatement("SELECT * FROM users");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                User x = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"));
                System.out.println(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE users SET username = ? WHERE id = ?")) {
            st.setString(1, "new_name");
            st.setLong(2, 1L);
            System.out.println("UPDATE: rows updated - " + st.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            st.setLong(1, 3);
            System.out.println("DELETE: rows updated - " + st.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
