package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class Car extends AbstractVehicle {

    public Car(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = 15;
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) {
            return false;
        } else if (theTerrain.equals(Terrain.CROSSWALK) && !theLight.equals(Light.GREEN)) {
            return false;
        }
        return theTerrain.equals(Terrain.STREET)
                || theTerrain.equals(Terrain.CROSSWALK)
                || theTerrain.equals(Terrain.LIGHT);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] availDirections = {myDir, myDir.left(), myDir.right()};
        for (Direction theDirection : availDirections) {
            if (theNeighbors.get(theDirection).equals(Terrain.STREET)
                    || theNeighbors.get(theDirection).equals(Terrain.CROSSWALK)
                    || theNeighbors.get(theDirection).equals(Terrain.LIGHT)) {
                return theDirection;
            }
        }
        return myDir.reverse();
    }

}