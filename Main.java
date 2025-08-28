
package com.auebs.billing;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // list of accounts using records 
        List<Account> accounts = List.of(
            new Account("ACC-001", Account.Role.RESIDENTIAL, new BigDecimal("250.5")),
            new Account("ACC-002", Account.Role.COMMERCIAL, new BigDecimal("1500.0")),
            new Account("ACC-003", Account.Role.INDUSTRIAL, new BigDecimal("50000.7")),
            new Account("ACC-004", Account.Role.RESIDENTIAL, new BigDecimal("150.2"))
        );

        // the processor
        BillingProcessor processor = new BillingProcessor();

        // Process the bills
        System.out.println("--- Starting Bill Processing ---");
        processor.processBills(accounts);
        System.out.println("--- Bill Processing Finished ---");
        
        // Demonstrate additional functionality with records
        System.out.println("\n--- Billing Summary ---");
        List<BillingResult> results = processor.calculateBills(accounts);
        
        BigDecimal totalBilling = results.stream()
            .map(BillingResult::billAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
            
        System.out.printf("Total billing amount: $%.2f%n", totalBilling);
        System.out.printf("Number of accounts processed: %d%n", results.size());
    }
}
