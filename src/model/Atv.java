package model;

/*
Cage Peterson
V1
TCSS 305 Section C
Dr. Dincer
 */

import java.util.Map;

/**
 * Atv child class. Moves randomly, can move anywhere but wall.
 */
public class Atv extends AbstractVehicle{

    /**
     * Constructor for AbstractVehicle child class. Calls super constructor to initialize location and death time.
     * @param theX the initial x location
     * @param theY the initial y location
     * @param theDir the initial direction
     */
    public Atv(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = 25;
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * Atv can traverse any terrain but wall.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return !theTerrain.equals(Terrain.WALL);
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * Atv will choose a random direction to move.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        double randNum = random.nextDouble();
        if (randNum <= 0.25) {
            myDir = Direction.NORTH;
        } else if (randNum <= 0.5) {
            myDir = Direction.EAST;
        } else if (randNum <= 0.75) {
            myDir = Direction.SOUTH;
        } else {
            myDir = Direction.WEST;
        }
        return myDir;
    }

}