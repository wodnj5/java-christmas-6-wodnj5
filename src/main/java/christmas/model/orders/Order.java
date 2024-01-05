package christmas.model.orders;

import static christmas.constants.ErrorMessage.ORDER_FORMAT_ERROR;
import static christmas.model.orders.Menu.NO_MENU;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(String name, int count) {
        Menu menu = Menu.decideMenuBy(name);
        validateMenu(menu);
        this.menu = menu;
        validateCount(count);
        this.count = count;
    }

    private void validateMenu(Menu menu) {
        if(menu.equals(NO_MENU)) {
            throw new IllegalArgumentException(ORDER_FORMAT_ERROR);
        }
    }

    private void validateCount(int count) {
        if(count < 1) {
            throw new IllegalArgumentException(ORDER_FORMAT_ERROR);
        }
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return menu.price() * count;
    }

    public boolean isMenuInCategory(Category category) {
        return category.contains(menu);
    }

    @Override
    public String toString() {
        return String.format("%s %d개\n", menu, count);
    }
}
