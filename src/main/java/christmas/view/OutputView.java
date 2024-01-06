package christmas.view;

import static christmas.model.event.Gift.NO_GIFT;

import christmas.model.VisitDate;
import christmas.model.event.EventSummary;
import christmas.model.order.Orders;

public class OutputView {

    public void printEventSummary(VisitDate visitDate, Orders orders, EventSummary eventSummary) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println();
        System.out.println("<주문 메뉴>");
        System.out.println(formatOrders(orders));
        System.out.println(formatVisitDate(visitDate) + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatTotalPrice(orders));
        System.out.println("<증정 메뉴>");
        System.out.println(formatGift(eventSummary));
        System.out.println("<혜택 내역>");
        System.out.println(formatEvents(visitDate, orders, eventSummary));
        System.out.println("<총혜택 금액>");
        System.out.println(formatTotalDiscount(visitDate, orders, eventSummary));
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatEstimatedPayment(visitDate, orders, eventSummary));
        System.out.println("<12월 이벤트 배지>");
        System.out.println(formatBadge(eventSummary));
    }

    private String formatVisitDate(VisitDate visitDate) {
        return String.format("%d월 %d일", visitDate.getMonth(), visitDate.getDate());
    }

    private String formatOrders(Orders orders) {
        StringBuilder sb = new StringBuilder();
        orders.getOrders().forEach(o -> sb.append(String.format("%s %d개\n", o.getMenuName(), o.getCount())));
        return sb.toString();
    }

    private String formatTotalPrice(Orders orders) {
        return String.format("%,d원\n", orders.getTotalPrice());
    }

    private String formatGift(EventSummary eventSummary) {
        if(eventSummary.getGift().equals(NO_GIFT)) {
            return "없음";
        }
        return String.format("%s %d개\n", eventSummary.getGift().getMenuName(), eventSummary.getGift().getCount());
    }

    private String formatEvents(VisitDate visitDate, Orders orders, EventSummary eventSummary) {
        StringBuilder sb = new StringBuilder();
        if(!eventSummary.getEvents().isEmpty()) {
            eventSummary.getEvents().forEach(e -> sb.append(String.format("%s: -%,d원\n", e.getName(), e.calculate(visitDate, orders))));
        }
        if(!eventSummary.getGift().equals(NO_GIFT)) {
            sb.append(String.format("증정 이벤트: -%,d원\n", eventSummary.getGift().getPrice()));
        }
        if(sb.isEmpty()) {
            sb.append("없음");
        }
        return sb.toString();
    }

    private String formatTotalDiscount(VisitDate visitDate, Orders orders, EventSummary eventSummary) {
        return String.format("-%,d원\n",
                eventSummary.getEvents().stream()
                        .mapToInt(e -> e.calculate(visitDate, orders))
                        .sum() + eventSummary.getGift().getPrice()
        );
    }

    private String formatEstimatedPayment(VisitDate visitDate, Orders orders, EventSummary eventSummary) {
        return String.format("%,d원\n",
                orders.getTotalPrice() - eventSummary.getEvents().stream()
                        .mapToInt(e -> e.calculate(visitDate, orders))
                        .sum()
        );
    }

    private String formatBadge(EventSummary eventSummary) {
        return eventSummary.getBadge().getName();
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
