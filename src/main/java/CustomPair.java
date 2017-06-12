import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class CustomPair {

    private final Character key;
    private final List<Integer> values;

    CustomPair(final Character key, final Integer... values) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.addAll(Arrays.asList(values));
    }

    public Character getKey() {
        return key;
    }

    public List<Integer> getValues() {
        return values;
    }

    public int getValuesSize() {
        return values.size();
    }

    public static CustomPair create(final Character key, final Integer... values) {
        return new CustomPair(key, values);
    }

    @Override
    public String toString() {
        return "<" + key + values + ">";
    }
}
