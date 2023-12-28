package christmas.service;

import christmas.model.Badge;
import christmas.model.Gift;
import christmas.model.Orders;
import christmas.model.Date;
import christmas.repository.EventRepository;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void calculate(Date date, Orders orders, Gift gift) {
        eventRepository.addEvent(date, orders, gift);
    }

    public String getEvents() {
        return eventRepository.toString();
    }

    public int getTotalDiscount() {
        return eventRepository.totalDiscount();
    }

    public int getEstimatedPrice(Orders orders, Gift gift) {
        return orders.totalPrice() - eventRepository.totalDiscount() + gift.giftPrice();
    }

    public String getBadge() {
        return Badge.find(eventRepository.totalDiscount());
    }
}
