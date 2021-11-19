package ss.week1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import ss.week1.ThreeWayLamp;

public class ThreeWayLampTest {

    @Test
    void setAndGetSettingTest() {
        // Set Lamp to LOW and check if it was really set to LOW.
        ThreeWayLamp.setLampSetting(ThreeWayLamp.LampSetting.LOW);
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.LOW);

        // Set Lamp to MEDIUM and check if it was really set to MEDIUM.
        ThreeWayLamp.setLampSetting(ThreeWayLamp.LampSetting.MEDIUM);
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.MEDIUM);

        // Set Lamp to HIGH and check if it was really set to HIGH.
        ThreeWayLamp.setLampSetting(ThreeWayLamp.LampSetting.HIGH);
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.HIGH);

        // Set Lamp to OFF and check if it was really set to OFF.
        ThreeWayLamp.setLampSetting(ThreeWayLamp.LampSetting.OFF);
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.OFF);
    }

    @Test
    void switchAndGetSettingTest() {
        // First set the Lamp to OFF
        ThreeWayLamp.setLampSetting(ThreeWayLamp.LampSetting.OFF);

        // Go to next state and check if it is set to LOW.
        ThreeWayLamp.switchSetting();
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.LOW);

        // Go to next state and check if it is set to MEDIUM.
        ThreeWayLamp.switchSetting();
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.MEDIUM);

        // Go to next state and check if it is set to HIGH.
        ThreeWayLamp.switchSetting();
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.HIGH);

        // Go to next state and check if it is set to OFF.
        ThreeWayLamp.switchSetting();
        assertEquals(ThreeWayLamp.getSetting(), ThreeWayLamp.LampSetting.OFF);
    }

}
