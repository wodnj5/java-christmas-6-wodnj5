package christmas.model.event;

import static christmas.model.order.Menu.CHAMPAGNE;
import static christmas.model.order.Menu.NO_MENU;

import christmas.model.order.Menu;
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

    public static Gift findGiftBy(int totalPrice) {
        return Stream.of(Gift.values())
                .filter(g -> totalPrice >= g.limit)
                .findFirst()
                .orElse(NO_GIFT);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return menu.getPrice() * count;
    }
}
