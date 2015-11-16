import java.util.stream.Stream;

/* abstract */interface Print {

    default void printMessage() {
        System.out.println("Default message.");
    }
}

class Printer implements Print {

}

public class DefaultMethods {

    public static void main(final String[] args) {
        final Print printer = new Printer();
        printer.printMessage();

        Stream.of(1, 2, 3); // is interface
    }
}
