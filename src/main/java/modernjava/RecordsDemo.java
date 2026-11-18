package modernjava;

import static java.time.Duration.ofHours;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/** Slide "Records for data classes" (Java 16). */
public class RecordsDemo {

    record Shift(LocalDate date,
                 Duration length) {

        Shift {          // compact constructor
            Objects.requireNonNull(date);
        }
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        var other = new Shift(today, ofHours(8));

        var shift = new Shift(today, ofHours(8));
        System.out.println(shift.date());
        System.out.println(shift.length());
        System.out.println(shift.equals(other));
        System.out.println(shift);

        try {
            new Shift(null, ofHours(8));
        } catch (NullPointerException e) {
            System.out.println("Compact constructor rejected null date");
        }
    }
}
