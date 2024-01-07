package christmas.controller;

import christmas.model.Result;
import christmas.model.order.Order;
import christmas.model.order.Orders;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        VisitDate visitDate = repeatUntilSuccess(this::convertToDate);
        Orders orders = repeatUntilSuccess(this::convertToOrders);
        Result result = new Result(visitDate, orders);
        outputView.printResult(visitDate, orders, result);
    }

    private VisitDate convertToDate() {
        return new VisitDate(inputView.readDate());
    }

    private Orders convertToOrders() {
        List<Order> orders = Stream.of(inputView.readOrders())
                .map(o -> o.split("-"))
                .map(m -> new Order(m[0], Integer.parseInt(m[1])))
                .toList();
        return new Orders(orders);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        while(true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}

