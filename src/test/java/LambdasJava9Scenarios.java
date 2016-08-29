import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.Lists;

public class LambdasJava9Scenarios {

    @Test
    public void shouldIterateWithStopCondition() {
        // When
        // Use IntStream.rangeClosed(0, 5) instead
        Stream //
                .iterate(0, i -> i < 5, i -> i + 1) //
                .limit(5) //
                .forEach(System.out::println);
    }

    @Test
    public void shouldIterateThroughIteratorLikeData() {
        // Given
        final StringTokenizer tokens = new StringTokenizer("A B C D");
        List<Object> letters = Lists.newArrayList();

        // When
        if (tokens.hasMoreElements()) {
            letters = //
                    Stream //
                            .iterate(tokens.nextElement(), //
                                    element -> tokens.hasMoreElements(), //
                                    element -> tokens.nextElement()) //
                            .collect(toList());
        }

        // Then
        // Will fail as last element is not returned
        then(letters).containsExactly("A", "B", "C", "D");
    }

    @Test
    public void shouldIterateThroughIterator() {
        // Given
        List<Object> letters = Lists.newArrayList("A", "B", "C", "D");
        final Iterator<Object> iterator = letters.iterator();

        // When
        // Use letters.stream() instead
        if (iterator.hasNext()) {
            letters = //
                    Stream //
                            .iterate(iterator.next(), //
                                    element -> iterator.hasNext(), //
                                    element -> iterator.next()) //
                            .collect(toList());
        }

        // Then
        // Will fail as last element is not returned
        then(letters).containsExactly("A", "B", "C", "D");
    }
}
