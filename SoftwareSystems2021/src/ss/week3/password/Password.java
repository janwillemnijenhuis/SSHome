package ss.week3.password;

public class Password {
    public static final String INITIAL = "rootadmin123";
    private String password;
    private Checker checker;
    private String initPass;
    private Password p;

    /**
     * Constructor of the Password class with a given checker
     */
    public Password(Checker checker) {
        this.checker = checker;
        this.initPass = INITIAL;
        this.password = this.initPass;
    }

    /**
     * Constructor of the Password class without arguments
     */
    public Password() {
        this(new BasicChecker());
    }

    /**
     * @return the initial password
     */
    public String getInitPass() {
        return this.initPass;
    }

    /**
     * @return the checker implementation
     */
    public Checker getChecker() {
        return this.checker;
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
    public boolean testWord(String test){
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