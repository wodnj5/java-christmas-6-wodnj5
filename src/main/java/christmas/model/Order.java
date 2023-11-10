package christmas.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> order;

    public Order(List<String> input) {
        order = new HashMap<>();
        input.stream()
                .map(order -> order.split("-"))
                .forEach(menu -> validateDistinctMenu(findMenu(menu[0]), Integer.parseInt(menu[1])));
        validateOnlyDrinks();
    }

    private Menu findMenu(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.checkName(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private void validateDistinctMenu(Menu menu, int count) {
        if(order.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        order.put(menu, count);
    }

    private void validateOnlyDrinks() {
        if(order.keySet().stream()
                .allMatch(menu -> menu.checkType(4))) {
            throw new IllegalArgumentException();
        }
    }
}
