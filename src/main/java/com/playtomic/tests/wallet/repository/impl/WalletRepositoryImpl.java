package com.playtomic.tests.wallet.repository.impl;

import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    @Override
    public Wallet getById(String id) {
        return new Wallet(BigDecimal.ZERO);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return wallet;
    }
}
