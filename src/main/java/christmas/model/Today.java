package christmas.model;

import java.time.LocalDate;

public class Today {
    private static final int MONDAY = 1;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS = 25;
    private final int date;
    private final int day;

    public Today(int date) {
        validate(date);
        this.date = date;
        day = LocalDate.of(YEAR, MONTH, date)
                .getDayOfWeek()
                .getValue();
    }

    public int getDate() {
        return date;
    }

    public boolean isDDayEvent() {
        return date <= 25;
    }

    public boolean isWeekDayEvent() {
        return (day >= MONDAY && day < FRIDAY) || day == SUNDAY;
    }

    public boolean isWeekEndEvent() {
        return day == FRIDAY || day == SATURDAY;
    }

    public boolean isSpecialEvent() {
        return day == SUNDAY || date == CHRISTMAS;
    }

    private void validate(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }

}
