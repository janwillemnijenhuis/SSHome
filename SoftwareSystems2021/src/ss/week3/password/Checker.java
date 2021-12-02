package ss.week3.password;

import java.lang.Character;

interface Checker {

    public static final String INITIAL = "INITIAL123"; // initialize valid initial password

    /**
     * Checks if the parameter is an acceptable string
     *
     * @param suggestion: the suggested password
     * @return true if parameter is an acceptable string
     */
    boolean acceptable(String suggestion);

    /**
     * @return an acceptable password
     */
    String generatePassword();
}

class BasicChecker implements Checker {

    public boolean acceptable(String suggestion) {
        if(suggestion.contains(" ") || suggestion.length() < 6) {
            return false;
        }
        return true;
    }

    public String generatePassword() {
        return INITIAL;
    }
}

class strongChecker implements Checker {

    public boolean acceptable(String suggestion) {
        if(     suggestion.contains(" ") ||
                suggestion.length() < 6 ||
                !Character.isLetter(suggestion.charAt(0)) ||
                !Character.isDigit(suggestion.charAt(suggestion.length() - 1))
        ) {
            return false;
        }
        return true;
    }

    public String generatePassword() {
        return INITIAL;
    }
}