package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventManagerTest {

    int numberOfCases = 3;

    Today[] today = {
            new Today(1),
            new Today(15),
            new Today(25)
    };

    Orders[] orders = {
            new Orders(List.of("양송이수프-2", "티본스테이크-1", "바비큐립-1", "아이스크림-2", "제로콜라-2")),
            new Orders(List.of("시저샐러드-1", "초코케이크-1", "레드와인-1")),
            new Orders(List.of("타파스-1", "해산물파스타-1", "크리스마스파스타-1", "아이스크림-2"))
    };

    Gift[] gifts = {
            new Gift(orders[0]),
            new Gift(orders[1]),
            new Gift(orders[2])
    };

    @DisplayName("주문에 따라 총 할인 혜택을 출력한다.")
    @Test
    void calculateTotalDiscountTest() {
        int[] results = {30_046, 2_400, 8_446};

        for(int i = 0 ; i < numberOfCases; i++) {
            assertThat(new EventManager(today[i], orders[i], gifts[i]).totalDiscount())
                    .isEqualTo(results[i]);
        }
    }

    @DisplayName("주문에 따라 총 할인 혜택을 출력한다.")
    @Test
    void calculateEstimatedPriceTest() {
        int[] results = {131_954, 80_600, 67_054};

        for(int i = 0 ; i < numberOfCases; i++) {
            assertThat(new EventManager(today[i], orders[i], gifts[i]).estimatedPrice())
                    .isEqualTo(results[i]);
        }
    }
}
