package model;

import java.util.Random;

public abstract class AbstractVehicle implements Vehicle {
    final static Random random = new Random();
    Thread thread;
}
