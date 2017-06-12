import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class StateCollectorScenarios {

    @Test
    public void shouldGroupByValuesCount(){
        // Given
        int idLimit = 4;
        List<CustomPair> input = new ArrayList<>();
        CustomPair a = CustomPair.create('a', 1, 2);
        CustomPair b = CustomPair.create('b', 3, 4);
        CustomPair c = CustomPair.create('c', 5, 6);
        input.add(a);
        input.add(b);
        input.add(c);

        // When
        Grouper groupedPairs = input.stream().collect(Grouper::new, Grouper::accept, Grouper::andThen);
        List<List<CustomPair>> output = groupedPairs.get();

        // Then
        then(output.size()).isEqualTo(2);
        then(output.get(0).size()).isEqualTo(2);
        then(output.get(0)).containsSequence(a, b);
        then(output.get(1).size()).isEqualTo(1);
        then(output.get(1)).containsSequence(c);
    }
}
