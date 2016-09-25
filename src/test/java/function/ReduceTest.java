package function;

import jdk.nashorn.internal.objects.annotations.Function;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class ReduceTest {

    @Test
    public void reduceWithBinaryOperator(){

        List<Integer> list = Arrays.asList(1,2,3);
        //T apply(T t, T u) - BinaryOperator<T>
        System.out.println(list.stream().reduce((a, b) -> a + b).get());

        list.stream().mapToInt(l->l).sum();
    }

    @Test
    public void reduceWithBinaryOperatorAndIdentity(){

        List<Integer> list = Arrays.asList(1,2,3);

        System.out.println(list.stream().reduce(10, (a, b) -> a + b));

    }

    @Test
    public void reduceWithBinaryOperatorAndIdentityAndBiFunction(){

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        System.out.println(list.stream().reduce("", (String a, Integer b) -> a + b, String::concat));
        list.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
