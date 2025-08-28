
package com.auebs.billing;

import java.math.BigDecimal;

public class CommercialBillingStrategy implements BillingStrategy {
    private final BillingConfig.CommercialConfig config;
    
    public CommercialBillingStrategy() {
        this.config = new BillingConfig.CommercialConfig(
            new BigDecimal("0.15"), // rate per kWh
            new BigDecimal("50.00"), // base charge
            new BigDecimal("20.00")  // demand charge
        );
    }
    
    public CommercialBillingStrategy(BillingConfig.CommercialConfig config) {
        this.config = config;
    }

    @Override
    public BigDecimal calculateBill(Account account) {
        // Formula for commercial customers:
        // (usage * ratePerKwh) + baseCharge + demandCharge
        BigDecimal usageCharge = account.usage().multiply(config.ratePerKwh());
        return usageCharge.add(config.baseCharge()).add(config.demandCharge());
    }
}
