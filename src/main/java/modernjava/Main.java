package modernjava;

import java.util.List;

/**
 * Runs the demos in the order of the presentation.
 *
 * <pre>
 * ./gradlew run -q                      list the demos
 * ./gradlew run -q --args=records       run one demo
 * ./gradlew run -q --args=all           run all demos
 * </pre>
 */
public class Main {

    @FunctionalInterface
    interface DemoMain {
        void main(String[] args) throws Exception;
    }

    record Demo(String name, String slide, DemoMain main) { }

    static final List<Demo> DEMOS = List.of(
        new Demo("textblocks", "Text blocks and string improvements", TextBlocksDemo::main),
        new Demo("var", "Local variable type inference", VarDemo::main),
        new Demo("records", "Records for data classes", RecordsDemo::main),
        new Demo("sealed", "Sealed classes for closed hierarchies", SealedDemo::main),
        new Demo("switch", "Pattern matching in switch", SwitchPatternDemo::main),
        new Demo("instanceof", "Pattern matching to reduce casting", InstanceofDemo::main),
        new Demo("unnamed", "Unnamed patterns for cleaner code", UnnamedDemo::main),
        new Demo("collections", "Unmodifiable and sequenced collections", CollectionsDemo::main),
        new Demo("streams", "Stream API and gatherers", StreamsDemo::main),
        new Demo("http", "HTTP Client API", HttpClientDemo::main),
        new Demo("npe", "Tooling: helpful NullPointerExceptions", HelpfulNpeDemo::main),
        new Demo("virtualthreads", "Also in the box: virtual threads", VirtualThreadsDemo::main));

    static void main(String[] args) throws Exception {
        String choice = args.length > 0 ? args[0] : "";
        if (choice.isBlank()) {
            System.out.println("Usage: ./gradlew run -q --args=<demo>   (or --args=all)\n");
            DEMOS.forEach(d -> System.out.printf("  %-15s %s%n", d.name(), d.slide()));
            return;
        }
        boolean found = false;
        for (Demo demo : DEMOS) {
            if (choice.equals("all") || choice.equals(demo.name())) {
                System.out.println("=== " + demo.slide() + " ===");
                demo.main().main(new String[0]);
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Unknown demo '" + choice + "'. Run without arguments for the list.");
        }
    }
}
