package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Truck extends AbstractVehicle{

    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain.equals(Terrain.STREET)
                || theTerrain.equals(Terrain.CROSSWALK)
                || theTerrain.equals(Terrain.LIGHT);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] availDirections = {myDir, myDir.right(), myDir.left()};
        Collections.shuffle(Arrays.asList(availDirections));

        for (Direction randDir : availDirections) {
            if (theNeighbors.get(randDir).equals(Terrain.STREET)
                    || theNeighbors.get(randDir).equals(Terrain.CROSSWALK)
                    || theNeighbors.get(randDir).equals(Terrain.LIGHT)) {
                return randDir;
            }
        }
        return myDir.reverse();
    }

}
