package christmas.view;

import christmas.model.Order;

public class OutputView {

    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrder(Order order) {
        System.out.println("<주문 메뉴>");
        order.keySet().forEach(menu -> System.out.printf(menu.getName() + " %d개\n", order.get(menu)));
    }

    public void printTotalPrice(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", order.totalPrice());
    }
}
