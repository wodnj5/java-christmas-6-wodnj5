package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersTest {

    @DisplayName("음료수만 있으면 예외를 발생한다.")
    @Test
    void createOrderByOnlyDrink() {

        assertThatThrownBy(() -> new Orders(List.of(
                new String[]{"제로콜라", "1"},
                new String[]{"샴페인", "1"},
                new String[]{"레드와인", "1"}
        ))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴가 있으면 예외를 발생한다.")
    @Test
    void createOrderByDistinctMenu() {

        assertThatThrownBy(() -> new Orders(List.of(
                new String[]{"제로콜라", "1"},
                new String[]{"해산물파스타", "1"},
                new String[]{"해산물파스타", "2"}
        ))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("없는 메뉴를 주문하면 예외를 발생한다.")
    @Test
    void createOrderByInvalidMenu() {

        assertThatThrownBy(() -> new Orders(List.of(
                new String[]{"제로콜라", "1"},
                new String[]{"토마토파스타", "1"},
                new String[]{"해산물파스타", "2"}
        ))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("20개 이상의 메뉴를 주문하면 예외를 발생한다.")
    @Test
    void createOrderByTooManyMenu() {

        assertThatThrownBy(() -> new Orders(List.of(
                new String[]{"제로콜라", "7"},
                new String[]{"크리스마스파스타", "8"},
                new String[]{"해산물파스타", "8"}
        ))).isInstanceOf(IllegalArgumentException.class);
    }
}
