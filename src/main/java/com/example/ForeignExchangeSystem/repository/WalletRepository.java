package com.example.ForeignExchangeSystem.repository;

import com.example.ForeignExchangeSystem.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("walletRepository")
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query(value="SELECT * FROM wallet WHERE user_id=:userId", nativeQuery = true)
    public List<Wallet> getUserWallets(@Param("userId") long userId);

    @Query(value="SELECT * FROM wallet WHERE user_id=:userId AND waluta=:currency", nativeQuery = true)
    public Wallet getUserWallet(@Param("userId") long userId, @Param("currency") String currency);
}
