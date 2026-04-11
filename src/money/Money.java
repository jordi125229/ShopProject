package money;

import exceptions.MoneyCantBeNegative;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal amount;
    private final String currency = "PLN";

    public Money(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new MoneyCantBeNegative("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    @Override
    public String toString() {
        return "Price " + amount + currency;
    }
}
