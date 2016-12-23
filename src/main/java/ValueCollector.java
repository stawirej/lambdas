import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@SuppressWarnings("unchecked")
final class ValueCollector<T, A, R> implements Collector<T, A, R> {

    @Override
    public Supplier<A> supplier() {
        return () -> (A)new ArrayList<T>();
    }

    @Override
    public BiConsumer<A, T> accumulator() {
        return (accumulator, element) -> ((List)accumulator).add(element);
    }

    @Override
    public BinaryOperator<A> combiner() {
        return (accumulator1, accumulator2) -> {
            throw new UnsupportedOperationException();
        };
    }

    @Override
    public Function<A, R> finisher() {
        return accumulator -> (R)((List)accumulator).stream().reduce((acc, element) -> element).get();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
