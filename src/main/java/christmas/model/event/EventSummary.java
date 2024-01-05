package christmas.model.event;

import christmas.model.badge.Badge;
import christmas.model.VisitDate;
import christmas.model.gift.GiftSummary;
import christmas.model.order.OrderSummary;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class EventSummary {
    private final Map<Event, Integer> events;

    public EventSummary(VisitDate visitDate, OrderSummary orderSummary, GiftSummary giftSummary) {
        events = new TreeMap<>();
        if(!orderSummary.noBenefit()) {
            Stream.of(Event.values())
                    .forEach(e -> events.put(e, e.applyDiscount(visitDate, orderSummary, giftSummary)));
        }
    }

    public int getTotalDiscount() {
        return events.keySet().stream()
                .mapToInt(events::get)
                .sum();
    }

    public String getContents() {
        if(events.isEmpty()) {
            return "없음\n";
        }
        StringBuilder sb = new StringBuilder();
        events.keySet().stream()
                .filter(e -> !events.get(e).equals(0))
                .forEach(e -> sb.append(String.format("%s: -%,d원\n", e.getName(), events.get(e))));
        return sb.toString();
    }

    public Badge getBadge() {
        return Badge.decideBadgeBy(getTotalDiscount());
    }
}
