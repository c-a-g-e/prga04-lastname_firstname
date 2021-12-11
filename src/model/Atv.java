package model;

import java.util.Map;

public class Atv extends AbstractVehicle{

    int initialPosX;
    int initialPosY;
    int myX;
    int myY;
    Direction myDir;
    String myImageFileName;
    String myDeadImageFileName;
    Boolean alive = true;

    public Atv(int theX, int theY, Direction theDir) {
        myX = theX;
        myY = theY;
        initialPosX = theX;
        initialPosY = theY;
        myDir = theDir;
        myImageFileName = "atv.gif";
        myDeadImageFileName = "atv_dead.gif";
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
        if (theOther.isAlive()) {
            alive = false;
        }
    }

    @Override
    public int getDeathTime() {
        return 25;
    }

    @Override
    public String getImageFileName() {
        if (this.isAlive()) {
            return myImageFileName;
        } else {
            return myDeadImageFileName;
        }
    }

    @Override
    public Direction getDirection() {
        return myDir;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void poke() {

    }

    @Override
    public void reset() {
        alive = true;
        myX = initialPosX;
        myY = initialPosY;
    }

    @Override
    public void setDirection(Direction theDir) {
        myDir = theDir;
    }

    @Override
    public void setX(int theX) {
        myX = theX;
    }

    @Override
    public void setY(int theY) {
        myY = theY;
    }
}
