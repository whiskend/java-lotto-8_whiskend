package lotto.domain;

import java.util.Optional;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L);

    private final int match;
    private final boolean requiresBonus;
    private final long prize;

    Rank(int match, boolean requiresBonus, long prize) {
        this.match = match;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public long prize() { return prize; }

    public static Optional<Rank> of(int matchCount, boolean bonusMatched) {
        if(matchCount == 6) return Optional.of(FIRST);
        if(matchCount == 5 && bonusMatched) return Optional.of(SECOND);
        if(matchCount == 5) return Optional.of(THIRD);
        if(matchCount == 4) return Optional.of(FOURTH);
        if(matchCount == 3) return Optional.of(FIFTH);
        return Optional.empty();
    }
}
