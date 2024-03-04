package tt;

public enum Resource {
    BRICK(":resource_brick:"),
    GLASS(":resource_glass"),
    STONE(":resource_stone"),
    WHEAT(":resource_wheat"),
    WOOD(":resource_wood:");

    private String emoji;

    private Resource (String emoji) {
        this.emoji = emoji;
    }
    public String getEmoji() {
        return emoji;
    }
    @Override
    public String toString () {
        return emoji;
    }
}