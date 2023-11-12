package christmas.model;

import java.time.LocalDate;

public class Today {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
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
        return (day >= 1 && day <= 5) || day == 7;
    }

    public boolean isWeekEndEvent() {
        return day == 5 || day == 6;
    }

    public boolean isSpecialEvent() {
        return day == 7 || date == 25;
    }

    private void validate(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }

}
