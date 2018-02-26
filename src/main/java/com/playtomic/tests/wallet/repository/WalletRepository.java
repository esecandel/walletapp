package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.model.Wallet;

public interface WalletRepository {

    Wallet getById(String id);
    Wallet save(Wallet wallet);
}
