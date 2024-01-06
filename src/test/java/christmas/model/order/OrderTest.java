package christmas.model.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    @ValueSource(strings = {"짬뽕", "함박스테이크", "펩시제로"})
    @ParameterizedTest
    void 잘못된_메뉴_이름_입력_시_예외_발생(String name) {

        Assertions.assertThatThrownBy(() -> new Order(name, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ValueSource(ints = {-1, 0, -2})
    @ParameterizedTest
    void 잘못된_메뉴_개수_입력_시_예외_발생(int count) {

        Assertions.assertThatThrownBy(() -> new Order("제로콜라", count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 카테고리_확인_테스트() {
        String name = "제로콜라";
        int count = 1;

        Assertions.assertThat(new Order(name, count).isMenuInCategory(Category.DRINK))
                .isEqualTo(true);
    }
}
