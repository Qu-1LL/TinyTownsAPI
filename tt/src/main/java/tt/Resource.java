package tt;

public enum Resource implements Holdable {
    BRICK("<:brick_new:1215769685073858680>",false),
    GLASS("<:glass_new:1215769467435483266>",false),
    STONE("<:stone_new:1215769584758558821>",false),
    WHEAT("<:wheat_new:1215769538142933002>",false),
    WOOD("<:wood_new:1215769728136515724>",false),
    EMPTY("<:empty_tile:1215710638714654751>",true);

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