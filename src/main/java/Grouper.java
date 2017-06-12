import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class Grouper implements Consumer<CustomPair>{

    private final List<List<CustomPair>> groups;
    private int index;
    private int currentVaulesSize;
    private static final int limit = 4;

    public Grouper() {
        groups = new ArrayList<>();
        groups.add(new ArrayList<>());
        index = 0;
        currentVaulesSize = 0;
    }

    public List<List<CustomPair>> get() {
        return groups;
    }

    @Override
    public void accept(final CustomPair customPair) {
        if (currentVaulesSize >= limit){
            groups.add(new ArrayList<>());
            index++;
            currentVaulesSize = 0;
        }

        currentVaulesSize += customPair.getValuesSize();
        groups.get(index).add(customPair);
    }
}
