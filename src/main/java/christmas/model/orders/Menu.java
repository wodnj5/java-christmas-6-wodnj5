package christmas.model.orders;

import java.util.stream.Stream;

public enum Menu {
    BUTTON_MUSH_ROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드",  8_000),
    T_BONE_STEAK("티본스테이크",  55_000),
    BARBECUE_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000),
    NO_MENU("없음", 0);

    private final String name;
    private final int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Menu decideMenuBy(String name) {
        return Stream.of(Menu.values())
                .filter(m -> m.name.equals(name))
                .findFirst()
                .orElse(NO_MENU);
    }

    public String getName() {
        return name;
    }

    public int price() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
