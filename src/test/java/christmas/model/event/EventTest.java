package christmas.model.event;

import static christmas.model.event.Event.DDAY_EVENT;
import static christmas.model.event.Event.SPECIAL_EVENT;

import christmas.model.VisitDate;
import christmas.model.order.Order;
import christmas.model.order.Orders;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    VisitDate visitDate = new VisitDate(3);
    Orders orders = new Orders(List.of(
            new Order("티본스테이크", 1),
            new Order("바비큐립", 1),
            new Order("해산물파스타", 1),
            new Order("제로콜라", 2)
    ));

    @Test
    void 이벤트_리스트_생성_테스트() {

        Assertions.assertThat(Event.findEventsBy(visitDate, orders))
                .contains(DDAY_EVENT)
                .contains(SPECIAL_EVENT);
    }

    @Test
    void 이벤트_할인_가격_계산_테스트() {
        List<Event> events = Event.findEventsBy(visitDate, orders);

        Assertions.assertThat(
                events.stream()
                        .map(e -> e.calculate(visitDate, orders))
                        .toList()
                ).contains(1_200)
                .contains(1_000);
    }

    @Test
    void 비어있는_리스트_반환_테스트() {
        VisitDate visitDate = new VisitDate(25);
        Orders orders = new Orders(List.of(
                new Order("양송이수프", 1),
                new Order("제로콜라", 1)
        ));

        Assertions.assertThat(Event.findEventsBy(visitDate, orders).isEmpty())
                .isEqualTo(true);
    }
}
