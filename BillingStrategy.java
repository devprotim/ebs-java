
package com.auebs.billing;

import java.math.BigDecimal;

public interface BillingStrategy {
    BigDecimal calculateBill(Account account);
}
