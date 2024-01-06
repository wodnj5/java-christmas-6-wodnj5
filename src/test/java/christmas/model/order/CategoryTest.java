package christmas.model.order;

import static christmas.model.order.Category.DRINK;
import static christmas.model.order.Category.MAIN_MENU;
import static christmas.model.order.Menu.T_BONE_STEAK;
import static christmas.model.order.Menu.ZERO_COKE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    void 메뉴_카테고리_테스트() {

        Assertions.assertThat(MAIN_MENU.contains(T_BONE_STEAK))
                .isEqualTo(true);

        Assertions.assertThat(DRINK.contains(ZERO_COKE))
                .isEqualTo(true);
    }
}
