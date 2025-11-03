package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final long amount;

    public Money(long amount) {
        if (amount < LOTTO_PRICE || amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위의 양수여야 합니다.");
        }
        this.amount = amount;
    }

    public long amount() {
        return amount;
    }

    public int countOfTickets() {
        return (int) (amount / LOTTO_PRICE);
    }

    public static BigDecimal calculateRate(long totalPrize, long paid) {
        if (paid == 0) return BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.valueOf(totalPrize).divide(BigDecimal.valueOf(paid), 3, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100)).setScale(1, RoundingMode.HALF_UP);
        return rate;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
