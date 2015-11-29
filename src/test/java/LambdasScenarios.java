import static org.fest.assertions.api.Assertions.assertThat;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.fest.util.Lists;
import org.junit.Test;

public class LambdasScenarios {

    private static boolean isEven(final int number) {
        return number % 2 == 0;
    }

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
        System.out.println("evenCounter = " + evenCounter);
    }

    @Test
    public void shouldCountByStreamsDeclarativeStyle() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final long evenCounter = table.stream().filter(x -> x % 2 == 0).count();

        // Then
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
    public void shouldDoSimpleFiltering() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final boolean matched = table.stream().anyMatch(x -> x == 2);
        System.out.println("matched = " + matched);
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
    public void shouldFlatMap() {
        // Given
        final List<Integer> tableA = Lists.newArrayList(1, 2, 3, 4, 5);
        final List<Integer> tableB = Lists.newArrayList(6, 7, 8, 9, 10);

        // When
        final List<Integer> flattened =
            Stream.of(tableA, tableB).flatMap(table -> table.stream()).collect(Collectors.toList());

        // Then
        System.out.println("flattened = " + flattened);
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

    @Test
    public void shouldUseSpecializedStream() {
        // Given
        final Set<Integer> collected =
            IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(x -> x > 5).boxed().collect(Collectors.toSet());
    }

    @Test
    public void shouldUseMethodReference() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final List<Person> people = Lists.newArrayList(new Person("Brandon"), new Person("Jessica"), new Person("Marry"));

        // When
        final List<String> names = people.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println("names = " + names);

        final List<Integer> collect = table.stream().filter(LambdasScenarios::isEven).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        table.stream().reduce(0, (acc, element) -> acc += element).intValue();
        final int sum = table.stream().reduce(0, Integer::sum).intValue();
        System.out.println("sum = " + sum);
    }

    @Test
    public void shouldOrder() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Set<Integer> set = table.stream().collect(Collectors.toSet());
        // Replace first and last digit in table
        System.out.println("set = " + set);

        final Set<Integer> sortedSet = table.stream().sorted().collect(Collectors.toSet());
        System.out.println("sortedSet = " + sortedSet);
    }

    @Test
    public void shouldPickCustomCollector() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When

        // STEP ONE
        //
        // final Supplier<Collection<Integer>> collectionFactory = new Supplier<Collection<Integer>>() {
        //
        // @Override
        // public Collection<Integer> get() {
        // return new TreeSet<>();
        // }
        // };
        // final Collection<Integer> set = table.stream().collect(Collectors.toCollection(collectionFactory));

        // STEP TWO
        // final Collection<Integer> set = table.stream().collect(Collectors.toCollection(() -> new TreeSet<Integer>()));

        // STEP THREE
        final Collection<Integer> set = table.stream().collect(Collectors.toCollection(TreeSet::new));

        // Then
        System.out.println("set = " + set);
        System.out.println("supplier = " + set.getClass());
    }

    @Test
    public void shouldCollectSingleValues() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Double average = table.stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("average = " + average);

        final IntSummaryStatistics statistics = table.stream().collect(Collectors.summarizingInt(value -> value.intValue()));
        System.out.println("sum = " + statistics.getSum());

        final Optional<Integer> max = table.stream().collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("max = " + max.get());

        final long count = table.stream().count();
        System.out.println("count = " + count);
    }

    @Test
    public void shouldPartition() {
        // Given
        final List<Integer> table = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // When
        final Map<Boolean, List<Integer>> partitioned = table.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));

        // Then
        System.out.println("partitioned = " + partitioned);
    }

    @Test
    public void shouldGroup() {
        // Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"));

        // When
        final Map<String, List<Album>> albumsByMusicians =
            albums.stream().collect(Collectors.groupingBy(album -> album.getMusician()));

        // Then
        System.out.println("albumsByMusicians = " + albumsByMusicians);
        System.out.println("albumsByMusicians.get(\"Vader\") = " + albumsByMusicians.get("Vader"));
    }

    @Test
    public void shouldConcatenateString() {
        // Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"));

        final StringBuilder builder = new StringBuilder("[");
        // When
        for (final Album album : albums) {
            if (builder.length() > 1) {
                builder.append(",");
            }
            builder.append(album.getMusician());
        }

        builder.append("]");

        // Then
        System.out.println("builder.toString() = " + builder.toString());
    }

    @Test
    public void shouldGroupString() {
        // Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"));

        // When
        final String musicians =
            albums.stream().map(album -> album.getMusician()).distinct().collect(Collectors.joining(",", "[", "]"));

        // Then
        System.out.println("musicians = " + musicians);
    }

    @Test
    public void shouldComposeCollectors() {
        // Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
            new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"));

        // When
        final Map<String, List<String>> albumsByMusician = albums.stream().collect(Collectors
            .groupingBy(album -> album.getMusician(), Collectors.mapping(album -> album.getTitle(), Collectors.toList())));

        // Then
        System.out.println("albumsByMusician = " + albumsByMusician);
    }

    @Test
    public void shouldParrallelData() {
        // Given
        final List<Integer> numbers = IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toList());

        // When
        final long startTime = System.currentTimeMillis();

        final int sum = numbers.parallelStream().mapToInt(value -> value).sum();

        final long stopTime = System.currentTimeMillis();
        final long elapsedTime = stopTime - startTime;
        System.out.println("elapsedTime = " + elapsedTime + " ms");

        // Then
        System.out.println("sum = " + sum);
    }

    @Test
    public void shouldParrallelDataDifferentImplementations() {
        // Given
        // final List<Integer> numbers =
        // IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(ArrayList::new));

        // final TreeSet<Integer> numbers =
        // IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(TreeSet::new));

        final LinkedList<Integer> numbers =
            IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(LinkedList::new));

        // When
        final long startTime = System.currentTimeMillis();

        final int sum = numbers.parallelStream().mapToInt(value -> value).sum();

        final long stopTime = System.currentTimeMillis();
        final long elapsedTime = stopTime - startTime;
        System.out.println("elapsedTime = " + elapsedTime + " ms");

        // Then
        System.out.println("sum = " + sum);
    }
}
