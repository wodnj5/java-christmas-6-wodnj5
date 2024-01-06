package christmas.model.order;

import static christmas.model.order.Menu.ZERO_COKE;

import christmas.model.order.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @Test
    void 메뉴_찾기_테스트() {
        String name = "제로콜라";

        Assertions.assertThat(Menu.findMenuBy(name))
                .isEqualTo(ZERO_COKE);
    }
}
