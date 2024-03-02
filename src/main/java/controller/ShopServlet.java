package controller;

import shop.model.bean.Good;
import shop.model.bean.Order;
import shop.model.bean.OrderGood;
import shop.model.bean.User;
import shop.model.repository.GoodDao;
import shop.model.repository.OrderDao;
import shop.model.repository.OrderGoodDao;
import shop.model.repository.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(asyncSupported = false, name = "ShopServlet", urlPatterns = { "/hello" })
public class ShopServlet extends HttpServlet {

 RequestDispatcher dispatcher = null; //***
 GoodDao goodDao= new GoodDao();
 OrderGoodDao orderGoodDao= new OrderGoodDao();
 UserDao userDao=new UserDao();
 OrderDao orderDao= new OrderDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String submit = request.getParameter("submitForm");
        if (submit == null){
            submit = "browse";
            System.out.println("browse");
        }
        if (submit.equalsIgnoreCase("addToCart")) {
            addItem(request, response);

            request.getRequestDispatcher("page3.jsp")
                    .forward(request, response);
                    }
        if (submit.equalsIgnoreCase("Submit")) {
            request.getRequestDispatcher("page4.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String name = (String) request.getParameter("name");
        session.setAttribute("name", name);
        if (request.getParameter("agree")!=null){
            request.getRequestDispatcher("page2.jsp")
                    .forward(request,response);}
        else {
            request.getRequestDispatcher("pageOops.jsp")
                    .forward(request,response);}
    }

    private void addItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer keyyyy = Integer.parseInt(req.getParameter("item"));
        Integer priceItem=goodDao.getPricebyKey(keyyyy);
        Long idItem=goodDao.getIdbyKey(keyyyy);
        User user=userDao.findByLogin((String) session.getAttribute("name"));
        Good e=goodDao.get((idItem));
        Order order=orderDao.get(user.getId());  //переделать по ID заказа!!?
        if (orderDao.get(user.getId()).getTotalPrice()==0){
            orderDao.save(user,priceItem); //добавляем только цену последнего
        } else orderDao.update(user,priceItem);//добавляем только цену последнего
        Order order_new=orderDao.get(user.getId());  //переделать по ID заказа!!?
        orderGoodDao.save(e,user, order_new, keyyyy);
        Map<Integer, OrderGood> goodDaoMap = orderGoodDao.get(order_new);
        session.setAttribute("list2", goodDaoMap);
        session.setAttribute("list4", order_new);
    }
}

