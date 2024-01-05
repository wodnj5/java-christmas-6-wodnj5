package christmas.controller;

import christmas.model.orders.Order;
import christmas.model.orders.OrderSummary;
import christmas.model.VisitDate;
import christmas.model.benefits.EventSummary;
import christmas.model.benefits.GiftSummary;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EventController {
    public void run() {
        VisitDate visitDate = repeatUntilSuccess(this::convertToDate);
        OrderSummary orderSummary = repeatUntilSuccess(this::convertToOrders);
        GiftSummary giftSummary = new GiftSummary(orderSummary);
        EventSummary eventSummary = new EventSummary(visitDate, orderSummary, giftSummary);
        OutputView.printContents(orderSummary, giftSummary, eventSummary);
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

