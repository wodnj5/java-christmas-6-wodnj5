package christmas.model.benefits;

import christmas.model.VisitDate;
import christmas.model.orders.OrderSummary;
import org.assertj.core.util.TriFunction;

public enum Event {
    DDAY_EVENT("크리스마스 디데이 할인", (visitDate, orders, giftSummary) -> {
        if(visitDate.isDDayEvent()) {
            return 1000 + (visitDate.getDate() - 1) * 100;
        }
        return 0;
    }),
    WEEKDAY_EVENT("평일 할인", (visitDate, orders, giftSummary) -> {
        if(visitDate.isWeekDayEvent()) {
            return 2_023 * orders.getNumberOfDessert();
        }
        return 0;
    }),
    WEEKEND_EVENT("주말 할인", (visitDate, orders, giftSummary) -> {
        if(visitDate.isWeekEndEvent()) {
            return 2_023 * orders.getNumberOfMainMenu();
        }
        return 0;
    }),
    SPECIAL_EVENT("특별 할인", (visitDate, orders, giftSummary) -> {
        if(visitDate.isSpecialEvent()) {
            return 1_000;
        }
        return 0;
    }),

    GIFT_EVENT("증정 이벤트", (visitDate, orders, giftSummary) -> giftSummary.getPrice());

    private final String name;
    private final TriFunction<VisitDate, OrderSummary, GiftSummary, Integer> discount;

    Event(String name, TriFunction<VisitDate, OrderSummary, GiftSummary, Integer> discount) {
        this.name = name;
        this.discount = discount;
    }

    public int applyDiscount(VisitDate visitDate, OrderSummary orderSummary, GiftSummary giftSummary) {
        return discount.apply(visitDate, orderSummary, giftSummary);
    }

    @Override
    public String toString() {
        return name;
    }
}
