package christmas.controller;

import christmas.model.EventManager;
import christmas.model.Gift;
import christmas.model.OrderList;
import christmas.model.Today;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private InputView inputView;
    private OutputView outputView;
    private Today today;
    private OrderList orderList;
    private Gift gift;
    private EventManager eventManager;

    public void run() {
        start();
        input();
        calculate();
        preview();
    }

    private void start() {
        inputView = inputView();
        outputView = outputView();
    }

    private void input() {
        outputView.printHello();
        today = today();
        orderList = orderList();
    }

    private void calculate() {
        gift = gift();
        eventManager = eventManager();
    }

    private void preview() {
        outputView.printEventPreviewStart();
        orderListDetails();
        giftDetails();
        eventDetails();
    }

    private void orderListDetails() {
        outputView.printOrderList(orderList.toString());
        outputView.printTotalPrice(orderList.totalPrice());
    }

    private void giftDetails() {
        outputView.printGift(gift.toString());
    }

    private void eventDetails() {
        outputView.printEventList(eventManager.toString());
        outputView.printTotalDiscount(eventManager.totalDiscount());
        outputView.printEstimatedPrice(eventManager.estimatedPrice());
        outputView.printBadge(eventManager.badge());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private Today today() {
        try {
            return new Today(inputView.inputDate());
        } catch (IllegalArgumentException e) {
            return today();
        }
    }

    private OrderList orderList() {
        try {
            return new OrderList(inputView.inputOrders());
        } catch (IllegalArgumentException e) {
            return orderList();
        }
    }

    private Gift gift() {
        return new Gift(orderList.calculateTotalPrice());
    }

    private EventManager eventManager() {
        return new EventManager(today, orderList, gift);
    }
}
