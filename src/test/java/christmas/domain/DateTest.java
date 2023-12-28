package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

    @DisplayName("유효하지 않는 날짜을 입력하면 예외를 발생한다.")
    @ValueSource(ints = {-1, 32, 0, 44})
    @ParameterizedTest
    void createTodayByInvalidDate(int date) {

        assertThatThrownBy(() -> new Date(date))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
