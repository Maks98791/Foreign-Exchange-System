package com.example.ForeignExchangeSystem.Service;

import com.example.ForeignExchangeSystem.model.User;
import com.example.ForeignExchangeSystem.model.Wallet;
import com.example.ForeignExchangeSystem.repository.UserRepository;
import com.example.ForeignExchangeSystem.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Wallet> getUserWallet(String email) {

        User user = userRepository.findByEmail(email);
        List<Wallet> wallets = walletRepository.getUserWallets(user.getId());
        return wallets;


    }

    @Override
    public void Save(Wallet wallet,String email) {
        User user = userRepository.findByEmail(email);
        wallet.setUser(user);
        walletRepository.save(wallet);
    }

    @Override
    public void update(Wallet wallet, String email) {
        User user = userRepository.findByEmail(email);
        String currency = wallet.getWaluta();
        Wallet dbWallet =  walletRepository.getUserWallet(user.getId(),currency);
        dbWallet.setValue(wallet.getValue());
        walletRepository.save(dbWallet);
    }

    @Override
    public void delete(Wallet wallet,String email ) {
        User user = userRepository.findByEmail(email);
        String currency = wallet.getWaluta();
        Wallet dbWallet =  walletRepository.getUserWallet(user.getId(),currency);
        walletRepository.delete(dbWallet);

    }

    @Override
    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }


}
