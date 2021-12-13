package model;

/*
Cage Peterson
V1
TCSS 305 Section C
Dr. Dincer
 */

import java.util.Map;

/**
 * Car subclass of abstract vehicle.
 * This subclass can drive on the street, crosswalks, or lights. If the car can go straight, it will. If not,
 * the car will always turn left over right. When stuck, it will reverse.
 * Stops at red lights.
 */
public class Car extends AbstractVehicle {

    /**
     * Constructor for AbstractVehicle child class. Calls super constructor to initialize location and death time.
     * @param theX the initial x location
     * @param theY the initial y location
     * @param theDir the initial direction
     */
    public Car(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = 15;
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * Car will drive on street, crosswalks that are green, and lights that are not red.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
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

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * Car prefers to drive straight > left > right > reverse.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
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