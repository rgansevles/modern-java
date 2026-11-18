package modernjava;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Slide "Unmodifiable and sequenced collections" (Java 9 / 10 / 21). */
public class CollectionsDemo {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(List.of("a", "b", "c"));
        List<String> list = List.of("first", "middle", "last");
        var linkedMap = new LinkedHashMap<String, Integer>();
        linkedMap.put("Mon", 8);
        linkedMap.put("Tue", 6);

        var days = List.of("Mon", "Tue", "Wed");
        var hours = Map.of("Mon", 8, "Tue", 6);
        var copy = List.copyOf(input);    // 10

        String first = list.getFirst();   // 21
        String last = list.getLast();
        for (var d : days.reversed()) { System.out.println(d); }
        System.out.println(linkedMap.firstEntry());

        System.out.println(first + " / " + last + " / " + hours.get("Mon"));

        input.add("d");
        System.out.println("copy is a real copy: " + copy);

        try {
            days.add("Thu");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of is immutable: add() throws");
        }
        try {
            List.of("Mon", null);
        } catch (NullPointerException e) {
            System.out.println("List.of does not allow null");
        }
    }
}
