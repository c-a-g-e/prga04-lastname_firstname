package model;

import java.util.Map;

public class Bicycle extends AbstractVehicle {

    public Bicycle(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
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
        if (theTerrain.equals(Terrain.LIGHT) && (theLight.equals(Light.RED) || theLight.equals(Light.YELLOW))) {
            return false;
        } else if (theTerrain.equals(Terrain.CROSSWALK) && !theLight.equals(Light.GREEN)) {
            return false;
        }
        return theTerrain.equals(Terrain.STREET)
                || theTerrain.equals(Terrain.CROSSWALK)
                || theTerrain.equals(Terrain.LIGHT)
                || theTerrain.equals(Terrain.TRAIL);
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
            if (theNeighbors.get(theDirection).equals(Terrain.TRAIL)) {
                return theDirection;
            }
        }
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