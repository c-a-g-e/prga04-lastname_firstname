package tests;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/** Contains all testing methods for the Atv class */
class AtvTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /** Test method for Atv constructor. */
    @Test
    public void testAtvConstructor() {
        final Atv atv = new Atv(10, 11, Direction.NORTH);

        assertEquals( 10, atv.getX(), "Atv x coordinate not initialized correctly!");
        assertEquals( 11, atv.getY(), "Atv y coordinate not initialized correctly!");
        assertEquals( Direction.NORTH, atv.getDirection(), "Atv direction not initialized correctly!");
        assertEquals( 25, atv.getDeathTime(), "Atv death time not initialized correctly!");
        Assertions.assertTrue(atv.getMyAlive(), "Atv getMyAlive() fails initially!");
    }

    /** Test method for {@link Atv#collide(Vehicle)}. */
    @Test
    void testCollide() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        final Bicycle bike = new Bicycle(0, 0, Direction.NORTH);
        final Car car = new Car(0, 0, Direction.NORTH);
        final Human human = new Human(0, 0, Direction.NORTH);
        final Taxi taxi = new Taxi(0, 0, Direction.NORTH);
        final Truck truck = new Truck(0, 0, Direction.NORTH);

        atv.collide(atv);
        Assertions.assertTrue(atv.getMyAlive(), "Atv died when colliding with atv.");

        atv.collide(human);
        Assertions.assertTrue(atv.getMyAlive(), "Atv died when colliding with human.");

        atv.collide(bike);
        Assertions.assertTrue(atv.getMyAlive(), "Atv died when colliding with bike.");

        atv.collide(car);
        Assertions.assertFalse(atv.getMyAlive(), "Atv lived when colliding with car.");

        atv.collide(taxi);
        Assertions.assertFalse(atv.getMyAlive(), "Atv lived when colliding with taxi.");

        atv.collide(truck);
        Assertions.assertFalse(atv.getMyAlive(), "Atv lived when colliding with truck.");
    }

    /** Test method for Atv setters. */
    @Test
    public void testAtvSetters() {
        final Atv atv = new Atv(10, 11, Direction.NORTH);

        atv.setX(12);
        assertEquals(12, atv.getX(), "Atv setX failed!");
        atv.setY(13);
        assertEquals( 13, atv.getY(),"Atv setY failed!");
        atv.setDirection(Direction.SOUTH);
        assertEquals( Direction.SOUTH, atv.getDirection(),"Atv setDirection failed!");
    }

    /** Test method for Atv getters. */
    @Test
    void testAtvGetters() {
        final Atv atv = new Atv(10, 11, Direction.NORTH);
        final Truck truck = new Truck(0, 0, Direction.NORTH);

        assertEquals(10, atv.getX(), "Atv getX failed!");
        assertEquals( 11, atv.getY(),"Atv getY failed!");

        assertEquals( Direction.NORTH, atv.getDirection(),"Atv getDirection failed!");

        assertTrue(atv.getMyAlive(), "Atv getMyAlive failed!");

        assertEquals("atv.gif", atv.getImageFileName(),
                "Atv getImageFileName on alive instance failed!");
        atv.collide(truck);
        assertEquals("atv_dead.gif", atv.getImageFileName(),
                "Atv getImageFileName on dead instance failed!");

        assertEquals(25, atv.getDeathTime(), "Atv getDeathTime failed!");
    }

    /** Test method for {@link Atv#poke()}. */
    @Test
    void testPoke() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        atv.collide(truck);
        assertFalse(atv.getMyAlive(), "atv survived collision with truck!");
        for (int i = 0; i <= atv.getDeathTime(); i++) {
            atv.poke();
        }
        assertTrue(atv.getMyAlive(), "poke() did not notify atv to revive!");
    }

    /** Test method for {@link Atv#reset()}. */
    @Test
    void testReset() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        atv.setX(10);
        atv.setY(15);
        atv.reset();
        assertEquals(0, atv.getX(), "Didn't reset x");
        assertEquals(0, atv.getY(), "Didn't reset y");
    }

    /** Test method for {@link Atv#canPass(Terrain, Light)}. */
    @Test
    void testCanPass() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        assertFalse(atv.canPass(Terrain.WALL, Light.GREEN));
    }

    /** Test method for {@link Atv#chooseDirection(java.util.Map)}. */
    @Test
    void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();

        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Atv atv = new Atv(0, 0, Direction.NORTH);
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = atv.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) {
                seenSouth = true;
            }
        }

        Assertions.assertTrue(seenWest && seenNorth && seenEast && seenSouth, "Atv chooseDirection() " +
                "fails to select randomly " +
                "among all possible valid choices!");
    }

    @Test
    void testToString() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        assertEquals("Atv", atv.toString(), "Atv toString failed!");
    }

}