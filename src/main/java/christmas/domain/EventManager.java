package christmas.domain;

import static christmas.domain.Event.DDAY_EVENT;
import static christmas.domain.Event.GIFT_EVENT;
import static christmas.domain.Event.SPECIAL_EVENT;
import static christmas.domain.Event.WEEKDAY_EVENT;
import static christmas.domain.Event.WEEKEND_EVENT;

import java.util.Map;
import java.util.TreeMap;

public class EventManager {

    private final Map<Event, Integer> events;
    private final Today today;
    private final Orders orders;
    private final Gift gift;

    public EventManager(Today today, Orders orders, Gift gift) {
        this.today = today;
        this.orders = orders;
        this.gift = gift;
        events = new TreeMap<>();
        if(validateTotalPrice()) {
            addEvent();
        }
    }

    public int totalDiscount() {
        return events.keySet().stream()
                .mapToInt(events::get)
                .sum();
    }

    public int estimatedPrice() {
        return orders.totalPrice() - totalDiscount() + gift.giftPrice();
    }

    public String eventBadgeName() {
        return EventBadge.classifyEventBadge(totalDiscount());
    }

    @Override
    public String toString() {
        if(events.keySet().isEmpty()) {
            return "없음\n";
        }
        StringBuilder sb = new StringBuilder();
        events.keySet().forEach(event -> sb.append(
                String.format("%s: -%,d원\n", event.getName(), events.get(event))
        ));
        return sb.toString();
    }

    private boolean validateTotalPrice() {
        return orders.totalPrice() >= 10_000;
    }

    private void addEvent() {
        addDDayEvent();
        addWeekDayEvent();
        addWeekEndEvent();
        addSpecialEvent();
        addGiftEvent();
    }

    private void addDDayEvent() {
        if(today.isDDayEvent()) {
            events.put(DDAY_EVENT, 1000 + (today.getDate() - 1) * 100);
        }
    }

    private void addWeekDayEvent() {
        if(today.isWeekDayEvent()) {
            int discount = 2_023 * orders.numberOfDessert();
            if(discount != 0) {
                events.put(WEEKDAY_EVENT, discount);
            }
        }
    }

    private void addWeekEndEvent() {
        if(today.isWeekEndEvent()) {
            int discount = 2_023 * orders.numberOfMainMenu();
            if(discount != 0) {
                events.put(WEEKEND_EVENT, discount);
            }
        }
    }

    private void addSpecialEvent() {
        if(today.isSpecialEvent()) {
            events.put(SPECIAL_EVENT, 1000);
        }
    }

    private void addGiftEvent() {
        int price = gift.giftPrice();
        if (price != 0) {
            events.put(GIFT_EVENT, price);
        }
    }
}
