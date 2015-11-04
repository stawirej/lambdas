import java.util.function.BinaryOperator;

public class Lambdas {

    public static void main(final String[] args) {
        final BinaryOperator<Integer> add = (a, b) -> a + b;
    }
}
