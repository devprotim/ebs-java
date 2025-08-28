
package com.auebs.billing;

import java.math.BigDecimal;

public class ResidentialBillingStrategy implements BillingStrategy {
    private final BillingConfig.ResidentialConfig config;
    
    public ResidentialBillingStrategy() {
        this.config = new BillingConfig.ResidentialConfig(
            new BigDecimal("0.12"), // rate per kWh
            new BigDecimal("5.00")   // base charge
        );
    }
    
    public ResidentialBillingStrategy(BillingConfig.ResidentialConfig config) {
        this.config = config;
    }

    @Override
    public BigDecimal calculateBill(Account account) {
        // Formula for residential customers:
        // (usage * ratePerKwh) + baseCharge
        BigDecimal usageCharge = account.usage().multiply(config.ratePerKwh());
        return usageCharge.add(config.baseCharge());
    }
}
