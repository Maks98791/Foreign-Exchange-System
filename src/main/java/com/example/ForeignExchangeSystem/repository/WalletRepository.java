package com.example.ForeignExchangeSystem.repository;

import com.example.ForeignExchangeSystem.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("walletRepository")
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
