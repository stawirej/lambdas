import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

public class ValueCollectorScenarios {

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

    @Test
    public void shouldGroupWithReduce(){
        // Given
        final List<Person> people = Lists.newArrayList(
                new Person(1, "Brandon", 11),
                new Person(2, "Jessica", 22),
                new Person(3, "Marry", 33)
        );

        // When
        Map<Integer, String> idToName = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.reducing("", Person::getName, (s, s2) -> s2)));

        Map<Integer, Integer> idToAge = people
                .stream()
                .collect(groupingBy(Person::getId, Collectors.reducing(0, Person::getAge, (integer, integer2) -> integer2)));

        // Then
        then(idToName)
                .containsKeys(1, 2, 3)
                .containsValues("Brandon", "Jessica", "Marry");

        then(idToAge)
                .containsKeys(1, 2, 3)
                .containsValues(11, 22, 33);
    }
}
