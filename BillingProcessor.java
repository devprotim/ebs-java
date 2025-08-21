
package com.auebs.billing;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BillingProcessor {
    private final Map<Account.Role, BillingStrategy> strategies = new EnumMap<>(Account.Role.class);

    public BillingProcessor() {
        // Initialize all the strategies
        strategies.put(Account.Role.RESIDENTIAL, new ResidentialBillingStrategy());
        strategies.put(Account.Role.COMMERCIAL, new CommercialBillingStrategy());
        strategies.put(Account.Role.INDUSTRIAL, new IndustrialBillingStrategy());
    }

    public void processBills(List<Account> accounts) {
        for (Account account : accounts) {
            BillingStrategy strategy = strategies.get(account.getRole());

            if (strategy == null) {
                System.err.println("No billing strategy found for role: " + account.getRole());
                continue;
            }

            BigDecimal billAmount = strategy.calculateBill(account);
            System.out.printf("Account: %s, Role: %s, Usage: %.2f, Bill Amount: $%.2f%n",
                    account.getAccountNumber(),
                    account.getRole(),
                    account.getUsage(),
                    billAmount);
        }
    }
}
