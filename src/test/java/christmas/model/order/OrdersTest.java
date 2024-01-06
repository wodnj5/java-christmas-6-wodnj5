package christmas.model.order;

import christmas.model.order.Order;
import christmas.model.order.Orders;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrdersTest {
    @Test
    void 음료만_주문_시_예외_발생() {
        List<Order> orders = List.of(
                new Order("제로콜라", 1),
                new Order("샴페인", 2),
                new Order("레드와인", 1)
        );

        Assertions.assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_개수_가_20개_초과_시_에외_발생() {
        List<Order> orders = List.of(
                new Order("티본스테이크", 6),
                new Order("바비큐립", 7),
                new Order("레드와인", 8)
        );

        Assertions.assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 총_금액_테스트() {
        List<Order> orders = List.of(
                new Order("티본스테이크", 1),
                new Order("바비큐립", 1),
                new Order("레드와인", 1)
        );

        Assertions.assertThat(new Orders(orders).getTotalPrice())
                .isEqualTo(169_000);
    }

    @Test
    void 음료_개수_테스트() {
        List<Order> orders = List.of(
                new Order("티본스테이크", 1),
                new Order("바비큐립", 1),
                new Order("레드와인", 1),
                new Order("아이스크림", 4)
        );

        Assertions.assertThat(new Orders(orders).getNumberOfDessert())
                .isEqualTo(4);
    }

    @Test
    void 메인_메뉴_개수_테스트() {
        List<Order> orders = List.of(
                new Order("티본스테이크", 1),
                new Order("바비큐립", 1),
                new Order("레드와인", 1),
                new Order("아이스크림", 4)
        );

        Assertions.assertThat(new Orders(orders).getNumberOfMainMenu())
                .isEqualTo(2);
    }

    @Test
    void 혜택_가능한_금액_여부_테스트() {
        List<Order> orders = List.of(
                new Order("티본스테이크", 1),
                new Order("바비큐립", 1),
                new Order("레드와인", 1),
                new Order("아이스크림", 4)
        );

        Assertions.assertThat(new Orders(orders).isEnoughTotalPrice())
                .isEqualTo(true);
    }
}
