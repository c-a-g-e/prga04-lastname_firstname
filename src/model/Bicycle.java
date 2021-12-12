package model;

import java.util.Map;

public class Bicycle extends AbstractVehicle {

    public Bicycle(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myImageFileName = "bicycle.gif";
        myDeadImageFileName = "bicycle_dead.gif";
        myDeathTime = 35;
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
        return null;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        if (theOther.getClass().getSimpleName().equalsIgnoreCase("truck")) {
            myAlive = false;
        }
    }
}
