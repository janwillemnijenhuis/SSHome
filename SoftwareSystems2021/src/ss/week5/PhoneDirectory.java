package ss.week5;

import java.util.Set;
import java.util.TreeMap;

/**
    * A PhoneDirectory holds a list of names with a phone number for
    * each name. It is possible to find the number associated with
    * a given name, and to specify the phone number for a given name.
    */
public class PhoneDirectory {
    /**
     * An object of type PhoneEntry holds one name/number pair.
     */
    private static class PhoneEntry {
        String name; // The name.
        String number; // The associated phone number.
    }
    private TreeMap<String, String> data;
    //private PhoneEntry[] data; // Array that holds the name/number pairs.
    private int dataCount; // The number of pairs stored in the array.
    /**
     * Constructor creates an initially empty directory.
     */
    public PhoneDirectory() {
        data = new TreeMap<>();
        dataCount = 0;
    }
    /**
     * Looks for a name/number pair with a given name. If found, the index
     * of the pair in the data array is returned. If no pair contains the
     * given name, then the return value is -1. This private method is
     * used internally in getNumber() and putNumber().
     */
    private String find( String name ) {
        Set<String> keys = data.keySet();
        for (String k : keys) {
            if (k.equals(name)) {
                return data.get(k); // The name has been found in position i.
            }
        }
        return null; // The name does not exist in the array.
    }
    /**
     * Finds the phone number, if any, for a given name.
     * @return The phone number associated with the name; if the name does
     * not occur in the phone directory, then the return value is null.
     */
    public String getNumber(String name) {
        return find(name);
    }

    /**
     * Associates a given name with a given phone number. If the name
     * already exists in the phone directory, then the new number replaces
     * the old one. Otherwise, a new name/number pair is added. The
     * name and number should both be non-null. An IllegalArgumentException
     * is thrown if this is not the case.
     */
    public void putNumber( String name, String number ) {
        if (name == null || number == null)
            throw new IllegalArgumentException("name and number cannot be null");
        if (find(name) == null) {
            data.put(name, number);
            dataCount++;
        }
    }
} // end class PhoneDirectory

