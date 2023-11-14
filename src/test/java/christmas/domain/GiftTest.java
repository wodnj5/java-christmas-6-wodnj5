package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftTest {

    @DisplayName("금액이 12만원 이상이면 샴페인 증정 이벤트 상품을 받는다.")
    @Test
    void createGiftByEnoughMoney() {
        Orders[] orders = {
                new Orders(List.of(
                        new String[]{"양송이수프", "2"},
                        new String[]{"티본스테이크", "1"},
                        new String[]{"바비큐립", "1"},
                        new String[]{"아이스크림", "2"},
                        new String[]{"제로콜라", "2"}
                )),
                new Orders(List.of(
                        new String[]{"시저샐러드", "1"},
                        new String[]{"바비큐립", "1"},
                        new String[]{"초코케이크", "1"},
                        new String[]{"레드와인", "1"}
                )),
                new Orders(List.of(
                        new String[]{"타파스", "1"},
                        new String[]{"해산물파스타", "1"},
                        new String[]{"크리스마스파스타", "1"},
                        new String[]{"티본스테이크", "1"},
                        new String[]{"아이스크림", "2"}
                ))
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
                new Orders(List.of(
                        new String[]{"양송이수프", "2"},
                        new String[]{"티본스테이크", "1"},
                        new String[]{"아이스크림", "2"},
                        new String[]{"제로콜라", "2"}
                )),
                new Orders(List.of(
                        new String[]{"시저샐러드", "1"},
                        new String[]{"초코케이크", "1"},
                        new String[]{"레드와인", "1"}
                )),
                new Orders(List.of(
                        new String[]{"타파스", "1"},
                        new String[]{"해산물파스타", "1"},
                        new String[]{"크리스마스파스타", "1"},
                        new String[]{"아이스크림", "2"}
                ))
        };

        for(Orders order : orders) {
            assertThat(new Gift(order).toString())
                    .isEqualTo("없음\n");
        }
    }
}
