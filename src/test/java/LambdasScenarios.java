import static org.fest.assertions.api.Assertions.assertThat;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import org.junit.Test;

public class LambdasScenarios {

    @Test
    public void shouldShowLambdasExpressionsForms() {
        final Runnable runnable = () -> System.out.println("Hello TTDay");
        runnable.run();

        final ActionListener listener = event -> System.out.println("Event!!!");
        listener.actionPerformed(null);

        final Runnable multiStatement = () -> {
            System.out.println("Hello");
            System.out.println("TTDay");
        };
        multiStatement.run();

        final BinaryOperator<Integer> add = (a, b) -> a + b;
        final LongBinaryOperator addLong = (a, b) -> a + b;
        final int i = add.apply(1, 2);
        System.out.println("i = " + i);
        final long j = addLong.applyAsLong(2L, 3L);
        System.out.println("j = " + j);
    }

    @Test
    public void shouldCalculateTableElementsSumUsingReduce() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int tableSum = IntStream.range(0, table.length).reduce(0, (sum, i) -> sum + table[i]);

        // Then
        assertThat(tableSum).isEqualTo(55);
    }

    @Test
    public void shouldUseStreamsToGetTableElementsSum() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = IntStream.of(table).sum();

        // Then
        assertThat(sum).isEqualTo(55);
    }
}
