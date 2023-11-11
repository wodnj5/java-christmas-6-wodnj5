package christmas.model;

import static christmas.model.Event.DDAY_EVENT;
import static christmas.model.Event.GIFT_EVENT;
import static christmas.model.Event.SPECIAL_EVENT;
import static christmas.model.Event.WEEKDAY_EVENT;
import static christmas.model.Event.WEEKEND_EVENT;

import java.util.Map;
import java.util.TreeMap;

public class EventManager {

    private final Map<Event, Integer> eventList;
    private final OrderList orderList;
    private final Gift gift;

    public EventManager(Today today, OrderList orderList, Gift gift) {
        eventList = new TreeMap<>();
        this.orderList = orderList;
        this.gift = gift;
        if(validateTotalPrice()) {
            addEvent(today);
        }
    }

    public String totalDiscount() {
        return String.format("-%,d원\n", calculateTotalDiscount());
    }

    public String estimatedPrice() {
        return String.format("%,d원\n", calculateEstimatedPrice());
    }

    @Override
    public String toString() {
        if(eventList.keySet().isEmpty()) {
            return "없음\n";
        }
        StringBuilder sb = new StringBuilder();
        eventList.keySet()
                .forEach(event -> sb.append(String.format("%s: -%,d원\n", event.getName(), eventList.get(event))));
        return sb.toString();
    }

    private boolean validateTotalPrice() {
        return orderList.calculateTotalPrice() >= 10_000;
    }

    private void addEvent(Today today) {
        if(today.isDDayEvent()) {
            eventList.put(DDAY_EVENT, today.calculateDDayEvent());
        }
        if(today.isWeekDayEvent()) {
            eventList.put(WEEKDAY_EVENT, orderList.calculateWeekDayEvent());
        }
        if(today.isWeekEndEvent()) {
            eventList.put(WEEKEND_EVENT, orderList.calculateWeekEndEvent());
        }
        if(today.isSpecialEvent()) {
            eventList.put(SPECIAL_EVENT, 1000);
        }
        if(!gift.isEmpty()) {
            eventList.put(GIFT_EVENT, gift.getMenuPrice());
        }
    }

    private int calculateTotalDiscount() {
        return eventList.keySet().stream()
                .mapToInt(eventList::get)
                .sum();
    }

    private int calculateEstimatedPrice() {
        int sum = orderList.calculateTotalPrice() - calculateTotalDiscount();
        if(!gift.isEmpty()) {
            sum += gift.getMenuPrice();
        }
        return sum;
    }
}
