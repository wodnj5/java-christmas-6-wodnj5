package christmas.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Order {

    private final Map<Menu, Integer> order;

    public Order(List<String> input) {
        order = new TreeMap<>();
        input.stream()
                .map(order -> order.split("-"))
                .forEach(menu -> validateDistinctMenu(findMenu(menu[0]), Integer.parseInt(menu[1])));
        validateOnlyDrinks();
    }

    public Set<Menu> keySet() {
        return order.keySet();
    }

    public int get(Menu menu) {
        return order.get(menu);
    }

    public int totalPrice() {
        return order.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * order.get(menu)).sum();
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
