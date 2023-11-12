package christmas.controller;

import christmas.model.EventManager;
import christmas.model.Gift;
import christmas.model.OrderList;
import christmas.model.Today;
import christmas.view.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private InputView inputView;
    private OutputView outputView;
    private ErrorMessage errorMessage;
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
        inputView = initInputView();
        outputView = initOutputView();
        errorMessage = initErrorMessage();
    }

    private void input() {
        outputView.printHello();
        today = initToday();
        orderList = initOrder();
    }

    private void calculate() {
        gift = initGift();
        eventManager = initEventManager();
    }

    private void preview() {
        outputView.printEventPreviewStart();
        outputView.printOrderList(orderList.toString());
        outputView.printTotalPrice(orderList.totalPrice());
        outputView.printGift(gift.toString());
        outputView.printEventList(eventManager.toString());
        outputView.printTotalDiscount(eventManager.totalDiscount());
        outputView.printEstimatedPrice(eventManager.estimatedPrice());
        outputView.printBadge(eventManager.eventBadge());
    }

    private InputView initInputView() {
        return new InputView();
    }

    private OutputView initOutputView() {
        return new OutputView();
    }

    private ErrorMessage initErrorMessage() {
        return new ErrorMessage();
    }

    private Today initToday() {
        try {
            return new Today(inputView.inputDate());
        } catch (IllegalArgumentException e) {
            errorMessage.printDateFormatError();
            return initToday();
        }
    }

    private OrderList initOrder() {
        try {
            return new OrderList(inputView.inputOrders());
        } catch (IllegalArgumentException e) {
            errorMessage.printOrderFormatError();
            return initOrder();
        }
    }

    private Gift initGift() {
        return new Gift(orderList.totalPrice());
    }

    private EventManager initEventManager() {
        return new EventManager(today, orderList, gift);
    }
}
