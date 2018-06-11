import java.util.ArrayList;
import java.util.List;

final class Person {

    private final int id;
    private final String name;
    private final int age;
    private List<Person> friends;

    Person(final int id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
    }

    Person(final int id, final String name, final int age, List<Person> friends) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.friends = friends;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Person> friends() {
        return this.friends;
    }
}
