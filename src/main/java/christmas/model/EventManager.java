package christmas.model;

import static christmas.model.Event.DDAY_EVENT;
import static christmas.model.Event.GIFT_EVENT;
import static christmas.model.Event.SPECIAL_EVENT;
import static christmas.model.Event.WEEKDAY_EVENT;
import static christmas.model.Event.WEEKEND_EVENT;

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

    @Override
    public String toString() {
        if(events.keySet().isEmpty()) {
            return "없음\n";
        }
        StringBuilder sb = new StringBuilder();
        events.keySet()
                .forEach(event -> sb.append(String.format("%s: -%,d원\n", event.getName(), events.get(event))));
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
            events.put(WEEKDAY_EVENT, 2_023 * orders.numberOfDessert());
        }
    }

    private void addWeekEndEvent() {
        if(today.isWeekEndEvent()) {
            events.put(WEEKEND_EVENT, 2_023 * orders.numberOfMainMenu());
        }
    }

    private void addSpecialEvent() {
        if(today.isSpecialEvent()) {
            events.put(SPECIAL_EVENT, 1000);
        }
    }

    private void addGiftEvent() {
        if (!gift.isEmpty()) {
            events.put(GIFT_EVENT, gift.giftPrice());
        }
    }
}
