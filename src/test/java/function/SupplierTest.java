package function;

import function.Annotations.BeforeJava8;
import function.Annotations.Declaration;
import function.Annotations.Java8;
import function.Annotations.Usage;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class SupplierTest {

    @Test
    @BeforeJava8
    @Declaration
    public void supplierUsingAnonymousClass() throws Exception {
        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return null;
            }
        };
    }

    @Test
    @Declaration
    @BeforeJava8
    public void supplierUsingConcreteClass() throws Exception {

        class GenericSupplier<T> implements Supplier<T> {

            @Override
            public T get() {
                return null;
            }
        }

        class MySupplier implements Supplier<String> {

            @Override
            public String get() {
                return LocalDateTime.now().toString();
            }
        }

        Supplier<String> supplier1 = new MySupplier();
        Supplier<String> supplier2 = new GenericSupplier<String>();
        Supplier<Integer> supplier3 = new GenericSupplier<Integer>();
    }

    @Test
    @Declaration
    @Java8
    public void supplierUsingLambda() throws Exception {
        // T get(); => String get();
        Supplier<String> supplier = () -> LocalDateTime.now().toString();
    }

    @Test
    @Declaration
    @Java8
    public void supplierUsingMethodReference() throws Exception {
        // T get(); => T get();
        // String get();
        Supplier<String> supplier1 = this::toString;
        //Integer get();
        Supplier<Integer> supplier2 = this::hashCode;


    }

    @Test
    @Declaration
    @Java8
    public void supplierUsingDifferentMethodReferences() throws Exception {
        Supplier supplier = this::toPrint;
        supplier = SupplierTest::staticPrint;
    }

    private String toPrint() {
        return "Bla";
    }

    public static String staticPrint() {
        return "Bla";
    }

    @Test
    @Usage
    @Java8
    public void usingSupplier() throws Exception {
        //Define how to get user input
        Supplier<String> userInputSupplier = () -> new Scanner(System.in).next();
        processUserInput(userInputSupplier);
    }

    //Deferred user input
    public void processUserInput(Supplier<String> userInput) {
        //You know what to do with user input, but you don't know where to get it
        System.out.println(userInput.get());
    }
}
