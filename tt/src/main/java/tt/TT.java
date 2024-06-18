package tt;

import java.util.HashSet;
import java.util.HashMap;

public interface TT {
    public HashSet<String> getUsernames ();
    public Resource getRoundResource (String username);
    public void placeResources(HashMap<String,Player> players);
    public boolean freeBuild(HashSet<Player> activePlayers);
    public void gameEnd();
    public Resource chooseResource(String name, Resource[] options);
}
