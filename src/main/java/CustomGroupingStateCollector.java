import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

final class CustomGroupingStateCollector implements Collector<CustomPair, List<List<CustomPair>>, List<List<CustomPair>>> {

    private int index;
    private int currentVaulesSize;
    private static final int limit = 4;

    public CustomGroupingStateCollector() {
                    index = 0;
                    currentVaulesSize = 0;
                }

    @Override
    public Supplier<List<List<CustomPair>>> supplier() {
        return () -> {
            final ArrayList<List<CustomPair>> accumulator = new ArrayList<>();
            accumulator.add(new ArrayList<>());
            return accumulator;
        };
    }

    @Override
    public BiConsumer<List<List<CustomPair>>, CustomPair> accumulator() {
       return (accumulator, customPair) -> {
           if (currentVaulesSize >= limit){
               accumulator.add(new ArrayList<>());
               index++;
               currentVaulesSize = 0;
           }

           currentVaulesSize += customPair.getValuesSize();
           accumulator.get(index).add(customPair);
       };
    }

    @Override
    public BinaryOperator<List<List<CustomPair>>> combiner() {
        return (accumulator1, accumulator2) -> {
            throw new UnsupportedOperationException();
        };
    }

    @Override
    public Function<List<List<CustomPair>>, List<List<CustomPair>>> finisher() {
        return accumulator -> accumulator;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
