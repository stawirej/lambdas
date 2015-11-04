import static org.fest.assertions.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.Test;

public class LambdasScenarios {

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
