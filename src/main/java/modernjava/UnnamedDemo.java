package modernjava;

import java.util.Map;

import modernjava.domain.Failed;
import modernjava.domain.Result;

/** Slide "Unnamed patterns for cleaner code" (Java 22). */
public class UnnamedDemo {

    static int parse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException _) {
            return 0;
        }
    }

    static void print(Object value) {
        System.out.println(value);
    }

    static void log(int status) {
        System.out.println("status " + status);
    }

    public static void main(String[] args) {
        System.out.println(parse("12") + " " + parse("twelve"));

        Map<String, Integer> map = Map.of("Mon", 8, "Tue", 6);
        map.forEach((_, value) -> print(value));

        Result r = new Failed(503, "Service Unavailable");
        if (r instanceof Failed(var s, _)) {
            log(s);
        }
    }
}
