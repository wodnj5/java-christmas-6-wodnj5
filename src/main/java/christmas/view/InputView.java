package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public int inputDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return validateNumbersFormat(Console.readLine());
    }

    public List<String[]> inputOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Arrays.stream(Console.readLine().split(","))
                .map(this::validateOrderFormat)
                .toList();
    }

    private String[] validateOrderFormat(String input) {
        String[] menu = input.split("-");
        if(menu.length != 2) {
            throw new IllegalArgumentException();
        }
        menu[0] = menu[0].trim();
        menu[1] = menu[1].trim();
        validateNumbersFormat(menu[1]);
        return menu;
    }

    private int validateNumbersFormat(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
