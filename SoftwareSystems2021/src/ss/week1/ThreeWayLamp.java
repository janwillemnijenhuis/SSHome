package ss.week1;
import ss.utils.TextIO;
import java.util.Arrays;

public class ThreeWayLamp {

    public enum State {
        OFF, LOW, MEDIUM, HIGH, STATE, NEXT, HELP, EXIT
    }

    public static State LampSetting = State.OFF;

    public static void main(String[] args) {

        System.out.println("Menu:" + Arrays.asList(State.values())); // display the menu
        boolean exit = false; // set exit status to false

        while(!exit) {
            System.out.println("Please provide the desired state: ");
            String input = TextIO.getlnString();
            switch(input) {
                case "OFF":
                    setLampSetting(State.OFF);
                    break;
                case "LOW":
                    setLampSetting(State.LOW);
                    break;
                case "MEDIUM":
                    setLampSetting(State.MEDIUM);
                    break;
                case "HIGH":
                    setLampSetting(State.HIGH);
                    break;
                case "STATE":
                    System.out.println(getSetting());
                    break;
                case "NEXT":
                    switchSetting();
                    break;
                case "HELP":
                    System.out.println("Menu:" + Arrays.asList(State.values()));
                    break;
                case "EXIT":
                    exit = true;
                    break;
                default:
                    System.out.println("Error: Invalid input. Please choose from the menu.");
                    break;
            }
        }
    }

    public static void switchSetting() {

        switch(LampSetting) {
            case OFF:
                LampSetting = State.LOW;
                break;
            case LOW:
                LampSetting = State.MEDIUM;
                break;
            case MEDIUM:
                LampSetting = State.HIGH;
                break;
            case HIGH:
                LampSetting = State.OFF;
        }
    }

    public static State getSetting() {
        return LampSetting;
    }

    public static void setLampSetting(State val) {
        LampSetting = val;
    }
}