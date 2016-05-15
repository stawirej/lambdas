import static org.assertj.core.api.BDDAssertions.then;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

public class LambdasTalentRoadScenarios {

    private static boolean isEven(final int number) {
        return number % 2 == 0;
    }

    @Test
    public void shouldCountImperativeStyle() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        int evenCounter = 0;
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i) % 2 == 0) {
                evenCounter++;
            }
        }

        // Then
        then(evenCounter).isEqualTo(5);
    }

    /*
     * Write shouldCountImperativeStyle scenario in declarative style.
     */
    @Test
    public void shouldCountByStreamsDeclarativeStyle() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final long evenCounter = 0;

        // Then
        then(evenCounter).isEqualTo(5);

    }

    @Test
    public void shouldCollectToSet() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Set<Integer> set = null;

        // Then
        then(set).isNotNull().isInstanceOf(Set.class).hasSize(10);
    }

    /**
     * HINT: Use map.
     */
    @Test
    public void shouldMultiplyBy10() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> multipliedNumbers = null;

        // Then
        then(multipliedNumbers).isNotNull().containsExactly(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
    }

    @Test
    public void shouldCheckIfStreamContainsValue2() {
        // Given
        final Stream<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

        // When
        final boolean matched = false;

        // Then
        then(matched).isTrue();
    }

    @Test
    public void shouldFilterValuesDividedBy2And3() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> filtered = null;

        // Then
        then(filtered).isNotNull().containsExactly(6);
    }

    @Test
    public void shouldFlatToOneCollection() {
        // Given
        final List<Integer> tableA = Lists.newArrayList(1, 2, 3, 4, 5);
        final List<Integer> tableB = Lists.newArrayList(6, 7, 8, 9, 10);

        // When
        final List<Integer> flattened = null;

        // Then
        then(flattened).isNotNull().containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    /**
     * HINT: To get stream from table use Arrays.stream().
     */
    @Test
    public void shouldCalculateTableElementsSumUsingReduce() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = 0;

        // Then
        then(sum).isEqualTo(55);
    }

    @Test
    public void shouldUseSpecializedStreamToGetTableElementsSum() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = 0;

        // Then
        then(sum).isEqualTo(55);
    }

    @Test
    public void shouldUseSpecializedIntStreamToSelectValuesGreaterThan5() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final Set<Integer> numbers = null;

        // Then
        then(numbers).isNotNull().isInstanceOf(Set.class).hasSize(5).containsExactly(6, 7, 8, 9, 10);
    }

    @Test
    public void shouldGetEvenNumbersUsingReferenceMethod() {
        // Given
        final List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> evenNumbers = null;

        // Then
        then(evenNumbers).isNotNull().containsExactly(2, 4, 6, 8, 10);
    }

    /**
     * HINT: use map function
     */
    @Test
    public void shouldGetNamesUsingMethodReference() {
        // Given
        final List<Person> people = Lists.newArrayList(new Person("Brandon"), new Person("Jessica"), new Person("Marry"));

        // When
        final List<String> names = null;

        // Then
        then(names).isNotNull().contains("Brandon", "Jessica", "Marry");
    }

    @Test
    public void shouldCreateOrderedSet() {
        // Given
        final List<Integer> table = Lists.newArrayList(6, 7, 8, 9, 10, 1, 2, 3, 4, 5);

        // When
        final Set<Integer> ordered = null;

        // Then
        then(ordered).isNotNull().containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void shouldPickCustomTreeSetCollector() {
        // Given
        final List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Collection<Integer> set = null;

        // Then
        then(set).isNotNull().isInstanceOf(TreeSet.class);
    }

    @Test
    public void shouldPickCustomLinkedListCollector() {
        // Given
        final List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Collection<Integer> list = null;

        // Then
        then(list).isNotNull().isInstanceOf(LinkedList.class);
    }

    @Test
    public void shouldCollectSummaryStatistics() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final IntSummaryStatistics statistics = null;

        // Then
        then(statistics.getAverage()).isEqualTo(5.5);
        then(statistics.getSum()).isEqualTo(55);
        then(statistics.getMax()).isEqualTo(10);
    }

    @Test
    public void shouldSplitOnNumbersDividedBy2AndRest() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Map<Boolean, List<Integer>> partitioned = null;

        // Then
        then(partitioned.get(true)).containsExactly(2, 4, 6, 8, 10);
        then(partitioned.get(false)).containsExactly(1, 3, 5, 7, 9);
    }

    @Test
    public void shouldGroupAlbumsByArtistAsStrings() {
        // Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"));

        // When
        final Map<String, List<String>> albumsByMusician = null;
        // Then
        then(albumsByMusician.get("Hunter")).contains("Kingdom");
        then(albumsByMusician.get("Vader")).contains("Sothis", "De Profundis");
        then(albumsByMusician.get("Michael Jackson")).contains("Dangerous");
    }

    @Test
    public void shouldJoinUniqueArtistsIntoOneString() {
        // Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"));

        // When
        final String musicians = null;

        // Then
        then(musicians).isEqualTo("[Vader,Hunter,Michael Jackson]");
    }

    @Test
    public void shouldFilterCommonValuesFromBothCollections() {
        // Given
        final Collection<String> firstCollection = Lists.newArrayList("a", "b", "c", "d");
        final Collection<String> secondCollection = Lists.newArrayList("a", "b", "x", "y");

        // When
        final List<String> collect = null;

        // Then
        then(collect).isNotNull().containsExactly("a", "b");
    }

    @Test
    public void shouldCompareParallelSpeedOfDifferentImplementations() {
        // Given
        final List<Integer> numbers =
            IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(ArrayList::new));

        // final TreeSet<Integer> numbers =
        // IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(TreeSet::new));

        // final LinkedList<Integer> numbers =
        // IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(LinkedList::new));

        // When
        final Stopwatch timer = Stopwatch.createStarted();

        final int sum = numbers.parallelStream().mapToInt(value -> value).sum();

        timer.stop();

        // Then
        System.out.println("timer = " + timer);
    }
}
