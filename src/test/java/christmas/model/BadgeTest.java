package christmas.model;

import static christmas.model.Badge.SANTA;
import static christmas.model.Badge.STAR;
import static christmas.model.Badge.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {

    @DisplayName("금액에 따라 다른 배지 이름을 출력한다.")
    @Test
    void classifyBadgeTest() {
        int[] ints = {9_900, 19_900, 20_001, 4_999};
        Badge[] result = {STAR, TREE, SANTA, null};

        for(int i = 0; i < ints.length ; i++) {
            assertThat(Badge.classifyBadge(ints[i])).isEqualTo(result[i]);
        }
    }
}
