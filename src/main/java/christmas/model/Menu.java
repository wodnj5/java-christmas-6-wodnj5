package christmas.model;

import static christmas.model.Type.APPETIZER;
import static christmas.model.Type.DESSERT;
import static christmas.model.Type.DRINK;
import static christmas.model.Type.MAIN_MENU;

public enum Menu {

    BUTTON_MUSH_ROOM_SOUP("양송이수프", APPETIZER, 6_000),
    TAPAS("타파스", APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", APPETIZER, 8_000),
    T_BONE_STEAK("티본스테이크", MAIN_MENU, 55_000),
    BARBECUE_RIBS("바비큐립", MAIN_MENU, 54_000),
    SEAFOOD_PASTA("해산물파스타", MAIN_MENU, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", MAIN_MENU, 25_000),
    CHOCOLATE_CAKE("초코케이크", DESSERT, 15_000),
    ICE_CREAM("아이스크림", DESSERT, 5_000),
    ZERO_COKE("제로콜라", DRINK, 3_000),
    RED_WINE("레드와인", DRINK, 60_000),
    CHAMPAGNE("샴페인", DRINK, 25_000);

    private final String name;
    private final Type type;
    private final int price;

    Menu(String name, Type type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public static Menu findMenuByName(String name) {
        for(Menu menu : Menu.values()) {
            if(menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean typeEquals(Type type) {
        return this.type == type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
