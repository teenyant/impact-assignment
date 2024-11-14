package numberrangesummarizer;

import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println( "Hello World!" );

        if (args.length != 1) {
            System.err.println("Incorrect number of arguments or no input given");
            System.exit(1);
        }

        runSummarizer(args[0]);
    }


    public static void runSummarizer(String input) {

        RangeSummarizer summarizer = new RangeSummarizer();
        Collection<Integer> collect = summarizer.collect(input);

    }
}
