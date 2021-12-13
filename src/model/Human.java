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
 * Human subclass of abstract vehicle.
 * This subclass can only walk on grass, trails, and crosswalks (when the light is yellow or red).
 */
public class Human extends AbstractVehicle {

    /**
     * Constructor for AbstractVehicle child class. Calls super constructor to initialize location and death time.
     * @param theX the initial x location
     * @param theY the initial y location
     * @param theDir the initial direction
     */
    public Human(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = 45;
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * human can travel on crosswalks that are green.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        //can pass terrain that isnt crosswalk w/ green light
        return (!theTerrain.equals(Terrain.CROSSWALK) || !theLight.equals(Light.GREEN))
                && (!theTerrain.equals(Terrain.STREET)
                && !theTerrain.equals(Terrain.LIGHT)
                && !theTerrain.equals(Terrain.WALL));
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * human will move on crosswalks, grass, and trails and prefers going straight > left > right.
     * If human can walk on a crosswalk, it will always choose to do so.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] availDirections = {myDir, myDir.left(), myDir.right()};
        for (Direction theDirection : availDirections) {
            if (theNeighbors.get(theDirection).equals(Terrain.CROSSWALK)) {
                return theDirection;
            }
        }
        Collections.shuffle(Arrays.asList(availDirections));
        for (Direction theDirection : availDirections) {
            if (theNeighbors.get(theDirection).equals(Terrain.GRASS)
                    || theNeighbors.get(theDirection).equals(Terrain.TRAIL)) {
                return theDirection;
            }
        }
        return myDir.reverse();
    }
}
