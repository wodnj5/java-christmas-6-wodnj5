package christmas.view;

import christmas.model.VisitDate;
import christmas.model.badge.BadgeSummary;
import christmas.model.event.EventSummary;
import christmas.model.order.OrderSummary;
import christmas.model.gift.GiftSummary;

public class OutputView {
    public static void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n");
    }

    public static void printOrderSummary(OrderSummary orderSummary) {
        System.out.println("<주문 메뉴>\n" + orderSummary.getContents());
    }

    public static void printPreviewStart(VisitDate visitDate) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", visitDate.getDate());
    }

    public static void printTotalPrice(OrderSummary orderSummary) {
        System.out.printf("<할인 전 총주문 금액>\n%,d원\n\n", orderSummary.getTotalPrice());
    }

    public static void printGift(GiftSummary giftSummary) {
        System.out.println("<증정 메뉴>\n" + giftSummary.getContents());
    }

    public static void printEventSummary(EventSummary eventSummary) {
        System.out.println("<혜택 내역>\n" + eventSummary.getContents());
    }

    public static void printTotalDiscount(EventSummary eventSummary) {
        System.out.printf("<총혜택 금액>\n%,d원\n\n", eventSummary.getTotalDiscount());
    }

    public static void printExpectDiscount(OrderSummary orderSummary, GiftSummary giftSummary, EventSummary eventSummary) {
        System.out.printf("<할인 후 예상 결제 금액>\n%,d원\n\n", orderSummary.getTotalPrice() - eventSummary.getTotalDiscount() - giftSummary.getPrice());
    }

    public static void printBadge(BadgeSummary badgeSummary) {
        System.out.println("<12월 이벤트 배지>\n" + badgeSummary.getContents());
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
