package lotto.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    void 매칭_규칙_검증() {
        assertThat(Rank.of(6, false)).contains(Rank.FIRST);
        assertThat(Rank.of(5, true)).contains(Rank.SECOND);
        assertThat(Rank.of(5, false)).contains(Rank.THIRD);
        assertThat(Rank.of(4, false)).contains(Rank.FOURTH);
        assertThat(Rank.of(3, false)).contains(Rank.FIFTH);
        assertThat(Rank.of(2, false)).isEmpty();
    }
}