package christmas.view;

import static christmas.constants.ErrorMessage.DATE_FORMAT_ERROR;
import static christmas.constants.ErrorMessage.ORDER_FORMAT_ERROR;

import camp.nextstep.edu.missionutils.Console;
import java.util.stream.Stream;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n");
        return validateNumberFormat(Console.readLine());
    }

    public String[] readOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n");
        return validateOrderFormat(Console.readLine().split(","));
    }

    private String[] validateOrderFormat(String[] input) {
        try {
            Stream.of(input).forEach(s -> Integer.parseInt(s.split("-")[1]));
            return input;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_FORMAT_ERROR);
        }
    }

    private int validateNumberFormat(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_FORMAT_ERROR);
        }
    }
}
