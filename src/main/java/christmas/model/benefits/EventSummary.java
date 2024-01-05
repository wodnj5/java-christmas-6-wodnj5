package christmas.model.benefits;

import christmas.model.VisitDate;
import christmas.model.orders.OrderSummary;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class EventSummary {
    private final Map<Event, Integer> benefit;

    public EventSummary(VisitDate visitDate, OrderSummary orderSummary, GiftSummary giftSummary) {
        benefit = new TreeMap<>();
        if(!orderSummary.noBenefit()) {
            Stream.of(Event.values())
                    .forEach(e -> benefit.put(e, e.applyDiscount(visitDate, orderSummary, giftSummary)));
        }
    }

    public int getTotalDiscount() {
        return benefit.keySet().stream()
                .mapToInt(benefit::get)
                .sum();
    }

    public Badge getBadge() {
        return Badge.decideBadgeBy(getTotalDiscount());
    }

    @Override
    public String toString() {
        if(benefit.isEmpty()) {
            return "없음\n";
        }
        StringBuilder sb = new StringBuilder();
        benefit.keySet().stream()
                .filter(e -> !benefit.get(e).equals(0))
                .forEach(e -> sb.append(e)
                        .append(" ")
                        .append(String.format("-%,d원", benefit.get(e)))
                        .append("\n")
                );
        return sb.toString();
    }
}
