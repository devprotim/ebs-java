
package com.auebs.billing;

import java.math.BigDecimal;

public class IndustrialBillingStrategy implements BillingStrategy {
    private final BillingConfig.IndustrialConfig config;
    
    public IndustrialBillingStrategy() {
        this.config = new BillingConfig.IndustrialConfig(
            new BigDecimal("0.20"), // peak rate per kWh
            new BigDecimal("0.08"), // off-peak rate per kWh
            new BigDecimal("500.00"), // base charge
            new BigDecimal("0.4"),  // peak usage ratio (40%)
            new BigDecimal("0.6")   // off-peak usage ratio (60%)
        );
    }
    
    public IndustrialBillingStrategy(BillingConfig.IndustrialConfig config) {
        this.config = config;
    }

    @Override
    public BigDecimal calculateBill(Account account) {
        // Formula for industrial customers with peak/off-peak rates
        BigDecimal peakUsage = account.usage().multiply(config.peakUsageRatio());
        BigDecimal offPeakUsage = account.usage().multiply(config.offPeakUsageRatio());

        BigDecimal peakCharge = peakUsage.multiply(config.peakRatePerKwh());
        BigDecimal offPeakCharge = offPeakUsage.multiply(config.offPeakRatePerKwh());

        return peakCharge.add(offPeakCharge).add(config.baseCharge());
    }
}
