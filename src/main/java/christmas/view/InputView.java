package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Order;
import christmas.model.Today;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    public Today inputDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        try {
            return new Today(validateDateFormat(Console.readLine()));
        } catch (IllegalArgumentException e) {
            return inputDate();
        }
    }

    public Order inputOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String[] order = Console.readLine().split(",");
        try {
            validateOrders(order);
            return new Order(order);
        } catch (IllegalArgumentException e) {
            return inputOrders();
        }
    }

    private int validateDateFormat(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOrders(String[] orders) {
        for (String order : orders) {
            validateOrderFormat(order);
        }
    }

    private String validateOrderFormat(String input) {
        String regex = "^.+-\\d+$";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if(!matcher.matches()) {
            throw new IllegalArgumentException();
        }
        return input;
    }
}
