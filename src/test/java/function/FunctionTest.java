package function;

import function.Annotations.Declaration;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static function.Annotations.*;
import static java.util.stream.Collectors.toList;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class FunctionTest {

    @Test
    @BeforeJava8
    @Declaration
    public void functionUsingAnonymousClass() throws Exception {
        Function<String, Integer> function  = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        };
    }

    @Test
    @BeforeJava8
    @Declaration
    public void functionUsingConcreteClass() throws Exception {
        class MyFunction implements Function<String, Integer>{
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        }
        class MyGenericFunction<T> implements Function<T,String>{
            @Override
            public String apply(T t) {
                return t.toString();
            }
        }

        MyFunction myFunction = new MyFunction();
        MyGenericFunction<Boolean> myGenericFunction1 = new MyGenericFunction<>();
        MyGenericFunction<Integer> myGenericFunction2 = new MyGenericFunction<>();
    }

    @Test
    @Java8
    @Declaration
    public void functionUsingLambda() throws Exception {
        //R apply(T t); => Integer apply(String t);
        Function<String, Integer> function1 = str -> Integer.valueOf(str);
        Function<String, Integer> function2 = (String str) -> Integer.valueOf(str);
        Function<String, Integer> function3 = (String str) -> {
            int i = Integer.parseInt(str);
            return i;
        };
    }

    @Test
    @Java8
    @Declaration
    public void functionUsingMethodReference() throws Exception {
        //R apply(T t); => Integer apply(String t);
        Function<String, Integer> function1 = Integer::valueOf;
        //Function<String, Boolean> function2 = Integer::valueOf; //Doesn't compile
        Function<String, Boolean> function3 = this::toBoolean;
    }

    private Boolean toBoolean(String str){
        return Boolean.valueOf(str);
    }

    @Test
    @Java8
    @Usage
    public void functionUsage() throws Exception {
        Function<String, Integer> function1 = Integer::valueOf;
        function1.apply("10");
    }

    @Test
    @Java8
    @Usage
    public void usingFunctionInStream() throws Exception {
        Function<String, Integer> function1 = Integer::valueOf;
        List<String> list = Arrays.asList("1", "2");
        List<Integer> ints = list.stream().map(function1).collect(toList());
    }

    @Test
    @Java8
    @Usage
    public void chainingFunctions() throws Exception {
        //String -> Integer
        Function<String, Integer> f1 = Integer::valueOf;
        //String -> Integer
        Function<Integer, String> f2 = i -> i.toString();

        //String -> Integer -> String
        //f1() -> f2()
        //y = f2(f1(x))
        Function<String, String> chainOfTwoFunctions =
                f1.andThen(f2);
        System.out.println(chainOfTwoFunctions.apply("10"));

        //String -> Integer -> String -> Integer
        Function<String, Integer> chainOfThreeFunctions =
                f1.andThen(f2).andThen(f1);
        System.out.println(chainOfThreeFunctions.apply("10"));

        System.out.println(f1.andThen(f2).apply("10"));
    }

    @Test
    @Java8
    @Usage
    public void functionalProgramming() throws Exception {
        Function<Integer, Integer> f = i -> i + 1;
        //1 -> 1 + 1 = 2 -> 2 + 1 = 3
        int sum = f.andThen(f).apply(1); //3
        System.out.println(sum);
        sum = f.andThen(f).andThen(f).apply(1); //4
        System.out.println(sum);
    }

    @Test
    @Java8
    @Usage
    public void composeMethod() throws Exception {
        Function<String, Integer> f1 = Integer::valueOf;
        Function<Integer, String> f2 = String::valueOf;
        //Integer -> String -> Integer
        //f2() -> f1()
        //y = f1(f2(x))
        f1.compose(f2).apply(10);
    }

    @Test
    @Java8
    @Usage
    public void guessWhat() throws Exception {
        Function<String, Integer> f1 = Integer::valueOf;
        Function<Integer, String> f2 = String::valueOf;
        f1.compose(f2.andThen(f1).andThen(f2)).compose(f1).apply("10"); //10

        Function<Integer, Integer> f = i -> i + 1;
        System.out.println(
            f.compose(f.andThen(f).andThen(f)).compose(f).apply(1) //???
        );
        System.out.println(
            f.compose(f.compose(f).andThen(f)).andThen(f).apply(1) //???
        );
    }
}
