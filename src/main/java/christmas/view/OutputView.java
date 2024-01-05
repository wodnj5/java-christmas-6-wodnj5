package christmas.view;

import christmas.model.benefits.EventSummary;
import christmas.model.orders.OrderSummary;
import christmas.model.benefits.GiftSummary;

public class OutputView {

    public static void printContents(OrderSummary orderSummary, GiftSummary giftSummary, EventSummary eventSummary) {
        printHello();
        printOrders(orderSummary);
        printBenefitStart();
        printTotalPrice(orderSummary);
        printGiftEvent(giftSummary);
        printBenefits(eventSummary);
        printTotalDiscount(eventSummary);
        printExpectDiscount(orderSummary, eventSummary, giftSummary);
        printBadge(eventSummary);
    }

    private static void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n");
    }

    private static void printOrders(OrderSummary orderSummary) {
        System.out.println("<주문 메뉴>");
        System.out.println(orderSummary);
    }

    private static void printBenefitStart() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    private static void printTotalPrice(OrderSummary orderSummary) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", orderSummary.getTotalPrice());
        System.out.println();
    }

    private static void printGiftEvent(GiftSummary giftSummary) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftSummary);
    }

    private static void printBenefits(EventSummary eventSummary) {
        System.out.println("<혜택 내역>");
        System.out.println(eventSummary);
    }

    private static void printTotalDiscount(EventSummary eventSummary) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원\n", eventSummary.getTotalDiscount());
        System.out.println();
    }

    private static void printExpectDiscount(OrderSummary orderSummary, EventSummary eventSummary, GiftSummary giftSummary) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", orderSummary.getTotalPrice() - eventSummary.getTotalDiscount() - giftSummary.getPrice());
        System.out.println();
    }

    private static void printBadge(EventSummary eventSummary) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventSummary.getBadge());
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
