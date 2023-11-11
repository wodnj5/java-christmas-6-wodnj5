package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GiftTest {

    @ValueSource(ints = {120_000, 130_000, 142_023})
    @DisplayName("금액이 12만원 이상이면 샴페인 증정 이벤트 상품을 받는다.")
    @ParameterizedTest
    void createGift(int totalPrice) {

        Assertions.assertThat("샴페인 1개\n")
                .contains(new Gift(totalPrice).toString());
    }

    @ValueSource(ints = {8_500, 13_000, 20_230})
    @DisplayName("금액이 12만원 이상이면 샴페인 증정 이벤트 상품을 받는다.")
    @ParameterizedTest
    void invalidGift(int totalPrice) {

        Assertions.assertThat("없음\n")
                .contains(new Gift(totalPrice).toString());
    }
}
