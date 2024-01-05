package christmas.model.gift;

import static christmas.model.gift.Gift.NO_GIFT;

import christmas.model.order.OrderSummary;

public class GiftSummary {
    private final Gift gift;

    public GiftSummary(OrderSummary orderSummary) {
        this.gift = Gift.decideGiftBy(orderSummary.getTotalPrice());
    }

    public int getPrice() {
        return gift.getPrice();
    }

    public String getContents() {
        if(gift.equals(NO_GIFT)) {
            return "없음\n";
        }
        return gift.getMenuName() + " " + gift.getCount() + "개\n";
    }
}
