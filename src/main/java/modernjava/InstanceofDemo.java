package modernjava;

import java.time.LocalDate;

import modernjava.domain.Employee;
import modernjava.domain.Ok;
import modernjava.domain.Result;

/** Slide "Pattern matching to reduce casting" (Java 16 / 21). */
public class InstanceofDemo {

    /** A normal class (not a record), to show equals() with a pattern. */
    static final class Shift {
        private final LocalDate date;

        Shift(LocalDate date) {
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Shift other
                && date.equals(other.date);
        }

        @Override
        public int hashCode() {
            return date.hashCode();
        }
    }

    static void use(Object value) {
        System.out.println("use(" + value + ")");
    }

    public static void main(String[] args) {
        Object obj = "hello";
        if (obj instanceof String s
                && !s.isEmpty()) {
            use(s);
        }

        LocalDate today = LocalDate.now();
        System.out.println("equals: " + new Shift(today).equals(new Shift(today)));

        Result r = new Ok(new Employee(1, "Ada", "Planning"));
        // record pattern (21)
        if (r instanceof Ok(var e)) use(e);
    }
}
