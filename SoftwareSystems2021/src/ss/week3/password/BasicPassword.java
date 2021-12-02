package ss.week3.password;

public class BasicPassword {
    public static final String INITIAL = "INITIAL";
    private String password;

    /**
     * Constructor of the BasicPassword class
     */
    public BasicPassword() {
        this.password = INITIAL;
    }

    /**
     * Tests if a given string is an acceptable password
     *
     * @param suggestion: suggested password
     * @return true if the password is acceptable
     */
    public boolean acceptable(String suggestion) {
        if(suggestion.contains(" ") || suggestion.length() < 6) {
            return false;
        }
        return true;
    }

    /**
     * Tests if a given word is equal to the current password
     *
     * @param test: given password by user
     * @return true if password is correct
     */
    public boolean testWord(String test) {
        if(test.equals(this.password)){
            return true;
        }
        return false;
    }

    /**
     * Changes the current password
     *
     * @param oldpass: old password
     * @param newpass: new password
     * @return true if successfully changed password
     */
    public boolean setWord(String oldpass, String newpass) {
        if(this.acceptable(newpass) && this.testWord(oldpass)) {
            this.password = newpass;
            return true;
        }
        return false;
    }

}