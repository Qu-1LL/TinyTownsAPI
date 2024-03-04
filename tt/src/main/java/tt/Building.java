package tt;

public interface Building {
    public String toString();
    public int getScore();
    public Resource[][] getSchematic();
    public String getRules(); 
    public BuildingType getType();
}