# ModernJava

Live demos for the knowledge session *Modern Java* (from Java 8 to Java 25). Each slide with
code has its own class with a `main` method, so you can run it from the IDE or with Gradle. The
modern code from the slides is in the class as it is on the slide, with only enough around it to
run.

Requires JDK 25. The Gradle wrapper (9.9.1) uses a JDK 25 toolchain and finds it automatically
(for example in `~/.jdks`).

## Run

```
./gradlew run -q                        # list the demos
./gradlew run -q --args=records         # run one demo
./gradlew run -q --args=all             # run all demos in slide order
./gradlew test                          # JUnit 6 + Mockito 5 tests
java scripts/Hello.java                 # single-file source launch, no build
```

## Slides and demos

| Slide | Demo | Class |
|---|---|---|
| Text blocks and string improvements | `textblocks` | `TextBlocksDemo` |
| Local variable type inference | `var` | `VarDemo` |
| Records for data classes | `records` | `RecordsDemo` |
| Sealed classes for closed hierarchies | `sealed` | `SealedDemo`, `domain/Result`, `Ok`, `Failed` |
| Pattern matching in switch | `switch` | `SwitchPatternDemo` |
| Pattern matching to reduce casting | `instanceof` | `InstanceofDemo` |
| Unnamed patterns for cleaner code | `unnamed` | `UnnamedDemo` |
| Unmodifiable and sequenced collections | `collections` | `CollectionsDemo` |
| Stream API and gatherers | `streams` | `StreamsDemo` |
| HTTP Client API | `http` | `HttpClientDemo` (starts a local server, works offline) |
| Tooling: `java Foo.java`, compact source files | – | `scripts/Hello.java` |
| Tooling: helpful NullPointerExceptions | `npe` | `HelpfulNpeDemo` |
| Also in the box: virtual threads | `virtualthreads` | `VirtualThreadsDemo` |
| Also in the box: scoped values | `scopedvalues` | `ScopedValuesDemo` |
| Frameworks that need more than Java 8 | – | `src/test/java/modernjava/DemoTest` |

## Tips for the live demo

- `VarDemo` and `SealedDemo` have a commented-out line that does not compile. Remove the `//`
  to show the compile error.
- In `SwitchPatternDemo`, comment out one `case` to show that the switch is no longer
  exhaustive.
- `jshell` is a good way to try `List.of(...).add(...)` or `"-".repeat(20)` interactively.
