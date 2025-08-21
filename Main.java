
package com.auebs.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create a list of accounts (N accounts)
        // In a real application, you would fetch this from a database.
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("ACC-001", Account.Role.RESIDENTIAL, new BigDecimal("250.5")));
        accounts.add(new Account("ACC-002", Account.Role.COMMERCIAL, new BigDecimal("1500.0")));
        accounts.add(new Account("ACC-003", Account.Role.INDUSTRIAL, new BigDecimal("50000.7")));
        accounts.add(new Account("ACC-004", Account.Role.RESIDENTIAL, new BigDecimal("150.2")));

        // 2. Create the processor
        BillingProcessor processor = new BillingProcessor();

        // 3. Process the bills
        System.out.println("--- Starting Bill Processing ---");
        processor.processBills(accounts);
        System.out.println("--- Bill Processing Finished ---");
    }
}
