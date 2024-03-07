package tt;

public enum Resource implements Holdable {
    BRICK(":red_square:",false),
    GLASS(":blue_square:",false),
    STONE(":white_large_square:",false),
    WHEAT(":yellow_square:",false),
    WOOD(":brown_square:",false),
    EMPTY(":empty_tile:",true);

    private String emoji;
    private boolean empty;

    private Resource (String emoji,boolean empty) {
        this.emoji = emoji;
        this.empty = empty;
    }
    public boolean isEmpty () {
        return empty;
    }
    @Override
    public Holdable getHeld() {
        return this;
    }
    @Override
    public String toString () {
        return emoji;
    }
}