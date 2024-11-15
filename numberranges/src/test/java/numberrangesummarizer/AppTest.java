package numberrangesummarizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * Unit tests for the RangeSummarizer class to verify functionality.
 */
public class AppTest extends TestCase {

    private RangeSummarizer summarizer;

    /**
     * Sets up the RangeSummarizer instance before each test.
     */
    protected void setUp() {
        summarizer = new RangeSummarizer();
    }

    /**
     * Reads a file and returns its content as a string.
     *
     * @param filePath the path to the file
     * @return the file content as a string
     * @throws Exception if an I/O error occurs
     */
    private String readFile(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Test for the provided example case with expected range output.
     */
    public void test1_GivenExample() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedOutput = "1, 3, 6-8, 12-15, 21-24, 31";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests a simple list of numbers with sequential ranges.
     */
    public void test2_Simple() {
        String input = "1,2,3,6,7,8,10";
        String expectedOutput = "1-3, 6-8, 10";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of duplicate numbers in the input.
     */
    public void test3_Duplicates() {
        String input = "1,2,2,3,4,4,5,7,8,10,10,11";
        String expectedOutput = "1-5, 7-8, 10-11";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of an empty string, expecting an empty output.
     */
    public void test4_EmptyString() {
        String input = "";
        String expectedOutput = "";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of a single number, expecting the output to be the number itself.
     */
    public void test5_SingleNumber() {
        String input = "15";
        String expectedOutput = "15";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of negative numbers in sequential order.
     */
    public void test6_NegativeNumbers() {
        String input = "-10,-9,-8,-7,-6,-6,-4,-3,-2,-1,0";
        String expectedOutput = "-10--6, -4-0";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of invalid input containing non-integer characters.
     * Expects a RuntimeException to be thrown.
     */
    public void test7_Invalid() {
        String input = "1,2,a,b,c,4";

        try {
            summarizer.collect(input);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Invalid number format"));
        }
    }

    /**
     * Tests a large list of integers (size of 10000) read from a file.
     */
    public void test8_BigTest() throws Exception {
        String input = readFile("src/test/resources/bigtestInput.txt");
        String expectedOutput = readFile("src/test/resources/bigtestOutput.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput.trim(), output.trim());
    }

    /**
     * Tests a large list of integers (size of 1000) with no duplicates read from a file.
     */
    public void test9_BigTestNoDuplicates() throws Exception {
        String input = readFile("src/test/resources/bigtestnodupesInput.txt");
        String expectedOutput = readFile("src/test/resources/bigtestnodupesOutput.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput.trim(), output);
    }

    /**
     * Tests handling of a null input, expecting an empty output.
     */
    public void test10_Null() {
        String expectedOutput = "";

        Collection<Integer> collect = summarizer.collect(null);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of a list with no sequential integers, expecting the list back.
     */
    public void test11_NotSequential() {
        String input = "1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99";
        String expectedOutput = "1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests handling of a reverse-ordered list, expecting it to sort the numbers.
     */
    public void test12_ReverseOrder() {
        String input = "20,16,15,14,13,12,8,7,6,3,1";
        String expectedOutput = "1, 3, 6-8, 12-16, 20";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     *  Tests handling of extra commas in the input, expecting to ignore them.
     */
    public void test13_ExtraCommas() {
        String input = ",1,2,,3,4,,";
        String expectedOutput = "1-4";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests an invalid input, expecting runtime exception.
     */
    public void test14_OnlyInvalid() {
        String input = "a,b,,c,,";
        try {
            summarizer.collect(input);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Invalid number format"));
        }
    }

    /**
     * Tests very large integers as input.
     */
    public void test15_LargeIntegers() {
        String input = "2147483647,2147483646,2147483645";
        String expectedOutput = "2147483645-2147483647";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }
}
