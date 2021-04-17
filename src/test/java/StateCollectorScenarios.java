import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class StateCollectorScenarios {

    @Test
    public void shouldGroupByValuesCount() {
        // Given
        final List<CustomPair> input = new ArrayList<>();
        final CustomPair a = CustomPair.create('a', 1, 2);
        final CustomPair b = CustomPair.create('b', 3, 4);
        final CustomPair c = CustomPair.create('c', 5, 6);
        input.add(a);
        input.add(b);
        input.add(c);

        // When
        final Grouper groupedPairs = input.stream().collect(Grouper::new, Grouper::accept, Grouper::andThen);
        final List<List<CustomPair>> output = groupedPairs.get();

        // Then
        then(output.size()).isEqualTo(2);
        then(output.get(0)).containsSequence(a, b).hasSize(2);
        then(output.get(1)).containsSequence(c).hasSize(1);
    }

    @Test
    public void shouldGroupByValuesCountWithCustomCollector() {
        // Given
        final List<CustomPair> input = new ArrayList<>();
        final CustomPair a = CustomPair.create('a', 1, 2);
        final CustomPair b = CustomPair.create('b', 3, 4);
        final CustomPair c = CustomPair.create('c', 5, 6);
        input.add(a);
        input.add(b);
        input.add(c);

        // When

        final List<List<CustomPair>> output = input.stream().collect(new CustomGroupingStateCollector());

        // Then
        then(output.size()).isEqualTo(2);
        then(output.get(0)).containsSequence(a, b).hasSize(2);
        then(output.get(1)).containsSequence(c).hasSize(1);
    }
}
