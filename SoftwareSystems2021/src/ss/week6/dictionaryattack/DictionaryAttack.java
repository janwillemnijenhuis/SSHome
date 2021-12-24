package ss.week6.dictionaryattack;

import org.apache.commons.codec.binary.Hex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class DictionaryAttack {
	private Map<String, String> passwordMap;
	private Map<String, String> hashDictionary;

	/**
	 * Reads a password file. Each line of the password file has the form:
	 * username: encodedpassword
	 * 
	 * After calling this method, the passwordMap class variable should be
	 * filled with the content of the file. The key for the map should be
	 * the username, and the password hash should be the content.
	 * @param filename
	 */
	public void readPasswords(String filename) throws FileNotFoundException {
		passwordMap = new HashMap<>();
		Scanner sc = new Scanner(new FileReader(filename));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] contents = line.split(":");
			passwordMap.put(contents[0].trim(), contents[1].trim());
		}
	}

	/**
	 * Given a password, return the MD5 hash of a password. The resulting
	 * hash (or sometimes called digest) should be hex-encoded in a String.
	 * @param password
	 * @return
	 */
	public String getPasswordHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		String hash = null;
		try {
			md.update(password.getBytes());
			byte[] digest = md.digest();
			hash = Hex.encodeHexString(digest);
		} catch (Exception e) {
			// todo, maybe do nothing
		}
		return hash;
	}
	/**
	 * Checks the password for the user the password list. If the user
	 * does not exist, returns false.
	 * @param user
	 * @param password
	 * @return whether the password for that user was correct.
	 */
	public boolean checkPassword(String user, String password) throws NoSuchAlgorithmException {
		// check of user exists
		return passwordMap.get(user).equals(getPasswordHash(password));
	}

	/**
	 * Reads a dictionary from file (one line per word) and use it to add
	 * entries to a dictionary that maps password hashes (hex-encoded) to
     * the original password.
	 * @param filename filename of the dictionary.
	 */
	public void addToHashDictionary(String filename) throws FileNotFoundException, NoSuchAlgorithmException {
    	Scanner sc = new Scanner(new FileReader(filename));
		hashDictionary = new HashMap<>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			hashDictionary.put(getPasswordHash(line), line);
		}
    }

	/**
	 * get the password corresponding to a certain hash from the file of most common passwords
	 * @param hash
	 * @return the password
	 */
	public String getDictionaryValue(String hash) {
		return hashDictionary.get(hash);
	}

	/**
	 * Do the dictionary attack.
	 */
	public void doDictionaryAttack() {
		Set<String> users = new HashSet<>(passwordMap.keySet());
		for (String user: users) {
			String hash = passwordMap.get(user);
			if (hashDictionary.containsKey(hash)) {
				System.out.format("username: %s => password: %s\n", user, hashDictionary.get(hash));
			}
		}
	}

	public boolean doBruteForceAttack(String user, int passLength) throws NoSuchAlgorithmException {
		String hash = passwordMap.get(user);
		char[] alphabet =  "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Set<String> triedPasswords = new HashSet<>();
		for (int i = 0; i < (Math.pow(26, passLength)); i++) {
			String tempPassword = null;
			do {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < 4; j++) {
					sb.append(alphabet[(int) (Math.random() * 26)]);
				}
				tempPassword = sb.toString();
			} while (triedPasswords.contains(tempPassword));
			triedPasswords.add(tempPassword);
			String tempHash = getPasswordHash(tempPassword);
			if (tempHash.equals(hash)) {
				System.out.format("username: %s => password: %s\n", user, tempPassword);
				return true;
			}
		}
		return false;
	}

	public void averageBruteForceTime(int attempts, String user, int passLength) throws NoSuchAlgorithmException {
		long sum = 0;
		for (int i = 0; i < attempts; i++) {
			long start = System.currentTimeMillis();
			doBruteForceAttack(user, passLength);
			long end = System.currentTimeMillis();
			sum += end - start;
		}
		System.out.format("It takes on average %.4f seconds to brute force the password.\n", (sum / (long) attempts)/ 1000.0);
	}

	public void findPassword(int startlength, int endlength, String user) throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
		int i = startlength;
		System.out.format("Trying length: %d\n", i);
		while (!doBruteForceAttack(user, i)) {
			if (i == endlength) {
				System.out.format("We haven't cracked the password.\n");
				break;
			}
			long temptime = System.currentTimeMillis();
			System.out.format("Time elapsed: %.4f seconds\nTrying length: %d.\n", (temptime - start) / 1000.0, i + 1);
			i++;
		}
		long end = System.currentTimeMillis();
		System.out.format("Time elapsed: %.4f seconds\n", (end - start) / 1000.0);
	}

	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
		DictionaryAttack da = new DictionaryAttack();
		da.readPasswords("SoftwareSystems2021\\src\\ss\\week6\\test\\LeakedPasswords.txt");
		da.addToHashDictionary("SoftwareSystems2021\\src\\ss\\week6\\test\\MostCommonPasswords.txt");
		da.doDictionaryAttack();
		/// Exercise 6.6 ///
		// we have to compute the average time until the word is found
		// we expect to find word in 26^4 = 456976 iterations
		// the expected time until a certain word is found Z ~ EXP(l) distribution, where l = 1 as we expect 1 finding in 26^4 iterations
		// then the probability to wait longer than T: Pr(T > t) = exp(-n/time * t) = exp(-1/26^4 * t). We want to find t, the time until we have found this word in 50% of the cases we do this.
		// this then results in 0.50 = exp(-1/26^4 * t) => t = 317344 items
		/// Exercise 6.7 ///
		da.doBruteForceAttack("alice", 4); // finds the password of alice using brute force
		da.averageBruteForceTime(100, "alice", 4); // finds the password of
		da.findPassword(3, 10, "michael"); // brute forcing michaels password for a max of 10 iterations.
	}

}
