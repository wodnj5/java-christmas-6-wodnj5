package christmas.model.benefits;

import static christmas.model.orders.Menu.CHAMPAGNE;
import static christmas.model.orders.Menu.NO_MENU;

import christmas.model.orders.Menu;
import java.util.stream.Stream;

public enum Gift {
    CHAMPAGNE_GIFT(CHAMPAGNE, 1, 120_000),
    NO_GIFT(NO_MENU, 0, 0);

    private final Menu menu;
    private final int count;
    private final int limit;

    Gift(Menu menu, int count, int price) {
        this.menu = menu;
        this.count = count;
        this.limit = price;
    }

    public static Gift decideGiftBy(int totalPrice) {
        return Stream.of(Gift.values())
                .filter(g -> totalPrice >= g.limit)
                .findFirst()
                .orElse(NO_GIFT);
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return menu.price() * count;
    }

    @Override
    public String toString() {
        return menu.getName();
    }

}
