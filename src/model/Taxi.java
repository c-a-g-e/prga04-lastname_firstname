package model;

import java.util.Map;

public class Taxi extends AbstractVehicle {

    Boolean wait = false;
    static int waitCount = 0;

    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myImageFileName = "taxi.gif";
        myDeadImageFileName = "taxi_dead.gif";
        myDeathTime = 15;
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (!wait) {
            if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) {
                wait = true;
                return false;
            } else if (theTerrain.equals(Terrain.CROSSWALK) && !theLight.equals(Light.GREEN)) {
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

    @Override
    public void collide(Vehicle theOther) {
        if (theOther.getClass().getSimpleName().equalsIgnoreCase("truck")) {
            myAlive = false;
        }
    }
}