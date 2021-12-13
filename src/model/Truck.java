package model;

/*
Cage Peterson
V1
TCSS 305 Section C
Dr. Dincer
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Truck subclass of abstract vehicle.
 * This vehicle runs through stop lights, cannot die, and kills everything it collides with.
 */
public class Truck extends AbstractVehicle{

    /**
     * Constructor for AbstractVehicle child class. Calls super constructor to initialize location and death time.
     * @param theX the initial x location
     * @param theY the initial y location
     * @param theDir the initial direction
     */
    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
    }

    /**
     * Returns whether or not this object may move onto streets, crosswalks, and lights.
     * Ignores all lights.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain.equals(Terrain.STREET)
                || theTerrain.equals(Terrain.CROSSWALK)
                || theTerrain.equals(Terrain.LIGHT);
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * This vehicle will choose where to go randomly.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
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
