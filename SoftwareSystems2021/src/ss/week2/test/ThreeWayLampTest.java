package ss.week2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ss.week2.ThreeWayLamp;
import ss.week1.ThreeWayLamp.State;

public class ThreeWayLampTest {

    private static ThreeWayLamp threeWayLamp;

    @BeforeEach
    public void setUp() {
        threeWayLamp = new ThreeWayLamp();
    }

    @Test
    public void getDefaultSettingTest() {
        // check if the current state is the default state (OFF)
        assertEquals(threeWayLamp.getState(), State.OFF);
    }
    @Test
    public void nextSwitchesCorrectlyTest() {
        // check if the state is successfully switched from OFF to LOW
        threeWayLamp.setNext();
        assertEquals(threeWayLamp.getState(), State.LOW);

        // check if the state is successfully switched from LOW to MEDIUM
        threeWayLamp.setNext();
        assertEquals(threeWayLamp.getState(), State.MEDIUM);

        // check if the state is successfully switched from MEDIUM to HIGH
        threeWayLamp.setNext();
        assertEquals(threeWayLamp.getState(), State.HIGH);

        // check if the state is successfully switched from HIGH to OFF
        threeWayLamp.setNext();
        assertEquals(threeWayLamp.getState(), State.OFF);
    }
}