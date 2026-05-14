package money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;
    private final String currency = "PLN";

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(String s) {
        return new Money(new BigDecimal(s));
    }

    public Money add(Money other){
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other){
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(BigDecimal other) {
        return new Money(this.amount.multiply(other));
    }

    public Money divide(BigDecimal other) {
        return new Money(this.amount.divide(other, 2, RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return "Price " + amount + currency;
    }
}
