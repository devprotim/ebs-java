
package com.auebs.billing;

import java.math.BigDecimal;

public class Account {
    private final String accountNumber;
    private final Role role;
    private final BigDecimal usage; // Example data, like kWh used

    public enum Role {
        RESIDENTIAL,
        COMMERCIAL,
        INDUSTRIAL
    }

    public Account(String accountNumber, Role role, BigDecimal usage) {
        this.accountNumber = accountNumber;
        this.role = role;
        this.usage = usage;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Role getRole() {
        return role;
    }

    public BigDecimal getUsage() {
        return usage;
    }
}
