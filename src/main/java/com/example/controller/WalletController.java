package com.example.controller;

import com.example.model.dao.WalletDAO;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class WalletController {

    @Autowired
    WalletDAO walletDAO;

    @RequestMapping(value = "/wallets", method = RequestMethod.GET)
    public String getAllWallets(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Set<Wallet> wallets = walletDAO.selectUserWallets(user.getUserId());
        model.addAttribute("wallets", wallets);

        return "wallets";
    }
}
