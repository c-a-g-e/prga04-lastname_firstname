package model;

/*
Cage Peterson
V1
TCSS 305 Section C
Dr. Dincer
 */

import java.util.Random;

/**
 * Abstract parent class for vehicles that implements the Vehicle interface.
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * Random object for use in child classes.
     */
    protected final static Random random = new Random();
    /**
     * The starting x coordinate of the Vehicle.
     */
    public int initialPosX;
    /**
     * The starting y coordinate of the Vehicle.
     */
    public int initialPosY;
    /**
     * The vehicle's current x coordinate.
     */
    private int myX;
    /**
     * The vehicle's current y coordinate.
     */
    private int myY;
    /**
     * The direction that the vehicle is currently facing.
     */
    protected Direction myDir;
    /**
     * True if the vehicle is alive, false if it collided with a Vehicle that has a smaller death time.
     */
    private Boolean myAlive = true;
    /**
     * The amount of time that the Vehicle must remain dead.
     */
    protected int myDeathTime;
    /**
     * The time that the vehicle has spent dead.
     */
    private int myTimeDead;

    /**
     * Default constructor for a vehicle. Initializes x and y fields and direction field.
     * @param theX the starting x coordinate of the vehicle.
     * @param theY the starting y coordinate of the vehicle.
     * @param theDir the starting direction that the vehicle will face upon spawning.
     */
    AbstractVehicle(int theX, int theY, Direction theDir) {
        myX = theX;
        myY = theY;
        initialPosX = theX;
        initialPosY = theY;
        myDir = theDir;
    }

    /**
     * Obtains the death time of the Vehicle.
     * @return the death time
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other vehicle.
     */
    @Override
    public void collide(Vehicle theOther) {
        if (myDeathTime > theOther.getDeathTime()) {
            myAlive = false;
            getImageFileName();
        }
    }

    /**
     * Obtains the Image file name of the vehicle using the class name. If the vehicle is alive, ".gif" will be added
     * to the lowercase class name, and if dead, "_dead.gif" will be appended to the lowercase class name.
     * @return the class name plus ".gif" or "_dead.gif" depending on the alive state of the vehicle.
     */
    @Override
    public String getImageFileName() {
        return getClass().getSimpleName().toLowerCase() + (myAlive ? ".gif" : "_dead.gif");
    }

    /**
     * Obtains the current direction that the vehicle is facing.
     * @return the current direction that the vehicle is facing.
     */
    @Override
    public Direction getDirection() {
        return myDir;
    }

    /**
     * Obtains the current x coordinate of the Vehicle.
     * @return the current x coordinate of the Vehicle.
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * Obtains the current y coordinate of the Vehicle.
     * @return the current y coordinate of the Vehicle.
     */
    @Override
    public int getY() {
        return myY;
    }

    /**
     * Obtains the alive state of the vehicle.
     * @return the alive state of the vehicle.
     */
    @Override
    public boolean getMyAlive() {
        return myAlive;
    }

    /**
     * Tells a dead vehicle that one step has passed by adding one to its myTimeDead.
     */
    @Override
    public void poke() {
        if (!this.getMyAlive() && this.myTimeDead < myDeathTime) {
            myTimeDead++;
        } else {
            myAlive = true;
            myTimeDead = 0;
        }
    }

    /**
     * Resets the vehicle to its initial starting position with its initial direction.
     */
    @Override
    public void reset() {
        myAlive = true;
        myX = initialPosX;
        myY = initialPosY;
    }

    /**
     * Sets the direction of the vehicle.
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(Direction theDir) {
        myDir = theDir;
    }

    /**
     * Sets the vehicle's x coordinate.
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(int theX) {
        myX = theX;
    }

    /**
     * Sets the vehicle's y coordinate.
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(int theY) {
        myY = theY;
    }

}