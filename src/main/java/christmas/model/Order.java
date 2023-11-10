package christmas.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Menu, Integer> order;

    public Order(String[] orders) {
        order = new HashMap<>();
        Arrays.stream(orders)
                .map(order -> order.split("-"))
                .forEach(menu -> order.put(validateMenu(menu[0]), Integer.parseInt(menu[1])));
        validateOnlyDrinks();
    }

    private Menu validateMenu(String name) {
        for(Menu menu : Menu.values()) {
            if(menu.checkName(name)) {
                return validateDistinctMenu(menu);
            }
        }
        throw new IllegalArgumentException();
    }

    private Menu validateDistinctMenu(Menu menu) {
        if(order.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        return menu;
    }

    private void validateOnlyDrinks() {
        if(order.keySet().stream()
                .filter(menu -> menu.checkType(4))
                .count() == order.size()) {
            throw new IllegalArgumentException();
        }
    }
}
