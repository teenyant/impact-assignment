package numberrangesummarizer;

import java.util.*;

public class RangeSummarizer implements NumberRangeSummarizer {

    public Collection<Integer> collect(String input) {
        String[] numbersString = input.split(",");
        Set<Integer> numberSet = new TreeSet<>();

        for (int i = 0; i < numbersString.length; i++) {
            try {
                int num = Integer.parseInt(numbersString[i]);
                numberSet.add(num);
            } catch (NumberFormatException e) {
                System.err.print(e);
                System.exit(1);
            }
        }

        System.out.println("Set results " + numberSet.toString());

        return numberSet;
    }

    public String summarizeCollection(Collection<Integer> input) {

        return null;
    }
}
