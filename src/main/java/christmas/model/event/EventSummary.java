package christmas.model.event;

import christmas.model.VisitDate;
import christmas.model.order.Orders;
import java.util.List;

public class EventSummary {

    private Gift gift;
    private List<Event> events;
    private Badge badge;

    public void apply(VisitDate visitDate, Orders orders) {
        gift = Gift.findGiftBy(orders.getTotalPrice());
        events = Event.findApplicableEventsBy(visitDate, orders);
        badge = Badge.decideBadgeBy(events.stream()
                .mapToInt(e -> e.calculate(visitDate, orders))
                .sum() + gift.getPrice());
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
