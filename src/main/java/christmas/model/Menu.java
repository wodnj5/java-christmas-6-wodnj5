package christmas.model;

public enum Menu {

    ButtonMushRoomSoup("양송이수프 ", 1, 6_000),
    Tapas("타파스", 1, 5_500),
    CaesarSalad("시저샐러드", 1, 8_000),
    TBoneSteak("티본스테이크", 2, 55_000),
    BarbecueRibs("바베큐립", 2, 54_000),
    SeafoodPasta("해산물파스타", 2, 35_000),
    ChristmasPasta("크리스마스파스타", 2, 25_000),
    ChocolateCake("초코케이크", 3, 15_000),
    IceCream("아이스크림", 3, 5_000),
    ZeroCoke("제로콜라", 4, 3_000),
    RedWine("레드와인", 4, 60_000),
    Champagne("샴페인", 4, 25_000);

    private final String name;
    private final int type;
    private final int price;

    Menu(String name, int type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    public boolean checkType(int type) {
        return this.type == type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
