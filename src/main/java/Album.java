public class Album {

    private final String musician;
    private final String title;

    public Album(final String musician, final String title) {
        this.musician = musician;
        this.title = title;
    }

    public String getMusician() {
        return musician;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
