package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.error.WalletException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.WalletOperation;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.PaymentService;
import com.playtomic.tests.wallet.error.PaymentServiceException;
import com.playtomic.tests.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PaymentService paymentService;

    /**
     * Recovery wallet entity from DB by its identifier
     * @param walletId identifier of wallet
     * @return wrecovered entity
     */
    @Override
    public Wallet getWalletById(String walletId) {
        return walletRepository.getById(walletId);
    }

    /**
     * Modify wallet entity and save it into DB
     * @param walletOperation operation on wallet
     * @return saved entity after operation
     */
    @Override
    public Wallet modifyWallet(WalletOperation walletOperation) {
        Wallet wallet = this.getWalletById(walletOperation.getWalletId());
        wallet.setAmount(wallet.getAmount().add(walletOperation.getAmount()));
        return this.save(wallet);
    }

    /**
     * Charge in third party service the amount of wallet operation, and save ir into wallet.
     * @param walletOperation  operation on wallet
     * @return saved entity after operation
     * @throws WalletException
     */
    @Override
    public Wallet thirdPartyChargeWallet(WalletOperation walletOperation) throws WalletException {

        Wallet wallet = this.getWalletById(walletOperation.getWalletId());
        try{
            paymentService.charge(walletOperation.getAmount());
            wallet.setAmount(wallet.getAmount().add(walletOperation.getAmount()));
            wallet = this.save(wallet);
        }catch (PaymentServiceException e){
            LOGGER.error("Error trying to operate with third party service: {}", e.getMessage());
            throw new WalletException(e);
        }
        return wallet;
    }

    /**
     * Save wallet
     * @param wallet entity to save
     * @return saved entity
     */
    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
