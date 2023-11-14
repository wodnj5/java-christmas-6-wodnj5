package christmas.domain;

public enum EventBadge {

    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String name;

    EventBadge(String name) {
        this.name = name;
    }

    public static String classifyEventBadge(int totalDiscount) {
        if(totalDiscount >= 5_000 && totalDiscount < 10_000) {
            return STAR.name;
        }
        if(totalDiscount >= 10_000 && totalDiscount < 20_000) {
            return TREE.name;
        }
        if(totalDiscount >= 20_000) {
            return SANTA.name;
        }
        return "없음";
    }
}
