package christmas.controller;

import christmas.model.Gift;
import christmas.model.Orders;
import christmas.model.Date;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final EventService eventService;

    public Controller(EventService eventService) {
        this.eventService = eventService;
    }

    public void run() {
        OutputView.printHello();
        Date date = date();
        Orders orders = order();
        Gift gift = new Gift(orders);
        OutputView.printEventPreviewStart();
        OutputView.printOrders(orders.toString());
        OutputView.printTotalPrice(orders.totalPrice());
        OutputView.printGift(gift.toString());
        eventService.calculate(date, orders, gift);
        OutputView.printEvents(eventService.getEvents());
        OutputView.printTotalDiscount(eventService.getTotalDiscount());
        OutputView.printEstimatedPrice(eventService.getEstimatedPrice(orders, gift));
        OutputView.printBadge(eventService.getBadge());
    }

    private Date date() {
        while(true) {
            try {
                return new Date(InputView.inputDate());
            } catch (IllegalArgumentException e) {
                OutputView.printDateFormatError();
            }
        }
    }

    private Orders order() {
        while(true) {
            try {
                return new Orders(InputView.inputOrders());
            } catch (IllegalArgumentException e) {
                OutputView.printOrderFormatError();
            }
        }
    }
}
