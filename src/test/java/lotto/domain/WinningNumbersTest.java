package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @Test
    void 메인_6개_아니면_예외() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5), 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_범위_벗어나면_예외() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,6), 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스가_메인과_중복이면_예외() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1,2,3,4,5,6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}