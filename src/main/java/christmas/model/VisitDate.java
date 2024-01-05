package christmas.model;

import static christmas.constants.ErrorMessage.DATE_FORMAT_ERROR;

import java.time.LocalDate;

public class VisitDate {
    private final LocalDate localDate;

    public VisitDate(int date) {
        validateDateRange(date);
        this.localDate = LocalDate.of(2023, 12, date);
    }

    private void validateDateRange(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException(DATE_FORMAT_ERROR);
        }
    }

    public int getDate() {
        return localDate.getDayOfMonth();
    }

    private int getDay() {
        return localDate.getDayOfWeek().getValue();
    }

    public boolean isDDayEvent() {
        return getDate() <= 25;
    }

    public boolean isWeekDayEvent() {
        int day = getDay();
        return (day >= 1 && day < 5) || day == 7;
    }

    public boolean isWeekEndEvent() {
        int day = getDay();
        return day == 5 || day == 6;
    }

    public boolean isSpecialEvent() {
        return getDay() == 7 || getDate() == 25;
    }
}
