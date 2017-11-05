package com.example.controller;

import com.example.model.dao.UserDAO;
import com.example.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Set;

@Controller
public class AjaxControllers {
    @Autowired
    UserDAO userDao;
    @RequestMapping( value = "/PrintAllUserWithAjax" ,method = RequestMethod.GET)
    public String allUsers(Model model) {

        Set< User > users = userDao.selecttUsers();
        model.addAttribute("allUsers", users);
        System.out.println("users size "+users.size());
        return "ajaxJsp/PrintAllUserWithAjax";
    }

    @RequestMapping( value = "/printallusers" ,method = RequestMethod.GET)
    public String allUsersForAdmin(Model model) {
        Set< User > users = userDao.selecttUsers();
        model.addAttribute("allUsers", users);
        System.out.println("users size "+users.size());
        return "printallusers";
    }


    @RequestMapping( value = "/DelUserFromAdmin" ,method = RequestMethod.POST)
    public String deleteUserForAdmin(Model model , HttpServletRequest request) {
        String userId = request.getParameter("del_user_id");
        System.out.println("user id in ajax "+userId);
        long uId =  Long.parseLong(userId);
        try {
            userDao.delUser(uId);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
//        Set< User > users = userDao.selecttUsers();
//        model.addAttribute("allUsers", users);
//        System.out.println("users size "+users.size());

        return "printallusers";
    }

}
