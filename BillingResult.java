package com.auebs.billing;

import java.math.BigDecimal;

/**
 * Record representing the result of a billing calculation.
 * Provides immutable billing information with additional metadata.
 */
public record BillingResult(
    String accountNumber,
    Account.Role role,
    BigDecimal usage,
    BigDecimal billAmount,
    String calculationDetails
) {
    public BillingResult {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if (usage == null || usage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Usage cannot be null or negative");
        }
        if (billAmount == null || billAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bill amount cannot be null or negative");
        }
    }
    
    /**
     * Factory method to create a BillingResult from an Account and calculated amount.
     */
    public static BillingResult from(Account account, BigDecimal billAmount, String details) {
        return new BillingResult(
            account.accountNumber(),
            account.role(),
            account.usage(),
            billAmount,
            details != null ? details : "Standard calculation"
        );
    }
    
    /**
     * Formats the billing result for display.
     */
    public String formatForDisplay() {
        return String.format("Account: %s, Role: %s, Usage: %.2f kWh, Bill Amount: $%.2f (%s)",
            accountNumber, role, usage, billAmount, calculationDetails);
    }
}