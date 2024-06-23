package tt;

import java.util.HashSet;
import java.util.HashMap;

public interface TT {
    public HashSet<String> findPlayers ();
    public Resource getRoundResource (String username);
    public void placeResources(HashMap<String,Resource> placeableResources, HashMap<String,Player> playersMap);
    public boolean freeBuild(HashMap<String,Player> playersMap);
    public void gameEnd();
    public Resource chooseResource(String name, Resource[] options);
}
