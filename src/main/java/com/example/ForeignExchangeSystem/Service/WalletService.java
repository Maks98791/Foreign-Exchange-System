package com.example.ForeignExchangeSystem.Service;

import com.example.ForeignExchangeSystem.model.Wallet;

import java.util.List;

public interface WalletService {

    public List<Wallet>  getUserWallet(String email);

    public void Save(Wallet wallet,String email);

    public void update(Wallet wallet,String email);

    public void delete(Wallet wallet ,String email );

    public void deleteById(Long id);

}
