package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    void 잘못된_날짜_입력_시_예외_발생(int date) {
        Assertions.assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 디데이_이벤트_확인_테스트() {
        int date = 26;

        Assertions.assertThat(new VisitDate(date).isDDayEvent())
                .isEqualTo(false);
    }

    @Test
    void 평일_이벤트_확인_테스트() {
        int date = 9;

        Assertions.assertThat(new VisitDate(date).isWeekDayEvent())
                .isEqualTo(false);
    }

    @Test
    void 주말_이벤트_확인_테스트() {
        int date = 11;

        Assertions.assertThat(new VisitDate(date).isWeekEndEvent())
                .isEqualTo(false);
    }

    @Test
    void 특별_이벤트_확인_테스트() {
        int date = 28;

        Assertions.assertThat(new VisitDate(date).isSpecialEvent())
                .isEqualTo(false);
    }
}
