package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.Service.CurrencyService;
import com.example.ForeignExchangeSystem.Service.UserService;
import com.example.ForeignExchangeSystem.Service.WalletService;
import com.example.ForeignExchangeSystem.model.Wallet;
import com.example.ForeignExchangeSystem.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private WalletService walletService;

    @GetMapping("/showWallet")
    public String showWallet(Model theModel) throws IOException {
        basicSetup(theModel);
        return "/wallet";
    }

    @PostMapping("/addEntry")
    public String addEntry( @ModelAttribute("wallet") Wallet wallet, @RequestParam("currency") String currency,BindingResult bindingResult, Model theModel){

            boolean exists = checkIfExists(currency);

            String email = Utils.getLoggedUser();

            wallet.setWaluta(currency);

            if (exists) {
                walletService.update(addToWallet(wallet), email);
            } else {
                walletService.Save(wallet, email);
            }
            return "redirect:/wallet/showWallet";

    }

    @PostMapping("/subtract")
    public String subtract( @ModelAttribute("wallet1") Wallet wallet, @RequestParam("currency1") String currency, BindingResult bindingResult, Model theModel){


            boolean exists = checkIfExists(currency);

            String email = Utils.getLoggedUser();

            wallet.setWaluta(currency);

            if (exists) {
                wallet = subrtractFromWallet(wallet);

                if (wallet.getValue() <= 0) {
                    walletService.delete(wallet, email);
                } else {
                    walletService.update(wallet, email);
                }
            }

            return "redirect:/wallet/showWallet";
    }

    @GetMapping("/deleteEntry")
    public String deleteEntry(@RequestParam("walletId") Long id ){

        walletService.deleteById(id);

        return "redirect:/wallet/showWallet";
    }

    private boolean checkIfExists(String currency){
        String email = Utils.getLoggedUser();

        List<Wallet> wallets = walletService.getUserWallet(email);

        boolean result = false;

        for(int i=0; i<wallets.size(); i++){

            if(wallets.get(i).getWaluta().equals(currency)){
                result = true;
            }
        }
        return result;
    }

    private Wallet addToWallet(Wallet wallet){

        String email = Utils.getLoggedUser();

        List<Wallet> wallets = walletService.getUserWallet(email);

        for(int i=0; i<wallets.size(); i++){

            if(wallets.get(i).getWaluta().equals(wallet.getWaluta())){
                double newValue = wallet.getValue() + wallets.get(i).getValue();
                wallet.setValue(newValue);
            }
        }

        return wallet;
    }

    private Wallet subrtractFromWallet(Wallet wallet){
        String email = Utils.getLoggedUser();

        List<Wallet> wallets = walletService.getUserWallet(email);

        for(int i=0; i<wallets.size(); i++){

            if(wallets.get(i).getWaluta().equals(wallet.getWaluta())){
                double newValue =  wallets.get(i).getValue() - wallet.getValue() ;
                wallet.setValue(newValue);
            }
        }

        return wallet;
    }

    public void basicSetup(Model theModel){
        ArrayList<String> currencies = currencyService.getCurrencies();

        String email = Utils.getLoggedUser();

        List<Wallet> wallets = walletService.getUserWallet(email);

        Wallet wallet = new Wallet();
        Wallet wallet1 = new Wallet();

        theModel.addAttribute("walletEntries",wallets);
        theModel.addAttribute("wallet",wallet);
        theModel.addAttribute("wallet1",wallet1);
        theModel.addAttribute("currencies",currencies);
        theModel.addAttribute("currencies1",currencies);
        theModel.addAttribute("total",calculateTotal(wallets));
    }

    private double calculateTotal(List<Wallet> wallets){
        double total = 0;
        Map<String, Float> rates = currencyService.getRates();

        for(int i=0; i<wallets.size(); i++){

            total = total + wallets.get(i).getValue() *  rates.get(wallets.get(i).getWaluta());
        }

        total = Math.round(total * 100.0) / 100.0;

        return total;
    }

}
