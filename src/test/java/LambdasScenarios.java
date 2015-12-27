import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.api.Assertions.assertThat;

import java.awt.event.ActionListener;
import java.util.Arrays;
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

import com.google.common.base.Stopwatch;

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
        final List<Integer> mapped = table.stream().map(x -> x * 10).collect(toList());

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
        final List<Integer> filtered = table.stream().filter(x -> x % 2 == 0).filter(x -> x % 3 == 0).collect(toList());

        // Then
        System.out.println("filtered = " + filtered);
    }

    @Test
    public void shouldFlatMap() {
        // Given
        final List<Integer> tableA = Lists.newArrayList(1, 2, 3, 4, 5);
        final List<Integer> tableB = Lists.newArrayList(6, 7, 8, 9, 10);

        // When
        final List<Integer> flattened = Stream.of(tableA, tableB).flatMap(table -> table.stream()).collect(toList());

        // Then
        System.out.println("flattened = " + flattened);
    }

    @Test
    public void shouldCalculateTableElementsSumUsingReduce() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = Arrays.stream(table).reduce(0, (accumulator, element) -> accumulator + element);

        // Then
        assertThat(sum).isEqualTo(55);
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
        final List<String> names = people.stream().map(Person::getName).collect(toList());
        System.out.println("names = " + names);

        final List<Integer> collect = table.stream().filter(LambdasScenarios::isEven).collect(toList());
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
        final Map<String, List<Album>> albumsByMusicians = albums.stream().collect(groupingBy(album -> album.getMusician()));

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
        final Map<String, List<String>> albumsByMusician = albums.stream()
            .collect(groupingBy(album -> album.getMusician(), Collectors.mapping(album -> album.getTitle(), toList())));

        // Then
        System.out.println("albumsByMusician = " + albumsByMusician);
    }

    @Test
    public void shouldParrallelData() {
        // Given
        final List<Integer> numbers = IntStream.rangeClosed(0, 100_000_000).boxed().collect(toList());

        // When
        final Stopwatch timer = Stopwatch.createStarted();

        final int sum = numbers.stream().mapToInt(value -> value).sum();

        timer.stop();
        System.out.println("timer = " + timer);

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
        final Stopwatch timer = Stopwatch.createStarted();

        final int sum = numbers.parallelStream().mapToInt(value -> value).sum();

        timer.stop();
        System.out.println("timer = " + timer);

        // Then
        System.out.println("sum = " + sum);
    }

    @Test
    public void shouldOneCollectionBasedOnSecondCollection() {
        // Given
        final Collection<String> firstCollection = Lists.newArrayList("a", "b", "c", "d");
        final Collection<String> secondCollection = Lists.newArrayList("a", "b", "x", "y");

        // When
        final List<String> collect = firstCollection.stream().filter(secondCollection::contains).collect(toList());

        // Then
        assertThat(collect).containsExactly("a", "b");
    }

    @Test
    public void shouldFilterOneCollectionBasedOnSecondCollectionByCheckingOneAttribute() {
        // Given
        final Collection<Association> associations =
            Lists.newArrayList(new Association("A"), new Association("B"), new Association("C"), new Association("D"));

        final Collection<Association> associationsToFilter =
            Lists.newArrayList(new Association("A"), new Association("B"), new Association("X"), new Association("Y"));

        // When
        final List<String> typesToFilter = associationsToFilter.parallelStream().map(Association::getType).collect(toList());
        final List<Association> filteredAssociations =
            associations.parallelStream().filter(a -> typesToFilter.contains(a.getType())).collect(toList());

        // Then
        assertThat(filteredAssociations).containsExactly(new Association("A"), new Association("B"));
    }

    @Test
    public void shouldCheckPerformanceOfFilterOneCollectionBasedOnSecondCollectionByCheckingOneAttribute() {
        // Given
        System.out.println("prepare 1");
        final Collection<Association> associations = createAssociations(100_000);
        // final Collection<Association> associations =
        // Lists.newArrayList(new Association("A"), new Association("B"), new Association("C"), new Association("D"));

        System.out.println("prepare 2");
        final Collection<Association> inAssociations = createAssociations(100_000);
        // final Collection<Association> inAssociations =
        // Lists.newArrayList(new Association("A"), new Association("B"), new Association("X"), new Association("Y"));

        System.out.println("start");
        final long startTime = System.currentTimeMillis();

        // original - slowest
        // final Collection<Association> assocs = Lists.newArrayList();
        // for (final Association association : inAssociations) {
        // associations.stream().filter(a -> a.isTypeOf(association)).forEach(t -> assocs.add(t));
        // }
        //

        // System.out.println("old assocs = " + assocs);

        // When

        // ok - medium
        // final List<Association> collect =
        // associations.stream().filter(a -> inAssociations.stream().anyMatch(ia -> ia.isTypeOf(a))).collect(toList());

        // ok2 - fastest
        // final List<String> types = inAssociations.parallelStream().map(Association::getType).collect(toList());
        // final List<Association> collect =
        // associations.parallelStream().filter(a -> types.contains(a.getType())).collect(toList());

        final long stopTime = System.currentTimeMillis();
        final long elapsedTime = stopTime - startTime;
        System.out.println("elapsedTime = " + elapsedTime + " ms");

        // Then
        // System.out.println("collect = " + collect);
    }

    private List<Association> createAssociations(final long count) {
        final List<Association> associations = Lists.newArrayList();
        for (int i = 0; i < count; i++) {
            associations.add(new Association(Integer.toString(i + 1)));
        }
        return associations;
    }
}
