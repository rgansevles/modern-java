package modernjava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modernjava.domain.Employee;

/** Slide "Local variable type inference" (Java 10). */
public class VarDemo {

    public static void main(String[] args) throws IOException {
        var byDept =
            new HashMap<String, List<Employee>>();
        byDept.computeIfAbsent("Planning", _ -> new ArrayList<>()).add(new Employee(1, "Ada", "Planning"));
        byDept.computeIfAbsent("Planning", _ -> new ArrayList<>()).add(new Employee(2, "Linus", "Planning"));
        byDept.computeIfAbsent("HR", _ -> new ArrayList<>()).add(new Employee(3, "Grace", "HR"));

        for (var e : byDept.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue().size() + " employees");
        }

        Path path = Files.createTempFile("var-demo", ".txt");
        Files.writeString(path, "line 1\nline 2\n");
        try (var reader =
                Files.newBufferedReader(path)) {
            System.out.println("First line: " + reader.readLine());
        }
        Files.delete(path);

        // still static typing:
        // byDept = "text";   → compile error (remove the // to show it)
    }
}
