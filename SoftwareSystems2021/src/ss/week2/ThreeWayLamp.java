package ss.week2;

import ss.utils.TextIO;
import ss.week1.ThreeWayLamp.State;

import java.util.Arrays;

public class ThreeWayLamp {

    private State LampSetting;
    private final State defaultState = State.OFF;
    private boolean exit = false;

    //@ private invariant defaultState == State.OFF;

    public ThreeWayLamp() {
        this.LampSetting = defaultState;
    }

    public void askInput() {
        this.printMenu();
        while(!exit) {
            System.out.println("Please provide the desired state: ");
            String input = TextIO.getlnString();
            switch(input) {
                case "OFF":
                    this.setState(State.OFF);
                    break;
                case "LOW":
                    this.setState(State.LOW);
                    break;
                case "MEDIUM":
                    this.setState(State.MEDIUM);
                    break;
                case "HIGH":
                    this.setState(State.HIGH);
                    break;
                case "STATE":
                    System.out.println(this.getState());
                    break;
                case "NEXT":
                    this.setNext();
                    break;
                case "HELP":
                    this.printMenu();
                    break;
                case "EXIT":
                    this.exitProgram();
                    break;
                default:
                    System.out.println("Error: Invalid input. Please choose from the menu.");
                    break;
            }
        }
    }

    // Changes the current state of the lamp to a new state
    /*@
        requires newState instanceof State;
        ensures this.LampSetting == newState;
    */
    public void setState(State newState){
        this.LampSetting = newState;
    }

    // set lamp to next setting in the order: OFF, LOW, MEDIUM, HIGH, OFF, ..., etc.
    /*@
        ensures \old(this.LampSetting) == State.OFF ==> this.LampSetting == State.LOW;
        ensures \old(this.LampSetting) == State.LOW ==> this.LampSetting == State.MEDIUM;
        ensures \old(this.LampSetting) == State.MEDIUM ==> this.LampSetting == State.HIGH;
        ensures \old(this.LampSetting) == State.HIGH ==> this.LampSetting == State.OFF;
    */
    public void setNext() {
        switch (this.LampSetting) {
            case OFF:
                this.LampSetting = State.LOW;
                break;
            case LOW:
                this.LampSetting = State.MEDIUM;
                break;
            case MEDIUM:
                this.LampSetting = State.HIGH;
                break;
            case HIGH:
                this.LampSetting = State.OFF;
                break;
            default:
                break;
        }
    }

    // get current state of the lamp
    /*@
        ensures \result instanceof State;
        ensures \result == this.LampSetting;
    */
    public State getState() {
        return this.LampSetting;
    }

    // print the menu
    /*@
        requires this.LampSetting == State.HELP;
    */
    public void printMenu() {
        System.out.println("Menu:" + Arrays.asList(State.values()));
    }

    // exit the program
    /*@
        requires this.LampSetting == State.EXIT;
    */
    public void exitProgram() {
        this.exit = true;
    }


}