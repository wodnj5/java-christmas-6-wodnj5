package christmas.domain;

import static christmas.domain.Menu.CHAMPAGNE;

public class Gift {

    private Menu menu;
    private int count;

    public Gift(Orders orders) {
        if(validate(orders.totalPrice())) {
            menu = CHAMPAGNE;
            count = 1;
        }
    }

    public int giftPrice() {
        if(menu == null) {
            return 0;
        }
        return menu.getPrice();
    }

    private boolean validate(int totalPrice) {
        return totalPrice >= 120_000;
    }

    @Override
    public String toString() {
        if(menu == null) {
            return "없음\n";
        }
        return String.format("%s %d개\n", menu.getName(), count);
    }
}
