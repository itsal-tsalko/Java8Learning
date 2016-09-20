package function;

import function.Annotations.*;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class ConsumerTest {

    @Test
    @BeforeJava8
    @Declaration
    public void consumerUsingAnonymousClass() throws Exception {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
    }

    @Test
    @Declaration
    @BeforeJava8
    public void consumerUsingConcreteClass() throws Exception {
        class GenericConsumer<T> implements Consumer<T>{
            @Override
            public void accept(T t) {
                System.out.println(t);
            }
        }
        class MyConsumer implements Consumer<String>{
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        }
        Consumer<String> consumer1 = new MyConsumer();
        Consumer<String> consumer2 = new GenericConsumer<>();
        Consumer<Integer> consumer3 = new GenericConsumer<>();
    }

    @Test
    @Declaration
    @Java8
    public void consumerUsingLambda() throws Exception {
        //void accept(T t); => void accept(String s);
        Consumer<String> consumer1 = s -> {System.out.println(s);};
        Consumer<String> consumer2 = (String s) -> {System.out.println(s);};
        //Consumer<String> consumer3 = (Integer s) -> {System.out.println(s);}; //Doesn't compile
        Consumer<Integer> consumer4 = (Integer s) -> {System.out.println(s);};
    }

    @Test
    @Declaration
    @Java8
    public void consumerUsingMethodReference() throws Exception {
        //void accept(T t); => void accept(String s);
        //                     void println(String x);
        Consumer<String> consumer1 = System.out::println;
        Consumer<String> consumer2 = System.out::println;
        Consumer<Integer> consumer3 = System.out::println;
    }

    @Test
    @Declaration
    @Java8
    public void consumerUsingDifferentMethodReferences() throws Exception {
        Consumer<String> consumer1 = System.out::println;
        Consumer<Integer> consumer2 = System.out::println;
        consumer1 = this::toPrint;
        consumer1 = ConsumerTest::staticPrint;
        //void accept(Integer i);
        //consumer2 = this::toPrint;  //Doesn't compile
        //consumer2 = ConsumerTest::staticPrint; //Doesn't compile
    }

    private void toPrint(String s){
        System.out.println(s);
    }

    public static void staticPrint(String s){
        System.out.println(s);
    }

    @Test
    @Usage
    @Java8
    public void usingConsumer() throws Exception {
        Consumer<String> consumer1 = System.out::println;
        Consumer<Integer> consumer2 = System.out::println;
        //void accept(String t); => void println(String s);
        consumer1.accept("I love Yulya!");
        //void accept(Integer t); => void println(Integer s);
        consumer2.accept(100);
    }

    @Test
    @Usage
    @Java8
    public void usingConsumerAsParameter() throws Exception {
        class Processor{
            //Higher order function (function consume other function)
            public <T> void process(T object, Consumer<T> delegate){
                System.out.println("Processing "+object);
                delegate.accept(object);
            }
        }

        Processor processor = new Processor();
        processor.process("I love Yulya!", s -> {
            System.out.println(s.toUpperCase());
        });
    }

    @Test
    @Usage
    @Java8
    public void usingConsumerAsResult() throws Exception {
        class ConsumerFactory{
            //Factory method
            public <T> Consumer<T> create(){
                return System.out::println;
            }
        }

        ConsumerFactory factory = new ConsumerFactory();
        Consumer<String> consumer1 = factory.create();
        Consumer<Integer> consumer2 = factory.create();
        consumer1.accept("I love Yulya!");
        consumer2.accept(100);
    }
}
