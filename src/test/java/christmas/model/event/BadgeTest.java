package christmas.model.event;

import static christmas.model.event.Badge.NO_BADGE;
import static christmas.model.event.Badge.SANTA;
import static christmas.model.event.Badge.STAR;
import static christmas.model.event.Badge.TREE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BadgeTest {

    @ValueSource(ints = {21_000, 30_500, 29_700})
    @ParameterizedTest
    void 산타_배지_반환_테스트(int totalDiscount) {

        Assertions.assertThat(Badge.findBadgeBy(totalDiscount))
                .isEqualTo(SANTA);
    }

    @ValueSource(ints = {11_000, 13_500, 19_000})
    @ParameterizedTest
    void 트리_배지_반환_테스트(int totalDiscount) {

        Assertions.assertThat(Badge.findBadgeBy(totalDiscount))
                .isEqualTo(TREE);
    }

    @ValueSource(ints = {6_000, 7_900, 9_990})
    @ParameterizedTest
    void 별_배지_반환_테스트(int totalDiscount) {

        Assertions.assertThat(Badge.findBadgeBy(totalDiscount))
                .isEqualTo(STAR);
    }

    @ValueSource(ints = {2_000, 3_900, 4_990})
    @ParameterizedTest
    void 배지_없음_반환_테스트(int totalDiscount) {

        Assertions.assertThat(Badge.findBadgeBy(totalDiscount))
                .isEqualTo(NO_BADGE);
    }
}
