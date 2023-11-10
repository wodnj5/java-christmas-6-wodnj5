package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TodayTest {

    @DisplayName("유효하지 않는 날짜을 입력하면 예외를 발생한다.")
    @ValueSource(ints = {-1, 32, 0, 44})
    @ParameterizedTest
    void createTodayByInvalidDate(int date) {

        assertThatThrownBy(() -> new Today(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
