
package com.auebs.billing;

import java.math.BigDecimal;

public record Account(
    String accountNumber,
    Role role,
    BigDecimal usage 
) {
    public enum Role {
        RESIDENTIAL,
        COMMERCIAL,
        INDUSTRIAL
    }
    
    // Custom constructor with validation
    public Account {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if (usage == null || usage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Usage cannot be null or negative");
        }
    }
}
