import static java.util.stream.Collectors.*;
import static org.assertj.core.api.BDDAssertions.then;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
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
import java.util.stream.StreamSupport;

import com.google.common.collect.Sets;
import com.sun.org.glassfish.gmbal.Description;
import javafx.util.Pair;
import org.junit.Test;

import com.google.common.collect.Lists;

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
    public void shouldCreateStreamFromIterable(){
        //Given
        Iterable<String> names = Lists.newArrayList("Ala", "Ola", "Piotr", "Dominik");

        //When
        ArrayList<String> sortedNames = StreamSupport
                .stream(names.spliterator(), false)
                .sorted()
                .collect(toCollection(ArrayList::new));

        //Then
        then(sortedNames).containsSequence("Ala", "Dominik", "Ola", "Piotr");
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
    public void shouldCollectToMap(){
        //Given
        final List<Album> albums = Lists.newArrayList(new Album("Vader", "De Profundis"), new Album("Hunter", "Kingdom"),
                new Album("Michael Jackson", "Dangerous"), new Album("Vader", "Sothis"), new Album("Vader", "Tibi Et Igni"));

        //When
        final Map<String, String> artistsToTitles = albums //
                .stream() //
                .collect(toMap(Album::getMusician, Album::getTitle, (albumTitle1, albumTitle2) -> albumTitle1));

        //Then
        then(artistsToTitles).containsKeys("Vader", "Hunter", "Michael Jackson");
        then(artistsToTitles).containsValues("De Profundis", "Kingdom", "Dangerous");
    }

    @Test
    public void shouldCalculateTableElementsSumUsingReduce() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = Arrays.stream(table).reduce(0, (accumulator, element) -> accumulator + element);

        // Then
        then(sum).isEqualTo(55);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenConcatenateStringWithReduceWithNullAccumulator() {
        // Given
        final String message = null;
        final List<String> words = Arrays.asList("Ala", " ma", " kota.");

        // When
        final String sentence = words.stream().reduce(message, String::concat);
    }

    @Test
    public void shouldConcatenateStringWithReduce() {
        // Given
        final String message = "";
        final List<String> words = Arrays.asList("Ala", " ma", " kota.");

        // When
        final String sentence = words.stream().reduce(message, String::concat);

        // Then
        then(sentence).isEqualTo("Ala ma kota.");
    }

    @Test
    public void shouldCalculateFruitsWeight(){
        // Given
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Banana());
        fruits.add(new Banana());

        // When
        int weight = fruits
                .stream()
                .map(Fruit::getWeight)
                .reduce(0, (accumulator, element) -> accumulator + element);

        // Then
        then(weight).isEqualTo(440);
    }

    @Test
    public void shouldUseStreamsToGetTableElementsSum() {
        // Given
        final int[] table = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        final int sum = IntStream.of(table).sum();

        // Then
        then(sum).isEqualTo(55);
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
        final List<Person> people = Lists.newArrayList(
                new Person(1, "Brandon", 11),
                new Person(2, "Jessica", 22),
                new Person(3, "Marry", 33)
        );

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
    public void shouldParallelData() {
        // Given
        final List<Integer> numbers = IntStream.rangeClosed(0, 10_000_000).boxed().collect(toList());

        // When
        final long startTime = System.currentTimeMillis();

        final int sum = numbers.stream().mapToInt(value -> value).sum();

        final long endTime = System.currentTimeMillis();
        System.out.println("Computation time = " + (endTime - startTime) + " ms");

        // Then
        System.out.println("sum = " + sum);
    }

    @Test
    public void shouldParrallelDataDifferentImplementations() {
        // Given
        final List<Integer> numbers =
            IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(ArrayList::new));

        // final TreeSet<Integer> numbers =
        // IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(TreeSet::new));

        // final LinkedList<Integer> numbers =
        // IntStream.rangeClosed(0, 10_000_000).boxed().collect(Collectors.toCollection(LinkedList::new));

        // When
        final long startTime = System.currentTimeMillis();

        final int sum = numbers.parallelStream().mapToInt(value -> value).sum();

        final long endTime = System.currentTimeMillis();
        System.out.println("Computation time = " + (endTime - startTime) + " ms");

        // Then
        System.out.println("sum = " + sum);
    }

    @Test
    public void shouldFlatRecursiveStreams(){
        //Given
//        http://www.nurkiewicz.com/2014/07/turning-recursive-file-system-traversal.html#!/2014/07/turning-recursive-file-system-traversal.html

        //When
//        return Stream.concat(Stream.of(this), this.getSubActions().stream().flatMap(CascadedActionInfo::flattened));

        //Then
    }

    @Test
    public void shouldCollectToSingleCollection(){
        // Given
        List<List<String>> alphabet = new ArrayList<>();
        List<String> vowels = new ArrayList<>();
        List<String> consonants = new ArrayList<>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");
        consonants.add("b");
        consonants.add("c");
        consonants.add("d");
        alphabet.add(vowels);
        alphabet.add(consonants);

        // When
        List<String> collect = alphabet.stream()
                .flatMap(Collection::stream)
                .collect(toList());

        // Then
        System.out.println("collect = " + collect);
    }

    @Test
    public void shouldSelectIdsBaseOnFirstValueCountAndSecondValueDifference(){
        //Given
        List<Pair<Integer, Integer>> pairs = Lists.newArrayList(
                new Pair<>(1, 11),
                new Pair<>(1, 12),
                new Pair<>(1, 13),
                new Pair<>(2, 21),
                new Pair<>(2, 22),
                new Pair<>(3, 31),
                new Pair<>(3, 31),
                new Pair<>(4, 41),
                new Pair<>(4, 42)
        );

        Set<Integer> expected = Sets.newHashSet(2, 4);

        //When
        Map<Integer, List<Pair<Integer, Integer>>> grouped = pairs
                .stream()
                .collect(groupingBy(Pair::getKey));

        Set<Integer> result = grouped
                .entrySet()
                .stream()
                .filter(this::has2Keys)
                .map(Map.Entry::getValue)
                .filter(this::hasDifferentValues)
                .flatMap(Collection::stream)
                .map(Pair::getKey)
                .collect(toSet());

        //Then
        then(result)
                .hasSize(expected.size())
                .containsAll(expected);
    }

    @Test
    public void shouldGroupByChunks(){
        // Given
        LocalDate startDay = LocalDate.parse("2018-03-01");
        int daysCount = 5;
        int daysInPartition = 2;

        // When
        List<List<String>> groupedDays = Stream.iterate(startDay, date -> date.plusDays(1))
                .limit(daysCount)
                .map(LocalDate::toString)
                .collect(Collectors.collectingAndThen(toList(), dates -> Lists.partition(dates, daysInPartition)));

        // Then
        List<String> group1 = new ArrayList<>();
        List<String> group2 = new ArrayList<>();
        List<String> group3 = new ArrayList<>();
        group1.add("2018-03-01");
        group1.add("2018-03-02");
        group2.add("2018-03-03");
        group2.add("2018-03-04");
        group3.add("2018-03-05");

        then(groupedDays).containsExactly(group1, group2, group3);
        System.out.println("groupedDays = " + groupedDays);
    }

    @Test
    public void shouldOneCollectionBasedOnSecondCollection() {
        // Given
        final Collection<String> firstCollection = Lists.newArrayList("a", "b", "c", "d");
        final Collection<String> secondCollection = Lists.newArrayList("a", "b", "x", "y");

        // When
        final List<String> collect = firstCollection.stream().filter(secondCollection::contains).collect(toList());

        // Then
        then(collect).containsExactly("a", "b");
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

        final List<Association> filteredAssociations = //
            associations.parallelStream() //
                .filter(a -> typesToFilter.contains(a.getType())) //
                .collect(toList());

        // Then
        then(filteredAssociations).containsExactly(new Association("A"), new Association("B"));
    }

    @Test
    public void shouldGenerateSequence() {
        // Given
        final int N = 16;

        // When
        final List<Integer> sequence = //
            Stream //
                .iterate(1, i -> i + 1) //
                .filter(this::notDivisibleBy3) //
                .filter(this::notDivisibleBy5) //
                .filter(this::notContainingDigit3) //
                .limit(N) //
                .collect(Collectors.toList());

        // Then
        then(sequence).containsSequence(1, 2, 4, 7, 8, 11, 14, 16, 17, 19, 22, 26, 28, 29, 41, 44);
    }

    @Test
    public void shouldPeek() {
        // When
        IntStream.range(1, 15).peek(System.out::println).limit(5).count();
    }

    @Test
    @Description("Codility DecReprSenior")
    public void codilityDecReprSenior(){
        // Given
        int N = 213;

        // When
        Integer integer = Integer.
                toString(N)
                .chars()
                .map(c -> c - '0')
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(Object::toString)
                .reduce((accumulator, element) -> accumulator += element)
                .map(Integer::parseInt)
                .get();


        // Then
        System.out.println("integer = " + integer);
    }

    @Test
    public void shouldIterateInnerStream(){
        // Given
        Person peter = new Person(1, "Piotrek", 20);
        Person aga = new Person(2, "Aga", 21);
        Person john = new Person(1, "John", 22, Lists.newArrayList(peter, aga));

        List<Person> people = Lists.newArrayList(john);

        // When
        String collect = people
                .stream()
                .map(person -> person.getName() + person.friends().stream()
                        .map(friend -> friend.getName() + " " + friend.getId()).collect(joining(":", "<", ">")))
                .collect(joining("#"));

        // Then
        System.out.println("collect = " + collect);
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

        final long endTime = System.currentTimeMillis();
        System.out.println("Computation time = " + (endTime - startTime) + " ms");

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

    private boolean notDivisibleBy3(final Integer value) {
        return value % 3 != 0;
    }

    private boolean notDivisibleBy5(final Integer value) {
        return value % 5 != 0;
    }

    private boolean notContainingDigit3(final Integer value) {
        return !String.valueOf(value).contains("3");
    }


    private boolean has2Keys(final Map.Entry<Integer, List<Pair<Integer, Integer>>> entry) {
        return entry.getValue().size() == 2;
    }

    private boolean hasDifferentValues(final List<Pair<Integer, Integer>> pairs) {
        return  !pairs.get(0).getValue().equals(pairs.get(1).getValue());
    }
}
