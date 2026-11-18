// Slide "Tooling you get with the JDK": run a source file without a build (Java 11),
// written as a compact source file (Java 25): no class, no static, no String[] args.
//
//   java scripts/Hello.java
void main() {
    var name = IO.readln("Name? ");
    IO.println("Hello " + name);
}
