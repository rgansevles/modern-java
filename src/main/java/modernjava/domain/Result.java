package modernjava.domain;

/**
 * Slide "Sealed classes for closed hierarchies" (Java 17).
 * Only Ok and Failed may implement Result: the compiler knows the full list.
 */
public sealed interface Result
    permits Ok, Failed { }
