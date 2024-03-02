package shop.model.service;

import shop.model.bean.User;
import shop.model.repository.UserDao;

public class UserService {
    UserDao userDao = new UserDao();

    public String login(User user) {
        User findUser = userDao.findByLogin(user.getUsername());
        if(findUser!=null){
        //    if(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()).equals(findUser.getPassword())){
                if(user.getPassword().equals(findUser.getPassword())){
                return "life is beautiful";
            }
        }
        return "do not give up";
    }
    public String registration(String name, String password) {
        User findUser = userDao.findByLogin(name);
        System.out.println("Регистрация");
        if(findUser==null) {
            userDao.save( name,  password);
            return "life is beautiful";
        } else {
        System.out.println("Логин существует");
        return "this login is not available";}
    }

}
