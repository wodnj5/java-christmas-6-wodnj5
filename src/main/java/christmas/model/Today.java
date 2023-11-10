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

    private void validate(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }

}
