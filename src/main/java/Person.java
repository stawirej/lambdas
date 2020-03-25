import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Person {

    private final int id;
    private final String name;
    private final int age;
    private List<Person> friends;

    public Person(final int id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id &&
                age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(friends, person.friends);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, age, friends);
    }

    Person(final int id, final String name, final int age, List<Person> friends) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Person> friends() {
        return this.friends;
    }

    @Override
    public String toString() {

        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
