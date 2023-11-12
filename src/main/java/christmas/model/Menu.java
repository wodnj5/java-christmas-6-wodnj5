package christmas.model;

public enum Menu {

    BUTTON_MUSH_ROOM_SOUP("양송이수프 ", 1, 6_000),
    TAPAS("타파스", 1, 5_500),
    CAESAR_SALAD("시저샐러드", 1, 8_000),
    T_BONE_STEAK("티본스테이크", 2, 55_000),
    BARBECUE_RIBS("바비큐립", 2, 54_000),
    SEAFOOD_PASTA("해산물파스타", 2, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 2, 25_000),
    CHOCOLATE_CAKE("초코케이크", 3, 15_000),
    ICE_CREAM("아이스크림", 3, 5_000),
    ZERO_COKE("제로콜라", 4, 3_000),
    RED_WINE("레드와인", 4, 60_000),
    CHAMPAGNE("샴페인", 4, 25_000);

    private final String name;
    private final int type;
    private final int price;

    Menu(String name, int type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public static Menu findMenuByName(String name) {
        for(Menu menu : Menu.values()) {
            if(menu.nameEquals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean typeEquals(int type) {
        return this.type == type;
    }

    public boolean nameEquals(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
