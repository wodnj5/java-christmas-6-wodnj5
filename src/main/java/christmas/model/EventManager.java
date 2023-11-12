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
    private final Today today;
    private final Order order;
    private final Gift gift;

    public EventManager(Today today, Order order, Gift gift) {
        this.today = today;
        this.order = order;
        this.gift = gift;
        eventList = new TreeMap<>();
        if(validateTotalPrice()) {
            addEvent();
        }
    }

    public String totalDiscount() {
        return String.format("-%,d원\n", calculateTotalDiscount());
    }

    public String estimatedPrice() {
        return String.format("%,d원\n", calculateEstimatedPrice());
    }

    public String badge() {
        return String.format("%s", sortBadge().getName());
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
        return order.calculateTotalPrice() >= 10_000;
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
            eventList.put(DDAY_EVENT, today.calculateDDayEvent());
        }
    }

    private void addWeekDayEvent() {
        if(today.isWeekDayEvent()) {
            eventList.put(WEEKDAY_EVENT, 2_023 * order.numberOfDessert());
        }
    }

    private void addWeekEndEvent() {
        if(today.isWeekEndEvent()) {
            eventList.put(WEEKEND_EVENT, 2_023 * order.numberOfMainMenu());
        }
    }

    private void addSpecialEvent() {
        if(today.isSpecialEvent()) {
            eventList.put(SPECIAL_EVENT, 1000);
        }
    }

    private void addGiftEvent() {
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
        int sum = order.calculateTotalPrice() - calculateTotalDiscount();
        if(!gift.isEmpty()) {
            sum += gift.getMenuPrice();
        }
        return sum;
    }

    private Badge sortBadge() {
        return Badge.findBadge(calculateTotalDiscount());
    }
}
