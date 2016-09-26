package exercises;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class Level2 {

    private List<Integer> numbers;

    @Before
    public void setUpBufferedReader() throws IOException {
        numbers = Arrays.asList(2, 8, 5, 1, 6, 4, 9, 7, 8, 4, 0);
    }

    /**
     * Exercise 1
     * Given: List of integers - "numbers". Using streams filter even values and output the maximal one.
     * 1) using stream max
     * 2) using IntStream max
     * 3) using stream reduce and inside Integer max function
     * 4) using Collectors max
     * 5) using Collectors reducing + Integer max function
     * 6) using Collectors summarizing
     */

    @Test
    public void filterEvenMax() {

        // TODO: write your implementation instead of nulls and 0

        // given
        Predicate<Integer> getEven = number -> number % 2 == 0;

        // when
        // 1)
        int maxInStream = numbers.stream().filter(getEven).max(Integer::compare).get();

        // 2)
        int maxInIntStream = numbers.stream().filter(getEven).mapToInt(n -> n).max().getAsInt();

        // 3.1)
        int maxUsingReduce = numbers.stream().filter(getEven).reduce(Integer::max).get();

        // 3.2)
        int maxUsingCollectorsReduceOwn = numbers.stream().filter(getEven).reduce(Integer.MIN_VALUE, Integer::max);
        // 4)
        int maxUsingComparatorMax = numbers.stream().filter(getEven).collect(Collectors.maxBy(Integer::compare)).get();


        // 5)
        int maxUsingCollectorsReduce = numbers.stream().filter(getEven).collect(Collectors.reducing(Integer::max)).get();

        // 6)
        int maxUsingCollectorsSummarizing = numbers.stream().filter(getEven).collect(Collectors.summarizingInt(n->n)).getMax();

                // then
                assertEquals(8, maxInStream);
        assertEquals(8, maxInIntStream);
        assertEquals(8, maxUsingReduce);
        assertEquals(8, maxUsingCollectorsReduceOwn);
        assertEquals(8, maxUsingComparatorMax);
        assertEquals(8, maxUsingCollectorsReduce);
        assertEquals(8, maxUsingCollectorsSummarizing);
    }
}
