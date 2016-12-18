import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

public class CustomCollectorScenarios {

    @Test
    public void shouldGroupWithCustomStringCollector(){
        // Given
        final List<Person> people = Lists.newArrayList(
                new Person(1, "Brandon", 11),
                new Person(2, "Jessica", 22),
                new Person(3, "Marry", 33)
        );

        final Collector<String, List<String>, String> toNameCollector = new Collector<>() {

            @Override
            public Supplier<List<String>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<String>, String> accumulator() {
                return List::add;
            }

            @Override
            public BinaryOperator<List<String>> combiner() {
                return null;
            }

            @Override
            public Function<List<String>, String> finisher() {
                return accumulator -> accumulator.stream().reduce((acc, element) -> element).get();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };

        // When
        Map<Integer, String> idToName = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.mapping(Person::getName, toNameCollector)));

        System.out.println("idToName = " + idToName);
        // Then
        then(idToName)
                .containsKeys(1, 2, 3)
                .containsValues("Brandon", "Jessica", "Marry");
    }

    @Test
    public void shouldGroupWithCustomGenericCollector(){
        // Given
        final List<Person> people = Lists.newArrayList(
                new Person(1, "Brandon", 11),
                new Person(2, "Jessica", 22),
                new Person(3, "Marry", 33)
        );

        // When
        Map<Integer, String> idToName = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.mapping(Person::getName, new ValueCollector<String, List<String>, String>())));

        // Short example. No type definitions required in ValueCollector.
        Map<Integer, Integer> idToAge = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.mapping(Person::getAge, new ValueCollector<>())));

        // Then
        then(idToName)
                .containsKeys(1, 2, 3)
                .containsValues("Brandon", "Jessica", "Marry");

        then(idToAge)
                .containsKeys(1, 2, 3)
                .containsValues(11, 22, 33);
    }

    @Test
    public void shouldGroupWithCustomGenericCollectorForEmptyCollection(){
        // Given
        final List<Person> people = Collections.emptyList();

        // When
        Map<Integer, String> idToName = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.mapping(Person::getName, new ValueCollector<>())));

        // Then
        then(idToName).isEmpty();
    }

    @Test
    public void shouldReduceDuplicatedValuesByCustomGenericCollector(){
        // Given
        final List<Person> people = Lists.newArrayList(
                new Person(1, "Brandon", 11),
                new Person(1, "Brandon", 11),
                new Person(2, "Jessica", 22)
        );

        // When
        Map<Integer, String> idToName = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.mapping(Person::getName, new ValueCollector<>())));

        // Then
        then(idToName)
                .hasSize(2)
                .containsKeys(1, 2)
                .containsValues("Brandon", "Jessica");
    }
}
