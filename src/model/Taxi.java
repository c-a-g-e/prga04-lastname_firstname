package model;

/*
Cage Peterson
V1
TCSS 305 Section C
Dr. Dincer
 */

import java.util.Map;

/**
 * Taxi subclass of abstract vehicle.
 * This subclass can drive on the street, crosswalks, and lights. It will stop at red traffic lights,
 * but when it reaches a crosswalk light, it will stop for either 3 steps or until the light turns green
 * (whatever comes first).
 */
public class Taxi extends AbstractVehicle {

    /**
     * If true, the vehicle will wait at crosswalks with a red light.
     */
    private static Boolean wait = false;
    /**
     * Keeps track of how long the taxi has been waiting at a crosswalk. When this reaches 4, the taxi will
     * continue through the crosswalk.
     */
    private static int waitCount = 0;

    /**
     * Constructor for AbstractVehicle child class. Calls super constructor to initialize location and death time.
     * @param theX the initial x location
     * @param theY the initial y location
     * @param theDir the initial direction
     */
    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = 15;
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (!wait) {
            if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) {
                return false;
            } else if (theTerrain.equals(Terrain.CROSSWALK) && theLight.equals(Light.RED)) { // will wait if on red crosswalk
                wait = true;
                return false;
            }
            return theTerrain.equals(Terrain.STREET)
                    || theTerrain.equals(Terrain.CROSSWALK)
                    || theTerrain.equals(Terrain.LIGHT);
        } else {
            waitCount++;
            if (waitCount == 4 || theLight.equals(Light.GREEN)) {
                waitCount = 0;
                wait = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
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