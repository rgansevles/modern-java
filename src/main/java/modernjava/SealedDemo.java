package modernjava;

import java.util.Arrays;

import modernjava.domain.Result;

/**
 * Slide "Sealed classes for closed hierarchies" (Java 17).
 * The hierarchy itself is in modernjava.domain: Result, Ok and Failed.
 */
public class SealedDemo {

    // Only Ok and Failed exist: try to add another one and the compiler refuses.
    // record Other() implements Result { }  

    public static void main(String[] args) {
        System.out.println("Result is sealed: " + Result.class.isSealed());
        System.out.println("Permitted subclasses: " + Arrays.stream(Result.class.getPermittedSubclasses())
            .map(Class::getSimpleName)
            .toList());
    }
}
