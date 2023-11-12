package christmas.model;

public enum Badge {

    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public static String classifyBadge(int totalDiscountPrice) {
        if(totalDiscountPrice >= 5_000 && totalDiscountPrice < 10_000) {
            return STAR.name;
        }
        if(totalDiscountPrice >= 10_000 && totalDiscountPrice < 20_000) {
            return TREE.name;
        }
        if(totalDiscountPrice >= 20_000) {
            return SANTA.name;
        }
        return NONE.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
