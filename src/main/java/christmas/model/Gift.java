package christmas.model;

import static christmas.model.Menu.CHAMPAGNE;

public class Gift {

    private final Menu menu;
    private int count;

    public Gift(int totalPrice) {
        menu = validate(totalPrice);
    }

    private Menu validate(int totalPrice) {
        if(totalPrice >= 120_000) {
            count = 1;
            return CHAMPAGNE;
        }
        return null;
    }

    @Override
    public String toString() {
        if(menu == null) {
            return "없음\n";
        }
        return String.format("%s %d개\n", menu.getName(), count);
    }
}
