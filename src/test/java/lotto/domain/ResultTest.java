package lotto.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.*;

class ResultTest {

    @Test
    void 등수별_집계와_총합_검증() {
        Result result = new Result();
        result.record(Rank.FIFTH);
        result.record(Rank.FIFTH);
        result.record(Rank.THIRD);

        assertThat(result.count(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.count(Rank.THIRD)).isEqualTo(1);
        assertThat(result.totalPrize()).isEqualTo(5_000 * 2 + 1_500_000);
    }

    @Test
    void 수익률_계산_소수점_반올림() {
        Result result = new Result();
        result.record(Rank.FIFTH); // 5,000원
        BigDecimal rate = result.rate(10_000); // 5,000 / 10,000 * 100 = 50.0%
        assertThat(rate).isEqualTo(BigDecimal.valueOf(50.0).setScale(1));
    }
}