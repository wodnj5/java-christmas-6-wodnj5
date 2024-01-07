package christmas.view;

import static christmas.model.event.Gift.NO_GIFT;

import christmas.model.VisitDate;
import christmas.model.event.Badge;
import christmas.model.event.Event;
import christmas.model.event.Gift;
import christmas.model.Result;
import christmas.model.order.Orders;
import java.util.List;

public class OutputView {

    public void printResult(VisitDate visitDate, Orders orders, Result result) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n");
        System.out.println("<주문 메뉴>");
        System.out.println(formatOrders(orders));
        System.out.println(formatVisitDate(visitDate) + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatTotalPrice(orders));
        System.out.println("<증정 메뉴>");
        System.out.println(formatGift(result));
        System.out.println("<혜택 내역>");
        System.out.println(formatResult(visitDate, orders, result));
        System.out.println("<총혜택 금액>");
        System.out.println(formatTotalDiscount(visitDate, orders, result));
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatEstimatedPayment(visitDate, orders, result));
        System.out.println("<12월 이벤트 배지>");
        System.out.println(formatBadge(result));
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

    private String formatGift(Result result) {
        Gift gift = result.getGift();
        if(gift.equals(NO_GIFT)) {
            return "없음\n";
        }
        return String.format("%s %d개\n", gift.getMenuName(), gift.getCount());
    }

    private String formatResult(VisitDate visitDate, Orders orders, Result result) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatEvents(visitDate, orders, result));
        sb.append(formatGiftEvent(result));
        if(sb.isEmpty()) {
            return "없음\n";
        }
        return sb.toString();
    }

    private String formatEvents(VisitDate visitDate, Orders orders, Result result) {
        List<Event> events = result.getEvents();
        if(events.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        events.forEach(e -> sb.append(String.format("%s: -%,d원\n", e.getName(), e.calculate(visitDate, orders))));
        return sb.toString();
    }

    private String formatGiftEvent(Result result) {
        Gift gift = result.getGift();
        if(gift.equals(NO_GIFT)) {;
            return "";
        }
        return String.format("증정 이벤트: -%,d원\n", gift.getPrice());
    }

    private String formatTotalDiscount(VisitDate visitDate, Orders orders, Result result) {
        List<Event> events = result.getEvents();
        return String.format("%,d원\n",
                events.stream()
                        .mapToInt(e -> e.calculate(visitDate, orders))
                        .sum() + result.getGift().getPrice()
        );
    }

    private String formatEstimatedPayment(VisitDate visitDate, Orders orders, Result result) {
        List<Event> events = result.getEvents();
        return String.format("%,d원\n",
                orders.getTotalPrice() - events.stream()
                        .mapToInt(e -> e.calculate(visitDate, orders))
                        .sum()
        );
    }

    private String formatBadge(Result result) {
        Badge badge = result.getBadge();
        return badge.getName();
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}
