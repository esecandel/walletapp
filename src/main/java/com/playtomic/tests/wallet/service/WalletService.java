package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.error.WalletException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletOperation;

public interface WalletService {

    Wallet getWalletById(String walletId);

    Wallet modifyWallet(WalletOperation walletOperation);

    Wallet thirdPartyChargeWallet(WalletOperation walletOperation) throws WalletException;

    Wallet save(Wallet wallet);
}
