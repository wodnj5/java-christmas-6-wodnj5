package christmas.model;

import static christmas.constants.ErrorMessage.DATE_FORMAT_ERROR;

import java.time.DateTimeException;
import java.time.LocalDate;

public class VisitDate {
    private final static int YEAR = 2023;
    private final static int MONTH = 12;
    private final static int CHRISTMAS = 25;
    private final static int MONDAY = 1;
    private final static int FRIDAY = 5;
    private final static int SATURDAY = 6;
    private final static int SUNDAY = 7;

    private final LocalDate localDate;

    public VisitDate(int date) {
        try {
            this.localDate = LocalDate.of(YEAR, MONTH, date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(DATE_FORMAT_ERROR);
        }
    }

    public int getMonth() {
        return localDate.getMonth().getValue();
    }

    public int getDate() {
        return localDate.getDayOfMonth();
    }

    private int getDay() {
        return localDate.getDayOfWeek().getValue();
    }

    public boolean isDDayEvent() {
        return getDate() <= CHRISTMAS;
    }

    public boolean isWeekDayEvent() {
        int day = getDay();
        return (day >= MONDAY && day < FRIDAY) || day == SUNDAY;
    }

    public boolean isWeekEndEvent() {
        int day = getDay();
        return day == FRIDAY || day == SATURDAY;
    }

    public boolean isSpecialEvent() {
        return getDay() == SUNDAY || getDate() == CHRISTMAS;
    }
}
