package ss.week5.test;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * A simple class that experiments with the Hex encoding
 * of the Apache Commons Codec library.
 *
 */
public class EncodingTest {
    public static void main(String[] args) throws DecoderException {
        // Exercise 5.7
        String hello = "Hello World";
        System.out.println(Hex.encodeHexString(hello.getBytes()));
        String input = "Hello Big World";
        System.out.println(Hex.encodeHexString(input.getBytes()));

        // Exercise 5.8
        byte[] bytes = Hex.decodeHex("4d6f64756c652032");
        System.out.println(bytes);
        String result = new String(bytes);
        System.out.println(result);

        // Exercise 5.9
        byte[] base64Hello = Base64.encodeBase64(hello.getBytes());
        System.out.println(base64Hello);
        byte[] checkLen = Base64.encodeBase64(Hex.decodeHex("010203040506"));
        System.out.println("010203040506");
        System.out.println(checkLen);
        byte[] decode64 = Base64.decodeBase64("U29mdHdhcmUgU3lzdGVtcw==");
        String decode64Result = new String(decode64);
        System.out.println(decode64Result);

        for (int i = 1; i <= 10; i++) {
            char[] charArray = new char[i];
            Arrays.fill(charArray, 'a');
            // is this useless? can we do this with just chars
            String str = String.valueOf(charArray);
            String numberA = Base64.encodeBase64String(str.getBytes());
            System.out.println(String.valueOf(i) + ": " + numberA);
        }


    }
}
