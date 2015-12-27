public class Association {

    private final String type;

    public Association(final String type) {
        this.type = type;
    }

    public boolean isTypeOf(final Association association) {
        return this.type.equals(association.type);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        return ((Association)obj).type.equals(type);
    }
}
