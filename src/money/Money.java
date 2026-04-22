package money;

import exceptions.MoneyCantBeNegative;
import lombok.Getter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Money {
    @Getter
    private final BigDecimal amount;
    private final String currency = "PLN";

    public Money(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new MoneyCantBeNegative("Amount cannot be negative");
        }
        this.amount = amount;
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

    public Money multiply(BigDecimal rabat) {
        return new Money(this.amount.multiply(rabat));
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY); // tu LLMem robilem to
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        return "Price " + decimalFormat.format(amount) + currency;
    }
}
