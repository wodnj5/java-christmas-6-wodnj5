package christmas.model;

import static christmas.model.MenuType.DESSERT;
import static christmas.model.MenuType.DRINK;
import static christmas.model.MenuType.MAIN_MENU;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Orders {
    private final Map<Menu, Integer> orders;

    public Orders(List<String> input) {
        orders = new TreeMap<>();
        input.stream()
                .map(str -> str.split("-"))
                .forEach(menu -> addMenu(menu[0], Integer.parseInt(menu[1])));
        validateOrderCount();
        validateOnlyDrinks();
    }

    public int totalPrice() {
        return orders.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * orders.get(menu)).sum();
    }

    public int numberOfDessert() {
        return orders.keySet().stream()
                .filter(menu -> menu.typeEquals(DESSERT))
                .mapToInt(orders::get)
                .sum();
    }

    public int numberOfMainMenu() {
        return orders.keySet().stream()
                .filter(menu -> menu.typeEquals(MAIN_MENU))
                .mapToInt(orders::get)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        orders.keySet().forEach(menu -> sb.append(String.format("%s %d개\n", menu.getName(), orders.get(menu))));
        return sb.toString();
    }

    private void addMenu(String name, int count) {
        Menu menu = Menu.findMenu(name);
        orders.put(validateDistinctMenu(menu), count);
    }

    private Menu validateDistinctMenu(Menu menu) {
        if(orders.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        return menu;
    }

    private void validateOrderCount() {
        if(orders.keySet().stream()
                .mapToInt(orders::get)
                .sum() > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOnlyDrinks() {
        if(orders.keySet().stream()
                .allMatch(menu -> menu.typeEquals(DRINK))) {
            throw new IllegalArgumentException();
        }
    }
}
