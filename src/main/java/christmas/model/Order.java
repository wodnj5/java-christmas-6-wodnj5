package christmas.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Order {
    private final Map<Menu, Integer> order;

    public Order(List<String> input) {
        order = new TreeMap<>();
        input.stream()
                .map(order -> order.split("-"))
                .forEach(menu -> addMenu(menu[0], Integer.parseInt(menu[1])));
        validateOrderCount();
        validateOnlyDrinks();
    }

    public int totalPrice() {
        return order.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * order.get(menu)).sum();
    }

    public int numberOfDessert() {
        return order.keySet().stream()
                .filter(menu -> menu.typeEquals(3))
                .mapToInt(order::get)
                .sum();
    }

    public int numberOfMainMenu() {
        return order.keySet().stream()
                .filter(menu -> menu.typeEquals(2))
                .mapToInt(order::get)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        order.keySet().forEach(menu -> sb.append(String.format("%s %d개\n", menu.getName(), order.get(menu))));
        return sb.toString();
    }

    private void addMenu(String name, int count) {
        Menu menu = Menu.findMenuByName(name);
        order.put(validateDistinctMenu(menu), count);
    }

    private Menu validateDistinctMenu(Menu menu) {
        if(order.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        return menu;
    }

    private void validateOrderCount() {
        if(order.keySet().stream()
                .mapToInt(order::get)
                .sum() > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOnlyDrinks() {
        if(order.keySet().stream()
                .allMatch(menu -> menu.typeEquals(4))) {
            throw new IllegalArgumentException();
        }
    }
}
