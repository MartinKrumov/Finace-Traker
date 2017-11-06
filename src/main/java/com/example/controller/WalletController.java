package com.example.controller;

import com.example.model.dao.WalletDAO;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Set;

@Controller
public class WalletController {

    @Autowired
    WalletDAO walletDAO;

    @RequestMapping( value = "/wallets", method = RequestMethod.GET )
    public String getAllWallets(@ModelAttribute Wallet wallet, HttpSession session, Model model, @PathVariable( "parameter" ) String page) {
        model.addAttribute("wallet", wallet);
        System.out.println("in the wallets");
        System.err.println("Page parameter : " + page);
        User user = ( User ) session.getAttribute("user");
        if ( user != null ) {
            System.out.println(user.getUserId());
            Set< Wallet > wallets = walletDAO.selectUserWallets(user.getUserId());
            model.addAttribute("wallets", wallets);
        }
        return "wallets";
    }

    @RequestMapping( value = {"/home/{parameter}/wallets"}, method = RequestMethod.GET )
    public String getParam(@ModelAttribute Wallet wallet, HttpSession session, Model model, @PathVariable( "parameter" ) String page) {
        model.addAttribute("wallet", wallet);
        System.out.println("in the wallets");
        System.err.println("Page parameter : " + page);
        return "wallets";
    }

    @ModelAttribute
    @RequestMapping( value = "/walletInsert", method = RequestMethod.POST )
    public String InsertWallet(@ModelAttribute( "wallet" ) Wallet wallet, HttpSession session, Model model) {
        User user = ( User ) session.getAttribute("user");
        System.out.println(user.getUserId());
        System.out.println(wallet.getName());
        System.out.println(wallet.getAmount());
//        wallet.setUserId(user.getUserId());
//        Set<Wallet> wallets = walletDAO.selectUserWallets(user.getUserId());
//        model.addAttribute("wallets", wallets);
        long walletId = 0;
        try {
            walletId = walletDAO.insertWallet(wallet, user.getUserId());
            wallet.setWalletId(walletId);
//            if ( walletId > 0 ) {
//                Set< Wallet > newWallets = user.getWallets();
//                newWallets.add(wallet);
//                user.setWallets(newWallets);
//            }
            System.out.println("wallet id into controller " + walletId);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        System.out.println("Wallet id: " + walletId);
        return "redirect:home";
    }

}
