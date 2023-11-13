package christmas.controller;

import christmas.model.EventManager;
import christmas.model.Gift;
import christmas.model.OrderList;
import christmas.model.Today;
import christmas.view.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorMessage errorMessage;
    private Today today;
    private OrderList orderList;
    private Gift gift;
    private EventManager eventManager;

    public Controller(InputView inputView, OutputView outputView, ErrorMessage errorMessage) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorMessage = errorMessage;
    }

    public void start() {
        input();
        calculate();
        preview();
    }

    private void input() {
        outputView.printHello();
        today = createToday();
        orderList = createOrder();
    }

    private void calculate() {
        gift = createGift();
        eventManager = createEventManager();
    }

    private void preview() {
        outputView.printEventPreviewStart();
        outputView.printOrderList(orderList.toString());
        outputView.printTotalPrice(orderList.totalPrice());
        outputView.printGift(gift.toString());
        outputView.printEventList(eventManager.toString());
        outputView.printTotalDiscount(eventManager.totalDiscount());
        outputView.printEstimatedPrice(eventManager.estimatedPrice());
        outputView.printBadge(eventManager.badgeName());
    }

    private Today createToday() {
        while(true) {
            try {
                return new Today(inputView.inputDate());
            } catch (IllegalArgumentException e) {
                errorMessage.printDateFormatError();
            }
        }
    }

    private OrderList createOrder() {
        while(true) {
            try {
                return new OrderList(inputView.inputOrders());
            } catch (IllegalArgumentException e) {
                errorMessage.printOrderFormatError();
            }
        }
    }

    private Gift createGift() {
        return new Gift(orderList.totalPrice());
    }

    private EventManager createEventManager() {
        return new EventManager(today, orderList, gift);
    }
}
