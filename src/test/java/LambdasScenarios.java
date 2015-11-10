import static org.fest.assertions.api.Assertions.assertThat;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.fest.util.Lists;
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
    public void streams() {
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // int evenCounter = 0;
        // for (int i = 0; i < table.size(); i++) {
        // if (table.get(i) % 2 == 0) {
        // evenCounter++;
        // }
        // }

        final long evenCounter = table.stream().filter(x -> x % 2 == 0).count();
        System.out.println("evenCounter = " + evenCounter);
    }

    @Test
    public void shouldCollect() {
        // When
        final Set<Integer> table = Stream.of(1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 10).collect(Collectors.toSet());

        // Then
        System.out.println("table = " + table);
    }

    @Test
    public void shouldMap() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> mapped = table.stream().map(x -> x * 10).collect(Collectors.toList());

        // Then
        System.out.println("mapped = " + mapped);
    }

    @Test
    public void shouldFilter() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> filtered =
                table.stream().filter(x -> x % 2 == 0).filter(x -> x % 3 == 0).collect(Collectors.toList());

        // Then
        System.out.println("filtered = " + filtered);
    }

    @Test
    public void shouldDoSimpleFiltering() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final boolean matched = table.stream().anyMatch(x -> x == 2);
        System.out.println("matched = " + matched);
    }

    @Test
    public void shouldCalculateTableElementsSumUsingReduce() {
        // Given
        final int[] table = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int tableSum = IntStream.range(0, table.length).reduce(0, (sum, i) -> sum + table[i]);

        // Then
        assertThat(tableSum).isEqualTo(55);
    }

    @Test
    public void shouldUseStreamsToGetTableElementsSum() {
        // Given
        final int[] table = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = IntStream.of(table).sum();

        // Then
        assertThat(sum).isEqualTo(55);
    }
}
