package numberrangesummarizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * The App class is for calling RangeSummarizer to summarize ranges of given a list of integer input.
 *
 * Assumptions made:
 * - Input can be unsorted, contain negative numbers, duplicate numbers
 * - Only integer input will be parsed otherwise throw an error
 * - Duplicate numbers are ignored
 * - Automatically removes trailing commas
 */
public class App {

    /**
     * Validates the command line arguments or asks for input and calls the summarizer.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            String input;
            if (args.length == 1) {
                input = getInput(args[0]);
            } else {
                input = getInput(promptForInput());
            }
            runSummarizer(input);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Determines if the input is a file path and reads content if it's a valid file.
     *
     * @param input The command line argument or user input
     * @return The input string for summarization
     * @throws Exception if an error occurs while reading the file
     */
    private static String getInput(String input) throws Exception {
        Path path = Paths.get(input);
        if (Files.exists(path)) {
            return new String(Files.readAllBytes(path)).trim();
        } else {
            return input;
        }
    }

    /**
     * Prompts the user to provide input via standard input.
     *
     * @return The input string for summarization
     * @throws Exception if an error occurs while reading input
     */
    private static String promptForInput() throws Exception {
        System.out.println("Please enter a comma-separated list of numbers or a file path:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine().trim();
    }

    /**
     * Runs the range summarizer by collecting the input correctly first then
     * summarizing the integers into ranges.
     *
     * @param input String of integers
     */
    public static void runSummarizer(String input) {
        RangeSummarizer summarizer = new RangeSummarizer();

        try {
            Collection<Integer> collect = summarizer.collect(input);

            System.out.println("\nCollected input as follows:");
            System.out.println(collect.toString());

            String output = summarizer.summarizeCollection(collect);

            System.out.println("======================");
            System.out.println("Final summarized range:");
            System.out.println(output + "\n");
        } catch (RuntimeException e) {
            System.err.println("Error processing input: " + e.getMessage());
        }
    }
}
