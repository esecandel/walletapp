package com.playtomic.tests.wallet.model;

import java.math.BigDecimal;

public class WalletOperation {

    private String walletId;
    private BigDecimal amount;

    public WalletOperation() {
    }

    public WalletOperation(BigDecimal amount) {
        this.amount = amount;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WalletOperation{" +
                "walletId='" + walletId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
