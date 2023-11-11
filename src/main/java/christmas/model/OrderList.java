package christmas.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderList {

    private final Map<Menu, Integer> orderList;

    public OrderList(List<String> input) {
        orderList = new TreeMap<>();
        input.stream()
                .map(order -> order.split("-"))
                .forEach(menu -> orderList.put(validateDistinctMenu(Menu.findMenu(menu[0])), Integer.parseInt(menu[1])));
        validateOnlyDrinks();
    }

    public int totalPrice() {
        return orderList.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * orderList.get(menu)).sum();
    }

    public String getTotalPrice() {
        return String.format("%,d원\n", totalPrice());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        orderList.keySet().forEach(menu -> sb.append(String.format("%s %d개\n", menu.getName(), orderList.get(menu))));
        return sb.toString();
    }

    private Menu validateDistinctMenu(Menu menu) {
        if(orderList.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        return menu;
    }

    private void validateOnlyDrinks() {
        if(orderList.keySet().stream()
                .allMatch(menu -> menu.typeEquals(4))) {
            throw new IllegalArgumentException();
        }
    }
}
