package numberrangesummarizer;

import java.util.*;

/**
 * RangeSummarizer implements the NumberRangeSummarizer interface to collect input and summarize the ranges
 * of the given input (comma-delimited).
 *
 * The class parses the input into a collection of sorted integers without duplicates and then formats the
 * sequences of integers in their ranges.
 */
public class RangeSummarizer implements NumberRangeSummarizer {

    /**
     * Parses the input string to get all the numbers individually and remove duplicates. Returns
     * the list in sorted order.
     *
     * @param input Comma-delimited string of integer values
     * @return Sorted collection of unique integers
     * @throws RuntimeException if input contains non-integer values
     */
    public final Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String[] numbersString = input.split(",");
        // TreeSet automatically sorts and removes duplicates
        Set<Integer> numberSet = new TreeSet<>();

        for (int i = 0; i < numbersString.length; i++) {
            numbersString[i] = numbersString[i].trim();

            if (!numbersString[i].isEmpty()) {
                try {
                    int num = Integer.parseInt(numbersString[i].trim());
                    numberSet.add(num);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Invalid number format");
                }
            }
        }

        return numberSet;
    }

    /**
     * Summarizes a sorted collection of integers without duplicates by grouping integers that are
     * in sequence into ranges.
     *
     * @param input Sorted Collection of unique integers
     * @return Comma-delimited string with summarized ranges
     */
    public final String summarizeCollection(Collection<Integer> input) {
        if (input.isEmpty()) {
            return "";
        }

        StringBuilder output = new StringBuilder();

        Iterator<Integer> itr = input.iterator();

        // Start and end of current range
        int start = itr.next();
        int end = start;

        while (itr.hasNext()) {
            int curr = itr.next();

            // Check if current number is not sequential to previous one
            if (curr != end + 1) {
                if (start == end) {
                    output.append(start);
                } else {
                    output.append(start).append("-").append(end);
                }
                output.append(", ");

                // Start new range
                start = curr;
            }
            end = curr;
        }

        // Check last range/number
        if (start == end) {
            output.append(start);
        } else {
            output.append(start).append("-").append(end);
        }

        return output.toString();
    }
}
