package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @DisplayName("음료수만 있으면 예외를 발생한다.")
    @Test
    void createOrderByOnlyDrink() {

        assertThatThrownBy(() -> new Order(List.of("제로콜라-1", "샴페인-1", "레드와인-1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴가 있으면 예외를 발생한다.")
    @Test
    void createOrderByDistinctMenu() {

        assertThatThrownBy(() -> new Order(List.of("제로콜라-1", "해산물파스타-1", "해산물파스타-2")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("없는 메뉴를 주문하면 예외를 발생한다.")
    @Test
    void createOrderByInvalidMenu() {

        assertThatThrownBy(() -> new Order(List.of("제로콜라-1", "토마토파스타-1", "해산물파스타-2")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("20개 이상의 메뉴를 주문하면 예외를 발생한다.")
    @Test
    void createOrderByTooManyMenu() {

        assertThatThrownBy(() -> new Order(List.of("제로콜라-7", "크리스마스파스타-8", "해산물파스타-8")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
