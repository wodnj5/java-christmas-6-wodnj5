package christmas.controller;

import christmas.domain.EventManager;
import christmas.domain.Gift;
import christmas.domain.Orders;
import christmas.domain.Today;
import christmas.view.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorMessage errorMessage;
    private Today today;
    private Orders orders;
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
        orders = createOrder();
    }

    private void calculate() {
        gift = createGift();
        eventManager = createEventManager();
    }

    private void preview() {
        outputView.printEventPreviewStart();
        previewOrders();
        previewGift();
        previewEvents();
    }

    private void previewOrders() {
        outputView.printOrders(orders.toString());
        outputView.printTotalPrice(orders.totalPrice());
    }

    private void previewGift() {
        outputView.printGift(gift.toString());
    }

    private void previewEvents() {
        outputView.printEvents(eventManager.toString());
        outputView.printTotalDiscount(eventManager.totalDiscount());
        outputView.printEstimatedPrice(eventManager.estimatedPrice());
        outputView.printEventBadge(eventManager.eventBadgeName());
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

    private Orders createOrder() {
        while(true) {
            try {
                return new Orders(inputView.inputOrders());
            } catch (IllegalArgumentException e) {
                errorMessage.printOrderFormatError();
            }
        }
    }

    private Gift createGift() {
        return new Gift(orders);
    }

    private EventManager createEventManager() {
        return new EventManager(today, orders, gift);
    }
}
