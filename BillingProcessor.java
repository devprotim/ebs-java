
package com.auebs.billing;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingProcessor {
    private final Map<Account.Role, BillingStrategy> strategies = new EnumMap<>(Account.Role.class);

    public BillingProcessor() {
        // Initialize all the strategies
        strategies.put(Account.Role.RESIDENTIAL, new ResidentialBillingStrategy());
        strategies.put(Account.Role.COMMERCIAL, new CommercialBillingStrategy());
        strategies.put(Account.Role.INDUSTRIAL, new IndustrialBillingStrategy());
    }

    public void processBills(List<Account> accounts) {
        List<BillingResult> results = calculateBills(accounts);
        results.forEach(result -> System.out.println(result.formatForDisplay()));
    }
    
    
    //  Calculate bills for all accounts and return results as records.
     
    public List<BillingResult> calculateBills(List<Account> accounts) {
        return accounts.stream()
            .map(this::calculateBill)
            .collect(Collectors.toList());
    }
    
    
    // Calculate bill for a single account and return as a BillingResult record.
    
    public BillingResult calculateBill(Account account) {
        BillingStrategy strategy = strategies.get(account.role());
        
        if (strategy == null) {
            throw new IllegalArgumentException("No billing strategy found for role: " + account.role());
        }
        
        BigDecimal billAmount = strategy.calculateBill(account);
        String details = String.format("%s billing strategy applied", 
            account.role().toString().toLowerCase());
        
        return BillingResult.from(account, billAmount, details);
    }
}
