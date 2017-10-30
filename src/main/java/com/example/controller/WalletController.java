package com.example.controller;

import com.example.model.dao.WalletDAO;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class WalletController {

    @Autowired
    WalletDAO walletDAO;

    @RequestMapping( value = "/wallets", method = RequestMethod.GET )
    public String getAllWallets(@ModelAttribute Wallet wallet,HttpSession session, Model model) {
        model.addAttribute("wallet", wallet);
        User user = ( User ) session.getAttribute("user");
        System.out.println(user.getUserId());
        Set< Wallet > wallets = walletDAO.selectUserWallets(user.getUserId());

        model.addAttribute("wallets", wallets);

        return "wallets";
    }


    @RequestMapping( value = "/wallet_insert", method = RequestMethod.POST )
    public String InsertWallet(@ModelAttribute Wallet wallet, HttpSession session, Model model) {
        model.addAttribute("wallet", wallet);
        User user = ( User ) session.getAttribute("user");

        System.out.println(user.getUserId());
        wallet.setUserId(user.getUserId());
//        Set<Wallet> wallets = walletDAO.selectUserWallets(user.getUserId());
//        model.addAttribute("wallets", wallets);

        long wallet_id = walletDAO.insertWallet(wallet);
        System.out.println("Wallet id: " + wallet_id);
        return "redirect:wallets";
    }

}
