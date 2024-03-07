package tt;

public interface Building {
    public int getScore();
    public Resource[][] getSchematic();
    public String getRules(); 
    public BuildingType getType();
}