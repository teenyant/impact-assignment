package numberrangesummarizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    RangeSummarizer summarizer;

    protected void setUp() {
        summarizer = new RangeSummarizer();
    }

    private String readFile(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Tests the given example
     *
     * Input: 1,3,6,7,8,12,13,14,15,21,22,23,24,31
     * Output: 1, 3, 6-8, 12-15, 21-24, 31
     */
    public void test1_GivenExample() throws Exception {
        String input = readFile("src/test/resources/input1.txt");
        String expectedOutput = readFile("src/test/resources/output1.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests the given example
     */
    public void test2_Simple() throws Exception {
        String input = readFile("src/test/resources/input2.txt");
        String expectedOutput = readFile("src/test/resources/output2.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests the given example
     */
    public void test3_Duplicates() throws Exception {
        String input = readFile("src/test/resources/input3.txt");
        String expectedOutput = readFile("src/test/resources/output3.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests the given example
     */
    public void test4_EmptyString() throws Exception {
        String input = "";
        String expectedOutput = "";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    /**
     * Tests the given example
     */
    public void test5_SingleNumber() throws Exception {
        String input = "15";
        String expectedOutput = "15";

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }


    public void test6_NegativeNumbers() throws Exception {
        String input = readFile("src/test/resources/input4.txt");
        String expectedOutput = readFile("src/test/resources/output4.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        assertEquals(expectedOutput, output);
    }

    public void test7_Invalid() throws Exception {
        String input = "1,2,a,b,c,4";

        try {
            summarizer.collect(input);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Invalid number format"));
        }
    }

    public void test8_LongTest() throws Exception {
        String input = readFile("src/test/resources/input5.txt");
        String expectedOutput = readFile("src/test/resources/output5.txt");

        Collection<Integer> collect = summarizer.collect(input);
        String output = summarizer.summarizeCollection(collect);

        System.out.println("")

        assertEquals(expectedOutput.trim(), output);
    }
}
