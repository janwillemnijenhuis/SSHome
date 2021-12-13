package ss.week5;

public class Zipper {
	/**
	 * @return the zipped result String
	 */
    /*@ requires s1 != null & s2 != null;
    requires s1.length() == s2.length();
     @*/
    public static String zip(String s1, String s2) {
        String result = "";
        for (int i = 0; i < s1.length(); i++) {
            result += Character.toString(s1.charAt(i))
                      + Character.toString(s2.charAt(i));
        }
        return result;
    }

    public static String zip2(String s1, String s2) throws TooFewArgumentsException, ArgumentLengthsDifferException {
        // ik doe dit nu met if else, of moet try catch?
        if (s1 == null || s2 == null) {
            throw new TooFewArgumentsException("error: too few command line arguments");
        } else if (s1.length() != s2.length()) {
            throw new ArgumentLengthsDifferException("error: length of command line arguments "
                    + "differ (" + s1.length() + ", " + s2.length() + ")");
        } else {
            String result = "";
            for (int i = 0; i < s1.length(); i++) {
                result += Character.toString(s1.charAt(i))
                        + Character.toString(s2.charAt(i));
            }
            return result;
        }
    }
    

    public static void main(String[] args) throws TooFewArgumentsException, ArgumentLengthsDifferException {
        String s1 = args.length >= 1 ? args[0] : null;
        String s2 = args.length >= 2 ? args[1] : null;
        System.out.println(zip2(s1, s2));
    }
}
