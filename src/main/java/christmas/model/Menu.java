package christmas.model;

public enum Menu {

    BUTTON_MUSH_ROOM_SOUP("양송이수프", MenuType.APPETIZER, 6_000),
    TAPAS("타파스", MenuType.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8_000),
    T_BONE_STEAK("티본스테이크", MenuType.MAIN_MENU, 55_000),
    BARBECUE_RIBS("바비큐립", MenuType.MAIN_MENU, 54_000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN_MENU, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN_MENU, 25_000),
    CHOCOLATE_CAKE("초코케이크", MenuType.DESSERT, 15_000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5_000),
    ZERO_COKE("제로콜라", MenuType.DRINK, 3_000),
    RED_WINE("레드와인", MenuType.DRINK, 60_000),
    CHAMPAGNE("샴페인", MenuType.DRINK, 25_000);

    private final String name;
    private final MenuType menuType;
    private final int price;

    Menu(String name, MenuType menuType, int price) {
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }

    public static Menu findMenu(String name) {
        for(Menu menu : Menu.values()) {
            if(menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean typeEquals(MenuType menuType) {
        return this.menuType == menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
