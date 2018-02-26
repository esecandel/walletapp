package com.playtomic.tests.wallet.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class Wallet {

    @Id
    private String id;
    private BigDecimal amount;
    private String description;

    public Wallet() {
    }

    public Wallet(BigDecimal amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wallet)) return false;

        Wallet wallet = (Wallet) o;

        return amount != null ? amount.equals(wallet.amount) : wallet.amount == null;
    }

    @Override
    public int hashCode() {
        return amount != null ? amount.hashCode() : 0;
    }
}
