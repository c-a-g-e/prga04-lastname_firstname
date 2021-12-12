package model;

import java.util.Map;

public class Atv extends AbstractVehicle{

    public Atv(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
        myImageFileName = "atv.gif";
        myDeadImageFileName = "atv_dead.gif";
        myDeathTime = 25;
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return !theTerrain.equals(Terrain.WALL);
    }

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

    @Override
    public void collide(Vehicle theOther) {
        if (theOther.getMyAlive()) {
            myAlive = false;
        }
    }

}
