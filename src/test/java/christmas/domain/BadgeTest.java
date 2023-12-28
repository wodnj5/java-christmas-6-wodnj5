package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {

    @DisplayName("금액에 따라 다른 배지 이름을 출력한다.")
    @Test
    void classifyBadgeTest() {
        int numberOfCases = 4;
        int[] ints = {9_900, 19_900, 20_001, 4_999};
        String[] results = {"별", "트리", "산타", "없음"};

        for(int i = 0; i < numberOfCases ; i++) {
            assertThat(Badge.find(ints[i])).isEqualTo(results[i]);
        }
    }
}
