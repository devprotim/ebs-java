
package com.auebs.billing;

import java.math.BigDecimal;

public class IndustrialBillingStrategy implements BillingStrategy {
    private static final BigDecimal PEAK_RATE_PER_KWH = new BigDecimal("0.20");
    private static final BigDecimal OFF_PEAK_RATE_PER_KWH = new BigDecimal("0.08");
    private static final BigDecimal BASE_CHARGE = new BigDecimal("500.00");

    @Override
    public BigDecimal calculateBill(Account account) {
        // Formula for industrial customers might be more complex
        // e.g. different rates for peak and off-peak hours.
        // This is a simplified example.
        // Assuming 40% of usage is peak, 60% is off-peak.
        BigDecimal peakUsage = account.getUsage().multiply(new BigDecimal("0.4"));
        BigDecimal offPeakUsage = account.getUsage().multiply(new BigDecimal("0.6"));

        BigDecimal peakCharge = peakUsage.multiply(PEAK_RATE_PER_KWH);
        BigDecimal offPeakCharge = offPeakUsage.multiply(OFF_PEAK_RATE_PER_KWH);

        return peakCharge.add(offPeakCharge).add(BASE_CHARGE);
    }
}
