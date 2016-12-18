final class Person {

    private final int id;
    private final String name;
    private final int age;

    Person(final int id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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
}
