package modernjava;

import modernjava.domain.Employee;

/** Slide "Tooling you get with the JDK": helpful NullPointerExceptions (Java 14). */
public class HelpfulNpeDemo {

    public static void main(String[] args) {
        Employee employee = new Employee(1, "Ada", null);
        try {
            System.out.println(employee.dept().toUpperCase());
        } catch (NullPointerException e) {
            // Java 8: just "NullPointerException". Now the message names what was null.
            System.out.println(e.getMessage());
        }
    }
}
