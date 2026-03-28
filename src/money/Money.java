package money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;
    private final String currency = "PLN";

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public Money add(Money other){
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other){
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    @Override
    public String toString() {
        return "Price " + amount + currency;
    }
}
