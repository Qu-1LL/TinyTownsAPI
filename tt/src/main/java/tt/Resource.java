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
    @Override
    public String toString () {
        if (this.emoji == null) {
            return ":empty_tile:";
        }
        return emoji;
    }
}