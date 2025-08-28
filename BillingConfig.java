package com.auebs.billing;

import java.math.BigDecimal;

/**
 * Configuration records for different billing strategies using Java records.
 */
public final class BillingConfig {
    
    public record ResidentialConfig(
        BigDecimal ratePerKwh,
        BigDecimal baseCharge
    ) {
        public ResidentialConfig {
            validateRate(ratePerKwh, "Rate per kWh");
            validateRate(baseCharge, "Base charge");
        }
    }
    
    public record CommercialConfig(
        BigDecimal ratePerKwh,
        BigDecimal baseCharge,
        BigDecimal demandCharge
    ) {
        public CommercialConfig {
            validateRate(ratePerKwh, "Rate per kWh");
            validateRate(baseCharge, "Base charge");
            validateRate(demandCharge, "Demand charge");
        }
    }
    
    public record IndustrialConfig(
        BigDecimal peakRatePerKwh,
        BigDecimal offPeakRatePerKwh,
        BigDecimal baseCharge,
        BigDecimal peakUsageRatio,
        BigDecimal offPeakUsageRatio
    ) {
        public IndustrialConfig {
            validateRate(peakRatePerKwh, "Peak rate per kWh");
            validateRate(offPeakRatePerKwh, "Off-peak rate per kWh");
            validateRate(baseCharge, "Base charge");
            validateRatio(peakUsageRatio, "Peak usage ratio");
            validateRatio(offPeakUsageRatio, "Off-peak usage ratio");
            
            // Ensure ratios add up to 1.0
            if (peakUsageRatio.add(offPeakUsageRatio).compareTo(BigDecimal.ONE) != 0) {
                throw new IllegalArgumentException("Peak and off-peak usage ratios must sum to 1.0");
            }
        }
    }
    
    private static void validateRate(BigDecimal rate, String fieldName) {
        if (rate == null || rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be null or negative");
        }
    }
    
    private static void validateRatio(BigDecimal ratio, String fieldName) {
        if (ratio == null || ratio.compareTo(BigDecimal.ZERO) < 0 || ratio.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException(fieldName + " must be between 0 and 1");
        }
    }
}