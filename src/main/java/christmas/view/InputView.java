package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    public int inputDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return validateDateFormat(Console.readLine());
    }

    public List<String> inputOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Arrays.stream(Console.readLine().split(","))
                .map(this::validateOrderFormat)
                .toList();
    }

    private int validateDateFormat(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private String validateOrderFormat(String input) {
        Matcher matcher = Pattern.compile("^.+-\\d+$").matcher(input);
        if(!matcher.matches()) {
            throw new IllegalArgumentException();
        }
        return input;
    }
}
