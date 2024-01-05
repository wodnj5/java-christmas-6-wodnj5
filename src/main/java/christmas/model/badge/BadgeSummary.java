package christmas.model.badge;

import christmas.model.event.EventSummary;

public class BadgeSummary {
    private final Badge badge;
    public BadgeSummary(EventSummary eventSummary) {
        badge = Badge.decideBadgeBy(eventSummary.getTotalDiscount());
    }

    public String getContents() {
        return badge.getName();
    }
}
