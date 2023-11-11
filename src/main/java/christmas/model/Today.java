package christmas.model;

import java.time.LocalDate;

public class Today {

    private final int date;
    private final int day;

    public Today(int date) {
        validate(date);
        this.date = date;
        day = LocalDate.of(2023, 12, date)
                .getDayOfWeek()
                .getValue();
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

    public int calculateDDayEvent() {
        return 1000 + (date - 1) * 100;
    }

    private void validate(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }

}
