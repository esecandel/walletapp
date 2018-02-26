package com.playtomic.tests.wallet.error;

public class WalletException extends Exception {

    public WalletException(String message) {
        super(message);
    }

    public WalletException(Throwable cause) {
        super(cause);
    }
}
