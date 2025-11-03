package lotto.domain;

import java.math.BigDecimal;
import java.util.*;

public class Result {
    private final Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
    private long totalPrize = 0;
    public Result() {
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
    }

    public void record (Rank rank) {
        if (rank == null) return;
        rankCount.put(rank, rankCount.get(rank) + 1);
        totalPrize += rank.prize();
    }

    public  int count(Rank rank) {
        return rankCount.getOrDefault(rank, 0);
    }

    public  long totalPrize() {
        return totalPrize;
    }

    public  BigDecimal rate (long paid) {
        return Money.calculateRate(totalPrize, paid);
    }

    public Map<Rank, Integer> summary() {
        return  Collections.unmodifiableMap(rankCount);
    }
}
