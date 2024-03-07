package tt;

public enum Resource {
    BRICK(":resource_brick:",false),
    GLASS(":resource_glass:",false),
    STONE(":resource_stone:",false),
    WHEAT(":resource_wheat:",false),
    WOOD(":resource_wood:",false),
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
    public String toString () {
        return emoji;
    }
}