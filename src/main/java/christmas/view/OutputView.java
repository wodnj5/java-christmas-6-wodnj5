package christmas.view;

public class OutputView {

    public static void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printOrders(String output) {
        System.out.println("<주문 메뉴>");
        System.out.println(output);
    }

    public static void printEventPreviewStart() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printTotalPrice(int output) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n\n" ,output);
    }

    public static void printGift(String output) {
        System.out.println("<증정 메뉴>");
        System.out.println(output);
    }

    public static void printEvents(String output) {
        System.out.println("<혜택 내역>");
        System.out.println(output);
    }

    public static void printTotalDiscount(int output) {
        System.out.println("<총혜택 금액>");
        System.out.printf("-%,d원\n\n", output);
    }

    public static void printEstimatedPrice(int output) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n\n", output);
    }

    public static void printBadge(String output) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(output);
    }

    public static void printDateFormatError() {
        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public static void printOrderFormatError() {
        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
