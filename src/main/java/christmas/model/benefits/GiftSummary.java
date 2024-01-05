package christmas.model.benefits;

import static christmas.model.benefits.Gift.NO_GIFT;

import christmas.model.orders.OrderSummary;

public class GiftSummary {
    private final Gift gift;

    public GiftSummary(OrderSummary orderSummary) {
        this.gift = Gift.decideGiftBy(orderSummary.getTotalPrice());
    }

    public int getPrice() {
        return gift.getPrice();
    }

    @Override
    public String toString() {
        if(gift.equals(NO_GIFT)) {
            return "없음\n";
        }
        return String.format("%s %d개\n", gift, gift.getCount());
    }
}
