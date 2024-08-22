package tt;

public enum Resource implements Tileable {
    BRICK("<:f_brick:1250677977742708797>",false,"brick"),
    GLASS("<:e_glass:1250672736594821151>",false,"glass"),
    STONE("<:b_stone:1250677442494861364>",false,"stone"),
    WHEAT("<:c_wheat:1250664487690833982>",false,"wheat"),
    WOOD("<:d_wood:1250662480284221520>",false,"wood"),
    EMPTY("<:a_empty:1250660017015619664>",true,"empty");

    private String emoji;
    private boolean empty;
    public String name;

    private Resource (String emoji,boolean empty,String name) {
        this.emoji = emoji;
        this.empty = empty;
        this.name = name;
    }
    public boolean isEmpty () {
        return empty;
    }
    public static Resource[] fullValues () {
        Resource[] resources = new Resource[5];
        resources[0] = WOOD;
        resources[1] = WHEAT;
        resources[2] = GLASS;
        resources[3] = STONE;
        resources[4] = BRICK;
        return resources;
    }
    @Override
    public String toPrint () {
        return emoji;
    }
    @Override
    public String toString () {
        return emoji;
    }
}