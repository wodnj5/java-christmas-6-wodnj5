package christmas.model;

import christmas.model.VisitDate;
import christmas.model.event.Badge;
import christmas.model.event.Event;
import christmas.model.event.Gift;
import christmas.model.order.Orders;
import java.util.List;

public class Result {
    private final Gift gift;
    private final List<Event> events;
    private final Badge badge;

    public Result(VisitDate visitDate, Orders orders) {
        gift = Gift.findGiftBy(orders.getTotalPrice());
        events = Event.findEventsBy(visitDate, orders);
        badge = Badge.findBadgeBy(
                events.stream()
                        .mapToInt(e -> e.calculate(visitDate, orders))
                        .sum() + gift.getPrice()
        );
    }

    public Gift getGift() {
        return gift;
    }

    public List<Event> getEvents() {
        return events;
    }

    public Badge getBadge() {
        return badge;
    }
}
