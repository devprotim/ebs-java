
package com.auebs.billing;

import java.math.BigDecimal;

public class ResidentialBillingStrategy implements BillingStrategy {
    private static final BigDecimal RATE_PER_KWH = new BigDecimal("0.12");
    private static final BigDecimal BASE_CHARGE = new BigDecimal("5.00");

    @Override
    public BigDecimal calculateBill(Account account) {
        // Formula for residential customers:
        // (usage * RATE_PER_KWH) + BASE_CHARGE
        BigDecimal usageCharge = account.getUsage().multiply(RATE_PER_KWH);
        return usageCharge.add(BASE_CHARGE);
    }
}
