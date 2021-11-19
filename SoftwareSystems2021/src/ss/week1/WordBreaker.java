package ss.week1;

import ss.utils.TextIO;
import java.util.Arrays;
/*
    Write a program that reads one line of input text and breaks it up into words. The (solution)
    words should be output one per line. A word is defined to be a sequence of letters. Any
    characters in the input that are not letters should be discarded.
    An improved version of the program would list “that’s” as a single word. An apostrophe
    can be considered to be part of a word if there is a letter on each side of the apostrophe.
    To test whether a character is a letter, you might use (ch >= ’a’ && ch <= ’z’) ||
    (ch >= ’A’ && ch <= ’Z’). However, this only works in English and similar languages.
    A better choice is to call the standard function Character.isLetter(ch), which returns
    a boolean value of true if ch is a letter and false if it is not. This works for any Unicode
    character
 */

public class WordBreaker {
    public static void main(String[] args) {
        System.out.println("Please input a sentence.");
        String[] input = TextIO.getlnString().split(" ");
        String[] valid = {",", ".", "?", "!", ";", ":"};
        for(int i = 0; i < input.length; i++) {
            String word = input[i];
            boolean completeWord = true;
            for(int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                boolean isChar = Character.isLetter(c);
                if(!isChar) {
                    if(!Arrays.asList(valid).contains(Character.toString(c))) {
                        completeWord = false;
                        break;
                    }
                    else {
                        word = word.replace(Character.toString(c), "");
                    }
                }
            }
            if(completeWord) {
                System.out.format("%s%n", word);
            }
        }
    }
}