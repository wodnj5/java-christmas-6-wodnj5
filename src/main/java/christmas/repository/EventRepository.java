package christmas.repository;

import static christmas.model.Event.DDAY_EVENT;
import static christmas.model.Event.GIFT_EVENT;
import static christmas.model.Event.SPECIAL_EVENT;
import static christmas.model.Event.WEEKDAY_EVENT;
import static christmas.model.Event.WEEKEND_EVENT;

import christmas.model.Event;
import christmas.model.Gift;
import christmas.model.Orders;
import christmas.model.Date;
import java.util.Map;
import java.util.TreeMap;

public class EventRepository {

    private Map<Event, Integer> events;

    public void addEvent(Date date, Orders orders, Gift gift) {
        events = new TreeMap<>();
        if(validateTotalPrice(orders)) {
            addDDayEvent(date);
            addWeekDayEvent(date, orders);
            addWeekEndEvent(date, orders);
            addSpecialEvent(date);
            addGiftEvent(gift);
        }
    }

    private boolean validateTotalPrice(Orders orders) {
        return orders.totalPrice() >= 10_000;
    }

    private void addDDayEvent(Date date) {
        if(date.isDDayEvent()) {
            events.put(DDAY_EVENT, 1000 + (date.getDate() - 1) * 100);
        }
    }

    private void addWeekDayEvent(Date date, Orders orders) {
        if(date.isWeekDayEvent()) {
            int discount = 2_023 * orders.numberOfDessert();
            if(discount != 0) {
                events.put(WEEKDAY_EVENT, discount);
            }
        }
    }

    private void addWeekEndEvent(Date date, Orders orders) {
        if(date.isWeekEndEvent()) {
            int discount = 2_023 * orders.numberOfMainMenu();
            if(discount != 0) {
                events.put(WEEKEND_EVENT, discount);
            }
        }
    }

    private void addSpecialEvent(Date date) {
        if(date.isSpecialEvent()) {
            events.put(SPECIAL_EVENT, 1000);
        }
    }

    private void addGiftEvent(Gift gift) {
        int price = gift.giftPrice();
        if (price != 0) {
            events.put(GIFT_EVENT, price);
        }
    }

    public int totalDiscount() {
        return events.keySet().stream()
                .mapToInt(events::get)
                .sum();
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
}
