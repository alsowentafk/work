package task1;

public class User {

    private final long id;
    private final String content;

    public User(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
