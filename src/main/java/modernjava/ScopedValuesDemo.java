package modernjava;

/** Slide "Also in the box": scoped values (Java 25), a safer ThreadLocal. */
public class ScopedValuesDemo {

    private static final ScopedValue<String> CUSTOMER = ScopedValue.newInstance();

    static void process() {
        System.out.println("processing for customer " + CUSTOMER.get());
    }

    public static void main(String[] args) {
        ScopedValue.where(CUSTOMER, "customer-a").run(ScopedValuesDemo::process);
        ScopedValue.where(CUSTOMER, "customer-b").run(ScopedValuesDemo::process);
        System.out.println("bound outside the scope: " + CUSTOMER.isBound());
    }
}
