package christmas.model;

import static christmas.model.Event.DDAY_EVENT;
import static christmas.model.Event.SPECIAL_EVENT;
import static christmas.model.Event.WEEKDAY_EVENT;
import static christmas.model.Event.WEEKEND_EVENT;

import java.util.Map;
import java.util.TreeMap;

public class EventList {

    private final Map<Event, Integer> eventList;

    public EventList(Today today, OrderList orderList) {
        eventList = new TreeMap<>();
        validate(today, orderList);
    }

    private void validate(Today today, OrderList orderList) {
        if(orderList.totalPrice() >= 10_000) {
            findEvent(today, orderList);
        }
    }

    private void findEvent(Today today, OrderList orderList) {
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
}
