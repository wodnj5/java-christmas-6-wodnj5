package christmas.model.orders;

import static christmas.model.orders.Menu.BARBECUE_RIBS;
import static christmas.model.orders.Menu.BUTTON_MUSH_ROOM_SOUP;
import static christmas.model.orders.Menu.CAESAR_SALAD;
import static christmas.model.orders.Menu.CHAMPAGNE;
import static christmas.model.orders.Menu.CHOCOLATE_CAKE;
import static christmas.model.orders.Menu.CHRISTMAS_PASTA;
import static christmas.model.orders.Menu.ICE_CREAM;
import static christmas.model.orders.Menu.RED_WINE;
import static christmas.model.orders.Menu.SEAFOOD_PASTA;
import static christmas.model.orders.Menu.TAPAS;
import static christmas.model.orders.Menu.T_BONE_STEAK;
import static christmas.model.orders.Menu.ZERO_COKE;

import java.util.List;

public enum Category {
    APPETIZER(List.of(BUTTON_MUSH_ROOM_SOUP, TAPAS, CAESAR_SALAD)),
    MAIN_MENU(List.of(T_BONE_STEAK, BARBECUE_RIBS, SEAFOOD_PASTA, CHRISTMAS_PASTA)),
    DESSERT(List.of(CHOCOLATE_CAKE, ICE_CREAM)),
    DRINK(List.of(ZERO_COKE, RED_WINE, CHAMPAGNE));

    private final List<Menu> menus;

    Category(List<Menu> menus) {
        this.menus = menus;
    }

    public boolean contains(Menu menu) {
        return menus.contains(menu);
    }
}
