package modernjava;

/** Slide "Text blocks and string improvements" (Java 11 / 15). */
public class TextBlocksDemo {

    public static void main(String[] args) {
        int id = 42;
        String name = "Ada Lovelace";

        String json = """
            {
              "id": %d,
              "name": "%s"
            }
            """.formatted(id, name);          // 15
        System.out.println(json);

        String s = "   ";
        if (s == null || s.isBlank()) {    // 11
            System.out.println("s is blank");
        }

        String line = "-".repeat(20);      // 11
        System.out.println(line);

        String text = "  first line  \n  second line \n";
        text.lines().map(String::strip)    // 11
            .forEach(l -> System.out.println("[" + l + "]"));


        String csvContent = """
            Mandantengruppe;MAN;AK;Pnr;Vertragsnummer;Nachname;Vorname;Titel;Geschlecht;Geburtsdatum;Vertragsbeginn;Vertragsende;Historisches Ab-Datum;Historisches Bis-Datum
            Driebergen;62;030;001234;1;Hagen;Nina;;Weiblich;07.07.1974;01.10.2018;;01.01.1970;31.01.2099
            """;
    }
}
