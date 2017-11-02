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
import java.util.Set;

@Controller
public class WalletController {

    @Autowired
    WalletDAO walletDAO;

    @RequestMapping( value = {"/wallets", "/home/{parameter}"}, method = RequestMethod.GET )
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

//        long wallet_id = walletDAO.insertWallet(wallet);
//        System.out.println("Wallet id: " + wallet_id);
        return "redirect:home";
    }

}
