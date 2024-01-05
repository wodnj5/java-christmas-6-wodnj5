package christmas.controller;

import christmas.model.order.Order;
import christmas.model.order.OrderSummary;
import christmas.model.VisitDate;
import christmas.model.event.EventSummary;
import christmas.model.gift.GiftSummary;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EventController {
    public void run() {
        OutputView.printHello();
        VisitDate visitDate = repeatUntilSuccess(this::convertToDate);
        OrderSummary orderSummary = repeatUntilSuccess(this::convertToOrders);
        OutputView.printOrderSummary(orderSummary);
        OutputView.printPreviewStart(visitDate);
        OutputView.printTotalPrice(orderSummary);
        GiftSummary giftSummary = new GiftSummary(orderSummary);
        OutputView.printGift(giftSummary);
        EventSummary eventSummary = new EventSummary(visitDate, orderSummary, giftSummary);
        OutputView.printEventSummary(eventSummary);
        OutputView.printTotalDiscount(eventSummary);
        OutputView.printExpectDiscount(orderSummary, giftSummary, eventSummary);
        OutputView.printBadge(eventSummary);
    }

    private VisitDate convertToDate() {
        return new VisitDate(InputView.readDate());
    }

    private OrderSummary convertToOrders() {
        List<Order> orders = Stream.of(InputView.readOrders())
                .map(o -> o.split("-"))
                .map(m -> new Order(m[0], Integer.parseInt(m[1])))
                .toList();
        return new OrderSummary(orders);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        while(true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);
            }
        }
    }
}

