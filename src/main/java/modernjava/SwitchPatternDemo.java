package modernjava;

import java.util.List;

import modernjava.domain.Employee;
import modernjava.domain.Failed;
import modernjava.domain.Ok;
import modernjava.domain.Result;

/** Slide "Pattern matching in switch" (Java 21). */
public class SwitchPatternDemo {

    static String describe(Result r) {
        return switch (r) {
            case Ok(var e) -> "found " + e.name();
            case Failed(var s, var m)
                when s == 404 -> "not found";
            case Failed f -> "error " + f.status();
        };  // exhaustive: no default needed
    }

    public static void main(String[] args) {
        List<Result> results = List.of(
            new Ok(new Employee(1, "Ada", "Planning")),
            new Failed(404, "Not Found"),
            new Failed(500, "Internal Server Error"));

        for (Result r : results) {
            System.out.println(r + " -> " + describe(r));
        }
    }
}
