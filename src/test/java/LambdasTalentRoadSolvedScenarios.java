import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.ArrayList;
import java.util.Arrays;
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


import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

public class LambdasTalentRoadSolvedScenarios {

    private static boolean isEven(final int number) {
        return number % 2 == 0;
    }

    @Test
    public void shouldCountEvenNumbersInImperativeStyle() {
        // Given
        final List<Integer> table =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

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

        // When
        final long evenCounter = IntStream.rangeClosed(1, 10)
            .filter(LambdasTalentRoadSolvedScenarios::isEven).count();

        // Then
        then(evenCounter).isEqualTo(5);

    }

    /**
     * HINT: When Given sections contains collection in comment you have to
     * generate required range using streams.
     */
    @Test
    public void shouldCollectToSet() {
        // Given
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // When
        final Set<Integer> set =
            IntStream.rangeClosed(1, 10).boxed().collect(toSet());

        // Then
        then(set).isNotNull().isInstanceOf(Set.class).hasSize(10);
    }

    /**
     * HINT: Use map.
     */
    @Test
    public void shouldMultiplyBy10() {
        // Given
        final List<Integer> table =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> multipliedNumbers =
            table.stream().map(v -> v * 10).collect(toList());

        // Then
        then(multipliedNumbers).isNotNull().containsExactly(10, 20, 30, 40, 50,
            60, 70, 80, 90, 100);
    }

    @Test
    public void shouldCheckIfStreamContainsValue2() {
        // Given
        final Stream<Integer> numbers =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream();

        // When
        final boolean matched = numbers.anyMatch(value -> value == 2);

        // Then
        then(matched).isTrue();
    }

    @Test
    public void shouldFilterValuesDividedBy2And3() {
        // Given
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // When
        final List<Integer> filtered = IntStream.rangeClosed(1, 10)
            .filter(LambdasTalentRoadSolvedScenarios::isEven).filter(v -> v % 3 == 0)
            .boxed().collect(toList());

        // Then
        then(filtered).isNotNull().containsExactly(6);
    }

    @Test
    public void shouldFlatToOneCollection() {
        // Given
        final List<Integer> tableA = Lists.newArrayList(1, 2, 3, 4, 5);
        final List<Integer> tableB = Lists.newArrayList(6, 7, 8, 9, 10);

        // When
        final List<Integer> flattened = Stream.of(tableA, tableB)
            .flatMap(table -> table.stream()).collect(toList());

        // Then
        then(flattened).isNotNull().containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9,
            10);
    }

    /**
     * HINT: To get stream from table use Arrays.stream().
     */
    @Test
    public void shouldCalculateTableElementsSumUsingReduce() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = Arrays.stream(table).reduce(0,
            (accumulator, element) -> accumulator + element);

        // Then
        then(sum).isEqualTo(55);
    }

    @Test
    public void shouldUseSpecializedStreamToGetTableElementsSum() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = Arrays.stream(table).sum();

        // Then
        then(sum).isEqualTo(55);
    }

    @Test
    public void shouldUseSpecializedIntStreamToSelectValuesGreaterThan5() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final Set<Integer> numbers =
            Arrays.stream(table).filter(v -> v > 5).boxed().collect(toSet());

        // Then
        then(numbers).isNotNull().isInstanceOf(Set.class).hasSize(5)
            .containsExactly(6, 7, 8, 9, 10);
    }

    @Test
    public void shouldGetEvenNumbersUsingReferenceMethod() {
        // Given
        final List<Integer> numbers =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final List<Integer> evenNumbers = numbers.stream()
            .filter(LambdasTalentRoadSolvedScenarios::isEven).collect(toList());

        // Then
        then(evenNumbers).isNotNull().containsExactly(2, 4, 6, 8, 10);
    }

    /**
     * HINT: use map function
     */
    @Test
    public void shouldGetNamesUsingMethodReference() {
        // Given
        final List<Person> people = Lists.newArrayList(
                new Person(1, "Brandon", 11),
                new Person(2, "Jessica", 22),
                new Person(3, "Marry", 33)
        );

        // When
        final List<String> names =
            people.stream().map(Person::getName).collect(toList());

        // Then
        then(names).isNotNull().contains("Brandon", "Jessica", "Marry");
    }

    @Test
    public void shouldCreateOrderedSet() {
        // Given
        final List<Integer> table =
            Lists.newArrayList(6, 7, 8, 9, 10, 1, 2, 3, 4, 5);

        // When
        final Set<Integer> ordered = table.stream().sorted().collect(Collectors.toSet());

        // Then
        then(ordered).isNotNull().containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9,
            10);
    }

    @Test
    public void shouldPickCustomTreeSetCollector() {
        // Given
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // When
        final Collection<Integer> set = IntStream.rangeClosed(1,10).boxed().collect(Collectors.toCollection(TreeSet::new));

        // Then
        then(set).isNotNull().isInstanceOf(TreeSet.class);
    }

    @Test
    public void shouldPickCustomLinkedListCollector() {
        // Given
        final List<Integer> numbers =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Collection<Integer> list = numbers.stream().collect(Collectors.toCollection(LinkedList::new));

        // Then
        then(list).isNotNull().isInstanceOf(LinkedList.class);
    }

    @Test
    public void shouldCollectSummaryStatistics() {
        // Given
        final List<Integer> table =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final IntSummaryStatistics statistics =
            table.stream().mapToInt(v -> v).summaryStatistics();

        // Then
        then(statistics.getAverage()).isEqualTo(5.5);
        then(statistics.getSum()).isEqualTo(55);
        then(statistics.getMax()).isEqualTo(10);
    }

    @Test
    public void shouldSplitOnEvenNumbers() {
        // Given
        final List<Integer> table =
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Map<Boolean, List<Integer>> partitioned = table.stream().collect(
            Collectors.partitioningBy(LambdasTalentRoadSolvedScenarios::isEven));

        System.out.println("partitioned = " + partitioned);
        // Then
        then(partitioned.get(true)).containsExactly(2, 4, 6, 8, 10);
        then(partitioned.get(false)).containsExactly(1, 3, 5, 7, 9);
    }

    @Test
    public void shouldGroupAlbumsByArtistAsStrings() {
        // Given
        final List<Album> albums = Lists.newArrayList(
            new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"),
            new Album("Vader", "Sothis"));

        // When
        final Map<String, List<String>> albumsByMusicians =
            albums.stream().collect(groupingBy(Album::getMusician,
                mapping(Album::getTitle, toList())));

        // Then
        then(albumsByMusicians.get("Hunter")).contains("Kingdom");
        then(albumsByMusicians.get("Vader")).contains("Sothis", "De Profundis");
        then(albumsByMusicians.get("Michael Jackson")).contains("Dangerous");
    }

    @Test
    public void shouldJoinUniqueArtistsIntoOneString() {
        // Given
        final List<Album> albums = Lists.newArrayList(
            new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"),
            new Album("Vader", "Sothis"));

        // When
        final String musicians =
            albums.stream().map(album -> album.getMusician()).distinct()
                .collect(Collectors.joining(",", "[", "]"));

        // Then
        then(musicians).isEqualTo("[Vader,Hunter,Michael Jackson]");
    }

    @Test
    public void shouldFilterCommonValuesFromBothCollections() {
        // Given
        final Collection<String> firstCollection =
            Lists.newArrayList("a", "b", "c", "d");
        final Collection<String> secondCollection =
            Lists.newArrayList("a", "b", "x", "y");

        // When
        final List<String> collect = firstCollection.stream()
            .filter(secondCollection::contains).collect(Collectors.toList());

        System.out.println("collect = " + collect);
        // Then
        then(collect).isNotNull().containsExactly("a", "b").hasSize(2);
    }

    @Test
    public void shouldCompareParallelSpeedOfDifferentImplementations() {
        // Given
        final List<Integer> numbers = IntStream.rangeClosed(0, 10_000_000)
            .boxed().collect(toCollection(ArrayList::new));

        // final TreeSet<Integer> numbers =
        // IntStream.rangeClosed(0,
        // 10_000_000).boxed().collect(Collectors.toCollection(TreeSet::new));

        // final LinkedList<Integer> numbers =
        // IntStream.rangeClosed(0,
        // 10_000_000).boxed().collect(Collectors.toCollection(LinkedList::new));

        // When
        final Stopwatch timer = Stopwatch.createStarted();

        final int sum = numbers.parallelStream().mapToInt(value -> value).sum();

        timer.stop();

        // Then
        System.out.println("timer = " + timer);
    }
}
