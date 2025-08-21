
package com.auebs.billing;

import java.math.BigDecimal;

public class CommercialBillingStrategy implements BillingStrategy {
    private static final BigDecimal RATE_PER_KWH = new BigDecimal("0.15");
    private static final BigDecimal BASE_CHARGE = new BigDecimal("50.00");
    private static final BigDecimal DEMAND_CHARGE = new BigDecimal("20.00"); // Extra charge for commercial

    @Override
    public BigDecimal calculateBill(Account account) {
        // Formula for commercial customers:
        // (usage * RATE_PER_KWH) + BASE_CHARGE + DEMAND_CHARGE
        BigDecimal usageCharge = account.getUsage().multiply(RATE_PER_KWH);
        return usageCharge.add(BASE_CHARGE).add(DEMAND_CHARGE);
    }
}
