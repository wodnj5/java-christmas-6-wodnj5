package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftTest {

    @DisplayName("금액이 12만원 이상이면 샴페인 증정 이벤트 상품을 받는다.")
    @Test
    void createGiftByEnoughMoney() {
        Orders[] orders = {
                new Orders(List.of("양송이수프-2", "티본스테이크-1", "바비큐립-1", "아이스크림-2", "제로콜라-2")),
                new Orders(List.of("시저샐러드-1", "바비큐립-1", "초코케이크-1", "레드와인-1")),
                new Orders(List.of("타파스-1", "해산물파스타-1", "크리스마스파스타-1", "티본스테이크-1", "아이스크림-2"))
        };

        for(Orders order : orders) {
            assertThat(new Gift(order).toString())
                    .isEqualTo("샴페인 1개\n");
        }
    }
    @DisplayName("금액이 12만원 이하이면 이벤트 상품이 없다.")
    @Test
    void createGiftByNotEnoughMoney() {
        Orders[] orders = {
                new Orders(List.of("양송이수프-2", "티본스테이크-1", "아이스크림-2", "제로콜라-2")),
                new Orders(List.of("시저샐러드-1", "초코케이크-1", "레드와인-1")),
                new Orders(List.of("타파스-1", "해산물파스타-1", "크리스마스파스타-1", "아이스크림-2"))
        };

        for(Orders order : orders) {
            assertThat(new Gift(order).toString())
                    .isEqualTo("없음\n");
        }
    }
}
