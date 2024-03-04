package tt;

public class Main {
    public static void main(String[] args) {
        Tile<Resource> t1 = new Tile<>(0,0,Resource.WOOD);
        System.out.println(Resource.WOOD.getClass().getName());
        System.out.println(t1.getResource().getClass().getName());
        System.out.println(t1.removeResource());
    }
}