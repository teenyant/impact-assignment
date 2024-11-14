package numberrangesummarizer;

import java.util.Collection;

/**
 * The App class is for calling RangeSummarizer to summarize ranges of given a list of integer input.
 *
 * Assumptions made:
 * - Input can be unsorted, contain negative numbers, duplicate numbers
 * - Only integer input will be accepted otherwise throw an error
 * - Duplicate numbers are ignored
 * -
 */
public class App {

    /**
     * Validates the command line arguments and calls the summarizer.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Incorrect number of arguments or no input given");
            System.exit(1);
        }

        runSummarizer(args[0]);
    }


    /**
     * Runs the range summarizer by collecting the input correctly first then
     * summarizing the integers into ranges.
     *
     * @param input String of integers
     */
    public static void runSummarizer(String input) {
        RangeSummarizer summarizer = new RangeSummarizer();

        Collection<Integer> collect = summarizer.collect(input);

        System.out.println("\nCollected input as follows:");
        System.out.println(collect.toString());

        String output = summarizer.summarizeCollection(collect);

        System.out.println("======================");
        System.out.println("Final summarized range:");
        System.out.println(output + "\n");
    }
}
