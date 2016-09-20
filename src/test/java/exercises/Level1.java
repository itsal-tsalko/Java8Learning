package exercises;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
/**
 * Hint: Use forEach, filter, map,
 * */

public class Level1 {

// Exercise 1: Print out all the words in wordList, which is
// a static List<String> defined at the bottom of this file.

    @Test
    public void printAllWords() {

         wordList.forEach(word->System.out.println(word));
         wordList.forEach(System.out::println);
    }

// Exercise 2: Convert all words in wordList to upper case,
// and gather the result into an output list.

    @Test
    public void upperCaseWords() {
        List<String> output1 = wordList.stream().map(word->word.toUpperCase()).collect(Collectors.toList());
        List<String> output2 = wordList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());


        assertEquals(
                Arrays.asList(
                        "EVERY", "PROBLEM", "IN", "COMPUTER", "SCIENCE",
                        "CAN", "BE", "SOLVED", "BY", "ADDING", "ANOTHER",
                        "LEVEL", "OF", "INDIRECTION"),
                output2);
    }

// Exercise 3: Find all the words in wordList that have even length
// and put them into an output list.

    @Test
    public void findEvenLengthWords() {
        List<String> output = wordList.stream()
                .filter(word -> word.length() % 2 == 0)
                .collect(Collectors.toList());

        assertEquals(
                Arrays.asList(
                        "in", "computer", "be", "solved", "by", "adding", "of"),
                output);
    }

// Exercise 4: Count the number of lines in a file. The field *reader*
// is a BufferedReader which will be opened for you on the text file.
// See the JUnit @Before and @After methods at the bottom of this file.
// The text file is "SonnetI.txt" (Shakespeare's first sonnet) which is
// located at the root of this project.

    @Test
    public void countLinesInFile() throws IOException {
        long count = reader.lines().count();
        assertEquals(14, count);
    }

// Exercise 5: Join lines 3-4 from the text file into a single string.

    @Test
    public void joinLineRange() throws IOException {
        String output = reader.lines().skip(2).limit(2).collect(Collectors.joining());

        assertEquals(
                "But as the riper should by time decease," +
                        "His tender heir might bear his memory:",
                output);
    }

// Exercise 6: Find the length of the longest line in the file.

    @Test
    public void lengthOfLongestLine() throws IOException {

        //Use lambda as Comparator
        int longest1 = reader.lines()
                .map(String::length)
                .max((e1,e2)->e1.compareTo(e2)) //Match Comparator#compare() signature
                .get();

        //Use mathod reference as Comparator
        int longest2 = reader.lines()
                .map(String::length)
                .max(Integer::compareTo) //Match Comparator#compare() signature
                .get();

        //Use predefined Comparator.comparingInt method
        int longest3 = reader.lines()
                .map(String::length)
                .max(Comparator.comparingInt(lenght -> lenght))
                .get();

        //Use predefined Comparator.comparing method
        int longest4 = reader.lines()
                .map(String::length)
                .max(Comparator.comparing(Function.identity()))
                .get();

        //Using specialized Stream
        int longest5 = reader.lines()
                .mapToInt(String::length)
                .max()
                .getAsInt();

        assertEquals(longest4, 53);
    }

// ===== TEST INFRASTRUCTURE ==================================================

    static List<String> wordList = Arrays.asList(
            "every", "problem", "in", "computer", "science",
            "can", "be", "solved", "by", "adding", "another",
            "level", "of", "indirection");
    // Butler Lampson

    private BufferedReader reader;

    @Before
    public void setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("SonnetI.txt"), StandardCharsets.UTF_8);
    }

    @After
    public void closeBufferedReader() throws IOException {
        reader.close();
    }
}
