package christmas.model.order;

import static christmas.constants.ErrorMessage.ORDER_FORMAT_ERROR;
import static christmas.model.order.Category.DESSERT;
import static christmas.model.order.Category.DRINK;
import static christmas.model.order.Category.MAIN_MENU;

import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateOnlyDrinks(orders);
        validateTotalOrderCount(orders);
        this.orders = orders;
    }

    private void validateOnlyDrinks(List<Order> orders) {
        if(orders.stream()
                .allMatch(o -> o.isMenuInCategory(DRINK))) {
            throw new IllegalArgumentException(ORDER_FORMAT_ERROR);
        }
    }

    private void validateTotalOrderCount(List<Order> orders) {
        if(orders.stream().mapToInt(Order::getCount).sum() > 20) {
            throw new IllegalArgumentException(ORDER_FORMAT_ERROR);
        }
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getNumberOfDessert() {
        return orders.stream()
                .filter(o -> o.isMenuInCategory(DESSERT))
                .mapToInt(Order::getCount)
                .sum();
    }

    public int getNumberOfMainMenu() {
        return orders.stream()
                .filter(o -> o.isMenuInCategory(MAIN_MENU))
                .mapToInt(Order::getCount)
                .sum();
    }

    public boolean noBenefit() {
        return getTotalPrice() < 10_000;
    }
}
