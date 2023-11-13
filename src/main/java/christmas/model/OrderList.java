package christmas.model;

import static christmas.model.Type.DESSERT;
import static christmas.model.Type.DRINK;
import static christmas.model.Type.MAIN_MENU;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderList {
    private final Map<Menu, Integer> orderList;

    public OrderList(List<String> input) {
        orderList = new TreeMap<>();
        input.stream()
                .map(str -> str.split("-"))
                .forEach(menu -> addMenu(menu[0], Integer.parseInt(menu[1])));
        validateOrderCount();
        validateOnlyDrinks();
    }

    public int totalPrice() {
        return orderList.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * orderList.get(menu)).sum();
    }

    public int numberOfDessert() {
        return orderList.keySet().stream()
                .filter(menu -> menu.typeEquals(DESSERT))
                .mapToInt(orderList::get)
                .sum();
    }

    public int numberOfMainMenu() {
        return orderList.keySet().stream()
                .filter(menu -> menu.typeEquals(MAIN_MENU))
                .mapToInt(orderList::get)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        orderList.keySet().forEach(menu -> sb.append(String.format("%s %d개\n", menu.getName(), orderList.get(menu))));
        return sb.toString();
    }

    private void addMenu(String name, int count) {
        Menu menu = Menu.findMenu(name);
        orderList.put(validateDistinctMenu(menu), count);
    }

    private Menu validateDistinctMenu(Menu menu) {
        if(orderList.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        return menu;
    }

    private void validateOrderCount() {
        if(orderList.keySet().stream()
                .mapToInt(orderList::get)
                .sum() > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOnlyDrinks() {
        if(orderList.keySet().stream()
                .allMatch(menu -> menu.typeEquals(DRINK))) {
            throw new IllegalArgumentException();
        }
    }
}
