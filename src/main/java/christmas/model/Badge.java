package christmas.model;

public enum Badge {

    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public static Badge classifyBadge(int totalDiscountPrice) {
        if(totalDiscountPrice >= 5_000 && totalDiscountPrice < 10_000) {
            return STAR;
        }
        if(totalDiscountPrice >= 10_000 && totalDiscountPrice < 20_000) {
            return TREE;
        }
        if(totalDiscountPrice >= 20_000) {
            return SANTA;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
