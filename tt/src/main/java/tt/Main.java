package tt;

import tt.buildings.*;
import tt.green.buildings.Tavern;
import tt.grey.buildings.Well;
import tt.navy.buildings.Factory;
import tt.orange.buildings.Chapel;
import tt.red.buildings.Farm;
import tt.yellow.buildings.Theatre;

public class Main {
    public static void main(String[] args) {
        Well well = new Well();
        System.out.println(well);
        Theatre theatre = new Theatre();
        System.out.println(theatre);
        Tavern tavern = new Tavern();
        System.out.println(tavern);
        Farm farm = new Farm();
        System.out.println(farm);
        Cottage cottage = new Cottage();
        System.out.println(cottage);
        Chapel chapel = new Chapel();
        System.out.println(chapel);
        Factory factory = new Factory();
        System.out.println(factory);
    }
}