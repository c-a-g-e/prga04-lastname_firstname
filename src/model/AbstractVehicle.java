package model;

import java.util.Random;

public abstract class AbstractVehicle implements Vehicle {

    final static Random random = new Random();
    Thread thread;
    int initialPosX;
    int initialPosY;
    int myX;
    int myY;
    Direction myDir;
    Boolean myAlive = true;
    int myDeathTime;
    int myTimeDead;
    String myImageFileName;
    String myDeadImageFileName;

    AbstractVehicle(int theX, int theY, Direction theDir) {
        myX = theX;
        myY = theY;
        initialPosX = theX;
        initialPosY = theY;
        myDir = theDir;
    }

    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    @Override
    public String getImageFileName() {
        if (this.getMyAlive()) {
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
    public boolean getMyAlive() {
        return myAlive;
    }

    @Override
    public void poke() {
        if (!this.getMyAlive() && this.myTimeDead < myDeathTime) {
            myTimeDead++;
        } else {
            myAlive = true;
            myTimeDead = 0;
        }
    }

    @Override
    public void reset() {
        myAlive = true;
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