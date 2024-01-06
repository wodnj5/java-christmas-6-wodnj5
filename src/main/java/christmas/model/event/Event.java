package christmas.model.event;

import christmas.model.VisitDate;
import christmas.model.order.Orders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum Event {
    DDAY_EVENT("크리스마스 디데이 할인") {
        @Override
        public int calculate(VisitDate visitDate, Orders orders) {
            if(visitDate.isDDayEvent()) {
                return 1000 + (visitDate.getDate() - 1) * 100;
            }
            return 0;
        }
    },
    WEEKDAY_EVENT("평일 할인") {
        @Override
        public int calculate(VisitDate visitDate, Orders orders) {
            if(visitDate.isWeekDayEvent()) {
                return 2_023 * orders.getNumberOfDessert();
            }
            return 0;
        }
    },
    WEEKEND_EVENT("주말 할인") {
        @Override
        public int calculate(VisitDate visitDate, Orders orders) {
            if(visitDate.isWeekEndEvent()) {
                return 2_023 * orders.getNumberOfMainMenu();
            }
            return 0;
        }
    },
    SPECIAL_EVENT("특별 할인") {
        @Override
        public int calculate(VisitDate visitDate, Orders orders) {
            if(visitDate.isSpecialEvent()) {
                return 1_000;
            }
            return 0;
        }
    };

    private final String name;
    public abstract int calculate(VisitDate visitDate, Orders orders);

    Event(String name) {
        this.name = name;
    }

    public static List<Event> findEventsBy(VisitDate visitDate, Orders orders) {
        List<Event> events = new ArrayList<>();
        if(!orders.notEnoughPrice()) {
            Stream.of(Event.values())
                    .filter(e -> e.calculate(visitDate, orders) > 0)
                    .forEach(events::add);
        }
        return events;
    }

    public String getName() {
        return name;
    }
}
