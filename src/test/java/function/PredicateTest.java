package function;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Iuliia Tsal-Tsalko
 */
public class PredicateTest {

    @Test
    @Annotations.BeforeJava8
    @Annotations.Declaration
    public void predicateUsingAnonymousClass(){
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };
    }

    @Test
    @Annotations.BeforeJava8
    @Annotations.Declaration
    public void predicateUsingConcreteClass(){
       MyPredicate myPredicate = new MyPredicate();
    }

    class MyPredicate implements Predicate<String>{

        @Override
        public boolean test(String s) {
            return false;
        }
    }

    @Test
    @Annotations.Java8
    @Annotations.Declaration
    public void predicateUsingLambda(){
        Predicate<String> predicate1 = s-> Boolean.valueOf(s);
        Predicate<String> notNull1 = s-> s!=null;
        Predicate<String> notNull2 = s-> Objects.nonNull(s);
        Predicate<String> notNull3 = Objects::nonNull;
    }

    @Test
    @Annotations.Java8
    @Annotations.Declaration
    public void predicateUsingStreams(){
        List<String> list = Collections.emptyList();
        list.stream()
                .filter(l -> !l.isEmpty())
                .collect(Collectors.toList());

        //Using Predicate#negate
        list.stream().filter(
                ((Predicate<String>) (String::isEmpty)).negate())
                .collect(Collectors.toList());

        list.stream()
                .filter(not(String::isEmpty));
    }

    private <T> Predicate<T> not(Predicate<T> predicate){
            return  predicate.negate();
    }

    @Test
    @Annotations.Java8
    @Annotations.Declaration
    public void predicateUsingOr(){

        List<String> list = Collections.emptyList();
        list.stream().filter(l->Objects.nonNull(l) || !l.isEmpty());

        list.stream()
                .filter(Objects::nonNull)
                .filter(l -> !l.isEmpty());
        //Using Predicate#or
        list.stream().filter(((Predicate<String>)Objects::nonNull)
                                .or(l -> !l.isEmpty()));
    }


    @Test
    @Annotations.Java8
    @Annotations.Declaration
    public void predicateUsingIsEqual(){
        List<String> list = Collections.emptyList();
        //Using Predicate#isEqual
        list.stream().filter(Predicate.isEqual("Bla"));
    }
}
