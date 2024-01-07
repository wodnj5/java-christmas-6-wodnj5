package christmas.model.event;

import static christmas.model.event.Gift.CHAMPAGNE_GIFT;
import static christmas.model.event.Gift.NO_GIFT;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GiftTest {

    @Test
    void 샴페인_선물_반환_테스트() {
        int totalPrice = 120_021;

        Assertions.assertThat(Gift.findGiftBy(totalPrice))
                .isEqualTo(CHAMPAGNE_GIFT);
    }

    @Test
    void 선물_없음_반환_테스트() {
        int totalPrice = 119_999;

        Assertions.assertThat(Gift.findGiftBy(totalPrice))
                .isEqualTo(NO_GIFT);
    }
}
