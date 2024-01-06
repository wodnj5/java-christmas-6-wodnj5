package christmas.model.event;

import java.util.stream.Stream;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NO_BADGE("없음", 0);

    private final String name;
    private final int discount;

    Badge(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }

    public static Badge findBadgeBy(int totalDiscount) {
        return Stream.of(Badge.values())
                .filter(b -> totalDiscount >= b.discount)
                .findFirst()
                .orElse(NO_BADGE);
    }

    public String getName() {
        return name;
    }
}
