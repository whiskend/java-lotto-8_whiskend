package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @Test
    void 번호가_6개가_아니면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위를_벗어나면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(0,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복이면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(1,1,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 내부_정렬_보장() {
        Lotto lotto = new Lotto(List.of(6,5,4,3,2,1));
        assertThat(lotto.getNumbers()).containsExactly(1,2,3,4,5,6);
    }
}