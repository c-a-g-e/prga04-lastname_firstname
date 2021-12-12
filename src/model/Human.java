package model;

import java.util.Map;

public class Human extends AbstractVehicle {

    public Human(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myDeathTime = 45;
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
        return !theTerrain.equals(Terrain.CROSSWALK) || (theLight.equals(Light.YELLOW) || theLight.equals(Light.RED));
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
            if (theNeighbors.get(theDirection).equals(Terrain.CROSSWALK)) {
                return theDirection;
            }
        }

        for (Direction theDirection : availDirections) {
            if (theNeighbors.get(theDirection).equals(Terrain.GRASS)
                    || theNeighbors.get(theDirection).equals(Terrain.TRAIL)) {
                return theDirection;
            }
        }
        return myDir.reverse();
    }
}
