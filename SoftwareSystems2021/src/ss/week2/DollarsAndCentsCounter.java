package ss.week2;

public class DollarsAndCentsCounter {

    private int dollarCount = 0;
    private int centCount = 0;

    /*@
        private invariant dollarCount >= 0;
        private invariant centCount >= 0;
        private invariant centCount <= 99;
    */

    // counts the amount of dollars
    /*@
        ensures \result >= 0;
    */
    public int dollars() {
        return this.dollarCount;
    }

    // counts the amount of cents
    /*@
        ensures \result >= 0;
        ensures \result <= 99;
    */
    public int cents() {
        return this.centCount;
    }

    // adds an amount of dollars and cents to the counter
    /*@
        requires dollars >= 0;
        requires cents >= 0;
    */
    public void add(int dollars, int cents) {
        this.centCount += cents;
        if(this.centCount > 99) {
            this.dollarCount += this.centCount / 100;
            this.centCount = this.centCount % 100;
        }
        this.dollarCount += dollars;
    }

    // resets the counter to have 0 dollars and 0 cents
    /*@
        ensures this.dollarCount == 0;
        ensures this.centCount == 0;
    */
    public void reset() {
        this.dollarCount = 0;
        this.centCount = 0;
    }
}